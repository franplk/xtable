package com.emar.xreport.domain;

import com.emar.util.StringUtil;

public class DateSpan {

	private String type;
	private String startDate;
	private String endDate;

	public DateSpan(){}
	
	public DateSpan(String type){
		this.type = type;
	}
	
	public DateSpan(String startDate, String endDate){
		this.startDate = startDate;
		if (StringUtil.isEmpty(endDate)) {
			this.endDate = startDate;
		} else {
			this.endDate = endDate;
		}
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMonthStr () {
		String dateStr = startDate.replace("-", "");
		return dateStr.substring(0, 6);
	}
	
	public String getDateStr () {
		String dateStr = startDate.replace("-", "");
		if (startDate.equals(endDate)) {
			return dateStr;
		}
		return dateStr.substring(0, 6);
	}
	
	public boolean isEqual (DateSpan dateSpan) {
		if (this.isNull() || dateSpan.isNull()) {
			return false;
		}
		String s_date = dateSpan.getStartDate();
		String e_date = dateSpan.getEndDate();
		if (startDate.equals(s_date) && endDate.equals(e_date)) {
			return true;
		}
		return false;
	}
	
	public boolean isNull () {
		if (startDate == null || endDate == null) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString () {
		StringBuffer info = new StringBuffer ();
		
		info.append("[");
		info.append("StartDate=").append(startDate).append(";");
		info.append("EndDate=").append(endDate).append(";");
		info.append("]");
		
		return info.toString();
	}
}
