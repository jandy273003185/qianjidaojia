package com.qifenqian.bms.merchant.merchantReported.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoAliPay;
import com.qifenqian.bms.merchant.merchantReported.dao.MerchantDetailInfoAliPayDao;

@Service
public class MerchantDetailInfoAliPayService {
	
	private Logger logger = LoggerFactory.getLogger(MerchantDetailInfoAliPayService.class);
	
	@Autowired
	private MerchantDetailInfoAliPayDao merchantDetailInfoAliPayDao;
	
	public MerchantDetailInfoAliPay getMerchantDetailInfoAliPay(MerchantDetailInfoAliPay detail) {
	  return merchantDetailInfoAliPayDao.selMerchantDetailInfoAliPay(detail);
	}
}
