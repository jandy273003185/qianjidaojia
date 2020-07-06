package com.qifenqian.bms.accounting.subjectSummary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.subjectSummary.bean.SubjectSummary;
import com.qifenqian.bms.accounting.subjectSummary.mapper.SubjectSummaryMapper;

@Service
public class SubjectSummaryService {
	
	@Autowired
	private SubjectSummaryMapper subjectSummaryMapper;

	public List<SubjectSummary> selectSubjectSummary(SubjectSummary subjectSummary) {
		return subjectSummaryMapper.selectSubjectSummary(subjectSummary);
	}

}
