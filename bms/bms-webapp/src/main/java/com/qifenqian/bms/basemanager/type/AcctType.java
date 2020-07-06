package com.qifenqian.bms.basemanager.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * 账户类型
 * 
 * @project sevenpay-bms-web
 * @fileName AcctType.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo
 */
public enum AcctType {

	@Description("七分钱账户")
	SEVN_CUS_0,

	@Description("七分宝账户")
	QFB0_CUS_0,

	@Description("银行借记账户")
	BANK_CUS_0,

	@Description("银行贷记账户")
	BANK_CUS_1;

	/** 获取描述性内容 */
	public String getDesc() {
		Class<?> clazz = this.getClass();
		Field filed = null;
		try {
			filed = clazz.getField(this.name());
		} catch (Exception e) {
		}
		Description description = filed.getAnnotation(Description.class);
		if (description == null) {
			return null;
		} else {
			return description.value();
		}
	}

}
