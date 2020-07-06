package com.qifenqian.bms.paymentmanager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.paymentmanager.bean.TdPayInInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAccountAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentFeeInfo;
import com.qifenqian.bms.paymentmanager.service.PaymentService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.createacctbussagentpay.CreateAcctSevenBussAgentpayRequest;
import com.sevenpay.invoke.transaction.createacctbussagentpay.CreateAcctSevenBussAgentpayResponse;

@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class PaymentAuditController {
	private static Logger logger = LoggerFactory.getLogger(PaymentManagerController.class);

	
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private audingService audingService;
	@Autowired
	private SevenpayCoreServiceInterface coreServiceInterface;
	
	
	@RequestMapping(PaymentManagerPath.AUDITTOPAY)
	public ModelAndView openToPayList(HttpServletRequest request,TdCustInfo cust) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.AUDITTOPAY);
		//查询代付待审核商户
	    //cust.setTopayStatue("NO");
		cust.setEndTime(addDataOne(cust.getEndTime()));
		List<TdCustInfo> list =  paymentService.selToPayCustList(cust);
		mv.addObject("list", list);
		cust.setEndTime(subDataOne(cust.getEndTime()));
		mv.addObject("cust", cust);
		return mv;
	}
	
	@RequestMapping(PaymentManagerPath.SELCUSTINFO)
	@ResponseBody
	public String selCustInfo(HttpServletRequest request,TdCustInfo cust) {
		logger.info("================= 查询商户信息 开始 =================");
		JSONObject object = new JSONObject();
		//查询代付商户信息
		cust =  paymentService.selToPayCust(cust.getCustId());
		
		//查询代付账户费用信息
		TdPaymentFeeInfo pay = new TdPaymentFeeInfo();
		pay.setAccountNo(cust.getEmail());
		pay =  paymentService.selTdPaymentFeeInfo(pay);
		
		//查询默认代付费用信息
		TdPayInInfo td = new TdPayInInfo();
		td.setFeeCode("1001");
		td = paymentService.selDefaultFeeInfo(td);
		object.put("defaultAmtRate", td.getFeeRate());
		td.setFeeCode("1002");
		td = paymentService.selDefaultFeeInfo(td);
		object.put("defaultWorkRate", td.getFeeRate());
		td.setFeeCode("1003");
		td = paymentService.selDefaultFeeInfo(td);
		object.put("defaultNoWorkRate", td.getFeeRate());
		td.setFeeCode("1004");
		td = paymentService.selDefaultFeeInfo(td);
		object.put("defaultPayFee", td.getFeeRate());
		
		if(cust!=null){
			object.put("result", "SUCCESS");
			object.put("message", "商户信息查询成功");
			object.put("cust", cust);
			object.put("pay", pay);
			
		} else {
			object.put("result", "FAIL");
			object.put("message", "商户信息查询成功");
		}
		return object.toJSONString();
	}
	
	
	@RequestMapping(PaymentManagerPath.AUDITPASS)
	@ResponseBody
	public String AuditPass(HttpServletRequest request,TdCustInfo cust) {
		logger.info("================= 代付商户审核开始 =================");
		JSONObject object = new JSONObject();
		
		/**调用核心开通代付账户**/
		ResponseMessage<CreateAcctSevenBussAgentpayResponse> responseMessage = null;
		try {
			
			RequestMessage<CreateAcctSevenBussAgentpayRequest> requestMessage = new RequestMessage<CreateAcctSevenBussAgentpayRequest>();
			{
				requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_CREATE);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
				requestMessage.setReqSerialId(DatetimeUtils.datetime());
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());
				
				CreateAcctSevenBussAgentpayRequest request_ = new CreateAcctSevenBussAgentpayRequest();
				{
					request_.setCustId(cust.getCustId());
					request_.setCustName(cust.getCustName());
				}
				requestMessage.setRequest(request_);
			}
			
			responseMessage = coreServiceInterface.createAcctSevenBussAgentpay(requestMessage);
			
			
			logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
			
			//核心返回成功后更新客户信息表
			if(responseMessage!=null){
				if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
					TdCustInfo cust_ =new TdCustInfo();
					cust_.setDfAccId(responseMessage.getResponse().getAccId());
					cust_.setTopayStatue("YES");
					cust_.setCustId(cust.getCustId());
					paymentService.updateToPayCust(cust_);
					
					//添加商户代付账户费用信息
					insertPaymentFeeInfo(cust);
					//添加代付审核信息
					insertAccountAuditInfo(cust,"audit");
				}
			}
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "商户开通代付审核失败");
			logger.info("核心开通代付账户异常或更新商户信息异常:" + e.getMessage(), e);
		}
		
		object.put("result", "SUCCESS");
		object.put("message", "商户信息查询成功");
		logger.info("================= 代付商户审核结束 =================");
		return object.toJSONString();
	}
	
	
	@RequestMapping(PaymentManagerPath.AUDITNOTPASS)
	@ResponseBody
	public String AuditNotPass(HttpServletRequest request,TdCustInfo cust) {
		logger.info("================= 代付商户审核开始 =================");
		JSONObject object = new JSONObject();
		try {
			TdCustInfo cust_ =new TdCustInfo();
			cust_.setTopayStatue("AUDIT_NO");
			cust_.setCustId(cust.getCustId());
			paymentService.updateToPayCust(cust_);
			
			//添加代付审核信息
			insertAccountAuditInfo(cust,"auditNot");
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "商户开通代付审核失败");
			logger.info("更新商户信息异常:" + e.getMessage(), e);
		}
		
		object.put("result", "SUCCESS");
		object.put("message", "商户信息查询成功");
		logger.info("================= 代付商户审核结束 =================");
		return object.toJSONString();
	}
	
	@RequestMapping(PaymentManagerPath.GETOPENHISTORY)
	@ResponseBody
	public String getOpenHistory(HttpServletRequest request,TdCustInfo cust) {
		logger.info("================= 代付商户查询审核历史开始 =================");
		JSONObject object = new JSONObject();
		try {
			TdPaymentAccountAuditInfo acctInfo = new TdPaymentAccountAuditInfo();
			acctInfo.setAccount(cust.getEmail());
			List<TdPaymentAccountAuditInfo> list = paymentService.selToPayHistory(acctInfo);
			object.put("list", list);
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "代付商户查询审核历史失败");
			logger.info("更新商户信息异常:" + e.getMessage(), e);
		}
		
		object.put("result", "SUCCESS");
		object.put("message", "代付商户查询审核历史成功");
		logger.info("================= 代付商户查询审核历史结束 =================");
		return object.toJSONString();
	}
	
	
	
	// 读取服务器图片
	@RequestMapping(PaymentManagerPath.GETIMG)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String scanPath = "";
		if("99".equals(certifyType)){
			scanPath =paymentService.findBankScanPath(custId, certifyType);
		}else{
			scanPath =audingService.findScanPath(custId, certifyType);
		}
		
		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_PERSON_IDCARD)) {
				scanPath =scanPath.split(";")[0] ;
			}
			
			if (certifyType.equals(Constant.CERTIFY_TYPE_OPEN)) {
				scanPath =scanPath.split(";")[0] ;
			}
			
			if(certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)){
				scanPath = scanPath.split(";")[0];
			}
			
			if(certifyType.equals(Constant.CERTIFY_TYPE_BANK_PAPERS)){
				scanPath = scanPath.split(";")[0];
			}
			if (!StringUtils.isEmpty(front)) {
				path = scanPath.split(";");
				if (front.equals("0")) {
					scanPath = path[0];
				} else {
					scanPath = path[1];
				}
			}

			OutputStream os = response.getOutputStream();
			File file = new File(scanPath);

			if (file.exists()) {
				FileInputStream fips = new FileInputStream(file);
				byte[] btImg = readStream(fips);
				os.write(btImg);
				os.flush();
				if (null != fips) {
					fips.close();
				}
				if (null != os) {
					os.close();

				}
			}

		}

	}

	/**
	 * 读取管道中的流数据
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			if (null != bops) {
				try {
					bops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	/**
	 * 添加商户代付账户费用信息
	 * @param cust
	 */
	public void insertPaymentFeeInfo(TdCustInfo cust){
		TdPaymentFeeInfo fee = new TdPaymentFeeInfo();
		fee.setAccountNo(cust.getEmail());
		fee.setPayFee(new BigDecimal(cust.getPayFee()));
		fee.setWorkRate(new BigDecimal(cust.getWorkRate()));
		fee.setNoWorkRate(new BigDecimal(cust.getNoWorkRate()));
		fee.setAmtRate(new BigDecimal(cust.getAmtRate()));
		paymentService.insertTdPaymentFeeInfo(fee);
	}
	
	/**
	 * 添加商户代付审核信息
	 * @param cust
	 */
	public void insertAccountAuditInfo(TdCustInfo cust,String flag){
		TdPaymentAccountAuditInfo info = new TdPaymentAccountAuditInfo();
		String id = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+RandomStringUtils.randomNumeric(5);
		String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		info.setId(id);
		info.setAccount(cust.getEmail());
		info.setAuditUser(auditUserId);
		if("audit".equals(flag)){
			info.setAuditStatus("00");
			info.setMemo("审核成功");
		}else{
			info.setAuditStatus("00");
			info.setMemo(cust.getComment());
		}
		paymentService.insertPaymentAccountAuditInfo(info);
		
	}
	
	
	public String addDataOne(String end_time){//时间加一天
		if(null!=end_time&&(!"".equals(end_time))){
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");/*** 加一天*/
				Date addData = df.parse(end_time);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(addData);
				calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
				return df.format(calendar.getTime());
			} catch (Exception e) {
				return null;
			}
		}else{
			return end_time;
		}
		
	}
	
	public String subDataOne(String end_time){//时间减一天
		
		if(null!=end_time&&(!"".equals(end_time))){
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				long dif = df.parse(end_time).getTime()-86400*1000;//减一天
				Date date=new Date();
				date.setTime(dif);
				return df.format(date);
			} catch (Exception e) {
				return null;
			}
		}else{
			return end_time;
		}
		
	}
	
	
}
