package com.qifenqian.bms.basemanager.branchbank.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.dao.BranchBankDao;
import com.qifenqian.bms.basemanager.branchbank.mapper.BranchBankMapper;

@Service
public class BranchBankService {

	@Autowired
	private BranchBankDao branchBankDao;

	@Autowired
	private BranchBankMapper branchBankMapper;

	public List<BranchBank> branchBankList(BranchBank queryBean) {
		return branchBankDao.branchBankList(queryBean);
	}
	
	/**
	 * 新增银行支行信息
	 * @param insertBean
	 * @return
	 */
	public JSONObject add(BranchBank insertBean) {

		if (insertBean == null) {
			throw new IllegalArgumentException("支行信息对象为空");
		}
		if (StringUtils.isBlank(insertBean.getBankCnaps())) {
			throw new IllegalArgumentException("支行信息联行编号为空");
		}
		if (StringUtils.isBlank(insertBean.getBankCode())) {
			throw new IllegalArgumentException("支行信息银行支付系统行号为空");
		}
		if (StringUtils.isBlank(insertBean.getBankName())) {
			throw new IllegalArgumentException("支行信息银行名称为空");
		}
		if (insertBean.getAreaId() < 1) {
			throw new IllegalArgumentException("支行信息区域编号为空");
		}
		if (StringUtils.isBlank(insertBean.getBankAddress())) {
			throw new IllegalArgumentException("支行信息 地址为空");
		}
		JSONObject json = new JSONObject();
		try {
			BranchBank branchBank = branchBankMapper.selectBankCnaps(insertBean.getBankCnaps());
			if (branchBank != null) {
				json.put("result", "FAIL");
				json.put("message", "支行信息联行编号已存在");
				return json;
			}

			int i = branchBankMapper.insertBranchBank(insertBean);
			if (i == 1) {
				json.put("result", "SUCCESS");
			} else {
				json.put("result", "FAIL");
			}
			
		} catch (Exception e) {
			json.put("message", e.getMessage());
		}

		return json;
	}
	
	/***
	 * 修改银行支行信息
	 * @param updaetBean
	 * @return
	 */
	public JSONObject update(BranchBank updateBean) {
		
		if (updateBean == null) {
			throw new IllegalArgumentException("支行信息对象为空");
		}
		if (StringUtils.isBlank(updateBean.getBankCnaps())) {
			throw new IllegalArgumentException("支行信息联行编号为空");
		}
		if (StringUtils.isBlank(updateBean.getBankCode())) {
			throw new IllegalArgumentException("支行信息银行支付系统行号为空");
		}
		if (StringUtils.isBlank(updateBean.getBankName())) {
			throw new IllegalArgumentException("支行信息银行名称为空");
		}
		if (updateBean.getAreaId() < 1) {
			throw new IllegalArgumentException("支行信息区域编号为空");
		}
		if (StringUtils.isBlank(updateBean.getBankAddress())) {
			throw new IllegalArgumentException("支行信息 地址为空");
		}
		
		JSONObject json = new JSONObject();
		try {
			int i = branchBankMapper.updateBranchBank(updateBean);
			if (i == 1) {
				json.put("result", "SUCCESS");
			} else {
				json.put("result", "FAIL");
			}
		} catch (Exception e) {
			json.put("message", e.getMessage());
		}
		return json;
	}
	
	/***
	 * 刪除银行支行信息
	 * @param deleteBean
	 * @return
	 */
	public JSONObject delete(BranchBank deleteBean) {
		JSONObject json = new JSONObject();
		try {
			int i = branchBankMapper.deleteBranchBank(deleteBean);
			if (i == 1) {
				json.put("result", "SUCCESS");
			} else {
				json.put("result", "FAIL");
			}
		} catch (Exception e) {
			json.put("message", e.getMessage());
		}
		return json;
	}

}
