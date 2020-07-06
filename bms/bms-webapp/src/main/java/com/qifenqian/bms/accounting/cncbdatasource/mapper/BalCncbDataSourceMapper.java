package com.qifenqian.bms.accounting.cncbdatasource.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.cncbdatasource.bean.BalCncbDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;
@MapperScanCombinedmaster
public interface BalCncbDataSourceMapper {	
	public List<BalCncbDataSource> selectCncbDataSourceList(BalCncbDataSource cncbDataSource);
}
