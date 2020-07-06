package com.qifenqian.bms.accounting.jgkjdatasource.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.jgkjdatasource.bean.BalJgkjDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCore;
@MapperScanCore
public interface BalJgkjDataSourceMapper {	
	public List<BalJgkjDataSource> selectZytDataSourceList(BalJgkjDataSource zytDataSource);
}
