package com.emar.model.domain;

public class ChartModel {

	private String type;
	private String title;
	private String subTitle;
	private String description;
	

	public ChartModel () {}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString () {
		StringBuffer info = new StringBuffer();
		info.append("[");
		info.append("Type=").append(type).append(";");
		info.append("Title=").append(title).append(";");
		info.append("SubTitle=").append(subTitle).append(";");
		info.append("Description=").append(description).append(";");
		info.append("]");
		return info.toString();
	}
}
