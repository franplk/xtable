package com.emar.exception;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.emar.log.LogService;
import com.emar.xreport.domain.WebResult;

import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author Franplk
 * @Function Exception Deploy
 * {@code} UTF-8
 */
public class CustomHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		// Add To Log
		String msg_web = null;
		String msg_log = exception.getMessage() + ExceptionUtil.getStackTrace(exception);
		if (exception instanceof AuthException) {
			msg_web = "权限不够";
			LogService.error(msg_log, LogService.TYPE_AUTH);
		} else if (exception instanceof BussinessException) {
			msg_web = "业务错误";
			LogService.error(msg_log, LogService.TYPE_MODEL);
		} else {
			msg_web = "系统错误，访问失败";
			LogService.error(msg_log, LogService.TYPE_ACCESS);
		}
		
		// Exception Deploy
		WebResult result = new WebResult(msg_web, false);
		try {
			if (isAjax(request)) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSONString(result));
				writer.flush();
				writer.close();
			} else {
				// If Not AJAX Request, Redirect To The Failed Page
				return new ModelAndView("/frame/error/failed", "result", result);
			}
		} catch (Exception e) {
			LogService.error(ExceptionUtil.getStackTrace(e), LogService.TYPE_ACCESS);
		}
		return null;
	}

	private boolean isAjax(HttpServletRequest request) {
		String acceptType = request.getHeader("accept");
		if (acceptType.indexOf("application/json") > -1) {
			return true;
		}
		String x_request = request.getHeader("X-Requested-With");
		if (x_request != null && x_request.indexOf("XMLHttpRequest") > -1) {
			return true;
		}
		return false;
	}
}