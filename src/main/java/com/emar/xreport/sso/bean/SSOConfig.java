package com.emar.xreport.sso.bean;

public class SSOConfig {

	private String ssoLoginUrl;
	private String ssoLogoutUrl;
	private String callbackUrl;
	private String appKey;
	private String appSecret;
	private String appVersion;

	public String getSsoLoginUrl() {
		return ssoLoginUrl;
	}

	public void setSsoLoginUrl(String ssoLoginUrl) {
		this.ssoLoginUrl = ssoLoginUrl;
	}

	public String getSsoLogoutUrl() {
		return ssoLogoutUrl;
	}

	public void setSsoLogoutUrl(String ssoLogoutUrl) {
		this.ssoLogoutUrl = ssoLogoutUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
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

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}
