package com.emar.xreport.menu.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public abstract class BaseController {

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