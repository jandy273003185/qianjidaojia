package com.qifenqian.bms.busswithdrawbill.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.busswithdrawbill.bean.TdBussWithdrawBill;
@MapperScan
public interface BussWithdrawBillMapper {
	public void insertWithdrawBill(TdBussWithdrawBill withdrawBill);

	public String selectBatchBank(@Param("custId") String custId);
}
