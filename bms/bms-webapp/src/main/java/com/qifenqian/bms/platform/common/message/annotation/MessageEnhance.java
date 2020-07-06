package com.qifenqian.bms.platform.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;
import com.qifenqian.bms.platform.common.message.type.InterceptorAction;
import com.qifenqian.bms.platform.common.message.type.InterceptorTime;

/**
 * 
 * @project gyzb-platform-common
 * @fileName MessageEnhance.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageEnhance {

	/**
	 * 拦截时机
	 * @return
	 */
	public InterceptorTime time();
	
	/**
	 * 拦截动作
	 * @return
	 */
	public InterceptorAction action();
	
	/**
	 * 拦截器
	 * @return
	 */
	public Class<? extends MessageInterceptorInterface> interceptor();
}


