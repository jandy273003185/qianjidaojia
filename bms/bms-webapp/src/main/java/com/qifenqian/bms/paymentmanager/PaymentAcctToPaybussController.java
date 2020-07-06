package com.qifenqian.bms.paymentmanager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.paymentmanager.bean.AcctSevenBussAgentpay;
import com.qifenqian.bms.paymentmanager.service.PaymentService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctStatus;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.editbussagentpay.EditAcctSevenBussAgentpayRequest;
import com.sevenpay.invoke.transaction.editbussagentpay.EditAcctSevenBussAgentpayResponse;
import com.sevenpay.invoke.transaction.querybuss.bean.AcctBuss;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayRequest;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayResponse;



@Controller
@RequestMapping (PaymentManagerPath.BASE)
public class PaymentAcctToPaybussController {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentManagerController.class);
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private SevenpayCoreServiceInterface coreServiceInterface;
	
	/**
	 * 获取代付账户列表
	 */
	
	@RequestMapping(PaymentManagerPath.ACCTTOPAYBUSS)
	public ModelAndView selectList(AcctSevenBussAgentpay queryBean) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.ACCTTOPAYBUSS);
		if (!StringUtils.isBlank(queryBean.getEmail())){
			queryBean.setEmail(queryBean.getEmail().trim());
		}
		if (!StringUtils.isBlank(queryBean.getMobile())){
			queryBean.setMobile(queryBean.getMobile().trim());
		}
		if (!StringUtils.isBlank(queryBean.getCustName())){
			queryBean.setCustName(queryBean.getCustName().trim());
		}
		if (!StringUtils.isBlank(queryBean.getAcctId())){
			queryBean.setAcctId(queryBean.getAcctId().trim());
		}
		
		List<AcctSevenBussAgentpay> acctSevenBussList = paymentService.queryAcctSevenToPayBuss(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("acctSevenBussList", JSONObject.toJSON(acctSevenBussList));
		return mv;
	}
	
	
	@RequestMapping(PaymentManagerPath.GETPAYMENTSTATUS)
	@ResponseBody
	public String getStatus(String custId) {
		JSONObject object = new JSONObject();
		String status=null;
		RequestMessage<QueryAcctSevenBussAgentpayRequest> requestMessage = new RequestMessage<QueryAcctSevenBussAgentpayRequest>();
		{
			requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_QUERY);
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
			requestMessage.setReqSerialId(DatetimeUtils.datetime());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());
			QueryAcctSevenBussAgentpayRequest request = new QueryAcctSevenBussAgentpayRequest();
			 {
				request.setCustId(custId);

			 }
			requestMessage.setRequest(request);
		}
		ResponseMessage<QueryAcctSevenBussAgentpayResponse> responseMessage = coreServiceInterface.queryAcctSevenBussAgentpay(requestMessage);
		logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
		if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
			AcctBuss query = responseMessage.getResponse().getAcctBuss();
			status = query.getStatus().toString();
			object.put("result", "SUCCESS");
			object.put("status",status);
		}else{
			object.put("result", "FAIL");
			object.put("massage", "核心返回状态异常");
		}
	   return object.toJSONString();
		
	 }
     
	
	
	
	

	@RequestMapping(PaymentManagerPath.GETPAYMENTBALANCE)
	@ResponseBody
	public String getBalance(HttpServletRequest request,String custId) {
		JSONObject object = new JSONObject();
		String useBalance = "";
		String status=null;
		// 调用核心，开通代付账户
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
				useBalance =String.valueOf( query.getUsableAmt());
				status = query.getStatus().toString();
				object.put("result", "SUCCESS");
				object.put("useBalance", useBalance);
				object.put("status",status);
			}else{
				object.put("result", "FAIL");
				object.put("massage", "核心返回余额异常");
			}
			
		} catch (Exception e) {
			logger.info("查询代付账户余额异常"+ e.getMessage(), e);
		}
		return object.toJSONString();
	}
	
	/**
	 * 
	 */
	
	@RequestMapping(PaymentManagerPath.UPDATEPAYMENTSTATUS)
	@ResponseBody
	public String updatePaymentStatus(String custId,String acctName,AcctStatus status) {
		JSONObject object = new JSONObject();
		RequestMessage<EditAcctSevenBussAgentpayRequest> requestMessage = new RequestMessage<EditAcctSevenBussAgentpayRequest>();
		{
			requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_EDIT);
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
			requestMessage.setReqSerialId(DatetimeUtils.datetime());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());
			EditAcctSevenBussAgentpayRequest request = new EditAcctSevenBussAgentpayRequest();
			{
				
				request.setCustId(custId);
				request.setStatus(status);
				request.setAcctName(acctName);

			}
			requestMessage.setRequest(request);
		}
		ResponseMessage<EditAcctSevenBussAgentpayResponse> responseMessage = coreServiceInterface.editAcctSevenBussAgentpay(requestMessage);
		logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
		if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
			object.put("result", "SUCCESS");
		}else{
			object.put("result", "FAIL");
		}
		return object.toJSONString();
	  }
   }
