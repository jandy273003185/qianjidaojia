package com.qifenqian.bms.v2.biz.merchant.acctsevenbuss.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 商户账号信息
 * @date 2020/4/20 11:50
 */
@Service
public class BizAcctSevenBussService extends BaseService {
    @Autowired
    private AcctSevenBussMapper acctSevenBussMapper;

    public PageInfo<AcctSevenBuss> findAcctSevenBussList(AcctSevenBuss queryBean) {
        List<AcctSevenBuss> acctSevenBusses = acctSevenBussMapper.queryAcctSevenBuss(queryBean);
        return new PageInfo<>(acctSevenBusses);
    }

    public ResultData update(AcctSevenBuss acctSevenBuss) {
        int result = acctSevenBussMapper.updateAcctSevenBuss(acctSevenBuss);
        if (result < 1) {
            return ResultData.error("保存修改异常!");
        }
        return ResultData.success();
    }
}
