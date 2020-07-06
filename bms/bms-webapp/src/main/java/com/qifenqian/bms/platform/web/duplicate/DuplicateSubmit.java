package com.qifenqian.bms.platform.web.duplicate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 禁止重复提交
 * 
 * @project gyzb-platform-web
 * @fileName DuplicateSubmit.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DuplicateSubmit {
	
	Type value();
	
	public enum Type {
		SET,
		CHECK	
	}
}
