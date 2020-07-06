package com.qifenqian.bms.process.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.process.bean.ProcessAudit;
import com.qifenqian.bms.process.bean.ProcessManagement;
import com.qifenqian.bms.process.mapper.ProcessMapper;

@Repository
public class ProcessDao {

	@Autowired
	private ProcessMapper processMapper;
	
	public void insertApplication(ProcessManagement processManagement) {
		processMapper.insertApplication(processManagement);
	}

	public void insertAudit(ProcessAudit processAudit) {
		processMapper.insertAudit(processAudit);		
	}
	
	@Page
	public List<ProcessAudit> getMyInitiateList(ProcessAudit processAudit) {
		return processMapper.getMyInitiateList(processAudit);
	}
	
	public ProcessAudit selectApplyDetail(ProcessAudit processAudit) {
		return processMapper.selectApplyDetail(processAudit);
	}

	public void updateProcessAudit(ProcessAudit processAudit) {
		processMapper.updateProcessAudit(processAudit);
		
	}
	
	@Page
	public List<ProcessAudit> getMyFinishList(ProcessAudit processAudit) {
		return processMapper.getMyFinishList(processAudit);
	}

	@Page
	public List<ProcessAudit> getUnMyInitiateList(ProcessAudit processAudit) {
		return processMapper.getUnMyInitiateList(processAudit);
	}

	

	
	
	

}
