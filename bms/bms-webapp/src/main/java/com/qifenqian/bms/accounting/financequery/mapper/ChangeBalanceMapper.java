package com.qifenqian.bms.accounting.financequery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.financequery.bean.ChangeBalance;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface ChangeBalanceMapper {
	public List<ChangeBalance>changeBalanceList(ChangeBalance changeBalance);
	public List<ChangeBalance>changeBalanceSum(ChangeBalance changeBalance);
	
}
