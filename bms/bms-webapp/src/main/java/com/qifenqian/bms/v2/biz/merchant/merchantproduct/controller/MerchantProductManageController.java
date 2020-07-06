package com.qifenqian.bms.v2.biz.merchant.merchantproduct.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.v2.biz.merchant.merchantproduct.service.MerchantProductManageService;
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
@Api(tags = "商户产品管理")
public class MerchantProductManageController extends BaseController {
    @Autowired
    private MerchantProductManageService merchantProductManageService;

    @PostMapping("/merchant/product/list")
    @ApiOperation(value = "商户产品列表")
    public PageInfo<MerchantProduct> list(PageQuery query, @RequestBody MerchantProduct queryBean) {
        PageInfo<MerchantProduct> list = merchantProductManageService.list(queryBean);
        return list;
    }


    @PostMapping("/merchant/product/add")
    @ApiOperation(value = "商户产品新增")
    public ResultData add(@RequestBody MerchantProduct merchantProduct) {
        paramValidation(merchantProduct);
        return merchantProductManageService.add(merchantProduct);
    }

    @PostMapping("/merchant/product/update")
    @ApiOperation(value = "商户产品修改")
    public ResultData update(@RequestBody MerchantProduct merchantProduct) {
        paramValidation(merchantProduct);
        return merchantProductManageService.update(merchantProduct);
    }

    @PostMapping("/merchant/product/audit")
    @ApiOperation(value = "商户产品审核")
    public ResultData audit(CurrentAccount currentAccount, @RequestBody MerchantProduct merchantProduct) {
        paramValidation(merchantProduct);
        merchantProduct.setAuditId(String.valueOf(currentAccount.getLoginId()));
        return merchantProductManageService.audit(merchantProduct);
    }

    @PostMapping("/merchant/product/delete")
    @ApiOperation(value = "商户产品删除")
    public ResultData delete(CurrentAccount currentAccount, @RequestBody MerchantProduct merchantProduct) {
        paramValidation(merchantProduct);
        return merchantProductManageService.delete(merchantProduct);
    }

    private void paramValidation(MerchantProduct merchantProduct) {
        if (null == merchantProduct) {
            throw new BizException("商户产品对象不能为空！");
        }
        if (StringUtils.isBlank(merchantProduct.getMerchantCode())) {
            throw new BizException("商户代码不能为空！");
        }
        if (StringUtils.isBlank(merchantProduct.getProductId())) {
            throw new BizException("产品ID不能为空！");
        }
    }


}
