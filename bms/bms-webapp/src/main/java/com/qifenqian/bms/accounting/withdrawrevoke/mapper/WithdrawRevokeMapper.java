package com.qifenqian.bms.accounting.withdrawrevoke.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawExcel;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;

@MapperScan
public interface WithdrawRevokeMapper {
	
	/**
	 * 查询提现撤销信息
	 * @param withdrawBean
	 * @return
	 */
	public List<Withdraw> selectWithdrawRevokeList(WithdrawRequestBean withdrawBean);
	
	/**
	 * 导出提现撤销信息
	 * @param withdrawRequestBean
	 * @return
	 */
	public List<WithdrawExcel> selectWithdrawRevokeExcel(WithdrawRequestBean withdrawRequestBean);
	

	
}
