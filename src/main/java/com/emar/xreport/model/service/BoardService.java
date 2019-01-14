package com.emar.xreport.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.cache.ModelCacheService;
import com.emar.exception.BussinessException;
import com.emar.xreport.menu.domain.DrillMenu;
import com.emar.xreport.model.dao.DashboardDao;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;

/**
 * @author Franplk
 * 分析模型Service
 */
@Service
public class BoardService {

	@Autowired
	private DashboardDao boardDao;

	@Autowired
	private ModelCacheService modelCacheService;
	
	@Autowired
	private DataModelService dataModelService;

	/**
	 * Get Dashboard Specified By Id
	 * @throws BussinessException 
	 */
	public Dashboard getBoard(String boardId) {
		Dashboard board = modelCacheService.getBoard(boardId);
		if (board == null) {
			board = boardDao.getBoard(boardId);
			// Add Data Model
			DataModel dataModel = dataModelService.getDataModel(boardId);
			board.setDataModel(dataModel);
			modelCacheService.put(boardId, board);
		}
		return board;
	}
	
	/**
	 * Get Drill Menus Specified By Id
	 * @throws BussinessException 
	 */
	public List<DrillMenu> getDrillMenus(String boardId) {
		Dashboard board = modelCacheService.getBoard(boardId);
		if (board != null) {
			return board.getDrillMenuList();
		}
		return boardDao.getDrillMenus(boardId);
	}
}