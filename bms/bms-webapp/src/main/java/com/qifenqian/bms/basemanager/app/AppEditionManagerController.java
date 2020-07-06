/**
 * 
 */
package com.qifenqian.bms.basemanager.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.app.bean.TdAppEditionControl;
import com.qifenqian.bms.basemanager.app.service.AppEditionManagerService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
* @ClassName: AppEditionManager
* @Description: TODO(APP版本管理控制层)
* @author LvFeng
* @date 2019年12月18日
*
*/
@RequestMapping("/appEditionManager")
@Controller
public class AppEditionManagerController {
	
	@Autowired
	private AppEditionManagerService appEditionManagerService;
	
	@Value("${APP_EDITION_SERVICE_IP}")
	private String appPaths;
	
	
	@RequestMapping("/list")
	public ModelAndView appEditionManagerPage(TdAppEditionControl queryBean) {
		ModelAndView modelAndView = new ModelAndView();
		List<TdAppEditionControl> listAppEditionControls = appEditionManagerService.listTdAppEditionControl(queryBean);
		modelAndView.addObject("queryBean", queryBean);
		modelAndView.addObject("listAppEditionControls", listAppEditionControls);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, String> addAppEdition(TdAppEditionControl tdAppEditionControl) {
		Map<String, String> result = new HashMap<String, String>();
		if (StringUtils.isBlank(tdAppEditionControl.getFileUrl()) 
				|| StringUtils.isBlank(tdAppEditionControl.getMachineType()) 
				|| StringUtils.isBlank(tdAppEditionControl.getEditionId())) {
			result.put("result", "FAIL");
			result.put("message", "参数缺失");
			return result;
		}
		tdAppEditionControl.setState("00");
		tdAppEditionControl.setCreator(WebUtils.getUserInfo().getUserName());
		tdAppEditionControl.setFileUrl(appPaths + tdAppEditionControl.getFileUrl());
		int flag = appEditionManagerService.insertTdAppEditionControl(tdAppEditionControl);
		if (flag > 0) {
			result.put("result", "SUCCESS");
			result.put("message", "新增成功");
		} else {
			result.put("result", "FAIL");
			result.put("message", "新增失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, String> deleteAppEdition(TdAppEditionControl tdAppEditionControl) {
		Map<String, String> result = new HashMap<String, String>();
		if (StringUtils.isBlank(tdAppEditionControl.getId())) {
			result.put("result", "FAIL");
			result.put("message", "ID不能为空");
			return result;
		}
		tdAppEditionControl.setState("99");
		int flag = appEditionManagerService.updateTdAppEditionControl(tdAppEditionControl);
		if (flag > 0) {
			result.put("result", "SUCCESS");
			result.put("message", "停用成功");
		} else {
			result.put("result", "FAIL");
			result.put("message", "停用失败");
		}
		return result;
		
	}
}
