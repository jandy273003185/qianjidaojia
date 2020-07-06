package com.qifenqian.bms.paymentmanager.topay.service;

import java.util.List;

import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;

public interface PaymentQueryService {
	
		public List<TdPaymentBatInfo> getPaymetList(TdPaymentBatInfo paymentBatInfo);
		
		public List<TdPaymentBatDetail> getPaymetbatNoList(String batNo);
		
		public List<TdPaymentBatInfo> exportPaymentList(TdPaymentBatInfo paymentBatInfo);
		
		public List<TdPaymentBatDetail> exportPaymentInfoList(TdPaymentBatDetail paymentBatDetail);
		
		public List<TdPaymentBatDetail> selectPaymetbatNoList(TdPaymentBatDetail paymentBatDetail);
}
