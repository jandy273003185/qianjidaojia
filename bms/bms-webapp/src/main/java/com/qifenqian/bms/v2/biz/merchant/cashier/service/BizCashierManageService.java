package com.qifenqian.bms.v2.biz.merchant.cashier.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.CashierManageMapper;
import com.qifenqian.bms.basemanager.merchant.service.CashierManageService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizCashierManageService extends BaseService {
    @Autowired
    private CashierManageMapper cashierManageMapper;

    @Autowired
    private CashierManageService cashierManageService;


    public PageInfo<CashierInfo> list(CurrentAccount currentAccount, CashierInfo queryBean) {
        // 是否有权限查看所有订单
//        String flag = tdCustInfoMapper.isAllList(queryBean.getUserId());
        List<CashierInfo> list = null;
        if (currentAccount.getLoginId() != null)
            list = cashierManageMapper.getCashierList(queryBean);
//        } else {
//            list = cashierManageMapper.getMyCashierList(queryBean);
//        }
        return new PageInfo<>(list);
    }

    public ResultData delete(CashierInfo cashierInfo) {
        try {
            cashierManageMapper.deleteCashier(cashierInfo.getOnlyId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(CurrentAccount currentAccount, CashierInfo cashierInfo) {
        if ("1".equalsIgnoreCase(cashierInfo.getStatus())) {
            if (cashierManageService.validate(cashierInfo.getCashierMobile(), cashierInfo.getOnlyId())) {
                throw new BizException("该手机号不能设置为本店收银员, 请检查该手机是否已经成为别的门店收银员,或者商户帐号！");
            }
        }
        try {
            cashierInfo.setModifyId(String.valueOf(currentAccount.getLoginId()));
            cashierManageMapper.updateCashier(cashierInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData add(CurrentAccount currentAccount, CashierInfo cashierInfo) {
        cashierInfo.setStatus("1");
        List<CashierInfo> list = cashierManageService.getCashierList(cashierInfo);
        if (list != null && list.size() != 0) {
            throw new BizException("该手机号已注册成该商户的收银员！");
        }
        cashierInfo.setOnlyId(GenSN.getSN());
        cashierInfo.setCreateId(String.valueOf(currentAccount.getLoginId()));
        try {
            cashierManageService.addCashier(cashierInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }


}
