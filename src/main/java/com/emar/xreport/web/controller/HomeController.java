package com.emar.xreport.web.controller;

import com.emar.xreport.menu.domain.Menu;
import com.emar.xreport.menu.service.MenuService;
import com.emar.xreport.web.domain.EntryPage;
import com.emar.xreport.web.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/main")
public class HomeController {

	@Autowired
	private PageService pageService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/page")
	public ModelAndView mainPage(ModelMap modelMap) {
		// Query Menu List For The Different User
		List<Menu> menuList = menuService.getPageMenu();

		// Query The Entry Page For The Different User
		EntryPage entryPage = pageService.getEntryPage();

		modelMap.put("entryPage", entryPage);
		modelMap.put("menuList", menuList);

		return new ModelAndView("/index", modelMap);
	}

	@RequestMapping(value = "/welcome")
	public ModelAndView welcome() {
		return new ModelAndView("/frame/hello");
	}
}
