package com.qifenqian.bms.basemanager.channelroute.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;

@Service
public interface ChannelRouteService {
	//查询路由列表
	public List<ChannelRouteBean> selectRoute(ChannelRouteBean channelrouteBean);
	
	//添加路由列表
	public int addChannelRoute(ChannelRouteBean channelrouteBean);
	
	//修改路由列表
	public int updateChannelRoute(ChannelRouteBean channelrouteBean);


	//添加渠道关系
	public int addRouteRf(ChannelRouteBean channelrouteBean);
	
	//查找渠道Id
	public List<ChannelRouteBean> selectId(String code);
	
	
	
	
	
}
