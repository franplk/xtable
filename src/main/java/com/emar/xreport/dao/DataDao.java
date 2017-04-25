package com.emar.xreport.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emar.cache.DataCacheService;
import com.emar.encypt.MD5Encrypt;
import com.emar.exception.BussinessException;
import com.emar.log.LogService;
import com.emar.query.ModelUtil;
import com.emar.query.domain.SQLQuery;
import com.emar.xreport.domain.JTableData;

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
		JTableData tableData = new JTableData();
		
		// Total Records And Summary
		Map<String, Object> sumAndCountData = getSumAndCount(query);
		long records = (long) sumAndCountData.get("records");
		tableData.setTotal(records);
		tableData.addSum(sumAndCountData);
		
		// DataList
		List<Map<String, Object>> datas = getRowList(query);
		tableData.setRows(datas);
		
		return tableData;
	}

	/**
	 * Get Sum And Count
	 */
	public Map<String, Object> getSumAndCount(SQLQuery query) {
		String sql = ModelUtil.getSumAndCountSQL(query);
		LogService.info("Sum And Count SQL:" + sql, LogService.TYPE_QUERY);
		
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			Map<String, Object> data = dataCacheService.getDataMap(sqlKey);
			if (null == data) {
				data = dataTemplate.queryForMap(sql);
				dataCacheService.put(sqlKey, data);
			}
			return data;
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[sql=" + sql + "]", e);
		}
	}

	/**
	 * Get Data
	 */
	public List<Map<String, Object>> getRowList(SQLQuery query) {
		String sql = ModelUtil.getListSql(query);
		LogService.info("List SQL:" + sql, LogService.TYPE_QUERY);
		
		try {
			String sqlKey = MD5Encrypt.encrypt(sql);
			List<Map<String, Object>> datas = dataCacheService.getDataList(sqlKey);
			if (null == datas) {
				datas = dataTemplate.queryForList(sql);
				dataFilter (query.getDimColumn(), datas);
				dataCacheService.put(sqlKey, datas);
			}
			return datas;
		} catch (Exception e) {
			throw new BussinessException("QueryFailed[sql=" + sql + "]", e);
		}
	}
}
