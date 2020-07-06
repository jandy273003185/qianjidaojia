package com.qifenqian.bms.paymentmanager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.bean.TdRecodeExport;
import com.qifenqian.bms.paymentmanager.service.PaymentService;


@Controller
@RequestMapping (PaymentManagerPath.BASE)
public class ToPayRecodeQueryController {
	
private static Logger logger = LoggerFactory.getLogger(ToPayRecodeQueryController.class);

	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@RequestMapping(value=PaymentManagerPath.GETPAYMENTQUERY)
	public ModelAndView Recode(TdPaymentBatInfo queryBean) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.GETPAYMENTQUERY);
		List<TdPaymentBatInfo> audingList = paymentService.getPaymentRecodeQuery(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("audingList", JSONObject.toJSON(audingList));
		return mv;
	}
	/**
	 * 查询单笔代付记录明细
	 */
	
	@RequestMapping(PaymentManagerPath.GETSINGLETOPAYINFO)
	@ResponseBody
	public String getSingleTopayInfo(String  batNo,String toPayType){
		JSONObject ob = new JSONObject();
		TdPaymentBatInfo single=new TdPaymentBatInfo();
		single.setBatNo(batNo);
		single.setToPayType(toPayType);
		logger.info("开始 查询单笔代付记录明细:{}",single);
		try {
			TdPaymentBatInfo Info = paymentService.getSingleToPayOfRecode(single);
			if(null!=Info){
				String status = Info.getTradeStatus();
				String statusStr = getStatusStr(status);
				if(null!=statusStr){
					Info.setTradeStatus(statusStr);
				}
				ob.put("Info", Info);
				ob.put("result", "SUCCESS");
				ob.put("message", "商户单笔代付信息查询成功");
			}else{
				logger.error("商户单笔代付信息查询异常");
				ob.put("result", "FAILE");
				ob.put("message","商户单笔代付信息查询异常");
			}
			
		} catch (Exception e) {
			logger.error("商户单笔代付信息查询异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  


	
	@RequestMapping(PaymentManagerPath.GETREPORTEXPORTOFPAYMENT)
	public void backExportExcel(TdPaymentBatInfo paymentBatInfo, HttpServletRequest request, HttpServletResponse response) {
			logger.info("导出后台代付记录报表信息");
		try {
				List<TdRecodeExport> excels = paymentService.getPaymentRecodeQueryExcel(paymentBatInfo);
				String[] headers = { "交易号", "代付账号", "商户名称", "代付方式", "代付类型","代付笔数", "代付金额", "手续费","时间","状态"};
				String fileName = DatetimeUtils.dateSecond() + "_代付记录报表信息.xls";
				Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代付统计报表", request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出excel付记录报表列表成功");
			  } catch (Exception e) {
				logger.error("导出excel付记录报表列表异常", e);
				throw new RuntimeException(e);
			 }
		   }
	
	public String getStatusStr(String status){
		String str=null;
		if(null!=status && status.equals("04")){
			str="前台审核通过";
		}else if(null!=status && status.equals("06")){
			str="清洁算审核通过";
		}else if(null!=status && status.equals("07")){
			str="已撤销";
		}else if(null!=status && status.equals("08")){
			str="发送银行成功";
		}else if(null!=status && status.equals("99")){
			str="交易失败";
		}else if(null!=status && status.equals("92")){
			str="清洁算审核未通过";
		}else if(null!=status && status.equals("93")){
			str="财务审核未通过";
		}else if(null!=status && status.equals("60")){
			str="银行打款成功";
		}else if(null!=status && status.equals("00")){
			str="交易成功";
		}else if(null!=status && status.equals("80")){
			str="核销失败";
		}else if(null!=status && status.equals("94")){
			str="撤销失败";
		}
		return str;
	}
	
    }
