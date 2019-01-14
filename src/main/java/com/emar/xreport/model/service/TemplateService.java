package com.emar.xreport.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.xreport.model.dao.TemplateDao;
import com.emar.xreport.model.domain.Template;
import com.emar.xreport.sso.bean.User;

/**
 * @author Franplk
 * @since 2016-09-07
 * @version 0.0.1
 */
@Service
public class TemplateService {

	@Autowired
	private TemplateDao templateDao;

	public void saveIndex(User user, Template template) {
		templateDao.saveIndex(user, template);
	}
}