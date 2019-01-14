package com.emar.xreport.query.controller;

import com.emar.xreport.query.domain.QueryCondition;
import com.emar.xreport.query.service.FilterService;
import com.emar.xreport.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
	@ResponseBody
	@RequestMapping(value = "/con/{boardId}")
	public List<QueryCondition> getConTree(@PathVariable String boardId) {
		return conService.getConTree(boardId);
	}

	/**
	 * Query The Data Of The DashBoard
	 */
	@ResponseBody
	@RequestMapping(value = "/type/{id}")
	public List<Map<String, Object>> getConType(@PathVariable String id) {
		return conService.getConType(id);
	}

	/**
	 * Query The Data Of The DashBoard
	 */
	@ResponseBody
	@RequestMapping(value = "/value/{conId}")
	public List<Map<String, Object>> getConValue(@PathVariable String conId) {
		return conService.getConValue(conId);
	}
}
