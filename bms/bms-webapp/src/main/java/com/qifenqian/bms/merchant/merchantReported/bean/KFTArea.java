package com.qifenqian.bms.merchant.merchantReported.bean;

import java.io.Serializable;

public class KFTArea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908689986622078511L;

	private String areaId;//地区编号
	
	private String areaName;//地区名字
	
	private String provinceName;//省名称
	
	private String cityName;//市名称


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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	
	
}
