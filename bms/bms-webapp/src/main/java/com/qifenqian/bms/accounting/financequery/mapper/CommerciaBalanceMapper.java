package com.qifenqian.bms.accounting.financequery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;

@MapperScan
public interface CommerciaBalanceMapper {
	public List<CommerciaBalance> selectCommerciaBalanceList(CommerciaBalance commerciaBalance);

	public List<CommerciaBalance> selectCommerciaBalance(@Param("merchantCode") String merchantCode);

	public CommerciaBalance selectCommerciaBalanceCount();
}
