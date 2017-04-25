package com.emar.map.demo;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws Exception {
		String clsName = "com.emar.map.demo.CategoryId2NameUDF";

		Class<?> c = Class.forName(clsName);

		Object[] param = new Object[] { 1 };

		Method method = c.getDeclaredMethod("evaluate", Integer.class);

		Object o = method.invoke(c.newInstance(), param);

		System.out.println(o);

	}

}
