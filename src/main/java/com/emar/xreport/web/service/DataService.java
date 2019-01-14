package com.emar.xreport.web.service;

import com.emar.api.DataInter;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.model.domain.TableColumn;
import com.emar.xreport.model.service.BoardService;
import com.emar.xreport.query.domain.QueryBase;
import com.emar.xreport.query.domain.SQLQuery;
import com.emar.util.DateUtil;
import com.emar.xreport.web.BaseService;
import com.emar.xreport.web.dao.DataDao;
import com.emar.xreport.web.domain.DateSpan;
import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.web.domain.QueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data;
 */
@Service
@Component
public class DataService extends BaseService implements DataInter<JTableData> {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private DataDao dataDao;
	
	@Override
	public JTableData getData(String boardId, QueryBean queryBean) {
		
		// Get Dash board
		Dashboard dashboard = boardService.getBoard(boardId);
		DataModel dataModel = dashboard.getDataModel();
		
		// Get Date Span
		DateSpan dateSpan = queryBean.getDateSpan();
		if (DateUtil.isToday(dateSpan)) {
			//return getTodayData(dataModel, queryBean);
		}
		
		JTableData hisData = getHisData(dataModel, queryBean);
		
		return hisData;
	}

	/**
	 * Get Pagination Data For Specified Data Model
	 */
	private JTableData getHisData(DataModel dataModel, QueryBean queryBean) {
		
		JTableData tableData = null;
		List<TableColumn> tableColumn = null;
		
		// Query From Mysql
		SQLQuery mquery = getModelQuery(dataModel, queryBean);
		tableData = dataDao.getTableData(mquery);
		tableColumn = getTableColumn (mquery, false);

		tableData.setColumns(tableColumn);
		
		return tableData;
	}
	
	private List<TableColumn> getTableColumn (QueryBase query, boolean isEs) {
		// Columns
		List<ConfigColumn> columns = new ArrayList<>();
		columns.add(query.getDimColumn());
		columns.addAll(query.getIdxList());
		
		return getTableColumn(columns, isEs);
	}
}
