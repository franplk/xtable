package com.emar.xreport.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franplk 2015.01.14
 */
public class DataModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String theme;
	private String themeId;
	private String dateKey;
	private String dimName;
	private String orderName;
	private String orderType;
	private String exDimValue;
	private Map<String, ConfigColumn> columnMap;

	public DataModel() {
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getDimName() {
		return dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public String getDateKey() {
		return dateKey;
	}

	public void setDateKey(String dateKey) {
		this.dateKey = dateKey;
	}

	public String getExDimValue() {
		return exDimValue;
	}

	public void setExDimValue(String exDimValue) {
		this.exDimValue = exDimValue;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Map<String, ConfigColumn> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, ConfigColumn> columnMap) {
		this.columnMap = columnMap;
	}

	public ConfigColumn getColumn(String name) {
		if (name == null) {
			return columnMap.get(dimName);
		}
		return columnMap.get(name);
	}

	public void addColumn(ConfigColumn column) {
		if (columnMap == null) {
			columnMap = new LinkedHashMap<>();
		}
		columnMap.put(column.getField(), column);
	}

	public List<ConfigColumn> getIdxList() {
		List<ConfigColumn> columnList = new ArrayList<>();
		for (ConfigColumn column : columnMap.values()) {
			if (column.getDim() == 0) {
				columnList.add(column);
			}
		}
		Collections.sort(columnList);
		return columnList;
	}

	public ConfigColumn getDimColumn() {
		return columnMap.get(dimName);
	}

	public List<ConfigColumn> getIdxes(List<String> idxNames) {
		List<ConfigColumn> columns = new ArrayList<ConfigColumn>();
		
		if (idxNames == null || idxNames.isEmpty()) {
			for (ConfigColumn column : columnMap.values()) {
				if (1 == column.getFlag() && 0 == column.getDim()) {
					columns.add(column);
				}
			}
		} else {
			for (String name : idxNames) {
				ConfigColumn column = columnMap.get(name);
				if (null != column) {
					columns.add(column);
				}
			}
		}
		return columns;
	}
}
