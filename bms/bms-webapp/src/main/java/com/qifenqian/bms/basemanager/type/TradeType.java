package com.qifenqian.bms.basemanager.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Comment;

/**
 * 用户状态
 * 
 * @project sevenpay-bms-web
 * @fileName TradeType.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo 
 */
public enum TradeType {

	/**
	 * 转账到7分钱
	 */
	@Comment(code="1001",desc="转账到7分钱")
	TIS,
	
	/**
	 * 冻结
	 */
	@Comment(code="1002",desc="转账到银行卡")
	TIC,
	
	/**
	 * 商户接入-收款-无担保
	 */
	@Comment(code="1101",desc="商户接入-收款-无担保")
	MSW,
	
	/**
	 * 商户接入-收款-有担保
	 */
	@Comment(code="1102",desc="商户接入-收款-有担保")
	MSY,
	
	/**
	 * 1201 转入7分宝
	 */
	@Comment(code="1201",desc="转入7分宝")
	TIB,
	
	/**
	 * 1202 转出7分宝
	 */
	@Comment(code="1202",desc="转出7分宝")
	TOB,
	
	/**
	 * 8001 退款
	 */
	@Comment(code="8001",desc="退款")
	RB;
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


