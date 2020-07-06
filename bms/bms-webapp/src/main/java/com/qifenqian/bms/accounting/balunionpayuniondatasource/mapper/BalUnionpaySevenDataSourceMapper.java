package com.qifenqian.bms.accounting.balunionpayuniondatasource.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpaySevenDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCore;
@MapperScanCore
public interface BalUnionpaySevenDataSourceMapper {
	
	public List<BalUnionpaySevenDataSource> selectUnionpaySevenDataSourceList(BalUnionpaySevenDataSource source);
}
