package com.qifenqian.bms.v2.biz.merchant.subaccount.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;
import com.qifenqian.bms.v2.biz.merchant.subaccount.service.BizMerchantSubAccountService;
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
 * @Description: 分账关系
 * @date 2020/4/21 15:38
 */
@RestController
@Api(tags = "分账关系管理")
public class BizMerchantSubAccountController extends BaseController {

    @Autowired
    private BizMerchantSubAccountService bizMerchantSubAccountService;

    @PostMapping(value = "/sub/account/list")
    @ApiOperation(value = "分账关系列表")
    public PageInfo<MerchantSubAccouontBean> list(PageQuery pageQuery, @RequestBody MerchantSubAccouontBean merchantSubAccouontBean) {
        return this.bizMerchantSubAccountService.findMerchantSubAccouontList(merchantSubAccouontBean);
    }

    @PostMapping(value = "/sub/account/add")
    @ApiOperation(value = "分账关系添加")
    public ResultData add(@RequestBody MerchantSubAccouontBean merchantSubAccouontBean) {
        return this.bizMerchantSubAccountService.add(merchantSubAccouontBean);
    }

    @PostMapping(value = "/sub/account/delete")
    @ApiOperation(value = "分账关系删除")
    public ResultData delete(@RequestBody MerchantSubAccouontBean merchantSubAccouontBean) {
        return this.bizMerchantSubAccountService.delete(merchantSubAccouontBean);
    }
}
