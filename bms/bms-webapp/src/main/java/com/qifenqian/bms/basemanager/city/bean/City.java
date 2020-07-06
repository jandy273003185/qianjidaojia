package com.qifenqian.bms.basemanager.city.bean;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = -682833961334225889L;

	/**********************************************************/

	/** 城市代码 */
	private int cityId;

	/** 地区名称 */
	private String cityName;

	/** 省市编号 */
	private int provinceId;
	/** 省市名称 */
	private String provinceName;

	private int areaId;
	/** 区域代码 **/
	private String areaCode;

	private int areaLevel;

	/** 区域名称 **/
	private String areaName;

	private int cityLvl;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(int areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCityLvl() {
		return cityLvl;
	}

	public void setCityLvl(int cityLvl) {
		this.cityLvl = cityLvl;
	}

}
