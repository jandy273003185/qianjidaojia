package com.qifenqian.bms.basemanager.aggregatepayment.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.dao.PayProdDAO;
@Service
public class PayProdService {
	@Autowired
	private PayProdDAO payProdDAO;
	
	public List<PayProdBean> getPayProdList(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		return payProdDAO.getPayProdList(payProdBean);
	}
	public void addPayProdInfo(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		payProdDAO.addPayProdInfo(payProdBean);
	}
	public void updatePayProdInfo(PayProdBean payProdBean) {
		// TODO Auto-generated method stub
		payProdDAO.updatePayProdInfo(payProdBean);
	}
	public void deletePayProdInfo(String prodCode) {
		// TODO Auto-generated method stub
		payProdDAO.deletePayProdInfo(prodCode);
	}
	
}