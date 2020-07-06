package com.qifenqian.bms.basemanager.tradeRevoke.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.tradeRevoke.bean.CustTransRevoke;

@MapperScan
public interface CustTransRevokeMapper {
	
	public List<CustTransRevoke> selectTransRevokeList(CustTransRevoke queryBean);

	int insert(CustTransRevoke insertBean);

	int updateByAudit(CustTransRevoke updateBean);
	
	int updateByRevoke(CustTransRevoke updateRevoke);
	
	

}