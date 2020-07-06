package com.qifenqian.bms.basemanager.tradeControl.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;

@MapperScan
public interface TradeControlMapper {

	public List<TradeControl> selectAll(TradeControl tradeControl);
	
	public void addTradeControl(TradeControl tradeControl);
	
	public TradeControl selectTradeControl(TradeControl tradeControl);
	
	public void editTradeControl(TradeControl tradeControl);
	
	public void deleteTradeControl(TradeControl tradeControl);
}
