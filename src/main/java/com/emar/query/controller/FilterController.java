package com.emar.query.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emar.query.domain.QueryCondition;
import com.emar.query.service.FilterService;
import com.emar.xreport.controller.BaseController;

/**
 * @author franplk
 * 
 * Acquire Filter Condition
 */
@Controller
@RequestMapping(value = "/filter")
public class FilterController extends BaseController {

	@Autowired
	private FilterService conService;

	/**
	 * Query The Data Of The DashBoard
	 */
	@RequestMapping(value = "/con/{boardId}")
	public void getConTree(HttpServletRequest request, HttpServletResponse response, @PathVariable String boardId)
			throws Exception {

		List<QueryCondition> conTree = conService.getConTree(boardId);

		this.reply(response, conTree);
	}

	/**
	 * Query The Data Of The DashBoard
	 */
	@RequestMapping(value = "/type/{id}")
	public void getConType(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
			throws Exception {

		List<Map<String, Object>> conType = conService.getConType(id);

		this.reply(response, conType);
	}

	/**
	 * Query The Data Of The DashBoard
	 */
	@RequestMapping(value = "/value/{conId}")
	public void getConValue(HttpServletRequest request, HttpServletResponse response, @PathVariable String conId)
			throws Exception {

		List<Map<String, Object>> conValue = conService.getConValue(conId);

		this.reply(response, conValue);
	}
}
