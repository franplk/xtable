package com.emar.query.filter;

import com.emar.model.domain.ConfigColumn;

/**
 * @author franplk
 * Base Enclosure For Search Filter
 */
public class BaseFilter {

	protected ConfigColumn column;
	protected String type;
	protected String value;

	public ConfigColumn getColumn() {
		return column;
	}

	public void setColumn(ConfigColumn column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
