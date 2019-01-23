package com.emar.xreport.query.filter;

import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.query.FilterMap;

/**
 * @author franplk
 * wrap search filter
 */
public class SearchFilter extends BaseFilter implements Comparable<SearchFilter> {

    public SearchFilter() {
    }

    public SearchFilter(ConfigColumn column, String value) {
        this(column, "eq", value);
    }

    public SearchFilter(ConfigColumn column, String type, String value) {
        this.column = column;
        this.type = type;
        this.value = value;
    }

    public String toSQL() {
        StringBuffer sql = new StringBuffer();
        sql.append(" AND ").append(column.getField());

        if (value.contains(",")) {
            sql.append(" IN ('").append(value.replaceAll(",", "','")).append("')");// The String Value need round with ''
        } else if ("inc".equals(type)) {// 包含
            sql.append(" LIKE '%").append(value).append("%'");
        } else {
            sql.append(FilterMap.getSign(type)).append("'").append(value).append("'");
        }

        return sql.toString();
    }

    public String havingSQL() {
        return new StringBuffer(" AND ")
                .append(column.getFormula())
                .append(FilterMap.getSign(type))
                .append("'").append(value).append("'")
                .toString();
    }

    @Override
    public int compareTo(SearchFilter filter) {
        String type = filter.getType();
        if (type.equals("inc") || this.type.equals("eq")) {
            return -1;
        } else if (type.equals("eq") || this.type.equals("inc")) {
            return 1;
        }
        return 0;
    }
}
