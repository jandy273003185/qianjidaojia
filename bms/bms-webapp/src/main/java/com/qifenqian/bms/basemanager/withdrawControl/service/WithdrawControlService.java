package com.qifenqian.bms.basemanager.withdrawControl.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.utils.ValidateTool;
import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;
import com.qifenqian.bms.basemanager.withdrawControl.dao.WithdrawControlDAO;
import com.qifenqian.bms.basemanager.withdrawControl.mapper.WithdrawControlMapper;

@Service
public class WithdrawControlService {

	@Autowired
	private WithdrawControlDAO withdrawControlDAO;
	
	@Autowired
	private WithdrawControlMapper withdrawControlMapper;
	/**
	 * 查询交易控制信息
	 * @return
	 */
	public List<WithdrawControl> selectAll(WithdrawControl withdrawControl){
		return withdrawControlDAO.selectAll(withdrawControl);
	}
	
	/**
	 * 添加交易控制
	 * @param tradeControl
	 */
	public void addTradeControl(WithdrawControl withdrawControl){
		
		if(withdrawControl.getPcWdAmtPerDay()==null){
			withdrawControl.setPcWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getPcWdAmtPerMonth()==null){
			withdrawControl.setPcWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getPcWdAmtPerOnce()==null){
			withdrawControl.setPcWdAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerDay()==null){
			withdrawControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerMonth()==null){
			withdrawControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerOnce()==null){
			withdrawControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
		}
		
		this.valid(withdrawControl);
		withdrawControlMapper.addWithdrawControl(withdrawControl);
	}
	
	/**
	 * 根据条件查询
	 * @param tradeControl
	 * @return
	 */
	public WithdrawControl selectTradeControl(WithdrawControl tradeControl){
		return withdrawControlMapper.selectWithdrawControl(tradeControl);
	}
	
	/**
	 * 修改
	 * @param tradeControl
	 */
	public void editTradeControl(WithdrawControl withdrawControl){
		
		if(withdrawControl.getPcWdAmtPerDay()==null){
			withdrawControl.setPcWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getPcWdAmtPerMonth()==null){
			withdrawControl.setPcWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getPcWdAmtPerOnce()==null){
			withdrawControl.setPcWdAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerDay()==null){
			withdrawControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerMonth()==null){
			withdrawControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(withdrawControl.getMbWdAmtPerOnce()==null){
			withdrawControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
		}
		
		this.valid(withdrawControl);
		withdrawControlMapper.editTradeControl(withdrawControl);
	}
	
	/**
	 * 删除
	 * @param tradeControl
	 */
	public void deleteTradeControl(WithdrawControl tradeControl){
		withdrawControlMapper.deleteTradeControl(tradeControl);
	}
	/**
	 * 初始化对象
	 * @param tradeControl
	 * @return
	 */
	public TradeControl initTradeControl(TradeControl tradeControl){
		tradeControl.setIsShare("0");
		tradeControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
		tradeControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
		tradeControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
		tradeControl.setMbWdCntPerDay(0);
		tradeControl.setPcNoAmtPerDay(new BigDecimal("0.00"));
		tradeControl.setPcNoAmtPerMonth(new BigDecimal("0.00"));
		tradeControl.setPcNoAmtPerOnce(new BigDecimal("0.00"));
		tradeControl.setPcNoCntPerDay(0);
		tradeControl.setPcYesAmtPerDay(new BigDecimal("0.00"));
		tradeControl.setPcYesAmtPerMonth(new BigDecimal("0.00"));
		tradeControl.setPcYesAmtPerOnce(new BigDecimal("0.00"));
		tradeControl.setPcYesCntPerDay(0);
		return null;
		
	}
	
	/**
	 * 校验
	 * @param tradeControl
	 */
	private void valid(WithdrawControl tradeControl){
		if(tradeControl == null){
			throw new IllegalArgumentException("提现控制对象为空");
		}
		
		if(StringUtils.isEmpty(tradeControl.getControlType())){
			throw new IllegalArgumentException("控制类型为空");
		}
		
		if(StringUtils.isEmpty(tradeControl.getCustId())){
			throw new IllegalArgumentException("客户编号为空");
		}
		
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdCntPerDay()), "^-?[1-9]\\d*$")){
			throw new IllegalArgumentException("PcWdCntPerDay为整数");
		}
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcWdAmtPerDay最多16位整数。小数点后最多两个数字");
		}
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcWdAmtPerOnce最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcWdAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcWdAmtPerMonth最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdCntPerDay()), "^-?[1-9]\\d*$")){
			throw new IllegalArgumentException("MbWdCntPerDay为整数");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("MbWdAmtPerOnce最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("MbWdAmtPerDay最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getMbWdAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("MbWdAmtPerMonth最多16位整数。小数点后最多两个数字");
		}
	}

	public WithdrawControl selectCustIdByMobile(String mobile) {
		return withdrawControlMapper.selectCustIdByMobile(mobile);
	}
}
