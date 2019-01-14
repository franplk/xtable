package com.emar.exception;

import com.emar.xreport.sso.bean.User;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public AuthException () {
		super();
	}
	
	public AuthException (String message) {
		super(message);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getMessage () {
		if (null == user) {
			return super.getMessage();
		}
		return user.toString();
	}
}
