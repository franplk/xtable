package com.emar.xreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.xreport.dao.RuleDao;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.QueryBean;
import com.emar.api.DataInter;
import com.emar.model.domain.ConfigColumn;
import com.emar.model.domain.Dashboard;
import com.emar.model.domain.DataModel;
import com.emar.model.service.BoardService;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data; Download Data
 */
@Service
public class RuleDataService extends BaseService implements DataInter<JTableData> {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private RuleDao ruleDao;

	/**
	 * Get Pagination Data For Specified Data Model
	 */
	@Override
	public JTableData getData(String boardId, QueryBean queryBean) {
		// Get Dash board
		Dashboard dashboard = boardService.getBoard(boardId);
		DataModel dataModel = dashboard.getDataModel();
		
		/********* Query Data ********/
		JTableData tableData = ruleDao.getTableData(queryBean, dataModel);
		
		List<ConfigColumn> columnList = dataModel.getIdxList();
		tableData.setColumns(getTableColumn (columnList, false));
		
		return tableData;
	}
}
