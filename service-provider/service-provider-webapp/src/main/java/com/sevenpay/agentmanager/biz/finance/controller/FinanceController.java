package com.sevenpay.agentmanager.biz.finance.controller;


import com.qifenqian.app.bean.customer.FinanceInfo;
import com.sevenpay.agentmanager.biz.finance.service.FinanceServiceImpl;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.controller.AbstractBaseController;
import com.sevenpay.agentmanager.core.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 财务员
 */
@RestController
@RequestMapping("finance")
public class FinanceController extends AbstractBaseController {


    @Autowired
    FinanceServiceImpl financeService;


    /**
     * 服务商添加财务员
     *
     * @param financeInfo
     * @return
     */
    @PostMapping(value = "addFinance")
    public ResultData addFinance(FinanceInfo financeInfo) {

        if (StringUtils.isBlank(financeInfo.getFinanceMobile())) {
            throw new BizException("手机不能为空~");
        }
        if (StringUtils.isBlank(financeInfo.getFinanceName())) {
            throw new BizException("姓名不能为空~");
        }
        if (StringUtils.isBlank(financeInfo.getCustId())) {
            throw new BizException("服务商id不能为空~");
        }
        if (StringUtils.isBlank(financeInfo.getQueryAuth())) {
            throw new BizException("查询权限不能为空~");
        }

        return financeService.addFinance(financeInfo);

    }


    /**
     * 财务员列表查询（手机或商户编号）
     *
     * @param custId
     * @return
     */
    @PostMapping(value = "getFinanceList")
    public ResultData getFinanceList(String custId) {

        if (StringUtils.isBlank(custId)) {
            throw new BizException("服务商id不能为空~");
        }

        FinanceInfo financeInfo = new FinanceInfo();
        financeInfo.setCustId(custId);

        return financeService.getFinanceList(financeInfo);
    }


    /**
     * 财务员修改密码、退款密码
     *
     * @param financeId
     * @param refundPw
     * @param refundNewPw
     * @param loginPw
     * @param loginNewPw
     * @return
     */
    @PostMapping(value = "updateFinancePw")
    public ResultData updateFinancePw(String financeId, String refundPw, String refundNewPw, String loginPw, String loginNewPw) {
        if (StringUtils.isBlank(financeId)) {
            throw new BizException("财务员id不能为空~");
        }
        if (StringUtils.isBlank(loginPw)) {
            throw new BizException("原密码不能为空~");
        }
        if (StringUtils.isBlank(loginNewPw)) {
            throw new BizException("新密码不能为空~");
        }
        return financeService.updateFinancePw(financeId, refundPw, refundNewPw, loginPw, loginNewPw);

    }


    /**
     * 重置密码
     *
     * @param financeInfo
     * @return
     */
    @PostMapping(value = "resetFinancePw")
    public ResultData resetFinancePw(FinanceInfo financeInfo) {
        if (StringUtils.isBlank(financeInfo.getFinanceId())) {
            throw new BizException("财务员id不能为空~");
        }
        financeInfo.setLoginPw("123456");
        return financeService.updateFinance(financeInfo);
    }


    /**
     * @param mobile
     * @param custId
     * @return
     */
    @PostMapping(value = "validate")
    public ResultData validate(String mobile, String custId) {
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("手机号不能为空~");
        }
        if (StringUtils.isBlank(custId)) {
            throw new BizException("custId不能为空~");
        }

        return financeService.validate(mobile, custId);
    }


    /**
     * 修改状态
     *
     * @param financeInfo
     * @return
     */
    @PostMapping(value = "updateFinance")
    public ResultData updateFinance(FinanceInfo financeInfo) {

        return financeService.updateFinance(financeInfo);

    }


    /**
     * 解绑财务员
     *
     * @param financeInfo
     * @return
     */
    @PostMapping("updateTdFinanceInfo")
    public ResultData updateTdFinanceInfoById(FinanceInfo financeInfo) {
        if (StringUtils.isBlank(financeInfo.getFinanceId())) {
            throw new BizException("财务员id不能为空");
        }
//        if(StringUtils.isBlank(financeInfo.getCustId())){
//            throw new BizException("商户id不能为空");
//        }
        return financeService.updateTdFinanceInfoById(financeInfo);
    }


    /**
     * 查看财务员详情
     *
     * @param financeId
     * @return
     */
    @PostMapping(value = "getFinanceInfo")
    public ResultData queryFinanceInfoByFinanceId(String financeId) {

        if (StringUtils.isBlank(financeId)) {
            throw new BizException("财务员id不能为空~");
        }
        return financeService.queryFinanceInfoByFinanceId(financeId);

    }


}
