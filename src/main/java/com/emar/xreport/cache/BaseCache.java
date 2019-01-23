package com.emar.xreport.cache;

import net.sf.ehcache.CacheManager;

/**
 * @author Franplk
 * Abstract Base Cache
 */
public abstract class BaseCache {

	public BaseCache() { }

	public abstract void put(String key, Object obj);
	
	public void close () {
		CacheManager.getInstance().shutdown();
	}
}
