package com.qifenqian.bms.platform.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qifenqian.bms.platform.common.message.type.PadType;

/**
 * 
 * @project gyzb-platform-common
 * @fileName MessageField.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo 定长
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageFieldFixedLength {
	
	/**
	 * 栏位名称
	 * @return
	 */
	public String name() default "";
	
	/**
	 * 栏位代码
	 * @return
	 */
	public String fieldCode() default "";

	/**
	 * 开始位置
	 * @return
	 */
	public int startPos() default 0;
	
	/**
	 * 长度
	 * @return
	 */
	public int length() default 0;
	
	/**
	 * 填充方向
	 * @return
	 */
	public PadType padType() default PadType.RIGHT;
	
	/**
	 * 填充
	 * @return
	 */
	public char material() default ' ';
	
	/**
	 * 数据类型
	 * @return
	 */
	public Class<?> fieldClass() default String.class;
	
	/**
	 * 匹配格式
	 * @return
	 */
	public String regex() default "";
	
}


