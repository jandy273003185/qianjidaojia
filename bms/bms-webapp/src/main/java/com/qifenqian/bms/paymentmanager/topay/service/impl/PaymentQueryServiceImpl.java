package com.qifenqian.bms.paymentmanager.topay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.topay.dao.PaymentQueryDao;
import com.qifenqian.bms.paymentmanager.topay.service.PaymentQueryService;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService{
	
	@Autowired
	private PaymentQueryDao paymentManagerDao;
	@Override
	public List<TdPaymentBatInfo> getPaymetList(TdPaymentBatInfo paymentBatInfo) {
		
		return paymentManagerDao.getPaymetList(paymentBatInfo);
	}
	@Override
	public List<TdPaymentBatDetail> getPaymetbatNoList(String batNo) {
	
		return paymentManagerDao.getPaymetbatNoList(batNo);
	}
	@Override
	public List<TdPaymentBatInfo> exportPaymentList(TdPaymentBatInfo paymentBatInfo) {
		
		return paymentManagerDao.exportPaymentList(paymentBatInfo);
	}
	
	@Override
	public List<TdPaymentBatDetail> exportPaymentInfoList(
			TdPaymentBatDetail paymentBatDetail) {
		
		return paymentManagerDao.exportPaymentInfoList(paymentBatDetail);
	}
	@Override
	public List<TdPaymentBatDetail> selectPaymetbatNoList(
			TdPaymentBatDetail paymentBatDetail) {
		
		return paymentManagerDao.selectPaymetbatNoList(paymentBatDetail);
	}
	

}
