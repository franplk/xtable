package com.emar.query.filter;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.emar.model.domain.ConfigColumn;

/**
 * 封装ES查询条件
 * 
 * @author franplk
 * 
 * 2016.08.06
 */
public class ESFilter extends BaseFilter {

	public ESFilter() {}
	
	public ESFilter(ConfigColumn column, String value){
		this(column, "eq", value);
	}
	
	public ESFilter (String field, String type, String value) {
		this.column = new ConfigColumn(field);
		this.type = type;
		this.value = value;
	}
	
	public ESFilter (ConfigColumn column, String type, String value) {
		this.column = column;
		this.type = type;
		this.value = value;
	}

	/**
	 * 根据查询条件构建QB
	 * 
	 * @param queryCondition
	 * @return
	 */
	public QueryBuilder buildQuery() {
		String field = column.getField();
		
		if (value.contains(",")) {
			String [] values = value.split(",");
			return QueryBuilders.termsQuery(field, values);
		}
		
		QueryBuilder builder = null;
		if("inc".equals(type)){// 包含
			builder = QueryBuilders.wildcardQuery(field, value + "*");
		} else if("eq".equals(type)){// 
			builder = QueryBuilders.termQuery(field, value);
		} else if("lte".equals(type)){
			builder = QueryBuilders.rangeQuery(field).lte(value);
		} else if("lt".equals(type)){
			builder = QueryBuilders.rangeQuery(field).lt(value);
		} else if("gte".equals(type)){
			builder = QueryBuilders.rangeQuery(field).gte(value);
		} else if("gt".equals(type)){
			builder = QueryBuilders.rangeQuery(field).gt(value);
		} else if("from".equals(type)){
			builder = QueryBuilders.rangeQuery(field).from(value);
		} else if("to".equals(type)){
			builder = QueryBuilders.rangeQuery(field).to(value);
		} else {
			builder = QueryBuilders.regexpQuery(field, value);
		}
		return builder;
	}
}
