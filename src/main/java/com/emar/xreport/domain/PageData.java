package com.emar.xreport.domain;

import java.io.Serializable;

/**
 * @domain WebPage Data Model
 * @function
 */
public class PageData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private JTableData tableData;// Used For EasyUI Table
	private ChartData chartData;// Used For HighCharts Data

	public PageData () {}

	public JTableData getTableData() {
		return tableData;
	}

	public void setTableData(JTableData tableData) {
		this.tableData = tableData;
	}

	public ChartData getChartData() {
		return chartData;
	}

	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}
}
