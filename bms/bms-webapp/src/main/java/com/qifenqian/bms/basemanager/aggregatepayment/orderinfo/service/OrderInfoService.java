package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.utils.ExportExcel;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.ExportOrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.dao.OrderInfoDAO;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.dao.OrderRefundDAO;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.page.Page;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.bussaccounting.BussAccountingRequest;
import com.sevenpay.invoke.transaction.bussaccounting.BussAccountingResponse;

@Service
public class OrderInfoService {

	private static Logger logger = LoggerFactory.getLogger(OrderInfoService.class);
	@Autowired
	private OrderInfoDAO orderInfoDAO;

	@Autowired
	private OrderRefundDAO OrderRefundDAO;

	@Autowired
	private SevenpayCoreServiceInterface sevenpayCoreServiceInterface;

	@Autowired
	private TdCustInfoService tdCustInfoService;

	@Autowired
	private OrderRefundService OrderRefundService;
	
	@Value("${EXPORT_EXCEL}")
	private String EXPORT_EXCEL;

	/** 查询所有交易 */
	public List<OrderInfoBean> getOrderInfoList(OrderInfoQueryBean queryBean) {
		return orderInfoDAO.getOrderInfoList(queryBean);
	}

	public List<ExportOrderInfoBean> getOrderInfoListExport(OrderInfoQueryBean queryBean) {
		return orderInfoDAO.getOrderInfoListExport(queryBean);
	}

	public List<ExportOrderInfoBean> getMyOrderInfoListExport(OrderInfoQueryBean queryBean) {
		return orderInfoDAO.getMyOrderInfoListExport(queryBean);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> exportExcel(List excels, String[] headers, String fileName, String title,
			HttpServletRequest request) {

		Map<String, String> fileInfo = new HashMap<String, String>();
		OutputStream out = null;

		try {

			String exportPath =EXPORT_EXCEL;
			File saveFile = new File(exportPath);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			String filePath = saveFile + File.separator + fileName;
			fileInfo.put("fileName", fileName);
			fileInfo.put("filePath", filePath);
			out = new FileOutputStream(filePath);

			ExportExcel<T> ex = new ExportExcel<T>();

			ex.exportExcel(title, headers, excels, out);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileInfo;
	}

	public List<OrderInfoBean> getOrderExceptionList(OrderInfoQueryBean queryBean) {
		return orderInfoDAO.getOrderExceptionList(queryBean);
	}

	/**
	 * 查询订单详细信息
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderInfoBean getOrderInfoDetail(String orderId) {
		return orderInfoDAO.getOrderInfoDetail(orderId);
	}

	/**
	 * 续作
	 * 
	 * @param queryBean
	 */
	@SuppressWarnings("rawtypes")
	public void nextStep(OrderInfoQueryBean queryBean) {

		try {
			switch (queryBean.getOrderType()) {
			case Constant.COMBINED_TYPE_PAY:
				OrderInfoBean orderInfo = this.getOrderInfoDetail(queryBean.getOrderId());
				if (orderInfo == null) {
					throw new IllegalArgumentException("订单信息不存在！");
				}
				// 查询七分钱内部账号ID
				TdCustInfo custInfo = new TdCustInfo();
				custInfo.setCustId(orderInfo.getMchId());
				custInfo = tdCustInfoService.selectByBean(custInfo);
				String msg = GenSN.getSN();
				// 调用核心入账
				if (custInfo == null)
					logger.info("custInfo is null");
				ResponseMessage response = this.saveAccount(orderInfo, custInfo.getQfqAccId(), msg,
						RequestColumnValues.BusinessType.RECEIVE);
				OrderInfoBean updateOrder = new OrderInfoBean();
				updateOrder.setOrderId(orderInfo.getOrderId());
				updateOrder.setCoreSn(msg);
				updateOrder.setOrderCoreReturnCode(response.getRtnCode().name());
				updateOrder.setOrderCoreReturnTime(
						new SimpleDateFormat("yyyyMMddHHmmss").format(response.getRtnDatetime()));
				updateOrder.setOrderCoreReturnMsg(
						response.getRtnCode().name().equals("SUCCESS") ? "补录核心流水写入成功" : "补录核心流水写入异常");
				updateOrder.setCoreSubmitstate(switchStateCode(response.getRtnResult()));
				orderInfoDAO.updateOrder(updateOrder);
				// 更改订单信息
				break;
			case Constant.COMBINED_TYPE_REFUND:
				// 查询退款订单信息
				RefundBean refund = OrderRefundService.getRefundDetail(queryBean.getRefundId());
				if (refund == null) {
					throw new IllegalArgumentException("退款信息不存在！");
				}
				// 查询退款原订单信息
				OrderInfoBean orderInfo_refund = this.getOrderInfoDetail(refund.getOrderId());
				orderInfo_refund.setTradeAmt(refund.getRefundAmt().toString());
				// 查询七分钱内部账号ID
				TdCustInfo custInfo_refund = new TdCustInfo();
				custInfo_refund.setMerchantCode(refund.getMerchantCode());
				custInfo_refund = tdCustInfoService.selectById(custInfo_refund.getMerchantCode());// 商户号是CUSTID
				String msg_refund = GenSN.getSN();
				// 调用核心入账
				ResponseMessage responseRefund = this.saveAccount(orderInfo_refund, custInfo_refund.getQfqAccId(),
						msg_refund, RequestColumnValues.BusinessType.RECEIVE_REFUND);

				RefundBean updaterefund = new RefundBean();
				updaterefund.setRefundId(refund.getRefundId());
				updaterefund.setCoreSn(msg_refund);
				updaterefund.setOrderCoreReturnCode(responseRefund.getRtnCode().name());
				updaterefund.setOrderCoreReturnTime(
						new SimpleDateFormat("yyyyMMddHHmmss").format(responseRefund.getRtnDatetime()));
				updaterefund.setOrderCoreReturnMsg(
						responseRefund.getRtnCode().name().equals("SUCCESS") ? "补录核心流水写入成功" : "补录核心流水写入异常");
				updaterefund.setCoreSubmitstate(switchStateCode(responseRefund.getRtnResult()));
				OrderRefundDAO.updateRefundRecode(updaterefund);
				break;

			}
		} catch (Exception e) {

			throw e;
		}

	}

	public String switchStateCode(RequestColumnValues.RtnResult status) {

		String code = "";
		switch (status) {
		case SUCCESS:
			code = "00";// 核心返回成功
			break;
		case FAILURE:
			code = "04";// 核心返回失败
			break;
		default:
			code = "90";
			break;
		}
		return code;
	}

	/**
	 * 记录商户流水
	 * 
	 * @param order
	 * @return
	 */
	public ResponseMessage<BussAccountingResponse> saveAccount(OrderInfoBean order, String acctId, String msgId,
			RequestColumnValues.BusinessType type) {
		ResponseMessage<BussAccountingResponse> responseMessage = new ResponseMessage<BussAccountingResponse>();

		try {
			RequestMessage<BussAccountingRequest> requestMessage = new RequestMessage<BussAccountingRequest>();
			{
				requestMessage.setMsgType(MsgType.BUSS_ACCOUNTING);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.COMBINED);
				requestMessage.setReqSerialId(msgId);
				requestMessage.setReqMsgNum(1);
				requestMessage.setReqDatetime(new Date());

				BussAccountingRequest request = new BussAccountingRequest();
				{
					request.setBusinessType(type); // 收款
					// request.setBusinessType(RequestColumnValues.BusinessType.RECEIVE_REFUND); //
					// 退款
					request.setCustId(order.getMchId()); // 客户号
					request.setAcctId(acctId); // 账号
					request.setProductCode(order.getService()); // 产品码
					request.setChannelCode(order.getChannelSub()); // 渠道码
					request.setCurrCode(RequestColumnValues.CurrCode.CNY); // 币别
					request.setTransAmt(new BigDecimal(order.getTradeAmt())); // 发生额
					request.setBrief("商户聚合记账"); // 摘要
				}
				requestMessage.setRequest(request);
			}

			logger.info("记录商户流水请求报文==========>>request:" + JSONObject.toJSONString(requestMessage, true));
			responseMessage = sevenpayCoreServiceInterface.bussAccounting(requestMessage);
			logger.info("记录商户流水返回报文==========>>response:" + JSONObject.toJSONString(responseMessage, true));

		} catch (Exception e) {
			logger.error("记录商户流水失败：saveAccount()", e);
			throw e;
		}
		return responseMessage;
	}

	/**
	 * 查询自己权限的所属订单
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<OrderInfoBean> queryMylist(OrderInfoQueryBean queryBean) {
		return orderInfoDAO.queryMylist(queryBean);
	}

	/**
	 * 查询自己权限的所属退款订单
	 * 
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<RefundBean> queryMyRefundList(RefundQueryBean queryBean) {
		return orderInfoDAO.queryMyRefundList(queryBean);
	}
}