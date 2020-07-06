package com.qifenqian.bms.v2.biz.basedata.withdraw.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;
import com.qifenqian.bms.basemanager.withdrawControl.mapper.WithdrawControlMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 15:39
 */
@Service
public class BaseWithdrawService extends BaseService {

    @Autowired
    private WithdrawControlMapper withdrawControlMapper;

    public PageInfo<WithdrawControl> findWithdrawControlList(WithdrawControl withdrawControl) {
        List<WithdrawControl> withdrawControls = withdrawControlMapper.selectAll(withdrawControl);
        return new PageInfo<>(withdrawControls);
    }

    public ResultData add(WithdrawControl withdrawControl) {
        WithdrawControl exist = withdrawControlMapper.selectWithdrawControl(withdrawControl);
        if (exist != null) {
            if (StringUtils.isNotBlank(exist.getMobile())) {
                throw new BizException("客户 [" + withdrawControl.getMobile() + "]已经存在提现控制信息！");
            }
            if (StringUtils.isNotBlank(exist.getCustId())) {
                throw new BizException("客户 [" + withdrawControl.getCustId() + "]已经存在提现控制信息！");
            }
        }
        if (StringUtils.isBlank(withdrawControl.getCustId()) && StringUtils.isNotBlank(withdrawControl.getMobile())) {
            WithdrawControl queryBean = withdrawControlMapper.selectCustIdByMobile(withdrawControl.getMobile());
            if (queryBean == null || StringUtils.isBlank(queryBean.getCustId())) {
                throw new BizException("客户 [" + withdrawControl.getMobile() + "]在系统中不存在！");
            }
            withdrawControl.setCustId(queryBean.getCustId());
        }
        try {
            withdrawControlMapper.addWithdrawControl(withdrawControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("添加提现控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(WithdrawControl withdrawControl) {
        try {
            withdrawControlMapper.editTradeControl(withdrawControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改提现控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(WithdrawControl withdrawControl) {
        try {
            withdrawControlMapper.deleteTradeControl(withdrawControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除提现控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }
}
