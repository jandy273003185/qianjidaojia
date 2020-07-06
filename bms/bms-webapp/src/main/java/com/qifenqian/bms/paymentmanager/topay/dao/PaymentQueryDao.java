package com.qifenqian.bms.paymentmanager.topay.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.topay.mapper.PaymentQueryMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class PaymentQueryDao {
	@Autowired
	private PaymentQueryMapper paymentQueryMapper ;
	@Page
    public List<TdPaymentBatInfo> getPaymetList(TdPaymentBatInfo paymentBatInfo) {
		
		return paymentQueryMapper.getPaymetList(paymentBatInfo);
	}
	
  @Page	
  public List<TdPaymentBatDetail> getPaymetbatNoList(String batNo) {
		
		return paymentQueryMapper.getPaymetbatNoList(batNo);
	}
  
  public List<TdPaymentBatInfo> exportPaymentList(TdPaymentBatInfo paymentBatInfo) {
		
		return paymentQueryMapper.exportPaymentList(paymentBatInfo);
	}
  
  public List<TdPaymentBatDetail> exportPaymentInfoList(TdPaymentBatDetail paymentBatDetail) {
		
		return paymentQueryMapper.exportPaymentInfoList(paymentBatDetail);
	}
  @Page
  public List<TdPaymentBatDetail> selectPaymetbatNoList(TdPaymentBatDetail paymentBatDetail) {
		
		return paymentQueryMapper.selectPaymetbatNoList(paymentBatDetail);
	}
}
