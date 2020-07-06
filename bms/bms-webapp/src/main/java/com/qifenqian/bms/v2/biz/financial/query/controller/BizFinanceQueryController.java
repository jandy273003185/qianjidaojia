package com.qifenqian.bms.v2.biz.financial.query.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.accounting.financequery.bean.ChangeBalance;
import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;
import com.qifenqian.bms.accounting.financequery.bean.FinanceSum;
import com.qifenqian.bms.accounting.financequery.bean.UserBalance;
import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.v2.biz.financial.query.service.BizFinanceQueryService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 账务查询
 * @date 2020/5/6 15:45
 */
@RestController
@Api(tags = "账务查询")
public class BizFinanceQueryController extends BaseController {

    @Autowired
    private BizFinanceQueryService bizFinanceQueryService;

    @PostMapping(value = "/finance/query/finance/list")
    @ApiOperation("汇总余额")
    public PageInfo<FinanceSum> list(PageQuery pageQuery, @RequestBody FinanceSum financeSum) {
        return this.bizFinanceQueryService.findFinanceSumList(financeSum);
    }

    @PostMapping(value = "/finance/query/change/balance/list")
    @ApiOperation("余额变动")
    public PageInfo<ChangeBalance> list(PageQuery pageQuery, @RequestBody ChangeBalance changeBalance) {
        return this.bizFinanceQueryService.findChangeBalanceList(changeBalance);
    }

    @PostMapping(value = "/finance/query/commercia/balance/list")
    @ApiOperation("商户余额")
    public PageInfo<CommerciaBalance> list(PageQuery pageQuery, @RequestBody CommerciaBalance commerciaBalance) {
        return this.bizFinanceQueryService.findCommerciaBalanceList(commerciaBalance);
    }
    @PostMapping(value = "/finance/query/user/balance/list")
    @ApiOperation("用户余额")
    public PageInfo<UserBalance> list(PageQuery pageQuery, @RequestBody UserBalance userBalance) {
        return this.bizFinanceQueryService.findUserBalanceList(userBalance);
    }
    @PostMapping(value = "/finance/query/withdraw/list")
    @ApiOperation("客户提现列表")
    public PageInfo<Withdraw> list(PageQuery pageQuery, @RequestBody WithdrawRequestBean withdrawRequestBean) {
        return this.bizFinanceQueryService.findWithdrawList(withdrawRequestBean);
    }
    @PostMapping(value = "/finance/query/merchant/withdraw/list")
    @ApiOperation("商户提现列表")
    public PageInfo<MerchantWithdraw> list(PageQuery pageQuery, @RequestBody MerchantWithdraw merchantWithdraw) {
        return this.bizFinanceQueryService.findMerchantWithdrawList(merchantWithdraw);
    }
    @PostMapping(value = "/finance/query/revoke/withdraw/list")
    @ApiOperation("客户提现撤销列表")
    public PageInfo<Withdraw> revokeWithdrawList(PageQuery pageQuery, @RequestBody WithdrawRequestBean withdrawRequestBean) {
        return this.bizFinanceQueryService.findRevokeWithdrawList(withdrawRequestBean);
    }
}
