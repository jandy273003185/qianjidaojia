package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class YQBArea implements Serializable{

	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4226847829268790637L;

	private String areaCode;//地区编号
	
	private String areaName;//地区名字
	
	private String provinceName;//省名称
	
	private String cityName;//市名称

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
