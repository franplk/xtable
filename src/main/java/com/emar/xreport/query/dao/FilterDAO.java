package com.emar.xreport.query.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.emar.xreport.query.domain.QueryCondition;

@Repository
public class FilterDAO {

	@Autowired
	private JdbcTemplate modelTemplate;

	public List<QueryCondition> getConTree(String boardId) {

		String sql = "SELECT col.name,col.label,col.dim,con.type_id,con.value_id"
				+ " FROM xv_dashboard_rel rel, xv_filter_condition con, xv_metadata_column col"
				+ " WHERE rel.dashboard_id=? AND con.filter_id=rel.filter_id AND col.id=con.col_id"
				+ " ORDER BY con.level,con.code";

		System.out.println("ConTree SQL:[" + sql + "]");

		final QueryCondition dimList = new QueryCondition("维度");
		final QueryCondition idxList = new QueryCondition("数据");

		try {
			modelTemplate.query(sql, new Object[]{boardId}, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					QueryCondition qFilter = new QueryCondition();
					qFilter.setId(rs.getString(1));
					qFilter.setText(rs.getString(2));
					int dim = rs.getInt(3);
					if (dim == 1) {
						dimList.addChild(qFilter);
					} else {
						idxList.addChild(qFilter);
					}
					qFilter.addAttribute("type", rs.getString(4));
					qFilter.addAttribute("value", rs.getString(5));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<QueryCondition> conList = new ArrayList<>();
		if (!dimList.empty()) {
			conList.add(dimList);
		}
		if (!idxList.empty()) {
			conList.add(idxList);
		}
		
		return conList;
	}

	public List<Map<String, Object>> getConType(String id) {
		
		String sql = "SELECT value,text FROM xv_filter_type WHERE id=?";
		
		return modelTemplate.queryForList(sql, new Object[]{id});
	}

	public List<Map<String, Object>> getConValue(String id) {
		
		String sql = "SELECT value,text FROM xv_filter_value WHERE id=? ORDER BY code";

		return modelTemplate.queryForList(sql, new Object[]{id});
	}
}
