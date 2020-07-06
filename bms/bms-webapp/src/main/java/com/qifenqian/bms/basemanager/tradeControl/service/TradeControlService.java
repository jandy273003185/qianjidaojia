package com.qifenqian.bms.basemanager.tradeControl.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.tradeControl.Dao.TradeControlDAO;
import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.tradeControl.mapper.TradeControlMapper;
import com.qifenqian.bms.basemanager.utils.ValidateTool;

@Service
public class TradeControlService {

	@Autowired
	private TradeControlDAO tradeControlDAO;
	
	@Autowired
	private TradeControlMapper tradeControlMapper;
	/**
	 * 查询交易控制信息
	 * @return
	 */
	public List<TradeControl> selectAll(TradeControl tradeControl){
		return tradeControlDAO.selectAll(tradeControl);
	}
	
	/**
	 * 添加交易控制
	 * @param tradeControl
	 */
	public void addTradeControl(TradeControl tradeControl){
		
		if(tradeControl.getPcNoAmtPerDay()==null){
			tradeControl.setPcNoAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcNoAmtPerMonth()==null){
			tradeControl.setPcNoAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcNoAmtPerOnce()==null){
			tradeControl.setPcNoAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerDay()==null){
			tradeControl.setPcYesAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerMonth()==null){
			tradeControl.setPcYesAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerOnce()==null){
			tradeControl.setPcYesAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerDay()==null){
			tradeControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerMonth()==null){
			tradeControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerOnce()==null){
			tradeControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
		}
		
		this.valid(tradeControl);
		tradeControlMapper.addTradeControl(tradeControl);
	}
	
	/**
	 * 根据条件查询
	 * @param tradeControl
	 * @return
	 */
	public TradeControl selectTradeControl(TradeControl tradeControl){
		return tradeControlMapper.selectTradeControl(tradeControl);
	}
	
	/**
	 * 修改
	 * @param tradeControl
	 */
	public void editTradeControl(TradeControl tradeControl){
		
		if(tradeControl.getPcNoAmtPerDay()==null){
			tradeControl.setPcNoAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcNoAmtPerMonth()==null){
			tradeControl.setPcNoAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcNoAmtPerOnce()==null){
			tradeControl.setPcNoAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerDay()==null){
			tradeControl.setPcYesAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerMonth()==null){
			tradeControl.setPcYesAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getPcYesAmtPerOnce()==null){
			tradeControl.setPcYesAmtPerOnce(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerDay()==null){
			tradeControl.setMbWdAmtPerDay(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerMonth()==null){
			tradeControl.setMbWdAmtPerMonth(new BigDecimal("0.00"));
		}
		
		if(tradeControl.getMbWdAmtPerOnce()==null){
			tradeControl.setMbWdAmtPerOnce(new BigDecimal("0.00"));
		}
		this.valid(tradeControl);
		tradeControlMapper.editTradeControl(tradeControl);
	}
	
	/**
	 * 删除
	 * @param tradeControl
	 */
	public void deleteTradeControl(TradeControl tradeControl){
		tradeControlMapper.deleteTradeControl(tradeControl);
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
	private void valid(TradeControl tradeControl){
		if(tradeControl == null){
			throw new IllegalArgumentException("交易控制对象为空");
		}
		if(StringUtils.isEmpty(tradeControl.getTradeType())){
			throw new IllegalArgumentException("交易类型为空");
		}
		if(null == tradeControl.getPayeeAcctType()){
			throw new IllegalArgumentException("收方账户类型为空");
		}
		if(null == tradeControl.getPayeeAcctType()){
			throw new IllegalArgumentException("付方账户类型为空");
		}
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoCntPerDay()),"^-?[1-9]\\d*$")){
			throw new IllegalArgumentException("pcNoCntPerDay类型应为整数");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcNoAmtPerOnce最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcNoAmtPerDay最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcNoAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcNoAmtPerMonth最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesCntPerDay()), "^-?[1-9]\\d*$")){
			throw new IllegalArgumentException("PcYesCntPerDay为整数");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerOnce()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcYesAmtPerOnce最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerDay()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcYesAmtPerDay最多16位整数。小数点后最多两个数字");
		}
		
		if(!ValidateTool.runRegex(String.valueOf(tradeControl.getPcYesAmtPerMonth()), "^(([1-9]([\\d]{0,15}))|\\d)(.[0-9]{1,2})?$")){
			throw new IllegalArgumentException("PcYesAmtPerMonth最多16位整数。小数点后最多两个数字");
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
}
