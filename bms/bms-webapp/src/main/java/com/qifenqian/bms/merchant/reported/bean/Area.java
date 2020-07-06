package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class Area implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7264747055966984377L;

	private String areaId;//地区编号
	
	private String areaName;//地区名字
	
	private String cityId;//市区编号

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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	
	
}
