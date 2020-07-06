package com.qifenqian.bms.basemanager.aggregatepayment.merchant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantCollectDaily;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.dao.TdMerchantCollectDailyDAO;

@Service
public class TdMerchantCollectDailyService {
	
	private static Logger logger = LoggerFactory.getLogger(TdMerchantCollectDailyService.class);
	@Autowired
	private TdMerchantCollectDailyDAO tdMerchantCollectDailyDAO;
	
	/**
	 * 查询商户每日汇集信息
	 * @param collectDaily
	 * @return
	 */
	public List<TdMerchantCollectDaily> selectMerchantCollectDaily(TdMerchantCollectDaily collectDaily){
		List<TdMerchantCollectDaily> results = null;
		try {
			results = tdMerchantCollectDailyDAO.selectMerchantCollectDaily(collectDaily);
		} catch (Exception e) {
			logger.error("查询商户每日汇集信息异常"+e.getMessage());
			throw e;
		}
		return results;
	}
}
