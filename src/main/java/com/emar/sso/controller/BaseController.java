package com.emar.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.emar.sso.bean.AuthMessage;
import com.emar.sso.service.SSOService;

public abstract class BaseController {
	
	/** 从请求中获取认证信息 */
	protected AuthMessage getAuthMsg (HttpServletRequest request) {
		
		AuthMessage message = new AuthMessage();
		
		message.setVersion(request.getParameter(SSOService.PROP_VERSION));
		message.setAppKey(request.getParameter(SSOService.PROP_APPKEY));
		message.setRequestId(request.getParameter(SSOService.PROP_REQID));
		message.setTimestamp(request.getParameter(SSOService.PROP_TIMESTAMP));
		message.setSignData(request.getParameter(SSOService.PROP_SIGNDATA));
		message.setUserData(request.getParameter(SSOService.PROP_USERDATA));
		message.setCallbackUrl(request.getParameter(SSOService.PROP_CALLBACKURL));
		
		return message;
	}
	
	/** 清除会话内容  */
	protected void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
	}
}
