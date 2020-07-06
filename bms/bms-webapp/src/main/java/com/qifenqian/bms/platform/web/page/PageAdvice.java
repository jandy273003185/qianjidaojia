package com.qifenqian.bms.platform.web.page;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;

/**
 * 分页功能的探测性增强
 * 
 * @project sevenpay-platform-web
 * @fileName PageInfoAdvice.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo
 */
@Aspect
@Component
public class PageAdvice {
	
	/**
	 * 只拦截注解了Page的方法
	 */
	@Around("@annotation(com.qifenqian.bms.platform.web.page.Page)")
	public Object detactPageAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ThreadUtils.put(Constants.Platform.$_BY_PAGE, Boolean.TRUE);
		try {
			return proceedingJoinPoint.proceed();
		} finally {
			ThreadUtils.remove(Constants.Platform.$_BY_PAGE);
		}
	}
}
