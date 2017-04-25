package com.emar.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.emar.sso.bean.User;
/**
 * 表单组件DAO
 * @author jiaqiang
 * 2015.01.20
 */
@Repository
public class ChartDao {

	@Autowired
	private JdbcTemplate modelTemplate;
	
	public void getChart(Integer dashId) {
		String sql = "";
		modelTemplate.query(sql, new Object[]{dashId}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet arg0, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
}
