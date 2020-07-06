package com.qifenqian.bms.platform.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述一个字段的注解
 * 
 * @project gyzb-platform-common
 * @fileName Description.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
	String value();
}
