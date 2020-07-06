package com.qifenqian.bms.merchant.reported.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantSettleInfo;
import com.qifenqian.bms.merchant.reported.mapper.MerchantProfitSharingMapper;

@Repository
public class MerchantProfitSharingDao {
	@Autowired
	private MerchantProfitSharingMapper merchantProfitSharingMapper;
	
	public List<TdMerchantSettleInfo> ListTdMerchantSettleInfoByCustId(String custId) {
		return merchantProfitSharingMapper.ListTdMerchantSettleInfoByCustId(custId);
	}
}
