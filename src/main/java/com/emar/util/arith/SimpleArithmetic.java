package com.emar.util.arith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author franplk
 * Simple Arithmetic Operation Of Four Rules
 * (),+,-,*,/ No [] {}
 */
public class SimpleArithmetic {
	
	// Operational Character Stack
	private Stack<String> operator = new Stack<String>();
	
	// 后缀表达式
	private List<String> postFix = new ArrayList<String>();

	private Pattern operatorPattern = Pattern.compile("[\\d\\.]");
	private Pattern arithmeticPattern = Pattern.compile("[\\(\\)\\+\\-\\*\\/]");

	// 将中缀表达式换为后缀表达式
	public void parse(String s) {
		s = replace(s);
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			String temp = s.substring(i, i + 1);
			if (operatorPattern.matcher(temp).matches()) {
				continue;
			}
			if (arithmeticPattern.matcher(temp).matches()) {
				j = process(j, i, s, temp);
			} else if (" ".equals(temp)) {
				return;
			}
		}
		if (j < s.length()) {
			postFix.add(s.substring(j, s.length()));
		}
		while (!this.operator.isEmpty()) {
			postFix.add(operator.pop());
		}
	}

	private String replace(String s) {
		return s.replaceAll("\\s", "");
	}

	private int process(int startIndex, int currentIndex, String str, String word) {
		if (startIndex != currentIndex) {
			postFix.add(str.substring(startIndex, currentIndex));
		}
		addOperator(word);
		startIndex = currentIndex + 1;
		if (startIndex > str.length()) {
			startIndex = str.length();
		}
		return startIndex;
	}

	public void addOperator(String operator) {
		if ("(".equals(operator)) {

		} else if (")".equals(operator)) {
			while (!this.operator.isEmpty()) {
				String temp = this.operator.pop();
				if ("(".equals(temp)) {
					break;
				} else {
					postFix.add(temp);
				}
			}
			return;
		} else if (!this.operator.isEmpty()) {
			while (!this.operator.isEmpty()) {
				String temp = this.operator.peek();
				if (needPop(temp, operator)) {
					this.postFix.add(this.operator.pop());
				} else {
					break;
				}
			}
		}
		this.operator.add(operator);
	}

	public boolean needPop(String inStackTop, String current) {
		return getLevel(current.charAt(0)) <= getLevel(inStackTop.charAt(0));
	}

	public int getLevel(char operator) {
		switch (operator) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return -1;
		}
	}

	public BigDecimal compute() {
		Stack<BigDecimal> stack = new Stack<BigDecimal>();
		for (int i = 0; i < this.postFix.size(); i++) {
			String exp = postFix.get(i);
			if (arithmeticPattern.matcher(exp).matches()) {
				BigDecimal bd2 = stack.pop();
				BigDecimal bd1 = stack.pop();
				BigDecimal temp = compute(exp.charAt(0), bd1, bd2);
				stack.add(temp);
			} else {
				stack.add(new BigDecimal(exp));
			}
		}
		return stack.pop();
	}

	private BigDecimal compute(char operator, BigDecimal bd1, BigDecimal bd2) {
		switch (operator) {
		case '+':
			return bd1.add(bd2);
		case '-':
			return bd1.subtract(bd2);
		case '*':
			return bd1.multiply(bd2);
		case '/':
			return bd1.divide(bd2, 4, BigDecimal.ROUND_HALF_DOWN);
		default:
			return null;
		}
	}

	public static void main(String[] args) {
		SimpleArithmetic arithmetic = new SimpleArithmetic();
		arithmetic.parse("15189933617+452988171");
		System.out.println(arithmetic.postFix);
		System.out.println(arithmetic.compute());
	}
}