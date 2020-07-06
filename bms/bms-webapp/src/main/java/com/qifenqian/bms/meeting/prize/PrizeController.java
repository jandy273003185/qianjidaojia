package com.qifenqian.bms.meeting.prize;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.meeting.MeetingConstant;
import com.qifenqian.bms.meeting.activity.bean.ActivityBean;
import com.qifenqian.bms.meeting.activity.mapper.ActivityMapper;
import com.qifenqian.bms.meeting.activity.type.ActivityStatus;
import com.qifenqian.bms.meeting.helper.MeetingHelper;
import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.service.PrizeService;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;

@Controller
@RequestMapping(PrizePath.BASE)
public class PrizeController {
	private static Logger logger = LoggerFactory.getLogger(PrizeController.class);

	@Autowired
	private PrizeService prizeService;
	@Autowired
	private ActivityMapper activityMapper;

	/**
	 * 奖金配置列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(PrizePath.LIST)
	public ModelAndView list(PrizeBean queryBean) {
		logger.info("奖金配置列表查询对象 {}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(PrizePath.BASE + PrizePath.LIST);
		List<PrizeBean> prizeList = prizeService.selectPrizeBeanList(queryBean);
		mv.addObject("prizeList", JSONObject.toJSON(prizeList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/***
	 * 新增
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(PrizePath.ADD)
	@ResponseBody
	public String add(PrizeBean insertBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == insertBean) {
				throw new IllegalArgumentException("新增对象为空");
			}
			// 查询活动
			ActivityBean activityBean = activityMapper.selectActivityById(insertBean.getActivityId());
			if (!Arrays.asList(ActivityStatus.INIT, ActivityStatus.STAGE_ING).contains(activityBean.getStatus())) {
				throw new IllegalArgumentException("活动已结束或已失效");
			}
			if (DateFormatUtils.format(new Date(), "yyyy-MM-dd").compareTo(activityBean.getEndDate()) > 0) {
				throw new IllegalArgumentException("已超过活动结束时间");
			}
			prizeService.insertPrizeBean(insertBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("新增异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}

	/***
	 * 开始
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(PrizePath.START)
	@ResponseBody
	public String start(Integer prizeId) {
		JSONObject json = new JSONObject();
		try {
			//
			PrizeBean prizeBean = prizeService.selectPrizeSingleById(prizeId);
			// 查询活动
			ActivityBean activityBean = activityMapper.selectActivityById(prizeBean.getActivityId());
			if (!Arrays.asList(ActivityStatus.STAGE_ING).contains(activityBean.getStatus())) {
				throw new IllegalArgumentException("活动还未进行");
			}
			if (DateFormatUtils.format(new Date(), "yyyy-MM-dd").compareTo(activityBean.getEndDate()) > 0) {
				throw new IllegalArgumentException("已超过活动结束时间");
			}

			if (MeetingHelper.getEnvironment().equals(MeetingConstant.MESSAGE_TO_ALL)) {
				prizeService.startPrize(prizeId);
			} else if (MeetingHelper.getEnvironment().equals(MeetingConstant.MESSAGE_TO_CUR)) {
				prizeService.currentStartPrize(prizeId);
			}

			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("开始异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}

	/***
	 * 重发
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(PrizePath.RESEND)
	@ResponseBody
	public String resend(Integer prizeId) {
		JSONObject json = new JSONObject();
		try {
			//
			PrizeBean prizeBean = prizeService.selectPrizeSingleById(prizeId);
			// 查询活动
			ActivityBean activityBean = activityMapper.selectActivityById(prizeBean.getActivityId());
			if (!Arrays.asList(ActivityStatus.STAGE_ING).contains(activityBean.getStatus())) {
				throw new IllegalArgumentException("活动还未进行");
			}
			if (DateFormatUtils.format(new Date(), "yyyy-MM-dd").compareTo(activityBean.getEndDate()) > 0) {
				throw new IllegalArgumentException("已超过活动结束时间");
			}

			if (MeetingHelper.getEnvironment().equals(MeetingConstant.MESSAGE_TO_ALL)) {
				prizeService.resendPrize(prizeId);
			} else if (MeetingHelper.getEnvironment().equals(MeetingConstant.MESSAGE_TO_CUR)) {
				prizeService.currentResendPrize(prizeId);
			}
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("重发异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}

	/***
	 * 结束
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(PrizePath.END)
	@ResponseBody
	public String end(Integer prizeId) {
		JSONObject json = new JSONObject();
		try {
			PrizeBean updateBean = new PrizeBean();
			updateBean.setPrizeId(prizeId);
			updateBean.setStatus(PrizeStatus.OVER);
			prizeService.updatePrizeBean(updateBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("结束异常", e);
			json.put("result", "FAILURE");
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
	@RequestMapping(PrizePath.EDIT)
	@ResponseBody
	public String edit(PrizeBean updateBean) {
		JSONObject json = new JSONObject();
		try {
			if (null == updateBean) {
				throw new IllegalArgumentException("修改对象为空");
			}
			if (updateBean.getPrizeId() < 1) {
				throw new IllegalArgumentException("修改编号为空");
			}
			prizeService.updatePrizeBean(updateBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("修改异常", e);
			json.put("result", "FAILURE");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}
}
