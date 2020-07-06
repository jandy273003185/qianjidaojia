package com.qifenqian.bms.accounting.exception.dao.opercharge.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.opercharge.bean.OperCharge;
import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;

/**
 * 充值订单：td_charge_bill
 * 
 * @project sevenpay-bms-web
 * @fileName OperChargeMapper.java
 * @author huiquan.ma
 * @date 2015年10月20日
 * @memo 
 */
@MapperScan
public interface OperChargeMapper {

	/**
	 * 查询充值订单
	 * @param chargeSn
	 * @return
	 */
	OperCharge selectChargeBillBySn(String chargeSn);
	
	/**
	 * 更新充值状态
	 * @param chargeSn
	 * @return
	 */
	int updateChargeBillNetpayState(RechargeBean updateBean);
	
	/**
	 * 待提交隔日自动处理成“取消”状态
	 * @return
	 */
	int updateChargeBillTimeOut(@Param("timeOutMinute")int timeOutMinute);

}


