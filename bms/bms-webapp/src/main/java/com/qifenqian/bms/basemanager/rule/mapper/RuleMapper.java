package com.qifenqian.bms.basemanager.rule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.rule.bean.Rule;

/**
 * 费率持久层
 * 
 * @project sevenpay-bms-web
 * @fileName RuleMapper.java
 * @author PC
 * @date 2015年6月17日
 * @memo
 */

@MapperScan
public interface RuleMapper {

	/**
	 * 查询所有费率信息
	 * 
	 * @return
	 */
	public List<Rule> selectRules(Rule rule);
	
	public List<Rule> selectRules02(Rule rule);

	/**
	 * 新增费率
	 * 
	 * @param city
	 */
	public void insertRule(Rule rule);

	/**
	 * 更新费率
	 * 
	 * @param city
	 */
	public void updateRule(Rule rule);

	/**
	 * 删除费率
	 * 
	 * @param cityCode
	 */
	public void deleteRule(String ruleCode);
	
	/**
	 * 查询最大费率编号
	 * @return
	 */
	public String selectRuleMaxId();
	/**
	 * 保存商户费率
	 */
	public void saveFee(Rule rule);
	
	/**
	 * 删除商户费率
	 * @param custId
	 */
	public void deleteCustFeeRule(String custId);
	
	/**
	 * 更改商户费率
	 * @param custId
	 */
	public void updateCustFeeRule(@Param("custId") String custId,@Param("feeCode") String feeCode);

	/**
	 * 根据feeCode查询费率
	 * @param feeCode
	 * @return
	 */
	public Rule selectRuleByFeeCode(String feeCode);
}
