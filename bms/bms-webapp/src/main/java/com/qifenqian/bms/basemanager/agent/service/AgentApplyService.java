package com.qifenqian.bms.basemanager.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.agent.bean.AgentApplyBean;
import com.qifenqian.bms.basemanager.agent.bean.CustVo;
import com.qifenqian.bms.basemanager.agent.dao.AgentApplyDAO;

@Service
public class AgentApplyService {
	@Autowired
	private AgentApplyDAO applyDAO;
	//获取所有代理商申请
	public List<AgentApplyBean> getApplyList(AgentApplyBean applyBean){
		return applyDAO.getApplyList(applyBean);
	}
	
	//根据custId查找客户信息
	public CustVo findCustInfo(String custId){
		return applyDAO.findCustInfo(custId);
	}
	
	//代理商申请不通过
	public void auditNotPass(String custId,String memo,String auditUserId){
		applyDAO.auditNotPass(custId,memo,auditUserId);
	}
	//代理商申请通过
	public void auditPass(String custId,String auditUserId){
		applyDAO.auditPass(custId,auditUserId);
	}
}
