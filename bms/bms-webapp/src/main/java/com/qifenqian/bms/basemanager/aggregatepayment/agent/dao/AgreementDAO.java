package com.qifenqian.bms.basemanager.aggregatepayment.agent.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgreementBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AgreementDAO {

	@Autowired AgentMapper agentMapper;
	@Page
	public List<AgreementBean> getAgreementList(AgreementBean queryBean) {
		// TODO Auto-generated method stub
		return agentMapper.getAgreementList(queryBean);
	}

	public void addAgreementInfo(AgreementBean bean) {
		agentMapper.addAgreementInfo(bean);
		
	}

	public void updateAgreementInfo(AgreementBean bean) {
		agentMapper.updateAgreementInfo(bean);
		
	}

	public void deleteAgreementInfo(String agreement, String agentCode,
			String merCode, String prodCode) {
		agentMapper.deleteAgreementInfo(agreement, agentCode, merCode, prodCode);
		
	}

}
