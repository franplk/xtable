package com.emar.util.encypt;

import java.security.MessageDigest;

public class MD5Encrypt {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String encrypt(String inStr) {
		byte[] md5Bytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5Bytes = md5.digest(inStr.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
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

	public static void main(String[] args) {
		System.out.println("加密后：" + encrypt("franplk"));
	}
}
