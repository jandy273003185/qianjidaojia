package com.qifenqian.bms.basemanager.agent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.agent.bean.AgentApplyBean;
import com.qifenqian.bms.basemanager.agent.bean.CustVo;

@MapperScan
public interface AgentApplyMapper {
    //查找所有的代理商信息
	public List<AgentApplyBean> getApplyList(AgentApplyBean applyBean);
	
	//根据custId获取客户信息
	public CustVo findCustInfo(String custId);

	//代理商申请不通过
	public void auditNotPass(@Param("custId")String custId,@Param("memo")String memo,@Param("auditUserId")String auditUserId);

	//代理商申请通过
	public void auditPass(@Param("custId")String custId,@Param("auditUserId")String auditUserId);
	public void updateCustInfoAgentFlag(@Param("custId")String custId,@Param("flag")String flag);
}
