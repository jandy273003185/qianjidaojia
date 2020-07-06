package com.qifenqian.bms.basemanager.fee.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.fee.bean.Fee;
import com.qifenqian.bms.basemanager.fee.dao.FeeDAO;
import com.qifenqian.bms.basemanager.fee.mapper.FeeMapper;

/**
 * 费用服务层
 * 
 * @project sevenpay-bms-web
 * @fileName FeeService.java
 * @author Dayet
 * @date 2015年5月18日
 * @memo 
 */
@Service
public class FeeService {
	

	@Autowired
	private FeeMapper feeMapper;
	
	@Autowired
	private FeeDAO feeDAO;
	
	/**
	 * 查询所有手续信息
	 * @return
	 */
	public List<Fee> selectFees(Fee fee){
		return feeDAO.selectFees(fee);
	}
	
	/**
	 * 增加费用
	 * @param city
	 */
	public void addFee(Fee fee){
		
		if(null == fee){
			throw new IllegalArgumentException("手续费对象为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeCode())){
			throw new IllegalArgumentException("费用编号为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeName())){
			throw new IllegalArgumentException("费用名称为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeCodeDesc())){
			throw new IllegalArgumentException("费用描述为空");
		}
		feeMapper.insertFee(fee);
	}
	
	/**
	 * 更新城市
	 * @param city
	 */
	public void updateCity(Fee fee){
		
		if(null == fee){
			throw new IllegalArgumentException("手续费对象为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeCode())){
			throw new IllegalArgumentException("费用编号为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeName())){
			throw new IllegalArgumentException("费用名称为空");
		}
		
		if(StringUtils.isEmpty(fee.getFeeCodeDesc())){
			throw new IllegalArgumentException("费用描述为空");
		}
		feeMapper.updateFee(fee);
	}
	
	/**
	 * 删除
	 * @param cityCode
	 */
	public void deleteFee(String feeCode){
		
		if(StringUtils.isEmpty(feeCode)){
			throw new IllegalArgumentException("费用编号为空");
		}
		feeMapper.deleteFee(feeCode);
	}

	public Fee selectFeeCode(String feeCode) {
		if(StringUtils.isEmpty(feeCode.toString())){
			throw new IllegalArgumentException("费率code为空");
		}
		return feeMapper.validateFeeCode(feeCode) ;
		
	}
}
