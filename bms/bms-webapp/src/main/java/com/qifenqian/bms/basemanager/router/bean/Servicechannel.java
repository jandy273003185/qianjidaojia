package com.qifenqian.bms.basemanager.router.bean;

public class Servicechannel {
	//主键
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//产品类型
	private String productCode;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiCtrl() {
		return busiCtrl;
	}

	public void setBusiCtrl(String busiCtrl) {
		this.busiCtrl = busiCtrl;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	//渠道
	private String channel;
	
	//渠道编号
	private String channelCode;
	
	//本地业务类型代码
	private String busiType;
	
	//本地业务类型
	private String busiName;
	
	//是否由业务类型控制路由
	private String busiCtrl;
	
	//权重
	private Double weight;
}
