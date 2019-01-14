package com.emar.xreport.sso.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Franplk Login Authority Filter
 */
public class AuthFilter implements HandlerInterceptor {

	private boolean verify;

	/**
	 * 进入Controller处理之前进行是否登陆过的判断
	 **/
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		if (!verify) {
			return true;
		}
		
		// Authority
		HttpSession session = request.getSession();
		Object user = session.getAttribute("auth_user");
		if (user == null) {
			response.sendRedirect("/sso/auth");
			return false;
		}
		return true;
	}

	/**
	 * Controlller 处理完成返回Response之前处理
	 **/
	public void postHandle(HttpServletRequest res, HttpServletResponse rep, Object handler, ModelAndView view)
			throws Exception {
		rep.setCharacterEncoding("utf-8");
	}

	/**
	 * 响应Response之后，资源的释放等在此处理
	 **/
	public void afterCompletion(HttpServletRequest res, HttpServletResponse rep, Object handler, Exception exp)
			throws Exception {
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}
}
