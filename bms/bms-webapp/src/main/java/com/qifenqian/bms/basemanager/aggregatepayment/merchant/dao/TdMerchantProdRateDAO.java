package com.qifenqian.bms.basemanager.aggregatepayment.merchant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantProdRateOperationMapper;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantProdRateSelectMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class TdMerchantProdRateDAO {

	@Autowired
	private TdMerchantProdRateSelectMapper tdMerchantProdRateSelectMapper;
	
	@Autowired
	private TdMerchantProdRateOperationMapper tdMerchantProdRateOperationMapper;
	
	/**
	 * 查询商户产品费率
	 * @param channel
	 * @return
	 */
	@Page
	public List<TdMerchantProdRate> selectMerchantChannel(TdMerchantProdRate bean){
		return tdMerchantProdRateSelectMapper.selectMerchantProdRate(bean);
	}
	
	/**
	 * 增加商户产品费率
	 * @param bean
	 * @return
	 */
	public int insertMerchantProdRate(TdMerchantProdRate bean){
		return tdMerchantProdRateOperationMapper.insertMerchantProdRate(bean);
	}
	
	/**
	 * 删除商户产品费率
	 * @param bean
	 * @return
	 */
	public int deleteMerchantProdRate(TdMerchantProdRate bean){
		return tdMerchantProdRateOperationMapper.deleteMerchantProdRate(bean);
	}
}
