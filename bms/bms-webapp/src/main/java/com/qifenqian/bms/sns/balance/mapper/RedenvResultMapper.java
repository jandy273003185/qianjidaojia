package com.qifenqian.bms.sns.balance.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.sns.balance.bean.ResultEqual;
import com.qifenqian.bms.sns.balance.bean.ResultFailure;
import com.qifenqian.bms.sns.balance.bean.ResultRedenvDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultSevenDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultStatistic;
import com.qifenqian.bms.sns.balance.bean.ResultSummary;

@MapperScan
public interface RedenvResultMapper {
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultStatistic> selectResultStatistic(ResultStatistic queryBean);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultEqual> selectResultEqual(ResultEqual queryBean);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultFailure> selectResultFailure(ResultFailure queryBean);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultRedenvDoubt> selectResultRedenvDoubt(ResultRedenvDoubt queryBean);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultSevenDoubt> selectResultSevenDoubt(ResultSevenDoubt queryBean);
	
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<ResultSummary> selectResultSummary(ResultSummary queryBean);
	
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	int updateResultSummary(ResultSummary updateBean);

}
