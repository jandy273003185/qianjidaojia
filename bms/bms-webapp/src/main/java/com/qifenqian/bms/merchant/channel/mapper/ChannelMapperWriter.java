package com.qifenqian.bms.merchant.channel.mapper;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;

@MapperScanCombinedmaster
public interface ChannelMapperWriter {
	
	/**
	 * 插入通道信息
	 * 仅插入通道信息 
	 * @param channel
	 * @return 插入多少条
	 */
	int insertChannel(ChannelBean channel);
	/**
	 * 插入通道信息
	 * 仅插入通道详细信息 
	 * @param channel
	 * @return 挺好入多少条
	 */
	int insertChannelDetail(ChannelBean channel);
	/**
	 * 删除通道
	 * 仅删除通道信息
	 * @param channel
	 * @return
	 */
	
	int deleteChannel(ChannelBean channel);
	
	/**
	 * 删除详细信息
	 * 仅删除通相许信息
	 * @param channel
	 * @return
	 */
	
	int deleteChannelDetail(@Param("merchantChannelNo")String merchantChannelNo,@Param("custId") String cust_id);
	/**
	 * 更新通道信息
	 * 仅更新通道信息
	 * @param channel
	 * @return
	 */
	int updateChannel(@Param("channel")ChannelBean channel, @Param("oldChannel")ChannelBean oldChannel);


	int activeChannel(ChannelBean bean);

	int activeChannelDetail(ChannelBean bean);

	int deactiveChannel(ChannelBean bean);

	int deactiveChannelDetail(ChannelBean bean);

}
