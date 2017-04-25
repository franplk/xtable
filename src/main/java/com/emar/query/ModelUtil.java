package com.emar.query;

import com.emar.query.domain.SQLQuery;
import com.emar.query.domain.Pagination;
import com.emar.util.StringUtil;

public class ModelUtil {
	
	private ModelUtil () {}
	
	/**
	 * Get Table Name
	 */
	public static String getTableName (SQLQuery modelQuery, String prefix) {
		
		// level suffix
		boolean isTotal = false; int level = 1;

		// Construct Table Name
		StringBuffer tableName = new StringBuffer();
		
		// Prefix : system + theme
		tableName.append("xv_").append(prefix);
		tableName.append("_l").append(level);

		// total or not
		if (isTotal) {
			tableName.append("_1");
		} else {
			tableName.append("_0");
		}
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
		// 子查询
		if (StringUtil.isNotEmpty(havingSQL)) {
			sql.append("(SELECT * FROM ").append(table);
			// Condition
			sql.append(" WHERE ").append(whereSQL);
			// Group By
			sql.append(" GROUP BY ").append(groupSql);
			// Having
			sql.append(" HAVING ").append(havingSQL.substring(4));
			sql.append(") T");
		} else {
			sql.append(table);
			// Condition
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
