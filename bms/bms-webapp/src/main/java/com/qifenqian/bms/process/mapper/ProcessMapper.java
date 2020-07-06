package com.qifenqian.bms.process.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.process.bean.ProcessAudit;
import com.qifenqian.bms.process.bean.ProcessManagement;

@MapperScan
public interface ProcessMapper {

	void insertApplication(ProcessManagement processManagement);

	void insertAudit(ProcessAudit processAudit);
	
	List<ProcessAudit> getMyInitiateList(ProcessAudit processAudit);
	
	ProcessAudit selectApplyDetail(ProcessAudit processAudit);

	void updateProcessAudit(ProcessAudit processAudit);

	List<ProcessAudit> getMyFinishList(ProcessAudit processAudit);

	List<ProcessAudit> getUnMyInitiateList(ProcessAudit processAudit);

	

}
