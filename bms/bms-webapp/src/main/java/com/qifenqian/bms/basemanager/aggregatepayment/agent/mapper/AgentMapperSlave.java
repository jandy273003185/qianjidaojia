package com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentCollectDailyBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleDetailBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgreementBean;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

@MapperScanPayment
public interface AgentMapperSlave {
	public List<AgreementBean> getAgreementList(AgreementBean queryBean);

	public void addAgreementInfo(AgreementBean bean);

	public void updateAgreementInfo(AgreementBean bean);

	public void deleteAgreementInfo(String agreement,String agentCode,String merCode,String prodCode);

	public List<AgentCollectDailyBean> getAgentCollectDailyList(
			AgentCollectDailyBean queryBean);

	public List<AgentSettleBean> getAgentSettleList(AgentSettleBean queryBean);

	public AgentSettleDetailBean getDetailBySettleId(@Param("settleId")String settleId);
	
}
