package com.qifenqian.bms.accounting.aggregations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.aggregations.bean.JhAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.dao.JhAggregationResultExceptionDAO;

@Service
public class JhAggregationResultExceptionService {

	@Autowired
	private JhAggregationResultExceptionDAO jhAggregationResultExceptionDAO;	
	
	public List<JhAggregationResultException> selectQfqResultExceptionList(
			JhAggregationResultException jhAggregationResultException) {
		
		return jhAggregationResultExceptionDAO.selectJhResultExceptionList(jhAggregationResultException);
	}

}
