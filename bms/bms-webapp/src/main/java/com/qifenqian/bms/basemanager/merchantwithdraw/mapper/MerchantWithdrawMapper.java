package com.qifenqian.bms.basemanager.merchantwithdraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdrawExcel;

@MapperScan
public interface MerchantWithdrawMapper {

	List<MerchantWithdraw> selectMerchantWithdrawList(MerchantWithdraw withdrawBean);
	
	List<MerchantWithdrawExcel> selectWithdrawExcelByMerchant(MerchantWithdraw withdrawBean);
	
	int merchantWithdrawAudit(MerchantWithdraw withdraw);

	int merchantWithdrawVerification(MerchantWithdraw withdrawSn);
	
	String getAcctIdByCustId(@Param("custId") String custId);
	
	/**
	 * 修改商户提现流水表核销状态
	 * @param id
	 */
	public void updateMerchantWithdraw(List<String> id );
}
