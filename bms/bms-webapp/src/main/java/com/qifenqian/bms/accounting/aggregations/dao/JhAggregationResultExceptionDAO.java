package com.qifenqian.bms.accounting.aggregations.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.aggregations.bean.JhAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.mapper.JhAggregationResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class JhAggregationResultExceptionDAO {

	@Autowired
	private JhAggregationResultExceptionMapper jhAggregationResultExceptionMapper;
	
	@Page
	public List<JhAggregationResultException> selectJhResultExceptionList(
			JhAggregationResultException jhAggregationResultException) {
		return jhAggregationResultExceptionMapper.selectJhResultExceptionList(jhAggregationResultException);
	}

	

}
