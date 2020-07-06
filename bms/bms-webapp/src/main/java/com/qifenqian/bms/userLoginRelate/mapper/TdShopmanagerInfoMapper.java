package com.qifenqian.bms.userLoginRelate.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.TdShopmanagerInfo;


@MapperScan
public interface TdShopmanagerInfoMapper {
		
	TdShopmanagerInfo selectByShopmanagerId(String shopmanagerId);

}
