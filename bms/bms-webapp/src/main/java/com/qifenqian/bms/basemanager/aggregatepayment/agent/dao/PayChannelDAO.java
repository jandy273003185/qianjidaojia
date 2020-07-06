package com.qifenqian.bms.basemanager.aggregatepayment.agent.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;

@Repository
public class PayChannelDAO {

	@Autowired AgentMapper agentMapper;

	public List<PayChannelBean> getPayChannelList(PayChannelBean queryBean) {
		// TODO Auto-generated method stub
		return agentMapper.getPayChannelList(queryBean);
	}

	public void addPayChannelInfo(PayChannelBean payChannelBean) {
		agentMapper.addPayChannelInfo(payChannelBean);
		
	}

	public void updatePayChannelInfo(PayChannelBean payChannelBean) {
		// TODO Auto-generated method stub
		agentMapper.updatePayChannelInfo(payChannelBean);
	}

	public void deletePayChannelInfo(String payChannelCode) {
		agentMapper.deletePayChannelInfo(payChannelCode);
		
	}

	

}
