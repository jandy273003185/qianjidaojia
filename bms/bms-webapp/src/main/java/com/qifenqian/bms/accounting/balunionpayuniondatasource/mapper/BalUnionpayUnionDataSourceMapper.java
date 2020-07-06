package com.qifenqian.bms.accounting.balunionpayuniondatasource.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpayUnionDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface BalUnionpayUnionDataSourceMapper {
	public List<BalUnionpayUnionDataSource> selectUnionpayUnionDataSourceList(BalUnionpayUnionDataSource source);
	
}
