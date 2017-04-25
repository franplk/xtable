package com.emar.xreport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.model.domain.DataModel;
import com.emar.xreport.dao.RuleDao;
import com.emar.xreport.domain.JTableData;
import com.emar.xreport.domain.PageData;
import com.emar.xreport.domain.QueryBean;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data; Download Data
 */
@Service
public class RuleService {

	@Autowired
	private RuleDao ruleDao;
	
	/**
	 * Get Pagination Data For Specified Data Model
	 */
	public PageData getData(QueryBean queryBean, DataModel dataModel) {
		/********* Query Data ********/
		JTableData tableData = ruleDao.getTableData(queryBean, dataModel);
		PageData pageData = new PageData();
		pageData.setTableData(tableData);
		return pageData;
	}
}
