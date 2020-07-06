package com.qifenqian.bms.accounting.acctseveninner.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.acctseveninner.bean.AcctSevenInner;
import com.qifenqian.bms.accounting.acctseveninner.mapper.AcctSevenInnerMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AcctSevenInnerDao {
	@Autowired
	private AcctSevenInnerMapper acctSevenInnerMapper;

	@Page
	public List<AcctSevenInner> queryAcctSevenInnerList(AcctSevenInner queryBean) {
		return acctSevenInnerMapper.queryAcctSevenInnerList(queryBean);
	}

}
