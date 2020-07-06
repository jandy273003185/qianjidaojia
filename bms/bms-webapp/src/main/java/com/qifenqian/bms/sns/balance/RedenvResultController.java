package com.qifenqian.bms.sns.balance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.sns.balance.bean.ResultEqual;
import com.qifenqian.bms.sns.balance.bean.ResultFailure;
import com.qifenqian.bms.sns.balance.bean.ResultRedenvDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultSevenDoubt;
import com.qifenqian.bms.sns.balance.bean.ResultStatistic;
import com.qifenqian.bms.sns.balance.bean.ResultSummary;
import com.qifenqian.bms.sns.balance.dao.RedenvResultDao;
import com.qifenqian.bms.sns.balance.mapper.RedenvResultMapper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(RedenvResultPath.BASE)
public class RedenvResultController {

	private static Logger logger = LoggerFactory.getLogger(RedenvResultController.class);

	@Autowired
	private RedenvResultDao redenvResultDao;

	@Autowired
	private RedenvResultMapper redenvResultMapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 对账结果
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_STATISTIC)
	public ModelAndView selectResultStatistic(ResultStatistic queryBean) {
		logger.info("红包对账结果列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.RESULT_STATISTIC);
		List<ResultStatistic> resultStatisticList = redenvResultDao.selectResultStatistic(queryBean);
		mv.addObject("resultStatisticList", JSONObject.toJSON(resultStatisticList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 对账一致
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_EQUAL)
	public ModelAndView selectResultEqual(ResultEqual queryBean) {
		logger.info("红包对账一致列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.RESULT_EQUAL);
		List<ResultEqual> resultEqualList = redenvResultDao.selectResultEqual(queryBean);
		mv.addObject("resultEqualList", JSONObject.toJSON(resultEqualList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 对账差错
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_FAILURE)
	public ModelAndView selectResultFailure(ResultFailure queryBean) {
		logger.info("红包对账差错列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		List<ResultFailure> resultFailureList = redenvResultDao.selectResultFailure(queryBean);
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.RESULT_FAILURE);
		mv.addObject("resultFailureList", JSONObject.toJSON(resultFailureList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 红包存疑
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.REDENV_DOUBT)
	public ModelAndView selectResultRedenvDoubt(ResultRedenvDoubt queryBean) {
		logger.info("红包存疑列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.REDENV_DOUBT);
		List<ResultRedenvDoubt> redenvDoubtList = redenvResultDao.selectResultRedenvDoubt(queryBean);
		mv.addObject("redenvDoubtList", JSONObject.toJSON(redenvDoubtList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 七分钱存疑
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.SEVEN_DOUBT)
	public ModelAndView selectResultSevenDoubt(ResultSevenDoubt queryBean) {
		logger.info("红包七分钱存疑列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.SEVEN_DOUBT);
		List<ResultSevenDoubt> sevenDoubtList = redenvResultDao.selectResultSevenDoubt(queryBean);
		mv.addObject("sevenDoubtList", JSONObject.toJSON(sevenDoubtList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 红包汇总
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_SUMMARY)
	public ModelAndView selectResultSummary(ResultSummary queryBean) {
		logger.info("红包汇总列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RedenvResultPath.BASE + RedenvResultPath.RESULT_SUMMARY);
		List<ResultSummary> resultSummaryList = redenvResultDao.selectResultSummary(queryBean);
		mv.addObject("resultSummaryList", JSONObject.toJSON(resultSummaryList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 对账结果报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_STATISTIC_EXPORT)
	public void exportResultStatistic(ResultStatistic queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包对账结果列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		try {
			List<ResultStatistic> resultStatisticList = redenvResultMapper.selectResultStatistic(queryBean);
			String headers[] = { "对账批次", "对账日期", "会计日期", "系统", "交易结果", "交易类型", "总笔数", "总金额", "自身存疑笔数", "自身存疑金额", "存疑笔数", "存疑金额", "差错笔数", "差错金额",
					"一致笔数", "一致金额" };
			String fileName = DatetimeUtils.dateSecond() + "_对账结果报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(resultStatisticList, headers, fileName, "对账结果报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出对账结果报表成功");
		} catch (Exception e) {
			logger.error("导出对账结果报表异常", e);
		}
	}

	/**
	 * 对账一致报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_EQUAL_EXPORT)
	public void exportResultEqual(ResultEqual queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包对账一致列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		try {
			List<ResultEqual> resultEqualList = redenvResultMapper.selectResultEqual(queryBean);
			String headers[] = { "对账批次", "对账日期", "清算编号", "会计日期", "客户编号", "交易类型", "交易金额", "交易状态", "对账备注" };
			String fileName = DatetimeUtils.dateSecond() + "_对账一致报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(resultEqualList, headers, fileName, "对账一致报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出对账一致报表成功");
		} catch (Exception e) {
			logger.error("导出对账一致报表异常", e);
		}
	}

	/**
	 * 对账差错报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_FAILURE_EXPORT)
	public void exportResultFailure(ResultFailure queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包对账差错列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		try {
			List<ResultFailure> resultFailureList = redenvResultMapper.selectResultFailure(queryBean);
			String headers[] = { "对账批次", "对账日期", "清算编号", "会计日期", "客户编号", "交易类型", "交易金额", "交易状态", "存疑信息", "对账备注" };
			String fileName = DatetimeUtils.dateSecond() + "_对账差错报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(resultFailureList, headers, fileName, "对账差错报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出对账差错报表成功");
		} catch (Exception e) {
			logger.error("导出对账差错报表异常", e);
		}
	}

	/**
	 * 红包存疑报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.REDENV_DOUBT_EXPORT)
	public void exportResultRedenvDoubt(ResultRedenvDoubt queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包存疑列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		try {
			List<ResultRedenvDoubt> redenvDoubtList = redenvResultMapper.selectResultRedenvDoubt(queryBean);
			String headers[] = { "对账批次", "对账日期", "清算编号", "会计日期", "客户编号", "交易类型", "交易金额", "交易状态", "存疑信息", "对账备注" };
			String fileName = DatetimeUtils.dateSecond() + "_红包存疑报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(redenvDoubtList, headers, fileName, "红包存疑报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出红包存疑报表成功");
		} catch (Exception e) {
			logger.error("导出红包存疑报表异常", e);
		}
	}

	/**
	 * 七分钱存疑报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.SEVEN_DOUBT_EXPORT)
	public void exportResultSevenDoubt(ResultSevenDoubt queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包七分钱存疑列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));

		try {
			List<ResultSevenDoubt> sevenDoubtList = redenvResultMapper.selectResultSevenDoubt(queryBean);
			String headers[] = { "对账批次", "对账日期", "清算编号", "会计日期", "客户编号", "交易类型", "交易金额", "交易状态", "对账备注" };
			String fileName = DatetimeUtils.dateSecond() + "_七分钱存疑报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(sevenDoubtList, headers, fileName, "七分钱存疑报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出七分钱存疑报表成功");
		} catch (Exception e) {
			logger.error("导出七分钱存疑报表异常", e);
		}
	}

	/**
	 * 红包汇总报表导出
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(RedenvResultPath.RESULT_SUMMARY_EXPORT)
	public void exportResultummary(ResultSummary queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出红包汇总列表查询请求对象：  [{}]", JSONObject.toJSONString(queryBean, true));
		try {
			List<ResultSummary> resultSummaryList = redenvResultMapper.selectResultSummary(queryBean);
			String headers[] = { "批次编号", "对账日期", "会计日期", "红包日期", "红包流水号", "红包个数", "总金额", "出账状态", "入账成功个数", "入账成功金额", "入账失败个数", "入账失败金额", "未入账个数",
					"未入账金额", "退款金额", "退款状态", "对账状态", "对账备注", "处理状态", "处理人", "处理时间", "处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_红包汇总报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(resultSummaryList, headers, fileName, "红包汇总报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出红包汇总报表成功");
		} catch (Exception e) {
			logger.error("导出红包汇总报表异常", e);
		}
	}

	/**
	 * 退货审批
	 * 
	 * @param refundBill
	 * @return
	 */
	@RequestMapping(RedenvResultPath.DEAL_REDENV)
	@ResponseBody
	public String dealRedenv(ResultSummary updateBean) {
		logger.info("红包异常处理对象 [{}]", JSONObject.toJSONString(updateBean, true));
		JSONObject object = new JSONObject();

		if (null == updateBean) {
			throw new IllegalArgumentException("处理对象为空");
		}
		if (StringUtils.isBlank(updateBean.getRedenvId())) {
			throw new IllegalArgumentException("七分钱订单号不可为空");
		}
		if (StringUtils.isBlank(updateBean.getDealStatus())) {
			throw new IllegalArgumentException("七分钱订单号不可为空");
		}

		String dealUser = String.valueOf(WebUtils.getUserInfo().getUserId());

		try {
			updateBean.setDealUser(dealUser);
			int i = redenvResultMapper.updateResultSummary(updateBean);
			if (i == 1) {
				object.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
}
