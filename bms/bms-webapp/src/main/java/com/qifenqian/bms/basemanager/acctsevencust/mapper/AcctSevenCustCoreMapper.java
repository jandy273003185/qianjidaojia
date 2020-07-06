package com.qifenqian.bms.basemanager.acctsevencust.mapper;

import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface AcctSevenCustCoreMapper {

	/**修改核心账户表姓名*/
	public String updateAccountName(AcctSevenCust queryBean);

}
