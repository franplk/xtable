package com.emar.xreport.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emar.arith.SimpleArithmetic;

public abstract class EsBaseDao {

	protected Object computeFormula(Map<String, Object> item, final String formula) {

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

	private Set<String> analyze(final String formula) {
		Set<String> words = new HashSet<>();
		Pattern pattern = Pattern.compile("[_a-z]+");
		Matcher matcher = pattern.matcher(formula);
		while (matcher.find()) {
			words.add(matcher.group());
		}
		return words;
	}

}
