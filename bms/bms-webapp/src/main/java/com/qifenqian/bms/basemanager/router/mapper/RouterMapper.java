package com.qifenqian.bms.basemanager.router.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.router.bean.Servicechannel;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;



@MapperScanCombinedmaster
public interface RouterMapper {
	//查询路由列表
	public List<Servicechannel> selectRouters(Servicechannel servicechannel);
	
	//新增路由
	public int addRouters(Servicechannel servicechannel);
	
	//更新
	public int updateRouter(Servicechannel servicechannel);
	
	//删除
	public void deleteRouterById(@Param("id") String id);
}
