package com.qifenqian.bms.merchant.subAccountOrder.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;


/**
 * 
 * @author zhanggc  分账表
 *
 */
@MapperScan
public interface MerchantSubAccountOrderMapper {

	/**
	 * 查询商户分账列表
	 * 
	 * @return
	 */
	List<MerchantSubAccouontOrderBean> selectSubAccountOrderList(MerchantSubAccouontOrderBean merchantSubAccouontOrderBean);
	
	

}
