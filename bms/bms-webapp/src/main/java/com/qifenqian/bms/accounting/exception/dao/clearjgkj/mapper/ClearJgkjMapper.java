package com.qifenqian.bms.accounting.exception.dao.clearjgkj.mapper;

import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkj;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkjTransfer;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 交广科技账户冻结：clear_jgkj_freeze
 * 账户更改：clear_jgkj_modify
 * 交易退款/撤销：clear_jgkj_reback
 * 注册：clear_jgkj_register
 * 充值/消费：clear_jgkj_trade
 * 
 * @project sevenpay-bms-web
 * @fileName ClearJgkjMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
@MapperScanCore
public interface ClearJgkjMapper {
	
	/**
	 * 查询交广科技账户冻结信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjFreezeByFLowId(String transFlowId);
	
	/**
	 * 查询交广账户修改信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjModifyByFLowId(String transFlowId);
	
	/**
	 * 查询交广交易退款/撤销信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjRebackByFLowId(String transFlowId);
	
	/**
	 * 查询交广注册信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjRegisterByFLowId(String transFlowId);	
	
	/**
	 * 查询交广交易信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjTradeByFLowId(String transFlowId);
	
	/**
	 * 查询交广充值信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectJgkjRechargeByFLowId(String transFlowId);
	
	/**
	 * 查询交广转账信息
	 * @param transFlowId
	 * @return
	 */
	ClearJgkjTransfer selectJgkjTransferByFLowId(String transFlowId);
	
	/**
	 * 修改交广科技交易信息
	 * @param updateBean
	 * @return
	 */
	int updateClearJgkjTrade(ClearJgkj updateBean);
	
	/**
	 * 修改交广充值信息
	 * @param updateBean
	 * @return
	 */
	int updateClearJgkjRecharge(ClearJgkj updateBean);
	
	/**
	 * 修改交广退款/撤销信息
	 * @param updateBean
	 * @return
	 */
	int updateClearJgkjReback(ClearJgkj updateBean);
	
	/**
	 * 修改转账信息
	 * @param transFlowId
	 * @return
	 */
	int updateJgkjTransfer(ClearJgkjTransfer updateTransferBean);
	
	/**
	 * 修改转账信息
	 * @param transFlowId
	 * @return
	 */
	int updateJgkjFreeze(ClearJgkj updateFreezeBean);
	
	/**
	 * 查询卡号
	 * @param transFlowId
	 * @return
	 */
	ClearJgkj selectCardNoByTransFlowId(String transFlowId);
}


