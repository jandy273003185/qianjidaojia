package com.qifenqian.bms.basemanager.aggregatepayment.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgreementBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.dao.AgreementDAO;
@Service
public class AgreementService {
	@Autowired
	private AgreementDAO agreementDAO;
	public List<AgreementBean> getAgreementList(
			AgreementBean queryBean) {
		
		return agreementDAO.getAgreementList(queryBean);
	}
	public void addAgreementInfo(AgreementBean bean) {
		
		agreementDAO.addAgreementInfo(bean);
	}

	public void updateAgreementInfo(AgreementBean bean) {
		agreementDAO.updateAgreementInfo(bean);
		
	}

	public void deleteAgreementInfo(String agreement,String agentCode,String merCode,String prodCode) {
		agreementDAO.deleteAgreementInfo(agreement,agentCode,merCode,prodCode);
		
	}
	
}