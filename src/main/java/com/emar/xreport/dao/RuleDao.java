package com.emar.xreport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emar.cache.DataCacheService;
import com.emar.encypt.MD5Encrypt;
import com.emar.exception.BussinessException;
import com.emar.log.LogService;
import com.emar.model.domain.DataModel;
import com.emar.query.RuleModelUtil;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.QueryBean;

/**
 * 数据模型DAO
 * 
 * @author franplk 2016.07.11
 */
@Repository
public class RuleDao {

	@Autowired
	private JdbcTemplate dataTemplate;
	
	@Autowired
	private DataCacheService dataCacheService;

	/**
	 * Organize The Table Data
	 */
	public JTableData getTableData(QueryBean query, DataModel dataModel) {
		JTableData tableData = new JTableData();
		
		String theme = dataModel.getTheme();
		
		// Total Records
		Map<String, Object> countData = getCount(query, theme);
		long records = (long) countData.get("records");
		tableData.setTotal(records);
		
		// DataList
		List<Map<String, Object>> datas = getRowList(query, theme);
		tableData.setRows(datas);
		
		return tableData;
	}

	/**
	 * Get Sum And Count
	 */
	public Map<String, Object> getCount(QueryBean queryBean, String theme) {
		String sql = RuleModelUtil.getCountSQL(queryBean, theme);
		LogService.info("Rule Count SQL:" + sql, LogService.TYPE_QUERY);
		
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			Map<String, Object> data = dataCacheService.getDataMap(sqlKey);
			if (null == data) {
				data = dataTemplate.queryForMap(sql);
				dataCacheService.put(sqlKey, data);
			}
			return data;
		} catch (Exception e) {
			throw new BussinessException("Rule Query Failed[sql=" + sql + "]", e);
		}
	}

	/**
	 * Get Data
	 */
	public List<Map<String, Object>> getRowList(QueryBean queryBean, String themeId) {
		String sql = RuleModelUtil.getListSql(queryBean, themeId);
		LogService.info("Rule List SQL:" + sql, LogService.TYPE_QUERY);
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			List<Map<String, Object>> data = dataCacheService.getDataList(sqlKey);
			if (null == data) {
				data = dataTemplate.queryForList(sql);
				dataCacheService.put(sqlKey, data);
			}
			return data;
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[sql=" + sql + "]");
		}
	}
}
