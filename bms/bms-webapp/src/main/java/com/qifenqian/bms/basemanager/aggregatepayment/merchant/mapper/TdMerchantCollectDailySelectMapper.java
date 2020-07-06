package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantCollectDaily;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

/**
 * 商户每日汇集表
 * 
 * @project sevenpay-bms-web
 * @fileName TdMerchantChannelMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScanPayment
public interface TdMerchantCollectDailySelectMapper {
	
	public List<TdMerchantCollectDaily> selectMerchantCollectDaily(TdMerchantCollectDaily collectDaily);
}
