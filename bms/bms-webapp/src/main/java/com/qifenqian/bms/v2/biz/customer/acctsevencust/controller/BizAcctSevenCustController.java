package com.qifenqian.bms.v2.biz.customer.acctsevencust.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.v2.biz.customer.acctsevencust.service.BizAcctSevenCustService;
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
 * @author LvFeng
 * @Description: 客户账户管理
 * @date 2020/4/28 14:51
 */
@RestController
@Api(tags = "客户账户管理")
public class BizAcctSevenCustController extends BaseController {
    @Autowired
    private BizAcctSevenCustService bizAcctSevenCustService;

    @PostMapping("/customer/acctsevencust/list")
    @ApiOperation(value = "客户账户列表")
    public PageInfo<AcctSevenCust> list(PageQuery query, @RequestBody AcctSevenCust queryBean) {
        return bizAcctSevenCustService.list(queryBean);
    }

    @PostMapping("/customer/acctsevencust/edit")
    @ApiOperation(value = "修改账号状态")
    public ResultData edit(@RequestBody AcctSevenCust acctSevenCust) {
        return bizAcctSevenCustService.edit(acctSevenCust);
    }


}
