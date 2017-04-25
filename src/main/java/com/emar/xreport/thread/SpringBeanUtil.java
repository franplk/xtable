package com.emar.xreport.thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationcontext;

	@Override
	public final void setApplicationContext(ApplicationContext applicationcontext) {
		SpringBeanUtil.applicationcontext = applicationcontext;
	}

	public static Object getBean(String name) {
		return applicationcontext.getBean(name);
	}
	
	public static <T>T getBean(Class<T> clazz) {
		return applicationcontext.getBean(clazz);
	}
	
	public static <T>T getBean(String name, Class<T> clazz) {
		return applicationcontext.getBean(name, clazz);
	}
}
