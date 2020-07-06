package com.qifenqian.bms.accounting.aggregations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.dao.BalAggregationResultExceptionDAO;

@Service
public class BalAggregationResultExceptionService {

	
	@Autowired
	private BalAggregationResultExceptionDAO balAggregationResultExceptionDAO;
	
	public List<BalAggregationResultException> selectZxResultExceptionList(BalAggregationResultException balAggregationResultException){
		
		return balAggregationResultExceptionDAO.selectZxResultExceptionList(balAggregationResultException);		
	}
}
