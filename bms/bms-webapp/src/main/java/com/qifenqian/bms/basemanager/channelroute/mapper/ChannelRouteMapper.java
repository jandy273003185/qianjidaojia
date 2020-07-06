package com.qifenqian.bms.basemanager.channelroute.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface ChannelRouteMapper {
	//查询路由列表
	public List<ChannelRouteBean> selectRoute(ChannelRouteBean channelrouteBean);
	
	//添加
	public int addChannelRoute(ChannelRouteBean channelrouteBean);
	
	//修改
	public int updateChannelRoute(ChannelRouteBean channelrouteBean);

	//删除
	public void deleteChannelRoute(String id);

	
	
	//添加渠道关系
	public int addRouteRf(ChannelRouteBean channelrouteBean);
	
	//查找渠道Id
	public List<ChannelRouteBean> selectId(String code);
	
	//删除渠道Id
	public void deleteRouteId(ChannelRouteBean channelrouteBean);


}
