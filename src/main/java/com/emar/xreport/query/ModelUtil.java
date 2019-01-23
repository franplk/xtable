package com.emar.xreport.query;

import com.emar.xreport.util.StringUtil;
import com.emar.xreport.query.domain.Pagination;
import com.emar.xreport.query.domain.SQLQuery;

public class ModelUtil {
	
	private ModelUtil () {}
	
	/**
	 * Get Table Name
	 */
	public static String getTableName (String prefix) {
		StringBuffer tableName = new StringBuffer();
		
		// Prefix : system + theme
		tableName.append("tb_").append(prefix);

		return tableName.toString();
	}

	/**
	 * Get Sum And Count
	 */
	public static String getSumAndCountSQL (SQLQuery query) {
		
		String table = query.getTable();
		String idxSql = query.getIdxSQL();
		String groupSql = query.getGroupSQL();
		String whereSQL = query.getWhereSQL();
		
		StringBuffer sql = new StringBuffer();
		// Count
		sql.append("SELECT COUNT(DISTINCT ").append(groupSql).append(") AS records,");
		// Total
		sql.append("'TOTAL' AS ").append(query.getDimField()).append(",").append(idxSql);
		sql.append(" FROM ");
		
		String havingSQL = query.getHavingSQL();
		if (StringUtil.isNotEmpty(havingSQL)) {// 子查询
			sql.append("(SELECT * FROM ").append(table);
			sql.append(" WHERE ").append(whereSQL);// Condition
			sql.append(" GROUP BY ").append(groupSql);// Group By
			sql.append(" HAVING ").append(havingSQL.substring(4));// Having
			sql.append(") T");
		} else {
			sql.append(table);// Condition
			sql.append(" WHERE ").append(whereSQL);
		}
		
		return sql.toString();
	}
	
	/**
	 * Get List SQL, Page Or Not
	 */
	public static String getListSql(SQLQuery query) {
		
		String table = query.getTable();
		String dimSql = query.getDimSQL();
		String idxSql = query.getIdxSQL();
		String groupSql = query.getGroupSQL();
		String whereSQL = query.getWhereSQL();
		String havingSQL = query.getHavingSQL();
		Pagination page = query.getPage();
		
		StringBuffer sql = new StringBuffer();
		// Query Columns
		sql.append("SELECT ").append(dimSql).append(idxSql);
		sql.append(" FROM ").append(table);
		
		// Condition
		sql.append(" WHERE ").append(whereSQL);
		
		// Group By
		sql.append(" GROUP BY ").append(groupSql);
		
		// Having
		if (StringUtil.isNotEmpty(havingSQL)) {
			sql.append(" HAVING ").append(havingSQL.substring(4));
		}
		
		// Order By
		sql.append(" ORDER BY ").append(query.getOrderSql());
		
		// Pagination
		if (page != null) {
			sql.append(" LIMIT ").append(page.getFrom());
			sql.append(",").append(page.getPageSize());
		}

		return sql.toString();
	}
}
