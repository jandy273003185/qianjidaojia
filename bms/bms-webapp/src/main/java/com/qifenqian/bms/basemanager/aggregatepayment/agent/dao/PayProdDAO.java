package com.qifenqian.bms.basemanager.aggregatepayment.agent.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;

@Repository
public class PayProdDAO {

	@Autowired AgentMapper agentMapper;

	public List<PayProdBean> getPayProdList(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		return agentMapper.getPayProdList(payProdBean);
	}

	public void addPayProdInfo(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		agentMapper.addPayProdInfo(payProdBean);
	}

	public void updatePayProdInfo(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		agentMapper.updatePayProdInfo(payProdBean);
	}

	public void deletePayProdInfo(String prodCode) {
		// TODO Auto-generated method stub
		agentMapper.deletePayProdInfo(prodCode);
	}
	
}
