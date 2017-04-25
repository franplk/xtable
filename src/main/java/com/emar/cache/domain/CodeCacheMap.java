package com.emar.cache.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeCacheMap {

	public static String TYPE_TRADE = "trade_type";
	public static String TYPE_CAMP = "campaign_id";
	public static String TYPE_CHANNEL = "channel_id";
	public static String TYPE_PROJECT = "project_id";
	public static String TYPE_INDUSTRY = "category_id";

	private static Map<String, Object> tradeMap = new HashMap<String, Object>();;
	private static Map<String, Object> channelMap = new HashMap<String, Object>();
	private static Map<String, Object> projectMap = new HashMap<String, Object>();
	private static Map<String, Object> campaignMap = new HashMap<String, Object>();
	private static Map<String, Object> industryMap = new HashMap<String, Object>();

	private CodeCacheMap() {
	}

	public static void updateTrade(List<Map<String, Object>> datas) {
		for (Map<String, Object> row : datas) {
			String cid = String.valueOf(row.get("code"));
			Object name = row.get("name");
			tradeMap.put(cid, name);
		}
	}
	
	public static void updateChannel(List<Map<String, Object>> datas) {
		for (Map<String, Object> row : datas) {
			String cid = String.valueOf(row.get("id"));
			Object name = row.get("name");
			channelMap.put(cid, name);
		}
	}
	
	public static void updateProject(List<Map<String, Object>> datas) {
		for (Map<String, Object> row : datas) {
			String pid = String.valueOf(row.get("id"));
			Object name = row.get("name");
			projectMap.put(pid, name);
		}
	}

	public static void updateCamp(List<Map<String, Object>> datas) {
		for (Map<String, Object> row : datas) {
			String campid = String.valueOf(row.get("id"));
			Object name = row.get("name");
			campaignMap.put(campid, name);
		}
	}

	public static void updateIndustry(List<Map<String, Object>> datas) {
		for (Map<String, Object> row : datas) {
			String id = String.valueOf(row.get("id"));
			Object name = row.get("type");
			industryMap.put(id, name);
		}
	}

	public static Object getValue(String dimKey, String dimCode) {
		if (TYPE_PROJECT.equals(dimKey)) {
			return projectMap.get(dimCode);
		} else if (TYPE_CAMP.equals(dimKey)) {
			return campaignMap.get(dimCode);
		} else if (TYPE_INDUSTRY.equals(dimKey)) {
			return industryMap.get(dimCode);
		} else if (TYPE_CHANNEL.equals(dimKey)) {
			return channelMap.get(dimCode);
		} else if (TYPE_TRADE.equals(dimKey)) {
			return tradeMap.get(dimCode);
		}
		return dimCode;
	}
}
