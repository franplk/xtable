package com.emar.xreport.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emar.xreport.model.domain.Dashboard;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author Franplk
 * Dashboard Cache
 */
@Service
public class ModelCacheService extends BaseCache {

	@Resource(name="modelCache")
	private Cache modelCache;

	public ModelCacheService() {
	}

	/**
	 * 将模型存入缓存中
	 */
	public void put(String key, Object model) {
		if (key == null) {
			return;
		}
		modelCache.put(new Element(key, model));
	}

	/**
	 * 从cache中获取分析模型对象
	 */
	public Dashboard getBoard(String key) {
		Element element = modelCache.get(key);
		if (element == null) {
			return null;
		}
		return (Dashboard) element.getObjectValue();
	}
}
