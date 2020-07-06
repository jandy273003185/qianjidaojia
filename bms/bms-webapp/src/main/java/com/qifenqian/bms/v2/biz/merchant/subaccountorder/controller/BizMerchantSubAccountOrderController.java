package com.qifenqian.bms.v2.biz.merchant.subaccountorder.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;
import com.qifenqian.bms.v2.biz.merchant.subaccountorder.service.BizMerchantSubAccountOrderService;
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
 * @Description: 分账管理
 * @date 2020/4/21 18:05
 */
@RestController
@Api(tags = "分账管理")
public class BizMerchantSubAccountOrderController extends BaseController {

    @Autowired
    private BizMerchantSubAccountOrderService bizMerchantSubAccountOrderService;

    @PostMapping(value = "/sub/account/order/list")
    @ApiOperation(value = "分账关系列表")
    public PageInfo<MerchantSubAccouontOrderBean> list(PageQuery pageQuery, @RequestBody MerchantSubAccouontOrderBean merchantSubAccouontOrderBean) {
        return this.bizMerchantSubAccountOrderService.findMerchantSubAccountOrderList(merchantSubAccouontOrderBean);
    }

}
