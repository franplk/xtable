package com.emar.xreport.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Franplk
 * @Function Log
 */
public class LogUtil {

	private static Logger log = LoggerFactory.getLogger(LogUtil.class);
	
	public static String TYPE_AUTH = "[SSO]";
	public static String TYPE_INIT = "[INIT]";
	public static String TYPE_QUERY = "[QUERY]";
	public static String TYPE_MODEL = "[MODEL]";
	public static String TYPE_UPDATE = "[UPDATE]";
	public static String TYPE_ACCESS = "[ACCESS]";
	
	public static void info(String msg, String logType) {
		log.info(logType + msg);
	}

	public static void debug(String msg, String logType) {
		log.debug(logType + msg);
	}

	public static void error(String msg, String logType) {
		log.error(logType + msg);
	}
}
