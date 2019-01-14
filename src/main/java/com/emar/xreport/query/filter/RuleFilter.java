package com.emar.xreport.query.filter;

public class RuleFilter implements Comparable<RuleFilter> {

	private String name;
	private String sign;
	private String value;

	public RuleFilter() {}
	
	public RuleFilter(String name, String value){
		this(name, "eq", value);
	}
	
	public RuleFilter (String name, String sign, String value) {
		this.name = name;
		this.sign = sign;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toSQL () {
		StringBuffer sql = new StringBuffer();
		sql.append(" AND ").append(name);
		
		if (value.contains(",")) {
			// The String Value need round with ''
			sql.append(" IN ('").append(value.replaceAll(",","','")).append("')");
		} else {
			if ("inc".equals(sign)) {// 包含
				sql.append(" LIKE '%").append(value).append("%'");
			} else if ("neq".equals(sign)) {
				sql.append("<>'").append(value).append("'");
			} else if ("lte".equals(sign)) {
				sql.append("<='").append(value).append("'");
			} else if ("lt".equals(sign)) {
				sql.append("<'").append(value).append("'");
			} else if ("gte".equals(sign)) {
				sql.append(">='").append(value).append("'");
			} else if ("gt".equals(sign)) {
				sql.append(">'").append(value).append("'");
			} else {
				sql.append("='").append(value).append("'");
			}
		}
		
		return sql.toString();
	}

	@Override
	public int compareTo(RuleFilter filter) {
		String sign = filter.getSign();
		if (this.sign.equals(sign)) {
			return 0;
		} else {
			if (sign.equals("inc") || this.sign.equals("eq")) {
				return -1;
			} else if (sign.equals("eq") || this.sign.equals("inc")) {
				return 1;
			}
		}
		return 0;
	}
}
