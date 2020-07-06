package com.qifenqian.bms.merchant.reported.enums;

public enum WeChatRelationType {
	SERVICE_PROVIDER("服务商"),
	STORE("门店"),
	STAFF("员工"),
	STORE_OWNER("店主"),
	PARTNER("合作伙伴"),
	HEADQUARTER("总部"),
	BRAND("品牌方"),
	DISTRIBUTOR("分销商"),
	USER("用户"),
	SUPPLIER("供应商"),
	CUSTOM("自定义");
	
	private String text;
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	private WeChatRelationType(String text) {
		this.text = text;
	}
}
