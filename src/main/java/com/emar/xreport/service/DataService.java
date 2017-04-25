package com.emar.xreport.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emar.xreport.dao.DataDao;
import com.emar.xreport.dao.ESDataDao;
import com.emar.xreport.domain.DateSpan;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.QueryBean;
import com.emar.api.DataInter;
import com.emar.arith.DataUtil;
import com.emar.arith.FormulaUtil;
import com.emar.model.domain.ConfigColumn;
import com.emar.model.domain.Dashboard;
import com.emar.model.domain.DataModel;
import com.emar.model.domain.TableColumn;
import com.emar.model.service.BoardService;
import com.emar.query.domain.ESQuery;
import com.emar.query.domain.QueryBase;
import com.emar.query.domain.SQLQuery;
import com.emar.util.DateUtil;
import com.emar.util.StringUtil;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data;
 */
@Service
@Component
public class DataService extends BaseService implements DataInter<JTableData> {

	@Resource(name = "formulaMap")
	private HashMap<String, String> formulaMap;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	private ESDataDao esDataDao;
	
	@Override
	public JTableData getData(String boardId, QueryBean queryBean) {
		
		// Get Dash board
		Dashboard dashboard = boardService.getBoard(boardId);
		DataModel dataModel = dashboard.getDataModel();
		
		// Get Date Span
		DateSpan dateSpan = queryBean.getDateSpan();
		if (DateUtil.isToday(dateSpan)) {
			return getTodayData(dataModel, queryBean);
		}
		
		JTableData hisData = getHisData(dataModel, queryBean);
		
		if (!DateUtil.containsToday(dateSpan)) {
			return hisData;
		} else {
			String dimName = dataModel.getDimColumn().getField();
			
			// Get All Dim Value
			if (!"rep_date".equals(dimName)) {
				StringBuffer dimValue = new StringBuffer();
				List<Map<String, Object>> rows = hisData.getRows();
				for (Map<String, Object> item : rows) {
					Object itemValue = item.get("dimCode");
					if (null == itemValue) {
						itemValue = item.get(dimName);
					}
					dimValue.append(",").append(itemValue);
				}
				
				if (StringUtil.isNotEmpty(dimValue.toString())) {
					String dimConValue = dimValue.toString().substring(1);
					Map<String, Map<String, String>> paraMap = queryBean.getParamMap();
					if (null == paraMap) {
						paraMap = new HashMap<String, Map<String, String>>();
						queryBean.setParamMap(paraMap);
					}
					Map<String, String> dimCon = paraMap.get(dimName);
					if (null == dimCon) {
						dimCon = new HashMap<String, String>();
						dimCon.put("eq", dimConValue);
						paraMap.put(dimName, dimCon);
					} else {
						String value = dimCon.get("eq");
						if (value == null || dimConValue.contains(value)) {
							dimCon.put("eq", dimConValue);
						} else {
							dimCon.put("eq", value + "," + dimConValue);
						}
					}
				}
			}
			
			// Cancel Pagination
			queryBean.setPage(null);
			
			// Query Today Data With The History Dims As Condition
			JTableData todayData = getTodayData(dataModel, queryBean);
			
			// Merge The History Data And Today Data
			JTableData tableData = mergeData(hisData, todayData, dimName);
			
			// ReCaculate The Complex Index
			complexIndex(tableData);
			
			return tableData;
		}
	}
	
	private JTableData mergeData(final JTableData hisData, JTableData todayData, final String dimName) {
		
		JTableData tableData = new JTableData();
		
		// Set Columns
		List<TableColumn> columns = hisData.getColumns();
		tableData.setColumns(columns);
		
		// Set Total
		Long total = hisData.getTotal();
		tableData.setTotal(total);
		
		// Merge Row Data
		List<Map<String, Object>> tableRows = null;
		List<Map<String, Object>> dayRows = todayData.getRows();
		List<Map<String, Object>> hisRows = hisData.getRows();
		if ("rep_date".equals(dimName)) {
			tableRows = new ArrayList<>();
			tableRows.addAll(dayRows);
			tableRows.addAll(hisRows);
		} else {
			tableRows = mergeData(hisRows, dayRows, dimName, columns);
		}
		tableData.setRows(tableRows);
		
		// Merge Sum Data
		List<Map<String, Object>> hisFooter = hisData.getFooter();
		List<Map<String, Object>> dayFooter = todayData.getFooter();
		List<Map<String, Object>> tableFooter = mergeData(hisFooter, dayFooter, dimName, columns);
		tableData.setFooter(tableFooter);
		
		return tableData;
	}
	
	private List<Map<String, Object>> mergeData(final List<Map<String, Object>> hisRows, final List<Map<String, Object>> dayRows, final String dimName, final List<TableColumn> columns) {
		List<Map<String, Object>> rows = new ArrayList<>();
		for (Map<String, Object> hisItem : hisRows) {
			Object value = hisItem.get(dimName);
			Map<String, Object> dayItem = getMapItem (dayRows, dimName, value);
			if (null == dayItem) {
				rows.add(hisItem);
			} else {
				Map<String, Object> rowItem = mergeData(hisItem, dayItem, columns);
				rows.add(rowItem);
			}
		}
		return rows;
	}
	
	private Map<String, Object> mergeData(final Map<String, Object> hisRow, final Map<String, Object> dayRow, final List<TableColumn> columns) {
		Map<String, Object> rowItem = new HashMap<>();
		
		Object dimMapValue = hisRow.get("dimCode");
		if (dimMapValue != null) {
			rowItem.put("dimCode", dimMapValue);
		}
		
		for (TableColumn column : columns) {
			String fieldName = column.getField();
			Object hisField = hisRow.get(fieldName);
			Object dayField = dayRow.get(fieldName);
			
			String dataType = column.getDataType();
			if (dataType != null && dataType.matches("digit|int")) {
				BigDecimal hisValue = DataUtil.getBigDecimal(hisField);
				BigDecimal dayValue = DataUtil.getBigDecimal(dayField);
				rowItem.put(fieldName, hisValue.add(dayValue));
			} else {
				rowItem.put(fieldName, hisField);
			}
		}
		return rowItem;
	}

	private Map<String, Object> getMapItem (final List<Map<String, Object>> dayRows, final String key, final Object value) {
		for (Map<String, Object> item : dayRows) {
			if (String.valueOf(item.get(key)).equalsIgnoreCase(value.toString())) {
				return item;
			}
		}
		return null;
	}
	
	private void complexIndex(JTableData tableData) {
		List<TableColumn> columns = tableData.getColumns();
		for (Map<String, Object> item : tableData.getFooter()) {
			composite(columns, item);
		}
		for (Map<String, Object> item : tableData.getRows()) {
			composite(columns, item);
		}
	}
	
	/**
	 * Computer Complex Index
	 */
	private void composite(List<TableColumn> columnList, Map<String, Object> item) {
		for (TableColumn column : columnList) {
			String field = column.getField();
			String formula = formulaMap.get(field);
			if (StringUtil.isNotEmpty(formula)) {
				Object value = FormulaUtil.computeFormula(item, formula);
				item.put(field, value);
			}
		}
	}
	
	/**
	 * Get Pagination Data For Specified Data Model
	 */
	private JTableData getHisData(DataModel dataModel, QueryBean queryBean) {
		
		JTableData tableData = null;
		List<TableColumn> tableColumn = null;
		
		// IF ES Store Then Query From ES
		if (isEsStore (dataModel, queryBean)) {
			ESQuery esQuery = getESQuery(dataModel, queryBean);
			esQuery.setReal(false);
			tableData = esDataDao.getTableData(esQuery);
			tableColumn = getTableColumn (esQuery, true);
		} else {
			// Query From Mysql
			SQLQuery mquery = getModelQuery(dataModel, queryBean);
			tableData = dataDao.getTableData(mquery);
			tableColumn = getTableColumn (mquery, false);
		}
		
		tableData.setColumns(tableColumn);
		
		return tableData;
	}

	// Today Data
	private JTableData getTodayData(DataModel dataModel, QueryBean queryBean) {
		
		ESQuery esQuery = getESQuery(dataModel, queryBean);
		esQuery.setReal(true);
		
		JTableData tableData = esDataDao.getTableData(esQuery);
		List<TableColumn> tableColumn = getTableColumn (esQuery, true);
		tableData.setColumns(tableColumn);
		
		return tableData;
	}
	
	private List<TableColumn> getTableColumn (QueryBase query, boolean isEs) {
		// Columns
		List<ConfigColumn> columns = new ArrayList<>();
		columns.add(query.getDimColumn());
		columns.addAll(query.getIdxList());
		
		return getTableColumn(columns, isEs);
	}
	
	private boolean isEsStore (DataModel dataModel, QueryBean queryBean) {
		
		// Dim Is 'domain|adslot_id' Or Not
		String dimName = dataModel.getDimName();
		if (dimName.matches("domain|adslot_id")) {
			return true;
		}
		
		// Query Parameter Contains 'domain|adslot_id' Or Not
		Map<String, Map<String, String>> paramMap = queryBean.getParamMap();
		if (null != paramMap && (paramMap.containsKey("domain") || paramMap.containsKey("adslot_id"))) {
			return true;
		}
		
		return false;
	}
}
