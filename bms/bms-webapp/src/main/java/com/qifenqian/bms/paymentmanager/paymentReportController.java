package com.qifenqian.bms.paymentmanager;

import java.math.BigDecimal;
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
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.bean.TdReportExport;
import com.qifenqian.bms.paymentmanager.service.PaymentService;


@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class paymentReportController {
	
	private static Logger logger = LoggerFactory.getLogger(paymentReportController.class);

	@Autowired
	private PaymentService paymentService;
	
	
	@Autowired
	private TradeBillService tradeBillService;
	
	
	/**
	 * 
	 * @param batNo
	 * @return
	 */
	@RequestMapping(PaymentManagerPath.PAYMENTREPORTlIST)
	public ModelAndView selectList(TdPaymentBatInfo queryBean) {
		BigDecimal totalMoney = BigDecimal.ZERO;
		BigDecimal FeeAmt=BigDecimal.ZERO;
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTREPORTlIST);

		/*去掉多余的空格*/
		if (!StringUtils.isBlank(queryBean.getRecAccountNo())){
			queryBean.setRecAccountNo(queryBean.getRecAccountNo().trim());
		}
		List<TdPaymentBatInfo> reportList = paymentService.getPaymentReportList(queryBean);
		for (int i = 0; i < reportList.size(); i++) {
			totalMoney = totalMoney.add(new BigDecimal(reportList.get(i).getSumAmt()));
			FeeAmt=FeeAmt.add(new BigDecimal(reportList.get(i).getServiceFee()));
		}
		mv.addObject("totalMoney", totalMoney);
		mv.addObject("FeeAmt", FeeAmt);
		mv.addObject("sumCount", reportList.size());
		mv.addObject("reportList", JSONObject.toJSON(reportList));
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(PaymentManagerPath.PAYMENTREPORTINFOS)
	public ModelAndView getPaymentInfos(String batNo,String type,String toPayType){
		List<TdPaymentBatDetail> paymentList =null;
		 if(type.equals("0") && toPayType.equals("00")){//银行卡
			      //批量
		     paymentList = paymentService.getBatchReportPaymentList(batNo, null, "00");
		  }
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTREPORTINFOS);
		if(paymentList!=null && paymentList.size()>0){
			mv.addObject("paymentList", JSONObject.toJSON(paymentList));
			mv.addObject("toPayType", toPayType);
			
		}
		return mv;
	}
	@RequestMapping(PaymentManagerPath.GETREPORTEXPORT)
	public void backExportExcel(TdPaymentBatInfo paymentBatInfo, HttpServletRequest request, HttpServletResponse response) {
			logger.info("导出后台代付统计报表列表信息");
		try {
				List<TdReportExport> excels = paymentService.getReportExport(paymentBatInfo);
				String[] headers = { "交易号","代付账号", "名称", "代付方式", "代付类型", "代付笔数","代付金额", "代付手续费", "成功笔数","失败笔数","成功金额","失败金额","代付时间","代付状态"};
				String fileName = DatetimeUtils.dateSecond() + "_代付统计报表信息.xls";
				Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代付统计报表", request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出excel代付统计报表列表成功");
			  } catch (Exception e) {
				logger.error("导出exce代付统计报表列表异常", e);
				throw new RuntimeException(e);
			 }
		   }

	
	@RequestMapping(PaymentManagerPath.GETSINGLEREPORT)
	@ResponseBody
	public String getOpenHistory(HttpServletRequest request,String batNo,String toPayType) {
		TdPaymentBatInfo singleInfo=null;
		JSONObject object = new JSONObject();
		try {
			if(toPayType.equals("00")){
				singleInfo = paymentService.getSingleInfo(batNo, "00");
				object.put("toPayType", "00");
				object.put("singleInfo",singleInfo);
			  }else{
				 singleInfo= paymentService.getSingleInfo(batNo, "01");
				 object.put("toPayType", "01");
				 object.put("singleInfo",singleInfo);
			  }
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "代付商户查询审核历史失败");
			logger.info("更新商户信息异常:" + e.getMessage(), e);
		}
		object.put("result", "SUCCESS");
		return object.toJSONString();
	}
	
}
