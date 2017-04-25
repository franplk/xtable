package com.emar.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;

import com.emar.model.domain.ConfigColumn;
import com.emar.query.domain.ESQuery;
import com.emar.query.domain.Pagination;
import com.emar.util.DateUtil;
import com.emar.xreport.domain.JTableData;

public class EsTemplate extends AbstractESOperation {

	private String dateType;
	
	public EsTemplate() {}

	public String getIndex(ESQuery query) {
		if ("day".equals(dateType)) {
			return prefix + DateUtil.getTodayStr("yyyyMMdd");
		} else {
			return prefix + "_top_" + dateType;
		}
	}
	
	@Override
	public JTableData queryTable(ESQuery query) {

		// Set Query Index
		String index = getIndex(query);
		Client client = getClient();
		
		// Perform Query
		MultiSearchRequestBuilder multiSearchBuilder = client.prepareMultiSearch();
		multiSearchBuilder.add(getStatSearch(query, index));
		multiSearchBuilder.add(getListSearch(query, index));
		MultiSearchResponse multiResponse = multiSearchBuilder.execute().actionGet();
		MultiSearchResponse.Item[] items = multiResponse.getResponses();

		// Packaging Table Data
		JTableData tableData = new JTableData();
		
		// Summary Data And Count
		SearchResponse sumResponse = items[0].getResponse();
		Map<String, Object> sumData = getSumMap(sumResponse, query);
		long records = (long) sumData.remove("records");
		tableData.setTotal(Math.min(1000, records));
		tableData.addSum(sumData);
		
		// All List Data
		SearchResponse listResponse = items[1].getResponse();
		List<Map<String, Object>> datas = getListMap(listResponse, query);
		
		// Set Pagination
		Pagination page = query.getPage();
		if (null != page) {
			int from = page.getFrom();
			int toIndex = from + page.getPageSize();
			if (toIndex > records) {
				toIndex = (int) records;
			}
			tableData.setRows(datas.subList(from, toIndex));
		} else {
			tableData.setRows(datas);
		}

		return tableData;
	}

	/**
	 * Summary Query
	 */
	public Map<String, Object> querySumData(ESQuery query) {
		String index = getIndex(query);
		SearchRequestBuilder searchBuilder = getStatSearch(query, index);
		SearchResponse response = searchBuilder.execute().actionGet();
		
		// Summary Data
		Map<String, Object> sumData = getSumMap(response, query);
		
		return sumData;
	}

	/**
	 * Pagination Query
	 */
	public List<Map<String, Object>> queryDataList(ESQuery query) {
		String index = getIndex(query);
		SearchRequestBuilder searchBuilder = getListSearch(query, index);
		SearchResponse response = searchBuilder.execute().actionGet();
		
		return getListMap(response, query);
	}
	
	/**
	 * Summary Search
	 */
	public SearchRequestBuilder getStatSearch(ESQuery query, String index) {

		SearchRequestBuilder esSearch = getClient().prepareSearch(index).setTypes(type);

		// Count Aggregation
		String dimField = query.getDimField();
		esSearch.addAggregation(AggregationBuilders.cardinality(dimField).field(dimField));

		// Sum Aggregation
		for (ConfigColumn column : query.getIdxList()) {
			String field = column.getField();
			esSearch.addAggregation(AggregationBuilders.sum(field).field(field));
		}

		// Query Filter
		esSearch.setQuery(query.getQueryBuilder());

		return esSearch;
	}
	/**
	 * Get Pagination Data List
	 */
	public SearchRequestBuilder getListSearch(ESQuery query, String index) {

		// Group Aggregation
		String dimField = query.getDimField();
		TermsBuilder termsBuilder = AggregationBuilders.terms(dimField).field(dimField);

		// Group Filter
		/*List<ESFilter> aggFilter = query.getAggFilter();
		if (null != aggFilter) {
			termsBuilder.collectMode(mode);
		}*/
		
		// Group Sum Aggregation
		for (ConfigColumn column : query.getIdxList()) {
			String field = column.getField();
			termsBuilder.subAggregation(AggregationBuilders.sum(field).field(field));
		}

		// Group Order
		ConfigColumn sortField = query.getSortField();
		if (sortField != null) {
			if (sortField.getDim() == 1) {
				termsBuilder.order(Terms.Order.term(query.isAsc()));
			} else {
				String fieldName = sortField.getField();
				termsBuilder.order(Terms.Order.aggregation(fieldName, query.isAsc()));
			}
		}
		
		termsBuilder.size(500);
		
		// Construct Search Builder
		SearchRequestBuilder esSearch = getClient().prepareSearch(index).setTypes(type);
		esSearch.addAggregation(termsBuilder).setQuery(query.getQueryBuilder());

		return esSearch;
	}

	/**
	 * Packaging Summary Data
	 */
	private Map<String, Object> getSumMap(SearchResponse response, ESQuery query) {

		Aggregations aggregations = response.getAggregations();

		// Get Aggregation Summary
		List<ConfigColumn> sumFields = query.getIdxList();
		Map<String, Object> rowMap = getItem (aggregations, sumFields);
		
		// Set Aggregation Count
		String dimField = query.getDimField();
		rowMap.put(dimField, "Total");
		InternalCardinality count = aggregations.get(dimField);
		rowMap.put("records", count.getValue());
		
		return rowMap;
	}

	/**
	 * Packaging Pagination Data
	 */
	private List<Map<String, Object>> getListMap(SearchResponse response, ESQuery query) {

		String dimField = query.getDimField();
		List<ConfigColumn> sumFields = query.getIdxList();

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(20);
		Terms dimAggTerm = response.getAggregations().get(dimField);
		
		List<Bucket> bukList = dimAggTerm.getBuckets();
		for (Terms.Bucket tempBk : bukList) {
			Aggregations aggregations = tempBk.getAggregations();
			Map<String, Object> rowMap = getItem (aggregations, sumFields);
			rowMap.put(dimField, tempBk.getKey());
			dataList.add(rowMap);
		}
		return dataList;
	}
	
	private Map<String, Object> getItem (Aggregations aggregations, List<ConfigColumn> fields) {
		Map<String, Object> rowMap = new HashMap<String, Object>();
		for (ConfigColumn column : fields) {
			String fieldName = column.getField();
			Sum aggSum = (Sum) aggregations.get(fieldName);
			double value = aggSum.getValue();
			String dataType = column.getDataType();
			if ("digit".equals(dataType)) {
				rowMap.put(fieldName, String.format("%.2f", value));
			} else {
				rowMap.put(fieldName, String.format("%.0f", value));
			}
		}
		return rowMap;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
}
