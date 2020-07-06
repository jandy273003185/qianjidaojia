package com.qifenqian.bms.accounting.exception.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.qifenqian.bms.accounting.exception.base.type.DealType;
import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenFreeze;
import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans;
import com.qifenqian.bms.accounting.exception.dao.acctseven.mapper.AcctSevenFreezeMapper;
import com.qifenqian.bms.accounting.exception.dao.acctseven.mapper.AcctSevenTransMapper;
import com.qifenqian.bms.accounting.exception.dao.clearbank.bean.ClearBank;
import com.qifenqian.bms.accounting.exception.dao.clearbank.mapper.ClearBankMapper;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkj;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.bean.ClearJgkjTransfer;
import com.qifenqian.bms.accounting.exception.dao.clearjgkj.mapper.ClearJgkjMapper;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.mapper.KingdeeClearMapper;
import com.qifenqian.bms.accounting.exception.dao.opercharge.mapper.OperChargeMapper;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;
import com.qifenqian.bms.accounting.exception.dao.operfreeze.bean.OperFreeze;
import com.qifenqian.bms.accounting.exception.dao.operfreeze.mapper.OperFreezeMapper;
import com.qifenqian.bms.accounting.exception.dao.operrefund.mapper.OperRefundMapper;
import com.qifenqian.bms.accounting.exception.dao.operrevoke.bean.OperRevoke;
import com.qifenqian.bms.accounting.exception.dao.operrevoke.mapper.OperRevokeMapper;
import com.qifenqian.bms.accounting.exception.dao.opertrade.mapper.OperTradeMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord;
import com.qifenqian.bms.accounting.exception.dao.transrecord.mapper.TransRecordMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.mapper.TransRecordFlowMapper;
import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;
import com.qifenqian.bms.accounting.exception.dao.transyl.dao.TransYlDao;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevencust.mapper.AcctSevenCustMapper;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.sns.redpacket.mapper.RedEnvelopeInfoMapper;
import com.qifenqian.bms.unionPay.tradeylresult.mapper.TradeYlResultMapper;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.TransStatus;
import com.sevenpay.invoke.transaction.exceptiondeal.ExceptionDealRequest;
import com.sevenpay.invoke.transaction.exceptiondeal.ExceptionDealResponse;
import com.stc.gateway.unionpay.impl.consumeundo.bean.ConsumeUndoRequestBean;
import com.stc.gateway.unionpay.impl.consumeundo.bean.ConsumeUndoResponseBean;
import com.stc.gateway.unionpay.impl.refund.bean.RefundRequestBean;
import com.stc.gateway.unionpay.impl.refund.bean.RefundResponseBean;

/**
 * 成功,失败,撤销,重新执行,续作下一步,续作剩余流程,退回当前步骤
 * 
 * @project sevenpay-bms-web
 * @fileName TransConfirmService.java
 * @author huiquan.ma
 * @date 2015年10月27日
 * @memo
 */
@Service
public class TransDealService {

	private Logger logger = LoggerFactory.getLogger(TransDealService.class);

	@Autowired
	@Qualifier("coreHttpInvokerProxy")
	private SevenpayCoreServiceInterface coreHttpInvokerProxy;

	@Autowired
	private ClearJgkjMapper clearJgkjMapper;
	@Autowired
	private TransRecordFlowMapper transRecordFlowMapper;
	@Autowired
	private TransRecordMapper transRecordMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperationExceptionService operationExceptionService;
	@Autowired
	private TradeYlResultMapper tradeYlResultMapper;
	@Autowired
	private DictionaryUtils dictionaryUtils;
	@Autowired
	private AcctSevenCustMapper acctSevenCustMapper;
	@Autowired
	private OperChargeMapper operChargeMapper;
	@Autowired
	private OperTradeMapper operTradeMapper;
	@Autowired
	private OperRevokeMapper operRevokeMapper;
	@Autowired
	private AcctSevenTransMapper acctSevenTransMapper;
	@Autowired
	private TransYlDao transYlDao;
	@Autowired
	private ClearBankMapper clearBankMapper;
	@Autowired
	private KingdeeClearMapper kingdeeClearMapper;
	@Autowired
	private OperRefundMapper operRefundMapper;
	@Autowired
	private OperFreezeMapper operFreezeMapper;
	@Autowired
	private AcctSevenFreezeMapper acctSevenFreezeMapper;
	
	@Autowired
	private  RedEnvelopeInfoMapper redEnvelopeInfoMapper;

	/**
	 * 确认成功 （需要更新会计日期）
	 * 
	 * @param transAction
	 */
	public void confirmSuccess(TransAction transAction) {

		/** 确认成功修改记录 **/
		TransRecordFlow transRecordFlow = new TransRecordFlow();
		transRecordFlow.setStatus(RequestColumnValues.TransStatus.CONFIRM_SUCCESS);
		transRecordFlow.setId(transAction.getTransFlowId());
		String workDate = commonService.getIPlugin().findDictByPath("ACCTING_WORK_DATE");

		/** 金蝶交易确认成功 **/
		KingdeeClear kingdeeClear = new KingdeeClear();
		kingdeeClear.setStatus(TransStatus.CONFIRM_SUCCESS.name());
		kingdeeClear.setWorkDate(workDate);
		kingdeeClear.setClearId(transAction.getTransFlowId());

		/** 银联交易确认成功 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transAction.getTransFlowId());
		updateBean.setYlRespCodeYb("C0");
		updateBean.setWorkDate(workDate);

		/** 交广科技交易确认成功 **/
		ClearJgkj updateJgkjBean = new ClearJgkj();
		updateJgkjBean.setWorkDate(workDate);
		updateJgkjBean.setStatus(TransStatus.CONFIRM_SUCCESS);
		updateJgkjBean.setTransFlowId(transAction.getTransFlowId());

		ClearJgkjTransfer updateTransferBean = new ClearJgkjTransfer();
		updateTransferBean.setWorkDate(workDate);
		updateTransferBean.setStatus(TransStatus.CONFIRM_SUCCESS);
		updateTransferBean.setTransFlowId(transAction.getTransFlowId());

		/** 七分钱交易确认成功 **/
		AcctSevenTrans updateAcctSeven = new AcctSevenTrans();
		updateAcctSeven.setWorkDate(workDate);
		updateAcctSeven.setTransFlowId(transAction.getTransFlowId());

		/** 银行清算确认成功 **/
		ClearBank clearBank = new ClearBank();
		clearBank.setWorkDate(workDate);
		clearBank.setTransFlowId(transAction.getTransFlowId());
		
		AcctSevenFreeze freezeBean = new AcctSevenFreeze();
		freezeBean.setWorkDate(workDate);
		freezeBean.setTransFlowId(transAction.getTransFlowId());

		switch (transAction.getTransFlowOperate()) {
		case KINGDEE_WITHDRAW:
			kingdeeClearMapper.updateKingdeeClear(kingdeeClear);
			break;
		case KINGDEE_SETTLE:
			kingdeeClearMapper.updateKingdeeClear(kingdeeClear);
			break;
		case UNIONPAY_PAYMENT:
			transYlDao.updateTransYl(updateBean);
			break;
		case UNIONPAY_REVOKE:
			transYlDao.updateTransYl(updateBean);
			break;
		case UNIONPAY_REFUND:
			transYlDao.updateTransYl(updateBean);
			break;
		case JGKJ_RECHARGE:
			clearJgkjMapper.updateClearJgkjRecharge(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_RECHARGE_REVOKE:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_RECHARGE_REFUND:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT:
			clearJgkjMapper.updateClearJgkjTrade(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT_REVOKE:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT_REFUND:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_TRANSFER:
			clearJgkjMapper.updateJgkjTransfer(updateTransferBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_TRANSFER_REVERSAL:
			clearJgkjMapper.updateJgkjTransfer(updateTransferBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
			
		case JGKJ_CARD_FREEZE:
			clearJgkjMapper.updateJgkjFreeze(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_CARD_UNFREEZE:
			clearJgkjMapper.updateJgkjFreeze(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE_REVOKE:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE_REFUND:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECEIVE:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT_REVOKE:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT_REFUND:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_WITHDRAW_APPLY:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_WITHDRAW_REVOKE:
			acctSevenTransMapper.updateCustAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_SETTLE:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_APPLY_RECEIVE:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_REVOKE_RECEIVE:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE_REVOKE:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE_REFUND:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_APPLY_PAY:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_REVOKE_PAY:
			acctSevenTransMapper.updateBussAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_INNER_RECEIVE:
			acctSevenTransMapper.updateInnerAccountFlow(updateAcctSeven);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BANK_CLEAR:
			clearBankMapper.updateBankClearFlow(clearBank);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_FULL_FREEZE:
			acctSevenFreezeMapper.updateCustFreezeFlow(freezeBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_FULL_UNFREEZE:
			acctSevenFreezeMapper.updateCustFreezeFlow(freezeBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		default:
			logger.error("确认成功暂不支持的操作步骤" + transAction.getTransFlowOperate());
			throw new UnsupportedOperationException("暂不支持的操作步骤" + transAction.getTransFlowOperate());
		}
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.TransStatus.CONFIRM_SUCCESS.name());
		operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(transAction.getOperationId());
		operationFlow.setTransStep(transAction.getTransFlowOperate().name());
		operationFlow.setDealMemo(transAction.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);

	}

	/**
	 * 确认失败 （不需要更新会计日期）
	 * 
	 * @param transAction
	 */
	public void confirmFailure(TransAction transAction) {

		/** 确认失败修改记录 **/
		TransRecordFlow transRecordFlow = new TransRecordFlow();
		transRecordFlow.setStatus(RequestColumnValues.TransStatus.CONFIRM_FAILURE);
		transRecordFlow.setId(transAction.getTransFlowId());

		/** 金蝶交易确定失敗 **/
		KingdeeClear kingdeeClear = new KingdeeClear();
		kingdeeClear.setStatus(TransStatus.CONFIRM_FAILURE.name());
		kingdeeClear.setClearId(transAction.getTransFlowId());

		/** 银联交易确定失败 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transAction.getTransFlowId());
		updateBean.setYlRespCodeYb("C4");

		/** 交广科技交易确定失败 **/
		ClearJgkj updateJgkjBean = new ClearJgkj();
		updateJgkjBean.setStatus(TransStatus.CONFIRM_FAILURE);
		updateJgkjBean.setTransFlowId(transAction.getTransFlowId());

		ClearJgkjTransfer updateTransferBean = new ClearJgkjTransfer();
		updateTransferBean.setStatus(TransStatus.CONFIRM_FAILURE);
		updateTransferBean.setTransFlowId(transAction.getTransFlowId());

		switch (transAction.getTransFlowOperate()) {
		case KINGDEE_WITHDRAW:
			kingdeeClearMapper.updateKingdeeClear(kingdeeClear);
			break;
		case KINGDEE_SETTLE:
			kingdeeClearMapper.updateKingdeeClear(kingdeeClear);
			break;
		case UNIONPAY_PAYMENT:
			transYlDao.updateTransYl(updateBean);
			break;
		case UNIONPAY_REVOKE:
			transYlDao.updateTransYl(updateBean);
			break;
		case UNIONPAY_REFUND:
			transYlDao.updateTransYl(updateBean);
			break;
		case JGKJ_RECHARGE:
			clearJgkjMapper.updateClearJgkjRecharge(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_RECHARGE_REVOKE:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_RECHARGE_REFUND:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT:
			clearJgkjMapper.updateClearJgkjTrade(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT_REVOKE:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_PAYMENT_REFUND:
			clearJgkjMapper.updateClearJgkjReback(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_TRANSFER:
			clearJgkjMapper.updateJgkjTransfer(updateTransferBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_TRANSFER_REVERSAL:
			clearJgkjMapper.updateJgkjTransfer(updateTransferBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_CARD_FREEZE:
			clearJgkjMapper.updateJgkjFreeze(updateJgkjBean);
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case JGKJ_CARD_UNFREEZE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE_REVOKE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECHARGE_REFUND:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_RECEIVE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT_REVOKE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_PAYMENT_REFUND:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_WITHDRAW_APPLY:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_WITHDRAW_REVOKE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_WITHDRAW:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE_REVOKE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_RECEIVE_REFUND:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_BUSS_SETTLE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_APPLY_RECEIVE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_REVOKE_RECEIVE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_APPLY_PAY:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BUSS_SETTLE_REVOKE_PAY:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_INNER_RECEIVE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case BANK_CLEAR:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_FULL_FREEZE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		case SEVEN_CUST_FULL_UNFREEZE:
			transRecordFlowMapper.updateRecordFlow(transRecordFlow);
			break;
		default:
			logger.error("确认失败暂不支持的操作步骤" + transAction.getTransFlowOperate());
			throw new UnsupportedOperationException("暂不支持的操作步骤" + transAction.getTransFlowOperate());
		}

		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.TransStatus.CONFIRM_FAILURE.name());
		operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(transAction.getOperationId());
		operationFlow.setTransStep(transAction.getTransFlowOperate().name());
		operationFlow.setDealMemo(transAction.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);

	}

	/**
	 * 撤销当前步骤
	 * 
	 * @param transAction
	 * @throws ParseException
	 */
	public JSONObject revoke(TransAction transAction) throws ParseException {

		JSONObject json = new JSONObject();
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		switch (transAction.getTransFlowOperate()) {
		case UNIONPAY_PAYMENT:
			/** 调用银联接口撤销 **/
			TransYl resultBean = transYlDao.selectTransYlByTransId(transAction.getTransFlowId());
			if (null == resultBean) {
				return null;
			}
			ConsumeUndoResponseBean consumeUndoResponse = IUnionPayRevocation(resultBean);

			if (null == consumeUndoResponse) {
				json.put("result", "FAIL");
				json.put("message", "调用撤销异常：无返回结果");
				return json;
			} else if (Constant.YINLIAN_SUCC.equals(consumeUndoResponse.getRespCode())) {
				json.put("result", "SUCCESS");
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

			} else if (consumeUndoResponse.getRespCode().equals("G8")) {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
				operationFlow.setExecuteMemo(consumeUndoResponse.getRespMsg());
				json.put("result", "FAIL");
				json.put("message", "调用撤销异常：" + consumeUndoResponse.getRespMsg());
			} else {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
				operationFlow.setExecuteMemo(consumeUndoResponse.getRespMsg());
				json.put("result", "FAIL");
				json.put("message", "调用撤销失败：" + consumeUndoResponse.getRespMsg());
			}
			break;
		default:
			/** 撤销当前步骤 **/
			ResponseMessage<ExceptionDealResponse> response = exceptionDealNextStep(transAction,
					RequestColumnValues.ExceptionDealStrategy.REVOKE_CURRENT_STEP);
			if (null == response || null == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用撤销异常：无返回结果");
				return json;
			} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
				json.put("result", "SUCCESS");
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

			} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
				json.put("result", "FAIL");
				json.put("message", "调用撤销异常：" + response.getRtnInfo());
			} else {
				json.put("result", "FAIL");
				json.put("message", "调用撤销失败：" + response.getRtnInfo());
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
			}
			break;
		}

		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.ExceptionDealStrategy.REVOKE_CURRENT_STEP.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(transAction.getOperationId());
		operationFlow.setTransStep(transAction.getTransFlowOperate().name());
		operationFlow.setDealMemo(transAction.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);
		return json;
	}

	/***
	 * 重新执行当前步骤
	 * 
	 * @param transAction
	 */

	public JSONObject reExcute(TransAction transAction) {
		JSONObject json = new JSONObject();
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		switch (transAction.getTransFlowOperate()) {
		case KINGDEE_WITHDRAW:
			kingdeeClearMapper.updateWithdrawByReExcute(transAction.getTransFlowId());
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
			json.put("result", "SUCCESS");
			break;
		case KINGDEE_SETTLE:
			kingdeeClearMapper.updateSettleByReExcute(transAction.getTransFlowId());
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
			json.put("result", "SUCCESS");
			break;
		default:
			ResponseMessage<ExceptionDealResponse> response = exceptionDealNextStep(transAction,
					RequestColumnValues.ExceptionDealStrategy.SEQUEL_CURRENT_STEP);
			if (null == response || null == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用重新执行当前步骤异常：无返回结果");
				return json;
			} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
				json.put("result", "SUCCESS");
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

			} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
				json.put("result", "FAIL");
				json.put("message", "调用重新执行当前步骤异常：" + response.getRtnInfo());
			} else {
				json.put("result", "FAIL");
				json.put("message", "调用重新执行当前步骤失败：" + response.getRtnInfo());
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
			}
		}

		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.ExceptionDealStrategy.SEQUEL_CURRENT_STEP.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(transAction.getOperationId());
		operationFlow.setTransStep(transAction.getTransFlowOperate().name());
		operationFlow.setDealMemo(transAction.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);
		return json;
	}

	/**
	 * 退回当前步骤
	 * 
	 * @param requestBean
	 * @return
	 */
	public JSONObject rollback(TransAction requestBean) throws ParseException {
		JSONObject json = new JSONObject();
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		switch (requestBean.getTransFlowOperate()) {
		case UNIONPAY_PAYMENT:
			/** 银联退款 **/
			TransYl resultBean = transYlDao.selectTransYlByTransId(requestBean.getTransFlowId());
			if (null == resultBean) {
				return null;
			}
			RefundResponseBean refundResponse = IUnionPayRollBack(resultBean);
			if (null == refundResponse) {
				json.put("result", "FAIL");
				json.put("message", "调用退款异常：无返回结果");
				return json;
			} else if (Constant.YINLIAN_SUCC.equals(refundResponse.getRespCode())) {
				json.put("result", "SUCCESS");
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

			} else if (refundResponse.getRespCode().equals("G8")) {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
				operationFlow.setExecuteMemo(refundResponse.getRespMsg());
				json.put("result", "FAIL");
				json.put("message", "调用退款异常：" + refundResponse.getRespMsg());
			} else {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
				operationFlow.setExecuteMemo(refundResponse.getRespMsg());
				json.put("result", "FAIL");
				json.put("message", "调用退款失败：" + refundResponse.getRespMsg());
			}
			break;
		default:
			ResponseMessage<ExceptionDealResponse> response = exceptionDealNextStep(requestBean,
					RequestColumnValues.ExceptionDealStrategy.BACK_CURRENT_STEP);

			if (null == response || null == response.getRtnResult()) {
				json.put("result", "FAIL");
				json.put("message", "调用退回当前步骤异常：无返回结果");
				return json;
			} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
				json.put("result", "SUCCESS");
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

			} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
				json.put("result", "FAIL");
				json.put("message", "调用退回当前步骤异常：" + response.getRtnInfo());
			} else {
				json.put("result", "FAIL");
				json.put("message", "调用退回当前步骤失败：" + response.getRtnInfo());
				operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
				operationFlow.setExecuteMemo(response.getRtnInfo());
			}

		}

		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.ExceptionDealStrategy.BACK_CURRENT_STEP.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(requestBean.getOperationId());
		operationFlow.setTransStep(requestBean.getTransFlowOperate().name());
		operationFlow.setDealMemo(requestBean.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);
		return json;
	}

	/**
	 * 续作下一步
	 * 
	 * @param transAction
	 */
	public JSONObject sequelNextStep(TransAction transAction) {
		JSONObject json = new JSONObject();

		ResponseMessage<ExceptionDealResponse> response = exceptionDealNextStep(transAction,
				RequestColumnValues.ExceptionDealStrategy.SEQUEL_NEXT_STEP);
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();
		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用续作下一步异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			json.put("result", "SUCCESS");
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());

		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());
			operationFlow.setExecuteMemo(response.getRtnInfo());
			json.put("result", "FAIL");
			json.put("message", "调用续作下一步异常：" + response.getRtnInfo());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用续作下一步失败：" + response.getRtnInfo());
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
			operationFlow.setExecuteMemo(response.getRtnInfo());
		}

		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.ExceptionDealStrategy.SEQUEL_NEXT_STEP.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(transAction.getOperationId());
		operationFlow.setTransStep(transAction.getTransFlowOperate().name());
		operationFlow.setDealMemo(transAction.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);

		return json;
	}

	/**
	 * 续作整个流程
	 * 
	 * @param requestBean
	 * @return
	 */
	public JSONObject restart(TransAction requestBean) {
		JSONObject json = new JSONObject();
		OperationExceptionFlow operationFlow = new OperationExceptionFlow();

		ResponseMessage<ExceptionDealResponse> response = exceptionDealNextStep(requestBean,
				RequestColumnValues.ExceptionDealStrategy.SEQUEL_WHOLE_PROCESS);
		Map<String, String> recordMap = new HashMap<String, String>();

		if (null == response || null == response.getRtnResult()) {
			json.put("result", "FAIL");
			json.put("message", "调用续作剩余流程异常：无返回结果");
			return json;
		} else if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {

			/** 修改订单状态 **/
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.SUCCESS.name());
			switch (requestBean.getMsgType()) {
			case CUST_RECHARGE:
				com.qifenqian.bms.basemanager.recharge.bean.RechargeBean rechargeBean = new com.qifenqian.bms.basemanager.recharge.bean.RechargeBean();
				rechargeBean.setChargeSn(requestBean.getOperationId());
				rechargeBean.setCoreSn(requestBean.getMsgId());
				rechargeBean.setChargeNetpayState(Constant.STATE_SUCCESS);
				operChargeMapper.updateChargeBillNetpayState(rechargeBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case CUST_RECHARGE_REVOKE:
				OperRevoke rechargeRevokeBean = new OperRevoke();
				rechargeRevokeBean.setOrderId(requestBean.getOperationId());
				rechargeRevokeBean.setRevokeStatus(Constant.PAYMENT_REVOKE_CORE_SUCC);
				rechargeRevokeBean.setCoreSn(requestBean.getMsgId());
				operRevokeMapper.updateRechargeRevoke(rechargeRevokeBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case CUST_TRANSFER:
				TdTradeBillMainVO transferUpdateBean = new TdTradeBillMainVO();
				transferUpdateBean.setOrderId(requestBean.getOperationId());
				transferUpdateBean.setOrderState(Constant.STATE_SUCCESS);
				transferUpdateBean.setCoreSn(requestBean.getMsgId());
				operTradeMapper.updateBillMainOrderState(transferUpdateBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case CUST_TRANSFER_REVOKE:
				OperRevoke transferRevokeBean = new OperRevoke();
				transferRevokeBean.setOrderId(requestBean.getOperationId());
				transferRevokeBean.setRevokeStatus(Constant.STATE_SUCCESS);
				transferRevokeBean.setCoreSn(requestBean.getMsgId());
				operRevokeMapper.updateTransferRevokeState(transferRevokeBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case BALANCE_PAYMENT:
				TdTradeBillMainVO balanceUpdateBean = new TdTradeBillMainVO();
				balanceUpdateBean.setOrderId(requestBean.getOperationId());
				balanceUpdateBean.setOrderState(Constant.STATE_SUCCESS);
				balanceUpdateBean.setCoreSn(requestBean.getMsgId());
				operTradeMapper.updateBillMainOrderState(balanceUpdateBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case BALANCE_PAYMENT_REVOKE:
				OperRevoke balanceRevoke = new OperRevoke();
				balanceRevoke.setRevokeStatus(Constant.STATE_SUCCESS);
				balanceRevoke.setOrderId(requestBean.getOperationId());
				balanceRevoke.setCoreSn(requestBean.getMsgId());
				operRevokeMapper.updatePaymentRevoke(balanceRevoke);
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				
				break;
			case BALANCE_PAYMENT_REFUND:
				RefundBill balanceRefund = new RefundBill();
				balanceRefund.setRefundState(Constant.STATE_SUCCESS);
				balanceRefund.setCoreSn(requestBean.getMsgId());
				balanceRefund.setOrderId(operRefundMapper.selectOrderIdById(requestBean.getOperationId())
						.getOrderId());
				operRefundMapper.updateRefundOrderState(balanceRefund);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case BANK_CARD_PAYMENT:
				TdTradeBillMainVO bankCardUpdateBean = new TdTradeBillMainVO();
				bankCardUpdateBean.setOrderId(requestBean.getOperationId());
				bankCardUpdateBean.setOrderState(Constant.STATE_SUCCESS);
				bankCardUpdateBean.setChargeNetpayState(Constant.STATE_SUCCESS);
				bankCardUpdateBean.setCoreSn(requestBean.getMsgId());
				operTradeMapper.updateBillMainOrderState(bankCardUpdateBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case RED_PACKET_PAYMENT_REFUND:
				RefundBill redPacketRefund = new RefundBill();
				redPacketRefund.setRefundState(Constant.STATE_SUCCESS);
				redPacketRefund.setCoreSn(requestBean.getMsgId());
				redPacketRefund.setOrderId(operRefundMapper.selectOrderIdById(requestBean.getOperationId()).getOrderId());
				operRefundMapper.updateRefundOrderState(redPacketRefund);
				
				/**更新红包**/
				RedEnvelopeInfo redEnvelopeInfo =new RedEnvelopeInfo();
				RedEnvelopeInfo queryBean = redEnvelopeInfoMapper.selectRedEnvelopeInfoRefund(operRefundMapper.selectOrderIdById(requestBean.getOperationId())
						.getOrderId());
				if(queryBean!=null){
					redEnvelopeInfo.setRedEnvId(queryBean.getRedEnvId());
					redEnvelopeInfo.setExpiredBalProcStatus(Constant.REFUND_STATE_SUCCESS);
					redEnvelopeInfoMapper.updateRedEnvelopeInfo(redEnvelopeInfo);
				}
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case BANK_CARD_PAYMENT_REVOKE:
				OperRevoke bankCardRevoke = new OperRevoke();
				bankCardRevoke.setRevokeStatus(Constant.PAYMENT_REVOKE_CORE_SUCC);
				bankCardRevoke.setOrderId(requestBean.getOperationId());
				bankCardRevoke.setCoreSn(requestBean.getMsgId());
				operRevokeMapper.updatePaymentRevoke(bankCardRevoke);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case BANK_CARD_PAYMENT_REFUND:
				RefundBill bankCardRefund = new RefundBill();
				bankCardRefund.setRefundState(Constant.STATE_SUCCESS);
				bankCardRefund.setCoreSn(requestBean.getMsgId());
				bankCardRefund.setOrderId(operRefundMapper.selectOrderIdById(requestBean.getOperationId())
						.getOrderId());
				operRefundMapper.updateRefundOrderState(bankCardRefund);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case CUST_FULL_FREEZE:
				OperFreeze freezeBean = new OperFreeze();
				freezeBean.setFreezeStatus(Constant.STATE_SUCCESS);
				freezeBean.setRelateId(requestBean.getMsgId());
				freezeBean.setId(requestBean.getOperationId());
				operFreezeMapper.updateCustFreezeStatus(freezeBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			case CUST_FULL_UNFREEZE:
				OperFreeze unFreezeBean = new OperFreeze();
				unFreezeBean.setFreezeStatus(Constant.STATE_SUCCESS);
				unFreezeBean.setRelateId(requestBean.getMsgId());
				unFreezeBean.setId(requestBean.getOperationId());
				operFreezeMapper.updateCustFreezeStatus(unFreezeBean);
				
				/** 修改核心trans_record **/
				recordMap.put("orderStatus", Constant.STATE_SUCCESS);
				recordMap.put("orderId", requestBean.getOperationId());
				updateTransRecordStatus(recordMap);
				break;
				
			default:
				throw new UnsupportedOperationException("暂不支持的业务类型：[" + requestBean.getMsgType() + "]");
			}
			json.put("result", "SUCCESS");
		} else if (RequestColumnValues.RtnResult.EXCEPTION == response.getRtnResult()) {
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.EXCEPTION.name());

			operationFlow.setExecuteMemo(response.getRtnInfo());
			json.put("result", "FAIL");
			json.put("message", "调用续作剩余流程异常：" + response.getRtnInfo());
		} else {
			json.put("result", "FAIL");
			json.put("message", "调用续作剩余流程失败：" + response.getRtnInfo());
			operationFlow.setExecuteStatus(RequestColumnValues.RtnResult.FAILURE.name());
			operationFlow.setExecuteMemo(response.getRtnInfo());
		}

		/**
		 * 处理结果
		 * 重启RESTART/回退ROLLBACK/终止END/确认成功CONFIRM_SUCCESS/确认失败CONFIRM_FAILURE
		 * /撤销REVOKE/重执行RE_EXECUTE
		 **/
		operationFlow.setDealResult(RequestColumnValues.ExceptionDealStrategy.SEQUEL_WHOLE_PROCESS.name());
		operationFlow.setDealType(DealType.TRANS.name());
		operationFlow.setOperationId(requestBean.getOperationId());
		operationFlow.setTransStep(requestBean.getTransFlowOperate().name());
		operationFlow.setDealMemo(requestBean.getOpinion());
		operationExceptionService.insertOperationExceptionFlow(operationFlow);
		return json;
	}

	/**
	 * 银联交易撤销
	 * 
	 * @param transBean
	 * @return
	 */
	public ConsumeUndoResponseBean IUnionPayRevocation(TransYl transBean) throws ParseException {

		ConsumeUndoRequestBean request = new ConsumeUndoRequestBean();
		String transId = GenSN.getOperateID();
		String transSubmitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setSysId("S004");
		request.setOrderId(transId);
		request.setTxnTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		if (null == transBean.getTransAmt()) {
			return null;
		}
		request.setTxnAmt(transBean.getTransAmt().toString());
		request.setOrigQryId(transBean.getQueryId());

		/** 记录 **/
		transBean.setTransId(transId);
		transBean.setTransSubmitTime(transSubmitTime);
		transBean.setTransType("REVOCATION");
		transBean.setRevokeQueryId(transBean.getQueryId());
		transBean.setWorkDate(commonService.getIPlugin().findDictByPath("ACCTING_WORK_DATE"));
		transYlDao.insertTransYl(transBean);

		ConsumeUndoResponseBean response = null;

		logger.info("调用银联交易撤销发送报文：" + JSONObject.toJSONString(request));
		response = commonService.getIUnionPay().consumeUndo(request);
		logger.info("调用银联交易撤销返回报文：" + JSONObject.toJSONString(response));
		if (null == response) {
			return null;
		}
		/** 修改返回 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transId);
		updateBean.setYlRespCodeTb(response.getRespCode());
		updateBean.setYlRespTimeTb(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		updateBean.setQueryId(response.getQueryId());
		updateBean.setBizType(response.getBizType());
		updateBean.setMerId(response.getMerId());
		updateBean.setTxnType(response.getTxnType());
		updateBean.setTxnSubType(response.getTxnSubType());
		updateBean.setAccessType(response.getAccessType());
		transYlDao.updateTransYl(updateBean);
		return response;
	}

	/**
	 * 银联退款接口
	 * 
	 * @param transBean
	 * @return
	 */
	public RefundResponseBean IUnionPayRollBack(TransYl transBean) throws ParseException {

		RefundRequestBean request = new RefundRequestBean();
		request.setSysId("S004");
		String transId = GenSN.getOperateID();
		String transSubmitTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setOrderId(transId);
		request.setTxnTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

		if (null == transBean.getTransAmt()) {
			return null;
		}
		request.setTxnAmt(transBean.getTransAmt().toString());
		request.setOrigQryId(transBean.getQueryId());

		/** 记录 **/
		transBean.setTransId(transId);
		transBean.setTransSubmitTime(transSubmitTime);
		transBean.setTransType("REFUND");
		transBean.setRevokeQueryId(transBean.getQueryId());
		transBean.setWorkDate(commonService.getIPlugin().findDictByPath("ACCTING_WORK_DATE"));
		transYlDao.insertTransYl(transBean);

		RefundResponseBean response = null;

		logger.info("调用银联退款发送报文：" + JSONObject.toJSONString(request));
		response = commonService.getIUnionPay().refund(request);
		logger.info("调用银联退款返回报文：" + JSONObject.toJSONString(response));
		if (null == response) {
			return null;
		}

		/** 修改返回 **/
		TransYl updateBean = new TransYl();
		updateBean.setTransId(transId);
		updateBean.setYlRespCodeTb(response.getRespCode());
		updateBean.setYlRespTimeTb(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		updateBean.setQueryId(response.getQueryId());
		updateBean.setBizType(response.getBizType());
		updateBean.setMerId(response.getMerId());
		updateBean.setTxnType(response.getTxnType());
		updateBean.setTxnSubType(response.getTxnSubType());
		updateBean.setAccessType(response.getAccessType());
		transYlDao.updateTransYl(updateBean);

		return response;
	}

	/***
	 * 调用核心 (撤销,重新执行,续作下一步,续作剩余流程,退回当前步骤)
	 * 
	 * @param transAction
	 * @param dealStrategy
	 * @return
	 */
	public ResponseMessage<ExceptionDealResponse> exceptionDealNextStep(TransAction transAction,
			RequestColumnValues.ExceptionDealStrategy dealStrategy) {
		ResponseMessage<ExceptionDealResponse> response = null;

		RequestMessage<ExceptionDealRequest> requestMessage = new RequestMessage<ExceptionDealRequest>();
		requestMessage.setMsgType(RequestColumnValues.MsgType.EXCEPTION_DEAL);
		requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
		requestMessage.setReqSerialId(DatetimeUtils.datetime());
		requestMessage.setReqMsgNum(1);
		requestMessage.setReqDatetime(new Date());
		
		ExceptionDealRequest request = new ExceptionDealRequest();
		{
			request.setDealMsgId(transAction.getMsgId());
			request.setDealMsgType(transAction.getMsgType());
			request.setDealStep(transAction.getTransFlowOperate());
			request.setDealStrategy(dealStrategy);
			request.setBrief(transRecordFlowMapper.selectRecordFlowById(transAction.getTransFlowId()).getBrief());
		}
		requestMessage.setRequest(request);

		logger.info("exceptionDealNextStep 发送报文：" + JSONObject.toJSONString(requestMessage));
		response = coreHttpInvokerProxy.exceptionDeal(requestMessage);
		logger.info("exceptionDealNextStep 返回报文：" + JSONObject.toJSONString(response));

		return response;
	}

	
	/**
	 * 判断是否隔日
	 * 
	 * @param transTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean isOutDate(String transTime) throws ParseException {
		boolean falg = false;

		String year = transTime.substring(0, 4);
		String month = transTime.substring(4, 6);
		String day = transTime.substring(6, 8);
		String transSubmitTime = year + "-" + month + "-" + day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createTime = sdf.parse(transSubmitTime);
		if (DateFormatUtils.format(DateUtils.addDays(new Date(), 0), "yyyy-MM-dd").compareTo(
				DateFormatUtils.format(createTime, "yyyy-MM-dd")) > 0) {
			falg = true;
		} else {
			falg = false;
		}
		return falg;
	}
	
	/***
	 * 修改核心流水状态
	 */
	public void updateTransRecordStatus(Map<String, String> recordMap) {

		String orderStatusCode = recordMap.get("orderStatus");
		String reqSerialId = recordMap.get("orderId");
		TransRecord updateBean = new TransRecord();
		updateBean.setReqSerialId(reqSerialId);
		if (orderStatusCode.equals(Constant.STATE_SUCCESS)) {
			updateBean.setStatus(RequestColumnValues.MsgStatus.SUCCESS);
		} else {
			updateBean.setStatus(RequestColumnValues.MsgStatus.FAILURE);
		}
		transRecordMapper.updateTransRecordStatus(updateBean);
	}
}
