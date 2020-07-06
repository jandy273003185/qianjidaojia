package com.qifenqian.bms.basemanager.agent.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.agent.bean.AgentApplyBean;
import com.qifenqian.bms.basemanager.agent.bean.CustVo;
import com.qifenqian.bms.basemanager.agent.mapper.AgentApplyMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AgentApplyDAO {
	
	@Autowired
	private AgentApplyMapper mapper;
	//获取代理商申请信息
	@Page
	public List<AgentApplyBean> getApplyList(AgentApplyBean applyBean) {
		return mapper.getApplyList(applyBean);
	}

	//根据custId查找客户信息
	public CustVo findCustInfo(String custId){
		return mapper.findCustInfo(custId);
	}
	
	//申请代理商成功 同时修改代理商申请表和客户表
	public void auditNotPass(String custId,String memo,String auditUserId) {
		mapper.auditNotPass(custId,memo,auditUserId);
		mapper.updateCustInfoAgentFlag(custId,"1");
	}
	
	//申请代理商成功 同时修改代理商申请表和客户表
	public void auditPass(String custId,String auditUserId) {
		
		mapper.auditPass(custId,auditUserId);
		mapper.updateCustInfoAgentFlag(custId,"0");
	}
}
