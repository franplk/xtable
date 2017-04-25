package com.emar.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.emar.cache.CacheUpdate;
import com.emar.log.LogService;

/**
 * Spring Init Data
 * Cache The Map<id, name> Of Project, Campaign, Channel And Other Code Map
 * @author franplk
 * @date 2016-08-28
 */
public class CodeConvertListener implements ApplicationListener<ContextRefreshedEvent> {

	private CacheUpdate cacheUpdate;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			LogService.info("Cache Init...", LogService.TYPE_UPDATE);
			cacheUpdate.initCache();
		}
	}

	public CacheUpdate getCacheUpdate() {
		return cacheUpdate;
	}

	public void setCacheUpdate(CacheUpdate cacheUpdate) {
		this.cacheUpdate = cacheUpdate;
	}
}
