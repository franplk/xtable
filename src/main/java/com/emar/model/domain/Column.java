package com.emar.model.domain;

import java.io.Serializable;

/**
 * @author franplk 2016.08.24
 */
public class Column implements Serializable, Comparable<Column>{

	private static final long serialVersionUID = 1L;
	
	protected int code;
	protected String field;
	protected String title;
	protected String relyon;
	protected String dataType;
	
	public Column(){}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public Column(String field){
		this.field = field;
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

	public String getRelyon() {
		return relyon;
	}

	public void setRelyon(String relyon) {
		this.relyon = relyon;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public int compareTo(Column column) {
		int code = column.getCode();
		return this.code - code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Column) {
			Column column = (Column) obj;
			return this.field.equals(column.getField());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 1;
	}
}
