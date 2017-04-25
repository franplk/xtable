package com.emar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.model.dao.TemplateDao;
import com.emar.model.domain.Template;
import com.emar.sso.bean.User;

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