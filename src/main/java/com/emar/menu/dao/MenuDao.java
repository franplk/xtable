package com.emar.menu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.emar.menu.domain.Menu;
import com.emar.model.domain.Template;
import com.emar.sso.bean.User;

/**
 * 菜单DAO
 */
@Repository
public class MenuDao {

	@Autowired
	private JdbcTemplate modelTemplate;

	/**
	 * 根据用户id查询该用户对应的菜单项
	 */
	public List<Menu> getPageMenu(User user) {

		String sql = "select name,url,subject from xv_menu where valid=1 order by subject,level,code";

		final List<Menu> menuList = new ArrayList<Menu>();
		try {
			modelTemplate.query(sql, new RowCallbackHandler() {
				Menu parentMenu = null; int parentSign = -1;

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					int currSign = rs.getInt("subject");

					Menu menu = new Menu();
					menu.setName(rs.getString("name"));
					menu.setUrl(rs.getString("url"));

					if (currSign != parentSign) { // New Parent Menu
						menuList.add(menu);
						parentMenu = menu;
						parentSign = currSign;
					} else {
						parentMenu.addSubMenu(menu);
					}
				}
			});
		} catch (Exception e) {}
		return menuList;
	}

	public Menu getTemplateMenu(User user) {
		List<Menu> tepMenus = new ArrayList<Menu>();

		Map<String, Template> templates = user.getTemplates();
		if (templates == null || templates.isEmpty()) {
			tepMenus.add(new Menu("添加模版", "/template/add"));
		} else {
			for (Template t : templates.values()) {
				Menu menu = new Menu(t.getTitle());
				menu.setUrl("/template/" + t.getTid());
				tepMenus.add(menu);
			}
		}

		return new Menu("我的模版", tepMenus);
	}
}
