/**
 * 
 */
package com.emar.es;

import org.elasticsearch.client.Client;

import com.emar.query.domain.ESQuery;
import com.emar.xreport.domain.JTableData;

/**
 * @author Franplk
 */
public abstract class AbstractESOperation {

	protected String type;
	protected String prefix;
	protected int groupSize;
	protected ESDataSource esDataSource;

	protected abstract JTableData queryTable(ESQuery query);
	
	protected Client getClient() {
		return esDataSource.getClient();
	}
	
	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ESDataSource getEsDataSource() {
		return esDataSource;
	}

	public void setEsDataSource(ESDataSource esDataSource) {
		this.esDataSource = esDataSource;
	}
}
