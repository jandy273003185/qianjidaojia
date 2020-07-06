package com.qifenqian.bms.accounting.financequery.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.financequery.bean.UserBalance;

@MapperScan
public interface UserBalanceMapper {
	public List<UserBalance>selectUserBalanceList(UserBalance userBalance);
	
	public UserBalance selectSumBalance();
	
}
