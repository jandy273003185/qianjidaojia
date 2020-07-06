package com.qifenqian.bms.accounting.subjectSummary.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.subjectSummary.bean.SubjectSummary;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface SubjectSummaryMapper {

	List<SubjectSummary> selectSubjectSummary(SubjectSummary subjectSummary);

}
