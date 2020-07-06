package com.qifenqian.bms.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.dao.BankDAO;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.mapper.CityMapper;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.common.dao.CommonInfoDao;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.bean.Province;

@Service
public class CommonInfoService {
	
	private Logger logger = LoggerFactory.getLogger(CommonInfoService.class);
	
	@Autowired
	private BankDAO bankDAO;
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private CommonInfoDao commonInfoDao;
	
	/**
	 * 查询所有银行信息
	 * @return
	 */
	public List<Bank> selectBanks(Bank bank,String channelCode){
		List<Bank>  bankList = new ArrayList<Bank>();
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			bankList = bankDAO.selectBanks(bank);
		}else if("SUIXING_PAY".equals(channelCode)) {
			bankList = commonInfoDao.selectSuiXingBanks();
		}
		return bankList;
	}
	
	public List<Map<String, String>> selectWeChatBankList(String bankName) {
		return commonInfoDao.selectWeChatBankList(bankName);
	}
	
	/**
	 * 查找所有省份
	 * @param city
	 * @return
	 */
	public List<Province> selectAllProvince(String channelCode) {
		List<Province> provinceList = new ArrayList<Province>();
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			provinceList = commonInfoDao.selectProvince();
		}else if("SUIXING_PAY".equals(channelCode)) {
			provinceList = commonInfoDao.selSuiXingProvince();
		}
		return provinceList;
	}

	/***
	 * 根据provinceId查找城市
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<City> getCityByProvinceId(String provinceId,String channelCode) {
		List<City> cityList = new ArrayList<City>();
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			cityList = cityMapper.getCityByProvinceId(provinceId);
		}else if("SUIXING_PAY".equals(channelCode)) {
			cityList = commonInfoDao.selSuiXingCity(provinceId);
		}else if("WX".equals(channelCode)) {
			cityList = commonInfoDao.selWxChatAppCity(provinceId);
		}else if("ALLIN_PAY".equals(channelCode)) {
			cityList = commonInfoDao.selAllinPayCity(provinceId);
		}else if("LKL".equals(channelCode)) {
			cityList = commonInfoDao.selLklPayCity(provinceId);
		}
		return cityList;
		
	}
	
	
	/***
	 * 根据cityId查找区/县
	 * 
	 * @param cityId
	 * @return
	 */
	public List<City> getAreaByCityId(City cityBean,String channelCode) {
		List<City> areaList = new ArrayList<City>();
		String cityId = Integer.toString(cityBean.getCityId());
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			areaList = cityMapper.getAreaByCityId(cityId);
		}else if("WX".equals(channelCode)) {
			areaList = commonInfoDao.getWxAreaByCityId(cityBean.getCityName());
		}else if("LKL".equals(channelCode)) {
			areaList = commonInfoDao.selLklPayCity(cityBean.getAreaCode());
		}
		return areaList;
	}
	
	/**
	 * 根据银行和城市查询渠道的分行
	 */
	public List<BranchBankInfo> branchBankList(BranchBankInfo queryBean,String channelCode) {
		List<BranchBankInfo>  branchBankList = new ArrayList<BranchBankInfo>();
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			branchBankList = commonInfoDao.branchBankList(queryBean);
			if(0 == branchBankList.size()) {
				queryBean.setCityCode(-1);
				branchBankList = commonInfoDao.branchBankList(queryBean);
			}
		}else if("SUIXING_PAY".equals(channelCode)) {
			branchBankList = commonInfoDao.suiXingBranchBankList(queryBean);
		}else if("ALLIN_PAY".equals(channelCode)) {
			branchBankList = commonInfoDao.allinPayBranchBankList(queryBean);
		}
		return branchBankList;
	}
	
	/**
	 * 查找商家经营类目
	 * @param channelCode 渠道号
	 * @param parentLevel 查询层级  levelOne 一级  levelTwo 二级  levelThree 三级
	 * @param parentText 上一级名称或编码 
	 * @return
	 */
	public List<CommonIndustry> selectCommonIndustrys(String channelCode, String parentLevel, String parentText) {
		List<CommonIndustry> industries = new ArrayList<CommonIndustry>();
		//七分钱
		if(StringUtils.isBlank(channelCode)) {
			//provinceList = commonInfoDao.selectProvince();
		}
		else if ("ALIPAY".equals(channelCode)) {
			industries = commonInfoDao.ListAliPayIndustry(parentLevel, parentText);
		}
		else if("SUIXING_PAY".equals(channelCode)) {
			//provinceList = commonInfoDao.selSuiXingProvince();
		}
		return industries;
	}
}
