package com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.bean.ClearJgkjRegister;
import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.mapper.ClearJgkjRegisterMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ClearJgkjRegisterDao {
	
	@Autowired
	private ClearJgkjRegisterMapper mapper;
	
	@Page
	public List<ClearJgkjRegister> selectClearJgkjRegisterByList(ClearJgkjRegister clearJgkjRegister){
		return mapper.selectClearJgkjRegisterByList(clearJgkjRegister);
	}
}
