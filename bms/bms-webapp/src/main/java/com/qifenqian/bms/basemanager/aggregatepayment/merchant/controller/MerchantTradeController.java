package com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.MerchantTradeQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.OrderSummaryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.MerchantTradeService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(MerchantTradePath.BASE)
public class MerchantTradeController {

	private static Logger logger = LoggerFactory.getLogger(MerchantTradeController.class);

	@Autowired
	private MerchantTradeService merchantTradeService;

	/**
	 * 交易汇总
	 */
	@RequestMapping(MerchantTradePath.LIST)
	public ModelAndView listSummary(MerchantTradeQueryBean queryBean,HttpServletRequest request) {
		String isFirst = request.getParameter("isFirst");
		if(queryBean.getTradeType()==null){
			queryBean.setTradeType("consume");
		}
		if(StringUtils.isEmpty(isFirst)){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			queryBean.setCreateTimeB(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			queryBean.setCreateTimeE(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		}
		ModelAndView model = new ModelAndView(MerchantTradePath.BASE + MerchantTradePath.LIST);
		
		
		List<BigDecimal> allList;
		model.addObject("queryBean",queryBean);
		model.addObject("isFirst","No");
		if(queryBean.getTradeType()!=null&&queryBean.getTradeType().equals("refund")){
			List<OrderSummaryBean> refundList =merchantTradeService.getMerchantRefundList(queryBean);
			List<OrderSummaryBean> refundListAll =merchantTradeService.getMerchantRefundExportList(queryBean);
			
			allList= merchantTradeService.getTotal(refundListAll);
			model.addObject("list",refundList);
			
		}else {
			List<OrderSummaryBean> tradeBillsAll = merchantTradeService.getMerchantExportList(queryBean);
			List<OrderSummaryBean> tradeBills = merchantTradeService.getMerchantTradeList(queryBean);
			allList= merchantTradeService.getTotal(tradeBillsAll);
			model.addObject("list",tradeBills);
			
		}
		model.addObject("allCount",allList.get(0));
		model.addObject("allSum",allList.get(1));
		model.addObject("allSettle",allList.get(2));
		return model;
	}

	/**
	 * 导出交易汇总列表
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@RequestMapping(MerchantTradePath.EXPORTLIST)
	public void exportExcel(MerchantTradeQueryBean queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出excel交易汇总表");
		
		try {
			//get方法提交的表单，要转码
			queryBean.setMerchantName(new String(queryBean.getMerchantName().getBytes("ISO-8859-1"),"UTF-8"));
			List<OrderSummaryBean> excels;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if(queryBean.getCreateTimeE() =="" && queryBean.getCreateTimeE() == null){
				queryBean.setCreateTimeE(sdf.format(cal.getTime()));
			}
			if(queryBean.getCreateTimeB() =="" && queryBean.getCreateTimeB() ==null){
				//默认开始日期为前三个月
				cal.add(Calendar.DAY_OF_YEAR, -90);
				queryBean.setCreateTimeB(sdf.format(cal.getTime()));
			}
			
			List<OrderSummaryBean> tradeBills = merchantTradeService.getMerchantExportList(queryBean);
			if(queryBean.getTradeType()!=null&&queryBean.getTradeType().equals("refund")){
				List<OrderSummaryBean> refundList =merchantTradeService.getMerchantRefundExportList(queryBean);
				excels= refundList;
			}else {
				excels = tradeBills;
			}
			for (OrderSummaryBean bean : excels) {
				bean.setCreateTimeB(queryBean.getCreateTimeB());
				bean.setCreateTimeE(queryBean.getCreateTimeE());
				if(queryBean.getTradeType().equals("consume")){
					bean.setTradeType("消费");
				}else if(queryBean.getTradeType().equals("refund")){
					bean.setTradeType("退款");
				}
			}
			String[] headers = { "开始账期", "结束账期", "商户编号", "商户名称","交易渠道" , "交易笔数", "交易金额", "客户编号", "交易类型" , "商户结算金额"};
			String fileName = DatetimeUtils.dateSecond() + "_交易汇总.xls";
			Map<String, String> fileInfo = merchantTradeService.exportExcel(excels, headers, fileName, "交易汇总表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易汇总表成功");
		} catch (Exception e) {
			logger.error("导出excel交易汇总表异常", e);
			e.printStackTrace();
		}

	}
	
	/*@RequestMapping(MerchantTradePath.ALLTRADEBILL)
	public ModelAndView allTradeBill(String bank,String payProd,String payChannel,HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("查询所有交易表（wx  ali sp）");
		ModelAndView model = new ModelAndView(MerchantTradePath.BASE + MerchantTradePath.ALLTRADEBILL);
		List<AllTradeBill> list = merchantTradeService.getAllTradeBill(bank,payProd,payChannel);
		model.addObject("allTradeBill", list);
		model.addObject("bank",bank);
		model.addObject("payProd",payProd);
		model.addObject("payChannel",payChannel);
		return model;
	}
	
	@RequestMapping(MerchantTradePath.ALLTRADEEXPORT)
	public void exportRechargeExcel(String bank,String payProd,String payChannel,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交易信息");

		try {
			String[] headers = { "订单号","商户号","商户名", "服务号", "渠道","下级渠道", "中信订单号", "渠道订单号", "返回商家页面地址", "通知商家后台调用地址", "网关版本", "订单金额","付款金额" ,"代金券" ,"商品描述","付款方式","订单时间","订单超时时间","订单失效时间","备用1","备用2","订单状态","通知时间","通知次数","通知结果(0成功500失败)","订单创建时间","订单支付成功时间","订单退款时间","修改时间","修改人"};
			List<AllTradeBill> list = merchantTradeService.getAllTradeBillExport(bank, payProd, payChannel);
			String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
			Map<String, String> fileInfo = merchantTradeService.exportExcel(list, headers, fileName, "交易信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易信息成功");
		} catch (Exception e) {
			logger.error("导出excel交易信息异常", e);
		}

	}*/

}
