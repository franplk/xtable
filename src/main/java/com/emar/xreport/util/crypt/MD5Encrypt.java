package com.emar.xreport.util.crypt;

import java.security.MessageDigest;

public class MD5Encrypt {

	private static final MessageDigest MD5;

	private MD5Encrypt(){}

	static {
		try {
			MD5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException("MD5加密实例初始化失败");
		}
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String encrypt(String inStr) {
		byte[] md5Bytes = MD5.digest(inStr.getBytes());
		return toHexString(md5Bytes);
	}

	private static String toHexString(byte[] md5Bytes) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
