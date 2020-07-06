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
import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpayUnionDataSource;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.mapper.BalUnionpayUnionDataSourceMapper;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.service.BalUnionpayUnionDataSourceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 银联对账对账源数据
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(BalUnionpayUnionDataSourcePath.BASE)
public class BalUnionpayUnionDataSourceController {
	private static Logger logger = LoggerFactory.getLogger(BalUnionpayUnionDataSourceController.class);
	@Autowired
	private BalUnionpayUnionDataSourceService service;

	@Autowired
	private BalUnionpayUnionDataSourceMapper mapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 银联对账对账源数据
	 * 
	 * @param request
	 * @param source
	 * @return
	 */
	@RequestMapping(BalUnionpayUnionDataSourcePath.LISTUNION)
	public ModelAndView sevenDataSourceList(HttpServletRequest request, BalUnionpayUnionDataSource queryBean) {

		logger.info("银联对账对账源数据查询对象：{}", JSONObject.toJSONString(queryBean,true));

		ModelAndView mv = new ModelAndView(BalUnionpayUnionDataSourcePath.BASE
				+ BalUnionpayUnionDataSourcePath.LISTUNION);
		List<BalUnionpayUnionDataSource> unionDataSourceList = service.selectUnionpayUnionDataSourceList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("unionDataSourceList", unionDataSourceList);
		return mv;
	}

	/***
	 * 导出银联对账对账源数据
	 * @param source
	 * @param request
	 * @param response
	 */
	@RequestMapping(BalUnionpayUnionDataSourcePath.EXPORTUNION)
	public void exportZytExcel(BalUnionpayUnionDataSource requestBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出银联对账对账源数据查询对象：{}",JSONObject.toJSONString(requestBean,true));

		try {
			List<BalUnionpayUnionDataSource> excels = mapper.selectUnionpayUnionDataSourceList(requestBean);
			
			String headers[] = { "文件编号", "序号/行号", "交广科技交易流水号", "会计日期(数据日期)", "交易代码", "代理机构标识码", "发送机构标识码", "系统跟踪号",
					"交易传输时间", "帐号", "交易金额", "商户类别", "终端类型", "查询流水号", "支付方式（旧）", "商户订单号", "支付卡类型", "原始交易的系统跟踪号",
					"原始交易日期时间", "商户手续费X+n12标示D/C", "商户手续费X+n12", "结算金额X+n12标示D/C", "结算金额X+n12", "支付方式", "集团商户代码",
					"交易类型", "交易子类", "业务类型", "帐号类型", "账单类型", "账单号码", "交互方式", "原交易查询流水号", "商户代码", "分账入账方式", "二级商户代码",
					"二级商户简称", "二级商户分账入账金额X+n12标示D/C", "二级商户分账入账金额X+n12", "清算净额X+n12标示D/C", "清算净额X+n12", "终端号",
					"商户自定义域", "优惠金额X+n12标示D/C", "优惠金额X+n12", "发票金额X+n12标示D/C", "发票金额X+n12", "分期付款附加手续费X+n11标示D/C",
					"分期付款附加手续费X+n11", "分期付款期数", "交易介质", "保留使用", "写入日期", "写入时间" };
			String fileName = DatetimeUtils.dateSecond() + "_银联对账对账源数据.xls";
			Map<String, String> fileInfo = tradeBillService
					.exportExcel(excels, headers, fileName, "银联对账对账源数据", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出银联对账对账源数据成功");
		} catch (Exception e) {
			logger.error("导出银联对账对账源数据异常", e);
		}
	}
}
