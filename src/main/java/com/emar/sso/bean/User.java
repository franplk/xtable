package com.emar.sso.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emar.model.domain.Template;

/**
 * 用户对象
 */
public class User implements Serializable {

	private static final long serialVersionUID = -5320257834723733881L;

	private Long userId;
	private String userType;
	private String loginName;
	private List<String> projects;
	private Map<String, String> menuIndexs;
	private Map<String, Template> templates;

	public User() {}
	
	public User(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public Map<String, Template> getTemplates() {
		return templates;
	}

	public void setTemplates(Map<String, Template> templates) {
		this.templates = templates;
	}

	public Map<String, String> getMenuIndexs() {
		return menuIndexs;
	}

	public void setMenuIndexs(Map<String, String> menuIndexs) {
		this.menuIndexs = menuIndexs;
	}

	public Template getTemplate(String tId) {
		return templates.get(tId);
	}

	public String getMenuIndex(String boardId) {
		if (null == menuIndexs) {
			return null;
		}
		return menuIndexs.get(boardId);
	}

	public void putMenuIndex(String board, String idxes) {
		if (null == menuIndexs) {
			menuIndexs = new HashMap<String, String>();
		}
		menuIndexs.put(board, idxes);
	}
}
