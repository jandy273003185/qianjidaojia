package com.qifenqian.bms.basemanager.feerule.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.feerule.bean.FeeRule;
@MapperScan
public interface FeeRuleMapper {
	/**
	 * 更新费用规则 feecode
	 * @param feeRule
	 */
	public void updateFeeRule(FeeRule feeRule);
	
	/**
	 * 查找feeCode
	 * @param feeRule
	 */
	public FeeRule selectFeeRule(FeeRule feeRule);

	public void insertFeeRule(FeeRule feeRule);
}
