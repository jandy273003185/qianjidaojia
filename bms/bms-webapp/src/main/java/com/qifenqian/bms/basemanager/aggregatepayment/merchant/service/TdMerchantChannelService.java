package com.qifenqian.bms.basemanager.aggregatepayment.merchant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.dao.TdMerchantChannelDAO;

@Service
public class TdMerchantChannelService {
	
	private static Logger logger = LoggerFactory.getLogger(TdMerchantChannelService.class);
	
	@Autowired
	private TdMerchantChannelDAO tdMerchantChannelDAO;
	
	/**
	 * 查询商户渠道金额限制列表
	 * @param channel
	 * @return
	 */
	public List<TdMerchantChannel> selectMerchantChannel(TdMerchantChannel channel){
		return tdMerchantChannelDAO.selectMerchantChannel(channel);
	}
	
	/**
	 * 增加商户渠道金额限制
	 * @param channel
	 */
	public void insertMerchantChannel(TdMerchantChannel channel){
		try {
			tdMerchantChannelDAO.insertMerchantChannel(channel);
		} catch (Exception e) {
			logger.error("增加商户渠道金额限制异常:"+e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	/**
	 * 修改商户渠道金额限制
	 * @param channel
	 */
	public void  updateMerchantChannel(TdMerchantChannel channel){
		tdMerchantChannelDAO.updateMerchantChannel(channel);
	}
	
	/**
	 * 删除商户渠道金额限制
	 * @param channel
	 * @return
	 */
	public void  deleteMerchantChannel(TdMerchantChannel channel){
		try {
			tdMerchantChannelDAO.deleteMerchantChannel(channel);
		} catch (Exception e) {
			logger.error("删除商户渠道金额限制异常"+e.getMessage());
			throw e;
		}
		
	}
}
