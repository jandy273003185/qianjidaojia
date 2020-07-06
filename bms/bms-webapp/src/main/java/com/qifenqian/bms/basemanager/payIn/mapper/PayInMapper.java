package com.qifenqian.bms.basemanager.payIn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.payIn.bean.PayIn;

/**
 *  代付垫资费率信息持久层
 * 
 * @project sevenpay-bms-web
 * @fileName PayInMapper.java
 * @author Dayet
 * @date 2017年11月28日
 * @memo 
 */

@MapperScan
public interface PayInMapper {
	
	/**
	 * 查询所有代付垫资费率信息
	 * @return
	 */
	public List<PayIn> selectPayIns(PayIn payIn);
	
	/**
	 * 根据费用代码查询信息是否已存在
	 */
	public PayIn selectPayInByFeeCode(@Param("feeCode") String feeCode);

	/**
	 * 添加信息
	 * @param payIn
	 * @return
	 */
	public void insertPayIn(PayIn payIn);

	/**
	 * 更新信息
	 * @param payIn
	 */
	public void updatePayIn(PayIn payIn);

	/**
	 * 停用信息
	 * @param feeCode
	 */
	public void stopPayInByCode(@Param("feeCode") String feeCode);
	
	
	
}
