package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface TdMerchantChannelOperationMapper {

	public int insertMerchantChannel(TdMerchantChannel channel);
	
	public int updateMerchantChannel(TdMerchantChannel channel);
	
	public int deleteMerchantChannel(TdMerchantChannel channel);
}
