package com.emar.xreport.web.controller;

import com.emar.log.LogService;
import com.emar.util.RequestUtil;
import com.emar.xreport.web.BaseController;
import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.web.domain.PageData;
import com.emar.xreport.web.domain.QueryBean;
import com.emar.xreport.web.WebResult;
import com.emar.xreport.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Franplk
 * Page Model Controller
 */
@Controller
public class DataController extends BaseController {

	@Autowired
	private DataService dataService;

	/**
	 * Query The Data Of The DashBoard
	 */
	@ResponseBody
	@RequestMapping(value = "/data/{boardId}")
	public WebResult data(HttpServletRequest res, @PathVariable String boardId)
			throws Exception {
		
		// Request Parameter Resolve
		QueryBean queryBean = RequestUtil.getQueryParams(res);
		LogService.info("QueryParams=" + queryBean, LogService.TYPE_QUERY);
		
		// Assemble Page Data
		PageData pageData = new PageData();
		
		// Get Table Data
		JTableData tableData = dataService.getData(boardId, queryBean);
		pageData.setTableData(tableData);
		
		// Return Web Query Result
		return WebResult.success(pageData);
	}
}
