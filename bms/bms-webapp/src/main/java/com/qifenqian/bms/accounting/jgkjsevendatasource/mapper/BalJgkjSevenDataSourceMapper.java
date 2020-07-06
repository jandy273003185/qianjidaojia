package com.qifenqian.bms.accounting.jgkjsevendatasource.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.jgkjsevendatasource.bean.BalJgkjSevenDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface BalJgkjSevenDataSourceMapper {	
	public List<BalJgkjSevenDataSource> selectSevenDataSourceList(BalJgkjSevenDataSource sevenDataSource);
}
