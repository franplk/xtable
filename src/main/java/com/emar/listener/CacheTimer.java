package com.emar.listener;

import com.emar.cache.CacheUpdate;
import com.emar.log.LogService;

/**
 * Spring Quartz Cache Cache The Map<id, name> Of Project, Campaign, Channel And
 * Other Code Map
 * 
 * @author franplk
 * @date 2016-08-28
 */
public class CacheTimer {

	private CacheUpdate cacheUpdate;

	public void mapCache() {
		LogService.info("Cache Init...", LogService.TYPE_UPDATE);
		cacheUpdate.update();
	}

	public void dataCache() {
		
	}
	
	public CacheUpdate getCacheUpdate() {
		return cacheUpdate;
	}

	public void setCacheUpdate(CacheUpdate cacheUpdate) {
		this.cacheUpdate = cacheUpdate;
	}
}