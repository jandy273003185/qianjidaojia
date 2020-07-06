package com.qifenqian.bms.merchant.subAccount.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;
import com.qifenqian.bms.merchant.subAccount.mapper.MerchantSubAccountMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductDAO.java
 * @memo
 */
@Repository
public class MerchantSubAccountDao {
	
	@Autowired
	private MerchantSubAccountMapper merchantSubAccountMapper;

	/**
	 * 查询商户分账列表
	 * 
	 * @return
	 */
	@Page
	public List<MerchantSubAccouontBean> selectSubAccountList(MerchantSubAccouontBean merchantSubAccouontBean) {
		return merchantSubAccountMapper.selectSubAccountList(merchantSubAccouontBean);
	}
	
	public MerchantSubAccouontBean getSubAccountById(String relationId) {
		return merchantSubAccountMapper.getSubAccountById(relationId);
	}

	/**
	 * 商户分账新增
	 * @return
	 */
	public int insterSubAccount(MerchantSubAccouontBean merchantSubAccouont) {
		int i = merchantSubAccountMapper.insterSubAccount(merchantSubAccouont);
		return i;
	}
	
	public int updateSubAccount(MerchantSubAccouontBean merchantSubAccouont) {
		return merchantSubAccountMapper.updateSubAccount(merchantSubAccouont);
	}

}
