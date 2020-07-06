package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

public class TbProvince implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -618850320671694262L;

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
