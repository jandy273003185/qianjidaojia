package com.qifenqian.bms.basemanager.payIn.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.payIn.bean.PayIn;
import com.qifenqian.bms.basemanager.payIn.dao.PayInDAO;
import com.qifenqian.bms.basemanager.payIn.mapper.PayInMapper;

/**
 * 代付垫资费率服务层
 * 
 * @project sevenpay-bms-web
 * @fileName PayInService.java
 * @author Dayet
 * @date 2017年11月28日
 * @memo 
 */
@Service
public class PayInService {
	

	@Autowired
	private PayInMapper payInMapper;
	
	@Autowired
	private PayInDAO payInDAO;
	
	
	
	/**
	 * 查询所有代付垫资费率信息
	 * @return
	 */
	public List<PayIn> selectPayIn(PayIn payIn){
		return payInDAO.selectPayIn(payIn);
	}
	
	/**
	 *根据费用代码查询信息是否已存在
	 */
	public PayIn selectPayInByFeeCode(@Param("feeCode") String feeCode){
		return payInMapper.selectPayInByFeeCode(feeCode);
	}
	
	/**
	 * 添加信息
	 */
	public void insertPayIn(PayIn payIn){
		if(StringUtils.isEmpty(payIn.getFeeCode())){
			throw new IllegalArgumentException("费用代码为空");
		}
		
		if(StringUtils.isEmpty(payIn.getFeeName())){
			throw new IllegalArgumentException("费用名称为空");
		}
		
		if(StringUtils.isEmpty(payIn.getFeeRate())){
			throw new IllegalArgumentException("费率为空");
		}
		
		
		payInMapper.insertPayIn(payIn);
		
	}
	
	/**
	 * 更新信息
	 * @param payIn
	 */
	public void updatePayIn(PayIn payIn) {
		if(StringUtils.isEmpty(payIn.getFeeCode())){
			throw new IllegalArgumentException("费用代码为空");
		}
		
		if(StringUtils.isEmpty(payIn.getFeeName())){
			throw new IllegalArgumentException("费用名称为空");
		}
		
		if(StringUtils.isEmpty(payIn.getFeeRate())){
			throw new IllegalArgumentException("费率为空");
		}
		
		payInMapper.updatePayIn(payIn);
		
	}
	
	public void stopPayInByCode(@Param("feeCode") String feeCode) {
		if(StringUtils.isEmpty(feeCode)){
			throw new IllegalArgumentException("费用代码为空");
		}
		payInMapper.stopPayInByCode(feeCode);
		
	}
}
