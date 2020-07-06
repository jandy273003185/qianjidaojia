package com.qifenqian.bms.accounting.aggregations.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultEqual;
import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultStatistic;
import com.qifenqian.bms.accounting.aggregations.bean.BaseChannel;
import com.qifenqian.bms.accounting.aggregations.bean.JhAggregationResultException;
import com.qifenqian.bms.accounting.aggregations.mapper.AggregationResultEqualMapper;
import com.qifenqian.bms.accounting.aggregations.mapper.AggregationResultExceptionMapper;
import com.qifenqian.bms.accounting.aggregations.mapper.BalAggregationResultExceptionMapper;
import com.qifenqian.bms.accounting.aggregations.mapper.BalAggregationResultStatisticMapper;
import com.qifenqian.bms.accounting.aggregations.mapper.JhAggregationResultExceptionMapper;
import com.qifenqian.bms.accounting.aggregations.service.AggregationResultExceptionService;
import com.qifenqian.bms.accounting.aggregations.service.AggregationResultStatisticService;
import com.qifenqian.bms.accounting.aggregations.service.BalAggregationResultExceptionService;
import com.qifenqian.bms.accounting.aggregations.service.JhAggregationResultExceptionService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.utils.DownLoadUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;



@Controller
@RequestMapping(AggregationPath.BASE)
public class AggregationResultController {

	private static Logger logger = LoggerFactory.getLogger(AggregationResultController.class);

	
	@Autowired
	private AggregationResultStatisticService aggregationResultStatisticService;
	@Autowired
	private BalAggregationResultStatisticMapper balAggregationResultStatisticMapper;
	
	@Autowired
	private BalAggregationResultExceptionService balAggregationResultExceptionService;
	@Autowired
	private BalAggregationResultExceptionMapper balAggregationResultExceptionMapper;
	
	@Autowired
	private JhAggregationResultExceptionService qfqAggregationResultExceptionService;
	@Autowired
	private JhAggregationResultExceptionMapper jhAggregationResultExceptionMapper;
	
	
	@Autowired
	private AggregationResultExceptionService aggregationResultExceptionService;
	@Autowired
	private AggregationResultExceptionMapper aggregationResultExceptionMapper;
	
	
	@Autowired
	private AggregationResultEqualMapper aggregationResultEqualMapper;
	
	
	
	/**
	 * 对账结果统计
	 */
	@RequestMapping(AggregationPath.LIST)
	public ModelAndView statisticsList(BalAggregationResultStatistic balAggregationResultStatistic){
		logger.info("中信对账结果表报表查询对象：{}", JSONObject.toJSONString(balAggregationResultStatistic, true));

		
		List<BalAggregationResultStatistic> list = aggregationResultStatisticService.selectList(balAggregationResultStatistic);
		List<BaseChannel> baseChannels = aggregationResultStatisticService.queryBalBaseChannel();
	
		ModelAndView mv = new ModelAndView(AggregationPath.BASE + AggregationPath.LIST);
		
		mv.addObject("statisticList", list);
		mv.addObject("balAggregationResultStatistic", balAggregationResultStatistic);
		mv.addObject("baseChannels", baseChannels);
		return mv;			
	}
	
	/**
	 * 对账结果统计报表导出
	 * @return 
	 */
	@RequestMapping(AggregationPath.EXPORT)
	public void exportExcel(BalAggregationResultStatistic requestBean, 
			HttpServletRequest request, HttpServletResponse response){
		logger.info("对账结果统计报表导出：{}", JSONObject.toJSONString(requestBean, true));
		
		String workDateDefault = DateFormatUtils.format(DateUtils.addDays(new Date(),-1), "yyyymmdd") ;
		
		if (StringUtils.isBlank(requestBean.getWorkDate())){
			requestBean.setWorkDate(workDateDefault);
		}
		
		try {
		
			List<BalAggregationResultStatistic> excels = balAggregationResultStatisticMapper.selectList(requestBean);
		
			String[] headers = { "编号","对账批次", "对账日期", "会计日期", "文件编号","渠道编号","数据方", "交易结果", 
					"统计类型", "币别", "总笔数", "总金额", "对账一致笔数", "对账一致总额", "对账存疑笔数", "对账存疑总额", 
					"对账差错笔数", "对账差错总额", "自身存疑笔数", "自身存疑总额", "状态", "返回更新时间","渠道中文名称","类型名称"};
			String fileName = DatetimeUtils.datetime()+"_对账结果统计报表_.xls";
			
			Map<String, String> fileInfo = ExportExcelUtils.exportExcel(excels,headers, fileName, "对账结果统计报表", request);
			
			DownLoadUtils.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出对账结果统计报表成功");

		} catch (Exception e) {
			logger.error("导出对账结果统计报表异常", e);
			e.printStackTrace();
		}			
	}
	
	
	

		/**
		 * 中信对账存疑表
		 * 
		 * @param exception
		 * @return
		 */
		@RequestMapping(AggregationPath.IMPEACH)
		public ModelAndView zxList(BalAggregationResultException balAggregationResultException) {
			logger.info("中信对账存疑表报表查询对象：{}", JSONObject.toJSONString(balAggregationResultException, true));
			ModelAndView mv = new ModelAndView(AggregationPath.BASE + AggregationPath.IMPEACH);
			List<BalAggregationResultException> zxList = balAggregationResultExceptionService.selectZxResultExceptionList(balAggregationResultException);
			mv.addObject("queryBean", balAggregationResultException);
			mv.addObject("zxList", zxList);
			return mv;
		}

		/**
		 * 导出中信对账存疑表
		 * 
		 * @param exception
		 * @param request
		 * @param response
		 */
		@RequestMapping(AggregationPath.EXPORTIMPEACH)
		public void exportZytExcel(BalAggregationResultException requestBean, HttpServletRequest request, HttpServletResponse response) {
			logger.info("导出中信存疑报表请求对象：{}",JSONObject.toJSONString(requestBean, true));

			try {
				List<BalAggregationResultException> excels = balAggregationResultExceptionMapper.selectZxResultExceptionList(requestBean);
				String headers[] = {"对账日期","异常编号","源数据编号","渠道编号","文件编号","序号/行号","清算编号","会计日期(数据日期)",
						"对账批次号","对账结果","对账处理备注","初始写入人",
						"写入日期：YYYY-MM-DD","初始时间","异常处理标记：挂账/抹账/销账","异常处理备注","异常处理人","异常处理时间" };
				String fileName = DatetimeUtils.datetime() + "_中信对账结果存疑表报表.xls";
				Map<String, String> fileInfo = ExportExcelUtils.exportExcel(excels, headers, fileName, "中信对账结果中信存疑表报表",request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出中信存疑报表成功");
			} catch (Exception e) {
				logger.error("导出中信存疑报表异常", e);
				throw new RuntimeException(e);
			}

		}
	
		
		/**
		 * 聚合存疑表
		 * @param qfqAggregationResultException
		 * @return
		 */
		@RequestMapping(AggregationPath.JHIMPEACH)
		public ModelAndView qfqList(JhAggregationResultException qfqAggregationResultException) {
			logger.info("中信对账存疑表报表查询对象：{}", JSONObject.toJSONString(qfqAggregationResultException, true));
			ModelAndView mv = new ModelAndView(AggregationPath.BASE + AggregationPath.JHIMPEACH);
			List<JhAggregationResultException> qfqList = qfqAggregationResultExceptionService.selectQfqResultExceptionList(qfqAggregationResultException);
			mv.addObject("queryBean", qfqAggregationResultException);
			mv.addObject("qfqList", qfqList);
			return mv;
		}
		
		/**
		 * 聚合存疑表导出
		 * @param requestBean
		 * @param request
		 * @param response
		 */
		@RequestMapping(AggregationPath.EXPORTJHIMPEACH)
		public void exportQfqImpeach(JhAggregationResultException requestBean, HttpServletRequest request, HttpServletResponse response) {
			logger.info("导出七分钱存疑查询对象：{}", JSONObject.toJSONString(requestBean, true));

			try {
				List<JhAggregationResultException> impeachList = jhAggregationResultExceptionMapper.selectJhResultExceptionList(requestBean);
				String[] headers = { "批次编号","异常编号","源数据编号","渠道编号","清算编号","会计日期(数据日期)",
						"对账批次号","对账结果","对账处理备注","初始写入人",
						"写入日期：YYYY-MM-DD","初始时间","异常处理标记：挂账/抹账/销账","异常处理备注","异常处理人","异常处理时间"  };
				String fileName = DatetimeUtils.datetime() + "_七分钱存疑数据.xls";
				Map<String, String> fileInfo = ExportExcelUtils.exportExcel(impeachList, headers, fileName, "七分钱存疑数据", request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出七分钱存疑成功");
			} catch (Exception e) {
				logger.error("导出七分钱存疑异常", e);
				e.printStackTrace();
			}

		}
		
		
		
		/**
		 * 中信差错报表
		 * @param queryBean
		 * @return
		 */
		@RequestMapping(AggregationPath.SLIPREPORT)
		public ModelAndView slipReport(BalAggregationResultException queryBean) {
			
			logger.info("导出中信对账差错报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
			
			ModelAndView mv = new ModelAndView(AggregationPath.BASE + AggregationPath.SLIPREPORT);
			List<BalAggregationResultException> errorList = aggregationResultExceptionService.selectErrorList(queryBean);
			mv.addObject("queryBean", queryBean);
			mv.addObject("errorList", errorList);
			return mv;
		}

		/**
		 * 导出中信对账差错报表
		 * 
		 * @param exception
		 * @param request
		 * @param response
		 */
		@RequestMapping(AggregationPath.EXPORTSLIP)
		public void exportSlipExcel(BalAggregationResultException queryBean, HttpServletRequest request,
				HttpServletResponse response) {
			
			logger.info("导出中信对账差错报表请求对象：{}", JSONObject.toJSONString(queryBean, true));

			try {
				List<BalAggregationResultException> excels = aggregationResultExceptionMapper.selectErrorList(queryBean);
				String headers[] = { "对账日期", "异常编号","源数据编号","渠道编号","文件编号","序号", "清算编号", "清算编号", "会计日期(数据日期)",
						"对账批次号","对账结果","对账处理备注","初始写入人",
						"写入日期：YYYY-MM-DD","初始时间","异常处理标记：挂账/抹账/销账","异常处理备注","异常处理人","异常处理时间"};
				String fileName = DatetimeUtils.datetime() + "_中信对账差错报表.xls";
				Map<String, String> fileInfo = ExportExcelUtils.exportExcel(excels, headers, fileName, "中信对账差错报表",
						request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出中信对账差错报表成功");
			} catch (Exception e) {
				logger.error("导出中信对账差错报表异常", e);
			}

		}

		
		
		/**
		 * 中信对账一致报表
		 * 
		 * @param resultEqual
		 * @return
		 */
		@RequestMapping(AggregationPath.FITREPORT)
		public ModelAndView fitReport(BalAggregationResultEqual queryBean) {

			logger.info("中信对账一致报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
			
			ModelAndView mv = new ModelAndView(AggregationPath.BASE + AggregationPath.FITREPORT);
			List<BalAggregationResultEqual> fitList = aggregationResultExceptionService.selectFitList(queryBean);
			mv.addObject("queryBean", queryBean);
			mv.addObject("fitList", fitList);
			return mv;
		}

		/**
		 * 导出中信对账一致报表
		 * 
		 * @param resultEqual
		 * @param request
		 * @param response
		 */
		@RequestMapping(AggregationPath.EXPORTFIT)
		public void exportFitExcel(BalAggregationResultEqual queryBean, HttpServletRequest request, HttpServletResponse response) {

			logger.info("导出中信对账一致报表请求对象：{}", JSONObject.toJSONString(queryBean, true));
			
			try {
				String headers[] = { "对账日期", "一致编号", "源数据编号", "渠道编号", "文件编号", "序号","清算编号","会计日期","对账批次号","对账结果","对账处理备注","初始写入人","写入日期","初始时间"};
				List<BalAggregationResultEqual> excels = aggregationResultEqualMapper.selectFitList(queryBean);
				String fileName = DatetimeUtils.datetime() + "_中信对账一致报表.xls";
				Map<String, String> fileInfo = ExportExcelUtils.exportExcel(excels, headers, fileName, "中信一致报表", request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出中信对账一致报表成功");
			} catch (Exception e) {
				logger.error("导出中信对账一致报表异常", e);
			}

		}
}
