package com.qifenqian.bms.merchant.channel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.common.annotation.MapperScanPayment;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;

@MapperScanPayment
public interface ChannelMapperReader {

	List<ChannelBean> selectChannels(ChannelBean queryBean);

	List<ChannelDetailBean> selectChannelDetails(ChannelDetailBean queryBean);

	ChannelBean selectChannel(@Param("custId")String custId, @Param("channelCode")String channelCode, @Param("merNo")String merNo);

	List<ChannelDetailBean> selectChannelDetail(@Param("custId")String custId, @Param("merNo")String merNo);
}
