package com.qifenqian.bms.basemanager.protocolcontent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolColumn;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContentExportBean;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolInfo;

@MapperScan
public interface ProtocolContentMapper {
	
	int insertContent(ProtocolContent insertBean);
	
	int insertColumn(ProtocolColumn insertBean);
	
	List<ProtocolContent> select(ProtocolContent queryBean);

	int delete(ProtocolContent deleteBean);

	int deleteProtocol(ProtocolContent deleteBean);
	
	ProtocolContent selectProto(@Param("custId")String custId);
	
	public List<ProtocolContent> selectMyProto(ProtocolContent queryBean);
	
	public List<ProtocolContent> selectMyAgentProto(ProtocolContent queryBean);

	List<ProtocolContentExportBean> getProtocolContentExport(ProtocolContent queryBean);
	
	public int updateProt(ProtocolContent content);
	
	public ProtocolContent selectByBean(ProtocolContent content);
	
	public int updateColumn(ProtocolColumn column);

	List<ProtocolContent> selectAgent(ProtocolContent queryBean);
	
	public ProtocolInfo selectProtocolInfo(@Param("custId")String custId);
	
	public List<ProtocolContent> selectNewProtocolInfo(@Param("custId")String custId);
	
	public ProtocolInfo selectProtocolForEmail(@Param("custId")String custId);
}
