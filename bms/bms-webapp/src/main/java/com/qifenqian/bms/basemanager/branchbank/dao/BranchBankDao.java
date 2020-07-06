package com.qifenqian.bms.basemanager.branchbank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.mapper.BranchBankMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BranchBankDao {

	@Autowired
	private BranchBankMapper branchBankMapper;

	@Page
	public List<BranchBank> branchBankList(BranchBank queryBean) {
		return branchBankMapper.branchBankList(queryBean);
	}

}
