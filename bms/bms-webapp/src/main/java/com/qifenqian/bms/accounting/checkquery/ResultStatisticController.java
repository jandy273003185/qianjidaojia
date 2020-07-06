package com.qifenqian.bms.accounting.checkquery;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.checkquery.bean.BalResultStatistic;
import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultEqual;
import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultException;
import com.qifenqian.bms.accounting.checkquery.mapper.BalResultStatisticMapper;
import com.qifenqian.bms.accounting.checkquery.mapper.BalSevenResultEqualMapper;
import com.qifenqian.bms.accounting.checkquery.mapper.BalSevenResultExceptionMapper;
import com.qifenqian.bms.accounting.checkquery.service.BalResultStatisticService;
import com.qifenqian.bms.accounting.checkquery.service.BalSevenResultExceptionService;
import com.qifenqian.bms.accounting.checkquery.type.ChannelId;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 交广科技对账结果
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(ResultStatisticPath.BASE)
public class ResultStatisticController {
	private static Logger logger = LoggerFactory.getLogger(ResultStatisticController.class);

	@Autowired
	private BalResultStatisticService balResultStatisticService;

	@Autowired
	private BalSevenResultExceptionService resultExceptionService;

	@Autowired
	private TradeBillService tradeBillService;

	@Resource
	private BalSevenResultEqualMapper equalMapper;

	@Resource
	private BalSevenResultExceptionMapper mapper;

	@Autowired
	private BalResultStatisticMapper balResultStatisticMapper;

	/**
	 * 交广科技对账结果统计报表
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(ResultStatisticPath.LIST)
	public ModelAndView list(BalResultStatistic requestBean) {

		logger.info("交广科技对账结果统计报表查询对象:{}", JSONObject.toJSONString(requestBean, true));

		requestBean.setChannelId(ChannelId.JGKJ.name());
		ModelAndView mv = new ModelAndView(ResultStatisticPath.BASE + ResultStatisticPath.LIST);
		mv.addObject("selectBean", requestBean);
		mv.addObject("statisticList", balResultStatisticService.selectListByPage(requestBean));
		return mv;
	}

	/**
	 * 导出交广科技对账结果统计报表
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(ResultStatisticPath.EXPORT)
	public void exportExcel(BalResultStatistic requestBean, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交广科技对账结果统计报表请求对象：{}", JSONObject.toJSONString(requestBean, true));

		requestBean.setChannelId(ChannelId.JGKJ.name());
		try {
			List<BalResultStatistic> excels = balResultStatisticMapper.selectList(requestBean);

			String[] headers = { "对账批次", "对账日期", "会计日期", "渠道", "文件编号", "交易结果", "业务类型", "数据平台", "总笔数", "总金额", "对账一致笔数",
					"对账一致总额", "对账存疑笔数", "对账存疑总额", "对账差错笔数", "对账差错总额", "自身异常笔数", "自身异常总额", "状态", "备注" };
			String fileName = "交广科技对账结果统计报表_"
					+ (StringUtils.isBlank(requestBean.getChannelIdDesc()) ? "" : requestBean.getChannelIdDesc() + "_")
					+ (StringUtils.isBlank(requestBean.getWorkDateMin()) ? "" : requestBean.getWorkDateMin() + "-")
					+ (StringUtils.isBlank(requestBean.getWorkDateMax()) ? DatetimeUtils.shortDate() + "_"
							: requestBean.getWorkDateMax() + "_") + DatetimeUtils.dateSecond() + ".xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交广科技对账结果统计报表",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技对账结果统计报表成功");
		} catch (Exception e) {
			logger.error("导出交广科技对账结果统计报表异常", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 交广科技对账差错报表
	 * 
	 * @param exception
	 * @return
	 */
	@RequestMapping(ResultStatisticPath.SLIPREPORT)
	public ModelAndView slipReport(BalSevenResultException queryBean) {
		
		logger.info("导出交广科技对账差错报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
		
		ModelAndView mv = new ModelAndView(ResultStatisticPath.BASE + ResultStatisticPath.SLIPREPORT);
		List<BalSevenResultException> errorList = resultExceptionService.selectErrorList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("errorList", errorList);
		return mv;
	}

	/**
	 * 导出交广科技对账差错报表
	 * 
	 * @param exception
	 * @param request
	 * @param response
	 */
	@RequestMapping(ResultStatisticPath.EXPORTSLIP)
	public void exportSlipExcel(BalSevenResultException queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		logger.info("导出交广科技对账差错报表请求对象：{}", JSONObject.toJSONString(queryBean, true));

		try {
			List<BalSevenResultException> excels = mapper.selectErrorList(queryBean);
			String headers[] = { "对账日期", "七分钱会计日期", "渠道名称", "清算编号", "交广科技编号", "交易类型", "支付平台交易金额",
					"交广科技交易金额","交易发送时间","交易返回时间", "差错信息"};
			String fileName = DatetimeUtils.dateSecond() + "_交广科技对账差错报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交广科技对账差错报表",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技对账差错报表成功");
		} catch (Exception e) {
			logger.error("导出交广科技对账差错报表异常", e);
		}

	}

	/**
	 * 交广科技对账一致报表
	 * 
	 * @param resultEqual
	 * @return
	 */
	@RequestMapping(ResultStatisticPath.FITREPORT)
	public ModelAndView fitReport(BalSevenResultEqual queryBean) {

		logger.info("交广科技对账一致报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
		
		ModelAndView mv = new ModelAndView(ResultStatisticPath.BASE + ResultStatisticPath.FITREPORT);
		List<BalSevenResultEqual> fitList = resultExceptionService.selectFitList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("fitList", fitList);
		return mv;
	}

	/**
	 * 导出交广科技对账一致报表
	 * 
	 * @param resultEqual
	 * @param request
	 * @param response
	 */
	@RequestMapping(ResultStatisticPath.EXPORTFIT)
	public void exportFitExcel(BalSevenResultEqual queryBean, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交广科技对账一致报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
		
		try {
			String headers[] = { "对账日期","会计日期", "渠道交易时间", " 支付平台交易时间", "渠道名称", "渠道订单号", "七分钱订单号", "渠道交易金额", "支付平台交易金额" };
			List<BalSevenResultEqual> excels = equalMapper.selectFitList(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_交广科技对账一致报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交广科技一致报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技对账一致报表成功");
		} catch (Exception e) {
			logger.error("导出交广科技对账一致报表异常", e);
		}

	}

}
