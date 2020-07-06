package com.qifenqian.bms.accounting.exception.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.exception.OperationExceptionPath;
import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.qifenqian.bms.accounting.exception.base.type.DealResult;
import com.qifenqian.bms.accounting.exception.base.type.OperationExceptionStatus;
import com.qifenqian.bms.accounting.exception.dao.acctseven.bean.AcctSevenTrans;
import com.qifenqian.bms.accounting.exception.dao.acctseven.mapper.AcctSevenTransMapper;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeeClear;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.mapper.KingdeeClearMapper;
import com.qifenqian.bms.accounting.exception.dao.operabusssettle.mapper.BussSettleMapper;
import com.qifenqian.bms.accounting.exception.dao.opercharge.mapper.OperChargeMapper;
import com.qifenqian.bms.accounting.exception.dao.opercustwithdraw.bean.CustWithdrawBean;
import com.qifenqian.bms.accounting.exception.dao.opercustwithdraw.mapper.CustWithdrawMapper;
import com.qifenqian.bms.accounting.exception.dao.operdeal.bean.OperationException;
import com.qifenqian.bms.accounting.exception.dao.operfreeze.bean.OperFreeze;
import com.qifenqian.bms.accounting.exception.dao.operfreeze.mapper.OperFreezeMapper;
import com.qifenqian.bms.accounting.exception.dao.operrefund.mapper.OperRefundMapper;
import com.qifenqian.bms.accounting.exception.dao.operrevoke.bean.OperRevoke;
import com.qifenqian.bms.accounting.exception.dao.operrevoke.mapper.OperRevokeMapper;
import com.qifenqian.bms.accounting.exception.dao.opertrade.bean.OperTrade;
import com.qifenqian.bms.accounting.exception.dao.opertrade.mapper.OperTradeMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord;
import com.qifenqian.bms.accounting.exception.dao.transrecord.mapper.TransRecordMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.mapper.TransRecordFlowMapper;
import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;
import com.qifenqian.bms.accounting.exception.dao.transyl.mapper.TransYlMapper;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperMaster;
import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.platform.common.utils.ReflectUtils;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.sns.redpacket.mapper.RedEnvelopeInfoMapper;
import com.qifenqian.bms.sns.redpacketdetail.bean.RedPacketDetail;
import com.qifenqian.bms.sns.redpacketdetail.mapper.RedPacketDetailMapper;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName OperationDealService.java
 * @author huiquan.ma
 * @date 2015年10月27日
 * @memo
 */
@Service
public class OperationDealService {

	private static Logger logger = LoggerFactory.getLogger(OperationDealService.class);

	@Autowired
	@Qualifier("coreHttpInvokerProxy")
	private SevenpayCoreServiceInterface coreHttpInvokerProxy;
	@Autowired
	private OperChargeMapper operChargeMapper;
	@Autowired
	private OperRefundMapper operRefundMapper;
	@Autowired
	private CustWithdrawMapper custWithdrawMapper;
	@Autowired
	private TransYlMapper transYlMapper;
	@Autowired
	private TransQueryService transQueryService;
	@Autowired
	private TransRecordFlowMapper transRecordFlowMapper;
	@Autowired
	private OperTradeMapper operTradeMapper;
	
	@Autowired
	private OrderMapperMaster orderMapperMaster;
	
	@Autowired
	private AcctSevenTransMapper acctSevenTransMapper;
	
	@Autowired
	private TransRecordMapper transRecordMapper;
	@Autowired
	private OperationExceptionService operationExceptionService;
	@Autowired
	private KingdeeClearMapper kingdeeClearMapper;
	@Autowired
	private BussSettleMapper bussSettleMapper;
	@Autowired
	private OperRevokeMapper operRevokeMapper;
	@Autowired
	private OperFreezeMapper operFreezeMapper;

	@Autowired
	private RedEnvelopeInfoMapper tedEnvelopeInfoMapper;

	@Autowired
	private RedPacketDetailMapper redPacketDetailMapper;
 
	/**
	 * 获取异常处理明细页面视图
	 * 
	 * @param requestBean
	 * @return
	 */
	public ModelAndView selectOperationDetail(Operation requestBean) {
		if (null == requestBean) {
			throw new IllegalArgumentException("请求bean为空");
		}
		if (StringUtils.isBlank(requestBean.getOperId())) {
			throw new IllegalArgumentException("业务编号为空");
		}
		if (null == requestBean.getOperType()) {
			throw new IllegalArgumentException("业务类型为空");
		}
		ModelAndView modelAndView = new ModelAndView();
		/** 跳转视图 **/
		String viewName = null;

		/** 操作对象 **/
		Operation operation = null;

		List<TransAction> transList = new ArrayList<TransAction>();

		switch (requestBean.getOperType()) {
		case CUST_RECHARGE:
			/** detailCustRecharge.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_CUST_RECHARGE;
			/** TD_CHARGE_BILL **/
			operation = operChargeMapper.selectChargeBillBySn(requestBean.getOperId());
			/** 业务系统银联支付信息 TD_TRANS_YL **/
			List<TransYl> transYlRecharge = transYlMapper.selectBySn(requestBean.getOperId());
			if (transYlRecharge.size() > 0) {
				/** 银联支付 **/
				transList.addAll(transYlRecharge);
			}
			break;
		case RED_PACKET_RECHARGE:
			/** detailCustRecharge.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_RED_PACKET_RECHARGE;
			/** TD_CHARGE_BILL **/
			operation = operChargeMapper.selectChargeBillBySn(requestBean.getOperId());
			break;
		case CUST_RECHARGE_REVOKE:
			/** detailPaymentRevoke.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_DETAIL_RECHARGE_REVOKE;

			operation = operRevokeMapper.selectRechargeRevokeById(requestBean.getOperId());
			List<TransYl> rechargeRevoke = transYlMapper.selectBySn(requestBean.getOperId());
			if (rechargeRevoke.size() > 0) {
				/** 银联支付 **/
				transList.addAll(rechargeRevoke);
			}
			break;
		case CUST_TRANSFER:
			/** detailCustTransfer.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_CUST_TRANSFER;
			/** TD_REFUND_BILL **/
			operation = operTradeMapper.selectTradeBillById(requestBean.getOperId());
			break;

		case CUST_TRANSFER_REVOKE:
			/** detailCustTransfer.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_CUST_TRANSFER_REVOKE;
			/** TD_REFUND_BILL **/
			operation = operRevokeMapper.selectTransferRevokeById(requestBean.getOperId());
			break;

		case BALANCE_PAYMENT:
			/** detailBalancePayment.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_BALANCE_PAYMENT;
			/** TD_TRADE_BILL_MAIN **/
			operation = operTradeMapper.selectTradeBillById(requestBean.getOperId());
			break;

		case RED_PACKET_PAYMENT:
			/** detailRedPacketPayment.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_RED_PACKET_PAYMENT;
			/** TD_TRADE_BILL_MAIN **/
			operation = operTradeMapper.selectTradeBillById(requestBean.getOperId());
			break;

		case BALANCE_PAYMENT_REFUND:
			/** detailBankCardPaymentRefund.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_PAYMENT_REFUND;
			/** TD_REFUND_BILL **/
			operation = operRefundMapper.selectRefundBillById(requestBean.getOperId());
			break;

		case RED_PACKET_PAYMENT_REFUND:
			/** detailRedPacketPaymentRefund.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_RED_PACKET_PAYMENT_REFUND;
			/** TD_REFUND_BILL **/
			operation = operRefundMapper.selectRefundBillById(requestBean.getOperId());
			break;

		case BANK_CARD_PAYMENT:
			/** detailBankCardPayment.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_BANK_CARD_PAYMENT;
			/** TD_TRADE_BILL_MAIN **/
			operation = operTradeMapper.selectTradeBillById(requestBean.getOperId());
			/** 业务系统银联支付信息 TD_TRANS_YL **/
			List<TransYl> transYlPayment = transYlMapper.selectBySn(requestBean.getOperId());
			if (transYlPayment.size() > 0) {
				/** 银联支付 **/
				transList.addAll(transYlPayment);
			}
			break;
		case BANK_CARD_PAYMENT_REFUND:
			/** detailBalancePaymentRefund.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_PAYMENT_REFUND;
			/** TD_REFUND_BILL **/
			operation = operRefundMapper.selectRefundBillById(requestBean.getOperId());

			/*
			 * List<TransYl> transYlPaymentRefund =
			 * transYlMapper.selectBySn(requestBean.getOperId()); if
			 * (transYlPaymentRefund.size() > 0) {
			 * transList.addAll(transYlPaymentRefund); }
			 */
			break;
		case BALANCE_PAYMENT_REVOKE:
			/** detailPaymentRevoke.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_PAYMENT_REVOKE;
			operation = operRevokeMapper.selectRevokeBillById(requestBean.getOperId());
			break;
		case BANK_CARD_PAYMENT_REVOKE:
			/** detailPaymentRevoke.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_PAYMENT_REVOKE;
			operation = operRevokeMapper.selectRevokeBillById(requestBean.getOperId());
			List<TransYl> transYlBankCardRevoke = transYlMapper.selectBySn(requestBean.getOperId());
			if (transYlBankCardRevoke.size() > 0) {
				/** 银联支付 **/
				transList.addAll(transYlBankCardRevoke);
			}
			break;
		case CUST_WITHDRAW_APPLY:
			/** detailCustWithdrawApply.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_CUST_WITHDRAW_APPLY;
			/** TD_WITHDRAW_BILL **/
			operation = custWithdrawMapper.selectCustWithdrawBySn(requestBean.getOperId());
			break;
		case CUST_WITHDRAW:
			/** detailCustWithdraw.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_CUST_WITHDRAW;
			/** TD_WITHDRAW_BILL,TDWITHDRAW_CHILD_BILL **/
			operation = custWithdrawMapper.selectCustWithdrawChildByWithdrawReqserialid(requestBean.getOperId());

			List<KingdeeClear> withdrawKingdeeClear = kingdeeClearMapper.selectKingdeeClearByOperaId(requestBean.getOperId());

			if (withdrawKingdeeClear.size() > 0) {
				/** 银联支付 **/
				transList.addAll(withdrawKingdeeClear);
			}
			break;
		case BUSS_SETTLE_APPLY:
			/** detailBussSettleApply.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_BUSS_SETTLE_APPLY;
			operation = bussSettleMapper.selectBussSettleApplyById(requestBean.getOperId());
			List<KingdeeClear> settleKingdeeClear = kingdeeClearMapper.selectKingdeeClearByOperaId(requestBean.getOperId());

			if (settleKingdeeClear.size() > 0) {
				/** 银联支付 **/
				transList.addAll(settleKingdeeClear);
			}
			break;
		case BUSS_SETTLE:
			/** detailBussSettle.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_OPERATION_BUSS_SETTLE;
			operation = bussSettleMapper.selectBussSettleApplyById(requestBean.getOperId());
			break;
		case CUST_FULL_FREEZE:
			/** detailCustFreeze.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_CUST_FREEZE;
			/** ACCT_SEVEN_CUST_FREEZE **/
			operation = operFreezeMapper.selectCustFreezeById(requestBean.getOperId());
			break;
		case CUST_FULL_UNFREEZE:
			/** detailCustFreeze.jsp **/
			viewName = OperationExceptionPath.BASE + OperationExceptionPath.DETAIL_CUST_FREEZE;
			/** ACCT_SEVEN_CUST_FREEZE **/
			operation = operFreezeMapper.selectCustFreezeById(requestBean.getOperId());
			break;
		default:
			throw new UnsupportedOperationException("暂不支持的业务类型：[" + requestBean.getOperType() + "," + ReflectUtils.getDesc(requestBean.getOperType())
					+ "]");
		}
		operation.setOperId(requestBean.getOperId());
		operation.setOperType(requestBean.getOperType());
		operation.setStatus(requestBean.getStatus());

		TransRecord selectBean = new TransRecord();

		/** 业务请求流水号 **/
		selectBean.setReqSerialId(requestBean.getOperId());
		/** 报文信息 TRANS_RECORD **/
		TransRecord transRecord = transRecordMapper.selectSingleByRequest(selectBean);
		/** 获取账务交易分录 bean TRANS_RECORD_FLOW **/
		List<TransRecordFlow> recordFlowList = transRecordFlowMapper.selectRecordFlowListByReqId(requestBean.getOperId());

		/** 核心交易流水信息列表 **/
		List<TransAction> recordFlowTransList = transQueryService.selectTransListByRecordFlow(recordFlowList);
		if (null != recordFlowTransList) {
			transList.addAll(recordFlowTransList);
		}
		modelAndView.setViewName(viewName);
		/** 业务流水信息 **/
		modelAndView.addObject("operation", operation);
		/** 核心报文信息（核心报文） **/
		modelAndView.addObject("transRecord", transRecord);
		/** 交易流水信息（银联/交广科技/七分钱） **/
		modelAndView.addObject("transList", transList);
		logger.info("交易流水信息{}", JSONObject.toJSONString(transList, true));
		return modelAndView;
	}

	/**
	 * 终止流程 bms_operation_exception_flow bms_operation_exception
	 * 
	 * @param operation
	 */
	public void closureOperation(Operation operation) {
		try {
			Map<String, String> recordMap = new HashMap<String, String>();
			TransRecord selectBean = new TransRecord();
			selectBean.setReqSerialId(operation.getOperId());
			TransRecord transRecord = transRecordMapper.selectSingleByRequest(selectBean);
			String coreSn = "";
			if (transRecord != null && !StringUtils.isBlank(transRecord.getMsgId())) {
				coreSn = transRecord.getMsgId();
			}
			switch (operation.getOperType()) {
			case CUST_RECHARGE:
				RechargeBean updateRechargeBean = new RechargeBean();
				updateRechargeBean.setChargeNetpayState(operation.getChargeNetpayState());
				updateRechargeBean.setChargeSn(operation.getOperId());
				updateRechargeBean.setCoreSn(coreSn);
				operChargeMapper.updateChargeBillNetpayState(updateRechargeBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case RED_PACKET_RECHARGE:
				RechargeBean updateRedPacketRechargeBean = new RechargeBean();
				updateRedPacketRechargeBean.setChargeNetpayState(operation.getChargeNetpayState());
				updateRedPacketRechargeBean.setChargeSn(operation.getOperId());
				updateRedPacketRechargeBean.setCoreSn(coreSn);
				operChargeMapper.updateChargeBillNetpayState(updateRedPacketRechargeBean);

				if (operation.getChargeNetpayState().equals(Constant.STATE_SUCCESS)) {
					/** 更新红包入账信息 **/
					RedPacketDetail updateBean = new RedPacketDetail();
					updateBean.setInOrderState(Constant.IN_ORDER_SATATUS_SUCCESS);
					updateBean.setInOrderId(operation.getOperId());
					redPacketDetailMapper.updateRedPacketDetail(updateBean);
				}

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case BALANCE_PAYMENT:/**余额支付 **/
				
				/**修改td_trade_bill_main*/
				TdTradeBillMainVO updateTradeBean = new TdTradeBillMainVO();
				updateTradeBean.setOrderState(operation.getChargeNetpayState());
				updateTradeBean.setOrderId(operation.getOperId());
				updateTradeBean.setCoreSn(coreSn);
				operTradeMapper.updateBillMainOrderState(updateTradeBean);
				
				//通过td_trade_bill_main的ORDER_ID 查询流水表（acct_seven_buss_flow）记录
				AcctSevenTrans at = acctSevenTransMapper.selectAcctSevenBussFlow(operation.getOperId());
				
				if(at!=null){
					//获取 产品代码和渠道代码
					OperTrade ot = operTradeMapper.selectTradeBillById(operation.getOperId());
					if((null!=ot.getProductCode()&&!("".equals(ot.getProductCode())))||(null!=ot.getChannelCode()&&!("".equals(ot.getChannelCode())))){
						at.setProductCode(ot.getProductCode());
						at.setChannelCode(ot.getChannelCode());
						//更新流水表产品代码和渠道代码
						acctSevenTransMapper.updateBussAccountFlowProCha(at);
					}
				}
				
				/**修改td_merchant_in*/
				//通过td_trade_bill_main的ORDER_ID 查询 td_merchant_in 表是否有该记录
				
				OperTrade oper = operTradeMapper.selectTdmerchantInByInOrderId(operation.getOperId());
				
				if(oper != null){
					//修改td_merchant_in 表
					operTradeMapper.updateMerchantInOrderState(updateTradeBean);
				}
				
				//通过 td_merchant_in 的OUT_ORDER_ID 查询 td_order 表是否有该记录
				OrderInfoBean order = operTradeMapper.selectTdOrderByOutOrderId(oper.getOutOrderId());
				
				if(order != null ){
					//修改td_order 表
					OrderInfoBean order_ = new OrderInfoBean();
					order_.setOrderState(operation.getChargeNetpayState());
					order_.setOrderId(order.getOrderId());
					orderMapperMaster.updateOrder(order_);
					//operTradeMapper.updateTdOrderState(order);
				}
				

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				
			
				break;

			case RED_PACKET_PAYMENT:
				TdTradeBillMainVO updateRedPacketBean = new TdTradeBillMainVO();
				updateRedPacketBean.setOrderState(operation.getChargeNetpayState());
				updateRedPacketBean.setOrderId(operation.getOperId());
				updateRedPacketBean.setCoreSn(coreSn);
				operTradeMapper.updateBillMainOrderState(updateRedPacketBean);

				/** 更新红包状态/核心状态 **/
				RedEnvelopeInfo redEnvelopeInfo = new RedEnvelopeInfo();
				RedEnvelopeInfo queryBean = tedEnvelopeInfoMapper.selectRedEnvelopeInfo(operation.getOperId());
				if (operation.getChargeNetpayState().equals(Constant.STATE_CANCEL)) {
					if (queryBean != null) {
						redEnvelopeInfo.setRedEnvId(queryBean.getRedEnvId());
						redEnvelopeInfo.setOrderState(Constant.STATE_CANCEL);
						tedEnvelopeInfoMapper.updateRedEnvelopeInfo(redEnvelopeInfo);
					}
				}

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case BANK_CARD_PAYMENT:
				/**卡支付*/
				TdTradeBillMainVO updateTradeBillMain = new TdTradeBillMainVO();
				updateTradeBillMain.setOrderState(operation.getChargeNetpayState());
				updateTradeBillMain.setChargeNetpayState(operation.getChargeNetpayState());
				updateTradeBillMain.setOrderId(operation.getOperId());
				updateTradeBillMain.setCoreSn(coreSn);
				operTradeMapper.updateBillMainOrderState(updateTradeBillMain);
				
				//通过td_trade_bill_main的ORDER_ID 查询流水表（acct_seven_buss_flow）记录
				AcctSevenTrans ats = acctSevenTransMapper.selectAcctSevenBussFlow(operation.getOperId());
				
				if(ats!=null){
					//获取 产品代码和渠道代码
					OperTrade ot = operTradeMapper.selectTradeBillById(operation.getOperId());
					if((null!=ot.getProductCode()&&!("".equals(ot.getProductCode())))||(null!=ot.getChannelCode()&&!("".equals(ot.getChannelCode())))){
						ats.setProductCode(ot.getProductCode());
						ats.setChannelCode(ot.getChannelCode());
						//更新流水表产品代码和渠道代码
						acctSevenTransMapper.updateBussAccountFlowProCha(ats);
					}
				}
				
				
				/**修改td_merchant_in*/
				//通过td_trade_bill_main的ORDER_ID 查询 td_merchant_in 表是否有该记录
				
				OperTrade opers = operTradeMapper.selectTdmerchantInByInOrderId(operation.getOperId());
				
				
				if(opers != null){
					//修改td_merchant_in 表
					operTradeMapper.updateMerchantInOrderState(updateTradeBillMain);
					
				}
				
				//通过 td_merchant_in 的OUT_ORDER_ID 查询 td_order 表是否有该记录
				OrderInfoBean orders = operTradeMapper.selectTdOrderByOutOrderId(opers.getOutOrderId());
				
				if(orders != null ){
					//修改td_order 表
					//operTradeMapper.updateTdOrderState(orders);
					OrderInfoBean orders_ = new OrderInfoBean();
					orders_.setOrderState(operation.getChargeNetpayState());
					orders_.setOrderId(orders.getOrderId());
					orderMapperMaster.updateOrder(orders_);
				}
				

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_TRANSFER:
				TdTradeBillMainVO updateTransferBean = new TdTradeBillMainVO();
				updateTransferBean.setOrderState(operation.getChargeNetpayState());
				updateTransferBean.setOrderId(operation.getOperId());
				updateTransferBean.setCoreSn(coreSn);
				operTradeMapper.updateBillMainOrderState(updateTransferBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_TRANSFER_REVOKE:
				OperRevoke transferRevokeBean = new OperRevoke();
				transferRevokeBean.setRevokeStatus(operation.getChargeNetpayState());
				transferRevokeBean.setOrderId(operation.getOperId());
				transferRevokeBean.setCoreSn(coreSn);
				operRevokeMapper.updateTransferRevokeState(transferRevokeBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);

				break;
			case BANK_CARD_PAYMENT_REFUND:
				RefundBill updateCardRefundBillMain = new RefundBill();
				updateCardRefundBillMain.setRefundState(operation.getChargeNetpayState());
				updateCardRefundBillMain.setOrderId(operRefundMapper.selectOrderIdById(operation.getOperId()).getOrderId());
				updateCardRefundBillMain.setCoreSn(coreSn);
				operRefundMapper.updateRefundOrderState(updateCardRefundBillMain);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;
			case BALANCE_PAYMENT_REFUND:
				RefundBill updateBalanceRefundBillMain = new RefundBill();
				updateBalanceRefundBillMain.setRefundState(operation.getChargeNetpayState());
				updateBalanceRefundBillMain.setOrderId(operRefundMapper.selectOrderIdById(operation.getOperId()).getOrderId());
				updateBalanceRefundBillMain.setCoreSn(coreSn);
				operRefundMapper.updateRefundOrderState(updateBalanceRefundBillMain);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case RED_PACKET_PAYMENT_REFUND:
				RefundBill updateRedPacketRefundBillMain = new RefundBill();
				updateRedPacketRefundBillMain.setRefundState(operation.getChargeNetpayState());
				updateRedPacketRefundBillMain.setOrderId(operRefundMapper.selectOrderIdById(operation.getOperId()).getOrderId());
				updateRedPacketRefundBillMain.setCoreSn(coreSn);
				operRefundMapper.updateRefundOrderState(updateRedPacketRefundBillMain);

				/** 更新红包状态/核心状态 **/
				RedEnvelopeInfo redEnvelopeInfoRefund = new RedEnvelopeInfo();
				RedEnvelopeInfo queryRefundBean = tedEnvelopeInfoMapper.selectRedEnvelopeInfoRefund(operRefundMapper.selectOrderIdById(
						operation.getOperId()).getOrderId());
				if (operation.getChargeNetpayState().equals(Constant.STATE_SUCCESS)) {
					if (queryRefundBean != null) {
						redEnvelopeInfoRefund.setRedEnvId(queryRefundBean.getRedEnvId());
						redEnvelopeInfoRefund.setExpiredBalProcStatus(Constant.REFUND_STATE_SUCCESS);
						tedEnvelopeInfoMapper.updateRedEnvelopeInfo(redEnvelopeInfoRefund);
					}
				}

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;
			case BALANCE_PAYMENT_REVOKE:

				OperRevoke balanceRevoke = new OperRevoke();
				balanceRevoke.setRevokeStatus(operation.getChargeNetpayState());
				balanceRevoke.setOrderId(operation.getOperId());
				balanceRevoke.setCoreSn(coreSn);
				operRevokeMapper.updatePaymentRevoke(balanceRevoke);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case BANK_CARD_PAYMENT_REVOKE:
				OperRevoke cardRevoke = new OperRevoke();
				cardRevoke.setRevokeStatus(operation.getChargeNetpayState());
				cardRevoke.setOrderId(operation.getOperId());
				cardRevoke.setCoreSn(coreSn);
				operRevokeMapper.updatePaymentRevoke(cardRevoke);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_RECHARGE_REVOKE:
				OperRevoke rechargeRevoke = new OperRevoke();
				rechargeRevoke.setRevokeStatus(operation.getChargeNetpayState());
				rechargeRevoke.setOrderId(operation.getOperId());
				rechargeRevoke.setRtnInfo(coreSn);
				operRevokeMapper.updateRechargeRevoke(rechargeRevoke);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_FULL_FREEZE:
				OperFreeze updateFreezeBean = new OperFreeze();
				updateFreezeBean.setFreezeStatus(operation.getChargeNetpayState());
				updateFreezeBean.setId(operation.getOperId());
				updateFreezeBean.setRelateId(coreSn);
				operFreezeMapper.updateCustFreezeStatus(updateFreezeBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_FULL_UNFREEZE:
				OperFreeze updateUnFreeaeBean = new OperFreeze();
				updateUnFreeaeBean.setFreezeStatus(operation.getChargeNetpayState());
				updateUnFreeaeBean.setId(operation.getOperId());
				updateUnFreeaeBean.setRelateId(coreSn);
				operFreezeMapper.updateCustFreezeStatus(updateUnFreeaeBean);

				/** 修改核心trans_record **/
				recordMap.put("orderStatus", operation.getChargeNetpayState());
				recordMap.put("orderId", operation.getOperId());
				updateTransRecordStatus(recordMap);
				break;

			case CUST_WITHDRAW_APPLY:
				CustWithdrawBean updateCustWithdrawApply = new CustWithdrawBean();
				updateCustWithdrawApply.setWithdrawState(operation.getChargeNetpayState());
				updateCustWithdrawApply.setWithdrawSn(operation.getOperId());
				custWithdrawMapper.updateCustWithdrawBySn(updateCustWithdrawApply);
				break;

			case CUST_WITHDRAW:
				CustWithdrawBean updateCustWithdraw = new CustWithdrawBean();
				updateCustWithdraw.setWithdrawState(operation.getChargeNetpayState());
				updateCustWithdraw.setWithdrawReqserialid(operation.getOperId());
				custWithdrawMapper.updateCustWithdrawByWithdrawReqSerialId(updateCustWithdraw);
				break;

			case BUSS_SETTLE:
				MerchantSettle settleBean = new MerchantSettle();
				settleBean.setStatusStr(operation.getChargeNetpayState());
				settleBean.setId(operation.getOperId());
				bussSettleMapper.updateMerchantSettleStatus(settleBean);
				break;

			case BUSS_SETTLE_APPLY:
				MerchantSettle settleBeanApply = new MerchantSettle();
				settleBeanApply.setStatusStr(operation.getChargeNetpayState());
				settleBeanApply.setId(operation.getOperId());
				bussSettleMapper.updateMerchantSettleStatus(settleBeanApply);
				break;

			default:
				throw new RuntimeException("暂不支持的操作");
			}
			OperationException operationException = new OperationException();
			operationException.setOperationId(operation.getOperId());
			operationException.setOperationType(operation.getOperType().name());
			operationException.setDealMemo(operation.getOperationOpinion());
			/** 处理状态 重启RESTART/回退ROLLBACK/终止END' **/
			operationException.setDealResult(DealResult.END.name());
			/** 处理类型 初始化INIT/处理中DEAL_ING/处理完成DEAL_OVER/失效DISABLE **/
			operationException.setStatus(OperationExceptionStatus.DEAL_OVER.name());
			operationExceptionService.insertOperationException(operationException);
		} catch (Exception e) {
			logger.error("流程结束操作异常" + e);
			e.printStackTrace();
		}
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
