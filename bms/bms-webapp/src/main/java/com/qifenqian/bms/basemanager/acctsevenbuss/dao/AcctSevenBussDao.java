package com.qifenqian.bms.basemanager.acctsevenbuss.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AcctSevenBussDao {
	@Autowired
	private AcctSevenBussMapper acctSevenBussMapper;

	@Page
	public List<AcctSevenBuss> queryAcctSevenBuss(AcctSevenBuss queryBean) {
		return acctSevenBussMapper.queryAcctSevenBuss(queryBean);
	}

}
