package com.emar.xreport.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String join(List<String> array, char sep) {
		if (array == null || array.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : array) {
			sb.append(sep).append(str);
		}
		return sb.toString().substring(1);
	}

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static List<String> split2List(String str, String split) {
		List<String> list = null;
		if (str != null) {
			list = Arrays.asList(str.split(split));
		}
		return list;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher numMatcher = pattern.matcher(str);
		return numMatcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(isNumeric("13254132"));
	}
}
