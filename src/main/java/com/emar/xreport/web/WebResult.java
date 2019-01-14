package com.emar.xreport.web;

import java.io.Serializable;

/**
 * @domain WebPage
 * @author franplk
 */
public class WebResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;// Is Query OK
	private String msg;// Failed Message
	private Object data;// Query Web Page

	public WebResult() {}

	public WebResult(String msg, boolean success) {
		this.msg = msg;
		this.success = success;
	}

	public WebResult(Object data) {
		this.success = true;
		this.data = data;
	}

	public static WebResult success() {
		return success(null);
	}

	public static WebResult success(Object data) {
		WebResult webResult = new WebResult();
		webResult.setSuccess(true);
		webResult.setData(data);
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
