package com.emar.sso.bean;

import java.io.Serializable;

/**
 * 权限检查结果对象
 */
public class AuthResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;
	private String code;
	private String message;

	private Long userId;
	private String userType;

	public AuthResult () {
		this.success = true;
	}
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
