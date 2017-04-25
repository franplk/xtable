package com.emar.download.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emar.download.service.DownLoadService;
import com.emar.util.RequestUtil;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.QueryBean;
import com.emar.xreport.service.DataService;
import com.emar.xreport.service.RuleDataService;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class DownLoadController extends BaseController {

	@Autowired
	private DownLoadService downService;

	@Autowired
	private DataService dataService;
	
	@Autowired
	private RuleDataService ruleDataService;

	/**
	 * Download Data
	 */
	@RequestMapping(value = "/download/{boardId}")
	public void downLoad(HttpServletRequest res, HttpServletResponse rep, @PathVariable String boardId)
			throws Exception {
		// Get Query Bean
		QueryBean queryBean = RequestUtil.getQueryParams(res);
		JTableData tableData = dataService.getData(boardId, queryBean);

		downService.downLoad(rep, tableData);
	}

	@RequestMapping(value = "/rule/download/{boardId}")
	public void ruleDown(HttpServletRequest res, HttpServletResponse rep, @PathVariable String boardId)
			throws Exception {
		// Get Query Bean
		QueryBean queryBean = RequestUtil.getQueryParams(res);
		JTableData tableData = ruleDataService.getData(boardId, queryBean);

		downService.downLoad(rep, tableData);
	}
}
