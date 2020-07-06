package com.qifenqian.bms.merchant.reported.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantBankInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.mapper.WeChatAppMapper;


@Repository
public class WeChatAppMapperDao {

	@Autowired
	private WeChatAppMapper weChatAppMapper;
	
	public List<WeChatAppAreaInfo> getProvinceName() {
		
		return weChatAppMapper.getProvinceName();
	}
	
	public WeChatAppAreaInfo selectWxAreaInfo(String areaName) {
		return weChatAppMapper.selectWxAreaInfo(areaName);
	}
	
	public TdMerchantBankInfo getMerchantBankInfo(String custId) {
		return weChatAppMapper.getMerchantBankInfo(custId);
	}
	
	public City getTbSpCity(String cityId) {
		return weChatAppMapper.getTbSpCity(cityId);
	}
	
}
