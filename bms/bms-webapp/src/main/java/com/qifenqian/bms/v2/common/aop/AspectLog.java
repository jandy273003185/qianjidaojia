package com.qifenqian.bms.v2.common.aop;

import com.qifenqian.bms.v2.common.config.ConfigBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/2 09:27
 */
@Aspect
@Component
public class AspectLog {
    private Logger logger = LoggerFactory.getLogger(getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private ConfigBean configBean;

    @Pointcut("execution(public * com.qifenqian.bms.v2.biz..controller.*.*(..))")
    public void webLog() {
        logger.debug("AspectLog webLog");
    }

    @Before("webLog()")
    public void doBeFore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
    }

    /**
     * @param [ret]
     * @return void
     * @description 后置通知
     * @author LiBin
     * @date 2020/4/2 09:46
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (ret == null) {
            return;
        }
        //处理完返回内容
        logger.info("RESPONSE : " + ret.toString());
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
