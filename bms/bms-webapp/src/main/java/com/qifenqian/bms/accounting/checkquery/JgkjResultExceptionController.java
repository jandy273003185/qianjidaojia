package com.qifenqian.bms.accounting.checkquery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.checkquery.bean.JgkjResultException;
import com.qifenqian.bms.accounting.checkquery.mapper.JgkjResultExceptionMapper;
import com.qifenqian.bms.accounting.checkquery.service.JgkjResultExceptionService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 交广科技对账结果交广科技存疑表报表
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(JgkjResultExceptionPath.BASE)
public class JgkjResultExceptionController {
	private static Logger logger = LoggerFactory.getLogger(JgkjResultExceptionController.class);

	@Autowired
	private JgkjResultExceptionService service;

	@Autowired
	private JgkjResultExceptionMapper mapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 交广科技对账存疑表报表
	 * 
	 * @param exception
	 * @return
	 */
	@RequestMapping(JgkjResultExceptionPath.JGKJLIST)
	public ModelAndView zrtList(JgkjResultException requestBean) {
		logger.info("交广科技对账存疑表报表查询对象：{}", JSONObject.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(JgkjResultExceptionPath.BASE + JgkjResultExceptionPath.JGKJLIST);
		List<JgkjResultException> zytList = service.selectZytResultExceptionList(requestBean);
		mv.addObject("queryBean", requestBean);
		mv.addObject("zytList", zytList);
		return mv;
	}

	/**
	 * 导出交广科技对账结果交广科技存疑表报表
	 * 
	 * @param exception
	 * @param request
	 * @param response
	 */
	@RequestMapping(JgkjResultExceptionPath.JGKjEXPORT)
	public void exportZytExcel(JgkjResultException requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出交广科技存疑报表请求对象：{}",JSONObject.toJSONString(requestBean, true));

		try {
			List<JgkjResultException> excels = mapper.selectZytResultExceptionList(requestBean);
			String headers[] = { "对账日期", "渠道名称", "文件编号", "序号/行号", "会计日期(数据日期)", "批次编号", "七分钱流水号", "交广科技流水号", "主账号",
					"交易传输时间MMDDhhmmss", "交易返回时间MMDDhhmmss", "渠道交易金额", "交易类型：对应4位接口编号", "交易状态：00:成功 99:失败", "预留字段",
					"写入日期", "写入时间", "对账结果", "对账处理时间", "渠道差错类型", "异常处理标记：挂账/抹账/销账", "异常处理人", "异常处理时间", "异常处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_交广科技对账结果交广科技存疑表报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交广科技对账结果交广科技存疑表报表",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技存疑报表成功");
		} catch (Exception e) {
			logger.error("导出交广科技存疑报表异常", e);
			throw new RuntimeException(e);
		}

	}
}
