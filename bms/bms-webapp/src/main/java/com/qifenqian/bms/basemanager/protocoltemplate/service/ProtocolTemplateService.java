package com.qifenqian.bms.basemanager.protocoltemplate.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.dao.ProtocolTemplateDao;
import com.qifenqian.bms.basemanager.protocoltemplate.mapper.ProtocolTemplateMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class ProtocolTemplateService {

	private static Logger logger = LoggerFactory.getLogger(ProtocolTemplateService.class);
	@Autowired
	private ProtocolTemplateDao protocolTemplateDao;

	@Autowired
	private ProtocolTemplateMapper protocolTemplateMapper;

	/**
	 * 查詢协议模板列表
	 * @param queryBean
	 * @return
	 */
	public List<ProtocolTemplate> select(ProtocolTemplate queryBean) {

		logger.info("查询协议模板列表  [{}]", JSONObject.toJSONString(queryBean, true));

		return protocolTemplateDao.select(queryBean);
	}

	/***
	 * 修改协议模板
	 * @param updateBean
	 * @return
	 */
	public int update(ProtocolTemplate updateBean) {
		logger.info("修改协议模板对象  [{}]", JSONObject.toJSONString(updateBean, true));
		if (null == updateBean) {
			throw new IllegalArgumentException("修改协议对象 为空");
		}
		if (StringUtils.isBlank(updateBean.getId())) {
			throw new IllegalArgumentException("修改协议对象 为空");
		}
		updateBean.setUpdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		return protocolTemplateMapper.update(updateBean);
	}

	/***
	 * 新增协议模板
	 * @param insertBean
	 * @return
	 */
	public int insert(ProtocolTemplate insertBean) {
		logger.info("新增协议模板對象 [{}]", JSONObject.toJSONString(insertBean, true));
		if (null == insertBean) {
			throw new IllegalArgumentException("新增协议对象 为空");
		}
		String id = GenSN.getOperateID();
		insertBean.setId(id);
		insertBean.setInstUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		insertBean.setStatus("VALID");
		return protocolTemplateMapper.insert(insertBean);
	}
	
	
	/**
	 * 查詢协议模板列表
	 * @param queryBean
	 * @return
	 */
	public ProtocolTemplate selectTemplateById(String id) {

		logger.info("查询协议模板编号 [{}]", id);
		if(StringUtils.isBlank(id)){
			throw new IllegalArgumentException("查询协议模板编号为空");
		}
		
		return protocolTemplateMapper.selectTemplateById(id);
	}
}
