package com.qifenqian.bms.merchant.reported.enums;
/**
 * 渠道编码
 * @author shili
 *
 */
public enum ChannelCodeType {
	ALIPAY("支付宝"),
	WX("微信"),
	SUIXING_PAY("随行付");
	
	private String text;
	private ChannelCodeType(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
