package com.qifenqian.bms.accounting.aggregations.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultEqual;
import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.mapper.AggregationResultEqualMapper;
import com.qifenqian.bms.accounting.aggregations.mapper.AggregationResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AggregationResultExceptionDAO {

	@Autowired
	private AggregationResultExceptionMapper aggregationResultExceptionMapper;
	@Autowired
	private AggregationResultEqualMapper aggregationResultEqualMapper;
	
	@Page
	public List<BalAggregationResultException> selectErrorList(
			BalAggregationResultException queryBean) {
		return aggregationResultExceptionMapper.selectErrorList(queryBean);
	}
	@Page
	public List<BalAggregationResultEqual> selectFitList(
			BalAggregationResultEqual queryBean) {
		
		return aggregationResultEqualMapper.selectFitList(queryBean);
	}


}
