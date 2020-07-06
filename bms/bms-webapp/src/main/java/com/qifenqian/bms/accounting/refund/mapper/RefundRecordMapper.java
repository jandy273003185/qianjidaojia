package com.qifenqian.bms.accounting.refund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.refund.bean.RefundRecord;

@MapperScan
public interface RefundRecordMapper {

	int insert(RefundRecord insertBean);

	int update(RefundRecord insertBean);
	
	List<RefundRecord> selectRefundRecordByOrderId(@Param("orderId") String orderId);
}
