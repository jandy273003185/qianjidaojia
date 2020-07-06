package com.qifenqian.bms.basemanager.city.bean;

import java.io.Serializable;

public class ProvinceCityBean implements Serializable {

	private static final long serialVersionUID = -1734055765619892895L;

	private String cityID;
	
	private String cityName;
	
	private String provinceID;
	
	private String provinceName;

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
