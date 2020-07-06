package com.qifenqian.bms.basemanager.acctsevenbuss.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;

@MapperScan
public interface AcctSevenBussMapper {

	List<AcctSevenBuss> queryAcctSevenBuss(AcctSevenBuss queryBean);

	int updateAcctSevenBuss(AcctSevenBuss updateBean);
	
	/** 根据custId修改账务-商户账户表 */
	public void updateAcctSevenBussByCustId(AcctSevenBuss acctSevenBuss);

}
