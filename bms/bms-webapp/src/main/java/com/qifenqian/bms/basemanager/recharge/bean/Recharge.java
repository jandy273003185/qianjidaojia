package com.qifenqian.bms.basemanager.recharge.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
	
public class Recharge implements Serializable{
	
	private static final long serialVersionUID = 1324166625209863798L;
	
	/**
	 * 充值流水号
	 */
	private String chargeSn;
	
	/**
	 * 客户编号
	 */
	private String custId;
	/**
	 * 客户名
	 */
	private String custName;
	/**
	 * 账户类型
	 */
	private String chargeAcctType;
	/**
	 * 充值方式
	 */
	private String chargeType;
	/**
	 * 充值行别
	 */
	private String chargeBankType;
	/**
	 * 充值行号
	 */
	private String chargeBankCode;
	/**
	 * 充值开户行行名
	 */
	private String chargeBankName;
	/**
	 * 充值银行卡类型
	 */
	private String chargeBankCardType;
	/**
	 * 充值银行卡号
	 */
	private String chargeBankCardNo;
	/**
	 * 对于未开通快捷支付的，此状态用于记录与核心的开通交互情况：1请求发往核心；2核心返回失败；0核心返回成功
	 */
	private String fastPayOpenState;
	/**
	 * 充值银行账号（内部ID），快捷支付才有
	 */
	private String chargeBankCardNoInternal;
	/**
	 *充值币别
	 */
	private String chargeCurrCode;
	/**
	 *充值金额
	 */
	private BigDecimal chargeAmt;
	/**
	 * 01 待支付；02 提交核心处理；03 核心返回失败；00 充值成功
	 */
	private String chargeFastpayState;
	/**
	 * 01 待支付；02 网银返回成功；03 网银返回失败；04：提交核心处理；05 核心返回失败；00 充值成功
	 */
	private String chargeNetpayState;
	/**
	 * 充值提交时间
	 */
	private Date chargeSubmitTime;
	/**
	 * 充值返回时间
	 */
	private Date chargeReturnTime;
	/**
	 * 操作终端：P：PC端；M：移动手机端
	 */
	private String opTerm;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 核心流水号
	 */
	private String coreSn;
	/**
	 * 核心返回码
	 */
	private String coreReturnCode;
	/**
	 * 核心返回信息
	 */
	private String coreReturnMsg;
	/**
	 * 返回码 给客户展现
	 */
	private String returnCode;
	/**
	 * 返回说明 给客户展现
	 */
	private String returnMsg;
	/**
	 * 创建人
	 */
	private String createId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String modifyId;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 银联流水号
	 */
	private String ylTn;
	/**
	 * 渠道编号
	 */
	private String channelId;
	
	/**
	 * 渠道
	 */
	private String channel;
	
	/**
	 * 结算金额
	 */
	private BigDecimal settleAmt;
	
	/**
	 * 对账日期
	 */
	private String compDate;
	
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getCompDate() {
		return compDate;
	}
	public void setCompDate(String compDate) {
		this.compDate = compDate;
	}
	public String getYlTn() {
		return ylTn;
	}
	public void setYlTn(String ylTn) {
		this.ylTn = ylTn;
	}
	public String getChargeSn() {
		return chargeSn;
	}
	public void setChargeSn(String chargeSn) {
		this.chargeSn = chargeSn;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getChargeFastpayState() {
		return chargeFastpayState;
	}
	public void setChargeFastpayState(String chargeFastpayState) {
		this.chargeFastpayState = chargeFastpayState;
	}
	public String getChargeNetpayState() {
		return chargeNetpayState;
	}
	public void setChargeNetpayState(String chargeNetpayState) {
		this.chargeNetpayState = chargeNetpayState;
	}
	public Date getChargeSubmitTime() {
		return chargeSubmitTime;
	}
	public void setChargeSubmitTime(Date chargeSubmitTime) {
		this.chargeSubmitTime = chargeSubmitTime;
	}
	public Date getChargeReturnTime() {
		return chargeReturnTime;
	}
	public void setChargeReturnTime(Date chargeReturnTime) {
		this.chargeReturnTime = chargeReturnTime;
	}
	public String getOpTerm() {
		return opTerm;
	}
	public void setOpTerm(String opTerm) {
		this.opTerm = opTerm;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCoreSn() {
		return coreSn;
	}
	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}
	public String getCoreReturnCode() {
		return coreReturnCode;
	}
	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}
	public String getCoreReturnMsg() {
		return coreReturnMsg;
	}
	public void setCoreReturnMsg(String coreReturnMsg) {
		this.coreReturnMsg = coreReturnMsg;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
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
	
	
	
}
