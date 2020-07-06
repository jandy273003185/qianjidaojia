package com.qifenqian.bms.accounting.exception.dao.opertrade.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName OperTrade.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class OperTrade extends Operation {

	private static final long serialVersionUID = 6648583949159535616L;

	/** 订单号 */
	private String orderId;
	/** 订单生成时间 */
	private Date orderCreateTime;
	/** 订单名称 */
	private String orderName;
	/**
	 * 交易类型： 1001 转账到7分钱； 1002 转账到银行卡； 1101 商户接入-收款-无担保； 1102 商户接入-收款-有担保； 1201
	 * 转入7分宝； 1202 转出7分宝； 8001 退款。
	 */
	private String tradeType;
	/** 订单描述 */
	private String orderDesc;
	/** 01：待提交；02 提交核心处理；03核心已受理；04 核心返回失败；00 核心返回成功；99 取消 */
	private String orderState;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String orderCoreReturnCode;
	/** 核心返回信息 */
	private String orderCoreReturnMsg;
	/** 核心返回时间 */
	private Date orderCoreReturnTime;
	/** 发起方客户编号 */
	private String srcCustId;
	/** 付方是否平台客户：1是，0否。 */
	private String payerIsPlatformCust;
	/** 付方客户编号 */
	private String payerCustId;
	/** 付方账户类型：SEVN_CUS_0 七分钱账户；QFB0_CUS_0 七分宝账户； */
	private String payerAcctType;
	/** 付方账户ID */
	private String payerAcctId;
	/** 付方客户名 */
	private String payerCustName;
	/** 总金额 */
	private BigDecimal sumAmt;
	/** 币别 */
	private RequestColumnValues.CurrCode currCode;
	/** 是否需要充值：1是，0否，2：不确定 */
	private String isNeedCharge;
	/** 充值流水号 */
	private String chargeSn;
	/** 需充值的客户编号 */
	private String chargeCustId;
	/** 需充值的账户类型：只能充七分钱 */
	private String chargeAcctType;
	/** 充值方式：1快捷支付（已开通）；2快捷支付（未开通）；3网银； 4 银联在线 */
	private String chargeType;
	/** 充值行别 */
	private String chargeBankType;
	/** 充值行号 支付系统行号 */
	private String chargeBankCode;
	/** 充值开户行行名 */
	private String chargeBankName;
	/** 充值银行卡类型：1：借记卡；2：信用卡 */
	private String chargeBankCardType;
	/** 充值银行卡号 */
	private String chargeBankCardNo;
	/** 对于未开通快捷支付的，此状态用于记录与核心的开通交互情况：1请求发往核心；2核心返回失败；0核心返回成功 */
	private String fastPayOpenState;
	/** 充值银行账号（内部ID），快捷支付才有 */
	private String chargeBankCardNoInternal;
	/** 充值币别 */
	private String chargeCurrCode;
	/** 充值金额 */
	private BigDecimal chargeAmt;
	/** 收方是否平台客户：1是，0否。 */
	private String payeeIsPlatformCust;
	/** 收方客户编号 */
	private String payeeCustId;
	/**
	 * 收方账户类型：BANK_CUS_0 银行借记账户；BANK_CUS_1 银行贷记账户；SEVN_CUS_0 七分钱账户；QFB0_CUS_0
	 * 七分宝账户；
	 */
	private String payeeAcctType;
	/** 收方账户ID */
	private String payeeAcctId;
	/** 收方客户名 */
	private String payeeCustName;
	/** 收方行别 */
	private String payeeBankType;
	/** 行号 支付系统行号 */
	private String payeeBankCode;
	/** 开户行行名 */
	private String payeeBankName;
	/** 开户行地区代码 */
	private String payeeBankArea;
	/** 收方银行账号 */
	private String payeeBankAcctNo;
	/** 收方姓名 */
	private String payeeBankAcctName;
	/** 紧急程度：对于转账交易：1：2小时到账；2：24小时内到账 */
	private String emergencyDegree;
	/** 是否担保交易：1是，0否 */
	private String isGuarantee;
	/** 担保交易状态：1核心未冻结货款；2核心已冻结货款；3买家确认收货，通知核心打款给卖家；4核心打款成功，卖家收到了货款 */
	private String guaranteeState;
	/** 核心返回码 */
	private String guaranteeCoreReturnCode;
	/** 核心返回信息 */
	private String guaranteeCoreReturnMsg;
	/** 操作终端：P：PC端；M：移动手机端 */
	private String opTerm;
	/** 返回商家页面地址 */
	private String pgUrl;
	/** 通知商家后台调用地址 */
	private String bgUrl;
	/** 退款关联订单号（退款交易时有值） */
	private String drawbackOrderId;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private Date modifyTime;
	/** 商户订单号 */
	private String merOrderId;
	/** */
	private String tradePin;
	
	/**外部订单号*/
	private String outOrderId;
	
	//产品代码
	private String productCode;
	
	//渠道代码channel_code
	private String channelCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public String getOrderCoreReturnCode() {
		return orderCoreReturnCode;
	}

	public void setOrderCoreReturnCode(String orderCoreReturnCode) {
		this.orderCoreReturnCode = orderCoreReturnCode;
	}

	public String getOrderCoreReturnMsg() {
		return orderCoreReturnMsg;
	}

	public void setOrderCoreReturnMsg(String orderCoreReturnMsg) {
		this.orderCoreReturnMsg = orderCoreReturnMsg;
	}

	public Date getOrderCoreReturnTime() {
		return orderCoreReturnTime;
	}

	public void setOrderCoreReturnTime(Date orderCoreReturnTime) {
		this.orderCoreReturnTime = orderCoreReturnTime;
	}

	public String getSrcCustId() {
		return srcCustId;
	}

	public void setSrcCustId(String srcCustId) {
		this.srcCustId = srcCustId;
	}

	public String getPayerIsPlatformCust() {
		return payerIsPlatformCust;
	}

	public void setPayerIsPlatformCust(String payerIsPlatformCust) {
		this.payerIsPlatformCust = payerIsPlatformCust;
	}

	public String getPayerCustId() {
		return payerCustId;
	}

	public void setPayerCustId(String payerCustId) {
		this.payerCustId = payerCustId;
	}

	public String getPayerAcctType() {
		return payerAcctType;
	}

	public void setPayerAcctType(String payerAcctType) {
		this.payerAcctType = payerAcctType;
	}

	public String getPayerAcctId() {
		return payerAcctId;
	}

	public void setPayerAcctId(String payerAcctId) {
		this.payerAcctId = payerAcctId;
	}

	public String getPayerCustName() {
		return payerCustName;
	}

	public void setPayerCustName(String payerCustName) {
		this.payerCustName = payerCustName;
	}

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public String getIsNeedCharge() {
		return isNeedCharge;
	}

	public void setIsNeedCharge(String isNeedCharge) {
		this.isNeedCharge = isNeedCharge;
	}

	public String getChargeSn() {
		return chargeSn;
	}

	public void setChargeSn(String chargeSn) {
		this.chargeSn = chargeSn;
	}

	public String getChargeCustId() {
		return chargeCustId;
	}

	public void setChargeCustId(String chargeCustId) {
		this.chargeCustId = chargeCustId;
	}

	public String getChargeAcctType() {
		return chargeAcctType;
	}

	public void setChargeAcctType(String chargeAcctType) {
		this.chargeAcctType = chargeAcctType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeBankType() {
		return chargeBankType;
	}

	public void setChargeBankType(String chargeBankType) {
		this.chargeBankType = chargeBankType;
	}

	public String getChargeBankCode() {
		return chargeBankCode;
	}

	public void setChargeBankCode(String chargeBankCode) {
		this.chargeBankCode = chargeBankCode;
	}

	public String getChargeBankName() {
		return chargeBankName;
	}

	public void setChargeBankName(String chargeBankName) {
		this.chargeBankName = chargeBankName;
	}

	public String getChargeBankCardType() {
		return chargeBankCardType;
	}

	public void setChargeBankCardType(String chargeBankCardType) {
		this.chargeBankCardType = chargeBankCardType;
	}

	public String getChargeBankCardNo() {
		return chargeBankCardNo;
	}

	public void setChargeBankCardNo(String chargeBankCardNo) {
		this.chargeBankCardNo = chargeBankCardNo;
	}

	public String getFastPayOpenState() {
		return fastPayOpenState;
	}

	public void setFastPayOpenState(String fastPayOpenState) {
		this.fastPayOpenState = fastPayOpenState;
	}

	public String getChargeBankCardNoInternal() {
		return chargeBankCardNoInternal;
	}

	public void setChargeBankCardNoInternal(String chargeBankCardNoInternal) {
		this.chargeBankCardNoInternal = chargeBankCardNoInternal;
	}

	public String getChargeCurrCode() {
		return chargeCurrCode;
	}

	public void setChargeCurrCode(String chargeCurrCode) {
		this.chargeCurrCode = chargeCurrCode;
	}

	public BigDecimal getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getPayeeIsPlatformCust() {
		return payeeIsPlatformCust;
	}

	public void setPayeeIsPlatformCust(String payeeIsPlatformCust) {
		this.payeeIsPlatformCust = payeeIsPlatformCust;
	}

	public String getPayeeCustId() {
		return payeeCustId;
	}

	public void setPayeeCustId(String payeeCustId) {
		this.payeeCustId = payeeCustId;
	}

	public String getPayeeAcctType() {
		return payeeAcctType;
	}

	public void setPayeeAcctType(String payeeAcctType) {
		this.payeeAcctType = payeeAcctType;
	}

	public String getPayeeAcctId() {
		return payeeAcctId;
	}

	public void setPayeeAcctId(String payeeAcctId) {
		this.payeeAcctId = payeeAcctId;
	}

	public String getPayeeCustName() {
		return payeeCustName;
	}

	public void setPayeeCustName(String payeeCustName) {
		this.payeeCustName = payeeCustName;
	}

	public String getPayeeBankType() {
		return payeeBankType;
	}

	public void setPayeeBankType(String payeeBankType) {
		this.payeeBankType = payeeBankType;
	}

	public String getPayeeBankCode() {
		return payeeBankCode;
	}

	public void setPayeeBankCode(String payeeBankCode) {
		this.payeeBankCode = payeeBankCode;
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public String getPayeeBankArea() {
		return payeeBankArea;
	}

	public void setPayeeBankArea(String payeeBankArea) {
		this.payeeBankArea = payeeBankArea;
	}

	public String getPayeeBankAcctNo() {
		return payeeBankAcctNo;
	}

	public void setPayeeBankAcctNo(String payeeBankAcctNo) {
		this.payeeBankAcctNo = payeeBankAcctNo;
	}

	public String getPayeeBankAcctName() {
		return payeeBankAcctName;
	}

	public void setPayeeBankAcctName(String payeeBankAcctName) {
		this.payeeBankAcctName = payeeBankAcctName;
	}

	public String getEmergencyDegree() {
		return emergencyDegree;
	}

	public void setEmergencyDegree(String emergencyDegree) {
		this.emergencyDegree = emergencyDegree;
	}

	public String getIsGuarantee() {
		return isGuarantee;
	}

	public void setIsGuarantee(String isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	public String getGuaranteeState() {
		return guaranteeState;
	}

	public void setGuaranteeState(String guaranteeState) {
		this.guaranteeState = guaranteeState;
	}

	public String getGuaranteeCoreReturnCode() {
		return guaranteeCoreReturnCode;
	}

	public void setGuaranteeCoreReturnCode(String guaranteeCoreReturnCode) {
		this.guaranteeCoreReturnCode = guaranteeCoreReturnCode;
	}

	public String getGuaranteeCoreReturnMsg() {
		return guaranteeCoreReturnMsg;
	}

	public void setGuaranteeCoreReturnMsg(String guaranteeCoreReturnMsg) {
		this.guaranteeCoreReturnMsg = guaranteeCoreReturnMsg;
	}

	public String getOpTerm() {
		return opTerm;
	}

	public void setOpTerm(String opTerm) {
		this.opTerm = opTerm;
	}

	public String getPgUrl() {
		return pgUrl;
	}

	public void setPgUrl(String pgUrl) {
		this.pgUrl = pgUrl;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getDrawbackOrderId() {
		return drawbackOrderId;
	}

	public void setDrawbackOrderId(String drawbackOrderId) {
		this.drawbackOrderId = drawbackOrderId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

	public String getTradePin() {
		return tradePin;
	}

	public void setTradePin(String tradePin) {
		this.tradePin = tradePin;
	}
}
