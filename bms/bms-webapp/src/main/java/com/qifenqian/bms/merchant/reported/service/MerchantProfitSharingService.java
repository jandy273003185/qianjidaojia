package com.qifenqian.bms.merchant.reported.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantSettleInfo;
import com.qifenqian.bms.merchant.reported.dao.MerchantProfitSharingDao;

@Service
public class MerchantProfitSharingService {
	@Autowired
	private MerchantProfitSharingDao merchantProfitSharingDao;
	
	/**
	 * 根据custId查询商户分账基础信息
	 * @param custId
	 * @return
	 */
	public List<TdMerchantSettleInfo> ListTdMerchantSettleInfoByCustId(String custId) {
		return merchantProfitSharingDao.ListTdMerchantSettleInfoByCustId(custId);
	}
	
}
