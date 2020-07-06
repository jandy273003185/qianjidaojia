package com.qifenqian.bms.merchant.merchantReported.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoAliPay;
import com.qifenqian.bms.merchant.merchantReported.mapper.MerchantDetailInfoAliPayMapper;

@Repository
public class MerchantDetailInfoAliPayDao {


	@Autowired
	private MerchantDetailInfoAliPayMapper merchantDetailInfoAliPayMapper;
	
	
	public MerchantDetailInfoAliPay selMerchantDetailInfoAliPay(MerchantDetailInfoAliPay detail) {
		 return merchantDetailInfoAliPayMapper.selMerchantDetailInfoAliPay(detail);
	}
	
	
}
