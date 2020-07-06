package com.qifenqian.bms.accounting.exception.dao.clearunionpay.mapper;

import com.qifenqian.bms.accounting.exception.dao.clearunionpay.bean.ClearUnionpay;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 银联清算: clear_unionpay
 * 银联扣款： td_trans_yl
 * 
 * @project sevenpay-bms-web
 * @fileName ClearBankMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
@MapperScanCore
public interface ClearUnionpayMapper {

	/**
	 * 查询银联清算交易
	 * @param transFlowId
	 * @return
	 */
	ClearUnionpay selectUnionpayClearByFLowId(String transFlowId);
}


