package com.qifenqian.bms.basemanager.dictData.controller;


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
/***
 * 数据字典
 * @author shen
 *
 */
@Controller
@RequestMapping(DictDataPath.BASE)
public class DictDataController {

	private Logger logger = LoggerFactory.getLogger(DictDataController.class);
	
	@Autowired
	private DictDataService dictDataService;

	
	/**
	 * 显示数据字典
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(DictDataPath.LIST)
	public ModelAndView list(DictData dictData) {
		ModelAndView mv = new ModelAndView(DictDataPath.BASE + DictDataPath.LIST);
		List<DictData> dictDataList = dictDataService.selectDictDataList(dictData);
		
		mv.addObject("dictDataList", JSONObject.toJSONString(dictDataList));
		mv.addObject("dictDatas", dictDataList);
		mv.addObject("dictDataBean", dictData);
	
		return mv;
	}
	
	/**
	 * 增加数据字典
	 * @param dictData
	 * @return
	 */
	@RequestMapping(DictDataPath.ADD)
	@ResponseBody
	public String  add(DictData dictData) {
		logger.info("增加数据字典",JSONObject.toJSONString(dictData,true));
		JSONObject jsonObject = new JSONObject();
		try {
			dictData.setCreator(String.valueOf(WebUtils.getUserInfo().getUserId()));
			dictDataService.insertDict(dictData);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("增加异常",e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		
		
		
	
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 修改数据字典
	 * @param dictData
	 * @return
	 */
	@RequestMapping(DictDataPath.EDIT)
	@ResponseBody
	public String  edit(DictData dictData) {
		logger.info("修改数据字典",JSONObject.toJSONString(dictData,true));
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
	
	/**
	 * 删除数据字典
	 * @param dictData
	 * @return
	 */
	@RequestMapping(DictDataPath.DELETE)
	@ResponseBody
	public String  delete(DictData dictData) {
		logger.info("删除数据字典",JSONObject.toJSONString(dictData,true));
		JSONObject jsonObject = new JSONObject();
		try {
		
			dictDataService.deleteDictData(dictData);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("删除数据字典异常",e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		
		return jsonObject.toJSONString();
	}

}
