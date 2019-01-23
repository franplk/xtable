package com.emar.xreport.web.service;

import com.emar.xreport.cache.ModelCacheService;
import com.emar.xreport.exception.BusinessException;
import com.emar.xreport.model.dao.DashboardDao;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.model.service.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * @throws BusinessException
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
}