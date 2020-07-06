package com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.bean.ClearJgkjTrade;
import com.qifenqian.bms.common.annotation.MapperScanCore;
@MapperScanCore
public interface ClearJgkjTradeMapper {
	List<ClearJgkjTrade> selectClearJgkjTradeList(ClearJgkjTrade clearJgkjTrade);
	
}
