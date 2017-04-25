package com.emar.xreport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emar.model.domain.ConfigColumn;
import com.emar.model.domain.Dashboard;
import com.emar.model.domain.DataModel;
import com.emar.model.service.BoardService;
import com.emar.sso.bean.User;
import com.emar.util.StringUtil;
import com.emar.xreport.domain.DateSpan;

/**
 * @author Franplk Page Model Controller
 */
@Controller
public class PageController extends BaseController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/board/{boardId}")
	public ModelAndView getModelPage(HttpServletRequest request, @PathVariable String boardId) throws Exception {

		Dashboard board = boardService.getBoard(boardId);
		DataModel dataModel = board.getDataModel();
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("boardId", boardId);// Model ID
		modelMap.put("dimName", dataModel.getDimName());// Dimension Name
		modelMap.put("orderBy", dataModel.getOrderName());// Order Field Name
		
		// Get Session User Default Index
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("auth_user");
		List<ConfigColumn> idxList = getUserIndex(user, dataModel, boardId);
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
		} else {
			return new ModelAndView("/frame/model/default", modelMap);
		}
	}
	
	/**
	 * Rule Analyze
	 */
	@RequestMapping(value = "rule/{boardId}")
	public ModelAndView getRulePage(HttpServletRequest request, @PathVariable String boardId) throws Exception {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("boardId", boardId);
		
		Dashboard board = boardService.getBoard(boardId);
		DataModel dataModel = board.getDataModel();

		// DateSpan
		DateSpan dateSpan = getDateSpan(request, "prev");
		modelMap.put("dateSpan", dateSpan);
		
		// Column List
		modelMap.put("columnList", dataModel.getIdxList());
		
		// Model Page
		return new ModelAndView("/frame/model/rule", modelMap);
	}
	
	private List<ConfigColumn> getUserIndex (User user, DataModel dataModel, String boardId) {
		
		// User Default Index
		List<ConfigColumn> idxList = dataModel.getIdxList();
		String idxes = user.getMenuIndex(boardId);
		if (null != idxes) {
			for (ConfigColumn column : idxList) {
				column.setFlag(0);
				String field = column.getField();
				if (idxes.contains(field)) {
					column.setFlag(1);
				}
			}
		}
		
		return idxList;
	}
}
