package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class SumPayArea implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 27905664124581902L;

	private String areaId;//地区编号
	
	private String areaName;//地区名字
	
	private String precinctName;//区域名称
	
	private String superiorAreaCode;//上级地区编号

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

	public String getPrecinctName() {
		return precinctName;
	}

	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}

	public String getSuperiorAreaCode() {
		return superiorAreaCode;
	}

	public void setSuperiorAreaCode(String superiorAreaCode) {
		this.superiorAreaCode = superiorAreaCode;
	}

	
	
}
