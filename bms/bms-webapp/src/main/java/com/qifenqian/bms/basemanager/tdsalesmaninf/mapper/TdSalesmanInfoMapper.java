package com.qifenqian.bms.basemanager.tdsalesmaninf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.tdsalesmaninf.bean.TdSalesmanInfo;

/**
 * 费率持久层
 * 
 * @project sevenpay-bms-web
 * @fileName RuleMapper.java
 * @author PC
 * @date 2015年6月17日
 * @memo
 */

@MapperScan
public interface TdSalesmanInfoMapper {
	
	public List<TdSalesmanInfo> selectTdSalesmanInfoList();

	TdSalesmanInfo selectTdSalesmanInfoById(@Param("salesmanId") String salesmanId);

}
