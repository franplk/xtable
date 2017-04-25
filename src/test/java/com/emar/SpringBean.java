/**
 * 
 */
package com.emar;

import java.util.Map;

/**
 * @author franplk
 *
 */
public abstract class SpringBean {

	public abstract String getName();
	public abstract String getClassName();
	
	public abstract Map<String, String> getBeanReference();

}
