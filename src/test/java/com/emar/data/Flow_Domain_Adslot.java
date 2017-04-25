package com.emar.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.emar.util.HttpUtils;

public class Flow_Domain_Adslot {

	public static void main(String[] args) {
		
		String url = "http://172.16.16.151:10010/get_flow_domain_report";
		
		// Wrap The Query Conditions
		Map<String, Object> params = new HashMap<>();
		
		Map<String, Object> dayCon = new HashMap<>();
		
		// Date 
		dayCon.put("gte", "2016-10-11");
		dayCon.put("lte", "2016-10-12");
		params.put("rep_date", dayCon);
		
		// “”:[{“channel_id”:”desc”},{“project_id”:”asc”}],
		List<Map<String, Object>> sort = new ArrayList<>();
		Map<String, Object> sortMap = new HashMap<>();
		sortMap.put("req_num", "desc");
		sort.add(sortMap);
		params.put("sort_by", sort);
		
		try {
			String result = HttpUtils.get(url, params);
			
			JSONObject json = JSON.parseObject(result);
			
			int errorCode = json.getIntValue("error");
			if (errorCode != 0) {
				System.out.println("errorCode = " + errorCode);
				String desc = json.getString("description");
				System.out.println("desc = " + desc);
				return;
			}
			
			int total = json.getIntValue("total_num");
			System.out.println("total = " + total);
			
			String rows = json.getString("result");
			List<Map<String, Object>> listMap = JSON.parseObject(rows, new TypeReference<List<Map<String,Object>>>(){});
			for (Map<String, Object> row : listMap) {
				System.out.println(row);
			}

		} catch (Exception e) {
			
		}

	}
}
