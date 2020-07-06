package com.qifenqian.bms.accounting.balunionpayuniondatasource;

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
import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpaySevenDataSource;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.mapper.BalUnionpaySevenDataSourceMapper;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.service.BalUnionpaySevenDataSourceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 七分钱对账源数据
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(BalUnionpaySevenDataSourcePath.BASE)
public class BalUnionpaySevenDataSourceController {
	private static Logger logger = LoggerFactory.getLogger(BalUnionpaySevenDataSourceController.class);
	@Autowired
	private BalUnionpaySevenDataSourceService service;
	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private BalUnionpaySevenDataSourceMapper mapper;

	/**
	 * 七分钱对账源数据
	 * 
	 * @param request
	 * @param source
	 * @return
	 */
	@RequestMapping(BalUnionpaySevenDataSourcePath.LISTUNIONSEVEN)
	public ModelAndView sevenDataSourceList(HttpServletRequest request, BalUnionpaySevenDataSource queryBean) {

		logger.info("七分钱对账源数据查询对象：{}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BalUnionpaySevenDataSourcePath.BASE
				+ BalUnionpaySevenDataSourcePath.LISTUNIONSEVEN);
		List<BalUnionpaySevenDataSource> unionSenvenDataSourceList = service
				.selectUnionpaySevenDataSourceList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("unionSenvenDataSourceList", unionSenvenDataSourceList);
		return mv;
	}
	
	/***
	 * 导出七分钱对账源数据
	 * @param queryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(BalUnionpaySevenDataSourcePath.EXPORTUNIONSEVEN)
	public void exportZytExcel(BalUnionpaySevenDataSource queryBean, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("导出七分钱对账源数据查询对象：{}", JSONObject.toJSONString(queryBean, true));

		try {
			List<BalUnionpaySevenDataSource> excels = mapper.selectUnionpaySevenDataSourceList(queryBean);
			String headers[] = { "交易清算流水号", "交广科技清算流水号", "交易编号", "七分钱会计日期", "客户号", "内部账户ID", "交易类型", "账号",
					"支付卡类型", "七分钱订单号", "交易查询流水号", "原始交易流水号", "订单发送时间", "交易币种", "交易金额", "响应码", "响应信息", "响应时间", "清算金额",
					"清算币种", "清算日期", "系统跟踪号", "交易传输时间", "交易类型", "交易子类", "产品类型", "接入类型", "商户代码", "请求方保留域", "保留域", "兑换日期",
					"汇率", "VPC交易信息域", "支付方式", "支付卡标识", "支付卡名称", "绑定标识号", "摘要", "写入日期", "写入时间" };
			String fileName = DatetimeUtils.dateSecond() + "_七分钱银联对账源数据表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "七分钱银联对账源数据",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出七分钱银联对账源数据表成功");
		} catch (Exception e) {
			logger.error("导出七分钱银联对账源数据表异常", e);
		}
	}
}
