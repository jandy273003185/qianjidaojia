package com.qifenqian.bms.basemanager.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.trade.bean.AllTradeBill;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

/**
 * 交易信息
 * 
 * @project sevenpay-bms-web
 * @fileName TdTradeBillMainMapper
 * @author Dayet
 * @date 2015年6月26日
 * @memo 
 */

@MapperScanPayment
public interface AllTradeBillMapper {
	/** 查询所有交易**/
	public List<AllTradeBill> getAllTradeBill(@Param("bank")String bank,@Param("payProd")String payProd,@Param("payChannel")String payChannel);

	public List<AllTradeBill> getAllTradeBillExport(@Param("bank")String bank,@Param("payProd")String payProd,@Param("payChannel")String payChannel);
	
}
