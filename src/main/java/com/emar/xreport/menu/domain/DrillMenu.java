package com.emar.xreport.menu.domain;

import java.io.Serializable;

public class DrillMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;// Menu Name
	private String title;// Menu title
	private String label;// Menu Label
	private String url;// Menu URL

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
