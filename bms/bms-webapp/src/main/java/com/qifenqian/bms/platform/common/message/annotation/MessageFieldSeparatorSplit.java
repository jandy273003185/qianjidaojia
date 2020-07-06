package com.qifenqian.bms.platform.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qifenqian.bms.platform.common.message.type.PadType;

/**
 * 
 * @project gyzb-platform-common
 * @fileName MessageFieldSeparatorSplit.java
 * @author huiquan.ma
 * @date 2016年02月22日
 * @memo 分割
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageFieldSeparatorSplit {
	
	/**
	 * 栏位名称
	 * @return
	 */
	public String name();
	
	/**
	 * 栏位索引编号
	 * @return
	 */
	public String columnId() default "0";
	
	/**
	 * 栏位代码
	 * @return
	 */
	public String fieldCode() default "";

	/**
	 * 分隔符
	 * @return
	 */
	public String separator() default "\\|";
	
	/**
	 * 分割序号 从0开始
	 * @return
	 */
	public int index();
	
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


