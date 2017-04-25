package com.emar.xreport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emar.log.LogService;
import com.emar.util.RequestUtil;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.PageData;
import com.emar.xreport.domain.QueryBean;
import com.emar.xreport.domain.WebResult;
import com.emar.xreport.service.RuleDataService;

/**
 * @author Franplk
 * Page Model Controller
 */
@Controller
public class RuleDataController extends BaseController {

	@Autowired
	private RuleDataService dataService;

	/**
	 * Query The Data Of The DashBoard
	 */
	@RequestMapping(value = "/rule/data/{boardId}")
	public void ruleData(HttpServletRequest res, HttpServletResponse rep, @PathVariable String boardId)
			throws Exception {
		QueryBean queryBean = RequestUtil.getQueryParams(res);
		LogService.info("QueryParams=" + queryBean, LogService.TYPE_QUERY);
		
		PageData pageData = new PageData();
		JTableData tableData = dataService.getData(boardId, queryBean);
		pageData.setTableData(tableData);
		WebResult result = new WebResult(pageData);
		this.reply(rep, result);
	}
}
