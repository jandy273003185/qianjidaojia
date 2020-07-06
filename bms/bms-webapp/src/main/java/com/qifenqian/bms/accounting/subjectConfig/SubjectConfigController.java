package com.qifenqian.bms.accounting.subjectConfig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.subjectConfig.bean.SubjectConfig;
import com.qifenqian.bms.accounting.subjectConfig.service.SubjectConfigSerivce;
import com.qifenqian.bms.accounting.subjectInfo.service.SubjectInfoService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 科目配置管理
 * @author pc
 *
 */
@Controller
@RequestMapping(SubjectConfigPath.BASE)
public class SubjectConfigController {

	private static Logger logger = LoggerFactory.getLogger(SubjectConfigController.class);

	@Autowired
	private SubjectConfigSerivce service;

	@Autowired
	private SubjectInfoService infoService;

	/**
	 * 科目配置列表
	 * @param subjectInfo
	 * @return
	 */
	@RequestMapping(SubjectConfigPath.LIST)
	public ModelAndView list(SubjectConfig queryBean) {
		ModelAndView mv = new ModelAndView(SubjectConfigPath.BASE + SubjectConfigPath.LIST);
		List<SubjectConfig> list = service.selectSubjectConfig(queryBean);
		mv.addObject("queryBean",queryBean);
		mv.addObject("subConfigList", JSONObject.toJSON(list));
		return mv;
	}
	
	/**
	 * 新增科目配置
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectConfigPath.ADD)
	@ResponseBody
	public String addSubject(SubjectConfig subject) {
		logger.info("增加科目配置信息");
		JSONObject object = new JSONObject();
		User user = WebUtils.getUserInfo();
		subject.setCreator(String.valueOf(user.getUserId()));
		try {
			service.insertSubjectConfig(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("增加科目配置信息出错：", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/**
	 * 修改科目配置
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectConfigPath.UPDATE)
	@ResponseBody
	public String updateSubject(SubjectConfig subject) {
		logger.info("更新科目配置信息");
		JSONObject object = new JSONObject();
		User user = WebUtils.getUserInfo();
		subject.setLastupdateUser(String.valueOf(user.getUserId()));
		try {
			service.updateSubjectConfig(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("更新科目配置信息出错：", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/***
	 * 删除科目配置
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectConfigPath.DELETE)
	@ResponseBody
	public String delSubject(SubjectConfig subject) {
		logger.info("删除科目配置信息");
		JSONObject object = new JSONObject();
		User user = WebUtils.getUserInfo();
		subject.setLastupdateUser(String.valueOf(user.getUserId()));
		try {
			service.delSubjectConfig(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("删除科目配置信息出错：", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
}
