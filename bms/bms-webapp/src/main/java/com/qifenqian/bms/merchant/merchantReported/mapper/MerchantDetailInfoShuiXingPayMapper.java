package com.qifenqian.bms.merchant.merchantReported.mapper;
import org.mybatis.spring.annotation.MapperScan;
import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoShuiXingPay;

@MapperScan
public interface MerchantDetailInfoShuiXingPayMapper {
	
	public MerchantDetailInfoShuiXingPay selMerchantDetailInfoShuiXingPay(MerchantDetailInfoShuiXingPay detail);
		

}
