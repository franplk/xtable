/**
 * 
 */
package com.emar.util.arith;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author franplk
 * @date 2016年10月8日 上午9:45:50
 * @TODO
 */
public class FormulaUtil {

	public static Object computeFormula(Map<String, Object> item, final String formula) {

		Set<String> params = analyze(formula);
		String expression = formula;
		for (String p : params) {
			Object value = item.get(p);
			if (null == value) {
				return "--";
			}
			expression = expression.replaceAll(p, String.valueOf(value));
		}
		try {
			SimpleArithmetic arithmetic = new SimpleArithmetic();
			arithmetic.parse(expression);
			return arithmetic.compute();
		} catch (Exception e) {
			return "--";
		}
	}

	private static Set<String> analyze(final String formula) {
		Set<String> words = new HashSet<>();
		Pattern pattern = Pattern.compile("[_a-z]+");
		Matcher matcher = pattern.matcher(formula);
		while (matcher.find()) {
			words.add(matcher.group());
		}
		return words;
	}
	
	public static void main(String[] args) {
	}
}
