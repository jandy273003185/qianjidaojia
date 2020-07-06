package com.qifenqian.bms.platform.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 备注
 * 
 * @project gyzb-platform-common
 * @fileName Comment.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Comment {
	/** 对应数据库值*/
	public String code() default "";
	
	/** 对应数据库描述*/
	public String desc();
	
}