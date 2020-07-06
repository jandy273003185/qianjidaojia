package com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizPayChannelService extends BaseService {

    @Autowired
    private AgentMapper agentMapper;

    public PageInfo<PayChannelBean> list(PayChannelBean queryBean) {
        List<PayChannelBean> payChannelList = agentMapper.getPayChannelList(queryBean);
        return new PageInfo<>(payChannelList);
    }

    public ResultData add(PayChannelBean payChannelBean) {
        try {
            agentMapper.addPayChannelInfo(payChannelBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(PayChannelBean payChannelBean) {
        try {
            agentMapper.updatePayChannelInfo(payChannelBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(PayChannelBean payChannelBean) {
        try {
            agentMapper.deletePayChannelInfo(payChannelBean.getPayChannelCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

}
