package com.qifenqian.bms.merchant.subAccountOrder.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;
import com.qifenqian.bms.merchant.subAccountOrder.mapper.MerchantSubAccountOrderMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductDAO.java
 * @memo
 */
@Repository
public class MerchantSubAccountOrderDao {
	
	@Autowired
	private MerchantSubAccountOrderMapper merchantSubAccountOrderMapper;

	/**
	 * 商户分账订单表
	 * 
	 * @return
	 */
	@Page
	public List<MerchantSubAccouontOrderBean> selectSubAccountOrderList(MerchantSubAccouontOrderBean merchantSubAccouontOrderBean) {
		return merchantSubAccountOrderMapper.selectSubAccountOrderList(merchantSubAccouontOrderBean);
	}
	
	

}
