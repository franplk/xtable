package com.emar.api;

import com.emar.xreport.domain.QueryBean;

/**
 * Data API Interface
 * 
 * @author franplk
 */
public interface DataInter<T> {
	
	/**
	 * Get Table Data For Specified Board With Query Bean
	 */
	public T getData(String boardId, QueryBean queryBean);
}
