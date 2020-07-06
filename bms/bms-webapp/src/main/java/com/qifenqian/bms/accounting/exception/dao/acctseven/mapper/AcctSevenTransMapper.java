package com.qifenqian.bms.accounting.exception.dao.acctseven.mapper;

import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 商户账：acct_seven_buss_flow
 * 客户账：acct_seven_cust_flow
 * 内部账：acct_seven_inner_flow
 * 
 * @project sevenpay-bms-web
 * @fileName AcctSevenTransMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
@MapperScanCore
public interface AcctSevenTransMapper {
	/**
	 * 查询客户账户信息
	 * @param transFlowId
	 * @return
	 */
	public AcctSevenTrans selectCustAccountByFLowId(String transFlowId);
	
	/**
	 * 查询流水信息
	 * @param transFlowId
	 * @return
	 */
	public AcctSevenTrans selectAcctSevenBussFlow(String OrderId);
	
	/**
	 * 查询商户账户信息
	 * @param transFlowId
	 * @return
	 */
	public AcctSevenTrans selectBussAccountByFLowId(String transFlowId);
	/**
	 * 查询内部账户信息
	 * @param transFlowId
	 * @return
	 */
	public AcctSevenTrans selectInnerAccountByFLowId(String transFlowId);
	
	/**
	 * 修改客户账户信息
	 * @param updateBean
	 * @return
	 */
	int updateCustAccountFlow(AcctSevenTrans updateBean);
	
	/**
	 * 修改商户账户信息
	 * @param updateBean
	 * @return
	 */
	int updateBussAccountFlow(AcctSevenTrans updateBean);
	
	/**
	 * 修改流水表产品代码和渠道代码
	 * @param updateBean
	 * @return
	 */
	int updateBussAccountFlowProCha(AcctSevenTrans updateBean);
	
	/**
	 * 修改内部账户信息
	 * @param updateBean
	 * @return
	 */
	int updateInnerAccountFlow(AcctSevenTrans updateBean);

	
}



