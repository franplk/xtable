package com.emar.download.domain;

import java.util.ArrayList;
import java.util.List;

public class ExcelHeader {

	private String field;
	private String title;
	private String format;
	private List<ExcelHeader> subHeader;

	public ExcelHeader(){}
	
	public ExcelHeader(String field, String title){
		this.field = field;
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public List<ExcelHeader> getSubHeader() {
		return subHeader;
	}

	public void setSubHeader(List<ExcelHeader> subHeader) {
		this.subHeader = subHeader;
	}
	
	public void addSubHeader(ExcelHeader header){
		if(subHeader == null){
			this.subHeader = new ArrayList<ExcelHeader>(0);
		}
		subHeader.add(header);
	}
}
