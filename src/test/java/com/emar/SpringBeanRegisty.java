/**
 * 
 */
package com.emar;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author franplk
 *
 */
public class SpringBeanRegisty implements ApplicationContextAware {

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	/**
	 * @desc 向spring容器注册bean
	 */
	public void addBean(SpringBean bean) {
		String beanName = bean.getName();
		if (!appContext.containsBean(beanName)) {
			
			// Bean Definition
			Class<?> beanClass = getBeanClass(bean.getClassName());
			BeanDefinitionBuilder beanBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
			BeanDefinitionBuilder.rootBeanDefinition(beanClass);
			beanBuilder.addPropertyValue("servicename", beanName);
			
			// Bean Factory
			ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) appContext;
			DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configContext.getBeanFactory();
			
			// Bean Registry
			beanFactory.registerBeanDefinition(beanName, beanBuilder.getRawBeanDefinition());
		}
	}

	/**
	 * @desc 根据类名查找class
	 */
	private Class<?> getBeanClass(String className) {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {

		}
		return null;
	}
}
