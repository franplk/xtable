package com.emar.xreport.web.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emar.xreport.web.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emar.xreport.cache.DataCacheService;
import com.emar.xreport.util.crypt.MD5Encrypt;
import com.emar.xreport.exception.BusinessException;
import com.emar.xreport.util.log.LogUtil;
import com.emar.xreport.query.ModelUtil;
import com.emar.xreport.query.domain.SQLQuery;
import com.emar.xreport.web.domain.JTableData;

/**
 * @author franplk 2016.07.11
 */
@Repository
public class DataDao extends BaseDao {

	@Resource(name="dataTemplate")
	private JdbcTemplate dataTemplate;
	
	@Autowired
	private DataCacheService dataCacheService;
	
	/**
	 * Organize The Table Data
	 */
	public JTableData getTableData(SQLQuery query) {

		// Total Records And Summary
		Map<String, Object> sumAndCountData = getSumAndCount(query);
		long records = (long) sumAndCountData.get("records");

		// DataList
		List<Map<String, Object>> dataList = getRowList(query);

		JTableData tableData = new JTableData();
		tableData.setTotal(records);
		tableData.addSum(sumAndCountData);
		tableData.setRows(dataList);

		return tableData;
	}

	/**
	 * Get Sum And Count
	 */
	public Map<String, Object> getSumAndCount(SQLQuery query) {
		String sql = ModelUtil.getSumAndCountSQL(query);
		LogUtil.info("Sum And Count SQL:" + sql, LogUtil.TYPE_QUERY);
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			Map<String, Object> data = dataCacheService.getDataMap(sqlKey);
			if (null == data) {
				data = dataTemplate.queryForMap(sql);
				dataCacheService.put(sqlKey, data);
			}
			return data;
		} catch (Exception e) {
			throw new BusinessException("QueryFailed[sql=" + sql + "]");
		}
	}

	/**
	 * Get Data
	 */
	public List<Map<String, Object>> getRowList(SQLQuery query) {
		String sql = ModelUtil.getListSql(query);
		LogUtil.info("List SQL:" + sql, LogUtil.TYPE_QUERY);
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			List<Map<String, Object>> datas = dataCacheService.getDataList(sqlKey);
			if (null == datas) {
				datas = dataTemplate.queryForList(sql);
				dataCacheService.put(sqlKey, datas);
			}
			return datas;
		} catch (Exception e) {
			throw new BusinessException("QueryFailed[sql=" + sql + "]");
		}
	}
}
