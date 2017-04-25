package com.emar.cache;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author Franplk Dashboard Cache
 */
@Service
@SuppressWarnings("unchecked")
public class DataCacheService extends BaseCache {

	@Resource(name = "dataCache")
	private Cache dataCache;

	public DataCacheService() {
	}

	/**
	 * 将数据存入缓存中
	 */
	public void put(String sqlKey, Object data) {
		if (sqlKey == null) {
			return;
		}
		dataCache.put(new Element(sqlKey, data));
	}

	/**
	 * 从cache中获取数据
	 */
	public List<Map<String, Object>> getDataList(String sqlKey) {
		Element element = dataCache.get(sqlKey);
		if (element == null) {
			return null;
		}
		return (List<Map<String, Object>>) element.getObjectValue();
	}

	/**
	 * 从cache中获取数据
	 */
	public Map<String, Object> getDataMap(String sqlKey) {
		Element element = dataCache.get(sqlKey);
		if (element == null) {
			return null;
		}
		return (Map<String, Object>) element.getObjectValue();
	}
}
