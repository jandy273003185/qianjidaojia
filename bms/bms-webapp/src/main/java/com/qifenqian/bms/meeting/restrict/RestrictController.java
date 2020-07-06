package com.qifenqian.bms.meeting.restrict;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.dictData.bean.DictData;
import com.qifenqian.bms.basemanager.dictData.service.DictDataService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/** 
 * 上下线控制
 * @author  chonggan_shen 
 * @date 2015年12月15日 
 */

@Controller
@RequestMapping(RestrictPath.BASE)
public class RestrictController {
	private static Logger logger = LoggerFactory.getLogger(RestrictController.class);
	
	@Autowired
	private DictDataService dictDataService;
	
	/**
	 * 列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(RestrictPath.LIST)
	public ModelAndView list(DictData queryBean) {
		ModelAndView mv = new ModelAndView(RestrictPath.BASE + RestrictPath.LIST);
		List<DictData> dictDataList = dictDataService.selectDictionaryBeanByRestrict(queryBean);
		mv.addObject("dictDataList", JSONObject.toJSON(dictDataList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	/**
	 * 修改
	 * @param dictData
	 * @return
	 */
	@RequestMapping(RestrictPath.EDIT)
	@ResponseBody
	public String  edit(DictData dictData) {
		logger.info("修改上下线控制对象{}",JSONObject.toJSONString(dictData,true));
		JSONObject jsonObject = new JSONObject();
		try {
			dictData.setLastupdateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
			dictDataService.updateDict(dictData);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("修改数据字典异常",e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		
		return jsonObject.toJSONString();
	}
	

}
