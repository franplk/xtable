package com.emar.xreport.web.controller;

import com.emar.xreport.util.log.LogUtil;
import com.emar.xreport.util.RequestUtil;
import com.emar.xreport.util.StringUtil;
import com.emar.xreport.web.service.DownService;
import com.emar.xreport.web.BaseController;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.web.service.BoardService;
import com.emar.xreport.web.WebResult;
import com.emar.xreport.web.domain.DateSpan;
import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.web.domain.PageData;
import com.emar.xreport.web.domain.QueryBean;
import com.emar.xreport.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class BoardController extends BaseController {

	@Autowired
	private BoardService boardService;

    @Autowired
    private DownService downService;

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/board/{boardId}")
	public ModelAndView getModelPage(HttpServletRequest request, @PathVariable String boardId) {

		Dashboard board = boardService.getBoard(boardId);
		DataModel dataModel = board.getDataModel();
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("boardId", boardId);// Model ID
		modelMap.put("dimName", dataModel.getDimName());// Dimension Name
		modelMap.put("orderBy", dataModel.getOrderName());// Order Field Name
		
		// Get Session User Default Index
		List<ConfigColumn> idxList = getUserIndex(dataModel, boardId);
		modelMap.put("idxList", idxList);
		
		// DateSpan
		DateSpan dateSpan = getDateSpan(request, board.getDateType());
		modelMap.put("dateSpan", dateSpan);

		// Drill Condition
		String drillCon = request.getParameter("drillCon");
		if (StringUtil.isNotEmpty(drillCon)) {
			modelMap.put("drillCon", drillCon);
		}
		modelMap.put("drillMenus", board.getDrillMenus(drillCon));

		// Model Page
		String boardUrl = board.getModelURL();
		if (StringUtil.isNotEmpty(boardUrl)) {
			return new ModelAndView("/frame/model/" + boardUrl, modelMap);
		}
		return new ModelAndView("/frame/model/default", modelMap);
	}

	/**
	 * Query The Data Of The DashBoard
	 */
	@ResponseBody
	@RequestMapping(value = "/data/{boardId}")
	public WebResult data(HttpServletRequest res, @PathVariable String boardId) {
		// Request Parameter Resolve
		QueryBean queryBean = RequestUtil.getQueryParams(res);
		LogUtil.info("QueryParams=" + queryBean, LogUtil.TYPE_QUERY);

		// Assemble Page Data
        Dashboard dashboard = boardService.getBoard(boardId);
		JTableData tableData = dataService.getData(dashboard, queryBean);
		PageData pageData = new PageData();
		pageData.setTableData(tableData);

		// Return Web Query Result
		return WebResult.success(pageData);
	}

    /**
     * Download Data
     */
    @RequestMapping(value = "/download/{boardId}")
    public void downLoad(HttpServletRequest res, HttpServletResponse rep, @PathVariable String boardId) {
        // Get Query Bean
        QueryBean queryBean = RequestUtil.getQueryParams(res);
        Dashboard dashboard = boardService.getBoard(boardId);
        JTableData tableData = dataService.getData(dashboard, queryBean);

        downService.downLoad(rep, tableData);
    }
}
