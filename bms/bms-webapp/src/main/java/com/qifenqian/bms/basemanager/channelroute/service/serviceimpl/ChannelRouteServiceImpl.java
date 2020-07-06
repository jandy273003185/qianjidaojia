package com.qifenqian.bms.basemanager.channelroute.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.basemanager.channelroute.dao.ChannelrouteDao;
import com.qifenqian.bms.basemanager.channelroute.mapper.ChannelRouteMapper;
import com.qifenqian.bms.basemanager.channelroute.service.ChannelRouteService;

@Service
public class ChannelRouteServiceImpl implements ChannelRouteService{
	@Autowired
	private ChannelrouteDao channelrouteDao; 
	
	@Autowired
	private ChannelRouteMapper channelRouteMapper;
	
	
	@Override
	public List<ChannelRouteBean> selectRoute(ChannelRouteBean channelrouteBean) {
		// TODO Auto-generated method stub
		return channelrouteDao.selectRoute(channelrouteBean);
	}
	
	@Override
	public int addChannelRoute(ChannelRouteBean channelrouteBean) {
		return channelrouteDao.addChannelRoute(channelrouteBean);
	}
	
	@Override
	public int updateChannelRoute(ChannelRouteBean channelrouteBean) {
		// TODO Auto-generated method stub
		return channelrouteDao.updateChannelRoute(channelrouteBean);
	}
	
	public void deleteChannelRoute(String id) {
		channelRouteMapper.deleteChannelRoute(id);
	}
	
	public int addRouteRf(ChannelRouteBean channelrouteBean){
		return channelrouteDao.addRouteRf(channelrouteBean);
		
	}
	
	@Override
	public List<ChannelRouteBean> selectId(String code ) {
		return channelrouteDao.selectId(code);
	}

	public void deleteRouteId(ChannelRouteBean channelrouteBean) {
		channelRouteMapper.deleteRouteId(channelrouteBean);
	}

	
	
}
