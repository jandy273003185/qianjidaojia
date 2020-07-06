package com.qifenqian.bms.platform.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qifenqian.bms.platform.common.message.type.AnalysisObtainType;

/**
 * 解析规则来源 注解/DB
 * @project gyzb-platform-common
 * @fileName MessageAnalysisObtain.java
 * @author huiquan.ma
 * @date 2016年2月25日
 * @memo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageAnalysisObtain {
	
	/**
	 * 解析来源类型
	 * @return
	 */
	public AnalysisObtainType obtainType();
}


