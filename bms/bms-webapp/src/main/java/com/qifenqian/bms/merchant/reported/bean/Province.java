package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class Province implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9085812816863846639L;

	private String provinceId;//省份编号
	
	private String provinceName;//省份名字

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
