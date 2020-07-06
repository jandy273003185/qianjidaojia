package com.qifenqian.bms.v2.biz.basedata.trade.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.utils.ValidateTool;
import com.qifenqian.bms.v2.biz.basedata.trade.service.BaseTradeService;
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
 * @Description: 交易控制
 * @date 2020/4/27 15:20
 */
@RestController
@Api(tags = "交易控制管理")
public class BaseTradeController extends BaseController {
    @Autowired
    private BaseTradeService baseTradeService;

    @PostMapping(value = "/trade/control/list")
    @ApiOperation("交易控制信息列表")
    public PageInfo<TradeControl> list(PageQuery pageQuery, @RequestBody TradeControl tradeControl) {
        return this.baseTradeService.findTradeControlList(tradeControl);
    }

    @PostMapping(value = "/trade/control/add")
    @ApiOperation("交易控制信息添加")
    public ResultData add(@RequestBody TradeControl tradeControl) {
        this.setDefault(tradeControl);
        this.valid(tradeControl);
        return this.baseTradeService.add(tradeControl);
    }

    @PostMapping(value = "/trade/control/update")
    @ApiOperation("交易控制信息更新")
    public ResultData update(@RequestBody TradeControl tradeControl) {
        this.setDefault(tradeControl);
        this.valid(tradeControl);
        return this.baseTradeService.update(tradeControl);
    }

    @PostMapping(value = "/trade/control/delete")
    @ApiOperation("交易控制信息删除")
    public ResultData delete(@RequestBody TradeControl tradeControl) {
        return this.baseTradeService.delete(tradeControl);
    }

    /**
     * @param [tradeControl]
     * @return void
     * @description 设置默认值
     * @author LiBin
     * @date 2020/4/27 15:58
     */
    private void setDefault(TradeControl tradeControl) {
        if (tradeControl.getPcNoAmtPerDay() == null) {
            tradeControl.setPcNoAmtPerDay(new BigDecimal("0.00"));
        }

        if (tradeControl.getPcNoAmtPerMonth() == null) {
            tradeControl.setPcNoAmtPerMonth(new BigDecimal("0.00"));
        }

        if (tradeControl.getPcNoAmtPerOnce() == null) {
            tradeControl.setPcNoAmtPerOnce(new BigDecimal("0.00"));
        }

        if (tradeControl.getPcYesAmtPerDay() == null) {
            tradeControl.setPcYesAmtPerDay(new BigDecimal("0.00"));
        }

        if (tradeControl.getPcYesAmtPerMonth() == null) {
            tradeControl.setPcYesAmtPerMonth(new BigDecimal("0.00"));
        }

        if (tradeControl.getPcYesAmtPerOnce() == null) {
            tradeControl.setPcYesAmtPerOnce(new BigDecimal("0.00"));
        }

        if (tradeControl.getMbWdAmtPerDay() == null) {
            tradeControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
        }

        if (tradeControl.getMbWdAmtPerMonth() == null) {
            tradeControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
        }

        if (tradeControl.getMbWdAmtPerOnce() == null) {
            tradeControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
        }
    }

    /**
     * 校验
     *
     * @param tradeControl
     */
    private void valid(TradeControl tradeControl) {
        if (StringUtils.isBlank(tradeControl.getTradeType())) {
            throw new BizException("交易类型为空");
        }
        if (null == tradeControl.getPayeeAcctType()) {
            throw new BizException("收方账户类型为空");
        }
        if (null == tradeControl.getPayeeAcctType()) {
            throw new BizException("付方账户类型为空");
        }
        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoCntPerDay()), "^-?[1-9]\\d*$")) {
            throw new BizException("pcNoCntPerDay类型应为整数");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcNoAmtPerOnce最多16位整数。小数点后最多两个数字");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcNoAmtPerDay最多16位整数。小数点后最多两个数字");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcNoAmtPerMonth最多16位整数。小数点后最多两个数字");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesCntPerDay()), "^-?[1-9]\\d*$")) {
            throw new BizException("PcYesCntPerDay为整数");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcYesAmtPerOnce最多16位整数。小数点后最多两个数字");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcYesAmtPerDay最多16位整数。小数点后最多两个数字");
        }

        if (!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")) {
            throw new BizException("PcYesAmtPerMonth最多16位整数。小数点后最多两个数字");
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
