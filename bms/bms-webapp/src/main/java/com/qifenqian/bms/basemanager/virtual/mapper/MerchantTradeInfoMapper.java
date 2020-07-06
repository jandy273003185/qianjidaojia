package com.qifenqian.bms.basemanager.virtual.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.virtual.bean.MerchantTradeInfo;

@MapperScan
public interface MerchantTradeInfoMapper {

	public void insertInfos(MerchantTradeInfo info) ;
	
	public List<MerchantTradeInfo> getTradeInfos(MerchantTradeInfo info);
	
	public String getTotalAmt(MerchantTradeInfo info);
}
