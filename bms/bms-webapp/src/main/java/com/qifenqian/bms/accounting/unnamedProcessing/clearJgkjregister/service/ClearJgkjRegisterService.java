package com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.bean.ClearJgkjRegister;
import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.dao.ClearJgkjRegisterDao;

@Service
public class ClearJgkjRegisterService {
	@Autowired
	private ClearJgkjRegisterDao dao;

	public List<ClearJgkjRegister> selectClearJgkjRegisterByList(ClearJgkjRegister clearJgkjRegister) {
		return dao.selectClearJgkjRegisterByList(clearJgkjRegister);
	}
}
