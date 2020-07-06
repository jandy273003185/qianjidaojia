package com.qifenqian.bms.platform.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

/**
 * 容器获取工具
 * 
 * @project gyzb-platform-common
 * @fileName SpringUtils.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
@Repository
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	/**
	 * 根据名称获取bean
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 根据class 获取bean
	 * 
	 * @param cls
	 * @return
	 */
	public static <T> T getBean(Class<T> cls) {
		return applicationContext.getBean(cls);
	}

}
