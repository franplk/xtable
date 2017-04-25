package com.emar.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.model.dao.DataModelDao;
import com.emar.model.domain.ConfigColumn;
import com.emar.model.domain.DataModel;

/**
 * @date 2016.06.15
 * 
 * @author franplk
 */
@Service
public class DataModelService {

	@Autowired
	private DataModelDao dataModelDao;

	public List<ConfigColumn> getColumnList(String themeId) {
		return dataModelDao.getColumnList(themeId);
	}

	public DataModel getDataModel(String dashId) {
		return dataModelDao.getModel(dashId);
	}
}
