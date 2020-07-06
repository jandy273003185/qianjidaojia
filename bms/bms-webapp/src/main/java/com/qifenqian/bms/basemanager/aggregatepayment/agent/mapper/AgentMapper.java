package com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentCollectDailyBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleDetailBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgreementBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface AgentMapper {
	public List<AgreementBean> getAgreementList(AgreementBean queryBean);

	public void addAgreementInfo(AgreementBean bean);

	public void updateAgreementInfo(AgreementBean bean);

	public void deleteAgreementInfo(@Param("agreement")String agreement,@Param("agentCode")String agentCode,@Param("merCode")String merCode,@Param("prodCode")String prodCode);

	public List<AgentCollectDailyBean> getAgentCollectDailyList(
			AgentCollectDailyBean queryBean);

	public List<AgentSettleBean> getAgentSettleList(AgentSettleBean queryBean);

	public AgentSettleDetailBean getDetailBySettleId(@Param("settleId")String settleId);

	public List<PayChannelBean> getPayChannelList(PayChannelBean queryBean);

	public void addPayChannelInfo(PayChannelBean payChannelBean);

	public void updatePayChannelInfo(PayChannelBean payChannelBean);

	public void deletePayChannelInfo(@Param("payChannelCode")String payChannelCode);

	public List<PayProdBean> getPayProdList(PayProdBean payProdBean);

	public void addPayProdInfo(PayProdBean payProdBean);

	public void updatePayProdInfo(PayProdBean payProdBean);

	public void deletePayProdInfo(@Param("prodCode")String prodCode);

	public List<AgentCollectDailyBean> getAgentCollectDailyListExport(
			AgentCollectDailyBean queryBean);
	
}
