package com.qifenqian.bms.accounting.refund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.refund.bean.TradeFlow;


@MapperScan
public interface TradeFlowMapper {
	
	TradeFlow selectTransFlowById(@Param("msgId") String msgId);
	
	TradeFlow selectTransFlowByRecharge(@Param("msgId") String msgId);

	List<TradeFlow> selectTransferFlowById(@Param("msgId") String msgId);
	
	
}
