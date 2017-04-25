package com.emar.query;

import java.util.Map;
import java.util.Map.Entry;

import com.emar.query.domain.Pagination;
import com.emar.query.filter.RuleFilter;
import com.emar.util.DateUtil;
import com.emar.xreport.domain.DateSpan;
import com.emar.xreport.domain.QueryBean;

public class RuleModelUtil {
	
	private RuleModelUtil () {}
	
	/**
	 * Get Table Name
	 */
	public static String getTableName (String theme) {
		
		// Construct Table Name
		StringBuffer tableName = new StringBuffer();
		
		tableName.append("xv_");
		
		// Prefix : system + theme
		tableName.append(theme);
		
		tableName.append("_l1_0");
		
		return tableName.toString();
	}
	
	/**
	 * Get Count SQL
	 */
	public static String getCountSQL(QueryBean queryBean, String theme) {
		
		StringBuffer sql = new StringBuffer(64);

		sql.append("SELECT COUNT(1) AS records");
		sql.append(" FROM ").append(getTableName(theme));
		sql.append(getWhereSQL(queryBean));
		
		return sql.toString();
	}
	
	/**
	 * Get List SQL, Page Or Not
	 */
	public static String getListSql(QueryBean queryBean, String theme) {
		
		StringBuffer sql = new StringBuffer(64);
		
		if (theme.contains("request")) {
			sql.append("SELECT channel_id,device_type,view_type,");
		} else if (theme.contains("camp")) {
			sql.append("SELECT project_id,camp_id,channel_id,");
		}
		
		sql.append("begin_step,begin_name,begin_count,end_count,ROUND(100*end_count/begin_count,2) AS pass_rate");
		
		sql.append(" FROM ").append(getTableName(theme));
		
		sql.append(getWhereSQL(queryBean));

		/** Add Order **/
		sql.append(" ORDER BY day_id ASC,");
		if (theme.equals("request")) {
			sql.append("channel_id,device_type,view_type,");
		} else if (theme.equals("camp")) {
			sql.append("project_id,camp_id,channel_id,");
		}
		sql.append("begin_step ASC");
		
		// Pagination
		Pagination page = queryBean.getPage();
		if (page != null) {
			sql.append(" LIMIT ").append(page.getFrom());
			sql.append(",").append(page.getPageSize());
		}
		return sql.toString();
	}
	
	private static String getWhereSQL (QueryBean queryBean) {
		StringBuffer sql = new StringBuffer(64);

		sql.append(" WHERE ");
		DateSpan span = queryBean.getDateSpan();
		String start = span.getStartDate();
		if (DateUtil.isOneday(span)) {
			sql.append("day_id").append("='").append(start).append("'");
		} else {
			String end = span.getEndDate();
			sql.append("day_id").append(">='").append(start).append("' AND ");
			sql.append("day_id").append("<='").append(end).append("'");
		}
		
		/** Add Filter Conditions **/
		Map<String, Map<String, String>> paramMap = queryBean.getParamMap();
		if (null != paramMap) {
			for (Entry<String, Map<String, String>> param : paramMap.entrySet()) {
				String key = param.getKey();
				for (Entry<String, String> filter : param.getValue().entrySet()) {
					RuleFilter ruleFilter = new RuleFilter(key, filter.getKey(), filter.getValue());
					sql.append(ruleFilter.toSQL());
				}
			}
		}
		
		return sql.toString();
	}
}
