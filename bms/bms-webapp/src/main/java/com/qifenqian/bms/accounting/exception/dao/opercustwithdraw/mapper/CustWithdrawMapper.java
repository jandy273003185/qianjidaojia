package com.qifenqian.bms.accounting.exception.dao.opercustwithdraw.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.opercustwithdraw.bean.CustWithdrawBean;
/***
 * 
 * @author shen
 *
 */
@MapperScan
public interface CustWithdrawMapper {
	/***
	 * 查询提现申请信息
	 * @param withdrawSn
	 * @return
	 */
	CustWithdrawBean selectCustWithdrawBySn(String withdrawSn);
	
	/**
	 * 查询提现信息
	 * @param withdrawReqserialid
	 * @return
	 */
	CustWithdrawBean selectCustWithdrawChildByWithdrawReqserialid(String withdrawReqserialid);
	
	/**
	 * 修改客户提现申请
	 * @param updateCustWithdraw
	 * @return
	 */
	int updateCustWithdrawBySn(CustWithdrawBean updateCustWithdraw);
	
	/**
	 * 修改客户提现/撤销
	 * @param updateCustWithdraw
	 * @return
	 */
	int updateCustWithdrawByWithdrawReqSerialId(CustWithdrawBean updateCustWithdraw);

	
}
