package com.qifenqian.bms.accounting.checkV2.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.checkV2.bean.CheckStats;
import com.qifenqian.bms.accounting.checkV2.mapper.CheckStatsMapper;

@Service
public class CheckStatsService {
	@Resource
	private CheckStatsMapper checkStatsMapper;
	
	public List<CheckStats> findCheckStats(CheckStats checkStats){
		return checkStatsMapper.findCheckStats(checkStats);
	}
}
