package com.qifenqian.bms.basemanager.channelroute.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.basemanager.channelroute.mapper.ChannelRouteMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ChannelrouteDao {
	@Autowired
	public ChannelRouteMapper channelRouteMapper;
	
	@Page
	public List<ChannelRouteBean> selectRoute(ChannelRouteBean channelrouteBean){
		
		return channelRouteMapper.selectRoute(channelrouteBean);
	}
	

	public int addChannelRoute(ChannelRouteBean channelRouteBean){
		
		return channelRouteMapper.addChannelRoute(channelRouteBean);
	}
	

	public int updateChannelRoute(ChannelRouteBean channelrouteBean){
		
		return channelRouteMapper.updateChannelRoute(channelrouteBean);
	}
	
	public int addRouteRf(ChannelRouteBean channelrouteBean){
		return channelRouteMapper.addRouteRf(channelrouteBean);
		
	}

	
	public List<ChannelRouteBean> selectId(String code){
		
		return channelRouteMapper.selectId(code);
	}
}
