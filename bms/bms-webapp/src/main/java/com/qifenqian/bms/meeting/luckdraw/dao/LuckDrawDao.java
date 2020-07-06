package com.qifenqian.bms.meeting.luckdraw.dao;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.meeting.MeetingConstant;
import com.qifenqian.bms.meeting.helper.JPushService;
import com.qifenqian.bms.meeting.luckdraw.bean.Prize;
import com.qifenqian.bms.meeting.luckdraw.bean.PrizeWin;
import com.qifenqian.bms.meeting.luckdraw.bean.RedPacket;
import com.qifenqian.bms.meeting.luckdraw.mapper.LuckDrawMapper;
import com.qifenqian.bms.meeting.luckdraw.type.RedPacketType;
import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.mapper.PrizeMapper;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;
import com.qifenqian.bms.platform.utils.SequenceUtils;

/**
 * @project sevenpay-bms-web
 * @fileName LuckDrawDao.java
 * @author huiquan.ma
 * @date 2015年12月17日
 * @memo 
 */
@Repository
public class LuckDrawDao {
	
	private static Logger logger = LoggerFactory.getLogger(LuckDrawDao.class);
	
	@Autowired
	private LuckDrawMapper luckDrawMapper;
	@Autowired
	private PrizeMapper prizeMapper;
	@Autowired
	private JPushService jPushService;
	@Autowired
	private DictionaryUtils dictionaryUtils;
	
	@Transactional("oper")
	public void insertDraw(PrizeWin requestBean, Prize prize) {
		
		
		// 客户信息
		TdCustInfo cust = luckDrawMapper.selectCustSingleById(requestBean.getWinCustId());
		
		Date nowDatetime = new Date();
		
		// 中奖信息
		PrizeWin prizeWin = new PrizeWin();
		prizeWin.setPrizeId(requestBean.getPrizeId());
		prizeWin.setWinCustId(requestBean.getWinCustId());
		prizeWin.setWinCustPhone(cust.getMobile());
		prizeWin.setWinAmount(requestBean.getWinAmount());
		prizeWin.setEffectiveDeadline(DateUtils.addDays(nowDatetime, prize.getEffectiveDays()));
		prizeWin.setInstUser(MeetingConstant.DRAW_USER);
		prizeWin.setInstDate(DatetimeUtils.dateFormat(nowDatetime, "yyyy-MM-dd"));
		
		int winId = luckDrawMapper.insertPrizeWinSingle(prizeWin);
		
		// 红包信息
		String redPacketSn = SequenceUtils.getSequence_8("");
		
		RedPacket redPacket = new RedPacket();
		redPacket.setRedPacketSn(redPacketSn);
		redPacket.setReceiveCustId(requestBean.getWinCustId());
		redPacket.setGiveCustId(MeetingConstant.DRAW_USER);// TODO
		redPacket.setRedPacketType(RedPacketType.LOTTERY.name());
		redPacket.setRedPacketName(prize.getPrizeName());
		redPacket.setRedPacketAmt(requestBean.getWinAmount());
		redPacket.setRelationId(String.valueOf(winId));
		
		luckDrawMapper.insertRedPacketSingle(redPacket);
		
		// 如果已抽完，则更新奖项表为已完结
		if(prize.getWinNumber() + 1 == prize.getQuotaNumber()) {
			PrizeBean updateBean = new PrizeBean();
			updateBean.setPrizeId(prize.getPrizeId());
			updateBean.setStatus(PrizeStatus.OVER);
			prizeMapper.updatePrizeById(updateBean);
		}
		
		// 推送内容
		String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
							.replace("{prizeName}", prize.getPrizeName())
							.replace("{activityName}", prize.getActivityName());
		
		logger.info("--------title-------------{}", title);
		
		String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
							.replace("{prizeName}", prize.getPrizeName())
							.replace("{winAmount}", requestBean.getWinAmount().toPlainString())
							.replace("{redPacketId}", redPacketSn)
							.replace("{effectiveDays}", String.valueOf(prize.getEffectiveDays()))
							.replace("{winCustPhone}", cust.getMobile());
		
		logger.info("--------content-------------{}", content);
		// 发送消息推送
		jPushService.sendToSpecifiedUser(requestBean.getWinCustId(), title, content);
	}

}


