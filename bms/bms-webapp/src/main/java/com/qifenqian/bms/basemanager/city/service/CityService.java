package com.qifenqian.bms.basemanager.city.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.bean.CityNew;
import com.qifenqian.bms.basemanager.city.bean.ProvinceCityBean;
import com.qifenqian.bms.basemanager.city.bean.RegionsBean;
import com.qifenqian.bms.basemanager.city.dao.CityDAO;
import com.qifenqian.bms.basemanager.city.mapper.CityMapper;

/**
 * 城市服务层
 * 
 * @project sevenpay-bms-web
 * @fileName RoleService.java
 * @author Dayet
 * @date 2015年5月11日
 * @memo
 */
@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private CityDAO cityDAO;

	/**
	 * 查询所有城市信息
	 * 
	 * @return
	 */
	public List<City> selectCitys(City city) {
		return cityDAO.selectCitys(city);
	}

	/***
	 * 根据provinceId查找城市
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<City> getCityByProvinceId(String provinceId) {
		return cityMapper.getCityByProvinceId(provinceId);
	}
	/**
	 * 查找所有省份
	 * @param city
	 * @return
	 */
	public List<City> selectAllProvince() {
		return cityMapper.selectAllProvince();
	}
	/***
	 * 根据cityId查找区/县
	 * 
	 * @param cityId
	 * @return
	 */
	public List<City> getAreaByCityId(String cityId) {
		return cityMapper.getAreaByCityId(cityId);
	}

	/***
	 * 根据areaId查找省/城市/区/县
	 * 
	 * @param areaId
	 * @return
	 */
	public List<City> getAreaByAreaId(String areaId) {
		return cityMapper.getAreaByAreaId(areaId);
	}

	/***
	 * 新增省/市/区/县
	 * 
	 * @param insertBean
	 * @return
	 */
	public JSONObject addProvince_city_area(City insertBean) {

		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (null == insertBean) {
			json.put("message", "新增对象为空");
		}
		try {

			switch (insertBean.getCityLvl()) {
			case 0:
				if (StringUtils.isEmpty(insertBean.getProvinceName())) {
					json.put("message", "省市名称为空");
				}
				cityMapper.addProvince(insertBean);
				json.put("result", "SUCCESS");
				break;
			case 1:
				if (StringUtils.isEmpty(String.valueOf(insertBean.getProvinceId()))) {
					json.put("message", "省份编号为空");
				}
				if (StringUtils.isEmpty(insertBean.getCityName())) {
					json.put("message", "城市名称为空");
				}
				cityMapper.addCity(insertBean);
				json.put("result", "SUCCESS");
				break;
			case 2:
				if (StringUtils.isEmpty(String.valueOf(insertBean.getProvinceId()))) {
					json.put("message", "省份编号为空");
				}
				if (StringUtils.isEmpty(String.valueOf(insertBean.getCityId()))) {
					json.put("message", "城市编号为空");
				}
				if (StringUtils.isEmpty(insertBean.getAreaName())) {
					json.put("message", "区域名称为空");
				}
				if (StringUtils.isEmpty(String.valueOf(insertBean.getAreaCode()))) {
					json.put("message", "区域代码为空");
				}
				cityMapper.addArea(insertBean);
				json.put("result", "SUCCESS");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("message", e.getMessage());
		}
		return json;
	}

	/**
	 * 修改信息
	 * @param updateBean
	 * @return
	 */
	public JSONObject updateProvince_city_area(City updateBean) {
		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (null == updateBean) {
			json.put("message", "修改对象为空");
		}
		try {

			switch (updateBean.getCityLvl()) {
			case 0:
				if (StringUtils.isEmpty(String.valueOf(updateBean.getProvinceId()))) {
					json.put("message", "省份编号为空");
				}
				if (StringUtils.isEmpty(updateBean.getProvinceName())) {
					json.put("message", "省份名称为空");
				}
				cityMapper.updateProvince(updateBean);
				json.put("result", "SUCCESS");
				break;
			case 1:
				if (StringUtils.isEmpty(String.valueOf(updateBean.getCityId()))) {
					json.put("message", "城市编号为空");
				}
				if (StringUtils.isEmpty(updateBean.getCityName())) {
					json.put("message", "城市名称为空");
				}
				cityMapper.updateCity(updateBean);
				json.put("result", "SUCCESS");
				break;
			case 2:
				if (StringUtils.isEmpty(String.valueOf(updateBean.getAreaId()))) {
					json.put("message", "区域编号为空");
				}
				if (StringUtils.isEmpty(updateBean.getAreaName())) {
					json.put("message", "区域名称为空");
				}
				if (StringUtils.isEmpty(String.valueOf(updateBean.getAreaCode()))) {
					json.put("message", "区域代码为空");
				}
				cityMapper.updateArea(updateBean);
				json.put("result", "SUCCESS");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("message", e.getMessage());
		}
		return json;
	}
	
	/**
	 * 删除城市信息
	 * @param deleteBean
	 * @return
	 */
	public JSONObject deleteProvince_city_area(City deleteBean) {
		JSONObject json = new JSONObject();
		json.put("result", "FAIL");
		if (null == deleteBean) {
			json.put("message", "删除对象为空");
		}
		try {

			switch (deleteBean.getCityLvl()) {
			case 0:
				if (StringUtils.isEmpty(String.valueOf(deleteBean.getProvinceId()))) {
					json.put("message", "省份编号为空");
				}
				cityMapper.deleteProvince(deleteBean);
				json.put("result", "SUCCESS");
				break;
			case 1:
				if (StringUtils.isEmpty(String.valueOf(deleteBean.getCityId()))) {
					json.put("message", "城市编号为空");
				}
				cityMapper.deleteCity(deleteBean);
				json.put("result", "SUCCESS");
				break;
			case 2:
				if (StringUtils.isEmpty(String.valueOf(deleteBean.getAreaId()))) {
					json.put("message", "区域编号为空");
				}
				
				cityMapper.deleteArea(deleteBean);
				json.put("result", "SUCCESS");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("message", e.getMessage());
		}
		return json;
	}
	
	/**
	 * 查询省市地址
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	public ProvinceCityBean findProvineAndCity(String provinceId, String cityId) {
		return cityMapper.findProvineAndCity(provinceId, cityId);
	}

	public String findProvineNameById(Integer province) {
		return cityMapper.findProvineNameById(province);
	}

	public String findCityName(City city) {
		return cityMapper.findCityName(city);
	}
	/**
	 * 获取所有省份(七分钱)
	 * @return
	 */
	public List<RegionsBean> selectSpProvince(){
		return cityDAO.selectSpProvince();
	}
	
	/**
	 * 获取省份下面的城市(七分钱)
	 * @param provinceId
	 * @return
	 */
	public List<RegionsBean> getSpCityByProvinceId(String provinceId){
		return cityDAO.getSpCityByProvinceId(provinceId);
	}
	
/*************************************商户地址所需省市区*******************************************/
	
	/**
	 * 获取所有省份
	 * @return
	 */
	public List<City> selAllProvince(){
		return cityMapper.getProvinceLists();
	}
	
	/**
	 * 获取省份下面的城市
	 * @param provinceId
	 * @return
	 */
	public List<City> selCityByProvinceId(String provinceId){
		CityNew city = new CityNew();
		city.setProvinceId(provinceId);
		return cityMapper.getCityList(city);
	}
	
	/**
	 * 获取城市下面的地区
	 * @param cityId
	 * @return
	 */
	public List<City> selAreaByCityId(String cityId){
		CityNew city = new CityNew();
		city.setCityId(cityId);
		return cityMapper.getAreaList(city);
	}
	
	/**
	 * 获取城市下面的地区(七分钱)
	 * @param cityId
	 * @return
	 */
	public List<RegionsBean> getSpAreaByCityId(String cityId){
		return cityDAO.getSpAreaByCityId(cityId);
	}
}
