package com.emar.xreport.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.emar.util.DateUtil;
import com.emar.util.StringUtil;
import com.emar.xreport.domain.DateSpan;

public abstract class BaseController {

	/**
	 * Date Span For This Query
	 */
	protected DateSpan getDateSpan (HttpServletRequest request, String type) {
		String start = request.getParameter("startDate");
		if (StringUtil.isEmpty(start)) {
			return DateUtil.getDateSpan(type);
		} else {
			String end = request.getParameter("endDate");
			String dType = DateUtil.getDateType(start, end);
			DateSpan span = new DateSpan(start, end);
			span.setType(dType);
			return span;
		}
	}

	/**
	 * Output Msg To Response
	 */
	protected void reply(HttpServletResponse response, Object datas) throws IOException {
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		if (datas instanceof String) {
			writer.write(String.valueOf(datas));
		} else {
			writer.write(JSON.toJSONString(datas));
		}
		writer.flush();
		writer.close();
	}
}