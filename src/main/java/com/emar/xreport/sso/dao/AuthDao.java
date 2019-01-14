package com.emar.xreport.sso.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emar.exception.AuthException;
import com.emar.xreport.model.dao.TemplateDao;
import com.emar.xreport.sso.bean.AuthResult;
import com.emar.xreport.sso.bean.User;

@Repository
public class AuthDao {

	@Autowired
	private TemplateDao templateDao;
	
	@Resource(name="dataTemplate")
	private JdbcTemplate dataTemplate;

	/**
	 * Get User Information And Some Authority Info
	 */
	public User getUserInfo(AuthResult query) {

		Long userId = query.getUserId();

		User user = new User(userId);
		
		// Project Authority
		List<String> projects = getAuthProject(userId);
		user.setProjects(projects);
		
		// Custom Indexes
		Map<String, String> menuIndexMap = getMenuIndex(userId);
		user.setMenuIndexs(menuIndexMap);
		
		return user;
	}

	/**
	 * Get User Projects
	 */
	public List<String> getAuthProject(Long userId) {
		String sql = "SELECT DISTINCT project_id FROM emarbox_user_project WHERE user_id=" + userId;
		try {
			return dataTemplate.queryForList(sql, String.class);
		} catch (Exception e) {
			throw new AuthException("登录失败:您使用的用户不能访问当前系统");
		}
	}
	
	/**
	 * Get User Customize Index
	 */
	public Map<String, String> getMenuIndex (Long userId) {
		return templateDao.getMenuIndex(userId);
	}
}
