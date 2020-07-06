package com.qifenqian.bms.merchant.subAccountOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;
import com.qifenqian.bms.merchant.subAccountOrder.dao.MerchantSubAccountOrderDao;
@Service
public class MerchantSubAccouontOrderService {

	@Autowired
	private MerchantSubAccountOrderDao merchantSubAccountOrderDao;
	
	/**
	 * 查询商户分账
	 * @param merchantSubAccouontBean
	 * @return
	 */
	public List<MerchantSubAccouontOrderBean> selectSubAccountOrderList(MerchantSubAccouontOrderBean merchantSubAccouontOrderBean) {
		//merchantSubAccouontBean.setStatus("00");
		return merchantSubAccountOrderDao.selectSubAccountOrderList(merchantSubAccouontOrderBean);
	}
	
}
