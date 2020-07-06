package com.qifenqian.bms.accounting.aggregations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultEqual;
import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.dao.AggregationResultExceptionDAO;


@Service
public class AggregationResultExceptionService {

	
	@Autowired
	private AggregationResultExceptionDAO aggregationResultExceptionDAO;

	/**
	 * 差錯報表
	 * @param queryBean
	 * @return
	 */
	public List<BalAggregationResultException> selectErrorList(
			BalAggregationResultException queryBean) {
		
		return aggregationResultExceptionDAO.selectErrorList(queryBean);
	}

	/**
	 * 一致報表
	 * @param queryBean
	 * @return
	 */
	public List<BalAggregationResultEqual> selectFitList(
			BalAggregationResultEqual queryBean) {
	
		return aggregationResultExceptionDAO.selectFitList(queryBean);
	}
}
