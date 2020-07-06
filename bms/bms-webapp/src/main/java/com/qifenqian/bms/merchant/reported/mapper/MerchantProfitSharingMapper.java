package com.qifenqian.bms.merchant.reported.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantSettleInfo;

@MapperScan
public interface MerchantProfitSharingMapper {
	/**
	 * 根据custId查询商户分账基础信息
	 * @param custId
	 * @return
	 */
	List<TdMerchantSettleInfo> ListTdMerchantSettleInfoByCustId(String custId);
}
