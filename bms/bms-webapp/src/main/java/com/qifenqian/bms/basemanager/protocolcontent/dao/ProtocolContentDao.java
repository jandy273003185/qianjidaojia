package com.qifenqian.bms.basemanager.protocolcontent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolColumn;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContentExportBean;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolInfo;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ProtocolContentDao {
	
	@Autowired
	private ProtocolContentMapper protocolContentMapper;
	
	@Page
	public List<ProtocolContent> select(ProtocolContent queryBean){
		return 	protocolContentMapper.select(queryBean);
	}
	
	public ProtocolContent selectProto(@Param("custId")String custId){
		return protocolContentMapper.selectProto(custId);
	}
	@Page
	public List<ProtocolContent> selectMyProto(ProtocolContent queryBean){
		return protocolContentMapper.selectMyProto(queryBean);
	}
	
	@Page
	public List<ProtocolContent> selectMyAgentProto(ProtocolContent queryBean){
		return protocolContentMapper.selectMyAgentProto(queryBean);
	}
	public List<ProtocolContentExportBean> getProtocolContentExport(
			ProtocolContent queryBean) {
		// TODO Auto-generated method stub
		return protocolContentMapper.getProtocolContentExport(queryBean);
	}
	
	/**
	 * 修改协议
	 * @param content
	 */
	public int updateProt(ProtocolContent content){
		return protocolContentMapper.updateProt(content);
	}
	
	/**
	 * 查询协议
	 * @param content
	 * @return
	 */
	public ProtocolContent selectByBean(ProtocolContent content){
		return protocolContentMapper.selectByBean(content);
	}
	
	/**
	 * 修改协议
	 * @param content
	 */
	public int updateColumn(ProtocolColumn column){
		return protocolContentMapper.updateColumn(column);
	}
	
	@Page
	public List<ProtocolContent> selectAgent(ProtocolContent queryBean) {
		return 	protocolContentMapper.selectAgent(queryBean);
	}
	
	public ProtocolInfo selectProtocolInfo(String custId){
		return protocolContentMapper.selectProtocolInfo(custId);
	}
	public List<ProtocolContent> selectNewProtocolInfo(String custId){
		return protocolContentMapper.selectNewProtocolInfo(custId);
	}
	
	public ProtocolInfo selectProtocolForEmail(String custId){
		return protocolContentMapper.selectProtocolForEmail(custId);
	}
}
