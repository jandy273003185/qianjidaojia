package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class WeChatAppAreaInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4988373454809088071L;

	private String areaId;//区域编号
	
	private String provinceName;//省份名字

	private String cityName;//城市名字
	
	private String areaName;//区县名字
	
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	
}
