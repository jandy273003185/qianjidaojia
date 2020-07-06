package com.qifenqian.bms.basemanager.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName BankDAO.java
 * @author Dayet
 * @date 2015年5月13日
 * @memo 
 */
@Repository
public class BankDAO{

	@Autowired
	private BankMapper BankMapper;
	
	/**
	 * 分页查询银行列表
	 * @return
	 */
	@Page
	public List<Bank> selectBanks(Bank bank) {
		return BankMapper.selectBanks(bank);
	}
}


