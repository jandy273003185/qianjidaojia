package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

/**
 * 商户产品费率表
 * 
 * @project sevenpay-bms-web
 * @fileName TdMerchantChannelMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScanPayment
public interface TdMerchantProdRateSelectMapper {
	
	public List<TdMerchantProdRate> selectMerchantProdRate(TdMerchantProdRate bean);
	
	public int insertMerchantProdRate(TdMerchantProdRate bean);
}
