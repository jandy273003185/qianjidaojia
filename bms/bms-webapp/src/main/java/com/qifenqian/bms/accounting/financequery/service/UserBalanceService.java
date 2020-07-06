package com.qifenqian.bms.accounting.financequery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.financequery.bean.UserBalance;
import com.qifenqian.bms.accounting.financequery.dao.UserBalanceDao;
import com.qifenqian.bms.accounting.financequery.mapper.UserBalanceMapper;

@Service
public class UserBalanceService {
	@Autowired
	private UserBalanceDao dao;

	@Autowired
	private UserBalanceMapper userBalanceMapper;
	
	
	public List<UserBalance> exportUserBalanceList(UserBalance userBalance) {
		return userBalanceMapper.selectUserBalanceList(userBalance);
	}
	
	public List<UserBalance> selectUserBalanceList(UserBalance userBalance) {
		return dao.selectUserBalanceList(userBalance);
	}
	
}
