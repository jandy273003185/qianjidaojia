package com.qifenqian.bms.basemanager.fee.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.fee.bean.Fee;

/**
 * 城市持久层
 * 
 * @project sevenpay-bms-web
 * @fileName FeeMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScan
public interface FeeMapper {
	
	/**
	 * 查询所有费用信息
	 * @return
	 */
	public List<Fee> selectFees(Fee fee);
	
	
	/**
	 * 新增费用
	 * @param city
	 */
	public void insertFee(Fee fee);
	
	/**
	 * 更新费用
	 * @param city
	 */
	public void updateFee(Fee fee);
	
	/**
	 * 删除城市
	 * @param cityCode
	 */
	public void deleteFee(String feeCode);


	public Fee validateFeeCode(String feeCode);
}
