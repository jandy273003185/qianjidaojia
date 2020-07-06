package com.qifenqian.bms.basemanager.router.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.router.bean.Servicechannel;
import com.qifenqian.bms.basemanager.router.dao.RouterDao;
import com.qifenqian.bms.basemanager.router.mapper.RouterMapper;
import com.qifenqian.bms.basemanager.router.service.RouterService;


@Service
public class RouterServiceImpl implements RouterService{
	@Autowired
	private RouterDao routerDao; 
	
	@Autowired
	private RouterMapper routerMapper;
	//路由列表
	@Override
	public List<Servicechannel> selectRouters(Servicechannel servicechannel) {
		// TODO Auto-generated method stub
		return routerDao.selectRouters(servicechannel);
	}

	//新增路由
	@Override
	public int addRouters(Servicechannel servicechannel) {
		// TODO Auto-generated method stub
		return routerDao.addRouters(servicechannel);
	}

	//更新路由
	@Override
	public int updateRouter(Servicechannel servicechannel) {
		// TODO Auto-generated method stub
		return routerDao.updateRouter(servicechannel);
	}
	
	//按id删除
	public void deleteRouterById(String id){
		routerMapper.deleteRouterById(id);
	}
}
