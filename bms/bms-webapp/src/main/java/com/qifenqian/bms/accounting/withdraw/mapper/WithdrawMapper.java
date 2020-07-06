package com.qifenqian.bms.accounting.withdraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawExcel;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;

@MapperScan
public interface WithdrawMapper {
	
	/**
	 * 查询消费者信息
	 * @param withdrawBean
	 * @return
	 */
	public List<Withdraw> selectCustomerWithdrawList(WithdrawRequestBean withdrawBean);
	
	/**
	 * 导出客户提现信息
	 * @param withdrawRequestBean
	 * @return
	 */
	public List<WithdrawExcel> selectWithdrawExcelByUser(WithdrawRequestBean withdrawRequestBean);
	
	/**
	 * 审核
	 * @param withdraw
	 * 
	 */
	public int custWithdrawAudit(Withdraw withdraw);
	
	/**
	 * 核销
	 * @param withdraw
	 */
	public int withdrawVerification(WithdrawRequestBean withdraw);
	
	
	/**
	 * 修改用户提现流水表核销状态 -Subject
	 * @param id
	 */
	public void updateCustomerWithdraw(List<String> id );
	
	
	public String getAcctIdByCustId(@Param("custId") String custId);
	
}
