package com.qifenqian.bms.basemanager.channel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.channel.bean.ChannelControlBean;
import com.qifenqian.bms.basemanager.channel.bean.ChannelInfoBean;

@MapperScan
public interface ChannelMapper {
	public List<ChannelInfoBean> getChannelList(ChannelInfoBean channelInfoBean);
	public void addChannelInfo(ChannelInfoBean channelInfoBean);
	public void updateChannelInfo(ChannelInfoBean channelInfoBean);
	public void deleteChannelInfo(@Param("channelId")String channelId);
	
	public List<ChannelControlBean> getChannelControlList(
			ChannelControlBean queryBean);
	public void addChannelControl(ChannelControlBean channelControlBean);
	public void updateChannelControl(ChannelControlBean channelControlBean);
	public void deleteChannelControl(@Param("channel")String channel,@Param("mchId")String mchId);
}
