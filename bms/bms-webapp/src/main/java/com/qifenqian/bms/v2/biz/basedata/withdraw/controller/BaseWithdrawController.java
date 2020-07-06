package com.qifenqian.bms.v2.biz.basedata.withdraw.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.utils.ValidateTool;
import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;
import com.qifenqian.bms.v2.biz.basedata.withdraw.service.BaseWithdrawService;
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

import java.math.BigDecimal;

/**
 * @author LiBin
 * @Description: 提现控制管理
 * @date 2020/4/27 15:38
 */
@RestController
@Api(tags = "提现控制管理")
public class BaseWithdrawController extends BaseController {

    @Autowired
    private BaseWithdrawService baseWithdrawService;

    @PostMapping(value = "/withdraw/control/list")
    @ApiOperation("提现控制信息列表")
    public PageInfo<WithdrawControl> list(PageQuery pageQuery, @RequestBody WithdrawControl withdrawControl) {
        return this.baseWithdrawService.findWithdrawControlList(withdrawControl);
    }

    @PostMapping(value = "/withdraw/control/add")
    @ApiOperation("提现控制添加")
    public ResultData add(@RequestBody WithdrawControl withdrawControl) {
        this.setDefault(withdrawControl);
        this.valid(withdrawControl);
        return this.baseWithdrawService.add(withdrawControl);
    }

    @PostMapping(value = "/withdraw/control/update")
    @ApiOperation("提现控制更新")
    public ResultData update(@RequestBody WithdrawControl withdrawControl) {
        this.setDefault(withdrawControl);
        this.valid(withdrawControl);
        return this.baseWithdrawService.update(withdrawControl);
    }

    @PostMapping(value = "/withdraw/control/delete")
    @ApiOperation("提现控制删除")
    public ResultData delete(@RequestBody WithdrawControl withdrawControl) {
        return this.baseWithdrawService.delete(withdrawControl);
    }

    /**
     * @param [withdrawControl]
     * @return void
     * @description 设置默认值
     * @author LiBin
     * @date 2020/4/27 15:55
     */
    private void setDefault(WithdrawControl withdrawControl) {
        if (withdrawControl.getPcWdAmtPerDay() == null) {
            withdrawControl.setPcWdAmtPerDay(new BigDecimal("0.00"));
        }

        if (withdrawControl.getPcWdAmtPerMonth() == null) {
            withdrawControl.setPcWdAmtPerMonth(new BigDecimal("0.00"));
        }

        if (withdrawControl.getPcWdAmtPerOnce() == null) {
            withdrawControl.setPcWdAmtPerOnce(new BigDecimal("0.00"));
        }

        if (withdrawControl.getMbWdAmtPerDay() == null) {
            withdrawControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
        }

        if (withdrawControl.getMbWdAmtPerMonth() == null) {
            withdrawControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
        }

        if (withdrawControl.getMbWdAmtPerOnce() == null) {
            withdrawControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
        }
    }

    /**
     * @param [tradeControl]
     * @return void
     * @description 校验参数
     * @author LiBin
     * @date 2020/4/27 15:54
     */
    private void valid(WithdrawControl tradeControl) {
        if (StringUtils.isBlank(tradeControl.getControlType())) {
            throw new BizException("控制类型为空");
        }
        if (StringUtils.isBlank(tradeControl.getCustId())) {
            throw new BizException("客户编号为空");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdCntPerDay()), "^-?[1-9]\\d*$")) {
            throw new BizException("PcWdCntPerDay为整数");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcWdAmtPerDay最多16位整数。小数点后最多两个数字");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcWdAmtPerOnce最多16位整数。小数点后最多两个数字");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcWdAmtPerMonth最多16位整数。小数点后最多两个数字");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdCntPerDay()), "^-?[1-9]\\d*$")) {
            throw new BizException("MbWdCntPerDay为整数");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("MbWdAmtPerOnce最多16位整数。小数点后最多两个数字");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("MbWdAmtPerDay最多16位整数。小数点后最多两个数字");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("MbWdAmtPerMonth最多16位整数。小数点后最多两个数字");
        }
    }
}
