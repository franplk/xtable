package com.emar.xreport.web.service;

import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.Dashboard;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.model.domain.TableColumn;
import com.emar.xreport.query.domain.QueryBase;
import com.emar.xreport.query.domain.SQLQuery;
import com.emar.xreport.web.BaseService;
import com.emar.xreport.web.dao.DataDao;
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
public class DataService extends BaseService {

	@Autowired
	private DataDao dataDao;

	public JTableData getData(Dashboard dashboard, QueryBean queryBean) {
		// Query Data
		DataModel dataModel = dashboard.getDataModel();
		SQLQuery mquery = getModelQuery(dataModel, queryBean);
		JTableData tableData = dataDao.getTableData(mquery);

		// Get Columns
		List<TableColumn> tableColumn = getTableColumn (mquery);
		tableData.setColumns(tableColumn);
		
		return tableData;
	}
	
	private List<TableColumn> getTableColumn (QueryBase query) {
		// Columns
		List<ConfigColumn> columns = new ArrayList<>();
		columns.add(query.getDimColumn());
		columns.addAll(query.getIdxList());
		
		return getTableColumn(columns);
	}
}
