package com.qifenqian.bms.basemanager.city.bean;

import java.io.Serializable;

public class ProvinceBean implements Serializable{

	private static final long serialVersionUID = -8145581714296716554L;

	private String provinceId;
	
	private String provinceCode;
	
	private String provinceName;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
