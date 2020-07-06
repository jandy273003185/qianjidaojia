package com.qifenqian.bms.accounting.accountMaintain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.accountMaintain.bean.BmsBaseBankAccount;
import com.qifenqian.bms.accounting.accountMaintain.dao.BmsBaseBankAccountDAO;
import com.qifenqian.bms.accounting.accountMaintain.mapper.BmsBaseBankAccountMapper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class BmsBaseBankAccountService {
	
	@Autowired
	private BmsBaseBankAccountDAO bmsBaseBankAccountDAO;
	
	@Autowired
	private BmsBaseBankAccountMapper bmsBaseBankAccountMapper;
	/**
	 * 我方账户查询
	 * @param record
	 * @return
	 */
	public List<BmsBaseBankAccount> selectBaseBankAccountList(BmsBaseBankAccount record){
		return bmsBaseBankAccountDAO.selectBaseBankAccountList(record);
	}
	
	/**
	 * 增加
	 * @param record
	 */
	public void addBaseBankAccount(BmsBaseBankAccount record){
		
		if (null == record) {
			throw new IllegalArgumentException("请求bean为空");
		}
		
		User user = WebUtils.getUserInfo();
		record.setInstUser(String.valueOf(user.getUserId()));
		bmsBaseBankAccountMapper.insertSelective(record);
	}
	
	/**
	 * 修改
	 * @param record
	 */
	public void updateBaseBankAccount(BmsBaseBankAccount record){
		if (null == record) {
			throw new IllegalArgumentException("请求bean为空");
		}
		User user = WebUtils.getUserInfo();
		record.setLupdUser(String.valueOf(user.getUserId()));
		bmsBaseBankAccountMapper.updateByPrimaryKeySelective(record);
	}
	
}
