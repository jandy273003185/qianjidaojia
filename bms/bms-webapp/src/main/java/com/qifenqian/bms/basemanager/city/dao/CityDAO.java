package com.qifenqian.bms.basemanager.city.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.bean.RegionsBean;
import com.qifenqian.bms.basemanager.city.mapper.CityMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName CityDAO.java
 * @author Dayet
 * @date 2015年5月18日
 * @memo 
 */
@Repository
public class CityDAO{

	@Autowired
	private CityMapper cityMapper;
	
	/**
	 * 分页查询城市列表
	 * @return
	 */
	@Page
	public List<City> selectCitys(City city) {
		return cityMapper.selectCitys(city);
	}
	
	/**
	 * 获取所有省份
	 * @return
	 */
	public List<City> selectAllProvince(){
		return cityMapper.selectAllProvince();
	}
	
	/**
	 * 获取所有省份(七分钱)
	 * @return
	 */
	public List<RegionsBean> selectSpProvince(){
		return cityMapper.selectSpProvince();
	}
	
	/**
	 * 获取省份下面的城市
	 * @param provinceId
	 * @return
	 */
	public List<City> getCityByProvinceId(String provinceId){
		return cityMapper.getCityByProvinceId(provinceId);
	}
	
	/**
	 * 获取省份下面的城市
	 * @param provinceId
	 * @return
	 */
	public List<RegionsBean> getSpCityByProvinceId(String provinceId){
		return cityMapper.getSpCityByProvinceId(provinceId);
	}
	
	/**
	 * 获取城市下面的地区
	 * @param cityId
	 * @return
	 */
	public List<City> getAreaByCityId(String cityId){
		return cityMapper.getAreaByCityId(cityId);
	}
	
	/**
	 * 获取城市下面的地区(七分钱)
	 * @param cityId
	 * @return
	 */
	public List<RegionsBean> getSpAreaByCityId(String cityId){
		return cityMapper.getSpAreaByCityId(cityId);
	}
}


