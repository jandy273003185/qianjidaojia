package com.qifenqian.bms.merchant.channel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.channel.bean.AgentMerInfo;
/**
 * 商户通道详情表
 * @author admin
 *
 */
@MapperScan
public interface AgentMerMapper {
	
	/**
	 * 增加代理通道信息
	 * @return
	 */
	public int insertAgentMerInfo(AgentMerInfo channleAgentMerInfo);
	
	/**
	 * 根据CHANNLECODE查询通道信息
	 * @param roleId
	 * @return
	 */
	public AgentMerInfo selectAgentMerInfoByChannlCode(@Param("channlCode") String channlCode);
	
	/**
	 * 根据ID删除通道信息
	 * @param roleId
	 */
	public void deleteChannleAgentMerInfoBymerCustId(AgentMerInfo channleAgentMerInfo);
	
	/**
	 * 查询所有匹配通道信息
	 * @return
	 */
	public List<AgentMerInfo> selectChannleAgentMerInfo(AgentMerInfo channleAgentMerInfo);
	
	/**
	 * 更新商户通道信息
	 * @param role
	 */
	public void updateChannleAgentMerInfo(AgentMerInfo channleAgentMerInfo);
    /**
     * 查询出所有商户通道信息
     * @return
     */
	public List<AgentMerInfo> selectAllChannleAgentMerInfo();

	public List<AgentMerInfo> selectChannleAgentMerInfoByPrimaryKey(AgentMerInfo agentMerInfo);

}
