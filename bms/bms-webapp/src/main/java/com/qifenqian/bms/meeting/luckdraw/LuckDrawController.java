package com.qifenqian.bms.meeting.luckdraw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.meeting.helper.MeetingHelper;
import com.qifenqian.bms.meeting.luckdraw.bean.Prize;
import com.qifenqian.bms.meeting.luckdraw.bean.PrizeWin;
import com.qifenqian.bms.meeting.luckdraw.service.LuckDrawService;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;

/**
 * @project sevenpay-bms-web
 * @fileName LuckDrawController.java
 * @author huiquan.ma
 * @date 2015年12月15日
 * @memo 
 */
@Controller
@RequestMapping(LuckDrawPath.BASE)
public class LuckDrawController {
	
	private static Logger logger = LoggerFactory.getLogger(LuckDrawController.class);

	@Autowired
	private LuckDrawService luckDrawService;
	
	@Autowired
	private DictionaryUtils dictionaryUtils;
	
	@RequestMapping(LuckDrawPath.PRIZE_LIST)
	public ModelAndView prizeList(Integer activityId) {
		ModelAndView mv = new ModelAndView(LuckDrawPath.BASE + LuckDrawPath.PRIZE_LIST);
		
		if(null == activityId) {
			activityId = MeetingHelper.getActivityId();
		}
		
		Prize selectBean = new Prize();
		selectBean.setActivityId(activityId);
		selectBean.setDynamicSql(" AND P.STATUS IN ('"+PrizeStatus.INIT+"', '"+PrizeStatus.OVER+"', '"+PrizeStatus.DRAW_IN+"')"); 
		mv.addObject("prizeList", luckDrawService.selectPrizeList(selectBean));
		
		return mv;
	}
	
	@RequestMapping(LuckDrawPath.DRAW)
	public ModelAndView draw(Integer prizeId) {
		ModelAndView mv = new ModelAndView(LuckDrawPath.BASE + LuckDrawPath.DRAW);
		
		Prize prize = null;
		if(null == prizeId) {
			prize = luckDrawService.selectNextDrawPrize(MeetingHelper.getActivityId());
		} else {
			prize = luckDrawService.selectPrizeSingleById(prizeId);
		}
		
		mv.addObject("prize", prize);
		
		return mv;
	}
	
	/**
	 * 抽奖
	 * @param prizeId
	 * @return
	 */
	@RequestMapping(LuckDrawPath.LUCK_DRAW)
	@ResponseBody
	public String luckDraw(Integer prizeId) {
		// 请求bean 打印
		logger.info("奖项编号：[{}]", prizeId);

		JSONObject jsonObject = new JSONObject();
		try {
			Prize prize = luckDrawService.selectPrizeSingleById(prizeId);
			// 抽奖
			PrizeWin prizeWin = luckDrawService.selectCustDrawByPrizeRandom(prize);
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("prizeWin", prizeWin);
		} catch (Exception e) {
			logger.error("异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 确认中奖
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(LuckDrawPath.CONFRIM_DRAW)
	@ResponseBody
	public String confrimDraw(PrizeWin requestBean) {
		// 请求bean 打印
		logger.info("中奖信息：[{}]", JSONObject.toJSONString(requestBean, true));

		JSONObject jsonObject = new JSONObject();
		try {
			if(!luckDrawService.isPermit(requestBean.getActivityId())) {
				throw new IllegalArgumentException("无效许可，禁止抽奖!");
			}
			// 抽奖
			luckDrawService.recordDraw(requestBean);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 获取下一个可抽奖奖项
	 * @param prizeId
	 * @return
	 */
	@RequestMapping(LuckDrawPath.NEXT_PRIZE)
	@ResponseBody
	public String nextPrize(Integer activityId) {
		// 请求bean 打印
		logger.info("活动编号：[{}]", activityId);

		JSONObject jsonObject = new JSONObject();
		try {
			if(null == activityId) {
				activityId = MeetingHelper.getActivityId();
			}
			
			Prize nextPrize = luckDrawService.selectNextDrawPrize(activityId);
			
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("nextPrize", nextPrize);
		} catch (Exception e) {
			logger.error("异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
}


