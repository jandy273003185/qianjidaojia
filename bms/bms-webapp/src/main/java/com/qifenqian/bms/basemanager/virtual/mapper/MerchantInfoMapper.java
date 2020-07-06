package com.qifenqian.bms.basemanager.virtual.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.virtual.bean.AgentInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantInfo;

@MapperScan
public interface MerchantInfoMapper {

	public List<MerchantInfo> getInfos(MerchantInfo info) ;
	
	public void updateInfos(MerchantInfo info);
	
	public List<AgentInfo> getAgentInfo(AgentInfo info);
	
	public List<MerchantInfo> getDataInfos() ;
}
