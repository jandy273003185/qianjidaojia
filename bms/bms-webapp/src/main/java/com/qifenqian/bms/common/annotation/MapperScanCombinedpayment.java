package com.qifenqian.bms.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 聚合mapper 注解扫描
 * @project sevenpay-platform-web
 * @fileName MapperScanCombinedpayment.java
 * @author huiquan.ma
 * @date 2015年7月22日
 * @memo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MapperScanCombinedpayment {

}
