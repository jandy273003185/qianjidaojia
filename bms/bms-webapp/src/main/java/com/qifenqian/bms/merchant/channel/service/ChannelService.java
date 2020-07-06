package com.qifenqian.bms.merchant.channel.service;

import java.util.List;

import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;

public interface ChannelService {

	/**
	 * 查询商户通道信息
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ChannelBean> getChannels(ChannelBean queryBean);

	/**
	 * 查询商户通道已开产品
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ChannelDetailBean> getChannelDetails(ChannelDetailBean queryBean);

	/**
	 * 根据商户ID查询出 通道信息
	 * 
	 * @param merchantId
	 * @return
	 */
	ChannelBean getChannel(String custId, String channelCode, String merChannerId);

	/**
	 * 保存或者更新一个通道信息, oldBean 为空表示 更新.
	 * 
	 * @param bean
	 * @return
	 */
	boolean saveOrupdateChannel(ChannelBean bean, ChannelBean oldBean);

	/**
	 * 激活一个通道
	 * 
	 * @param bean
	 * @return
	 */
	boolean activeChannel(ChannelBean bean);

	/**
	 * 下线一个通道
	 * 
	 * @param bean
	 * @return
	 */
	boolean deactiveChannel(ChannelBean bean);

}