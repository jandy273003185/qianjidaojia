package com.qifenqian.bms.merchant.settle.type;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleStatus.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo 
 */
public enum MerchantSettleStatus {

	@Description("待确认")
	INIT,
	@Description("审核通过")
	AUDIT_PASS,
	@Description("确认异常")
	AUDIT_EXCEPTION,
	@Description("确认撤销异常")
	CANCEL_EXCEPTION,
	@Description("发送金蝶")
	SEND_INIT,
	@Description("发送金蝶成功")
	SEND_SUCC,
	@Description("金蝶付款成功")
	PAY_SUCC,
	@Description("金蝶付款失败")
	PAY_FAIL,
	@Description("确认撤销")
	AUDIT_REFUSE,
	@Description("核销异常")
	VERIFIED_EXCEPTION,
	@Description("核销明确失败")
	VERIFIED_FAIL,
	@Description("已核销")
	VERIFIED,
	@Description("废弃")
	INVALID;
	
	/**	获取描述性内容	*/
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