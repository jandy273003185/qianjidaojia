package com.qifenqian.bms.basemanager.bank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.bank.bean.Bank;

/**
 * 银行信息持久层
 * 
 * @project sevenpay-bms-web
 * @fileName BankMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */

@MapperScan
public interface BankMapper {
	
	/**
	 * 增加银行信息
	 * @return
	 */
	public int insertBank(Bank bank);
	
	/**
	 * 根据BankCode查询银行信息
	 * @param roleId
	 * @return
	 */
	public Bank selectBankByBankCode(@Param("bankCode") String bankCode);
	
	/**
	 * 根据ID删除角色信息
	 * @param roleId
	 */
	public void deleteBankByBankCode(@Param("bankCode") String bankCode);
	
	/**
	 * 查询所有匹配银行信息
	 * @return
	 */
	public List<Bank> selectBanks(Bank bank);
	
	/**
	 * 更新角色信息
	 * @param role
	 */
	public void updateBank(Bank bank);
	
}
