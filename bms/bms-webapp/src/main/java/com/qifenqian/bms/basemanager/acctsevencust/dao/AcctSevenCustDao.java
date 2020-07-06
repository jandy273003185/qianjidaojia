package com.qifenqian.bms.basemanager.acctsevencust.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.mapper.AcctSevenCustMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AcctSevenCustDao {
	@Autowired
	private AcctSevenCustMapper acctSevenCustMapper;
	@Page
	public List<AcctSevenCust> getAcctSevenCust(AcctSevenCust queryBean) {
		
		return acctSevenCustMapper.getAcctSevenCust(queryBean);
	}
}
