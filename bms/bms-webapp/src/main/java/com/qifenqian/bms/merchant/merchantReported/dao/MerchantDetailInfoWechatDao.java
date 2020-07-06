package com.qifenqian.bms.merchant.merchantReported.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoWechat;
import com.qifenqian.bms.merchant.merchantReported.mapper.MerchantDetailInfoWechatMapper;

@Repository
public class MerchantDetailInfoWechatDao {


	@Autowired
	private MerchantDetailInfoWechatMapper merchantDetailInfoWechatMapper;
	
	/**
	 * @param detail zhanggc 查询微信报备详情表
	 * @return
	 */
	public MerchantDetailInfoWechat selMerchantDetailInfoWechat(MerchantDetailInfoWechat detail) {
		 return merchantDetailInfoWechatMapper.selMerchantDetailInfoWechat(detail);
	}
	
	
}
