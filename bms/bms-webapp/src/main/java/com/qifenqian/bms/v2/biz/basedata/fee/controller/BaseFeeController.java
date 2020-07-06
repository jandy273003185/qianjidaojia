package com.qifenqian.bms.v2.biz.basedata.fee.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.fee.bean.Fee;
import com.qifenqian.bms.v2.biz.basedata.fee.service.BaseFeeService;
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
 * @Description: 费率
 * @date 2020/4/27 10:47
 */
@RestController
@Api(tags = "费率信息管理")
public class BaseFeeController extends BaseController {

    @Autowired
    private BaseFeeService baseFeeService;

    @PostMapping(value = "/fee/list")
    @ApiOperation("费率信息列表")
    public PageInfo<Fee> list(PageQuery pageQuery, @RequestBody Fee fee) {
        return this.baseFeeService.findFeeList(fee);
    }

    @PostMapping(value = "/fee/add")
    @ApiOperation("费率信息添加")
    public ResultData add(@RequestBody Fee fee) {
        if (StringUtils.isBlank(fee.getFeeCode())) {
            throw new BizException("费用编号为空");
        }

        if (StringUtils.isBlank(fee.getFeeName())) {
            throw new BizException("费用名称为空");
        }

        if (StringUtils.isBlank(fee.getFeeCodeDesc())) {
            throw new BizException("费用描述为空");
        }
        return this.baseFeeService.add(fee);
    }
    @PostMapping(value = "/fee/update")
    @ApiOperation("费率信息更新")
    public ResultData update(@RequestBody Fee fee) {
        if (StringUtils.isBlank(fee.getFeeCode())) {
            throw new BizException("费用编号为空");
        }

        if (StringUtils.isBlank(fee.getFeeName())) {
            throw new BizException("费用名称为空");
        }

        if (StringUtils.isBlank(fee.getFeeCodeDesc())) {
            throw new BizException("费用描述为空");
        }
        return this.baseFeeService.update(fee);
    }
    @PostMapping(value = "/fee/delete")
    @ApiOperation("费率信息列表")
    public ResultData delete(@RequestBody Fee fee) {
        if (StringUtils.isBlank(fee.getFeeCode())) {
            throw new BizException("费用编号为空");
        }
        return this.baseFeeService.delete(fee);
    }
    @PostMapping(value = "/fee/validate")
    @ApiOperation("费率信息列表")
    public ResultData validate(@RequestBody Fee fee) {
        if (StringUtils.isBlank(fee.getFeeCode())) {
            throw new BizException("费用编号为空");
        }
        return this.baseFeeService.validate(fee);
    }
}
