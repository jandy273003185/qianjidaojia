package com.qifenqian.bms.basemanager.rule.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * 费率dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName CityDAO.java
 * @author PC
 * @date 2015年6月17日
 * @memo
 */
@Repository
public class RuleDAO {

	@Autowired
	private RuleMapper ruleMapper;

	/**
	 * 分页查询费率列表
	 * 
	 * @return
	 */
	@Page
	public List<Rule> selectRules(Rule rule) {
		return ruleMapper.selectRules(rule);
	}
}
