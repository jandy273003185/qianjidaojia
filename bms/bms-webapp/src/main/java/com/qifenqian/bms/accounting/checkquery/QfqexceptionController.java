package com.qifenqian.bms.accounting.checkquery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.accounting.checkquery.bean.Qfqexception;
import com.qifenqian.bms.accounting.checkquery.mapper.QfqexceptionMapper;
import com.qifenqian.bms.accounting.checkquery.service.QfqexceptionService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 交广科技对账七分钱存疑报表
 * 
 * @author shen
 * 
 */
@Repository
@RequestMapping(QfqexceptionPath.BASE)
public class QfqexceptionController {

	private static Logger logger = LoggerFactory.getLogger(QfqexceptionController.class);
	@Autowired
	private QfqexceptionService service;

	@Autowired
	private QfqexceptionMapper mapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 交广科技对账七分钱存疑报表
	 * 
	 * @param exception
	 * @return
	 */
	@RequestMapping(QfqexceptionPath.QFQLIST)
	public ModelAndView zrtList(Qfqexception queryBean) {

		ModelAndView mv = new ModelAndView(QfqexceptionPath.BASE + QfqexceptionPath.QFQLIST);
		List<Qfqexception> qfqList = service.selectQfqResultExceptionList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("qfqList", qfqList);
		return mv;
	}

	/**
	 * 导出交广科技对账七分钱存疑报表
	 * 
	 * @param exception
	 * @param request
	 * @param response
	 */
	@RequestMapping(QfqexceptionPath.QFQEXPORT)
	public void exportQfqExcel(Qfqexception exception, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出交广科技对账七分钱存疑报表");

		try {
			List<Qfqexception> excels = mapper.selectQfqResultExceptionList(exception);
			String headers[] = { "对账日期", "清算流水号", "对账批次号", "七分钱流水号", "交易类型", "交广科技交易代码", "交广科技卡号", "币别", "平台交易金额",
					"会计日期", "交易日期", "交易时间", "状态", "交广科技返回日期", "交广科技返回时间", "交广科技平台流水号", "交广科技返回交易结果代码",
					"交广科技返回描述", "写入日期", "写入时间", "对账结果", "对账处理时间", "平台差错类型",
					"异常处理标记：挂账/抹账/销账", "异常处理人", "异常处理时间", "异常处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_交广科技对账七分钱存疑报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交广科技对账七分钱存疑报表",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交广科技对账七分钱存疑报表成功");
		} catch (Exception e) {
			logger.error("导出交广科技对账七分钱存疑报表异常", e);
			throw new RuntimeException(e);
		}

	}
}
