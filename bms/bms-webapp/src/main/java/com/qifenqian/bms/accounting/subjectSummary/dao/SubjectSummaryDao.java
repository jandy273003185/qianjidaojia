package com.qifenqian.bms.accounting.subjectSummary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.subjectSummary.bean.SubjectSummary;
import com.qifenqian.bms.accounting.subjectSummary.mapper.SubjectSummaryMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class SubjectSummaryDao {
	@Autowired
	private SubjectSummaryMapper subjectSummaryMapper;

	@Page
	public List<SubjectSummary> selectSubjectSummary(SubjectSummary subjectSummary) {
		return subjectSummaryMapper.selectSubjectSummary(subjectSummary);
	}

}
