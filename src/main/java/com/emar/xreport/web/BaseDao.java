package com.emar.xreport.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.emar.cache.domain.CodeCacheMap;
import com.emar.xreport.model.domain.ConfigColumn;
import com.emar.util.udf.CodeConvert;
import com.emar.util.StringUtil;

public abstract class BaseDao {

	protected void dataFilter (ConfigColumn dimColumn, List<Map<String, Object>> datas) {
		
		// Data List Is Empty Or Not
		if (datas == null || datas.isEmpty()) {
			return;
		}
		
		String dimKey = dimColumn.getField();
		
		// Add Map Name
		String mapName = dimColumn.getMapName();
		if (StringUtil.isNotEmpty(mapName)) {
			for (Map<String, Object> item : datas) {
				String code = String.valueOf(item.get(dimKey));
				item.put(mapName, CodeCacheMap.getValue(dimKey, code));
			}
			return;
		}
		
		// Map Code
		String methodName = dimColumn.getFormula();
		if (StringUtil.isEmpty(methodName)) {
			return;
		}
		
		// Map Convert
		try {
			CodeConvert convert = new CodeConvert();
			Class<?> clazz = CodeConvert.class;
			Method method = clazz.getDeclaredMethod(methodName, Object.class);
			for (Map<String, Object> item : datas) {
				Object code = item.get(dimKey);
				item.put("dimCode", code);
				item.put(dimKey, method.invoke(convert, code));
			}
		} catch (Exception e) {
		}
	}
}
