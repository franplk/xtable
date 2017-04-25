package com.emar.cache;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.emar.log.LogService;
import com.emar.util.StringUtil;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.CachingFilter;

public class PageCacheService extends CachingFilter {

	public PageCacheService() {
		LogService.info("初始化页面缓存...", LogService.TYPE_INIT);
	}

	protected String getCacheName() {
		if (StringUtil.isNotEmpty(cacheName)) {
			return cacheName;
		} else {
			return "page";
		}
	}

	@Override
	protected CacheManager getCacheManager() {
		return CacheManager.getInstance();
	}

	@Override
	protected String calculateKey(HttpServletRequest request) {
		StringBuffer urlKey = new StringBuffer();
		urlKey.append(request.getMethod()).append(request.getRequestURI());
		String queryString = request.getQueryString();
		if (null != queryString) {
			String[] queryStrs = queryString.split("&");
			Arrays.sort(queryStrs);
			for (String query : queryStrs) {
				urlKey.append(query);
			}
		}
		return urlKey.toString();
	}
}
