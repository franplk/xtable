package com.emar.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.emar.exception.BussinessException;
import com.emar.model.domain.ConfigColumn;
import com.emar.model.domain.DataModel;

/**
 * 数据模型DAO
 * 
 * @author franplk
 */
@Repository
public class DataModelDao {

	@Autowired
	private JdbcTemplate modelTemplate;

	/**
	 * 根据Dash Board Id 获取 Data Model
	 * @throws BussinessException 
	 */
	public DataModel getModel(String dashId) {

		String sql = "SELECT dm.id,col.name,theme.id,theme.name,theme.date_key"
				+ " FROM xv_dashboard_rel dmr,xv_datamodel dm,xv_metadata_column col,xv_theme theme"
				+ " WHERE dmr.dashboard_id=? AND dm.id=dmr.data_model_id AND col.id=dm.ord_id AND dm.theme_id=theme.id";

		DataModel dataModel = null;
		try {
			dataModel = modelTemplate.queryForObject(sql, new Object[] { dashId }, new RowMapper<DataModel>() {
				@Override
				public DataModel mapRow(ResultSet rs, int idx) throws SQLException {

					DataModel dataModel = new DataModel();
					dataModel.setOrderName(rs.getString(2));
					dataModel.setThemeId(rs.getString(3));
					dataModel.setTheme(rs.getString(4));
					dataModel.setDateKey(rs.getString(5));

					return dataModel;
				}
			});
		} catch (Exception e) {
			throw new BussinessException("NO DataModel Found[DASHBOARD ID=" + dashId + "]", e);
		}
		
		// Get Dim Column And Indexes Column
		List<ConfigColumn> idxList = getColumnList(dataModel.getThemeId());
		for (ConfigColumn column : idxList) {
			dataModel.addColumn(column);
		}
		
		return dataModel;
	}

	/**
	 * Get Title Of Data Model
	 * @throws BussinessException 
	 */
	public List<ConfigColumn> getColumnList(String themeId) {

		String sql = "SELECT rel.flag,rel.sorting,rel.code,col.name,col.label,col.dim,col.datatype,col.formula,col.relyon,col.ex_value,col.mapname,col.maptitle"
				+ " FROM xv_theme_column rel,xv_metadata_column col"
				+ " WHERE rel.theme_id=? AND rel.col_id=col.id";

		List<ConfigColumn> idxList = null;
		try {
			idxList = modelTemplate.query(sql, new Object[] { themeId }, new RowMapper<ConfigColumn>() {

				@Override
				public ConfigColumn mapRow(ResultSet rs, int idx) throws SQLException {
					ConfigColumn column = new ConfigColumn();
					column.setFlag(rs.getInt(1));
					column.setSorting(rs.getInt(2));
					column.setCode(rs.getInt(3));
					column.setField(rs.getString(4));
					column.setTitle(rs.getString(5));
					column.setDim(rs.getInt(6));
					column.setDataType(rs.getString(7));
					column.setFormula(rs.getString(8));
					column.setRelyon(rs.getString(9));
					column.setExValue(rs.getString(10));
					column.setMapName(rs.getString(11));
					column.setMapTitle(rs.getString(12));
					return column;
				}
			});
		} catch (Exception e) {
			throw new BussinessException("Query Column Failed[THEME ID=" + themeId + "]", e);
		}
		return idxList;
	}
}