package com.qifenqian.bms.v2.biz.basedata.payin.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.payIn.bean.PayIn;
import com.qifenqian.bms.basemanager.payIn.mapper.PayInMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 16:19
 */
@Service
public class BasePayInService extends BaseService {

    @Autowired
    private PayInMapper payInMapper;

    public PageInfo<PayIn> findPayInList(PayIn payIn) {
        List<PayIn> payIns = payInMapper.selectPayIns(payIn);
        return new PageInfo<>(payIns);
    }

    public ResultData add(PayIn payIn) {
        PayIn exist = payInMapper.selectPayInByFeeCode(payIn.getFeeCode());
        if (exist != null) {
            throw new BizException("该费用代码已经占用!");
        }
        try {
            payInMapper.insertPayIn(payIn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("添加保存信息异常!");
        }
        return ResultData.success();
    }

    public ResultData update(PayIn payIn) {
        try {
            payInMapper.updatePayIn(payIn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改信息异常!");
        }
        return ResultData.success();
    }

    public ResultData stop(PayIn payIn) {
        try {
            payInMapper.stopPayInByCode(payIn.getFeeCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("停用信息异常!");
        }
        return ResultData.success();
    }
}
