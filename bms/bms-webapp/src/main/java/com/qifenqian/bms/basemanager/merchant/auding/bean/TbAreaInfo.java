package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

/**
 * 区域信息表
 * @author Roy.Li
 *
 */
public class TbAreaInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1552590284585418533L;

	/**
	 * 区域id
	 */
	private Integer areaId;
	
	/**
	 * 区域代码
	 */
	private Integer areaCode;
	
	/**
	 * 区域名称
	 */
	private String areaName;
	
	/**
	 * 区域级别
	 */
	private Integer areaLevel;
	
	/**
	 * 城市id
	 */
	private Integer cityId;
	
	/**
	 * 省份id
	 */
	private Integer province_id;

	private Integer provinceId;
	
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProvince_id() {
		return province_id;
	}

	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
}
