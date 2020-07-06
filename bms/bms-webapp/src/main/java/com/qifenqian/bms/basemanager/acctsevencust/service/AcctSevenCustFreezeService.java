package com.qifenqian.bms.basemanager.acctsevencust.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCustFreeze;
import com.qifenqian.bms.basemanager.acctsevencust.mapper.AcctSevenCustFreezeMapper;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.fullfreezecust.FullFreezeAcctSevenCustRequest;
import com.sevenpay.invoke.transaction.fullfreezecust.FullFreezeAcctSevenCustResponse;
import com.sevenpay.invoke.transaction.fullunfreezecust.FullUnfreezeAcctSevenCustRequest;
import com.sevenpay.invoke.transaction.fullunfreezecust.FullUnfreezeAcctSevenCustResponse;

@Service
public class AcctSevenCustFreezeService {

	private static Logger logger = LoggerFactory.getLogger(AcctSevenCustFreezeService.class);
	@Autowired
	private AcctSevenCustFreezeMapper acctSevenCustFreezeMapper;

	@Autowired
	private CommonService commonService;

	/**
	 * 冻结
	 * 
	 * @param acctSevenCust
	 * @return
	 */
	public JSONObject fullFreeze(AcctSevenCust acctSevenCust) {
		JSONObject json = new JSONObject();
		// 入库
		AcctSevenCustFreeze insertBean = new AcctSevenCustFreeze();
		String id = BusinessUtils.getMsgId();
		insertBean.setId(id);
		insertBean.setAcctId(acctSevenCust.getAcctId());
		insertBean.setBrief("冻结");
		insertBean.setCreator(String.valueOf(WebUtils.getUserInfo().getUserId()));
		insertBean.setCustId(acctSevenCust.getCustId());
		insertBean.setMsgType(RequestColumnValues.MsgType.CUST_FULL_FREEZE);
		insertBean.setInstDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		insertBean.setStatus(Constant.ACCOUNT_EDIT_CORE_DEAL);
		acctSevenCustFreezeMapper.insert(insertBean);
		AcctSevenCustFreeze updateBean = new AcctSevenCustFreeze();
		
		ResponseMessage<FullFreezeAcctSevenCustResponse> response = fullFreezeAcctSevenCust(insertBean);
		
		if(null==response){
			json.put("result", "FAIL");
			json.put("message", "调用冻结接口异常，无返回信息");
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}

		else if (RequestColumnValues.RtnResult.SUCCESS==response.getRtnResult()) {
			json.put("result", "SUCCESS");
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_SUCC);
		}
		else if (RequestColumnValues.RtnResult.EXCEPTION==response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用冻结接口异常:"+response.getRtnInfo());
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}else{
			json.put("result", "FAIL");
			json.put("message", "调用冻结接口错误:"+response.getRtnInfo());
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}
		updateBean.setId(id);
		updateBean.setRtnCode(response.getRtnCode().name());
		updateBean.setRtnInfo(response.getRtnInfo());
		updateBean.setRtnDatetime(response.getRtnDatetime());
		updateBean.setRelateId(response.getMsgId());
		acctSevenCustFreezeMapper.update(updateBean);
		return json;

	}

	/**
	 * 冻结
	 * 
	 * @param acctSevenCust
	 * @return
	 */
	public ResponseMessage<FullFreezeAcctSevenCustResponse> fullFreezeAcctSevenCust(AcctSevenCustFreeze freezeBean) {
		ResponseMessage<FullFreezeAcctSevenCustResponse> response = null;

		RequestMessage<FullFreezeAcctSevenCustRequest> request = new RequestMessage<FullFreezeAcctSevenCustRequest>();
		{
			request.setMsgType(freezeBean.getMsgType());
			request.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			request.setReqSerialId(freezeBean.getId());
			request.setReqMsgNum(1);
			request.setReqDatetime(new Date());

			FullFreezeAcctSevenCustRequest fullFreezeAcctSevenCustRequest = new FullFreezeAcctSevenCustRequest();
			{
				fullFreezeAcctSevenCustRequest.setCustId(freezeBean.getCustId());
				fullFreezeAcctSevenCustRequest.setBrief(freezeBean.getBrief());
				fullFreezeAcctSevenCustRequest.setAcctId(freezeBean.getAcctId());
			}
			request.setRequest(fullFreezeAcctSevenCustRequest);
		}

		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		logger.info("冻结调用核心请求[{}]", JSONObject.toJSONString(request, true));
		response = coreServiceInterface.fullFreezeAcctSevenCust(request);
		logger.info("冻结调用核心返回[{}]", JSONObject.toJSONString(response, true));

		if (null == response || null == response.getRtnCode()) {
			return null;
		}
		return response;
	}

	/**
	 * 解冻
	 * 
	 * @param acctSevenCust
	 * @return
	 */
	public JSONObject fullUnfreeze(AcctSevenCust acctSevenCust) {
		JSONObject json = new JSONObject();

		AcctSevenCustFreeze queryBean = new AcctSevenCustFreeze();
		queryBean.setAcctId(acctSevenCust.getAcctId());
		queryBean.setCustId(acctSevenCust.getCustId());
		
		List<AcctSevenCustFreeze> msgIdList = acctSevenCustFreezeMapper.selectMsgId(queryBean);

		if (msgIdList.size()<1) {
			json.put("result", "FAIL");
			json.put("message", "无冻结记录,无法解冻");
			return json;
		}
		if (msgIdList.size()>1) {
			json.put("result", "FAIL");
			json.put("message", "冻结记录异常");
			return json;
		}
		// 入库
		AcctSevenCustFreeze insertBean = new AcctSevenCustFreeze();
		String id = BusinessUtils.getMsgId();
		insertBean.setId(id);
		insertBean.setAcctId(acctSevenCust.getAcctId());
		insertBean.setBrief("解冻");
		insertBean.setCreator(String.valueOf(WebUtils.getUserInfo().getUserId()));
		insertBean.setCustId(acctSevenCust.getCustId());
		insertBean.setMsgType(MsgType.CUST_FULL_UNFREEZE);
		insertBean.setInstDatetime(new Date());
		insertBean.setInstDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		insertBean.setStatus(Constant.ACCOUNT_EDIT_CORE_DEAL);
		acctSevenCustFreezeMapper.insert(insertBean);

		ResponseMessage<FullUnfreezeAcctSevenCustResponse> response = fullUnfreezeAcctSevenCust(msgIdList.get(0).getRelateId(), insertBean);
		
		/** 根据返回结果更新记录表**/
		AcctSevenCustFreeze updateBean = new AcctSevenCustFreeze();
		
		if(null==response){
			json.put("result", "FAIL");
			json.put("message", "调用解冻接口异常，无返回信息");
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}

		if (RequestColumnValues.RtnResult.SUCCESS==response.getRtnResult()) {
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_SUCC);
			json.put("result", "SUCCESS");
		}
		else if (RequestColumnValues.RtnResult.EXCEPTION==response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用解冻接口异常:"+response.getRtnInfo());
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}else{
			json.put("result", "FAIL");
			json.put("message", "调用解冻接口错误:"+response.getRtnInfo());
			updateBean.setStatus(Constant.ACCOUNT_EDIT_CORE_FAIL);
		}

		updateBean.setId(id);
		updateBean.setRtnCode(response.getRtnCode().name());
		updateBean.setRtnInfo(response.getRtnInfo());
		updateBean.setRtnDatetime(response.getRtnDatetime());
		updateBean.setRelateId(response.getMsgId());

		acctSevenCustFreezeMapper.update(updateBean);
		
		return json;

	}

	/**
	 * 解冻
	 * 
	 * @param acctSevenCust
	 * @return
	 */
	public ResponseMessage<FullUnfreezeAcctSevenCustResponse> fullUnfreezeAcctSevenCust(String msgId, AcctSevenCustFreeze unFreezeBean) {
		ResponseMessage<FullUnfreezeAcctSevenCustResponse> response = null;

		RequestMessage<FullUnfreezeAcctSevenCustRequest> request = new RequestMessage<FullUnfreezeAcctSevenCustRequest>();
		{
			request.setMsgType(unFreezeBean.getMsgType());
			request.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			request.setReqSerialId(unFreezeBean.getId());
			request.setReqMsgNum(1);
			request.setReqDatetime(new Date());
			FullUnfreezeAcctSevenCustRequest fullUnfreezeAcctSevenCustRequest = new FullUnfreezeAcctSevenCustRequest();
			{
				fullUnfreezeAcctSevenCustRequest.setCustId(unFreezeBean.getCustId());
				fullUnfreezeAcctSevenCustRequest.setBrief(unFreezeBean.getBrief());
				fullUnfreezeAcctSevenCustRequest.setAcctId(unFreezeBean.getAcctId());
				fullUnfreezeAcctSevenCustRequest.setOriginMsgId(msgId);
			}
			request.setRequest(fullUnfreezeAcctSevenCustRequest);
		}
		SevenpayCoreServiceInterface coreServiceInterface = commonService.getSevenpayCoreServiceInterface();
		logger.info("解冻调用核心请求[{}]", JSONObject.toJSONString(request, true));
		response = coreServiceInterface.fullUnfreezeAcctSevenCust(request);
		logger.info("解冻调用核心返回[{}]", JSONObject.toJSONString(response, true));
		if (null == response || null == response.getRtnCode()) {
			return null;
		}
		return response;
	}

}
