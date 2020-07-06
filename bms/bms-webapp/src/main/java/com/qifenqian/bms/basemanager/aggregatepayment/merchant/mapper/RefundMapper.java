package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdRefund;
import com.qifenqian.bms.common.annotation.MapperScanPayment;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
@MapperScanPayment
public interface RefundMapper {

	@Page
	List<TdRefund> getRefundList(TdRefund bean);
	
	List<TdRefund> getRefundExport(TdRefund bean);
}
