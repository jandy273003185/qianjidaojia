package com.qifenqian.bms.v2.biz.customer.custinfo.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.v2.biz.customer.custinfo.bean.SendMessageAO;
import com.qifenqian.bms.v2.biz.customer.custinfo.service.BizTdCustInfoService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import com.sevenpay.invoke.transaction.querybankcard.bean.BankCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author LvFeng
 * @Description: 客户管理列表
 * @date 2020/4/28 18:39
 */
@RestController
@Api(tags = "客户管理列表")
public class BizTdCustInfoController extends BaseController {
    @Autowired
    private BizTdCustInfoService bizTdCustInfoService;

    @PostMapping("/customer/custinfo/list")
    @ApiOperation(value = "消费者列表")
    public PageInfo<TdCustInfo> list(PageQuery query, @RequestBody TdCustInfo queryBean) {
        return bizTdCustInfoService.list(queryBean);
    }

    @PostMapping("/customer/custinfo/export")
    @ApiOperation(value = "导出用户列表信息")
    public void exportExcel(@RequestBody TdCustInfo custInfo, HttpServletRequest request, HttpServletResponse response) {
        bizTdCustInfoService.exportExcel(custInfo, request, response);
    }

    @PostMapping("/customer/custinfo/update")
    @ApiOperation(value = "修改消费者信息")
    public ResultData update(CurrentAccount currentAccount, @RequestBody TdCustInfo custInfo) {
        custInfo.setModifyId(String.valueOf(currentAccount.getLoginId()));
        return bizTdCustInfoService.update(custInfo);
    }

    @PostMapping("/customer/custinfo/reset")
    @ApiOperation(value = "重置支付密码")
    public ResultData reset(@RequestBody TdCustInfo custInfo) {
        if (StringUtils.isBlank(custInfo.getCustId()))
            throw new BizException("客户编号不能为空");
        return bizTdCustInfoService.reset(custInfo);
    }

    @PostMapping("/customer/custinfo/sendmessage")
    @ApiOperation(value = "发送短信")
    public ResultData sendMessage(@RequestBody SendMessageAO sendMessageAO) {
        if (StringUtils.isBlank(sendMessageAO.getMobile()))
            throw new BizException("手机号不能为空");
        if (StringUtils.isBlank(sendMessageAO.getContent()))
            throw new BizException("内容不能为空");
        return bizTdCustInfoService.sendmessage(sendMessageAO);
    }

    @PostMapping("/customer/custinfo/bankcard")
    @ApiOperation(value = "查询银行卡信息")
    public List<BankCard> bankCard(@RequestBody TdCustInfo custInfo) {
        if (StringUtils.isBlank(custInfo.getCustId()))
            throw new BizException("客户编号不能为空");
        return bizTdCustInfoService.bankCard(custInfo);
    }

    @PostMapping("/customer/custinfo/syncsendmessage")
    @ApiOperation(value = "异步发送短信")
    public ResultData syncSendMessage(@RequestBody SendMessageAO sendMessageAO) {
        return bizTdCustInfoService.syncSendMessage(sendMessageAO);
    }

    @PostMapping("/customer/custinfo/queryaccount")
    @ApiOperation(value = "查询客户信息")
    public TdCustInfo queryAccount(@RequestBody TdCustInfo queryBean) {
        if (StringUtils.isBlank(queryBean.getCustId()))
            throw new BizException("客户编号不能为空！");
        return bizTdCustInfoService.queryAccount(queryBean);
    }

    @PostMapping("/customer/custinfo/validatemobile")
    @ApiOperation(value = "验证手机号码")
    public ResultData validateMobile(@RequestBody SendMessageAO sendMessageAO) {
        return bizTdCustInfoService.validateMobile(sendMessageAO);
    }

    @PostMapping("/customer/custinfo/validateemail")
    @ApiOperation(value = "验证邮箱")
    public ResultData validateEmail(@RequestBody SendMessageAO sendMessageAO) {
        return bizTdCustInfoService.validateEmail(sendMessageAO);
    }

}
