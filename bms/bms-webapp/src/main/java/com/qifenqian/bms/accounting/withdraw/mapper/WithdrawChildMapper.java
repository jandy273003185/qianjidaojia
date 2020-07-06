package com.qifenqian.bms.accounting.withdraw.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.withdraw.bean.WithdrawChild;

@MapperScan
public interface WithdrawChildMapper {

	/**
	 * 保存核心返回信息
	 * @param withdrawChild
	 */
	public void saveWithdrawChild(WithdrawChild withdrawChild);
	
	/**
	 * 查询用户提现核销流水
	 * @return
	 */
	public List<WithdrawChild> selectCustomerVerification();
	
	/**
	 * 查询商户提现核销流水
	 * @return
	 */
	public List<WithdrawChild> selectMerchantVerification();
	
	/**
	 * 修改核心返回信息
	 * @param withdrawChild
	 * @return
	 */
	int updateWithdrawChild(WithdrawChild withdrawChild); 
	

}
