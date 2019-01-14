package com.emar.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emar.util.arith.SimpleArithmetic;

public class FormulaTest {

	public static List<String> analyze (String input) {
		
		List<String> words = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("[a-z]+");
		
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find()) {
			words.add(matcher.group());
		}
		
		return words;
	}
	
	public static void main (String args[]) {
		Map<String, Object> item = new HashMap<>();
		item.put("imp", 536);
		item.put("clk", 234);
		item.put("exp", 365);
		
		String input = "100*(imp-clk)/exp";
		List<String> words = analyze(input);
		for (String word : words) {
			System.out.println(word);
			input = input.replaceAll(word, String.valueOf(item.get(word)));
		}
		SimpleArithmetic arithmetic = new SimpleArithmetic();
		arithmetic.parse(input);
		System.out.println(arithmetic.compute());
	}
	
}
