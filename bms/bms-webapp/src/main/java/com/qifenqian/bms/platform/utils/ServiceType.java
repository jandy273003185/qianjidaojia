package com.qifenqian.bms.platform.utils;

public enum ServiceType {
	
	ONECODE_COLL_PAY("onecode.coll.pay","扫码支付"),
		
	PC_GATEWAY_PAY("pc.gateway.pay","网关支付"),	
		
	H5_GATEWAY_PAY("h5.gateway.pay","H5支付"),
		
	MOBILE_PLUGIN_PAY("mobile.plugin.pay","APP支付"),
	
	H5_T_GATEWAY_PAY("h5_t.gateway.pay","原生H5支付"),
	
	AGENT_MERCHANT_LOW_RATE("agent_merchant_rate","代理商低价费率");
		
	private ServiceType(String type,String desc){
		this.desc = desc;
		this.type = type;
	}
	
	private String type;
	
	private String desc;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public static String getDesc(String serviceType){
		
		for (ServiceType type : ServiceType.values()) {
			if(type.getType().equals(serviceType)){
				return type.getDesc();
			}
		}
		return "其他支付方式";
		
	}
}
