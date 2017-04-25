package com.emar.sso.bean;

import java.io.Serializable;

public class AuthMessage implements Serializable {

	private static final long serialVersionUID = -345030049903629714L;
	
	public static final String MESSAGE_TOKEN = "|";

	private String version;
	private String requestId;
	private String appKey;
	private String appSecret;
	private String timestamp;

	private String targetUrl;
	private String callbackUrl;

	private String signData;
	private String userData;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getSignData() {
		return signData;
	}

	public void setSignData(String signData) {
		this.signData = signData;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthMessage [version=");
		builder.append(version);
		builder.append(", requestId=");
		builder.append(requestId);
		builder.append(", appKey=");
		builder.append(appKey);
		builder.append(", appSecret=");
		builder.append(appSecret);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", targetUrl=");
		builder.append(targetUrl);
		builder.append(", callbackUrl=");
		builder.append(callbackUrl);
		builder.append(", signData=");
		builder.append(signData);
		builder.append(", userData=");
		builder.append(userData);
		builder.append("]");
		return builder.toString();
	}

}
