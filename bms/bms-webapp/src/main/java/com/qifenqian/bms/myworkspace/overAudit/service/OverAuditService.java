package com.qifenqian.bms.myworkspace.overAudit.service;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.myworkspace.overAudit.bean.OverAuditBean;
import com.qifenqian.bms.myworkspace.overAudit.mapper.OverAuditMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Service
public class OverAuditService {

	@Resource
	private HistoryService historyService;
	
	@Autowired
	private OverAuditMapper overAuditMapper;
	
	
	/**
	 * 我的已审核
	 * @param userId
	 * @return
	 */
	@Page
	public List<OverAuditBean> getOverAudit(OverAuditBean bean){
	
		return overAuditMapper.getOverAudit(bean);
	}
}
