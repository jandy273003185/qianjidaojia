package com.qifenqian.bms.unionPay.tradedoc.mapper;

import java.util.List;

import com.qifenqian.bms.common.annotation.MapperScanCore;
import com.qifenqian.bms.unionPay.tradedoc.bean.TradeDoc;

@MapperScanCore
public interface TradeDocMapper {
	List<TradeDoc> selectDocList(TradeDoc tradeDoc);
}
