package com.qifenqian.bms.v2.biz.merchant.acctsevenbuss.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.v2.biz.merchant.acctsevenbuss.service.BizAcctSevenBussService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 商户账号信息
 * @date 2020/4/20 11:39
 */
@RestController
@Api(tags = "商户账号信息管理")
public class BizAcctSevenBussController extends BaseController {

    @Autowired
    private BizAcctSevenBussService bizAcctSevenBussService;

    @PostMapping(value = "/acctsevenbuss/list")
    @ApiOperation(value = "商户账号信息列表")
    public PageInfo<AcctSevenBuss> list(PageQuery pageQuery, @RequestBody AcctSevenBuss queryBean) {
        return this.bizAcctSevenBussService.findAcctSevenBussList(queryBean);
    }

    @PostMapping(value = "/acctsevenbuss/update")
    @ApiOperation(value = "商户账号信息修改")
    public ResultData update(@RequestBody AcctSevenBuss acctSevenBuss) {
        if (null == acctSevenBuss) {
            throw new BizException("商户账号对象为空");
        }
        if (StringUtils.isBlank(acctSevenBuss.getAcctId())) {
            throw new BizException("商户七分钱账号为空");
        }
        if (StringUtils.isBlank(acctSevenBuss.getStatus())) {
            throw new BizException("商户状态为空");
        }
        return this.bizAcctSevenBussService.update(acctSevenBuss);
    }
}
