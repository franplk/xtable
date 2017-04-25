package com.emar.xreport.thread;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.emar.query.domain.SQLQuery;
import com.emar.xreport.dao.DataDao;
import com.emar.xreport.domain.JTableData;

public class SumsJob extends Job {

	private SQLQuery query;
	
	public SumsJob() {
	}

	public SumsJob(CountDownLatch latch, JTableData tableData, final SQLQuery query) {
		super(latch, tableData);
		this.query = query;
	}

	@Override
	public void run() {
		
		DataDao dao = getDao(DataDao.class);
		
		Map<String, Object> sumData = dao.getSumAndCount(query);
		
		long records = (long) sumData.get("records");
		tableData.setTotal(records);
		tableData.addSum(sumData);
		
		latch.countDown();
	}
	
	public SQLQuery getQuery() {
		return query;
	}

	public void setQuery(SQLQuery query) {
		this.query = query;
	}
}
