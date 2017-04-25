package com.emar.sso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emar.exception.AuthException;
import com.emar.log.LogService;
import com.emar.sso.bean.AuthMessage;
import com.emar.sso.bean.AuthResult;
import com.emar.sso.bean.User;
import com.emar.sso.service.AnthService;
import com.emar.sso.service.SSOService;

/**
 * User Login
 */
@Controller
@RequestMapping("/sso")
public class SSOController extends BaseController {

	@Autowired
	private SSOService ssoService;

	@Autowired
	private AnthService anthService;

	@RequestMapping(value = "/auth")
	public ModelAndView authPage () {
		
		String authLoginUrl = ssoService.getSSOUrl(true);
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("authLoginUrl", authLoginUrl);
		
		return new ModelAndView("/frame/user/authTransit", modelMap);
	}
	
	/**
	 * 用户登录控制器
	 */
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		AuthMessage message = getAuthMsg(request);

		AuthResult result = ssoService.checkUserData(message);
		if (result.getSuccess()) {
			User user = anthService.getUserInfo(result);
			if (null == user || null == user.getUserId()) {
				throw new AuthException("登录失败:您使用的用户不能访问当前系统");
			} else {
				onLoginSuccess(user, request, response);
			}
		} else {
			throw new AuthException("登录失败:" + result.getMessage());
		}
	}

	/**
	 * 用户注销
	 * @throws AuthException 
	 */
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws AuthException {
		LogService.info("User Logout, Remove session", LogService.TYPE_AUTH);
		clearSession(request);
		try {
			String logoutURL = ssoService.getSSOUrl(false);
			request.setAttribute("failed_msg", "您已成功退出");
			response.sendRedirect(logoutURL);
		} catch (Exception e) {
			throw new AuthException("用户注销失败:" + e.getMessage());
		}
	}

	/**
	 * 用户登录成功
	 */
	public void onLoginSuccess(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogService.info("登录成功: " + user.getLoginName(), LogService.TYPE_AUTH);
		
		// Set User, Projects Info To Session
		HttpSession session = request.getSession();
		session.setAttribute("auth_user", user);
		
		// Redirect To Main Page
		String url = request.getParameter(SSOService.PROP_CALLBACKURL);

		response.sendRedirect(url);
	}
}
