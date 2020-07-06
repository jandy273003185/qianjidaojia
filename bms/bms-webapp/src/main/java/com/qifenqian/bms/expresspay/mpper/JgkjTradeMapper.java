package com.qifenqian.bms.expresspay.mpper;

import java.util.List;

import com.qifenqian.bms.common.annotation.MapperScanCore;
import com.qifenqian.bms.expresspay.tradeResult.bean.JgkjTrade;

@MapperScanCore
public interface JgkjTradeMapper {

	List<JgkjTrade> queryJgkjTradeList(JgkjTrade queryBean);
}
