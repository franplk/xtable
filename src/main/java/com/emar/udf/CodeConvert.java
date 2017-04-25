package com.emar.udf;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.emar.cache.domain.CodeCacheMap;

public class CodeConvert {
	
	public String date_format_day(Object code) {
		String codeStr = String.valueOf(code);
		if (codeStr.matches("\\d+")) {
			long timeMillions = Long.parseLong(codeStr);
			Date date = new Date(timeMillions);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return codeStr;
	}
	
	public String date_format_hour(Object code) {
		String codeStr = String.valueOf(code);
		if (null == code) {
			return "--";
		}
		
		StringBuffer hour = new StringBuffer(16);
		int len = codeStr.length();
		if(len < 2){
			hour.append("0").append(codeStr).append(":00-0");
		} else {
			hour.append(codeStr).append(":00-");
		}
		hour.append(codeStr).append(":59");
		
		return hour.toString();
	}
	
	public Object industry (Object code) {
		Object mapValue = CodeCacheMap.getValue(CodeCacheMap.TYPE_INDUSTRY, String.valueOf(code));
		if (null == mapValue) {
			return code;
		}
		return mapValue;
	}
	
	public Object trade_type (Object code) {
		Object mapValue = CodeCacheMap.getValue(CodeCacheMap.TYPE_TRADE, String.valueOf(code));
		if (null == mapValue) {
			return code;
		}
		return mapValue;
	}
	
	public Object flow_convert (Object code) {
		String codeStr = String.valueOf(code);
		if (codeStr.startsWith("10")) {
			return "PC流量";
		} else if (codeStr.startsWith("20")) {
			if (codeStr.endsWith("1")) {
				return "移动WEB";
			} else if (codeStr.endsWith("2")) {
				return "移动APP";
			} else if (codeStr.endsWith("3")) {
				return "移动原生";
			}
		} else if (codeStr.startsWith("30")) {
			return "社交广告";
		}
		return code;
	}
	
	public Object is_rd (Object code) {
		String codeStr = String.valueOf(code);
		if ("0".equals(codeStr) || "1".equals(codeStr)) {
			return "RD";
		} else if ("2".equals(codeStr)) {
			return "非RD";
		}
		return code;
	}
}
