package com.qifenqian.bms.accounting.kingdee.bean;

import java.io.Serializable;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = -1791018881819690310L;

	private String beginDate;

	private String endDate;

	/**
	 * 收方账户类型
	 */
	private String frecType;

	/**
	 * 省（收方）
	 */
	private String province;

	/**
	 * 市（收方）
	 */
	private String city;

	/**
	 * 地区（收方）
	 */
	private String district;

	public String getFrecType() {
		return frecType;
	}

	public void setFrecType(String frecType) {
		this.frecType = frecType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
