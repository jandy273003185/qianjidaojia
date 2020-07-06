package com.qifenqian.bms.accounting.financequery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;
import com.qifenqian.bms.accounting.financequery.dao.CommerciaBalanceDao;

@Service
public class CommerciaBalanceService {
	@Autowired
	private CommerciaBalanceDao dao;

	public List<CommerciaBalance> selectCommerciaBalanceList(CommerciaBalance commerciaBalance) {
		return dao.selectCommerciaBalanceList(commerciaBalance);
	}

}
