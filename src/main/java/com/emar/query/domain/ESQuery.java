package com.emar.query.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.emar.query.filter.ESFilter;
import com.emar.util.DateUtil;

/**
 * @author franplk 2016.08.08
 */
public class ESQuery extends QueryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean real;
	
	private List<ESFilter> dimFilter;
	private List<ESFilter> aggFilter;

	public ESQuery (){}
	
	public ESQuery (boolean real){
		this.real = real;
	}
	
	public boolean isReal() {
		return real;
	}

	public void setReal(boolean real) {
		this.real = real;
	}

	public List<ESFilter> getDimFilter() {
		return dimFilter;
	}

	public void setDimFilter(List<ESFilter> dimFilter) {
		this.dimFilter = dimFilter;
	}

	public List<ESFilter> getAggFilter() {
		return aggFilter;
	}

	public void setAggFilter(List<ESFilter> aggFilter) {
		this.aggFilter = aggFilter;
	}

	public void addFilter (String field, String type, String value) {
		if (null == this.dimFilter) {
			this.dimFilter = new ArrayList<>();
		}
		this.dimFilter.add(new ESFilter(field, type, value));
	}

	public QueryBuilder getQueryBuilder () {
		BoolQueryBuilder allbuilder = QueryBuilders.boolQuery();
		if (!real) {
			String s_date = dateSpan.getStartDate();
			if (DateUtil.isOneday(dateSpan)) {
				allbuilder.must(new ESFilter("rep_date", "eq", s_date).buildQuery());
			} else {
				String e_date = dateSpan.getEndDate();
				allbuilder.must(new ESFilter("rep_date", "gte", s_date).buildQuery());
				allbuilder.must(new ESFilter("rep_date", "lte", e_date).buildQuery());
			}
		}
		if (null != dimFilter) {
			for (ESFilter filter : dimFilter) {
				allbuilder.must(filter.buildQuery());
			}
		}
		return allbuilder;
	}
}
