package com.qifenqian.bms.v2.biz.basedata.payin.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.payIn.bean.PayIn;
import com.qifenqian.bms.v2.biz.basedata.payin.service.BasePayInService;
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
 * @Description: 代付垫资费率信息管理
 * @date 2020/4/27 16:18
 */
@RestController
@Api(tags = "代付垫资费率信息管理")
public class BasePayInController extends BaseController {

    @Autowired
    private BasePayInService basePayInService;

    @PostMapping(value = "/pay/in/list")
    @ApiOperation("代付垫资费率信息列表")
    public PageInfo<PayIn> list(PageQuery pageQuery, @RequestBody PayIn payIn) {
        return this.basePayInService.findPayInList(payIn);
    }

    @PostMapping(value = "/pay/in/add")
    @ApiOperation("代付垫资费率信息添加")
    public ResultData add(@RequestBody PayIn payIn) {
        if (StringUtils.isBlank(payIn.getFeeCode())) {
            throw new BizException("费用代码为空");
        }
        if (StringUtils.isBlank(payIn.getFeeName())) {
            throw new BizException("费用名称为空");
        }
        if (StringUtils.isBlank(payIn.getFeeRate())) {
            throw new BizException("费率为空");
        }
        return this.basePayInService.add(payIn);
    }

    @PostMapping(value = "/pay/in/update")
    @ApiOperation("代付垫资费率信息更新")
    public ResultData update(@RequestBody PayIn payIn) {
        if (StringUtils.isBlank(payIn.getFeeCode())) {
            throw new BizException("费用代码为空");
        }
        if (StringUtils.isBlank(payIn.getFeeName())) {
            throw new BizException("费用名称为空");
        }
        if (StringUtils.isBlank(payIn.getFeeRate())) {
            throw new BizException("费率为空");
        }
        return this.basePayInService.update(payIn);
    }
    @PostMapping(value = "/pay/in/stop")
    @ApiOperation("代付垫资费率信息停用")
    public ResultData stop(@RequestBody PayIn payIn) {
        if (StringUtils.isBlank(payIn.getFeeCode())) {
            throw new BizException("费用代码为空");
        }
        return this.basePayInService.stop(payIn);
    }
}
