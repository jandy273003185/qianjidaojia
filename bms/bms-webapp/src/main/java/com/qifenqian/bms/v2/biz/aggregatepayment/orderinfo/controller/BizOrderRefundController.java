package com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.service.BizOrderRefundService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "退款列表")
public class BizOrderRefundController extends BaseController {
    @Autowired
    private BizOrderRefundService bizOrderRefundService;

    @PostMapping("/aggregatepayment/orderinfo/orderRefundList")
    @ApiOperation(value = "订单列表")
    public PageInfo<RefundBean> list(CurrentAccount currentAccount, PageQuery query, @RequestBody RefundQueryBean queryBean) {
        return bizOrderRefundService.list(currentAccount, queryBean);
    }

    @PostMapping("/aggregatepayment/orderinfo/orderRefundExport")
    @ApiOperation(value = "订单报表导出")
    public void exportRechargeExcel(CurrentAccount currentAccount, @RequestBody RefundQueryBean queryBean, HttpServletRequest request,
                                    HttpServletResponse response) {
        bizOrderRefundService.exportRechargeExcel(currentAccount, queryBean, request, response);
    }
}
