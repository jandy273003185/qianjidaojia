package com.qifenqian.bms.meeting.activity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.meeting.activity.bean.ActivityBean;
import com.qifenqian.bms.meeting.activity.service.ActivityService;

@Controller
@RequestMapping(ActivityPath.BASE)
public class ActivityController {
	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private ActivityService activityService;

	/**
	 * 活动列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(ActivityPath.LIST)
	public ModelAndView list(ActivityBean queryBean) {
		logger.info("查询活动信息对象{}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(ActivityPath.BASE + ActivityPath.LIST);
		List<ActivityBean> activityList = activityService.selectActivityList(queryBean);
		mv.addObject("activityList", JSONObject.toJSON(activityList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	/***
	 * 新增
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ActivityPath.ADD)
	@ResponseBody
	public String add(ActivityBean insertBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == insertBean) {
				throw new IllegalArgumentException("新增对象为空");
			}
			activityService.insertActivityBean(insertBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("新增异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}

	/***
	 * 修改
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ActivityPath.EDIT)
	@ResponseBody
	public String edit(ActivityBean updateBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == updateBean) {
				throw new IllegalArgumentException("修改对象为空");
			}
			if (updateBean.getActivityId() < 1) {
				throw new IllegalArgumentException("修改编号为空");
			}
			activityService.updateActivityBean(updateBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("修改异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}
}
