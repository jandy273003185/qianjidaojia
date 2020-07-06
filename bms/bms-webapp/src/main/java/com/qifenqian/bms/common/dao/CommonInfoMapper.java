package com.qifenqian.bms.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.bean.Province;

@MapperScan	
public interface CommonInfoMapper {

	public List<Province> selProvinceList();
	
	public List<BranchBankInfo> branchBankList(BranchBankInfo queryBean);
	
	public List<Bank> selectSuiXingBanks();

	public List<Province> selSuiXingProvince();

	public List<City> selSuiXingCity(@Param("provinceId")String provinceId);

	public List<BranchBankInfo> suiXingBranchBankList(BranchBankInfo queryBean);
	
	/**
	 * 查询支付宝商户经营类目
	 * @return
	 */
	List<CommonIndustry> ListAliPayIndustry(@Param("parentLevel")String parentLevel, @Param("parentText")String parentText);

	public List<City> selWxChatAppCity(@Param("provinceId") String provinceId);

	public List<City> getWxAreaByCityId(@Param("cityName") String cityName);
	
	public List<Map<String, String>> selectWeChatBankList(String bankName);

	public List<City> selectAllinPayCity(String provinceId);

	public List<BranchBankInfo> allinPayBranchBankList(BranchBankInfo queryBean);

	public List<City> selLklPayCity(String provinceId);
	
}
