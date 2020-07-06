package com.qifenqian.bms.v2.biz.merchant.cashier.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.service.CashierManageService;
import com.qifenqian.bms.v2.biz.merchant.cashier.service.BizCashierManageService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
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

@RestController
@Api(tags = "收银员管理")
public class BizCashierManageController extends BaseController {

    @Autowired
    private BizCashierManageService bizCashierManageService;

    @Autowired
    private CashierManageService cashierManageService;


    @PostMapping("/merchant/cashierManage/list")
    @ApiOperation(value = "收银员列表")
    public PageInfo<CashierInfo> list(CurrentAccount currentAccount, PageQuery query, @RequestBody CashierInfo queryBean) {
        PageInfo<CashierInfo> list = bizCashierManageService.list(currentAccount, queryBean);
        return list;
    }

    @PostMapping("/merchant/cashierManage/delete")
    @ApiOperation(value = "删除收银员")
    public ResultData delete(@RequestBody CashierInfo cashierInfo) {
        if (StringUtils.isBlank(cashierInfo.getOnlyId())) {
            throw new BizException("收银员唯一ID不能为空！");
        }
        return bizCashierManageService.delete(cashierInfo);
    }

    @PostMapping("/merchant/cashierManage/add")
    @ApiOperation(value = "新增收银员")
    public ResultData add(CurrentAccount currentAccount, @RequestBody CashierInfo cashierInfo) {
        paramValidation(cashierInfo);
        if (cashierManageService.validate(cashierInfo.getCashierMobile(), cashierInfo.getOnlyId()))
            throw new BizException("该手机号不能设置为本店收银员, 请检查该手机是否已经成为别的门店收银员,或者商户帐号！");
        return bizCashierManageService.add(currentAccount, cashierInfo);
    }

    @PostMapping("/merchant/cashierManage/update")
    @ApiOperation(value = "修改收银员")
    public ResultData update(CurrentAccount currentAccount, @RequestBody CashierInfo cashierInfo) {
        if (StringUtils.isBlank(cashierInfo.getStatus()))
            throw new BizException("是否有效不能为空！");
        return bizCashierManageService.update(currentAccount, cashierInfo);
    }

    private void paramValidation(CashierInfo cashierInfo) {
        //收银员新增字段
        if (StringUtils.isBlank(cashierInfo.getWebRefundAuth()))
            throw new BizException("请设置网站退款权限！");
        if (StringUtils.isBlank(cashierInfo.getMachineRefundAuth()))
            throw new BizException("请设置机器退款权限！");
        if (StringUtils.isBlank(cashierInfo.getAppRefundAuth()))
            throw new BizException("请设置APP退款权限！");
        if (StringUtils.isBlank(cashierInfo.getOfficialRefundAuth()))
            throw new BizException("请设置公众号退款权限！");
        if (StringUtils.isBlank(cashierInfo.getMiniRefundAuth()))
            throw new BizException("请设置小程序退款权限！");

        if (StringUtils.isBlank(cashierInfo.getLoginPw()))
            throw new BizException("请设置登录密码！");
        if (StringUtils.isBlank(cashierInfo.getRefundPw()))
            throw new BizException("请设置退款密码！");
    }

}
