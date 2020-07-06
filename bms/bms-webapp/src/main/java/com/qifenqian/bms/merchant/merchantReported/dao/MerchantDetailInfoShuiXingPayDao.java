package com.qifenqian.bms.merchant.merchantReported.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoShuiXingPay;
import com.qifenqian.bms.merchant.merchantReported.mapper.MerchantDetailInfoShuiXingPayMapper;

@Repository
public class MerchantDetailInfoShuiXingPayDao {


	@Autowired
	private MerchantDetailInfoShuiXingPayMapper mrchantDetailInfoShuiXingPayMapper;
	
	
	public MerchantDetailInfoShuiXingPay selMerchantDetailInfoShuiXingPay(MerchantDetailInfoShuiXingPay detail) {
		 return mrchantDetailInfoShuiXingPayMapper.selMerchantDetailInfoShuiXingPay(detail);
	}
	
	
}
