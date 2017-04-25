package com.emar.log;

import org.apache.log4j.Logger;

/**
 * @author Franplk
 * @Function Log
 */
public class LogService {

	private static Logger log = Logger.getLogger(LogService.class);
	
	public static String TYPE_AUTH = "[SSO]";
	public static String TYPE_INIT = "[INIT]";
	public static String TYPE_QUERY = "[QUERY]";
	public static String TYPE_MODEL = "[MODEL]";
	public static String TYPE_UPDATE = "[UPDATE]";
	public static String TYPE_ACCESS = "[ACCESS]";

	public static Logger getLogger (Class<?> clazz) {
		if (clazz == null) {
			return Logger.getLogger(LogService.class);
		}
		return Logger.getLogger(clazz);
	}
	
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
