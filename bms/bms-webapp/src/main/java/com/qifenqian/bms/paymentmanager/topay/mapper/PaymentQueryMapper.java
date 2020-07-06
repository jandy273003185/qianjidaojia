package com.qifenqian.bms.paymentmanager.topay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;

@MapperScan
public interface PaymentQueryMapper {
	
	 public List<TdPaymentBatInfo> getPaymetList(TdPaymentBatInfo paymentBatInfo);
	 
	 public List<TdPaymentBatDetail> getPaymetbatNoList(@Param("batNo")String batNo);
	 
	 public List<TdPaymentBatInfo> exportPaymentList(TdPaymentBatInfo paymentBatInfo);
	 
	 public List<TdPaymentBatDetail> exportPaymentInfoList(TdPaymentBatDetail paymentBatDetail);
	 
	 public List<TdPaymentBatDetail> selectPaymetbatNoList(TdPaymentBatDetail paymentBatDetail);
}
