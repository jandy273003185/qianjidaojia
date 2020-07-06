package com.qifenqian.bms.merchant.reported.enums;
/**
 * 银行编码
 * @author shili
 *
 */
public enum WeChatBankType {
	工商银行("工商银行"),
	交通银行("交通银行"),
	招商银行("招商银行"),
	民生银行("民生银行"),
	中信银行("中信银行"),
	浦东银行("浦东银行"),
	兴业银行("兴业银行"),
	光大银行("光大银行"),
	广发银行("广发银行"),
	平安银行("平安银行"),
	北京银行("北京银行"),
	华夏银行("华夏银行"),
	农业银行("农业银行"),
	中国银行("中国银行"),
	建设银行("建设银行"),
	邮政储蓄银行("邮政储蓄银行"),
	宁波银行("宁波银行"),
	其他银行("其他银行");
	
	private String name;
	WeChatBankType(String name){
		
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
