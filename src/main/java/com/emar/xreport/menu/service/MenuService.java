package com.emar.xreport.menu.service;

import com.emar.xreport.menu.dao.MenuDao;
import com.emar.xreport.menu.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
	public List<Menu> getPageMenu() {
		return menuDao.getPageMenu();
	}
}
