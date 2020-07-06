package com.qifenqian.bms.basemanager.bank.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.dao.BankDAO;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;

/**
 * 银行服务层
 * 
 * @project sevenpay-bms-web
 * @fileName BankService.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */
@Service
public class BankService {
	

	@Autowired
	private BankMapper BankMapper;
	
	@Autowired
	private BankDAO bankDAO;
	
	/**
	 * 增加银行
	 * @return
	 */
	public int insertBank(Bank bank){
		
		if(null == bank){
			throw new IllegalArgumentException("银行对象为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankCode())){
			throw new IllegalArgumentException("银行编号为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankName())){
			throw new IllegalArgumentException("银行名称为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankType())){
			throw new IllegalArgumentException("银行别名为空");
		}
		
		if(StringUtils.isEmpty(bank.getCityCode())){
			throw new IllegalArgumentException("城市编号为空");
		}
		
		if(StringUtils.isEmpty(bank.getCcpc())){
			throw new IllegalArgumentException("ccpc为空");
		}
		
		if(StringUtils.isEmpty(String.valueOf(bank.getOrderBy()))){
			throw new IllegalArgumentException("排序号为空");
		}
		return BankMapper.insertBank(bank);
	}
	
	/**
	 * 根据code查询银行信息
	 * @param roleId
	 * @return
	 */
	public Bank selectBankByBankCode(@Param("bankCode") String bankCode){
		return BankMapper.selectBankByBankCode(bankCode);
	}
	
	/**
	 * 查询所有银行信息
	 * @return
	 */
	public List<Bank> selectBanks(Bank bank){
		return bankDAO.selectBanks(bank);
	}
	/**
	 * 更新角色信息
	 * @param role
	 */
	public void updateBank(Bank bank){
		
		if(null == bank){
			throw new IllegalArgumentException("银行对象为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankCode())){
			throw new IllegalArgumentException("银行编号为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankName())){
			throw new IllegalArgumentException("银行名称为空");
		}
		
		if(StringUtils.isEmpty(bank.getBankType())){
			throw new IllegalArgumentException("银行别名为空");
		}
		
		if(StringUtils.isEmpty(bank.getCityCode())){
			throw new IllegalArgumentException("城市编号为空");
		}
		
		if(StringUtils.isEmpty(bank.getCcpc())){
			throw new IllegalArgumentException("ccpc为空");
		}
		
		if(StringUtils.isEmpty(String.valueOf(bank.getOrderBy()))){
			throw new IllegalArgumentException("排序号为空");
		}
		BankMapper.updateBank(bank);
		
	}
	
	
	/**
	 * 删除银行信息
	 * @param bankCode
	 */
	public void deleteBankByCode(String bankCode){
		
		if(StringUtils.isEmpty(bankCode)){
			throw new IllegalArgumentException("银行编号为空");
		}
		BankMapper.deleteBankByBankCode(bankCode);
	}

}
