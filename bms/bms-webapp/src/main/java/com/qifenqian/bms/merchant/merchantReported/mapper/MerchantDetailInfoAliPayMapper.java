package com.qifenqian.bms.merchant.merchantReported.mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoAliPay;

@MapperScan
public interface MerchantDetailInfoAliPayMapper {
	
	public MerchantDetailInfoAliPay selMerchantDetailInfoAliPay(MerchantDetailInfoAliPay detail);
		

}
