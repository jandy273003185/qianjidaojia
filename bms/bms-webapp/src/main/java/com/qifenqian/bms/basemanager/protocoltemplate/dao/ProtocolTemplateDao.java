package com.qifenqian.bms.basemanager.protocoltemplate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.mapper.ProtocolTemplateMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ProtocolTemplateDao {
	@Autowired
	private ProtocolTemplateMapper protocolTemplateMapper;

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<ProtocolTemplate> select(ProtocolTemplate queryBean) {
		return protocolTemplateMapper.select(queryBean);
	}
}
