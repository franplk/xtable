package com.emar.xreport.query.domain;

import com.emar.xreport.util.DateUtil;
import com.emar.xreport.util.StringUtil;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.query.filter.SearchFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Franplk 2016.07.18
 */
public class SQLQuery extends QueryBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String table;// 数据表
    private String dateKey;

    private List<SearchFilter> where;// 查询过滤条件
    private List<SearchFilter> having;// 查询过滤条件

    public SQLQuery() {
    }

    /*** Query Table **/
    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return this.table;
    }

    public String getDateKey() {
        return dateKey;
    }

    public void setDateKey(String dateKey) {
        this.dateKey = dateKey;
    }

    /*** Where Condition **/
    public void addWhere(SearchFilter filter) {
        if (null == this.where) {
            this.where = new ArrayList<SearchFilter>();
        }
        this.where.add(filter);
    }

    public List<SearchFilter> getWhere() {
        return this.where;
    }

    public List<SearchFilter> getHaving() {
        return having;
    }

    public void setHaving(List<SearchFilter> having) {
        this.having = having;
    }

    /*** Having Condition **/
    public void addHaving(SearchFilter filter) {
        if (null == this.having) {
            this.having = new ArrayList<SearchFilter>();
        }
        this.having.add(filter);
    }

    public String getGroupSQL() {
        return dimColumn.getField();
    }

    /**
     * Contact Dim Column To SQL
     */
    public String getDimSQL() {
        return new StringBuffer(dimColumn.getField()).append(",").toString();
    }

    /**
     * Contact Indicator Column To SQL
     */
    public String getIdxSQL() {
        StringBuffer sql = new StringBuffer();
        for (ConfigColumn column : idxList) {
            sql.append(",");
            String dataType = column.getDataType();
            if ("digit".equals(dataType)) {
                sql.append("ROUND(").append(column.getFormula()).append(",2)");
            } else {
                sql.append(column.getFormula());
            }
            sql.append(" AS ").append(column.getField());
        }
        return sql.toString().substring(1);
    }

    /**
     * Contact Query Condition To SQL
     */
    public String getWhereSQL() {
        StringBuffer sql = new StringBuffer();
        // Date Condition
        if (StringUtil.isNotEmpty(dateKey)) {
            if (DateUtil.isOneday(dateSpan)) {// If One Day, Search From Table Of Day
                String start = dateSpan.getStartDate();
                sql.append(dateKey).append("='").append(start).append("'");
            } else {// If Current Month Search From Table Of Month, The Date Condition Can Be Ignored
                String start = dateSpan.getStartDate();
                String end = dateSpan.getEndDate();
                sql.append(dateKey).append(">='").append(start).append("' AND ");
                sql.append(dateKey).append("<='").append(end).append("'");
            }
        } else {
            sql.append("1 = 1");
        }
        // Where Condition
        if (null != where) {
            Collections.sort(where);
            for (SearchFilter filter : where) {
                sql.append(filter.toSQL());
            }
        }
        return sql.toString();
    }

    public String getHavingSQL() {
        StringBuffer sql = new StringBuffer();
        // Having Condition
        if (null != this.having) {
            Collections.sort(having);
            for (SearchFilter filter : having) {
                sql.append(filter.havingSQL());
            }
        }
        return sql.toString();
    }

    /**
     * Contact Order Column To SQL
     */
    public String getOrderSql() {
        StringBuffer sql = new StringBuffer();
        ConfigColumn sort = this.getSortField();
        sql.append(sort.getField());
        sql.append(" ").append(getSortType());
        return sql.toString();
    }

    public void addParam(ConfigColumn column, String sign, String value) {
        if (null == column) {
            return;
        }
        int dim = column.getDim();
        if (dim == 1) {
            addWhere(new SearchFilter(column, sign, value));
        } else {
            addHaving(new SearchFilter(column, sign, value));
        }
    }
}
