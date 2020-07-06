package com.qifenqian.bms.unionPay.tradeylresult.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeYlResut;

@MapperScan
public interface TradeYlResultMapper {
	
	List<TradeYlResut> selectTransYlResut(TradeYlResut queryBean);
	
	int updateTransYlResut(TradeYlResut updateBean);
	
	TradeYlResut selectTransYlResutByTransId(String transId);
	
}
