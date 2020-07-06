package com.qifenqian.bms.accounting.acctseveninner.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.acctseveninner.bean.AcctSevenInner;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface AcctSevenInnerMapper {

	List<AcctSevenInner> queryAcctSevenInnerList(AcctSevenInner queryBean);

}
