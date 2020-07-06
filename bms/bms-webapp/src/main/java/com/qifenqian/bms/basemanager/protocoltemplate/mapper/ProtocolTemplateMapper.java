package com.qifenqian.bms.basemanager.protocoltemplate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;

@MapperScan
public interface ProtocolTemplateMapper {

	int insert(ProtocolTemplate insertBean);

	int update(ProtocolTemplate updateBean);
	
	List<ProtocolTemplate> select(ProtocolTemplate queryBean);

	ProtocolTemplate selectTemplateById(@Param("id") String id);
}
