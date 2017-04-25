package com.emar.xreport.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.emar.query.domain.Pagination;

public class QueryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String orderBy;
	private String orderType;
	private DateSpan dateSpan;
	private List<String> idxList;
	private Pagination pagination;
	private Map<String, Map<String,String>> paramMap;

	public DateSpan getDateSpan() {
		return dateSpan;
	}

	public void setDateSpan(DateSpan dateSpan) {
		this.dateSpan = dateSpan;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Pagination getPage() {
		return pagination;
	}

	public void setPage(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<String> getIdxList() {
		return idxList;
	}

	public void setIdxList(List<String> idxList) {
		this.idxList = idxList;
	}
	
	public Map<String, Map<String,String>> getParamMap() {
		return paramMap;
	}
	
	public void setParamMap(Map<String, Map<String,String>> paramMap) {
		this.paramMap = paramMap;
	}
	
	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("[");
		info.append("DateSpan=").append(dateSpan).append(";");
		info.append("OrderBy=").append(this.orderBy).append(";");
		info.append("OrderType=").append(this.orderType).append(";");
		info.append("Conditon=").append(this.paramMap).append(";");
		info.append("Pagination=").append(this.pagination);
		info.append("]");
		return info.toString();
	}
}
