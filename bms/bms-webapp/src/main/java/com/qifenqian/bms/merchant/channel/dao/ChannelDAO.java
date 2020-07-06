package com.qifenqian.bms.merchant.channel.dao;

import java.util.List;

import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;

/**
 * 
 * @author Sun Xun
 *
 */
public interface ChannelDAO {

	/**
	 * 根据分页查询通道信息集合
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<ChannelBean> selectChannels(ChannelBean queryBean);

	/**
	 * 根据分页查询通道详细信息集合
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<ChannelDetailBean> selectChannelDetails(ChannelDetailBean queryBean);

	/**
	 * 查询某个商户的一条通道通道
	 * 
	 * @param custId
	 * @param channelCode
	 * @param merNo
	 *            商户在渠道的商户号
	 * @return
	 */

	ChannelBean selectChannel(String custId, String channelCode, String merNo);

	/**
	 * 查询某个商户的 渠道产品
	 * 
	 * @param custId
	 * @param merNo
	 *            商户在渠道的商户号
	 * @return
	 */
	List<ChannelDetailBean> selectChannelDetail(String custId, String merNo);

	/**
	 * 插入一个通道信息, 默认状态为 准备中
	 * 仅插入通道信息
	 * @param channel
	 * @return
	 */
	boolean insertChannel(ChannelBean channel);
	
	
	/**
	 * 插入一个通道详细信息, 默认状态为 准备中
	 * 仅插入通道详细
	 * @param channel
	 * @return
	 */
	boolean insertChannelDetail(ChannelBean channel);
	
	/**
	 * 插入一个通道信息, 默认状态为 准备中
	 * 仅插入通道信息
	 * @param channel
	 * @return
	 */
	boolean deleteChannel(ChannelBean channel);
	
	/**
	 * 删除一个通道详细信息, 默认状态为 准备中
	 * 仅插入通道详细
	 * @param channel
	 * @return
	 */
	boolean deleteChannelDetail(ChannelBean channel);

	/**
	 * 更新一个通道信息.
	 * 先更新通道信息
	 * 如果 通道里面的详细信息不为空 就先删除以前老的详细信息,再插入新的详细信息h
	 * @param channel
	 * @param oldBean
	 * @return
	 */
	boolean updateChannel(ChannelBean channel, ChannelBean oldBean);

	/**
	 * 激活通道
	 * 把通道信息 和 通道详细信息 的状态 修改为 00
	 * @param bean
	 * @return
	 */
	boolean activeChannel(ChannelBean bean);

	/**
	 * 让某个通道下线
	 * 把通道信息 和 通道详细信息 的状态 修改为 09 并清除 redis缓存
	 * @param bean
	 * @return
	 */
	boolean deactiveChannel(ChannelBean bean);
	
	
	/**
	 * 激活通道详细信息
	 * 把通道信息 和 通道详细信息 的状态 修改为 00
	 * @param bean
	 * @return
	 */
	boolean activeChannelDetail(ChannelBean bean);

	/**
	 * 让某个通道详细信息下线
	 * 把通道信息 和 通道详细信息 的状态 修改为 09 并清除 redis缓存
	 * @param bean
	 * @return
	 */
	boolean deactiveChannelDetail(ChannelBean bean);
	/**
	 * 删除某个通道在redis的信息
	 * @param bean
	 * @return
	 */
	boolean deleteChannelFoRedis(ChannelBean bean);

}
