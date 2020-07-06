package com.qifenqian.bms.myworkspace.applicationForm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.myworkspace.ActViewUrl;
import com.qifenqian.bms.myworkspace.ActViewUrlMapper;
import com.qifenqian.bms.myworkspace.applicationForm.bean.ActHiProcinst;
import com.qifenqian.bms.myworkspace.applicationForm.bean.ApplicationFormBean;
import com.qifenqian.bms.myworkspace.applicationForm.dao.ApplicationFormDAO;
import com.qifenqian.bms.myworkspace.applicationForm.mapper.ApplicationFormMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Service
public class ApplicationFormService {

	@Autowired
	private ApplicationFormDAO dao;
	
	@Autowired
	private ActViewUrlMapper actViewUrlMapper;
	
	@Autowired
	private ApplicationFormMapper applicationFormMapper;
	
	/**
	 * 我的申请单
	 * @param userId
	 * @return
	 */
	
	@Page
	public List<ApplicationFormBean> getMyApplictionForm(ApplicationFormBean bean){
		
		return dao.getApplicationForm(bean);
	}
	
	
	/**
	 * 查找申请单URL信息
	 * @param applicationName
	 * @return
	 */
	 public ActViewUrl selectByApplicationName( String applicationName){
		return actViewUrlMapper.selectByApplicationName(applicationName);
	 }
	 
	 /**
	  * 查找流程信息
	  * @param id
	  * @return
	  */
	 public ActHiProcinst selectActHiProcinstById( String id){
		return applicationFormMapper.selectActHiProcinstById(id);
	 } 
}
