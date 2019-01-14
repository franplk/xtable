package com.emar.xreport.model.domain;

/**
 * @author franplk 2016.08.24
 */
public class TableColumn extends Column {

	private static final long serialVersionUID = 1L;

	private String align;
	private boolean sortable;

	public TableColumn () {}
	
	public TableColumn (String field, String title) {
		this.align = "left";
		this.field = field;
		this.title = title;
	}
	
	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
}
