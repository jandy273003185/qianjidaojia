package com.qifenqian.bms.basemanager.channel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.channel.bean.ChannelInfoBean;
import com.qifenqian.bms.basemanager.channel.mapper.ChannelMapper;

@Service
public class ChannelInfoService {
	@Autowired
	private ChannelMapper channelMapper;
	
	public List<ChannelInfoBean> getChannelInfoList(ChannelInfoBean queryBean) {
		
		return channelMapper.getChannelList(queryBean);
	}

	public void addChannelInfo(ChannelInfoBean channelInfoBean) {
		
		channelMapper.addChannelInfo(channelInfoBean);
	}

	public void updateChannelInfo(ChannelInfoBean channelInfoBean) {
		channelMapper.updateChannelInfo(channelInfoBean);
		
	}

	public void deleteChannelInfo(String channelId) {
		channelMapper.deleteChannelInfo(channelId);
		
	}
}
