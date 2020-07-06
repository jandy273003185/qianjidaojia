package com.qifenqian.bms.paymentmanager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.paymentmanager.bean.TdAuditReportExport;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAuditRecode;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.dao.PaymentManagerDao;
import com.qifenqian.bms.paymentmanager.service.PaymentService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.utils.FormTokenUtil;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.querybuss.bean.AcctBuss;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayRequest;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayResponse;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;



@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class AuditPaymentController {
	
	
	private static Logger logger = LoggerFactory.getLogger(AuditPaymentController.class);
	
	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);
	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PaymentManagerDao dao;
	
	@Autowired
	private SevenpayCoreServiceInterface coreServiceInterface;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@Value("${channelMerchantid}")
	private String channelMerchantid;
	
	/**
	 * 查询批量代付记录明细
	 */
/*	@RequestMapping(PaymentManagerPath.SELBATCHTOPAYINFO)
	@ResponseBody
	public String selBatchTopayInfo(TdPaymentBatInfo single){
		JSONObject ob = new JSONObject();
		logger.info("开始 查询批量代付记录明细:{}",single);
		
		Properties properties = PropertiesUtil.getProperties("/zbMerchantId.properties");
		String channelMerchantidArray = properties.getProperty("channelMerchantid");
		if(StringUtils.isNotEmpty(channelMerchantidArray)) {
			ob.put("channelMerchantidArray", channelMerchantidArray);
		}
		try {
			TdPaymentBatInfo Info = paymentService.selBatchToPayInfo(single);
			if(null!=Info){
				ob.put("Info", Info);
				ob.put("result", "SUCCESS");
				ob.put("message", "商户批量代付信息查询成功");
			}else{
				logger.error("商户批量代付信息查询异常");
				ob.put("result", "FAILE");
				ob.put("message","商户批量代付信息查询异常");
			}
			
		} catch (Exception e) {
			logger.error("商户批量代付信息查询异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	*/
	/**
	 * 后台审核记录列表
	 * 
	 */
	@RequestMapping(PaymentManagerPath.AUDITPAYMENT)
	public ModelAndView auditPayment(TdPaymentBatInfo queryBean) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.AUDITPAYMENT);
		List<TdPaymentBatInfo> audingList = paymentService.getAudingPaymentBatList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("audingList", JSONObject.toJSON(audingList));
		return mv;
	}
	/**
	 * 根据批次号查询批量的审核信息
	 */
	@RequestMapping(PaymentManagerPath.BATCHAUDIT)
	@ResponseBody
  public String  getBatchAuditPayment(String  batNo,String type){
		JSONObject jo=new JSONObject();
		if(null!=batNo){
			TdPaymentBatInfo auditPayment = paymentService.getBatchAuditPayment(batNo);
			List<TdPaymentBatDetail> batDetailsList = paymentService.getBatchAuditPaymentList(batNo,null);
			jo.put("batDetailsList", JSONObject.toJSON(batDetailsList));
			jo.put("auditPayment", auditPayment);
			
			jo.put("result", "success");
		}else{
			jo.put("result", "fail");
			jo.put("message", "查询失败，批次号为空");
		}
		
		return jo.toJSONString();

  }

	/**
	 * 批量代付审核不通过
	 */
	@RequestMapping(PaymentManagerPath.AUDITFRISTNOTPASS)
	@ResponseBody
	public String secondNotPass(String batNo,String message,HttpServletRequest request,String email,String custName,String audit_flag){
		JSONObject ob = new JSONObject();
		logger.info("开始批量代付{}审核不通过流程",batNo);
		
		try {
			String result = paymentService.payMentAudit(batNo,message,audit_flag);
			if(null!=result && result.equals("SUCCESS")){
		     if(!StringUtils.isBlank(email)){
		    	   final IPlugin plugin = commonService.getIPlugin();
					final MessageBean messageBean = new MessageBean();
					String[] tos = new String[] {email};//EMAIL
					messageBean.setSubject("【七分钱支付】代付审核未通过");//标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
				   + "<div style=\"margin-bottom:10px;\">您的代付"+custName+"未能通过审核<br/>  ,原因是："+message+"。<br/>如有任何问题,请拨打400-633-0707</div></body></html>");//内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);//邮件EMAIL
					smsExecutors.execute(new Runnable() {
						@Override
						public void run() {
							logger.debug("发送邮件");
						    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); //邮件EMAIL
							
						}
					});	
		        }
			 }
		ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	
	
	/**
	 * 单笔代付审核不通过
	 */
	@RequestMapping(PaymentManagerPath.SINGLEAUDITNOTPASS)
	@ResponseBody
	public String singleAuditNotPass(HttpServletRequest request,TdPaymentBatInfo td,String auditFlag){
		JSONObject ob = new JSONObject();
		logger.info("开始单笔代付{}审核不通过流程",td);
		try {
			String email=td.getEmail();
			String status = "05";
			if("qjs".equals(auditFlag)){
				status = "05";
			}else{
				status = "04";
			}
			String result = paymentService.payNotPassAudit(td,status);
				if("SUCCESS".equals(result)){
					 //发送邮件短信
				     if(!StringUtils.isBlank(email)){
				    	 final IPlugin plugin = commonService.getIPlugin();
							final MessageBean messageBean = new MessageBean();
							String[] tos = new String[] {email};//EMAIL
							messageBean.setSubject("【七分钱支付】代付审核未通过");//标题
							messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
						+ "<div style=\"margin-bottom:10px;\">您的代付"+td.getCustName()+"未能通过审核<br/>  ,原因是："+td.getMessage()+"。<br/>如有任何问题,请拨打400-633-0707</div></body></html>");//内容
							messageBean.setTos(tos);
							messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);//邮件EMAIL
							smsExecutors.execute(new Runnable() {
								@Override
								public void run() {
									logger.debug("发送邮件");
								    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); //邮件EMAIL
									
								}
							});	
				        }
					ob.put("result", "SUCCESS");
			}else{
				ob.put("result", "FAILE");
				ob.put("message", "审核异常");
			}
		 
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	
	/***
	 * 核销
	 * @param batNo
	 * @param toPay_Type
	 * @param request
	 * @return
	 */
	@RequestMapping(PaymentManagerPath.VERIFICATIONPASS)
	@ResponseBody
	public String verificationPass(String batNo,String toPay_Type,HttpServletRequest request){
		JSONObject ob = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			ob.put("result", "FAILURE");
			ob.put("message", "已经确认,请不要重复提交");
			return ob.toJSONString();
		}
		logger.info("开始核销流程",batNo);
		Map map = null;
		try {
			
			if("1".equals(toPay_Type)){
				//单笔核销
				map = paymentService.singleVerification(batNo);
				
			}else{
				//批量
				map = paymentService.batVerification(batNo);
			}
			if("SUCCESS".equals(map.get("result"))){
				ob.put("result", "SUCCESS");
				ob.put("message", "核销成功");
			}else{
				ob.put("result", "FAILE");
				ob.put("message", map.get("message"));
			}
			
		} catch (Exception e) {
			logger.error("核销异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	/**
	 * 审核通过
	 */
	@RequestMapping(PaymentManagerPath.AUDITFRISTPASS)
	@ResponseBody
	public String fristPayPass(String batNo,HttpServletRequest request,String email,String custName){
		JSONObject ob = new JSONObject();
		logger.info("开始批量代付{}审核通过流程",batNo);
		try {
			paymentService.payMentFristPass(batNo);
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	/**
	 * 单笔代付清洁算审核通过
	 */
	@RequestMapping(PaymentManagerPath.SINGLEAUDITFRISTPASS)
	@ResponseBody
	public String singleAuditFristPass(HttpServletRequest request,TdPaymentBatInfo td){
		JSONObject ob = new JSONObject();
		logger.info("开始单笔代付{}审核通过流程",td);
		try {
			//审核通过
			paymentService.paySingleFristPass(td.getBatNo());
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("单笔清洁算审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   } 
	
	/**
	 * 代付-撤销
	 * 
	 */
	@RequestMapping(PaymentManagerPath.UNDOTOPAY)
	@ResponseBody
	public String undoTopay(HttpServletRequest request,TdPaymentBatInfo td){
		JSONObject ob = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			ob.put("result", "FAILURE");
			ob.put("message", "已经确认,请不要重复提交");
			return ob.toJSONString();
		}
		logger.info("撤销流程开始{}",td);
		try {
			//审核通过
			Map map = paymentService.undoTopay(td);
			if("SUCCESS".equals(map.get("result"))){
				ob.put("result", "SUCCESS");
				ob.put("message", "撤销成功");
			}else{
				ob.put("result", "FAILE");
				ob.put("message", map.get("message"));
			}
			
		} catch (Exception e) {
			logger.error("撤销流程异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   } 
	
	
	/**
	 * 批量代付-提交银行处理
	 * @param request
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.BATPAYMENTPASS)
	@ResponseBody
	public String auditSecodePass(HttpServletRequest request,String batNo,String custId,String channelType,String channelMerchantid) {
		logger.info("================= 开始  =================");
		JSONObject object = new JSONObject();
		Map<String, String> map=null;
		if(!FormTokenUtil.validateToken(request)){
			object.put("result", "FAILURE");
			object.put("message", "已经确认,请不要重复提交");
			return object.toJSONString();
		}
		
		//查询商户信息
		TdCustInfo c = new TdCustInfo();
		c.setCustId(custId);
		//查询代付账号custId
		List<TdCustInfo> cust = dao.selToPayCustList(c);//查询代付记录

		List<TdPaymentBatDetail> list = paymentService.getBatchAuditPaymentList(batNo,"05");//05清结算审核通过
		if(null!=list && list.size()>0){
			 map = paymentService.toPay(list, null,custId,batNo,channelType,cust,channelMerchantid);
		}else{
			object.put("message", "提交银行批量数据为空");
		}
		if("SUCCESS".equals(map.get("result").toString())){
			object.put("result", "SUCCESS");
			object.put("message", "提交银行处理成功");
			request.getSession().removeAttribute("detailList");
		} else {
			object.put("result", "FAIL");
			//object.put("message", "提交银行处理失败");
			object.put("message", map.get("message"));
		}
		return object.toJSONString();
	}
	
	
	
	
	/**
	 * 批量代付-提交银行处理
	 * @param request
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.SINGLEPAYMENTPASS)
	@ResponseBody
	public String singleAuditSecodePass(HttpServletRequest request,TdPaymentBatInfo td,String channelType,String lineNumber,String channelMerchantid) {
		logger.info("=================单笔财务审核 开始 =================");
		JSONObject object = new JSONObject();
		
		if(!FormTokenUtil.validateToken(request)){
			object.put("result", "FAILURE");
			object.put("message", "已经确认,请不要重复提交");
			return object.toJSONString();
		}
		try {
			td = dao.getSingleToPayInfo(td);//查询代付记录
			td.setLineNumber(lineNumber);
			td.setChannelType(channelType);
			TdCustInfo c = new TdCustInfo();
			c.setEmail(td.getPaerAcctNo());
			//查询代付账号custId
			List<TdCustInfo> cust = dao.selToPayCustList(c);//查询代付记录
			if(cust!=null && cust.size()==1){
				//判断代付金额是否大于代付在途余额
				String balance = seldfBalance(cust.get(0).getCustId());
				
				BigDecimal balance_ = new BigDecimal(balance);
				BigDecimal sumMoney = new BigDecimal(td.getSumAmt());
				int compareTo = sumMoney.compareTo(balance_);
				if(compareTo==-1){
					Map<String, String> map = paymentService.singletoPay(td,cust.get(0), null,channelMerchantid);
					if("SUCCESS".equals(map.get("result").toString())){
						object.put("result", "SUCCESS");
						object.put("message", "提交银行处理成功");
						request.getSession().removeAttribute("detailList");
					} else {
						object.put("result", "FAIL");
						object.put("message", map.get("message").toString());
					}
				}else{
					
					object.put("result", "FAIL");
					object.put("message", "代付金额不能大于商户代付余额");
				}
			}else{
				object.put("result", "FAIL");
				object.put("message", "查询代付账户信息失败");
			}
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message",e.getMessage());
		}
		
		return object.toJSONString();
	}	
	
	
	public String seldfBalance(String custId) {
		
		String useBalance = "";
		// 调用核心，查询代付账户余额信息
		try {
			RequestMessage<QueryAcctSevenBussAgentpayRequest> requestMessage = new RequestMessage<QueryAcctSevenBussAgentpayRequest>();
			{
				requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_QUERY);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
				requestMessage.setReqSerialId(DatetimeUtils.datetime());
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());
				
				QueryAcctSevenBussAgentpayRequest request_ = new QueryAcctSevenBussAgentpayRequest();
				{
					request_.setCustId(custId);
				}
				
				requestMessage.setRequest(request_);
			}
			
			ResponseMessage<QueryAcctSevenBussAgentpayResponse> responseMessage = coreServiceInterface.queryAcctSevenBussAgentpay(requestMessage);
			logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
			
			if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
				AcctBuss query = responseMessage.getResponse().getAcctBuss();
				useBalance =String.valueOf( query.getOnwayAmt());
			}
			
		} catch (Exception e) {
			logger.info("查询代付账户余额异常"+ e.getMessage(), e);
		}
			
		return useBalance;
	} 
	
	
	/**
	 * 获取微商审核历史记录
	 * 
	 */
	@RequestMapping(PaymentManagerPath.GETHISTORY)
	@ResponseBody
	public String getHistory(String batNo,HttpServletRequest request,String type_){
		List<TdPaymentAuditRecode> checkHistory = null;
		if("1".equals(type_)){
			checkHistory = paymentService.getSinglePaymentCheckHistory(batNo);

		}else{
			checkHistory = paymentService.getPaymentCheckHistory(batNo);
		}
		JSONObject ob = new JSONObject();
		ob.put("checkHistory",checkHistory);
			try {
				 if(null!=checkHistory  && checkHistory.size()>0){
					
					 }else{
						 ob.put("result", "FAILE");	 
					 }
			 } catch (Exception e) {
				logger.error("获取历史记录异常",e);
				ob.put("message", e.getMessage());
			 }
	      return  ob.toJSONString();
	   }
	/**
	 * 查询单笔代付记录明细
	 */
	@RequestMapping(PaymentManagerPath.SELSINGLETOPAYINFO)
	@ResponseBody
	public String selSingleTopayInfo(TdPaymentBatInfo single){
		JSONObject ob = new JSONObject();
		logger.info("开始 查询单笔代付记录明细:{}",single);
		
		//Properties properties = PropertiesUtil.getProperties("/zbMerchantId.properties");
		String channelMerchantidArray = channelMerchantid; //properties.getProperty("channelMerchantid");
		if(StringUtils.isNotEmpty(channelMerchantidArray)) {
			ob.put("channelMerchantidArray", channelMerchantidArray);
		}
		try {
			TdPaymentBatInfo Info = paymentService.selSingleToPayInfo(single);
			if(null!=Info){
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
	@RequestMapping(PaymentManagerPath.AUDITPAYMENTTINFOS)
	public ModelAndView getPaymentInfos(String batNo,String type,String toPayType,String audit_type){
		List<TdPaymentBatDetail> paymentList =null;
		 if(type.equals("0") && toPayType.equals("00")){//银行卡
			      //批量
		     paymentList = paymentService.getBatchReportPaymentList(batNo, null, "00");
		  }
		 
		//获取channelMerchantid
		//Properties properties = PropertiesUtil.getProperties("/zbMerchantId.properties");
		String channelMerchantidArray = channelMerchantid;//properties.getProperty("channelMerchantid");
		
		TdPaymentBatInfo auditPayment = paymentService.getBatchAuditPayment(batNo);
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.AUDITPAYMENTTINFOS);
		if(paymentList!=null && paymentList.size()>0){
			mv.addObject("paymentList", JSONObject.toJSON(paymentList));
			mv.addObject("toPayType", toPayType);
			
		  }
		mv.addObject("channelMerchantidArray", JSONObject.toJSON(channelMerchantidArray));
		mv.addObject("auditPayment", auditPayment);
		String toPayType2 = getToPayType(toPayType);
		String sumAmt = auditPayment.getSumAmt();
		String serviceFee = auditPayment.getFeeAmt();
		String totalMoney = addMoney(sumAmt, serviceFee);
		mv.addObject("totalMoney", totalMoney);
		mv.addObject("toPayType2", toPayType2);
		mv.addObject("audit_type", audit_type);
		return mv;
	 }
	public static String addMoney(String sumAmt,String serviceFee){   
		BigDecimal b1 = new BigDecimal(sumAmt);   
		BigDecimal b2 = new BigDecimal(serviceFee);   
		return b1.add(b2).toString();   
	} 
	
	public static String getToPayType(String toPayType){   
		 if(toPayType.equals("00")){
			 return "银行卡";
		 }else{
			 return "余额";
		 }
	} 
	
	
	/**
	 * 导出前台商户审核列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(PaymentManagerPath.PROEXPORTMERCHANTINFO)
	public void proExportExcel(TdPaymentBatInfo queryBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出前台商户审核列表信息");

		try {
			//List<MerchantExport> excels = merchantService.proExportMerchantInfo(merchantVo);
			
			List<TdAuditReportExport> audingList = paymentService.exportPayment(queryBean);

			String[] headers = { "批次号", "交易状态","客户名称","付款账号", "代付类型(00-银行卡|01-余额)", "单笔(1)|批量(0)", "总笔数", "手续费", "交易金额" , "成功笔数", "成功金额","失败笔数", "失败金额","创建时间"};
			String fileName = DatetimeUtils.dateMillis(new Date()) + "_商户代付审核信息表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(audingList, headers, fileName, "商户代付审核信息表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel前台商户审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel前台商户审核列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 生成Token
	 * @param request
	 * @return
	 */
	@RequestMapping(PaymentManagerPath.SAVE_TOKEN_TOPAY)
	@ResponseBody
	public String saveToken(HttpServletRequest request){
		logger.debug("生成Token");
		String token = FormTokenUtil.creatToken(request);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", token);
		return jsonObject.toJSONString();
	}
	
}
