package com.emar.query.filter;

import com.emar.model.domain.ConfigColumn;

/**
 * @author franplk
 * wrap search filter
 */
public class SearchFilter extends BaseFilter implements Comparable<SearchFilter> {

	public SearchFilter() {}
	
	public SearchFilter(ConfigColumn column, String value){
		this(column, "eq", value);
	}
	
	public SearchFilter (ConfigColumn column, String type, String value) {
		this.column = column;
		this.type = type;
		this.value = value;
	}

	public String toSQL () {
		StringBuffer sql = new StringBuffer();
		sql.append(" AND ").append(column.getField());
		
		if (value.contains(",")) {
			// The String Value need round with ''
			sql.append(" IN ('").append(value.replaceAll(",","','")).append("')");
		} else {
			if ("inc".equals(type)) {// 包含
				sql.append(" LIKE '%").append(value).append("%'");
			} else if ("neq".equals(type)) {
				sql.append("<>'").append(value).append("'");
			} else if ("lte".equals(type)) {
				sql.append("<='").append(value).append("'");
			} else if ("lt".equals(type)) {
				sql.append("<'").append(value).append("'");
			} else if ("gte".equals(type)) {
				sql.append(">='").append(value).append("'");
			} else if ("gt".equals(type)) {
				sql.append(">'").append(value).append("'");
			} else {
				sql.append("='").append(value).append("'");
			}
		}
		
		return sql.toString();
	}
	
	public String havingSQL () {
		StringBuffer sql = new StringBuffer();
		sql.append(" AND ").append(column.getFormula());
		if ("eq".equals(type)) {//
			sql.append("='").append(value).append("'");
		} else if ("lte".equals(type)) {
			sql.append("<='").append(value).append("'");
		} else if ("lt".equals(type)) {
			sql.append("<'").append(value).append("'");
		} else if ("gte".equals(type)) {
			sql.append(">='").append(value).append("'");
		} else if ("gt".equals(type)) {
			sql.append(">'").append(value).append("'");
		}
		
		return sql.toString();
	}

	@Override
	public int compareTo(SearchFilter filter) {
		String type = filter.getType();
		if (this.type.equals(type)) {
			return 0;
		} else {
			if (type.equals("inc") || this.type.equals("eq")) {
				return -1;
			} else if (type.equals("eq") || this.type.equals("inc")) {
				return 1;
			}
		}
		return 0;
	}
}
