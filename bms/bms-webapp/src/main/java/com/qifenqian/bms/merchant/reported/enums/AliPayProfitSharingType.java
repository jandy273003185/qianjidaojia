package com.qifenqian.bms.merchant.reported.enums;

public enum AliPayProfitSharingType {
	userId("支付宝唯一用户号"),
	loginName("支付宝登录号");
	
	private String text;
	
	private AliPayProfitSharingType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
