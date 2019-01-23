package com.emar.xreport.util.udf;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TableDataFormat {
	
	public static void format (List<Map<String, Object>> rowList) {
		for (Map<String, Object> row : rowList) {
			format(row);
		}
	}
	
	public static void format (Map<String, Object> row) {
		for (Entry<String, Object> entry : row.entrySet()) {
			String key = entry.getKey();
			row.put(key, format ("digit", row.get(key)));
		}
	}
	
	public static Object format (String type, Object value) {
		return String.format("%.2f", value);
	}
	
	public static void main(String[] args) {
		System.out.println(format("", 3456.426245d));
	}

}
