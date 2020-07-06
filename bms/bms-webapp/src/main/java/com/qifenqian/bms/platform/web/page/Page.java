package com.qifenqian.bms.platform.web.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示一个DAO层方法查询的是分页列表,而不是全量的列表
 * 
 * @project sevenpay-platform-web
 * @fileName Page.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Page {
}
