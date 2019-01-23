package com.emar.xreport.web;

import com.emar.xreport.util.StringUtil;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.model.domain.TableColumn;
import com.emar.xreport.query.ModelUtil;
import com.emar.xreport.query.domain.QueryBase;
import com.emar.xreport.query.domain.SQLQuery;
import com.emar.xreport.web.domain.QueryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Franplk
 *
 */
public abstract class BaseService {

	protected SQLQuery getModelQuery(DataModel dataModel, QueryBean queryBean) {

		SQLQuery query = new SQLQuery();

		setBaseQuery (query, dataModel, queryBean);
		
		/** Add Date Field **/
		query.setDateKey(dataModel.getDateKey());

		/** Add dimension Exclude **/
		ConfigColumn dimColumn = query.getDimColumn();
		String exValue = dimColumn.getExValue();
		if (null != exValue) {
			query.addParam(dimColumn, "neq", exValue);
		}

		/** Add Filter Conditions **/
		Map<String, Map<String, String>> paramMap = queryBean.getParamMap();
		if (null != paramMap) {
			for (Entry<String, Map<String, String>> param : paramMap.entrySet()) {
				String key = param.getKey();
				ConfigColumn column = dataModel.getColumn(key);
				if (null != column) {
					for (Entry<String, String> filter : param.getValue().entrySet()) {
						query.addParam(column, filter.getKey(), filter.getValue());
					}
				}
			}
		}

		/*** Set Query Table */
		String tableName = ModelUtil.getTableName(dataModel.getTheme());
		query.setTable(tableName);

		return query;
	}
	
	protected List<TableColumn> getTableColumn (List<ConfigColumn> configColumns) {
		List<TableColumn> tableColumns = new ArrayList<>();
		for (ConfigColumn column : configColumns) {
			TableColumn tableColumn = column.convert2TableColumn();
			if (column.getSorting() == 0) {
				tableColumn.setSortable(false);
			}
			tableColumns.add(tableColumn);
			String mapName = column.getMapName();
			if (StringUtil.isNotEmpty(mapName)) {
				String mapTitle = column.getMapTitle();
				tableColumns.add(new TableColumn(mapName, mapTitle));
			}
		}
		return tableColumns;
	}
	
	private void setBaseQuery (QueryBase query, DataModel dataModel, QueryBean queryBean) {
		
		/** Add Data Span **/
		query.setDateSpan(queryBean.getDateSpan());
		
		/** Add Query Pagination **/
		query.setPage(queryBean.getPage());
		
		/** Add Group Dim **/
		query.setDimColumn(dataModel.getDimColumn());
		
		/** Add Indicator **/
		List<String> idxList = queryBean.getIdxList();
		query.setIdxList(dataModel.getIdxes(idxList));
		
		/** Add Order **/
		String sortName = queryBean.getOrderBy();
		String sortType = queryBean.getOrderType();
		ConfigColumn sortField = dataModel.getColumn(sortName);
		if (sortField.getDim() == 0) {
			if (idxList != null && !idxList.contains(sortName)) {
				sortField = dataModel.getColumn(idxList.get(0));
			}
		}
		query.setSortField(sortField);
		query.setSortType(sortType);
	}
}
