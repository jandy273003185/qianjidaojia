package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

public class TbCity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3235015482988257279L;

	private Integer cityId;
	
	private String cityName;
	
	private Integer provinceId;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
}
