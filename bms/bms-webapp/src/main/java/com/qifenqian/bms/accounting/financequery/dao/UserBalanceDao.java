package com.qifenqian.bms.accounting.financequery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.financequery.bean.UserBalance;
import com.qifenqian.bms.accounting.financequery.mapper.UserBalanceMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class UserBalanceDao {
	@Autowired
	private UserBalanceMapper mapper;	
	@Page
	public List<UserBalance>selectUserBalanceList(UserBalance userBalance){
		return mapper.selectUserBalanceList(userBalance);
	} 
}
