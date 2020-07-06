package com.qifenqian.bms.process;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.process.bean.ProcessAudit;
import com.qifenqian.bms.process.bean.ProcessManagement;
import com.qifenqian.bms.process.dao.ProcessDao;

@Service
public class ProcessService {

	@Autowired
	private ProcessDao processDao;
	
	public void insertApplicationAndAudit(ProcessManagement processManagement) {
		
		processDao.insertApplication(processManagement);
		
		ProcessAudit processAudit = new ProcessAudit();
		processAudit.setProcessId(processManagement.getProcessId());
		processAudit.setProcessAuditId(UUID.randomUUID().toString().replaceAll("-", ""));
		//发起申请当前节点状态
		processAudit.setCurrentNode("2");
		processDao.insertAudit(processAudit);
	}
	
	public List<ProcessAudit> getMyInitiateList(ProcessAudit processAudit) {
		return processDao.getMyInitiateList(processAudit);
	}

	public ProcessAudit selectApplyDetail(ProcessAudit processAudit) {
		return processDao.selectApplyDetail(processAudit);
	}

	public void updateProcessAudit(ProcessAudit processAudit) {
		processDao.updateProcessAudit(processAudit);
	}
	
	public List<ProcessAudit> getMyFinishList(ProcessAudit processAudit) {
		return processDao.getMyFinishList(processAudit);
	}
	
	public List<ProcessAudit> getUnMyInitiateList(ProcessAudit processAudit) {
		return processDao.getUnMyInitiateList(processAudit);
	}
	

}
