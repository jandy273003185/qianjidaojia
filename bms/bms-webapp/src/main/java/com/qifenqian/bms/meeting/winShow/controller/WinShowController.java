package com.qifenqian.bms.meeting.winShow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.service.PrizeService;
import com.qifenqian.bms.meeting.winShow.bean.BasePrizeWin;
import com.qifenqian.bms.meeting.winShow.service.WinShowService;

@Controller
@RequestMapping(WinShowPath.BASE)
public class WinShowController {
	
	private static Logger logger = LoggerFactory.getLogger(WinShowController.class);
	
	@Autowired
	private WinShowService winShowService;
	
	@Autowired
	private DictDataService dictDataService;
	
	@Autowired
	private PrizeService prizeService;
	
	/**
	 * 活动主页面
	 * @return
	 */
	@RequestMapping(WinShowPath.MAIN)
	public ModelAndView showMain(){
		
		ModelAndView mv = new ModelAndView(WinShowPath.BASE+WinShowPath.MAIN);
		
		return mv;
		
	}
	
	/**
	 * 中奖显示主页面
	 * @return
	 */
	@RequestMapping(WinShowPath.WINSHOW)
	public ModelAndView winShow(){
		
		ModelAndView mv = new ModelAndView(WinShowPath.BASE+WinShowPath.WINSHOW);
		
		return mv;
		
	}
	
	/**
	 * 中奖人员信息
	 * @return
	 */
	@RequestMapping(WinShowPath.WININFO)
	@ResponseBody
	public String winInfo(String dataPath,HttpServletRequest request){
		
		JSONObject object = new JSONObject();
		
		try {
			DictData dictData = dictDataService.selectByDataPath(dataPath);
			
			//奖项编号
			String prizeId = dictData.getValue();
			
			// 奖项
			PrizeBean prize = prizeService.selectPrizeSingleById(Integer.parseInt(prizeId));
			
			
			List<BasePrizeWin> prizeList =winShowService.showPrizeWinNow(prizeId);
			
			object.put("result", "SUCCESS");
			object.put("scollTitle", prize.getPrizeName() + "(中奖人数"+prizeList.size()+")");
			object.put("winList", prizeList);
		} catch (Exception e) {
			
			logger.error("滚动异常"+e.getMessage());
			object.put("result", "FAIL");
		}

		return object.toJSONString();
		
	}
	
}
