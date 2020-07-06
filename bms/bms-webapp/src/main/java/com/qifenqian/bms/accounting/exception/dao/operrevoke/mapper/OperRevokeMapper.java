package com.qifenqian.bms.accounting.exception.dao.operrevoke.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.dao.operrevoke.bean.OperRevoke;

/**
 * 
 * @author shen
 * 
 */
@MapperScan
public interface OperRevokeMapper {

	/**
	 * 查询交易撤销数据
	 * 
	 * @param orderId
	 * @return
	 */
	OperRevoke selectRevokeBillById(String orderId);

	/***
	 * 更新交易撤销数据
	 * 
	 * @param updateBean
	 * @return
	 */
	int updatePaymentRevoke(OperRevoke updateBean);

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	OperRevoke selectRechargeRevokeById(String orderId);

	/**
	 * 
	 * @param updateBean
	 * @return
	 */
	int updateRechargeRevoke(OperRevoke updateBean);

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	Operation selectTransferRevokeById(String orderId);

	/**
	 * 
	 * @param transferRevokeBean
	 * @return
	 */
	int updateTransferRevokeState(OperRevoke transferRevokeBean);

}
