package com.emar.xreport.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.emar.query.domain.ESQuery;
import com.emar.xreport.dao.ESDataDao;
import com.emar.xreport.domain.JTableData;

public class RealtimeJob extends Job {

	private ESQuery query;
	
	public RealtimeJob() {
	}

	public RealtimeJob(CountDownLatch latch, JTableData tableData, final ESQuery query) {
		super(latch, tableData);
		this.query = query;
	}

	@Override
	public void run() {
		
		ESDataDao dao = getDao(ESDataDao.class);
		
		List<Map<String, Object>> dataList = dao.getDataList(query);
		
		tableData.setRows(dataList);
		
		latch.countDown();
	}

	public ESQuery getQuery() {
		return query;
	}

	public void setQuery(ESQuery query) {
		this.query = query;
	}
}
