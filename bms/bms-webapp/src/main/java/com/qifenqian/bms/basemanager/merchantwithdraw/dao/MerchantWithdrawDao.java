package com.qifenqian.bms.basemanager.merchantwithdraw.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.basemanager.merchantwithdraw.mapper.MerchantWithdrawMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class MerchantWithdrawDao {
	
	@Autowired
	private MerchantWithdrawMapper merchantWithdrawMapper;
	@Page
	public List<MerchantWithdraw> selectMerchantWithdrawList(MerchantWithdraw withdrawBean) {
		return merchantWithdrawMapper.selectMerchantWithdrawList(withdrawBean);
	}

}
