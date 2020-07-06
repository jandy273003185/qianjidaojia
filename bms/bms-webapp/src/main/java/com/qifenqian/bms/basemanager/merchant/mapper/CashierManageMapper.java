package com.qifenqian.bms.basemanager.merchant.mapper;


import java.util.List;

import com.qifenqian.bms.basemanager.merchant.bean.TdShopStaffRef;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.CashierInfo;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;

@MapperScan
public interface CashierManageMapper {
	/**收银员管理 */
	public List<CashierInfo> getCashierList(CashierInfo cashierInfo);
	
	public List<CashierInfo> getMyCashierList(CashierInfo cashierInfo);
	
	public void addCashier(CashierInfo cashierInfo);
	
	public void deleteCashier(String onlyId);
	
	public TdLoginUserInfo getCustInfoByMobile(@Param("mobile")String mobile);

	public void updateCashier(CashierInfo cashierInfo);
	
	
	public CashierInfo getMyCashierRef(String onlyId);

	public List<CashierInfo> getCashierListExcept(CashierInfo cashierInfo);
	
	/** 查找门店*/
	public List<StoreManage> selectStore(String custId);


	public void addShopStaffRef(TdShopStaffRef tdShopStaffRef);
}
