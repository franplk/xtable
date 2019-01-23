package com.emar.xreport.model.service;

import com.emar.xreport.model.dao.DataModelDao;
import com.emar.xreport.model.domain.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2016.06.15
 * 
 * @author franplk
 */
@Service
public class DataModelService {

	@Autowired
	private DataModelDao dataModelDao;

	public DataModel getDataModel(String dashId) {
		return dataModelDao.getModel(dashId);
	}
}
