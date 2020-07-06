package com.qifenqian.bms.accounting.checkV2.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.checkV2.bean.CheckDetail;
import com.qifenqian.bms.accounting.checkV2.mapper.CheckDetailMapper;

@Service
public class CheckDetailServic {
	@Resource
	private CheckDetailMapper checkDetailMapper;
	
	public List<CheckDetail> findCheckDetail(CheckDetail checkDetail){
		return checkDetailMapper.findCheckDetail(checkDetail);
	}
}
