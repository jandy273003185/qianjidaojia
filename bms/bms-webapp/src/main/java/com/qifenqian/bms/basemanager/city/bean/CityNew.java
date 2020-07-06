package com.qifenqian.bms.basemanager.city.bean;

import java.io.Serializable;

public class CityNew implements Serializable {

	private static final long serialVersionUID = -682833961334225889L;

	/**********************************************************/

	/** 城市代码 */
	private String cityId;

	/** 地区名称 */
	private String cityName;

	/** 省市编号 */
	private String provinceId;
	/** 省市名称 */
	private String provinceName;

	private String areaId;
	/** 区域代码 **/
	private String areaCode;

	private String areaLevel;

	/** 区域名称 **/
	private String areaName;

	private String cityLvl;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityLvl() {
		return cityLvl;
	}

	public void setCityLvl(String cityLvl) {
		this.cityLvl = cityLvl;
	}



}
