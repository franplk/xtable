package com.emar.xreport.web;

import com.emar.xreport.util.DateUtil;
import com.emar.xreport.util.StringUtil;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.xreport.model.domain.DataModel;
import com.emar.xreport.web.domain.DateSpan;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class BaseController {

	/**
	 * Date Span For This Query
	 */
	protected DateSpan getDateSpan (HttpServletRequest request, String type) {
		String start = request.getParameter("startDate");
		if (StringUtil.isEmpty(start)) {
			return DateUtil.getDateSpan(type);
		} else {
			String end = request.getParameter("endDate");
			String dType = DateUtil.getDateType(start, end);
			DateSpan span = new DateSpan(start, end);
			span.setType(dType);
			return span;
		}
	}

	protected List<ConfigColumn> getUserIndex (DataModel dataModel, String boardId) {
		// User Default Index
		List<ConfigColumn> idxList = dataModel.getIdxList();
//		String idxes = user.getMenuIndex(boardId);
//		if (null != idxes) {
//			for (ConfigColumn column : idxList) {
//				column.setFlag(0);
//				String field = column.getField();
//				if (idxes.contains(field)) {
//					column.setFlag(1);
//				}
//			}
//		}
		return idxList;
	}
}