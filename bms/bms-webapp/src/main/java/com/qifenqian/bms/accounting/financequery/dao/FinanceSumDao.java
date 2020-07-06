package com.qifenqian.bms.accounting.financequery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.financequery.bean.FinanceSum;
import com.qifenqian.bms.accounting.financequery.mapper.FinanceSumMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class FinanceSumDao {
	@Autowired
	private FinanceSumMapper mapper;
	@Page
	public List<FinanceSum> selectFinanceList(String subjectName){
		return mapper.selectFinanceList(subjectName);
	}
}
