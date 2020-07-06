package com.qifenqian.bms.v2.biz.aggregatepayment.merchantprodrate.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.v2.biz.aggregatepayment.merchantprodrate.service.BizMerchantProdRateService;
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
 * @Description: 商户产品费率
 * @date 2020/4/24 16:37
 */
@RestController
@Api(tags = "商户产品费率管理")
public class BizMerchantProdRateController extends BaseController {
    @Autowired
    private BizMerchantProdRateService bizMerchantProdRateService;

    @PostMapping(value = "/merchantprodrate/list")
    @ApiOperation("商户产品费率列表")
    public PageInfo<TdMerchantProdRate> list(PageQuery pageQuery, @RequestBody TdMerchantProdRate merchantProdRate) {
        return this.bizMerchantProdRateService.findMerchantProdRateList(merchantProdRate);
    }

    @PostMapping(value = "/merchantprodrate/add")
    @ApiOperation("商户产品费率添加")
    public ResultData add(@RequestBody TdMerchantProdRate merchantProdRate) {
        verfiyParam(merchantProdRate);
        return this.bizMerchantProdRateService.add(merchantProdRate);
    }

    @PostMapping(value = "/merchantprodrate/delete")
    @ApiOperation("商户产品费率删除")
    public ResultData delete(@RequestBody TdMerchantProdRate merchantProdRate) {
        if (StringUtils.isBlank(merchantProdRate.getMerCode())) {
            throw new BizException("商户编号不能为空!");
        }
        if (StringUtils.isBlank(merchantProdRate.getMerAgreementCode())) {
            throw new BizException("合同号不能为空!");
        }
        if (StringUtils.isBlank(merchantProdRate.getProdCode())) {
            throw new BizException("产品编号不能为空!");
        }
        return this.bizMerchantProdRateService.delete(merchantProdRate);
    }

    public void verfiyParam(TdMerchantProdRate bean) {
        if (StringUtils.isBlank(bean.getMerCode())) {
            throw new BizException("商户编号不能为空!");
        }
        if (StringUtils.isBlank(bean.getMerAgreementCode())) {
            throw new BizException("合同号不能为空!");
        }
        if (StringUtils.isBlank(bean.getProdCode())) {
            throw new BizException("产品编号不能为空!");
        }
        if (bean.getMerAgreeRate() == null) {
            throw new BizException("费率不能为空!");
        }
    }
}
