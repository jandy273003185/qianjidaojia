package com.qifenqian.bms.basemanager.rule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.dao.RuleDAO;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 费率服务层
 * 
 * @project sevenpay-bms-web
 * @fileName FeeService.java
 * @author PC
 * @date 2015年6月17日
 * @memo 
 */
@Service
public class RuleService {
	@Autowired
	private RuleMapper ruleMapper;
	
	@Autowired
	private RuleDAO ruleDAO;
	
	
	/**
	 * 查询费率列表
	 * @return
	 */
	public List<Rule> selectRules(Rule rule){
		return ruleDAO.selectRules(rule);
	}
	
	public List<Rule> selectRules02(Rule rule){
		return ruleMapper.selectRules02(rule);
	}
	
	/**
	 * 根据Feecode查询费率
	 * @return
	 */
	public Rule selectRuleByFeeCode(String feeCode){
		return ruleMapper.selectRuleByFeeCode(feeCode);
	}
	
	/**
	 * 根据费率代码查询费率信息
	 * @param feeCode
	 * @return
	 */
	public Rule selectRule(String feeCode){
		return ruleMapper.selectRuleByFeeCode(feeCode);
	}
	
	
	/**
	 * 增加费率
	 * @param rule
	 */
	public void addRule(Rule rule){
		ruleMapper.insertRule(rule);
	}
	
	/**
	 * 更新费率
	 * @param rule
	 */
	public void updateRule(Rule rule){
		ruleMapper.updateRule(rule);
	}
	
	/**
	 * 删除费率
	 * @param questNo
	 */
	public void deleteRule(String questNo){
		ruleMapper.deleteRule(questNo);
	}
	
	/**
	 * 根据编号查询费率
	 * @param questNo
	 * @return
	 */
	public String selectRuleMaxId(){
		
		return ruleMapper.selectRuleMaxId();
		
	}
	
	/**
	 * 增加费率
	 * @param rule
	 */
	public void saveFee(String custId,String feeCode){
		Rule rule=new Rule();
		User user = WebUtils.getUserInfo();
		rule.setMappingId(GenSN.getSN().substring(0,30));
		rule.setInstUser(String.valueOf(user.getUserId()));
		rule.setOperType(Constant.FEE_RATE_WITHDRAW);// 提现
		rule.setStatus(Constant.FEE_RATE_STATUS_VALID); // 有效
		rule.setFeeCode(feeCode);
		rule.setCustId(custId);
		ruleMapper.saveFee(rule);
	}
}
