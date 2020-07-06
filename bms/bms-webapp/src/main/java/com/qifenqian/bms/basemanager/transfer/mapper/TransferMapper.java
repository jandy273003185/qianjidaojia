package com.qifenqian.bms.basemanager.transfer.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.transfer.bean.TransferBean;
import com.qifenqian.bms.basemanager.transfer.bean.TransferExcel;

/**
 * 交易信息
 * 
 * @project sevenpay-bms-web
 * @fileName TdTradeBillMainMapper
 * @author Dayet
 * @date 2015年6月26日
 * @memo
 */

@MapperScan
public interface TransferMapper {

	/**
	 * 查询转账信息
	 * 
	 * @param transferBean
	 * @return
	 */
	public List<TransferBean> selectTransfer(TransferBean transferBean);

	/**
	 * 查询转账导出信息
	 * 
	 * @param transferBean
	 * @return
	 */
	public List<TransferExcel> selectTransferExcel(TransferBean transferBean);

	TdTradeBillMainVO selectTransferByOrderId(String orderId);

}
