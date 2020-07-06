package com.qifenqian.bms.basemanager.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.agent.bean.AgentSignBean;
import com.qifenqian.bms.basemanager.agent.bean.AgentSignVO;
import com.qifenqian.bms.basemanager.agent.dao.AgentSignDAO;

@Service
public class AgentSignService {
	@Autowired
	private AgentSignDAO signDAO;
	//获取签约信息
	public List<AgentSignBean> getSignList(AgentSignBean signBean){
		return signDAO.getSignList(signBean);
	}
	
	
	//签约申请不通过
	public void auditNotPass(String custId,String memo,String auditUserId){
		signDAO.auditNotPass(custId,memo,auditUserId);
	}
	//签约申请通过
	public void auditPass(String custId,String auditUserId){
		signDAO.auditPass(custId,auditUserId);
	}

	public AgentSignVO findSignInfo(String custId) {
		// TODO Auto-generated method stub
		return signDAO.findSignInfo(custId);
	}


	//查询域名图片路径
	public String findScanPath(String custId) {
		// TODO Auto-generated method stub
		return signDAO.findScanPath(custId);
	}
}
