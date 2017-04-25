package com.emar.map.registry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.emar.map.UDF;

/**
 * 函数注册
 * @author jiaqiang
 * 2016.08.16
 */
public final class FunctionRegistry {
	
	private static Logger log = Logger.getLogger(FunctionRegistry.class);
	
	
	//函数名到UDF.class映射
	private static Map<String,Class<?>> regFunMap = new HashMap<String, Class<?>>(256);
		
	/**
	 * 注册自定义函数
	 * @param functionName  函数名 如 get_category_by_id
	 * @param funClass      UDF对应的Class，如 GetCategoryByIdUDF.class
	 * @param regIfExist    存在是否再注册（存在是否覆盖） 
	 * @return
	 */
	public static boolean register(String functionName, Class<?> funClass, boolean regIfExist) {
		if (functionName == null || functionName.trim().length() == 0) {
			log.error("自定义函数名称[" + functionName + "]非法");
			return false;
		}
		
		if (funClass == null) {
			log.error("自定义函数Class is null");
			return false;
		}
		
		String f = functionName.trim();
		if (regFunMap.get(f) == null) { //不存在
			regFunMap.put(f, funClass);
		} else { //已经注册过
			if (regIfExist) { //存在仍注册，即覆盖
				regFunMap.put(f, funClass);
			}
		}
		
		return true;
	}

	private static Object invoke(String methodName, UDF udf, Object[] params) throws Exception {
		if (methodName == null || methodName.trim().length() == 0) {
			return new NoSuchMethodException();
		}
		
		if (udf == null) {
			throw new NullPointerException("param udf is null");
		}
		
		Class<?>[] paramClass = null;
		if (params != null) {
			paramClass = new Class<?>[params.length];
			for (int i=0; i < params.length; i++) {
				paramClass[i] = params[i].getClass();
			}
		}
		
		Method m = udf.getClass().getDeclaredMethod(methodName, paramClass);
		
		return m.invoke(udf, params);
	}
	
	/**
	 * 执行udf的evalute方法
	 * @param udf
	 * @param params 参数值
	 * @return
	 * @throws Exception
	 */
	public static Object invoke(UDF udf, Object[] params) throws Exception {
		return invoke("evaluate", udf, params);
	}
	
	/**
	 * 执行udf的evalute方法
	 * @param udf    自定义函数全限命名, 如com.emarbox.es.udf.demo.CategoryId2NameUDF
	 * @param params 参数值
	 * @return
	 * @throws Exception
	 */
	public static Object invoke(String udf, Object[] params) throws Exception {
		Class<?> c = Class.forName(udf);
		if (c.getSuperclass() != UDF.class) {
			return null;
		}
		
		return invoke("evaluate", (UDF)c.newInstance(), params);
	}
	
	/**
	 * 执行函数
	 * @param fucntionName   函数名称 
	 * @param params         参数
	 * @return
	 * @throws Exception
	 */
	public static Object execute(String functionName, Object[] params) throws Exception {
		Class<?> udfClass = regFunMap.get(functionName);
		if (udfClass == null) {
			return null;
		}
		
		return invoke("evaluate", (UDF)(udfClass.newInstance()), params);
	}
	
	public static void main(String[] args) throws Exception {
		String s = "com.emar.map.demo.CategoryId2NameUDF";
		Class<?> funClass = Class.forName(s);
		String f = "get_category_name";
		FunctionRegistry.register(f, funClass, true);
		
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 10000; i++) {
			FunctionRegistry.execute(f, new Object[]{i});
		}
		
		System.out.println((System.currentTimeMillis()-start));
	}

}
