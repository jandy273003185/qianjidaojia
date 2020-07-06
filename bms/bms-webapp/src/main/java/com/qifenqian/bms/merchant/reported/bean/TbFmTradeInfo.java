package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TbFmTradeInfo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4333405292936315824L;

	private String powerId;//支付功能ID
	
	private String powerName;//支付功能名称
	
	private String powerType;//所属产品类型

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	

	
	
}
