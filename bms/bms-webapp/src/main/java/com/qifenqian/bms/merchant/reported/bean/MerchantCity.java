package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class MerchantCity implements Serializable{

	
	
	
	/**
	 * 随行付商户注册地区bean
	 */
	private static final long serialVersionUID = 6826462760045754877L;

	//地区编号
	private String areaId;
	
	//地区名称
	private String areaName;

	//地区类型
	private String areaType;
	
	//上级地区编号
	private String superiorAreaCode;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getSuperiorAreaCode() {
		return superiorAreaCode;
	}

	public void setSuperiorAreaCode(String superiorAreaCode) {
		this.superiorAreaCode = superiorAreaCode;
	}
	
	
}
