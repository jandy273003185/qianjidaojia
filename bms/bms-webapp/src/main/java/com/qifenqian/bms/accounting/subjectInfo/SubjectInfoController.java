package com.qifenqian.bms.accounting.subjectInfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.subjectInfo.bean.SubjectInfo;
import com.qifenqian.bms.accounting.subjectInfo.service.SubjectInfoService;
import com.qifenqian.bms.basemanager.merchant.MerchantPath;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
/**
 * 科目管理
 * @author pc
 *
 */
@Controller
@RequestMapping(SubjectInfoPath.BASE)
public class SubjectInfoController {
	private static Logger logger = LoggerFactory.getLogger(SubjectInfoController.class);
	
	@Autowired
	private SubjectInfoService service;
	
	/**
	 * 查询科目列表
	 * @param subjectInfo
	 * @return
	 */
	@RequestMapping(SubjectInfoPath.LIST)
	public ModelAndView list(SubjectInfo subjectInfo) {
		String subjectName=subjectInfo.getSubjectName();
		String subjectCode=subjectInfo.getSubjectCode();
		ModelAndView mv = new ModelAndView(SubjectInfoPath.BASE + SubjectInfoPath.LIST);
		
		List<SubjectInfo>list=service.selectSubjectInfo(subjectInfo);
		mv.addObject("subList", JSONObject.toJSON(list));
		mv.addObject("subjectName", subjectName);
		mv.addObject("subjectCode", subjectCode);
		return mv;
	}
	
	/**
	 * 新增科目
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectInfoPath.ADD)
	@ResponseBody
	public String addSubject(SubjectInfo subject){
		logger.info("增加科目");		
		JSONObject object= new JSONObject();
		
		try {
			User user= WebUtils.getUserInfo();
			subject.setCreator(String.valueOf(user.getUserId()));
			service.createSubjectInfo(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("增加科目出错：",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}		
		return object.toJSONString();
	}
	
	/**
	 *修改科目 
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectInfoPath.UPDATE)
	@ResponseBody
	public String updateSubject(SubjectInfo subject){
		logger.info("更新科目");		
		JSONObject object= new JSONObject();
		
		try {
			User user= WebUtils.getUserInfo();
			subject.setLastUpdateUser(String.valueOf(user.getUserId()));
			service.updateSubjectInfo(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("更新科目出错：",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}		
		return object.toJSONString();
	}
	
	/**
	 * 删除科目
	 * @param subject
	 * @return
	 */
	@RequestMapping(SubjectInfoPath.DELETE)
	@ResponseBody
	public String deleSubject(SubjectInfo subject){
		logger.info("删除科目");		
		JSONObject object= new JSONObject();
		
		try {
			User user= WebUtils.getUserInfo();
			subject.setLastUpdateUser(String.valueOf(user.getUserId()));
			service.deleteSubjectInfo(subject);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("删除科目出错：",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}		
		return object.toJSONString();
	}
	
	/**
	 * 校验科目代码是否已经存在
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATE)
	@ResponseBody
	public String validateSubjectCode(String subjectCode){
		
		logger.info("校验科目代码");
		
		JSONObject object = new JSONObject();
		
		try {
			String subjectCode2= service.selectSubjectCode(subjectCode);
			
			if(subjectCode2.equals("0")){
				object.put("result", "SUCCESS");
			}else{
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验科目代码出现问题"+e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		
		return object.toJSONString();
		
	}
}
