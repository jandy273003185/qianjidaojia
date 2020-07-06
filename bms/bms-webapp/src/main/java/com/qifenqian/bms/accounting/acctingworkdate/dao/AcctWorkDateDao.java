package com.qifenqian.bms.accounting.acctingworkdate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.acctingworkdate.bean.AcctWorkDate;
import com.qifenqian.bms.accounting.acctingworkdate.mapper.AcctWorkDateMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class AcctWorkDateDao {
	@Autowired
	private AcctWorkDateMapper acctWorkDateMapper; 
	
	@Page
	public List<AcctWorkDate> queryAcctWorkDatList(AcctWorkDate queryBean) {
		return acctWorkDateMapper.queryAcctWorkDatList(queryBean);
	}

}
