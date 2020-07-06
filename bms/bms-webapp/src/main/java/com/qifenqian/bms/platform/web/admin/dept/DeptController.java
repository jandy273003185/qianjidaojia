package com.qifenqian.bms.platform.web.admin.dept;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.service.DeptService;
import com.qifenqian.bms.platform.web.admin.dept.type.DeptStatus;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 部门管理
 * 
 * @project gyzb-platform-web-admin
 * @fileName DeptController.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Controller
@RequestMapping(DeptPath.BASE)
public class DeptController {

	private Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private DeptService deptService;

	/**
	 * 部门列表查询
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping(DeptPath.LIST)
	public ModelAndView list(Dept dept) {

		ModelAndView mv = new ModelAndView(DeptPath.BASE + DeptPath.LIST);
		List<Dept> list = deptService.selectDeptList(dept);
		mv.addObject("requestDept", dept);
		mv.addObject("deptList", JSONObject.toJSON(list));
		return mv;
	}

	/**
	 * 部门新增
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping(DeptPath.ADD)
	@ResponseBody
	public String add(Dept dept) {
		// 请求bean 打印
		logger.info("请求保存dept：[{}]", JSONObject.toJSONString(dept, true));

		JSONObject jsonObject = new JSONObject();

		try {

			if (deptService.deptCodeIsExists(dept.getDeptCode())) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "部门代码已存在");
			} else {
				User user = WebUtils.getUserInfo();
				dept.setInstUser(user.getUserId());
				// 请求bean 打印
				logger.info("请求保存部门创建人：[{}]", JSONObject.toJSONString(user.getUserId(), true));
				// 保存新增
				deptService.addDept(dept);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("新增部门异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 部门修改
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(DeptPath.EDIT)
	@ResponseBody
	public String edit(Dept dept) {
		// 请求bean 打印
		logger.info("请求修改dept：[{}]", JSONObject.toJSONString(dept, true));

		JSONObject jsonObject = new JSONObject();
		try {
			if (deptService.deptCodeAndIdIsExists(dept)) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "部门代码已被使用");
			} else {
				User user = WebUtils.getUserInfo();
				dept.setLupdUser(user.getUserId());
				logger.info("请求修改部门更新人：[{}]", JSONObject.toJSONString(user.getUserId(), true));
				deptService.editDept(dept);
				jsonObject.put("result", "SUCCESS");
			}

		} catch (Exception e) {
			logger.error("修改部门异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 部门删除
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(DeptPath.DELETE)
	@ResponseBody
	public String delete(int deptId) {
		// 请求bean 打印
		logger.info("请求修改dept：[{}]", deptId);

		JSONObject jsonObject = new JSONObject();
		try {
			User user = WebUtils.getUserInfo();
			Dept dept = new Dept();
			dept.setLupdUser(user.getUserId());
			dept.setDeptId(deptId);
			dept.setStatus(DeptStatus.DISABLE);
			logger.info("请求删除部门删除人员：[{}]", JSONObject.toJSONString(user.getUserId(), true));
			// 保存修改
			deptService.deleteDept(dept);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除部门异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

}
