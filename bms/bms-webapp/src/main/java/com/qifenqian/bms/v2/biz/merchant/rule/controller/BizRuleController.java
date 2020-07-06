package com.qifenqian.bms.v2.biz.merchant.rule.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.v2.biz.merchant.rule.service.BizRuleService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 费率
 * @date 2020/4/21 10:16
 */
@RestController
@Api(tags = "费率管理")
public class BizRuleController extends BaseController {

    @Autowired
    private BizRuleService bizRuleService;

    @PostMapping(value = "/rule/list")
    @ApiOperation(value = "费率列表")
    public PageInfo<Rule> list(PageQuery pageQuery, @RequestBody Rule requestRule) {
        return bizRuleService.findRuleList(requestRule);
    }

    @PostMapping(value = "/rule/add")
    @ApiOperation(value = "费率添加")
    public ResultData add(CurrentAccount currentAccount, @RequestBody Rule rule) {
        rule.setInstUser(currentAccount.getLoginId().toString());
        return bizRuleService.add(rule);
    }

    @PostMapping(value = "/rule/update")
    @ApiOperation(value = "费率添加")
    public ResultData update(CurrentAccount currentAccount, @RequestBody Rule rule) {
        rule.setLupdUser(currentAccount.getLoginId().toString());
        return bizRuleService.update(rule);
    }

    @PostMapping(value = "/rule/delete")
    @ApiOperation(value = "费率删除")
    public ResultData delete(@RequestBody Rule rule) {
        return bizRuleService.delete(rule);
    }

}
