package com.qifenqian.bms.userLoginRelate.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.TdFinanceInfo;


@MapperScan
public interface TdFinanceInfoMapper {
		
	TdFinanceInfo selectByFinanceId(String financeId);

}
