package com.qifenqian.bms.basemanager.acctsevencust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.dao.AcctSevenCustDao;
import com.qifenqian.bms.expresspay.CommonService;

@Service
public class AcctSevenCustService {
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AcctSevenCustDao acctSevenCustDao;
	
	public List<AcctSevenCust> getAcctSevenCust(AcctSevenCust queryBean) {
		
		return acctSevenCustDao.getAcctSevenCust(queryBean);
	}


}
