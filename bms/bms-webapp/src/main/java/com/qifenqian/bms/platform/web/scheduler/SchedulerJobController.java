package com.qifenqian.bms.platform.web.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.platform.web.scheduler.bean.SchedulerJob;
import com.qifenqian.bms.platform.web.scheduler.service.SchedulerJobService;

/**
 * 任务调度配置
 * 
 * @author pc
 *
 */
@Controller
@RequestMapping(SchedulerJobPath.BASE)
public class SchedulerJobController {

	private Logger logger = LoggerFactory.getLogger(SchedulerJobController.class);
	@Autowired
	private SchedulerJobService schedulerJobService;

	@RequestMapping(SchedulerJobPath.LIST)
	public ModelAndView list(SchedulerJob job) {
		logger.info("任务调度配置查询" + job);
		ModelAndView mv = new ModelAndView(SchedulerJobPath.BASE + SchedulerJobPath.LIST);
		String jobName=job.getJobName();
		String classPath=job.getClassPath();
		String hostName=job.getHostName();
		String isEnable=job.getIsEnable();
		List<SchedulerJob> list = schedulerJobService.seleteSchedulerJob(job);
		mv.addObject("jobName", jobName);
		mv.addObject("classPath", classPath);
		mv.addObject("hostName", hostName);
		mv.addObject("isEnable", isEnable);
		mv.addObject("jobList", JSONObject.toJSON(list));

		return mv;
	}

	/**
	 * 新增
	 * 
	 * @param requestRole
	 * @return
	 */
	@RequestMapping(SchedulerJobPath.ADD)
	@ResponseBody
	public String add(SchedulerJob job) {
		// 请求bean 打印
		logger.info("请求保存job：[{}]", JSONObject.toJSONString(job, true));

		JSONObject jsonObject = new JSONObject();

		try {
			User user = WebUtils.getUserInfo();
			job.setCreator(String.valueOf(user.getUserId()));
			schedulerJobService.addSchedulerJob(job);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("新增任务异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 删除
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(SchedulerJobPath.DELETE)
	@ResponseBody
	public String delete(SchedulerJob job) {
		// 请求bean 打印
		logger.info("请求修改job：[{}]", job);

		JSONObject jsonObject = new JSONObject();
		try {

			User user = WebUtils.getUserInfo();
			job.setLastUpdateUser(String.valueOf(user.getUserId()));
			// 保存修改
			schedulerJobService.deleteSchedulerJob(job);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除角色异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 修改
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(SchedulerJobPath.EDIT)
	@ResponseBody
	public String edit(SchedulerJob job) {
		// 请求bean 打印
		logger.info("请求修改job：[{}]", JSONObject.toJSONString(job, true));

		JSONObject jsonObject = new JSONObject();
		try {
			User user = WebUtils.getUserInfo();
			job.setLastUpdateUser(String.valueOf(user.getUserId()));
			// 修改角色
			schedulerJobService.updateSchedulerJob(job);

			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("修改角色异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 执行
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping(SchedulerJobPath.EXECUTE)
	@ResponseBody
	public String execute(SchedulerJob job) {
		// 请求bean 打印
		logger.info("请求执行job：[{}]", JSONObject.toJSONString(job, true));

		JSONObject jsonObject = new JSONObject();
		try {

			schedulerJobService.execute(job);	
			
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("执行异常", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
}
