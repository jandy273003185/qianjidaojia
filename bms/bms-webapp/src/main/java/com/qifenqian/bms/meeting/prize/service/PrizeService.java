package com.qifenqian.bms.meeting.prize.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.meeting.MeetingConstant;
import com.qifenqian.bms.meeting.helper.JPushService;
import com.qifenqian.bms.meeting.helper.pushrelation.bean.PushRelation;
import com.qifenqian.bms.meeting.helper.pushrelation.mapper.PushRelationMapper;
import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.dao.PrizeDao;
import com.qifenqian.bms.meeting.prize.mapper.PrizeMapper;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;
import com.qifenqian.bms.meeting.prize.type.PrizeType;
import com.qifenqian.bms.meeting.prizewin.bean.PrizeWinBean;
import com.qifenqian.bms.meeting.prizewin.mapper.PrizeWinMapper;
import com.qifenqian.bms.meeting.prizewin.type.PrizeWinStatus;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class PrizeService {

	private static Logger logger = LoggerFactory.getLogger(PrizeService.class);

	@Autowired
	private PrizeDao prizeDao;

	@Autowired
	private PrizeMapper prizeMapper;

	@Autowired
	private PushRelationMapper pushRelationMapper;

	@Autowired
	private DictionaryUtils dictionaryUtils;
	@Autowired
	private PrizeWinMapper prizeWinMapper;
	@Autowired
	private JPushService jPushService;

	public List<PrizeBean> selectPrizeBeanList(PrizeBean queryBean) {
		return prizeDao.selectPrizeBeanList(queryBean);
	}

	public PrizeBean selectPrizeSingleById(int prizeId) {
		return prizeMapper.selectPrizeSingleById(prizeId);
	}

	@Transactional("oper")
	public void insertPrizeBean(PrizeBean insertBean) {
		// 写入奖项
		insertBean.setInstUser(null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId());
		prizeMapper.insertPrizeSingle(insertBean);

		// 写入奖项拆分表
		List<PrizeWinBean> winList = null;

		if (PrizeType.LUCK_DRAW == insertBean.getPrizeType()) {
			switch (insertBean.getOverallControlType()) {
			case AMOUNT:
				// 金额控制 逐渐递减直到不能再次分配
				switch (insertBean.getAmountType()) {
				case FIXED:
					// 固定
					winList = this.prizeSplitByAmountFixed(insertBean);
					break;
				case RANDOM:
					// 随机
					winList = this.prizeSplitByAmountRandom(insertBean);
					break;
				default:
					throw new UnsupportedOperationException("暂不支持的金额类型：" + insertBean.getAmountType());
				}
				break;
			case NUMBER:
				// 数量控制
				switch (insertBean.getAmountType()) {
				case FIXED:
					// 固定金额
					winList = this.prizeSplitByNumberFixed(insertBean);
					break;
				case RANDOM:
					// 随机金额
					winList = this.prizeSplitByNumberRandom(insertBean);
					break;
				default:
					throw new UnsupportedOperationException("暂不支持的金额类型：" + insertBean.getAmountType());
				}
				break;
			case JOINT:
				// 联合控制
				switch (insertBean.getAmountType()) {
				case FIXED:
					// 固定金额
					winList = this.prizeSplitByJointFixed(insertBean);
					break;
				case RANDOM:
					// 随机金额
					winList = this.prizeSplitByJointRandom(insertBean);
					break;
				default:
					throw new UnsupportedOperationException("暂不支持的金额类型：" + insertBean.getAmountType());
				}
				break;
			default:
				throw new UnsupportedOperationException("暂不支持的控制类型：" + insertBean.getOverallControlType());
			}
			if (null != winList) {
				prizeWinMapper.insertList(winList);
			}
		} else if (PrizeType.FULL_AWARD == insertBean.getPrizeType()) {
			switch (insertBean.getOverallControlType()) {
			case AMOUNT:
				break;
			case NUMBER:
				break;
			default:
				throw new UnsupportedOperationException("暂不支持的控制类型：" + insertBean.getOverallControlType());
			}
		}

	}

	public int updatePrizeBean(PrizeBean updateBean) {
		updateBean.setLupdUser(WebUtils.getUserInfo().getUserId());
		return prizeMapper.updatePrizeById(updateBean);
	}

	public void startPrize(int prizeId) {
		// 更新状态
		PrizeBean updateBean = new PrizeBean();
		updateBean.setPrizeId(prizeId);
		updateBean.setStatus(PrizeStatus.DRAW_IN);
		prizeMapper.updatePrizeById(updateBean);

		// 查询
		PrizeBean prize = prizeMapper.selectPrizeSingleById(prizeId);

		// 推送内容
		String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
				.replace("{prizeName}", prize.getPrizeName()).replace("{activityName}", prize.getActivityName());

		logger.info("--------title-------------{}", title);

		String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
				.replace("{activityName}", prize.getActivityName()).replace("{prizeName}", prize.getPrizeName())
				.replace("{prizeId}", String.valueOf(prize.getPrizeId()));

		logger.info("--------content-------------{}", content);

		jPushService.sendDepart(title, content);
	}

	public void resendPrize(int prizeId) {
		// 查询
		PrizeBean prize = prizeMapper.selectPrizeSingleById(prizeId);

		// 推送内容
		String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
				.replace("{prizeName}", prize.getPrizeName()).replace("{activityName}", prize.getActivityName());

		logger.info("--------title-------------{}", title);

		String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
				.replace("{activityName}", prize.getActivityName()).replace("{prizeName}", prize.getPrizeName())
				.replace("{prizeId}", String.valueOf(prize.getPrizeId()));

		logger.info("--------content-------------{}", content);

		jPushService.sendDepart(title, content);
	}

	/**
	 * 总额控制、固定金额
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByAmountFixed(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 总额度
		BigDecimal quotaAmountTemp = prizeBean.getQuotaAmount();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		// 当前用户
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());

		while (quotaAmountTemp.compareTo(BigDecimal.ZERO) > 0) {

			BigDecimal winAmount = prizeBean.getFixedAmount().compareTo(quotaAmountTemp) > 0 ? quotaAmountTemp
					: prizeBean.getFixedAmount();

			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());
			winTemp.setWinAmount(winAmount);
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);

			winList.add(winTemp);
			// 递减
			quotaAmountTemp = quotaAmountTemp.subtract(winAmount);
		}

		return winList;
	}

	/**
	 * 总额控制、随机金额
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByAmountRandom(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 总额度
		BigDecimal quotaAmountTemp = prizeBean.getQuotaAmount();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		/** 三目运算符：当前用户 **/
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		/** 精度控制为0 Math.pow() 计算次方 **/
		double scalePow = Math.pow(10,
				Double.parseDouble(dictionaryUtils.getDataValueByPath("meeting.redPacket.scale")));
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());

		while (quotaAmountTemp.compareTo(BigDecimal.ZERO) > 0) {
			/** Math.floor(x) 小于参数x的最大整数 **/
			/** Math.random()是令系统随机选取大于等于 0.0 且小于 1.0 的伪随机值 **/
			BigDecimal randomAmount = new BigDecimal(prizeBean.getRandomAmountMin().doubleValue()
					+ Math.floor(Math.random()
							* prizeBean.getRandomAmountMax().subtract(prizeBean.getRandomAmountMin()).doubleValue()
							* scalePow) / scalePow);
			BigDecimal winAmount = randomAmount.compareTo(quotaAmountTemp) > 0 ? quotaAmountTemp : randomAmount;

			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());
			winTemp.setWinAmount(winAmount);
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);

			winList.add(winTemp);
			// 递减
			quotaAmountTemp = quotaAmountTemp.subtract(winAmount);
		}

		return winList;
	}

	/**
	 * 数量控制、固定金额
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByNumberFixed(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		// 当前用户
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());

		for (int i = 0; i < prizeBean.getQuotaNumber(); i++) {
			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());
			winTemp.setWinAmount(prizeBean.getFixedAmount());
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);

			winList.add(winTemp);
		}

		return winList;
	}

	/**
	 * 数量控制、随机金额
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByNumberRandom(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		// 当前用户
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		// 精度控制
		double scalePow = Math.pow(10,
				Double.parseDouble(dictionaryUtils.getDataValueByPath("meeting.redPacket.scale")));
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());

		for (int i = 0; i < prizeBean.getQuotaNumber(); i++) {
			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());

			BigDecimal winAmount = new BigDecimal(prizeBean.getRandomAmountMin().doubleValue()
					+ Math.floor(Math.random()
							* prizeBean.getRandomAmountMax().subtract(prizeBean.getRandomAmountMin()).doubleValue()
							* scalePow) / scalePow);

			winTemp.setWinAmount(winAmount);
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);

			winList.add(winTemp);
		}

		return winList;
	}

	/**
	 * 数量额度联合控制、固定金额
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByJointFixed(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		// 当前用户
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		// 总额度
		BigDecimal quotaAmountTemp = prizeBean.getQuotaAmount();
		// 计算平均金额 (无法平均时四舍六入)
		BigDecimal averageAmount = prizeBean.getQuotaAmount().divide(new BigDecimal(prizeBean.getQuotaNumber()),
				Integer.parseInt(dictionaryUtils.getDataValueByPath("meeting.redPacket.scale")),
				BigDecimal.ROUND_HALF_EVEN);
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());

		for (int i = 0; i < prizeBean.getQuotaNumber(); i++) {
			BigDecimal winAmount = averageAmount.compareTo(quotaAmountTemp) > 0 ? quotaAmountTemp : averageAmount;
			// 若是最后一个则不再减
			if (i == prizeBean.getQuotaNumber() - 1) {
				winAmount = quotaAmountTemp;
			}
			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());
			winTemp.setWinAmount(winAmount);
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);

			winList.add(winTemp);

			// 递减
			quotaAmountTemp = quotaAmountTemp.subtract(winAmount);
			if (quotaAmountTemp.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}
		}

		return winList;
	}

	/**
	 * 数量额度联合控制、随机金额 每次随机（最小精度， 剩下的平均值*2）
	 * 
	 * @param prizeBean
	 * @return
	 */
	private List<PrizeWinBean> prizeSplitByJointRandom(PrizeBean prizeBean) {

		// 数据准备
		PrizeWinBean winTemp = null;
		List<PrizeWinBean> winList = new ArrayList<PrizeWinBean>();
		// 时间
		Date now = new Date();
		String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
		// 当前用户
		int userId = null == WebUtils.getUserInfo() ? 0 : WebUtils.getUserInfo().getUserId();
		// 总额度
		BigDecimal quotaAmountTemp = prizeBean.getQuotaAmount();
		// 精度控制
		int scale = Integer.parseInt(dictionaryUtils.getDataValueByPath("meeting.redPacket.scale"));
		double scalePow = Math.pow(10, scale);
		// 失效时间
		Date effectiveDeadline = DateUtils.addHours(now, prizeBean.getEffectiveHours());
		BigDecimal amountMin = new BigDecimal("1");
		BigDecimal winAmount = new BigDecimal("0");
		for (int i = 0; i < prizeBean.getQuotaNumber() - 1; i++) {
			int j = i + 1;
			int safeMoney = (quotaAmountTemp.intValue() - (prizeBean.getQuotaNumber() - j) * amountMin.intValue())
					/ (prizeBean.getQuotaNumber() - j);
			if (safeMoney < 1) {
				winAmount = amountMin;
			} else {
				winAmount = new BigDecimal(Math.floor((Math.random() * (safeMoney * scalePow - amountMin.intValue()
						* scalePow))
						/ scalePow)).add(amountMin);
			}
			quotaAmountTemp = quotaAmountTemp.subtract(winAmount);
			winTemp = new PrizeWinBean();
			winTemp.setPrizeId(prizeBean.getPrizeId());
			winTemp.setWinAmount(winAmount);
			winTemp.setWinMark(null);
			winTemp.setEffectiveDeadline(effectiveDeadline);
			winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
			winTemp.setInstUser(userId);
			winTemp.setInstDate(nowDate);
			winList.add(winTemp);
			if (j == prizeBean.getQuotaNumber() - 1) {
				winAmount = quotaAmountTemp;
				winTemp = new PrizeWinBean();
				winTemp.setPrizeId(prizeBean.getPrizeId());
				winTemp.setWinAmount(winAmount);
				winTemp.setWinMark(null);
				winTemp.setEffectiveDeadline(effectiveDeadline);
				winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
				winTemp.setInstUser(userId);
				winTemp.setInstDate(nowDate);
				winList.add(winTemp);
			}

		}

		/*
		 * for (int i = 0; i < prizeBean.getQuotaNumber(); i++) {
		 *//** 随机金额 随机的最小值为 最小精度 **/
		/*
		 * BigDecimal randomAmount = new BigDecimal(Math.pow(10, -scale) +
		 * Math.floor(
		 *//** 要总额减去 还剩下人数的最小值 **/
		/*
		 * Math.random() (quotaAmountTemp.doubleValue() /
		 * (prizeBean.getQuotaNumber() - i) * 2 - Math.pow(10, -scale) *
		 * (prizeBean.getQuotaNumber() - 1 - i)) * scalePow) / scalePow);
		 * 
		 * // 获取金额 BigDecimal winAmount =
		 * randomAmount.compareTo(quotaAmountTemp) > 0 ? quotaAmountTemp :
		 * randomAmount; // 若是最后一个则不再减 if (i == prizeBean.getQuotaNumber() - 1)
		 * { winAmount = quotaAmountTemp; }
		 * 
		 * 
		 * winTemp = new PrizeWinBean();
		 * winTemp.setPrizeId(prizeBean.getPrizeId());
		 * winTemp.setWinAmount(winAmount); winTemp.setWinMark(null);
		 * winTemp.setEffectiveDeadline(effectiveDeadline);
		 * winTemp.setStatus(PrizeWinStatus.WAIT_RECEIVE);
		 * winTemp.setInstUser(userId); winTemp.setInstDate(nowDate);
		 * 
		 * winList.add(winTemp);
		 * 
		 * // 递减 quotaAmountTemp = quotaAmountTemp.subtract(winAmount); if
		 * (quotaAmountTemp.compareTo(BigDecimal.ZERO) <= 0) { break; } }
		 */

		return winList;
	}

	public void currentStartPrize(int prizeId) {
		// 更新状态
		PrizeBean updateBean = new PrizeBean();
		updateBean.setPrizeId(prizeId);
		updateBean.setStatus(PrizeStatus.DRAW_IN);
		prizeMapper.updatePrizeById(updateBean);

		// 查询
		PrizeBean prize = prizeMapper.selectPrizeSingleById(prizeId);

		// 推送内容
		String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
				.replace("{prizeName}", prize.getPrizeName()).replace("{activityName}", prize.getActivityName());

		logger.info("--------title-------------{}", title);

		String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
				.replace("{activityName}", prize.getActivityName()).replace("{prizeName}", prize.getPrizeName())
				.replace("{prizeId}", String.valueOf(prize.getPrizeId()));

		logger.info("--------content-------------{}", content);

		List<PushRelation> relationList = pushRelationMapper.selectAllRelation();
		logger.info("查询注册用户数量：{}", relationList.size());
		if (relationList.size() > 0) {
			for (PushRelation queryBean : relationList) {
				jPushService.sendToSpecifiedUser(queryBean.getCustId(), title, content);
			}
		}
	}

	public void currentResendPrize(int prizeId) {
		// 查询
		PrizeBean prize = prizeMapper.selectPrizeSingleById(prizeId);

		// 推送内容
		String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
				.replace("{prizeName}", prize.getPrizeName()).replace("{activityName}", prize.getActivityName());

		logger.info("--------title-------------{}", title);

		String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
				.replace("{activityName}", prize.getActivityName()).replace("{prizeName}", prize.getPrizeName())
				.replace("{prizeId}", String.valueOf(prize.getPrizeId()));

		logger.info("--------content-------------{}", content);

		List<PushRelation> relationList = pushRelationMapper.selectAllRelation();
		logger.info("查询注册用户数量：{}", relationList.size());

		if (relationList.size() > 0) {
			for (PushRelation queryBean : relationList) {
				jPushService.sendToSpecifiedUser(queryBean.getCustId(), title, content);
			}
		}

	}

}
