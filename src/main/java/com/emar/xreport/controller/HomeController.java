package com.emar.xreport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emar.menu.domain.Menu;
import com.emar.menu.service.MenuService;
import com.emar.sso.bean.User;
import com.emar.xreport.domain.EntryPage;
import com.emar.xreport.service.PageService;

@Controller
@RequestMapping(value = "/main")
public class HomeController {

	@Autowired
	private PageService pageService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/page")
	public ModelAndView mainPage(HttpServletRequest request) throws Exception {

		// Get Session User
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("auth_user");

		// Query Menu List For The Different User
		List<Menu> menuList = new ArrayList<>();
		// menuList.add(menuService.getTemplateMenu(user));
		menuList.addAll(menuService.getPageMenu(user));

		// Query The Entry Page For The Different User
		EntryPage entryPage = pageService.getEntrypage(user);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("entryPage", entryPage);
		modelMap.put("menuList", menuList);

		return new ModelAndView("/index", modelMap);
	}
}
