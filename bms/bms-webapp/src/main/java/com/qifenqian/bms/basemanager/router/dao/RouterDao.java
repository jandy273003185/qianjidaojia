package com.qifenqian.bms.basemanager.router.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.router.bean.Servicechannel;
import com.qifenqian.bms.basemanager.router.mapper.RouterMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class RouterDao {
	
	@Autowired
	public RouterMapper routerMapper;
	
	@Page
	public List<Servicechannel> selectRouters(Servicechannel servicechannel){
		
		return routerMapper.selectRouters(servicechannel);
	}
	
	public int addRouters(Servicechannel queryBean){
		return routerMapper.addRouters(queryBean);
	}
	
	public int updateRouter(Servicechannel servicechannel){
		return routerMapper.updateRouter(servicechannel);
	}
	
}
