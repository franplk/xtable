package com.emar.xreport.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.emar.xreport.exception.BusinessException;
import com.emar.xreport.menu.domain.DrillMenu;
import com.emar.xreport.model.domain.Dashboard;

/**
 * 分析模型DAO
 */
@Repository
public class DashboardDao {

	@Autowired
	private JdbcTemplate modelTemplate;

	/**
	 * Get Dash Board By ID
	 * @throws BusinessException
	 */
	public Dashboard getBoard(final String boardId) {
		Dashboard dashboard = null;
		try {
			String sql = "SELECT dash.name,dash.board,dash.datespan,meta.name FROM xv_dashboard dash,xv_metadata_column meta WHERE dash.id=? AND meta.id=dash.dim_id";
			dashboard = modelTemplate.queryForObject(sql, new Object[] { boardId }, new RowMapper<Dashboard>() {
				@Override
				public Dashboard mapRow(ResultSet rs, int idx) throws SQLException {
					Dashboard dashboard = new Dashboard(boardId);
					dashboard.setName(rs.getString(1));
					dashboard.setModelURL(rs.getString(2));
					dashboard.setDateType(rs.getString(3));
					dashboard.setDimName(rs.getString(4));
					return dashboard;
				}
			});
		} catch (Exception e) {
			throw new BusinessException("DASHBOARD[ID=" + boardId + "]NO DashBoard Found");
		}
		List<DrillMenu> drillMenus = getDrillMenus(boardId);
		dashboard.setDrillMenuList(drillMenus);

		return dashboard;
	}

	public List<DrillMenu> getDrillMenus(String boardId) {
		List<DrillMenu> menus = null;
		try {
			String sql = "SELECT drill_id,drill_name,drill_label,drill_title FROM xv_dashboard_drill WHERE board_id=?";
			menus = modelTemplate.query(sql, new Object[]{boardId}, new RowMapper<DrillMenu>(){
				@Override
				public DrillMenu mapRow(ResultSet rs, int idx) throws SQLException {
					DrillMenu menu = new DrillMenu();
					menu.setUrl(rs.getString(1));
					menu.setName(rs.getString(2));
					menu.setLabel(rs.getString(3));
					menu.setTitle(rs.getString(4));
					return menu;
				}
			});
		} catch (Exception e) {
			throw new BusinessException("DASHBOARD[ID=" + boardId + "]Query DrillMenu Failed");
		}
		if (null == menus) {
			menus = new ArrayList<>(0);
		}
		return menus;
	}
}
