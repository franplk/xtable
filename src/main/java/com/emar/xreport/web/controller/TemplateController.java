package com.emar.xreport.web.controller;

import com.emar.xreport.web.BaseController;
import com.emar.xreport.model.domain.Template;
import com.emar.xreport.model.service.TemplateService;
import com.emar.xreport.sso.bean.User;
import com.emar.xreport.web.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class TemplateController extends BaseController {

	@Autowired
	private TemplateService templateService;

	@ResponseBody
	@RequestMapping(value = "/index/save")
	public WebResult saveIndex(HttpServletRequest request) {
		// Get Session User
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("auth_user");

		Template template = getTemplate(request);
		templateService.saveIndex(user, template);
		
		return WebResult.success();
	}

	private Template getTemplate(HttpServletRequest request) {
		Template template = new Template();
		
		String boardId = request.getParameter("boardId");
		template.setBoardId(boardId);
		
		String idxes = request.getParameter("idxes");
		template.setIdxes(idxes);
		
		return template;
	}
}
