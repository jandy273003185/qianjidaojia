package com.qifenqian.bms.basemanager.aggregatepayment.merchant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.dao.TdMerchantProdRateDAO;

@Service
public class TdMerchantProdRateService {
	
	private static Logger logger = LoggerFactory.getLogger(TdMerchantProdRateService.class);
	
	@Autowired
	private TdMerchantProdRateDAO tdMerchantProdRateDAO;
	
	public List<TdMerchantProdRate> selectMerchantChannel(TdMerchantProdRate bean){
		return tdMerchantProdRateDAO.selectMerchantChannel(bean);
	}
	
	public void insertMerchantProdRate(TdMerchantProdRate bean){
		try {
			tdMerchantProdRateDAO.insertMerchantProdRate(bean);
		} catch (Exception e) {
			logger.error("增加商户产品费率异常"+e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 删除商户产品费率
	 * @param bean
	 */
	public void deleteMerchantProdRate(TdMerchantProdRate bean){
		try {
			tdMerchantProdRateDAO.deleteMerchantProdRate(bean);
		} catch (Exception e) {
			logger.error("删除商户产品费率");
			throw e;
		}
		
	}
}
