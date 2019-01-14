package com.emar.xreport.query.domain;

import java.io.Serializable;

/**
 * 页面类
 * 
 * @author jiaqiang 2015.01.29
 */
public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageSize;// 页面行数
	private int currPage;// 当面页面

	public Pagination() {
		this(1, 20);
	}

	public Pagination(int currPage, int pageSize) {
		this.currPage = currPage;
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getFrom () {
		return pageSize * (currPage - 1);
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("[");
		info.append("Page=").append(this.currPage).append(";");
		info.append("Rows=").append(this.pageSize).append(";");
		info.append("]");
		return info.toString();
	}
}
