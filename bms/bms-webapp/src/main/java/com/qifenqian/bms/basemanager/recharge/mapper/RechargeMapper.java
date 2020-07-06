package com.qifenqian.bms.basemanager.recharge.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;
import com.qifenqian.bms.basemanager.recharge.bean.RechargeExcel;
import com.qifenqian.bms.common.annotation.MapperScanSub;

/**
 * 充值信息
 * 
 * @project sevenpay-bms-web
 * @fileName RechargeRevokeMapper
 * @author Dayet
 * @date 2015年6月26日
 * @memo 
 */

@MapperScanSub
public interface RechargeMapper {
	
	/**
	 * 查询充值信息
	 * @param recharge
	 * @return
	 */
	public List<RechargeBean> selectRecharge(RechargeBean recharge);
	
	/**
	 * 查询充值EXCEL信息
	 * @param recharge
	 * @return
	 */
	public List<RechargeExcel> selectRechargeExcel(RechargeBean recharge);
	
	
	public RechargeBean selectRechargeBeanByChargeSn(String chargeSn);
}
