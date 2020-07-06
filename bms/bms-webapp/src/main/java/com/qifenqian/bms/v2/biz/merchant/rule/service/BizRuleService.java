package com.qifenqian.bms.v2.biz.merchant.rule.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 费率
 * @date 2020/4/21 10:18
 */
@Service
public class BizRuleService extends BaseService {
    @Autowired
    private RuleMapper ruleMapper;

    public PageInfo<Rule> findRuleList(Rule rule) {
        List<Rule> rules = ruleMapper.selectRules(rule);
        return new PageInfo<>(rules);
    }

    public ResultData add(Rule rule) {
        try {
            String feeCode = BusinessUtils.getRuleId(ruleMapper.selectRuleMaxId());
            rule.setFeeCode(feeCode);
            ruleMapper.insertRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("保存费率信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(Rule rule) {
        try {
            ruleMapper.updateRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("更新费率信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(Rule rule) {
        try {
            ruleMapper.deleteRule(rule.getFeeCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除费率信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }


}
