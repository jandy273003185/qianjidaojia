package com.qifenqian.bms.accounting.subjectSummary;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.subjectSummary.bean.SubjectSummary;
import com.qifenqian.bms.accounting.subjectSummary.service.SubjectSummaryService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(SubjectSummaryPath.BASE)
public class SubjectSummaryContorller {
	private static Logger logger = LoggerFactory.getLogger(SubjectSummaryContorller.class);
	@Autowired
	private SubjectSummaryService subjectSummaryService;
	/**
	 * 科目汇总查询
	 * @param subjectSummary
	 * @return
	 */
	@RequestMapping(SubjectSummaryPath.LIST)
	public ModelAndView list(SubjectSummary subjectSummary) {
		logger.info("====SubjectSummaryContorller list====");
		ModelAndView mv = new ModelAndView(SubjectSummaryPath.BASE + SubjectSummaryPath.LIST);
		String subjectName=subjectSummary.getSubjectName();
		String workDate=subjectSummary.getWorkDate();
		if(StringUtils.isEmpty(subjectSummary.getWorkDate())){
			subjectSummary.setWorkDate(DatetimeUtils.getLastWorkDate());
		}
		List<SubjectSummary> list=subjectSummaryService.selectSubjectSummary(subjectSummary);
		mv.addObject("summaryList", JSONObject.toJSON(list));
		mv.addObject("subjectName", subjectName);
		mv.addObject("workDate", workDate);
		return mv;
	}

}
