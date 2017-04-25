package com.emar.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.menu.dao.MenuDao;
import com.emar.menu.domain.Menu;
import com.emar.sso.bean.User;
/**
 * 菜单Service
 */
@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	/**
	 * 获取用户的菜单组
	 */
	public List<Menu> getPageMenu(User user) {
		return menuDao.getPageMenu(user);
	}

	public Menu getTemplateMenu(User user) {
		return menuDao.getTemplateMenu(user);
	}
	
	public void addMenu() {
	}

	public List<Menu> getMenus() {
		return null;
	}
}
