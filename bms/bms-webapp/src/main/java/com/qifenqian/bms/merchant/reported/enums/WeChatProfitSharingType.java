package com.qifenqian.bms.merchant.reported.enums;

public enum WeChatProfitSharingType {
	MERCHANT_ID("商户ID"),
	PERSONAL_WECHATID("个人微信号"),
	PERSONAL_OPENID("个人openid"),
	PERSONAL_SUB_OPENID("个人sub_openid");
	
	private String text;
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	private WeChatProfitSharingType(String text) {
		this.text = text;
	}
}
