package com.qifenqian.bms.accounting.adjust.service;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AdjustServiceAspect {
	
	private static Logger logger = LoggerFactory.getLogger(AdjustService.class);
	
	@Pointcut("execution(* com.sevenpay.bms.accounting.adjust.service.AdjustServiceSupport.*(..))")
	public void performance() {
		System.out.println("@Aspect");
	}
	
	@Before("performance()")
	public void before() {
		logger.info("before");
	}
	
	
	@AfterThrowing("performance()")
	public void afterThrowing(Method method, Object[] args, Object cObj, Exception e) {
		logger.debug("调用" + method.getName() + "方法时发生异常, 方法参数", args);
		logger.error("调用" + method.getName() + "方法时发生异常:" + e.getMessage(), e);
	}
	
}
