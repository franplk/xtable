package com.emar.xreport.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.emar.arith.SimpleArithmetic;
import com.emar.es.EsTemplate;
import com.emar.exception.BussinessException;
import com.emar.model.domain.ConfigColumn;
import com.emar.query.domain.ESQuery;
import com.emar.util.StringUtil;
import com.emar.xreport.domain.JTableData;

/**
 * @author franplk 2016.07.11
 */
@Repository
public class ESDataDao extends BaseDao {

	@Resource(name = "formulaMap")
	private HashMap<String, String> formulaMap;

	@Resource(name = "realTemplate")
	private EsTemplate realTemplate;

	@Resource(name = "historyTemplate")
	private EsTemplate historyTemplate;

	/**
	 * Organize The Table Data
	 */
	public JTableData getTableData(ESQuery query) {
		JTableData tableData = null;
		try {
			if (query.isReal()) {
				tableData = realTemplate.queryTable(query);
			} else {
				tableData = historyTemplate.queryTable(query);
			}
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[Real Data Query Failed]", e);
		}
		if (tableData == null) {
			tableData = new JTableData();
		} else {
			dataFilter (query.getDimColumn(), tableData.getRows());
			complexIndex(query, tableData);
		}
		return tableData;
	}

	public Map<String, Object> getSumAndCount(ESQuery query) {
		Map<String, Object> sumData = null;
		try {
			if (query.isReal()) {
				sumData = realTemplate.querySumData(query);
			} else {
				sumData = historyTemplate.querySumData(query);
			}
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[Real Data Query Failed]", e);
		}
		if (sumData == null) {
			sumData = new HashMap<String, Object>();
		} else {
			composite(query.getIdxList(), sumData);
		}
		return sumData;
	}
	
	public List<Map<String, Object>> getDataList(ESQuery query) {
		List<Map<String, Object>> dataList = null;
		try {
			if (query.isReal()) {
				dataList = realTemplate.queryDataList(query);
			} else {
				dataList = historyTemplate.queryDataList(query);
			}
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[Real Data Query Failed]", e);
		}
		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		} else {
			dataFilter (query.getDimColumn(), dataList);
			complexIndex(query, dataList);
		}
		return dataList;
	}
	
	private JTableData complexIndex(ESQuery query, JTableData tableData) {
		List<ConfigColumn> columns = query.getIdxList();
		for (Map<String, Object> item : tableData.getFooter()) {
			composite(columns, item);
		}
		for (Map<String, Object> item : tableData.getRows()) {
			composite(columns, item);
		}
		return tableData;
	}
	
	private void complexIndex(ESQuery query, List<Map<String, Object>> dataList) {
		List<ConfigColumn> columns = query.getIdxList();
		for (Map<String, Object> item : dataList) {
			composite(columns, item);
		}
	}

	/**
	 * Computer Complex Index
	 */
	private void composite(List<ConfigColumn> columnList, Map<String, Object> item) {
		for (ConfigColumn column : columnList) {
			String field = column.getField();
			String formula = formulaMap.get(field);
			if (StringUtil.isNotEmpty(formula)) {
				Object value = computeFormula(item, formula);
				item.put(field, value);
			}
		}
	}
	
	private Object computeFormula(Map<String, Object> item, final String formula) {

		Set<String> params = analyze(formula);
		String expression = formula;
		for (String p : params) {
			Object value = item.get(p);
			if (null == value) {
				return "--";
			}
			expression = expression.replaceAll(p, String.valueOf(value));
		}
		try {
			SimpleArithmetic arithmetic = new SimpleArithmetic();
			arithmetic.parse(expression);
			return arithmetic.compute();
		} catch (Exception e) {
			return "--";
		}
	}

	private Set<String> analyze(final String formula) {
		Set<String> words = new HashSet<>();
		Pattern pattern = Pattern.compile("[_a-z]+");
		Matcher matcher = pattern.matcher(formula);
		while (matcher.find()) {
			words.add(matcher.group());
		}
		return words;
	}
}
