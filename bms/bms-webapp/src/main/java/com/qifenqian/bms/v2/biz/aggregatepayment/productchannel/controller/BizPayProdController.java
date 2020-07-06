package com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.service.BizPayProdService;
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
@Api(tags = "支付产品管理")
public class BizPayProdController extends BaseController {
    @Autowired
    private BizPayProdService bizPayProdService;

    @PostMapping("/aggregatepayment/productchannel/payprod/list")
    @ApiOperation(value = "支付产品列表")
    public PageInfo<PayProdBean> list(PageQuery query, @RequestBody PayProdBean queryBean) {
        return bizPayProdService.list(queryBean);
    }

    @PostMapping("/aggregatepayment/productchannel/payprod/add")
    @ApiOperation(value = "支付产品新增")
    public ResultData add(@RequestBody PayProdBean payProdBean) {
        paramValidation(payProdBean);
        return bizPayProdService.add(payProdBean);
    }

    @PostMapping("/aggregatepayment/productchannel/payprod/update")
    @ApiOperation(value = "支付产品修改")
    public ResultData update(@RequestBody PayProdBean payProdBean) {
        paramValidation(payProdBean);
        return bizPayProdService.update(payProdBean);
    }

    @PostMapping("/aggregatepayment/productchannel/payprod/delete")
    @ApiOperation(value = "支付产品删除")
    public ResultData delete(@RequestBody PayProdBean payProdBean) {
        if (StringUtils.isBlank(payProdBean.getProdCode()))
            throw new BizException("产品编号不能为空！");
        return bizPayProdService.delete(payProdBean);
    }

    private void paramValidation(PayProdBean payProdBean) {
        if (StringUtils.isBlank(payProdBean.getProdCode()))
            throw new BizException("产品编号不能为空！");
        if (StringUtils.isBlank(payProdBean.getProdMemo()))
            throw new BizException("产品名称不能为空！");
        if (StringUtils.isBlank(payProdBean.getProdMemo()))
            throw new BizException("产品说明不能为空！");
        if (StringUtils.isBlank(payProdBean.getStandardRate()))
            throw new BizException("标准费率不能为空！");
    }

}
