package com.emar.xreport.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.xreport.sso.bean.AuthResult;
import com.emar.xreport.sso.bean.User;
import com.emar.xreport.sso.dao.AuthDao;

@Service
public class AnthService {

	@Autowired
	private AuthDao authDao;

	public User getUserInfo(AuthResult query) {
		return authDao.getUserInfo(query);
	}
}
