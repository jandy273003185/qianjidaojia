package com.qifenqian.bms.accounting.checkquery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultEqual;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface BalSevenResultEqualMapper {
	/**
	 * 交广科技对账一致报表
	 * @param resultEqual
	 * @return
	 */
	 List<BalSevenResultEqual> selectFitList(BalSevenResultEqual resultEqual);
}
