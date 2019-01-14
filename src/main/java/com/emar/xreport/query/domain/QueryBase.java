package com.emar.xreport.query.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.util.StringUtil;
import com.emar.xreport.web.domain.DateSpan;

/**
 * @author Franplk 2016.07.18
 */
public class QueryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Pagination page;// Pagination
	protected DateSpan dateSpan;// Query Span
	protected String sortType;// Sort Type
	protected ConfigColumn sortField;// Sort Field
	protected ConfigColumn dimColumn;// Group Field
	protected List<ConfigColumn> idxList;// Index Fields
	protected List<ConfigColumn> comList;// Complex Fields

	public QueryBase() {}

	public ConfigColumn getSortField() {
		if (null == sortField) {
			sortField = idxList.get(0);
		}
		return sortField;
	}

	public void setSortField(ConfigColumn sortField) {
		this.sortField = sortField;
	}
	
	public String getSortType() {
		if (StringUtil.isEmpty(sortType)) {
			return "desc";
		}
		return sortType;
	}

	public boolean isAsc () {
		return "desc".equals(sortType) ? false : true;
	}
	
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public DateSpan getDateSpan() {
		return dateSpan;
	}

	public void setDateSpan(DateSpan dateSpan) {
		this.dateSpan = dateSpan;
	}

	public ConfigColumn getDimColumn() {
		return dimColumn;
	}
	
	public void setDimColumn(ConfigColumn dimColumn) {
		this.dimColumn = dimColumn;
	}
	
	public String getDimField() {
		return dimColumn.getField();
	}
	
	public List<ConfigColumn> getIdxList() {
		return idxList;
	}

	public void setIdxList(List<ConfigColumn> idxList) {
		Collections.sort(idxList);
		this.idxList = idxList;
	}

	public List<ConfigColumn> getComList() {
		return comList;
	}

	public void setComList(List<ConfigColumn> comList) {
		this.comList = comList;
	}
}
