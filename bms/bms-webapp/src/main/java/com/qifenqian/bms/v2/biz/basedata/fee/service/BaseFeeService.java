package com.qifenqian.bms.v2.biz.basedata.fee.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.fee.bean.Fee;
import com.qifenqian.bms.basemanager.fee.mapper.FeeMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 10:49
 */
@Service
public class BaseFeeService extends BaseService {

    @Autowired
    private FeeMapper feeMapper;

    public PageInfo<Fee> findFeeList(Fee fee) {
        List<Fee> fees = feeMapper.selectFees(fee);
        return new PageInfo<>(fees);
    }

    public ResultData add(Fee fee) {
        try {
            feeMapper.insertFee(fee);
        } catch (Exception e) {
            e.getMessage();
            throw new BizException("添加保存信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(Fee fee) {
        try {
            feeMapper.updateFee(fee);
        } catch (Exception e) {
            e.getMessage();
            throw new BizException("更新信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(Fee fee) {
        try {
            feeMapper.deleteFee(fee.getFeeCode());
        } catch (Exception e) {
            e.getMessage();
            throw new BizException("删除信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData validate(Fee fee) {
        Fee existFee = feeMapper.validateFeeCode(fee.getFeeCode());
        if (null != existFee) {
            throw new BizException("费率code已存在!");
        }
        return ResultData.success();
    }
}
