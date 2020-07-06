package com.qifenqian.bms.accounting.refund.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.refund.bean.RefundRecord;
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;


@MapperScan
public interface RebackRecordMapper {

	int countOriginRelateSuccessOrUnknowTrans(String originMsgId);
	
	/**
	 * 查询退款总额
	 * @param originMsgId
	 * @return
	 */
	RefundRecord countRebackAmtSuccessOrUnknowTrans(String originMsgId);
	
	/**
	 * 查询商户余额
	 * @param acctId
	 * @return
	 */
	AcctSevenBuss queryAcctBussByMerchantCode(String merchantCode);
	
}
