package com.qifenqian.bms.accounting.exception.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenFreeze;
import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans;
import com.qifenqian.bms.accounting.exception.dao.acctseven.mapper.AcctSevenFreezeMapper;
import com.qifenqian.bms.accounting.exception.dao.acctseven.mapper.AcctSevenTransMapper;
import com.qifenqian.bms.accounting.exception.dao.clearbank.bean.ClearBank;
import com.qifenqian.bms.accounting.exception.dao.clearbank.mapper.ClearBankMapper;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkj;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkjTransfer;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.mapper.ClearJgkjMapper;
import com.qifenqian.bms.accounting.exception.dao.clearunionpay.bean.ClearUnionpay;
import com.qifenqian.bms.accounting.exception.dao.clearunionpay.mapper.ClearUnionpayMapper;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeePayEntry;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.dao.KingdeeClearDao;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.mapper.KingdeeClearMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.common.utils.SequenceUtils;
import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeYlResut;
import com.qifenqian.bms.unionPay.tradeylresult.mapper.TradeYlResultMapper;
import com.sevenpay.gateway.jgkj.Head;
import com.sevenpay.gateway.jgkj.IJgkj;
import com.sevenpay.gateway.jgkj.RequestHead;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn20010;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn2001RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn2001.bean.Txn2001ResponseBean;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn20030;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn20031;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn2003RequestBean;
import com.sevenpay.gateway.jgkj.impl.txn2003.bean.Txn2003ResponseBean;
import com.sevenpay.gateway.k3cloud.RequestBean;
import com.sevenpay.gateway.k3cloud.ResponseBean;
import com.sevenpay.gateway.k3cloud.impl.appaybillview.bean.ApPayBillViewRequestBody;
import com.sevenpay.gateway.k3cloud.impl.appaybillview.bean.ApPayBillViewResponseBody;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.stc.gateway.unionpay.impl.query.bean.QueryRequestBean;
import com.stc.gateway.unionpay.impl.query.bean.QueryResponseBean;

/**
 * @project sevenpay-bms-web
 * @fileName QueryResultService.java
 * @author huiquan.ma
 * @date 2015年10月26日
 * @memo
 */
@Service
public class TransQueryService {

	private Logger logger = LoggerFactory.getLogger(TransQueryService.class);

	@Autowired
	private ClearJgkjMapper clearJgkjMapper;
	@Autowired
	private AcctSevenTransMapper acctSevenTransMapper;
	@Autowired
	private AcctSevenFreezeMapper acctSevenFreezeMapper;
	@Autowired
	private ClearUnionpayMapper clearUnionpayMapper;
	@Autowired
	private ClearBankMapper clearBankMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ClearUnionpayMapper clearUnionPayMapper;
	@Autowired
	private KingdeeClearMapper kingdeeClearMapper;
	@Autowired
	private TradeYlResultMapper tradeYlResultMapper;
	@Autowired
	private KingdeeClearDao kingdeeClearDao;
	@Autowired
	@Qualifier("expressPayHttpInvokerProxy")
	private IJgkj expressPay;

	/**
	 * 根据交易报文流水查询对应的交易明细
	 * 
	 * @param recordFlowList
	 * @return
	 */
	public List<TransAction> selectTransListByRecordFlow(List<TransRecordFlow> recordFlowList) {
		List<TransAction> transList = null;
		if (null != recordFlowList && !recordFlowList.isEmpty()) {
			transList = new ArrayList<TransAction>();
			TransAction transBeanTemp = null;
			for (TransRecordFlow recordFlow : recordFlowList) {
				switch (recordFlow.getOperate()) {
				case JGKJ_RECHARGE:
					/** 交广科技充值 CLEAR_JGKJ_RECHARGE **/
					transBeanTemp = clearJgkjMapper.selectJgkjRechargeByFLowId(recordFlow.getId());
					break;
				case JGKJ_RECHARGE_REVOKE: 			/** 交广科技充值撤销 CLEAR_JGKJ_REBACK **/
				case JGKJ_RECHARGE_REFUND: 			/** 交广科技充值退款 CLEAR_JGKJ_REBACK **/
				case JGKJ_PAYMENT_REVOKE: 			/** 交广科技交易- 撤销 CLEAR_JGKJ_REBACK **/
				case JGKJ_PAYMENT_REFUND: 			/** 交广科技交易- 退款 CLEAR_JGKJ_REBACK **/
				case JGKJ_ADJUST: 					/** 交广科技-（调账）退款 CLEAR_JGKJ_REBACK **/
					transBeanTemp = clearJgkjMapper.selectJgkjRebackByFLowId(recordFlow.getId());
					break;
				case JGKJ_PAYMENT: 					/** 交广科技交易-消费 CLEAR_JGKJ_TRADE **/
					transBeanTemp = clearJgkjMapper.selectJgkjTradeByFLowId(recordFlow.getId());
					break;
				case JGKJ_TRANSFER: 				/** 交广科技-转账 CLEAR_JGKJ_TRANSFER **/
				case JGKJ_TRANSFER_REVERSAL: 		/** 交广科技-转账反转 CLEAR_JGKJ_TRANSFER **/
					transBeanTemp = clearJgkjMapper.selectJgkjTransferByFLowId(recordFlow.getId());
					break;
				case JGKJ_CARD_FREEZE: 				/** 交广科技-转账 CLEAR_JGKJ_FREEZE **/
				case JGKJ_CARD_UNFREEZE: 			/** 交广科技-转账 CLEAR_JGKJ_FREEZE **/
					transBeanTemp = clearJgkjMapper.selectJgkjFreezeByFLowId(recordFlow.getId());
					break;
				case SEVEN_CUST_RECHARGE: 			/** 客户交易流水-充值 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_RECHARGE_REVOKE: 	/** 客户交易流水-充值撤销 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_RECHARGE_REFUND: 	/** 客户交易流水-充值退款 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_PAYMENT: 			/** 客户交易流水-消费 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_RECEIVE: 			/** 客户交易流水-收款 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_RECEIVE_REVOKE: 	/** 客户交易流水-收款 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_PAYMENT_REVOKE: 	/** 客户交易流水-消费撤销 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_PAYMENT_REFUND: 	/** 客户交易流水-消费退款 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_WITHDRAW_APPLY: 	/** 客户交易流水-提现申请 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_WITHDRAW: 			/** 客户交易流水-提现 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_WITHDRAW_REVOKE: 	/** 客户交易流水-提现撤销 ACCT_SEVEN_CUST_FLOW **/
				case SEVEN_CUST_ADJUST: 			/** 客户交易流水-（调账）退款 ACCT_SEVEN_CUST_FLOW **/
					transBeanTemp = acctSevenTransMapper.selectCustAccountByFLowId(recordFlow.getId());
					break;
				case SEVEN_CUST_FULL_FREEZE: 		/** 客户冻结流水 ACCT_SEVEN_CUST_FREEZE_FLOW **/
				case SEVEN_CUST_FULL_UNFREEZE: 		/** 客户解冻流水 ACCT_SEVEN_CUST_FREEZE_FLOW **/
					transBeanTemp = acctSevenFreezeMapper.selectCustFreezeByFLowId(recordFlow.getId());
					break;
				case SEVEN_BUSS_RECEIVE: 			/** 商户交易流水-收款 ACCT_SEVEN_BUSS_FLOW **/
				case SEVEN_BUSS_RECEIVE_REVOKE: 	/** 商户交易流水-收款撤销 ACCT_SEVEN_BUSS_FLOW **/
				case SEVEN_BUSS_RECEIVE_REFUND: 	/** 商户交易流水-收款退款 ACCT_SEVEN_BUSS_FLOW **/
				case BUSS_SETTLE_APPLY_RECEIVE: 	/** 商户交易流水-结算申请收款 ACCT_SEVEN_BUSS_FLOW **/
				case BUSS_SETTLE_REVOKE_RECEIVE: 	/** 商户交易流水-结算申请退款 ACCT_SEVEN_BUSS_FLOW **/
				case SEVEN_BUSS_SETTLE: 			/** 商户交易流水-结算 ACCT_SEVEN_BUSS_FLOW **/
				case BUSS_SETTLE_APPLY_PAY: 		/** 商户交易流水-结算付款 ACCT_SEVEN_BUSS_FLOW **/
				case BUSS_SETTLE_REVOKE_PAY: 		/** 商户交易流水-结算撤销 ACCT_SEVEN_BUSS_FLOW **/
					transBeanTemp = acctSevenTransMapper.selectBussAccountByFLowId(recordFlow.getId());
					break;
				case BANK_CLEAR: 					/** 银行清算 CLEAR_BANK **/
					transBeanTemp = clearBankMapper.selectBankClearByFLowId(recordFlow.getId());
					break;

				case SEVEN_INNER_RECEIVE: 			/** 内部账户交易流水 ACCT_SEVEN_INNER_FLOW **/
				case SEVEN_INNER_RECEIVE_REVOKE: 	/** 内部账户交易流水 ACCT_SEVEN_INNER_FLOW **/
				case SEVEN_INNER_RECEIVE_REFUND:
				case SEVEN_INNER_PAYMENT: 			/** 内部账户交易流水 ACCT_SEVEN_INNER_FLOW **/
				case SEVEN_INNER_PAYMENT_REVOKE: 	/** 内部账户交易流水 ACCT_SEVEN_INNER_FLOW **/
					transBeanTemp = acctSevenTransMapper.selectInnerAccountByFLowId(recordFlow.getId());
					break;
				default:
					throw new UnsupportedOperationException("暂不支持的操作步骤" + recordFlow.getOperate());
				}
				// 公共信息填充
				if (null != transBeanTemp) {
					transBeanTemp.setTransRecordFlow(recordFlow);
					transBeanTemp.setTransFlowOperate(recordFlow.getOperate());
					transBeanTemp.setResultStatus(recordFlow.getStatus());
					transBeanTemp.setTransFlowId(recordFlow.getId());
					transList.add(transBeanTemp);
				}
			}
		}
		return transList;
	}
	
	/***
	 * 结果自查
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultTrans(TransAction requestBean) {
		List<TransAction> queryResult = null;
		switch (requestBean.getTransFlowOperate()) {
		case UNIONPAY_PAYMENT:
			queryResult = queryResultUnionPay(requestBean.getTransFlowId());
			break;
		case UNIONPAY_REVOKE:
			queryResult = queryResultUnionPay(requestBean.getTransFlowId());
			break;
		case UNIONPAY_REFUND:
			queryResult = queryResultUnionPay(requestBean.getTransFlowId());
			break;
		case JGKJ_RECHARGE:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_RECHARGE_REVOKE:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_RECHARGE_REFUND:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_PAYMENT:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_PAYMENT_REVOKE:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_PAYMENT_REFUND:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_TRANSFER:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_TRANSFER_REVERSAL:
			queryResult = queryResultJgkj(requestBean);
			break;
		case SEVEN_CUST_RECHARGE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_RECHARGE_REVOKE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_RECHARGE_REFUND:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_PAYMENT:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_RECEIVE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_RECEIVE_REVOKE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_PAYMENT_REVOKE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_PAYMENT_REFUND:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_WITHDRAW_APPLY:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_WITHDRAW:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_WITHDRAW_REVOKE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_BUSS_RECEIVE:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case SEVEN_BUSS_RECEIVE_REVOKE:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case SEVEN_BUSS_RECEIVE_REFUND:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case SEVEN_BUSS_SETTLE:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case BUSS_SETTLE_APPLY_RECEIVE:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case BUSS_SETTLE_APPLY_PAY:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case BUSS_SETTLE_REVOKE_PAY:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case BUSS_SETTLE_REVOKE_RECEIVE:
			queryResult = queryResultBussAccount(requestBean);
			break;
		case BANK_CLEAR:
			queryResult = queryResultBankClear(requestBean);
			break;
		case KINGDEE_WITHDRAW:
			queryResult = queryResultKingClear(requestBean);
			break;
		case KINGDEE_SETTLE:
			queryResult = queryResultKingClear(requestBean);
			break;
		case SEVEN_INNER_RECEIVE:
			queryResult = queryResultInnerAccount(requestBean);
			break;
		case JGKJ_CARD_FREEZE:
			queryResult = queryResultJgkj(requestBean);
			break;
		case JGKJ_CARD_UNFREEZE:
			queryResult = queryResultJgkj(requestBean);
			break;
		case SEVEN_CUST_FULL_FREEZE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		case SEVEN_CUST_FULL_UNFREEZE:
			queryResult = queryResultCustAccount(requestBean);
			break;
		default:
			throw new UnsupportedOperationException("暂不支持的操作步骤" + requestBean.getTransFlowOperate());
		}
		logger.info("queryResultTrans trnasBean :", JSONObject.toJSONString(requestBean, true));
		if (null != queryResult) {
			queryResult.get(0).setTransFlowOperate(requestBean.getTransFlowOperate());
		}
		return queryResult;
	}

	/**
	 * 查询银联交易结果
	 * 
	 * @param queryId
	 * @return
	 */
	public List<TransAction> queryResultUnionPay(String transId) {
		logger.info("*****TransQueryService.queryResultUnionPay：" + transId);

		List<TransAction> queryResult = new ArrayList<TransAction>();
		try {
			TradeYlResut queryBean = new TradeYlResut();
			queryBean.setTransId(transId);
			List<TradeYlResut> resultBean = tradeYlResultMapper.selectTransYlResut(queryBean);
			if (resultBean.size() == 0) {
				return null;
			}
			QueryRequestBean requestBean = new QueryRequestBean();
			requestBean.setSysId("S004");
			requestBean.setOrderId(resultBean.get(0).getTransId());
			requestBean.setTxnTime(resultBean.get(0).getTransSubmitTime());

			logger.info("发送银联报文：" + JSONObject.toJSONString(requestBean));
			QueryResponseBean response = commonService.getIUnionPay().query(requestBean);
			logger.info("银联返回报文：" + JSONObject.toJSONString(response));
			if (null == response) {
				return null;
			}
			ClearUnionpay unionpay = new ClearUnionpay();
			if (Constant.YINLIAN_SUCC.equals(response.getRespCode())) {
				unionpay.setQueryId(response.getQueryId());
				unionpay.setSettleDate(response.getSettleDate());
				unionpay.setSettleCurrencyCode(response.getSettleCurrencyCode());
				unionpay.setRespCode(response.getOrigRespCode());
				unionpay.setRespMsg(response.getOrigRespMsg());
				unionpay.setExchangeRate(response.getExchangeRate());
				unionpay.setExchangeDate(response.getExchangeDate());
				unionpay.setAcctNo(response.getAccNo());
				unionpay.setTraceNo(response.getTraceNo());
				if (null != response.getSettleAmt()) {
					unionpay.setSettleAmt(new BigDecimal(response.getSettleAmt()));
				}
				if (null != response.getTxnAmt()) {
					unionpay.setTxnAmt(new BigDecimal(response.getTxnAmt()));
				}
			} else {
				unionpay.setQueryId("");
				unionpay.setExchangeDate(response.getExchangeDate());
				unionpay.setRespCode(response.getRespCode());
				unionpay.setRespMsg(response.getRespMsg());
				unionpay.setSettleAmt(new BigDecimal("0.00"));
				unionpay.setTxnAmt(new BigDecimal("0.00"));
				unionpay.setAcctNo("");
				unionpay.setSettleDate("");
				unionpay.setSettleCurrencyCode("");
				unionpay.setExchangeRate("");
			}

			unionpay.setOrderId(response.getOrderId());
			unionpay.setMerId(response.getMerId());
			unionpay.setTxnTime(response.getTxnTime());

			queryResult.add(unionpay);
		} catch (Exception e) {
			logger.error("查询银联交易结果异常：" + e);
			return null;
		}
		return queryResult;
	}

	/**
	 * 金蝶结果自查
	 * 
	 * @param clearId
	 * @return
	 */
	public List<TransAction> queryResultKingClear(TransAction requestKingdeeBean) {
		List<TransAction> queryResult = new ArrayList<TransAction>();
		logger.info("queryResultKingClear start...." + requestKingdeeBean.getTransFlowId());
		try {
			KingdeeClear kingdeeClear = kingdeeClearMapper.selectKingdeeClearByClearId(requestKingdeeBean
					.getTransFlowId());
			if (null == kingdeeClear) {
				return null;
			}
			ApPayBillViewRequestBody content = new ApPayBillViewRequestBody();
			{
				content.setCreateOrgId("0");
				content.setNumber(kingdeeClear.getClearId());
				content.setId("");
			}

			RequestBean<ApPayBillViewRequestBody> requestBean = new RequestBean<ApPayBillViewRequestBody>();
			com.sevenpay.gateway.k3cloud.RequestHead head = new com.sevenpay.gateway.k3cloud.RequestHead();
			head.setSysId("S004");
			head.setMsgId(DatetimeUtils.dateSecond());
			requestBean.setHead(head);
			requestBean.setBody(content);

			logger.info("发送金蝶报文：{}",JSONObject.toJSONString(requestBean,true));
			ResponseBean<ApPayBillViewResponseBody> response = commonService.getIKingdeePay()
					.apPayBillView(requestBean);
			logger.info("金蝶返回报文：{}",JSONObject.toJSONString(response,true));
			if (null == response.getBody().getResult().getResult()) {
				kingdeeClear.setStatus("A");
			} else if (response.getBody().getResult().getResult().getPAYBILLENTRY() != null
					&& !StringUtils.isBlank(response.getBody().getResult().getResult().getPAYBILLENTRY().get(0)
							.getBankStatus())) {
				kingdeeClear.setStatus(response.getBody().getResult().getResult().getPAYBILLENTRY().get(0)
						.getBankStatus());
			} else {
				kingdeeClear.setStatus("A");
			}
			kingdeeClear.setfBankMsg(response.getBody().getResult().getResult().getPAYBILLENTRY().get(0).getFBANKMSG());
			kingdeeClear.setfEbMsg(response.getBody().getResult().getResult().getPAYBILLENTRY().get(0).getFEBMSG());
			queryResult.add(kingdeeClear);
		} catch (Exception e) {
			logger.error("查询客户账户信息异常：" + e);
			e.printStackTrace();
			return null;
		}

		return queryResult;
	}

	/**
	 * 查询交广科技交易结果
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultJgkj(TransAction transAction) {
		List<TransAction> queryResult = new ArrayList<TransAction>();
		ClearJgkj clearJgkjTrade = new ClearJgkj();
		switch (transAction.getTransFlowOperate()) {
		case JGKJ_RECHARGE:
			clearJgkjTrade = clearJgkjMapper.selectJgkjRechargeByFLowId(transAction.getTransFlowId());
			break;
		case JGKJ_PAYMENT:
			clearJgkjTrade = clearJgkjMapper.selectJgkjTradeByFLowId(transAction.getTransFlowId());
			break;
		case JGKJ_TRANSFER:
			clearJgkjTrade = clearJgkjMapper.selectJgkjTransferByFLowId(transAction.getTransFlowId());
			break;
		case JGKJ_TRANSFER_REVERSAL:
			clearJgkjTrade = clearJgkjMapper.selectJgkjTransferByFLowId(transAction.getTransFlowId());
			break;
		case JGKJ_CARD_FREEZE:
			clearJgkjTrade = clearJgkjMapper.selectCardNoByTransFlowId(transAction.getTransFlowId());
			break;
		case JGKJ_CARD_UNFREEZE:
			clearJgkjTrade = clearJgkjMapper.selectCardNoByTransFlowId(transAction.getTransFlowId());
			break;
		default:
			clearJgkjTrade = clearJgkjMapper.selectJgkjRebackByFLowId(transAction.getTransFlowId());
			break;
		}

		if (null == clearJgkjTrade) {
			return null;
		}

		ClearJgkj jgkj = new ClearJgkj();
		try {
			if (RequestColumnValues.TransFlowOperate.JGKJ_CARD_FREEZE == transAction.getTransFlowOperate()||
					RequestColumnValues.TransFlowOperate.JGKJ_CARD_UNFREEZE == transAction.getTransFlowOperate()) {

				Txn2001RequestBean request = new Txn2001RequestBean();
				RequestHead head = new RequestHead();
				head.setMerchantSeq(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				head.setTxnDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
				head.setTxnTime(new SimpleDateFormat("HHmmss").format(new Date()));
				head.setSysId("S004");
				Txn20010 txn20010 = new Txn20010();
				txn20010.setCardNo(clearJgkjTrade.getCardNo());
				request.setHead(head);
				request.setBody(txn20010);

				logger.info("卡账户信息查询调用网关请求[{}]", JSONObject.toJSONString(request, true));
				Txn2001ResponseBean response = expressPay.txn2001(request);
				logger.info("卡账户信息查询调用网关返回[{}]", JSONObject.toJSONString(response, true));

				if (null == response || null == response.getBody()) {
					return null;
				}
				if (Constant.JGKJ_NO_RESULT.equals(response.getHead().getErrCode())) {
					jgkj.setRtnCode(response.getHead().getErrCode());
					jgkj.setTransCode(response.getHead().getTxnCode());
					jgkj.setRtnDate(response.getHead().getTxnDate());
					jgkj.setRtnTime(response.getHead().getTxnTime());
					jgkj.setRtnCode(response.getHead().getErrCode());
					jgkj.setRtnInfo("无返回结果");
				} else {
					jgkj.setCardNo(response.getBody().getCardNo());
					jgkj.setTransCode(response.getHead().getTxnCode());
					jgkj.setRtnCode(response.getHead().getErrCode());
					jgkj.setRtnInfo(response.getHead().getErrMsg());
					jgkj.setRtnDate(response.getHead().getTxnDate());
					jgkj.setRtnTime(response.getHead().getTxnTime());
					jgkj.setJGKJCardStatus(response.getBody().getStatus());
					jgkj.setJGKJCardType(response.getBody().getCardType());
				}

			} else {
				Date now = new Date();
				Txn2003RequestBean txn2003Request = new Txn2003RequestBean();
				String nowShortTime = DateFormatUtils.format(now, "HHmmss");
				String nowShortDate = DateFormatUtils.format(now, "yyyyMMdd");
				// 请求头
				RequestHead head = new RequestHead();
				head.setMerchantSeq(SequenceUtils.getMsgId()); // 平台流水号要入库
				head.setTxnDate(nowShortDate); // 日期要入库
				head.setTxnTime(nowShortTime); // 时间要入库
				head.setSysId("S004");
				txn2003Request.setHead(head);

				// 请求体
				Txn20030 body = new Txn20030();
				body.setoMerchantSeq(clearJgkjTrade.getId());
				body.setCardNo(clearJgkjTrade.getCardNo());
				if (transAction.getTransFlowOperate() == RequestColumnValues.TransFlowOperate.JGKJ_TRANSFER) {
					ClearJgkjTransfer transfer = (ClearJgkjTransfer) clearJgkjTrade;
					body.setCardNo(transfer.getDebitCardNo());
				}
				txn2003Request.setBody(body);
				/** 调用网关 **/
				logger.info("交易结果查询网关请求[{}]", JSONObject.toJSONString(txn2003Request, true));
				Txn2003ResponseBean response = expressPay.txn2003(txn2003Request);
				logger.info("交易结果查询网关返回[{}]", JSONObject.toJSONString(response, true));
				/** 组装返回信息 **/
				if (null == response) {
					return null;
				}

				Head responseHandler = response.getHead();
				Txn20031 responseBody = response.getBody();

				/** 无返回结果 **/
				if (Constant.JGKJ_NO_RESULT.equals(responseHandler.getErrCode())) {
					jgkj.setChannelSerialSeq(responseHandler.getPlatformSeq());
					jgkj.setRtnCode(responseHandler.getErrCode());
					jgkj.setTransCode(responseHandler.getTxnCode());
					jgkj.setRtnDate(responseHandler.getTxnDate());
					jgkj.setRtnTime(responseHandler.getTxnTime());
					jgkj.setRtnInfo("无返回结果");
				} else {
					jgkj.setChannelSerialSeq(response.getBody().getPlatformSeq());
					jgkj.setTransCode(responseBody.getTxnCode());
					jgkj.setRtnDate(responseBody.getTxnDate());
					jgkj.setRtnTime(responseBody.getTxnTime());
					jgkj.setCardNo(responseBody.getCardNo());
					jgkj.setRtnCode(responseBody.getErrCode());
					if (null != responseBody.getAmount()) {
						jgkj.setTransAmt(new BigDecimal(responseBody.getAmount()));
					}
					/** 失败操作 **/
					if (Constant.JGKJ_SUCC.equals(responseBody.getErrCode())) {
						jgkj.setRtnInfo("交易成功");
					} else if (Constant.isJgkjFailure(responseBody.getErrCode())) {
						jgkj.setRtnInfo("交易失败");
					}
				}
			}

			queryResult.add(jgkj);
		} catch (Exception e) {
			logger.error("查询交广科技交易结果异常：" + e);
			return null;
		}
		return queryResult;
	}

	/***
	 * 查询客户账户信息
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultCustAccount(TransAction reuqestBean) {
		List<TransAction> queryResult = new ArrayList<TransAction>();
		logger.info("queryResultCustAccount start...." + reuqestBean.getTransFlowId());
		try {
			AcctSevenTrans acctSevenTrans = null;
			AcctSevenFreeze acctSevenFreeze = null;
			switch (reuqestBean.getTransFlowOperate()) {
			case SEVEN_CUST_FULL_FREEZE:
				acctSevenFreeze = acctSevenFreezeMapper.selectCustFreezeByFLowId(reuqestBean.getTransFlowId());
				break;
			case SEVEN_CUST_FULL_UNFREEZE:
				acctSevenFreeze = acctSevenFreezeMapper.selectCustFreezeByFLowId(reuqestBean.getTransFlowId());
				break;
			default:
				acctSevenTrans = acctSevenTransMapper.selectCustAccountByFLowId(reuqestBean.getTransFlowId());
			}

			if (null == acctSevenTrans && acctSevenFreeze == null) {
				return null;
			}
			if (acctSevenTrans != null) {
				queryResult.add(acctSevenTrans);
			}
			if (acctSevenFreeze != null) {
				queryResult.add(acctSevenFreeze);
			}

		} catch (Exception e) {
			logger.error("查询客户账户信息异常：" + e.getMessage());
			return null;
		}

		return queryResult;
	}

	/***
	 * 查询商户账户信息
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultBussAccount(TransAction requestBean) {
		logger.info("queryResultBussAccount start...." + requestBean.getTransFlowId());
		List<TransAction> queryResult = new ArrayList<TransAction>();

		try {
			AcctSevenTrans acctSevenTrans = acctSevenTransMapper
					.selectBussAccountByFLowId(requestBean.getTransFlowId());
			if (null == acctSevenTrans) {
				return null;
			}
			queryResult.add(acctSevenTrans);
		} catch (Exception e) {
			logger.error("查询商户户账户信息异常：" + e.getMessage());
			return null;
		}
		return queryResult;
	}

	/***
	 * 查询内部账户结果
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultInnerAccount(TransAction requestBean) {
		logger.info("queryResultInnerAccount start...." + requestBean.getTransFlowId());
		List<TransAction> queryResult = new ArrayList<TransAction>();
		try {
			AcctSevenTrans acctSevenTrans = acctSevenTransMapper.selectInnerAccountByFLowId(requestBean
					.getTransFlowId());
			if (null == acctSevenTrans) {
				return null;
			}
			queryResult.add(acctSevenTrans);
		} catch (Exception e) {
			logger.error("查询内部户账户信息异常：" + e.getMessage());
			return null;
		}
		return queryResult;
	}

	/***
	 * 查询银行清算
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<TransAction> queryResultBankClear(TransAction requestBean) {
		List<TransAction> queryResult = new ArrayList<TransAction>();
		logger.info("queryResultBankClear start...." + requestBean.getTransFlowId());
		try {
			ClearBank clearBank = clearBankMapper.selectBankClearByFLowId(requestBean.getTransFlowId());
			if (null == clearBank) {
				return null;
			}
			queryResult.add(clearBank);
		} catch (Exception e) {
			logger.error("查询客户账户信息异常：" + e.getMessage());
			return null;
		}

		return queryResult;
	}

	/***
	 * 金蝶交易列表
	 * 
	 * @param transFlowId
	 * @return
	 */
	public List<KingdeePayEntry> queryKingdeeEntryList(String clearId) {
		return kingdeeClearDao.queryKingdeeEntryList(clearId);
	}

}
