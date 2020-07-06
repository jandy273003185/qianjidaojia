package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

/**
 * 商户产品费率表
 * 
 * @project sevenpay-bms-web
 * @fileName TdMerchantChannelMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScanCombinedmaster
public interface TdMerchantProdRateOperationMapper {
	
	public int insertMerchantProdRate(TdMerchantProdRate bean);
	
	public int deleteMerchantProdRate(TdMerchantProdRate bean);
}
