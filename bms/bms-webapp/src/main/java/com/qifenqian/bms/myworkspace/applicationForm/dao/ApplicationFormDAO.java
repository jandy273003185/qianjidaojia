package com.qifenqian.bms.myworkspace.applicationForm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.myworkspace.applicationForm.bean.ApplicationFormBean;
import com.qifenqian.bms.myworkspace.applicationForm.mapper.ApplicationFormMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ApplicationFormDAO {
	
	@Autowired
	private ApplicationFormMapper applicationFormMapper;
	
	@Page
	public List<ApplicationFormBean> getApplicationForm(ApplicationFormBean bean){
		
		return applicationFormMapper.getApplicationForm(bean);
	}
}
