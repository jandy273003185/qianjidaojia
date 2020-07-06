package com.qifenqian.bms.busswithdrawbill.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.busswithdrawbill.bean.TdBussWithdrawBill;
import com.qifenqian.bms.busswithdrawbill.mapper.BussWithdrawBillMapper;
import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.withdrawapplybuss.WithdrawApplyBussRequest;
import com.sevenpay.invoke.transaction.withdrawapplybuss.WithdrawApplyBussResponse;

@Service
public class BussWithdrawBillService {

	private static Logger logger = LoggerFactory.getLogger(BussWithdrawBillService.class);
	@Autowired
	private BussWithdrawBillMapper mapper;

	@Autowired
	private SevenpayCoreServiceInterface coreServiceInterface;

	public void insertWithdrawBill(TdBussWithdrawBill withdrawBill) {
		mapper.insertWithdrawBill(withdrawBill);
	}

	public String selectBatchBank(String custId) {
		return mapper.selectBatchBank(custId);
	}

	public void merchantsWithdrawal(CommerciaBalance commerciaBalance) throws Exception {
		
		try {
			TdBussWithdrawBill withdrawBill = new TdBussWithdrawBill();
			withdrawBill.setWithdrawSn(BusinessUtils.getMsgId());
			withdrawBill.setCustId(commerciaBalance.getCustId());
			withdrawBill.setBankAcctNoInternal(commerciaBalance.getCacctId());
			withdrawBill.setCustName(commerciaBalance.getBankCardName());
			withdrawBill.setWithdrawAcctType("SEVEN_BUSS");
			withdrawBill.setBankCode(commerciaBalance.getBankCardNo());
			String batch_name = selectBatchBank(commerciaBalance.getCustId());
			String bankName = commerciaBalance.getBankName();
			if (null != batch_name) {
				bankName = bankName + batch_name;
			}
			withdrawBill.setBankName(bankName);
			withdrawBill.setBankAcctName(commerciaBalance.getAcctName());
			withdrawBill.setCurrCode("CNY");
			withdrawBill.setWithdrawAmt(commerciaBalance.getPartbalance2());
			withdrawBill.setWithdrawType("2");
			withdrawBill.setWithdrawState("01");
			withdrawBill.setSubmitTime(new Date());
			withdrawBill.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			withdrawBill.setVerificationState("01");
			
			RequestMessage<WithdrawApplyBussRequest> requestMessage = new RequestMessage<WithdrawApplyBussRequest>();
			{
				requestMessage.setMsgType(MsgType.BUSS_WITHDRAW_APPLY);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
				requestMessage.setReqSerialId(DatetimeUtils.datetime());
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());

				WithdrawApplyBussRequest request = new WithdrawApplyBussRequest();
				{
					request.setCustId(commerciaBalance.getCustId());
					request.setAcctId(commerciaBalance.getAcctId());
					request.setOperateType(RequestColumnValues.WithdrawApplyOperate.APPLY);
					request.setCurrCode(RequestColumnValues.CurrCode.CNY);
					request.setWithdrawAmt(commerciaBalance.getPartbalance2());
					request.setBrief("提现申请");
				}
				requestMessage.setRequest(request);
			}
			
			logger.info("==========>>request:" + JSONObject.toJSONString(requestMessage, true));
			ResponseMessage<WithdrawApplyBussResponse> responseMessage = coreServiceInterface.withdrawApplyBuss(requestMessage);
			logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage, true));
			
			if(RequestColumnValues.RtnResult.SUCCESS != responseMessage.getRtnResult()){
				throw new IllegalAccessException(responseMessage.getRtnInfo());
			}
			String msgId = responseMessage.getMsgId();
			withdrawBill.setCoreSn(msgId);
			withdrawBill.setCoreReturnCode(responseMessage.getRtnCode().name());
			withdrawBill.setCoreReturnMsg(responseMessage.getRtnInfo());
			withdrawBill.setResultReturnTime(DateUtils.getDateTimeStr(responseMessage.getRtnDatetime()).toString());
			insertWithdrawBill(withdrawBill);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
