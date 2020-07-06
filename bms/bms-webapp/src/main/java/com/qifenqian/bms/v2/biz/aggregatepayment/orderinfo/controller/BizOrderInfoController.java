package com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.DealOperation;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.v2.biz.aggregatepayment.orderinfo.service.BizOrderInfoService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "订单列表")
public class BizOrderInfoController extends BaseController {
    @Value("${EXPORT_EXCEL}")
    private String EXPORT_EXCEL;
    @Autowired
    private BizOrderInfoService bizOrderInfoService;

    @PostMapping("/aggregatepayment/orderinfo/orderinfolist")
    @ApiOperation(value = "订单列表")
    public PageInfo<OrderInfoBean> list(CurrentAccount currentAccount, PageQuery query, @RequestBody OrderInfoQueryBean queryBean) {
        return bizOrderInfoService.list(currentAccount, queryBean);
    }

    @PostMapping("/aggregatepayment/orderinfo/orderInfoExport")
    @ApiOperation(value = "订单报表导出")
    public void exportRechargeExcel(CurrentAccount currentAccount, @RequestBody OrderInfoQueryBean queryBean, HttpServletRequest request,
                                    HttpServletResponse response) {
        bizOrderInfoService.exportRechargeExcel(currentAccount, queryBean, request, response);
    }

    @PostMapping("/aggregatepayment/orderinfo/orderExceptionList")
    @ApiOperation(value = "订单异常列表")
    public PageInfo<OrderInfoBean> orderExceptionList(CurrentAccount currentAccount, PageQuery query, @RequestBody OrderInfoQueryBean queryBean) {
        return bizOrderInfoService.orderExceptionList(currentAccount, queryBean);
    }

    @PostMapping("/aggregatepayment/orderinfo/detailException")
    @ApiOperation(value = "订单异常明细")
    public DealOperation detailException(@RequestBody OrderInfoQueryBean queryBean) {
        return bizOrderInfoService.detailException(queryBean);
    }

    @PostMapping("/aggregatepayment/orderinfo/nextstepoperation")
    @ApiOperation(value = "续作")
    public ResultData nextStep(@RequestBody OrderInfoQueryBean queryBean) {
        return bizOrderInfoService.nextStep(queryBean);
    }


}
