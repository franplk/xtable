package com.emar.xreport.web.controller;

import com.emar.util.StringUtil;
import com.emar.xreport.web.BaseController;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.model.service.BoardService;
import com.emar.xreport.web.domain.DateSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class PageController extends BaseController {

	@Autowired
	private BoardService boardService;

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
		modelMap.put("dirllMenus", board.getDrillMenus(drillCon));

		// Model Page
		String boardUrl = board.getModelURL();
		if (StringUtil.isNotEmpty(boardUrl)) {
			return new ModelAndView("/frame/model/" + boardUrl, modelMap);
		}
		return new ModelAndView("/frame/model/default", modelMap);
	}
}
