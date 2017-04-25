package com.emar.xreport.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.emar.query.domain.SQLQuery;
import com.emar.xreport.dao.DataDao;
import com.emar.xreport.domain.JTableData;

public class DataJob extends Job {

	private SQLQuery query;
	
	public DataJob() {
	}

	public DataJob(CountDownLatch latch, JTableData tableData, final SQLQuery query) {
		super(latch, tableData);
		this.query = query;
	}

	@Override
	public void run() {
		
		DataDao dao = getDao(DataDao.class);
		
		List<Map<String, Object>> dataList = dao.getRowList(query);
		
		tableData.setRows(dataList);
		
		latch.countDown();
	}
	
	public SQLQuery getQuery() {
		return query;
	}

	public void setQuery(SQLQuery query) {
		this.query = query;
	}
}
