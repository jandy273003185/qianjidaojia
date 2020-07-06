package com.qifenqian.bms.merchant.merchantReported.mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoWechat;

@MapperScan
public interface MerchantDetailInfoWechatMapper {
	/**
	 * @param detail zhanggc 查询微信报备详情表
	 * @return
	 */
	public MerchantDetailInfoWechat selMerchantDetailInfoWechat(MerchantDetailInfoWechat detail);
		

}
