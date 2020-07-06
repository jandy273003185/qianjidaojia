package com.qifenqian.bms.accounting.unionpayQuery.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.checkquery.bean.BalResultStatistic;
import com.qifenqian.bms.accounting.checkquery.mapper.BalResultStatisticMapper;
import com.qifenqian.bms.accounting.checkquery.service.BalResultStatisticService;
import com.qifenqian.bms.accounting.checkquery.type.ChannelId;
import com.qifenqian.bms.accounting.unionpayQuery.UnionpayQueryPath;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultEqual;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultEqualBean;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultEqualExport;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultException;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultExceptionBean;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpaySevenResultExceptionExport;
import com.qifenqian.bms.accounting.unionpayQuery.bean.BalUnionpayUnionResultException;
import com.qifenqian.bms.accounting.unionpayQuery.bean.UnionpayImpeathExport;
import com.qifenqian.bms.accounting.unionpayQuery.bean.UnionpayUnionResultExceptionBean;
import com.qifenqian.bms.accounting.unionpayQuery.service.UnionpayService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
/**
 * 
 * @author shen
 *
 */
@Controller
@RequestMapping(UnionpayQueryPath.BASE)
public class UnionpayController {

	private static Logger logger = LoggerFactory.getLogger(UnionpayController.class);
	@Autowired
	private BalResultStatisticService balResultStatisticService;

	@Autowired
	private BalResultStatisticMapper balResultStatisticMapper;

	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private UnionpayService unionpayService;

	/**
	 * 银联对账结果查询
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(UnionpayQueryPath.LIST)
	public ModelAndView statisticsList(BalResultStatistic requestBean) {
		logger.info("银联对账结果查询对象:{}", JSONObject.toJSONString(requestBean, true));
		requestBean.setChannelId(ChannelId.UNIONPAY.name());
		ModelAndView mv = new ModelAndView(UnionpayQueryPath.BASE + UnionpayQueryPath.LIST);
		mv.addObject("selectBean", requestBean);
		mv.addObject("statisticList", balResultStatisticService.selectListByPage(requestBean));
		return mv;

	}

	/**
	 * 导出银联对账结果报表
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(UnionpayQueryPath.EXPORT)
	public void exportExcel(BalResultStatistic requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出银联对账结果报表查询对象：{}", JSONObject.toJSONString(requestBean, true));
		requestBean.setChannelId(ChannelId.UNIONPAY.name());
		
		try {
			List<BalResultStatistic> excels = balResultStatisticMapper.selectList(requestBean);

			String[] headers = { "对账批次", "对账日期", "会计日期", "渠道", "文件编号", "交易结果", "业务类型", "数据平台", "总笔数", "总金额", "对账一致笔数", "对账一致总额", "对账存疑笔数", "对账存疑总额", "对账差错笔数", "对账差错总额", "自身异常笔数", "自身异常总额", "状态", "备注" };
			String fileName = "对账统计_" + (StringUtils.isBlank(requestBean.getChannelId()) ? "" : requestBean.getChannelId() + "_") + (StringUtils.isBlank(requestBean.getWorkDateMin()) ? "" : requestBean.getWorkDateMin() + "-") + (StringUtils.isBlank(requestBean.getWorkDateMax()) ? DatetimeUtils.shortDate() + "_" : requestBean.getWorkDateMax() + "_") + DatetimeUtils.dateSecond() + ".xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "对账结果统计表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel对账统计报表成功");
		} catch (Exception e) {
			logger.error("导出excel对账统计报表异常", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 银联存疑查询
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(UnionpayQueryPath.IMPEACH)
	public ModelAndView impeachList(UnionpayUnionResultExceptionBean requestBean) {
		logger.info("银联存疑查询:{}", JSONObject.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(UnionpayQueryPath.BASE + UnionpayQueryPath.IMPEACH);
		List<BalUnionpayUnionResultException> impeachList = unionpayService.selectImpeachList(requestBean);
		mv.addObject("impeachList", impeachList);
		mv.addObject("requestBean", requestBean);
		return mv;
	}

	/**
	 * 银联存疑导出
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(UnionpayQueryPath.EXPORTIMPEACH)
	public void exportImpeachExcel(UnionpayUnionResultExceptionBean requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出银联存疑数据查询对象:{}", JSONObject.toJSONString(requestBean, true));

		try {
			List<UnionpayImpeathExport> impeachList = unionpayService.selectImpeachListExport(requestBean);
			String[] headers = { "文件编号", "序号/行号", "会计日期(数据日期)", "批次编号", "交易代码", "代理机构标识码", "发送机构标识码", "系统跟踪号", "交易传输时间", "帐号", "交易金额", "商户类别", "终端类型", "查询流水号", "支付方式（旧）", "商户订单号", "支付卡类型", "原始交易的系统跟踪号", "原始交易日期时间", "商户手续费X+n12标示D/C", "商户手续费X+n12", "结算金额X+n12标示D/C", "结算金额X+n12", "支付方式", "集团商户代码", "交易类型", "交易子类", "业务类型", "帐号类型", "账单类型", "账单号码", "交互方式", "原交易查询流水号", "商户代码", "分账入账方式", "二级商户代码", "二级商户简称", "二级商户分账入账金额X+n12标示D/C", "二级商户分账入账金额X+n12", "清算净额X+n12标示D/C", "清算净额X+n12", "终端号", "商户自定义域", "优惠金额X+n12标示D/C", "优惠金额X+n12", "发票金额X+n12标示D/C", "发票金额X+n12", "分期付款附加手续费X+n11标示D/C", "分期付款附加手续费X+n11", "分期付款期数", "交易介质", "保留使用", "写入日期：YYYY-MM-DD", "写入时间", "对账结果：SELF_DOUBT/BAL_DOUBT/BAL_ERROR", "对账处理时间", "对账处理备注", "异常处理标记：挂账/抹账/销账", "异常处理人", "异常处理时间", "异常处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_银联存疑数据.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(impeachList, headers, fileName, "银联存疑数据", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出银联存疑数据成功");
		} catch (Exception e) {
			logger.error("导出银联存疑数据异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 七分钱存疑列表
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(UnionpayQueryPath.QFQIMPEACH)
	public ModelAndView qfqImpeachList(BalUnionpaySevenResultExceptionBean requestBean) {
		logger.info("七分钱存疑查询对象:{}", JSONObject.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(UnionpayQueryPath.BASE + UnionpayQueryPath.QFQIMPEACH);
		List<BalUnionpaySevenResultException> qfqimpeachList = unionpayService.selectQfqImpeachList(requestBean);
		mv.addObject("qfqimpeachList", qfqimpeachList);
		mv.addObject("requestBean", requestBean);
		return mv;
	}

	/**
	 * 导出七分钱存疑
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(UnionpayQueryPath.EXPORTQFQIMPEACH)
	public void exportQfqImpeach(BalUnionpaySevenResultExceptionBean requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出七分钱存疑查询对象：{}", JSONObject.toJSONString(requestBean, true));

		try {
			List<BalUnionpaySevenResultExceptionExport> impeachList = unionpayService.selectQfqImpeachExport(requestBean);
			String[] headers = { "交易清算流水号", "对账批次号", "交易编号", "七分钱会计日期", "客户号", "内部账户ID", "交易类型", "账号", "支付卡类型", "七分钱订单号", "交易查询流水号", "原始交易流水号", "订单发送时间", "交易币种", "交易金额", "响应码", "响应信息", "响应时间", "清算金额", "清算币种", "清算日期", "系统跟踪号", "交易传输时间", "交易类型", "交易子类", "产品类型", "接入类型", "商户代码", "请求方保留域", "保留域", "兑换日期", "汇率", "VPC交易信息域", "支付方式", "支付卡标识", "支付卡名称", "绑定标识号", "摘要", "写入日期", "写入时间", "对账结果", "对账处理时间", "对账处理备注", "异常处理标记", "异常处理人", "异常处理时间", "异常处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_七分钱存疑数据.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(impeachList, headers, fileName, "七分钱存疑数据", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出七分钱存疑成功");
		} catch (Exception e) {
			logger.error("导出七分钱存疑异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 差错报表
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(UnionpayQueryPath.SLIPREPORT)
	public ModelAndView slipList(BalUnionpaySevenResultExceptionBean requestBean) {
		logger.info("差错报表查询对象:{}", JSONObject.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(UnionpayQueryPath.BASE + UnionpayQueryPath.SLIPREPORT);
		List<BalUnionpaySevenResultException> qfqimpeachList = unionpayService.selectSlipList(requestBean);
		mv.addObject("slipList", qfqimpeachList);
		mv.addObject("requestBean", requestBean);
		return mv;
	}

	/**
	 * 导出差错报表
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(UnionpayQueryPath.EXPORTSLIP)
	public void exportSlip(BalUnionpaySevenResultExceptionBean requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出差错报表查询对象：{}", JSONObject.toJSONString(requestBean, true));

		try {
			List<BalUnionpaySevenResultExceptionExport> impeachList = unionpayService.selectSlipExport(requestBean);
			String[] headers = { "交易清算流水号", "对账批次号", "交易编号", "七分钱会计日期", "客户号", "内部账户ID", "交易类型", "账号", "支付卡类型", "七分钱订单号", "交易查询流水号", "原始交易流水号", "订单发送时间", "交易币种", "交易金额", "响应码", "响应信息", "响应时间", "清算金额", "清算币种", "清算日期", "系统跟踪号", "交易传输时间", "交易类型", "交易子类", "产品类型", "接入类型", "商户代码", "请求方保留域", "保留域", "兑换日期", "汇率", "VPC交易信息域", "支付方式", "支付卡标识", "支付卡名称", "绑定标识号", "摘要", "写入日期", "写入时间", "对账结果", "对账处理时间", "对账处理备注", "异常处理标记", "异常处理人", "异常处理时间", "异常处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_差错报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(impeachList, headers, fileName, "差错报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出差错报表成功");
		} catch (Exception e) {
			logger.error("导出差错报表异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 一致报表
	 * 
	 * @param requestBean
	 * @return
	 */
	@RequestMapping(UnionpayQueryPath.FITREPORT)
	public ModelAndView fitList(BalUnionpaySevenResultEqualBean requestBean) {
		logger.info("查询一致报表查询对象:{}", JSONObject.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(UnionpayQueryPath.BASE + UnionpayQueryPath.FITREPORT);
		List<BalUnionpaySevenResultEqual> fitList = unionpayService.selectFitList(requestBean);
		mv.addObject("fitList", fitList);
		mv.addObject("requestBean", requestBean);
		return mv;
	}

	/**
	 * 导出一致报表
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(UnionpayQueryPath.EXPORTFIT)
	public void exportFit(BalUnionpaySevenResultEqualBean requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出一致报表查询对象： {}", JSONObject.toJSONString(requestBean, true));

		try {
			List<BalUnionpaySevenResultEqualExport> impeachList = unionpayService.selectFitexport(requestBean);
			String[] headers = { "交易清算流水号", "对账批次号", "交易编号", "七分钱会计日期", "客户号", "内部账户ID", "交易类型", "账号", "支付卡类型", "七分钱订单号", "交易查询流水号", "原始交易流水号", "订单发送时间", "交易币种", "交易金额", "响应码", "响应信息", "响应时间", "清算金额", "清算币种", "清算日期", "系统跟踪号", "交易传输时间", "交易类型", "交易子类", "产品类型", "接入类型", "商户代码", "请求方保留域", "保留域", "兑换日期 YYYYMMDD", "汇率 YYYYMMDDhhmmss", "VPC交易信息域", "支付方式", "支付卡标识", "支付卡名称", "绑定标识号", "摘要", "写入日期", "写入时间","对账结果","对账处理时间","对账处理备注" };
			String fileName = DatetimeUtils.dateSecond() + "_一致报表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(impeachList, headers, fileName, "一致报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出一致报表成功");
		} catch (Exception e) {
			logger.error("导出一致报表异常", e);
			e.printStackTrace();
		}
	}
}
