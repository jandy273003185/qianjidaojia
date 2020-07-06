package com.qifenqian.bms.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.branchbank.service.BranchBankService;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;


@Controller
@RequestMapping("/common/info")
public class CommonInfoController {

	@Autowired
	CityService cityService;
	
	@Autowired
	BankService bankService;
	
	@Autowired
	BranchBankService branchBankService;
	
	@Autowired
	CommonInfoService commonInfoService;
	
	@Autowired
	FmIncomeMapperDao fmIncomeMapperDao;
	
	/**
	 * 根据渠道查询所有银行信息
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<Bank> bankInfo(Bank bank,String channelCode) {
		
		List<Bank>  bankList = commonInfoService.selectBanks(bank,channelCode);
		
		return bankList;
		
	}
	
	/**
	 * 查询微信开户银行信息
	 * @param bankName
	 * @return
	 */
	@RequestMapping(value = "/selectWeChatBankList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> selectWeChatBankList(String bankName) {
		List<Map<String, String>> selectWeChatBankList = commonInfoService.selectWeChatBankList(bankName);
		return selectWeChatBankList;
	}
	
	/**
	 * 根据渠道查询所有省份
	 * @return
	 */
	@RequestMapping(value = "/getProvinceInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<Province> selectAllProvince(String channelCode) {
		
		List<Province> provinceList = commonInfoService.selectAllProvince(channelCode);
		
		return provinceList;
		
	}
	
	/**
	 * 根据渠道和省份查询城市
	 * @param provinceBean
	 * @return
	 */
	@RequestMapping(value = "/getCityInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getCityInfo(String provinceId,String channelCode) {
		JSONObject object = new JSONObject();
		List<City> cityList = commonInfoService.getCityByProvinceId(provinceId,channelCode);
		if(null!=cityList &&cityList.size()>0){
			object.put("result", "SUCCESS");
			object.put("cityList", cityList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "查询城市失败");
		}
		return object.toString();
		
	}
	
	/**
	 * 根据渠道和城市查询区县
	 * @param cityBean
	 * @return
	 */
	@RequestMapping(value = "/getAreaInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getAreaInfo(City cityBean,String channelCode) {
		JSONObject object = new JSONObject();
		
		List<City> areaList = commonInfoService.getAreaByCityId(cityBean,channelCode);
		if(null!=areaList &&areaList.size()>0){
			object.put("result", "SUCCESS");
			object.put("areaList", areaList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "查询县区失败");
		}
		return object.toString();
		
	}
	
	/**
	 * 根据银行和城市查询渠道的分行
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "/bankCnapsInfo", method = RequestMethod.POST)
	@ResponseBody
	public String bankCnapsInfo(BranchBankInfo bank,String channelCode) {
		JSONObject object = new JSONObject();
		List<BranchBankInfo>  branchBankList = commonInfoService.branchBankList(bank,channelCode);
		
		if(null!=branchBankList &&branchBankList.size()>0){
			object.put("result", "SUCCESS");
			object.put("branchBankList", branchBankList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "银行编号为空");
		}
		return object.toString();
		
	}
	
	/**
	 * 根据渠道查询行业类目信息
	 * @param channelCode 渠道号
	 * @param parentLevel 查询层级  levelOne 一级  levelTwo 二级  levelThree 三级
	 * @param parentText 上一级名称或编码 
	 * @return
	 */
	@RequestMapping(value = "/getIndustrieInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIndustrieInfo(String channelCode, String parentLevel, String parentText) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(channelCode)) {
			result.put("result", "FAIL");
			result.put("message", "渠道号不能为空！");
			return result;
		}
		if (StringUtils.isBlank(parentLevel)) {
			result.put("result", "FAIL");
			result.put("message", "经营类目层级不能为空！");
			return result;
		}
		if (!"levelOne".equals(parentLevel) && StringUtils.isBlank(parentText)) {
			result.put("result", "FAIL");
			result.put("message", "查询非一级类目上级名称或编码不能为空！");
			return result;
		}
		List<CommonIndustry>  commonIndustries = commonInfoService.selectCommonIndustrys(channelCode, parentLevel, parentText);
		result.put("result", "SUCCESS");
		result.put("data", commonIndustries);
		return result;
		
	}
	
	/**
	 */
	@RequestMapping(value = "/getIndustryNameByCategoryId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIndustryNameByCategoryId(Industry industry) {
		Map<String, Object> result = new HashMap<>();
		List<Industry> industryListByCategoryId = fmIncomeMapperDao.selSuiXingIndustryListByCategoryId(industry);
		result.put("result", "SUCCESS");
		result.put("data", industryListByCategoryId);
		return result;
	
	}
}
