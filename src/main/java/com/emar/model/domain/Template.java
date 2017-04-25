/**
 * 
 */
package com.emar.model.domain;

import java.io.Serializable;
import java.util.List;

import com.emar.query.domain.ConBean;
import com.emar.util.StringUtil;

/**
 * @author franplk
 *
 */
public class Template implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tid;
	private String title;
	private String board;
	private String sortName;
	private String idxes;
	private List<ConBean> filters;

	public Template() {
	}
	
	public Template(String tid) {
		this.tid = tid;
	}
	
	public Template(String tid, String title) {
		this.tid = tid;
		this.title = title;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBoard() {
		return board;
	}

	public void setBoardId(String board) {
		this.board = board;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getIdxes() {
		return idxes;
	}

	public void setIdxes(String idxes) {
		this.idxes = idxes;
	}

	public List<ConBean> getFilters() {
		return filters;
	}

	public void setFilters(List<ConBean> filters) {
		this.filters = filters;
	}

	public boolean isValid() {
		if (StringUtil.isEmpty(board)) {
			return false;
		}
		if (StringUtil.isEmpty(idxes)) {
			return false;
		}
		return true;
	}
}
