package com.qifenqian.bms.accounting.subjectInfo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.subjectInfo.bean.SubjectInfo;
import com.qifenqian.bms.accounting.subjectInfo.dao.SubjectInfoDao;
import com.qifenqian.bms.accounting.subjectInfo.mapper.SubjectInfoMapper;

@Service
public class SubjectInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectInfoService.class);

	@Autowired
	private SubjectInfoMapper subjectInfoMapper;

	@Autowired
	private SubjectInfoDao subjectInfoDao;
	
	/**
	 * 创建科目信息
	 * @param actSevenCust
	 * @param actFlow
	 */
	public void createSubjectInfo(SubjectInfo subjectBean) {
		logger.info("创建科目信息:subjectCode"+subjectBean.getSubjectCode());
		subjectInfoMapper.createSubjectInfo(subjectBean);
		
	}
	

	/**
	 * 修改科目信息
	 * @param actSevenCust
	 * @param actFlow
	 */
	public void updateSubjectInfo(SubjectInfo subjectBean) {
		logger.info("修改科目信息:subjectCode"+subjectBean.getSubjectId());
		subjectInfoMapper.updateSubjectInfo(subjectBean);
		
	}
	
	/**
	 * 查询科目信息
	 * @param request
	 * @return
	 */
	public List<SubjectInfo> selectSubjectInfo(SubjectInfo subject) {
		logger.info("查询科目信息");
		return subjectInfoDao.selectSubjectInfo(subject);
	}
	
	public String selectSubjectCode(String subjectCode) {
		
		return subjectInfoMapper.selectSubjectCode(subjectCode);
	}
	
	public void deleteSubjectInfo(SubjectInfo subject){
		subjectInfoMapper.deleteSubjectInfo(subject);
	}
}
