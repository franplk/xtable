package com.emar.xreport.util.arith;

import java.math.BigDecimal;

public class DataUtil {

	public static Double getDoubleValue(Object value) {
		Double doubleValue = null;
		try {
			doubleValue = new Double(String.valueOf(value));
		} catch (Exception e) {
			doubleValue = 0d;
		}
		return doubleValue;
	}
	
	public static Long getLongValue(Object value) {
		Long longValue = null;
		try {
			longValue = new Long(String.valueOf(value));
		} catch (Exception e) {
			longValue = 0l;
		}
		return longValue;
	}
	
	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal decimalValue = null;
		try {
			decimalValue = new BigDecimal(String.valueOf(value));
		} catch (Exception e) {
			decimalValue = new BigDecimal(0);
		}
		return decimalValue;
	}
	
	public static void main(String[] args) {
		Long hisValue = getLongValue("15189933617");
		Long dayValue = getLongValue("452988171");
		
		System.out.println(hisValue + dayValue);
	}
}
