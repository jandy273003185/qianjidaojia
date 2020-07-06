package com.qifenqian.bms.platform.web.duplicate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.qifenqian.bms.platform.web.utils.WebUtils;

/**
 * 防重控制
 *
 * @project gyzb-platform-web
 * @fileName DuplicateSubmitAdvice.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Aspect
@Component
public class DuplicateSubmitAdvice {

  /** 只拦截注解了DuplicateSubmit的方法 */
  @Around(value = "@annotation(submitAnnotation)", argNames = "submitAnnotation")
  public Object detactPageAnnotation(
      ProceedingJoinPoint proceedingJoinPoint, DuplicateSubmit submitAnnotation) throws Throwable {
    try {
      if (submitAnnotation.value() == DuplicateSubmit.Type.CHECK) {
        WebUtils.checkSubmitToken();
      }
      return proceedingJoinPoint.proceed();
    } finally {
      if (submitAnnotation.value() == DuplicateSubmit.Type.SET) {
        WebUtils.setSubmitToken();
      }
    }
  }
}
