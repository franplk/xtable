package com.emar.xreport.thread;

import java.util.concurrent.CountDownLatch;

import com.emar.xreport.domain.JTableData;

public abstract class Job implements Runnable {

	protected CountDownLatch latch;

	protected JTableData tableData;
	
	public Job () {}
	
	public Job (CountDownLatch latch, JTableData tableData) {
		this.latch = latch;
		this.tableData = tableData;
	}

	public <T>T getDao(Class<? extends T> clazz) {
		return SpringBeanUtil.getBean(clazz);
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public JTableData getTableData() {
		return tableData;
	}

	public void setTableData(JTableData tableData) {
		this.tableData = tableData;
	}
}
