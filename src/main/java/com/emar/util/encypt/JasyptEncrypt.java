package com.emar.util.encypt;

import java.io.UnsupportedEncodingException;

import org.jasypt.util.text.StrongTextEncryptor;

public class JasyptEncrypt {

	private static final String charset = "UTF-8";
	
    /**
     * 加密数据
     * @param plainMessage 普通文本
     * @param appSecret 密钥
     */
    public static String encryptMessage(final String plainMessage, final String appSecret) {
        String encryptedMessage = "";
        try {
        	StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        	textEncryptor.setPassword(appSecret);
        	encryptedMessage = textEncryptor.encrypt(plainMessage);
        	encryptedMessage = unWebSafeAndPad(Base64.encode(encryptedMessage.getBytes(charset)));
        } catch (UnsupportedEncodingException e) {
        	encryptedMessage = unWebSafeAndPad(encryptedMessage);
        }
        return encryptedMessage;
    }
	
	/**
	 * 解密数据
	 */
	public synchronized final static String decryptMessage(String encryptedMessage, String appSecret) {
		String decodeText = null;
		try {
			decodeText = new String(Base64.decode(encryptedMessage), charset);
		} catch (UnsupportedEncodingException e) {
			decodeText = encryptedMessage;
		}
		StrongTextEncryptor encryptor = new StrongTextEncryptor();
		encryptor.setPassword(appSecret);
		return encryptor.decrypt(decodeText);
	}
	
	/**
     * Converts from unpadded web safe base 64 encoding (RFC 3548) to standard
     * base 64 encoding (RFC 2045) and pads the result.
     */
    private static String unWebSafeAndPad(String webSafe) {
        String pad = "";
        if ((webSafe.length() % 4) == 2) {
            pad = "==";
        } else if ((webSafe.length() % 4) == 1) {
            pad = "=";
        }
        return webSafe.replace('-', '+').replace('_', '/') + pad;
    }
    
    // 测试主函数
 	public static void main(String args[]) {
 		System.out.println("解密后：" + decryptMessage("M0tIeEl3Z25ZaEZGbTZWQytRdEYvZy8reEVTWU52UW54ajhrdmtsaG1kQTVUdTViS1ZjcVM1ZWF2b3I2Z2R3eXVmNmMzZUEzbnlMZ0p0VlJIcFFjeEQyZWtySU0ydDZ5QlV3dCtzeWdaZlRhWTNaOTdWZEh6R3JHUVoyOVJrRmo=", "emar-xview"));
 	}
}
