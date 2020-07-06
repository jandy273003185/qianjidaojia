package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

/**
 * 商户渠道交易金额限制
 * 
 * @project sevenpay-bms-web
 * @fileName TdMerchantChannelMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScanPayment
public interface TdMerchantChannelSelectMapper {
	
	public List<TdMerchantChannel> selectMerchantChannel(TdMerchantChannel channel);
}
