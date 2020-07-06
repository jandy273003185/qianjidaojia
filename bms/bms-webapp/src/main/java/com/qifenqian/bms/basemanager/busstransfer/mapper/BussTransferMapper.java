package com.qifenqian.bms.basemanager.busstransfer.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferBean;
import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferExcel;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;

/**
 * 商户转账
 * 
 * @project sevenpay-bms-web
 * @fileName BussTransferMapper
 * @author shen
 * @date 2016年10月20日
 * @memo
 */

@MapperScan
public interface BussTransferMapper {

	/**
	 * 查询转账信息
	 * 
	 * @param transferBean
	 * @return
	 */
	public List<BussTransferBean> selectTransfer(BussTransferBean bussTransferBean);

	/**
	 * 查询转账导出信息
	 * 
	 * @param transferBean
	 * @return
	 */
	public List<BussTransferExcel> selectTransferExcel(BussTransferBean bussTransferBean);
	
	/**
	 * 
	 * @param orderId
	 * @return
	 */
	TdTradeBillMainVO selectTransferByOrderId(String orderId);

}
