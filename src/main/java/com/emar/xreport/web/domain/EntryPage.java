package com.emar.xreport.web.domain;

public class EntryPage {

	private String name;
	private String title;
	private String url;

	public EntryPage () {}
	
	public EntryPage (String url) {
		this("New Page", url);
	}
	
	public EntryPage (String title, String url) {
		this.title = title;
		this.url = url;
	}
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
