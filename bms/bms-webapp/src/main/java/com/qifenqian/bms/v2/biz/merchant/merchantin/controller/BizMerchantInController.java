package com.qifenqian.bms.v2.biz.merchant.merchantin.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;
import com.qifenqian.bms.basemanager.merchantincontrol.service.TdMerchantInControlService;
import com.qifenqian.bms.v2.biz.merchant.merchantin.service.BizMerchantInService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 商户网关维护
 * @date 2020/4/20 14:34
 */
@RestController
@Api(tags = "商户网关维护管理")
public class BizMerchantInController extends BaseController {

    @Autowired
    private TdMerchantInControlService service;
    @Autowired
    private BizMerchantInService bizMerchantInService;

    @PostMapping(value = "/merchantincontrol/list")
    @ApiOperation(value = "商户网关列表")
    public PageInfo<MerchantInControl> list(PageQuery pageQuery, @RequestBody MerchantInControl queryBean) {
        return bizMerchantInService.findMerchantInList(queryBean);
    }

    @PostMapping(value = "/merchantincontrol/add")
    @ApiOperation(value = "商户网关添加")
    public ResultData add(@RequestBody MerchantInControl merchantInControl) {
        try {
            service.insert(merchantInControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("保存信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    @PostMapping(value = "/merchantincontrol/update")
    @ApiOperation(value = "商户网关修改")
    public ResultData update(@RequestBody MerchantInControl merchantInControl) {
        try {
            service.update(merchantInControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    @PostMapping(value = "/merchantincontrol/delete")
    @ApiOperation(value = "商户网关删除")
    public ResultData delete(@RequestBody MerchantInControl merchantInControl) {
        try {
            service.delete(merchantInControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    @PostMapping(value = "/merchantincontrol/verify")
    @ApiOperation(value = "商户网关校验")
    public ResultData verify(@RequestBody MerchantInControl merchantInControl) {
        try {
            service.verify(merchantInControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("校验信息异常! " + e.getMessage());
        }
        return ResultData.success();
    }
}
