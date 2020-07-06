package com.qifenqian.bms.basemanager.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Comment;

/**
 * 订单状态
 * 
 * @project sevenpay-bms-web
 * @fileName OrderStatus.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo 
 */
public enum OrderStatus {

	/**
	 * 01：待提交
	 */
	@Comment(code="01",desc="待提交")
	TS,
	
	/**
	 *02 提交核心处理
	 */
	@Comment(code="02",desc="提交核心")
	SC,
	
	/**
	 * 03 核心已受理
	 */
	@Comment(code="03",desc="核心处理中")
	CD,
	
	/**
	 * 04 核心返回失败
	 */
	@Comment(code="04",desc="失败")
	CF,
	
	/**
	 * 00 核心返回成功
	 */
	@Comment(code="00",desc="成功")
	CS,
	
	/**
	 * 99 取消
	 */
	@Comment(code="99",desc="取消")
	CANCEL,
	
	/**
	 * 90 未明
	 */
	@Comment(code="90",desc="未明")
	UN;

	
	
	/**	获取描述性内容	*/
	public String getDesc() {
		Class<?> clazz = this.getClass();
		Field filed = null;
		try {
			filed = clazz.getField(this.name());
		} catch (Exception e) {
		}
		Comment code = filed.getAnnotation(Comment.class);
		if (code == null) {
			return null;
		} else {
			return code.desc();
		}
	}
	
	/** 获取编码 */
	public String getCode() {
		Class<?> clazz = this.getClass();
		Field filed = null;
		try {
			filed = clazz.getField(this.name());
		} catch (Exception e) {
		}
		Comment code = filed.getAnnotation(Comment.class);
		if (code == null) {
			return null;
		} else {
			return code.code();
		}
	}
	
}


