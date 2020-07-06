package com.qifenqian.bms.v2.biz.login.merchant.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TdFinanceInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TdShopmanagerInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.CashierManageMapper;
import com.qifenqian.bms.basemanager.sysuser.bean.SysUser;
import com.qifenqian.bms.basemanager.sysuser.mapper.SysUserMapper;
import com.qifenqian.bms.basemanager.tdsalesmaninf.bean.TdSalesmanInfo;
import com.qifenqian.bms.basemanager.tdsalesmaninf.mapper.TdSalesmanInfoMapper;
import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import com.qifenqian.bms.userLoginRelate.mapper.TdFinanceInfoMapper;
import com.qifenqian.bms.userLoginRelate.mapper.TdShopmanagerInfoMapper;
import com.qifenqian.bms.userLoginRelate.mapper.UserLoginRelateMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/29 10:20
 */
@Service
public class BizUserLoginRelateService extends BaseService {

    @Autowired
    private UserLoginRelateMapper userLoginRelateMapper;
    @Autowired
    private TdCustInfoMapper custInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TdSalesmanInfoMapper salesmanInfoMapper;
    @Autowired
    private CashierManageMapper cashierManageMapper;
    @Autowired
    private TdFinanceInfoMapper tdFinanceInfoMapper;
    @Autowired
    private TdShopmanagerInfoMapper tdShopmanagerInfoMapper;

    public PageInfo<UserLoginRelate> findUserLoginRelateList(UserLoginRelate userLoginRelate) {
        List<UserLoginRelate> userLoginRelateList = userLoginRelateMapper.selectUserLoginRelateList(userLoginRelate);
        if (userLoginRelateList != null && userLoginRelateList.size() > 0) {
            for (int i = 0; i < userLoginRelateList.size(); i++) {
                TdCustInfo custInfo = custInfoMapper.selectById(userLoginRelateList.get(i).getCustId());
                if (custInfo != null) {
                    userLoginRelateList.get(i).setCustName(custInfo.getCustName());
                }
                if ("cust".equals(userLoginRelateList.get(i).getUserType())) {
                    SysUser selectUserById = sysUserMapper.selectDeptByUserCode(userLoginRelateList.get(i).getUserId());
                    if (selectUserById != null) {
                        userLoginRelateList.get(i).setUserName(selectUserById.getUserName());
                    }
                } else if ("salesman".equals(userLoginRelateList.get(i).getUserType())) {
                    TdSalesmanInfo selectTdSalesmanInfoById = salesmanInfoMapper.selectTdSalesmanInfoById(userLoginRelateList.get(i).getUserId());
                    if (selectTdSalesmanInfoById != null) {
                        userLoginRelateList.get(i).setUserName(selectTdSalesmanInfoById.getUserName());
                    }
                } else if ("cashier".equals(userLoginRelateList.get(i).getUserType())) {
                    CashierInfo cashierInfo = cashierManageMapper.getMyCashierRef(userLoginRelateList.get(i).getUserId());
                    if (cashierInfo != null) {
                        userLoginRelateList.get(i).setUserName(cashierInfo.getCashierName());
                    }
                } else if ("finance".equals(userLoginRelateList.get(i).getUserType())) {
                    TdFinanceInfo tdFinanceInfo = tdFinanceInfoMapper.selectByFinanceId(userLoginRelateList.get(i).getUserId());
                    if (tdFinanceInfo != null) {
                        userLoginRelateList.get(i).setUserName(tdFinanceInfo.getFinanceName());
                    }
                } else if ("shopmanager".equals(userLoginRelateList.get(i).getUserType())) {
                    TdShopmanagerInfo tdShopmanagerInfo = tdShopmanagerInfoMapper.selectByShopmanagerId(userLoginRelateList.get(i).getUserId());
                    if (tdShopmanagerInfo != null) {
                        userLoginRelateList.get(i).setUserName(tdShopmanagerInfo.getShopmanagerName());
                    }
                } else {
                    TdCustInfo custInfo2 = custInfoMapper.selectById(userLoginRelateList.get(i).getUserId());
                    if (custInfo2 != null) {
                        userLoginRelateList.get(i).setUserName(custInfo2.getCustName());
                    }
                }
            }
        }
        return new PageInfo<>(userLoginRelateList);
    }

    public List<TdCustInfo> findTdCustInfoList() {
        return custInfoMapper.selectByMerchantFlag("3");
    }

    public ResultData add(UserLoginRelate userLoginRelate) {
        UserLoginRelate userLoginRelate2 = new UserLoginRelate();
        userLoginRelate2.setUserId(userLoginRelate.getUserId());
        UserLoginRelate selectUserLoginRelate = userLoginRelateMapper.selectUserLoginRelateById(userLoginRelate2);
        if (selectUserLoginRelate != null) {
            throw new BizException("该商户产品已经存在!");
        }
        userLoginRelate.setIfUnbind("1");//默认绑定
        userLoginRelate.setCreateTime(new Date());
        userLoginRelate.setUpdateTime(new Date());
        int result = userLoginRelateMapper.insertUserLoginRelate(userLoginRelate);
        if (result < 1) {
            throw new BizException("添加商户产品异常!");
        }
        return ResultData.success();
    }

    public ResultData delete(UserLoginRelate userLoginRelate) {
        int result = userLoginRelateMapper.updateUserLoginRelate(userLoginRelate);
        if (result < 1) {
            throw new BizException("删除商户产品异常!");
        }
        return ResultData.success();
    }
}
