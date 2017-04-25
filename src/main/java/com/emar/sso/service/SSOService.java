package com.emar.sso.service;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emar.encypt.JasyptEncrypt;
import com.emar.sso.bean.AuthMessage;
import com.emar.sso.bean.AuthResult;
import com.emar.sso.bean.SSOConfig;

@Service
public class SSOService extends BaseService {

	public static final String PROP_VERSION = "sso_v";
	public static final String PROP_APPKEY = "sso_k";
	public static final String PROP_REQID = "sso_id";
	public static final String PROP_TIMESTAMP = "sso_t";
	public static final String PROP_SIGNDATA = "sso_s";
	public static final String PROP_USERDATA = "sso_u";
	public static final String PROP_CALLBACKURL = "sso_r";

	@Autowired
	private SSOConfig ssoConfig;

	/**
	 * 获取登录页面url
	 */
	public String getSSOUrl(boolean islogin) {

		StringBuffer url = new StringBuffer();
		
		if (islogin) {
			url.append(ssoConfig.getSsoLoginUrl());
		} else {
			url.append(ssoConfig.getSsoLogoutUrl());
		}
		url.append("?").append(PROP_VERSION).append("=").append(ssoConfig.getAppVersion());
		url.append("&").append(PROP_APPKEY).append("=").append(ssoConfig.getAppKey());
		url.append("&").append(PROP_CALLBACKURL).append("=");
		try {
			url.append(URLEncoder.encode(ssoConfig.getCallbackUrl(), charSet));
		} catch (Exception e) {
			url.append(ssoConfig.getCallbackUrl());
		}

		return url.toString();
	}

	public AuthResult checkUserData(AuthMessage message) {

		AuthResult result = new AuthResult();

		String msg = null;
		String userData = message.getUserData();
		if (null == userData) {
			msg = "参数值格式错误";
		} else {
			String plainText = JasyptEncrypt.decryptMessage(userData, ssoConfig.getAppSecret());

			String[] dataArr = plainText.split("\\" + AuthMessage.MESSAGE_TOKEN);
			if (dataArr.length != 5) {
				msg = "参数值个数错误";
			} else if (!dataArr[0].equalsIgnoreCase(message.getAppKey())) {
				msg = "参数 客户端标识 无效";
			} else if (!dataArr[1].equalsIgnoreCase(message.getTimestamp())) {
				msg = "参数 时间戳 无效";
			} else if (!dataArr[2].equalsIgnoreCase(message.getRequestId())) {
				msg = "参数 请求唯一标识 无效";
			} else {
				Long userId = Long.parseLong(dataArr[3]);
				result.setUserId(userId);
				result.setUserType(dataArr[4]);
			}
		}
		if (null != msg) {
			result.setSuccess(false);
			result.setMessage(msg);
		}
		return result;
	}
}
