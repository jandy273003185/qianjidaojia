package com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizPayProdService extends BaseService {

    @Autowired
    private AgentMapper agentMapper;

    public PageInfo<PayProdBean> list(PayProdBean queryBean) {
        List<PayProdBean> payProdList = agentMapper.getPayProdList(queryBean);
        return new PageInfo<>(payProdList);
    }

    public ResultData add(PayProdBean payProdBean) {
        try {
            agentMapper.addPayProdInfo(payProdBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(PayProdBean payProdBean) {
        try {
            agentMapper.updatePayProdInfo(payProdBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(PayProdBean payProdBean) {
        try {
            agentMapper.deletePayProdInfo(payProdBean.getProdCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

}
