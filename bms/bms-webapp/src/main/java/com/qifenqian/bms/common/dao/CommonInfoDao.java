package com.qifenqian.bms.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.common.bean.BranchBankInfo;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.bean.Province;

@Repository
public class CommonInfoDao {
	
	@Autowired
	private CommonInfoMapper  commonInfoMapper;
	
	/**
	 * 七分钱省份
	 * @return
	 */
	
	public List<Province> selectProvince() {
		return commonInfoMapper.selProvinceList();
	}
	/**
	 * 七分钱分行信息
	 * @param queryBean
	 * @return
	 */
	public List<BranchBankInfo> branchBankList(BranchBankInfo queryBean) {
		return commonInfoMapper.branchBankList(queryBean);
	}
	/**
	 * 查询随行付银行信息
	 * @return
	 */
	public List<Bank> selectSuiXingBanks() {
		return commonInfoMapper.selectSuiXingBanks();
	}
	/**
	 * 查询随行付开户行省份
	 * @return
	 */
	public List<Province> selSuiXingProvince() {
		return commonInfoMapper.selSuiXingProvince();
	}
	/**
	 * 查询随行付开户行城市
	 * @param provinceId
	 * @return
	 */
	public List<City> selSuiXingCity(String provinceId) {
		return commonInfoMapper.selSuiXingCity(provinceId);
	}
	/**
	 * 根据银行和城市查询渠道的分行
	 * @param queryBean
	 * @return
	 */
	public List<BranchBankInfo> suiXingBranchBankList(BranchBankInfo queryBean) {
		return commonInfoMapper.suiXingBranchBankList(queryBean);
	}
	
	/**
	 * 查询支付宝商户经营类目
	 * @return
	 */
	public List<CommonIndustry> ListAliPayIndustry(String parentLevel, String parentText) {
		return commonInfoMapper.ListAliPayIndustry(parentLevel, parentText);
	}
	
	/**
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<City> selWxChatAppCity(String provinceId) {
		return commonInfoMapper.selWxChatAppCity(provinceId);
	}
	public List<City> getWxAreaByCityId(String cityName) {
		return commonInfoMapper.getWxAreaByCityId(cityName);
	}
	
	public List<Map<String, String>> selectWeChatBankList(String bankName){
		return commonInfoMapper.selectWeChatBankList(bankName);
	}
	public List<City> selAllinPayCity(String provinceId) {
		return commonInfoMapper.selectAllinPayCity(provinceId);
	}
	public List<BranchBankInfo> allinPayBranchBankList(BranchBankInfo queryBean) {
		return commonInfoMapper.allinPayBranchBankList(queryBean);
	}
	public List<City> selLklPayCity(String provinceId) {
		return commonInfoMapper.selLklPayCity(provinceId);
	}
	


}
