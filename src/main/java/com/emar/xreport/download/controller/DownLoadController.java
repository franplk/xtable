package com.emar.xreport.download.controller;

import com.emar.xreport.download.service.DownLoadService;
import com.emar.util.RequestUtil;
import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.web.domain.QueryBean;
import com.emar.xreport.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class DownLoadController extends BaseController {

	@Autowired
	private DownLoadService downService;

	@Autowired
	private DataService dataService;

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
}
