package com.emar.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author franplk
 * @date 2016年10月12日 下午1:50:01
 */
public class HttpUtils {

	public static final String CHARSET = "UTF-8";

	public static final int REP_GET = 0;
	public static final int REP_POST = 1;

	public static String requestData(String url, int type, Map<String, Object> params) throws Exception {

		String result = null;

		switch (type) {
		case REP_GET:
			result = post(url, params);
			break;
		case REP_POST:
			result = get(url, params);
			break;
		default:
			result = get(url, params);
			break;
		}
		return result;
	}

	/**
	 * 发送 get请求
	 */
	public static String get(String url) throws Exception {
		return get(url, null);
	}

	public static String get(String url, Map<String, Object> params) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String paramURL = getParamURL(url, params);
		
		System.out.println("Encode:" + paramURL);

		try {
			HttpGet httpget = new HttpGet(paramURL);
			CloseableHttpResponse response = httpclient.execute(httpget);
			return getResponse(response);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param url
	 * @param queryBean
	 * @return
	 */
	private static String getParamURL(String url, Map<String, Object> params) {

		if (params == null) {
			return url;
		}

		StringBuffer paramStr = new StringBuffer();
		try {
			for (Entry<String, Object> entry : params.entrySet()) {
				paramStr.append("&").append(entry.getKey()).append("=");
				String jsonStr = JSON.toJSONString(entry.getValue());
				paramStr.append(URLEncoder.encode(jsonStr, CHARSET));
			}
		} catch (UnsupportedEncodingException e) {
		}

		String paramURL = paramStr.toString();
		if (!url.contains("?")) {
			paramURL = paramURL.replaceFirst("&", "?");
		}
		return url + paramURL;
	}

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static String post(String url) throws Exception {
		return post(url, null);
	}

	public static String post(String url, Map<String, Object> params) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);

			// Set Parameter
			HttpEntity uefEntity = getHttpEntity(params);
			if (uefEntity != null) {
				httppost.setEntity(uefEntity);
			}

			// Get Response Enity
			CloseableHttpResponse response = httpclient.execute(httppost);
			return getResponse(response);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Set Parameter
	private static StringEntity getHttpEntity(Map<String, Object> params) {

		if (params == null) {
			return null;
		}

		JSONObject jsonParams = new JSONObject();
		for (Entry<String, Object> entry : params.entrySet()) {
			jsonParams.put(entry.getKey(), entry.getValue());
		}

		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonParams.toString());
			entity.setContentEncoding(CHARSET);
			entity.setContentType("text/json");
		} catch (UnsupportedEncodingException e) {
		}

		return entity;
	}

	private static String getResponse(CloseableHttpResponse response) throws Exception {
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, CHARSET);
			} else {
				return "";
			}
		} finally {
			response.close();
		}
	}

	public static void main(String args[]) {
		/*
		 * try { String result = HttpUtils.post("http://www.baidu.com");
		 * System.out.println(result); } catch (Exception e) { }
		 */

		Map<String, Object> params = new HashMap<>();
		params.put("name", "franplk");

		String url = getParamURL("asds", params);

		System.out.println(url);
	}
}