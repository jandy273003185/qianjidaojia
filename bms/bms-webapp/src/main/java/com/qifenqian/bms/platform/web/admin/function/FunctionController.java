package com.qifenqian.bms.platform.web.admin.function;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;
import com.qifenqian.bms.platform.web.admin.rolefunction.service.RoleFunctionService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionController.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Controller
@RequestMapping(FunctionPath.BASE)
public class FunctionController {

	private Logger logger = LoggerFactory.getLogger(FunctionController.class);
	@Autowired
	private FunctionService functionService;

	@Autowired
	private RoleFunctionService roleFunctionService;

	/**
	 * 菜单列表展示
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(FunctionPath.LIST)
	public ModelAndView list(Function function) {

		List<Function> functions = functionService.selectFunctionByIdAndName(function);
		ModelAndView mv = new ModelAndView(FunctionPath.BASE + FunctionPath.LIST);
		mv.addObject("requestBean", function);
		mv.addObject("functions", functions);
		mv.addObject("functionList", JSONObject.toJSON(functions));
		return mv;
	}

	/**
	 * 菜单新增
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(FunctionPath.ADD)
	@ResponseBody
	public String add(Function function) {
		// 请求bean 打印
		logger.info("请求增加function：[{}]", JSONObject.toJSON(function));
		JSONObject jsonObject = new JSONObject();
		try {
			if (functionService.functionCodeIsExists(function.getFunctionCode())) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "菜单代码已存在");
			} else {
				User user = WebUtils.getUserInfo();
				function.setInstUser(user.getUserId());
				functionService.insertFunction(function);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("新增function异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 修改
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(FunctionPath.EDIT)
	@ResponseBody
	public String update(Function function) {
		logger.info("请求修改function：[{}]", JSONObject.toJSON(function));

		JSONObject jsonObject = new JSONObject();
		User user = WebUtils.getUserInfo();
		function.setLupdUser(user.getUserId());

		try {
			if (functionService.functionCodeAndIdIsExists(function)) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "菜单代码已被使用");
			} else {
				functionService.updateFunction(function);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("请求修改function异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 删除
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(FunctionPath.DELETE)
	@ResponseBody
	public String delete(Function function) {
		logger.info("请求删除function：[{}]", JSONObject.toJSON(function));

		JSONObject jsonObject = new JSONObject();

		try {
			// 删除菜单
			functionService.deleteFunction(function.getFunctionId());

			// 删除角色与菜单的关系
			roleFunctionService.deleteRoleFunctionbyFunctionId(function.getFunctionId());
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求删除function异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

}
