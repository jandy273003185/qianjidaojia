package com.qifenqian.bms.accounting.checkquery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.checkquery.bean.JgkjResultException;
import com.qifenqian.bms.common.annotation.MapperScanCore;
@MapperScanCore
public interface JgkjResultExceptionMapper {
	/**
	 * 交广科技对账结果交广科技存疑表报表
	 * @param exception
	 * @return
	 */
	public List<JgkjResultException> selectZytResultExceptionList(JgkjResultException exception);
}
