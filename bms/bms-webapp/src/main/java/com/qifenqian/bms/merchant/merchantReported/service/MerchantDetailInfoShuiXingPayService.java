package com.qifenqian.bms.merchant.merchantReported.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoShuiXingPay;
import com.qifenqian.bms.merchant.merchantReported.dao.MerchantDetailInfoShuiXingPayDao;

@Service
public class MerchantDetailInfoShuiXingPayService {
	
	private Logger logger = LoggerFactory.getLogger(MerchantDetailInfoShuiXingPayService.class);
	
	@Autowired
	private MerchantDetailInfoShuiXingPayDao merchantDetailInfoShuiXingPayDao;
	
	public MerchantDetailInfoShuiXingPay getMerchantDetailInfoShuiXingPay(MerchantDetailInfoShuiXingPay detail) {
	  return merchantDetailInfoShuiXingPayDao.selMerchantDetailInfoShuiXingPay(detail);
	}
}
