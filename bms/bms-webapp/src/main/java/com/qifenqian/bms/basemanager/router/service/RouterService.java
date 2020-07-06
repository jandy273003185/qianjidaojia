package com.qifenqian.bms.basemanager.router.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.router.bean.Servicechannel;

@Service
public interface RouterService {
	
	
	
	//查询路由列表
	public List<Servicechannel> selectRouters(Servicechannel servicechannel);
	
	//新增路由
	public int addRouters(Servicechannel servicechannel);
	
	//更新路由
	public int updateRouter(Servicechannel servicechannel);
	
	
}
