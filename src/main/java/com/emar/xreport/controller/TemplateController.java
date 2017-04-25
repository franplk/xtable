package com.emar.xreport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emar.model.domain.Template;
import com.emar.model.service.TemplateService;
import com.emar.sso.bean.User;
import com.emar.xreport.domain.WebResult;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class TemplateController extends BaseController {

	@Autowired
	private TemplateService templateService;

	@RequestMapping(value = "/index/save")
	public void saveIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Get Session User
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("auth_user");

		Template template = getTemplate(request);

		templateService.saveIndex(user, template);
		
		WebResult webResult = new WebResult();
		webResult.setSuccess(true);

		this.reply(response, webResult);
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
