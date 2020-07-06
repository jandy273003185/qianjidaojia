package com.qifenqian.bms.basemanager.city;

/**
 * 城市管理相关路径
 * 
 * @project sevenpay-bms-web
 * @fileName CityPath.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */
public final class CityPath {

	/** 系统管理-城市管理*/
	public final static String BASE = "/basemanager/city";
	/** [DATA]城市列表*/
	public final static String LIST = "/list";
	
	/** 增加城市*/
	public final static String ADD = "/add";
	
	/**获取城市级联**/
	public final static String GETCITY="/getCitys";
	
	/** 更新城市*/
	public final static String UPDATE = "/update";
	
	/** 删除城市*/
	public final static String DELETE = "/delete";
	
	public final static String GET_CITY_BY_PROVINCEID ="/getCityByProvinceId";
	
	public final static String GET_AREA_BY_CITYID ="/getAreaByCityId"; 
	
	public final static String GET_AREA_BY_AREAID ="/getAreaByAreaId";    
}


