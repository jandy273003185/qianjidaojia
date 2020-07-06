package com.qifenqian.bms.basemanager.city;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;

@Controller
@RequestMapping(CityPath.BASE)
public class CityController {

	private static Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;

	/**
	 * 查询城市
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(CityPath.LIST)
	public ModelAndView list(City city) {

		ModelAndView model = new ModelAndView(CityPath.BASE + CityPath.LIST);
		model.addObject("queryBean", city);
		model.addObject("cityList", JSONObject.toJSON(cityService.selectCitys(city)));
		return model;
	}
	
	/**
	 * 根据省市编号查询城市
	 * 
	 * @param provinceId
	 * @return
	 */
	@RequestMapping(CityPath.GET_CITY_BY_PROVINCEID)
	@ResponseBody
	public JSONObject getCityByProvinceId(String provinceId) {
		JSONObject json = new JSONObject();
		List<City> cityList = cityService.getCityByProvinceId(provinceId);
		if (cityList != null) {
			json.put("cityList", cityList);
		} else {
			json.put("fail", null);
		}
		return json;
	}

	/**
	 * 根据城市编号查询区
	 * 
	 * @param cityId
	 * @return
	 */
	@RequestMapping(CityPath.GET_AREA_BY_CITYID)
	@ResponseBody
	public JSONObject getAreaByCityId(String cityId) {
		JSONObject json = new JSONObject();
		List<City> areaList = cityService.getAreaByCityId(cityId);
		if (areaList != null) {
			json.put("areaList", areaList);
		} else {
			json.put("fail", null);
		}
		return json;
	}
	
	/**
	 * 根据区县编号查询区
	 * 
	 * @param areaId
	 * @return
	 */
	@RequestMapping(CityPath.GET_AREA_BY_AREAID)
	@ResponseBody
	public JSONObject getAreaByAreaId(String areaId) {
		JSONObject json = new JSONObject();
		List<City> addressList = cityService.getAreaByAreaId(areaId);
		if (addressList != null) {
			json.put("addressList", addressList);
		} else {
			json.put("fail", null);
		}
		return json;
	}

	/**
	 * 增加城市信息
	 * @param insertBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(CityPath.ADD)
	public String add(City insertBean) {
		logger.info("增加省市/城市/区域信息对象 {}",JSONObject.toJSONString(insertBean,true));
		JSONObject json = new JSONObject();
		json= cityService.addProvince_city_area(insertBean);
		return json.toJSONString();

	}
	
	/**
	 * 修改城市信息
	 * @param updateBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(CityPath.UPDATE)
	public String update(City updateBean) {
		logger.info("修改省市/城市/区域信息对象 {}",JSONObject.toJSONString(updateBean,true));
		JSONObject json = new JSONObject();
		json= cityService.updateProvince_city_area(updateBean);
		return json.toJSONString();

	}
	
	/**
	 * 删除城市信息
	 * @param deleteBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(CityPath.DELETE)
	public String delete(City deleteBean) {
		logger.info("删除省市/城市/区域信息对象 {}",JSONObject.toJSONString(deleteBean,true));
		JSONObject json = new JSONObject();
		json= cityService.deleteProvince_city_area(deleteBean);
		return json.toJSONString();

	}

}
