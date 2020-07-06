package com.qifenqian.bms.merchant.subAccount.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;


/**
 * 
 * @author Lx
 *
 */
@MapperScan
public interface MerchantSubAccountMapper {

	/**
	 * 查询商户分账列表
	 * 
	 * @return
	 */
	List<MerchantSubAccouontBean> selectSubAccountList(MerchantSubAccouontBean merchantSubAccouontBean);
	
	MerchantSubAccouontBean getSubAccountById(String relationId);

	int insterSubAccount(MerchantSubAccouontBean merchantSubAccouont);
	
	int updateSubAccount(MerchantSubAccouontBean merchantSubAccouont);

}
