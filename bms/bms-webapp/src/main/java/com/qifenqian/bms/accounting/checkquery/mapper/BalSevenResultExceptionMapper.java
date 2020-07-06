package com.qifenqian.bms.accounting.checkquery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultException;
import com.qifenqian.bms.common.annotation.MapperScanCore;
@MapperScanCore
public interface BalSevenResultExceptionMapper {
	/**
	 * 交广科技对账差错报表
	 * @param resultException
	 * @return
	 */
	List<BalSevenResultException> selectErrorList(BalSevenResultException resultException);
	
}
