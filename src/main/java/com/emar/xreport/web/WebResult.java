package com.emar.xreport.web;

import java.io.Serializable;

/**
 * @author franplk
 */
public class WebResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;// Is Query OK
	private String msg;// Failed Message
	private Object data;// Query Web Page

	public static WebResult success() {
		return success(null);
	}

	public static WebResult success(Object data) {
		WebResult webResult = new WebResult();
		webResult.setSuccess(true);
		webResult.setData(data);
		return webResult;
	}

	public static WebResult fail(String msg) {
		WebResult webResult = new WebResult();
		webResult.setSuccess(false);
		webResult.setMsg(msg);
		return webResult;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
