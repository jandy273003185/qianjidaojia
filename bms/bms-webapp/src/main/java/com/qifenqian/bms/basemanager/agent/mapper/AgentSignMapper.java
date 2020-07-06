package com.qifenqian.bms.basemanager.agent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.agent.bean.AgentSignBean;
import com.qifenqian.bms.basemanager.agent.bean.AgentSignVO;
import com.qifenqian.bms.basemanager.agent.bean.CustVo;

@MapperScan
public interface AgentSignMapper {
    //查找所有的签约信息
	public List<AgentSignBean> getSignList(AgentSignBean applyBean);
	
	//根据custId获取客户信息
	public CustVo findCustInfo(String custId);

	//代理商申请不通过
	public void auditNotPass(@Param("custId")String custId,@Param("memo")String memo,@Param("auditUserId")String auditUserId);

	//代理商申请通过
	public void auditPass(@Param("custId")String custId,@Param("auditUserId")String auditUserId);
	public void updateCustInfoAgentFlag(@Param("custId")String custId,@Param("flag")String flag);

	//根据merchantId查找签约信息
	public AgentSignVO findSignInfo(String custId);

	//查询域名证书图片路径
	public String findScanPath(String custId);
}
