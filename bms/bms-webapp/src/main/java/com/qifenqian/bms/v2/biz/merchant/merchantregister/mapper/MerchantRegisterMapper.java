package com.qifenqian.bms.v2.biz.merchant.merchantregister.mapper;


import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface MerchantRegisterMapper {
	
	/**
	  * @description 商户信息展现  
	  * @param [merchantVo]
	  * @return java.util.List<com.qifenqian.bms.basemanager.merchant.bean.MerchantVo> 
	  * @author ShiLi
	  * @date 2020/4/27 14:56 
	  */
	public List<MerchantVo> merchantRegisterList(MerchantVo merchantVo);
	
	/**
	  * @description 商户信息展现(登录客户经理所属)
	  * @param [merchantVo]
	  * @return java.util.List<com.qifenqian.bms.basemanager.merchant.bean.MerchantVo> 
	  * @author ShiLi
	  * @date 2020/4/27 14:57
	  */
	 
	public List<MerchantVo> myMerchantRegisterList(MerchantVo merchantVo);


	/**
	 * 查找商户
	 * @param custId
	 * @return
	 */
	public MerchantVo findMerchantInfo(String custId);
}
