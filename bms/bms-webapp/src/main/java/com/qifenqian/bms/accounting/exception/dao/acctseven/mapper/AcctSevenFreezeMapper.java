package com.qifenqian.bms.accounting.exception.dao.acctseven.mapper;

import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenFreeze;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 客户账户冻结/解冻：acct_seven_cust_freeze_flow
 * 
 * @project sevenpay-bms-web
 * @fileName AcctSevenFreezeMapper.java
 * @author chonggan.shen
 * @date 2016年07月08日
 * @memo
 */
@MapperScanCore
public interface AcctSevenFreezeMapper {
	/**
	 * 查询客户账户信息
	 * 
	 * @param transFlowId
	 * @return
	 */
	public AcctSevenFreeze selectCustFreezeByFLowId(String transFlowId);

	int updateCustFreezeFlow(AcctSevenFreeze updateBean);
}
