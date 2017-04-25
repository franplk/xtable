package com.emar.query.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.query.dao.FilterDAO;
import com.emar.query.domain.QueryCondition;

@Service
public class FilterService {

	@Autowired
	private FilterDAO conDao;
	
	public List<QueryCondition> getConTree(String boardId) {
		return conDao.getConTree(boardId);
	}

	public List<Map<String, Object>> getConType(String id) {
		return conDao.getConType(id);
	}

	public List<Map<String, Object>> getConValue(String conId) {
		return conDao.getConValue(conId);
	}

}
