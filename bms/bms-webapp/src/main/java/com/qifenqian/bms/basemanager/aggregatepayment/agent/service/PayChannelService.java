package com.qifenqian.bms.basemanager.aggregatepayment.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.dao.PayChannelDAO;
@Service
public class PayChannelService {
	@Autowired
	private PayChannelDAO payChannelDAO;
	
	public List<PayChannelBean> getPayChannelList(PayChannelBean queryBean) {
		// TODO Auto-generated method stub
		return payChannelDAO.getPayChannelList(queryBean);
	}
	public void addPayChannelInfo(PayChannelBean payChannelBean) {
		// TODO Auto-generated method stub
		payChannelDAO.addPayChannelInfo(payChannelBean);
	}
	public void updatePayChannelInfo(PayChannelBean payChannelBean) {
		// TODO Auto-generated method stub
		payChannelDAO.updatePayChannelInfo(payChannelBean);
	}
	public void deletePayChannelInfo(String payChannelCode) {
		// TODO Auto-generated method stub
		payChannelDAO.deletePayChannelInfo(payChannelCode);
	}
	
}