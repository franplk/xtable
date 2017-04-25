package com.emar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.model.dao.ChartDao;

@Service
public class ChartService {

	@Autowired
	private ChartDao chartDao;
	
	public void getChart(Integer dashId) {
		chartDao.getChart(dashId);
	}
	
}
