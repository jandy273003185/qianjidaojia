package com.qifenqian.bms.basemanager.rechargeRevoke.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.rechargeRevoke.bean.RechargeRevoke;



@MapperScan
public interface RechargeRevokeMapper {

	/**
	 * 查询充值撤销信息
	 * 
	 * @param recharge
	 * @return
	 */
	public List<RechargeRevoke> selectRechargeRevokeList(RechargeRevoke rechargeRevoke);

	int insertRechargeRevoke(RechargeRevoke insertBean);
	
	int updateRechargeRevoke(RechargeRevoke updateBean);
	
	int updateByAudit(RechargeRevoke updateBean);
	
}
