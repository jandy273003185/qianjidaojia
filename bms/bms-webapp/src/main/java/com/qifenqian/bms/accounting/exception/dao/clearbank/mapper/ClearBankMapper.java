package com.qifenqian.bms.accounting.exception.dao.clearbank.mapper;

import com.qifenqian.bms.accounting.exception.dao.clearbank.bean.ClearBank;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 银行清算：clear_bank
 * @project sevenpay-bms-web
 * @fileName ClearBankMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
@MapperScanCore
public interface ClearBankMapper {
	/**
	 * 查询银行清算信息
	 * @param transFlowId
	 * @return
	 */
	public ClearBank selectBankClearByFLowId(String transFlowId);
	
	/**
	 * 修改银联清算信息
	 * @param clearBank
	 * @return
	 */
	int updateBankClearFlow(ClearBank clearBank);
}


