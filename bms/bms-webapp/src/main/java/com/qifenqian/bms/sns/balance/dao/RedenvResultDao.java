package com.qifenqian.bms.sns.balance.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.sns.balance.bean.ResultEqual;
import com.qifenqian.bms.sns.balance.bean.ResultFailure;
import com.qifenqian.bms.sns.balance.bean.ResultRedenvDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultSevenDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultStatistic;
import com.qifenqian.bms.sns.balance.bean.ResultSummary;
import com.qifenqian.bms.sns.balance.mapper.RedenvResultMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RedenvResultDao {

	@Autowired
	private RedenvResultMapper redenvResultMapper;

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultStatistic> selectResultStatistic(ResultStatistic queryBean) {
		return redenvResultMapper.selectResultStatistic(queryBean);
	}

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultEqual> selectResultEqual(ResultEqual queryBean) {
		return redenvResultMapper.selectResultEqual(queryBean);
	}

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultFailure> selectResultFailure(ResultFailure queryBean) {
		return redenvResultMapper.selectResultFailure(queryBean);
	}

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultRedenvDoubt> selectResultRedenvDoubt(ResultRedenvDoubt queryBean) {
		return redenvResultMapper.selectResultRedenvDoubt(queryBean);
	}

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultSevenDoubt> selectResultSevenDoubt(ResultSevenDoubt queryBean) {
		return redenvResultMapper.selectResultSevenDoubt(queryBean);
	}
	
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ResultSummary> selectResultSummary(ResultSummary queryBean) {
		return redenvResultMapper.selectResultSummary(queryBean);
	}
}
