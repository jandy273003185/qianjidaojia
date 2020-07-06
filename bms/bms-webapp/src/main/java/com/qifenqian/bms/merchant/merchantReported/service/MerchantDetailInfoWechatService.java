package com.qifenqian.bms.merchant.merchantReported.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoWechat;
import com.qifenqian.bms.merchant.merchantReported.dao.MerchantDetailInfoWechatDao;

@Service
public class MerchantDetailInfoWechatService {
	
	private Logger logger = LoggerFactory.getLogger(MerchantDetailInfoWechatService.class);
	
	@Autowired
	private MerchantDetailInfoWechatDao merchantDetailInfoWechatDao;
	
	public MerchantDetailInfoWechat getMerchantDetailInfoWechat(MerchantDetailInfoWechat detail) {
	  return merchantDetailInfoWechatDao.selMerchantDetailInfoWechat(detail);
	}
}
