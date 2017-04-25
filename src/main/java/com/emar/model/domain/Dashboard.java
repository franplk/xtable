package com.emar.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.emar.menu.domain.DrillMenu;

/**
 * 仪表盘，一个仪表盘包括包括数据模型和Chart模型,查询组件
 * 
 */
public class Dashboard implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String dimName;
	private String dateType;
	private String modelURL;
	private DataModel dataModel;
	private ChartModel chartModel;
	private List<DrillMenu> drillMenuList;
	
	public Dashboard() {}

	public Dashboard(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDimName() {
		return dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getModelURL() {
		return modelURL;
	}

	public void setModelURL(String modelURL) {
		this.modelURL = modelURL;
	}

	public void setDataModel(DataModel dataModel) {
		dataModel.setDimName(dimName);
		this.dataModel = dataModel;
	}

	public DataModel getDataModel () {
		return this.dataModel;
	}

	public ChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(ChartModel chartModel) {
		this.chartModel = chartModel;
	}
	
	public List<DrillMenu> getDrillMenuList() {
		return drillMenuList;
	}

	public void setDrillMenuList(List<DrillMenu> drillMenuList) {
		this.drillMenuList = drillMenuList;
	}

	public List<DrillMenu> getDrillMenus(String drillCon) {
		
		if (null == drillMenuList) {
			return null;
		}
		
		if (null == drillCon) {
			return drillMenuList;
		}
		
		List<DrillMenu> drillMenus = new ArrayList<>();
		for (DrillMenu menu : drillMenuList) {
			String name = menu.getName();
			if (!drillCon.contains(name)) {
				drillMenus.add(menu);
			}
		}
		
		return drillMenus;
	}
	
	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("仪表盘:[");
		info.append("Name=").append(name).append(";");
		info.append("DataModel=").append(dataModel).append(";");
		info.append("ChartModel=").append(chartModel).append(";");
		info.append("]");
		return info.toString();
	}
}
