package com.qifenqian.bms.accounting.aggregations.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.mapper.BalAggregationResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class BalAggregationResultExceptionDAO {

	@Autowired
	private BalAggregationResultExceptionMapper balAggregationResultExceptionMapper;
	
	@Page
	public List<BalAggregationResultException> selectZxResultExceptionList(BalAggregationResultException balAggregationResultException){
		
		
		return balAggregationResultExceptionMapper .selectZxResultExceptionList(balAggregationResultException);
	
	}
}
