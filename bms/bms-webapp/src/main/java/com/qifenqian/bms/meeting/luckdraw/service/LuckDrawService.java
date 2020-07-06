package com.qifenqian.bms.meeting.luckdraw.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.meeting.luckdraw.bean.Activity;
import com.qifenqian.bms.meeting.luckdraw.bean.Prize;
import com.qifenqian.bms.meeting.luckdraw.bean.PrizeWin;
import com.qifenqian.bms.meeting.luckdraw.dao.LuckDrawDao;
import com.qifenqian.bms.meeting.luckdraw.mapper.LuckDrawMapper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * @project sevenpay-bms-web
 * @fileName LuckDrawService.java
 * @author huiquan.ma
 * @date 2015年12月15日
 * @memo 
 */
@Service
public class LuckDrawService {

	@Autowired
	private LuckDrawMapper luckDrawMapper;
	@Autowired
	private LuckDrawDao luckDrawDao;

	public List<Prize> selectPrizeList(Prize selectBean) {
		return luckDrawMapper.selectPrizeList(selectBean);
	}
	
	public Prize selectPrizeSingleById(int prizeId) {
		return luckDrawMapper.selectPrizeSingleById(prizeId);
	}
	
	public PrizeWin selectCustDrawByPrizeRandom(Prize prize) {
		return luckDrawMapper.selectCustDrawByPrizeRandom(prize);
	}
	
	public Prize selectNextDrawPrize(int activityId) {
		return luckDrawMapper.selectNextDrawPrize(activityId);
	}
	
	public void recordDraw(PrizeWin requestBean) {
		
		if(null == requestBean) {
			throw new IllegalArgumentException("中奖记录对象为空");
		}
		if(requestBean.getPrizeId() < 1) {
			throw new IllegalArgumentException("奖项非法");
		}
		if(null == requestBean.getWinAmount()) {
			throw new IllegalArgumentException("金额为空");
		}
		if(StringUtils.isBlank(requestBean.getWinCustId())) {
			throw new IllegalArgumentException("中奖客户为空");
		}
		
		// 奖项信息
		Prize prize = luckDrawMapper.selectPrizeSingleById(requestBean.getPrizeId());
		// 入库
		luckDrawDao.insertDraw(requestBean, prize);
	}
	
	
	public boolean isPermit(int activityId) {
		Activity activity = luckDrawMapper.selectActivitySingleById(activityId);
		if(null == activity) {
			throw new IllegalArgumentException("活动不存在");
		}
		if(null == activity.getPermitType()) {
			return false;
		}
		
		switch(activity.getPermitType()) {
			case ALL :
				return true;
			case IP :
				if(StringUtils.isNotBlank(activity.getPermitValue()) && WebUtils.getHttpServletRequest().getRemoteAddr().indexOf(activity.getPermitValue()) != -1) {
					return true;
				}
				return false;
			case USER :
				if(StringUtils.isNotBlank(activity.getPermitValue()) && String.valueOf(WebUtils.getUserInfo().getUserId()).indexOf(activity.getPermitValue()) != -1) {
					return true;
				}
				return false;
			default :
				throw new IllegalArgumentException("暂不支持的许可判断[" + activity.getPermitType()+"]");
		
		}
	}
}


