package com.emar.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.emar.query.domain.Pagination;
import com.emar.xreport.domain.DateSpan;
import com.emar.xreport.domain.QueryBean;

public class RequestUtil {

	/**
	 * Get Query Condition And Pagination
	 */
	public static QueryBean getQueryParams(HttpServletRequest request) throws Exception {
		QueryBean qParam = new QueryBean();

		// Date Span
		qParam.setDateSpan(getDateSpan (request));
		
		// Order
		qParam.setOrderBy(request.getParameter("orderBy"));
		qParam.setOrderType(request.getParameter("orderType"));
		
		// idxList
		String idxStr = request.getParameter("queryIdx");
		if (StringUtil.isNotEmpty(idxStr)) {
			List<String> idxList = StringUtil.split2List(idxStr, ";");
			qParam.setIdxList(idxList);
		}

		// Query Condition
		String queryStr = request.getParameter("queryStr");
		if (StringUtil.isNotEmpty(queryStr)) {
			qParam.setParamMap(getParamMap(queryStr, ";"));
		}

		// Pagination
		String type = request.getParameter("downType");
		if (!"all".equals(type)) {
			Pagination page = getPagination(request);
			qParam.setPage(page);
		}
		return qParam;
	}
	
	public static DateSpan getDateSpan (HttpServletRequest request) {
		// Date Span
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		return new DateSpan(start, end);
	}
	
	/**
	 * Get Query Parameters
	 */
	public static Map<String, Map<String,String>> getParamMap(String queryStr, String split) {
		Map<String, Map<String,String>> paramMap = new HashMap<>();
		
		String[] queryArr = queryStr.split(split);
		for (String paramItem : queryArr) {
			String[] paramArr = paramItem.split(":");
			if (paramArr.length != 3) {
				continue;
			}
			String key = paramArr[0];
			String sign = paramArr[1];
			String value = paramArr[2];
			
			if (paramMap.containsKey(key)) {
				Map<String,String> filter = paramMap.get(key);
				if (filter.containsKey(sign)) {
					if (sign.matches("(eq|inc)")) {
						String con = filter.get(sign);
						if (!con.contains(value)) {
							filter.put(sign, con + "," + value);
						}
					}
				} else {
					filter.put(sign, value);
				}
			} else {
				Map<String,String> filter = new HashMap<>();
				filter.put(sign, value);
				paramMap.put(key,filter);
			}
		}
		
		return paramMap;
	}

	/**
	 * Get Pagination Parameters
	 */
	public static Pagination getPagination(HttpServletRequest request) throws Exception {
		Pagination page = new Pagination();
		String pageNumber = request.getParameter("pageNumber");
		String pageSzie = request.getParameter("pageSize");
		try {
			if (pageNumber != null) {
				page.setCurrPage(Integer.parseInt(pageNumber));
			}
			if (pageSzie != null) {
				page.setPageSize(Integer.parseInt(pageSzie));
			}
		} catch (Exception e) {
			throw new Exception("Page Param Is Not Invalid");
		}
		return page;
	}
}
