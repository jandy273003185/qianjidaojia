package com.qifenqian.bms.accounting.exception.dao.clearbank.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.TransAction;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 银行清算：clear_bank
 * 
 * @project sevenpay-bms-web
 * @fileName ClearBank.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class ClearBank extends TransAction {

	private static final long serialVersionUID = 2158489164811221082L;

	/** 编号 */
	private String id;
	/** 交易编号 */
	private String transFlowId;
	/** 业务用途：提现/退款 */
	private RequestColumnValues.BusinessType businessType;
	/** 是否加急：Y-加急；N-不加急 */
	private RequestColumnValues.IsRapid isRapid;
	/** 付方内部账户编号 */
	private String payAcctId;
	/** 付方账户类型： */
	private RequestColumnValues.AcctType payAcctType;
	/** 付方银行账号 */
	private String payBankCardNo;
	/** 付方账户名 */
	private String payBankCardName;
	/** 开户行联行号 */
	private String payBankCode12;
	/** 收付客户类型 */
	private RequestColumnValues.CustType rcvCustType;
	/** 收方内部编号ID */
	private String rcvAcctId;
	/** 收方账户类型 */
	private RequestColumnValues.AcctType rcvAcctType;
	/** 收方银行账号 */
	private String rcvBankCardNo;
	/** 收方账户名 */
	private String rcvBankCardName;
	/** 开户行联行号 */
	private String rcvBankCode12;
	/** 币别 */
	private RequestColumnValues.CurrCode currCode;
	/** 交易金额 */
	private BigDecimal transAmt;
	/** 摘要 */
	private String brief;
	/** 预留字段 */
	private String reserve;
	/** 写入日期 */
	private String instDate;
	/** 写入时间 */
	private Date instDatetime;
	/** 状态:初始化/处理中/成功/失败 */
	private RequestColumnValues.TransStatus status;
	/** 执行方式：网银/代付接口 */
	private RequestColumnValues.BankExecuteWay executeWay;
	/** 执行银行代码 */
	private String executeBankCode;
	/** 七分钱会计日期 */
	private String workDate;
	/** 七分钱交易发送日期YYYYMMDD */
	private String sevenSendDate;
	/** 七分钱交易发送时间HHmmss */
	private String sevenSendTime;
	/** 返回时七分钱时间 */
	private Date sevenRtnDatetime;
	/** 外系统编号 */
	private String outerSysId;
	/** 外系统流水号 */
	private String ourerSerialId;
	/** 交广科技返回日期 */
	private String outerRtnDate;
	/** 交广科技返回时间 */
	private String outerRtnTime;
	/** 交广科技返回代码 */
	private String outerRtnCode;
	/** 交广科技返回描述 */
	private String outerRtnInfo;
	/** 对账状态 */
	private String balStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransFlowId() {
		return transFlowId;
	}

	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}

	public RequestColumnValues.BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(RequestColumnValues.BusinessType businessType) {
		this.businessType = businessType;
	}

	public RequestColumnValues.IsRapid getIsRapid() {
		return isRapid;
	}

	public void setIsRapid(RequestColumnValues.IsRapid isRapid) {
		this.isRapid = isRapid;
	}

	public String getPayAcctId() {
		return payAcctId;
	}

	public void setPayAcctId(String payAcctId) {
		this.payAcctId = payAcctId;
	}

	public RequestColumnValues.AcctType getPayAcctType() {
		return payAcctType;
	}

	public void setPayAcctType(RequestColumnValues.AcctType payAcctType) {
		this.payAcctType = payAcctType;
	}

	public String getPayBankCardNo() {
		return payBankCardNo;
	}

	public void setPayBankCardNo(String payBankCardNo) {
		this.payBankCardNo = payBankCardNo;
	}

	public String getPayBankCardName() {
		return payBankCardName;
	}

	public void setPayBankCardName(String payBankCardName) {
		this.payBankCardName = payBankCardName;
	}

	public String getPayBankCode12() {
		return payBankCode12;
	}

	public void setPayBankCode12(String payBankCode12) {
		this.payBankCode12 = payBankCode12;
	}

	public String getRcvAcctId() {
		return rcvAcctId;
	}

	public void setRcvAcctId(String rcvAcctId) {
		this.rcvAcctId = rcvAcctId;
	}

	public RequestColumnValues.AcctType getRcvAcctType() {
		return rcvAcctType;
	}

	public void setRcvAcctType(RequestColumnValues.AcctType rcvAcctType) {
		this.rcvAcctType = rcvAcctType;
	}

	public String getRcvBankCardNo() {
		return rcvBankCardNo;
	}

	public void setRcvBankCardNo(String rcvBankCardNo) {
		this.rcvBankCardNo = rcvBankCardNo;
	}

	public String getRcvBankCardName() {
		return rcvBankCardName;
	}

	public void setRcvBankCardName(String rcvBankCardName) {
		this.rcvBankCardName = rcvBankCardName;
	}

	public String getRcvBankCode12() {
		return rcvBankCode12;
	}

	public void setRcvBankCode12(String rcvBankCode12) {
		this.rcvBankCode12 = rcvBankCode12;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public RequestColumnValues.TransStatus getStatus() {
		return status;
	}

	public void setStatus(RequestColumnValues.TransStatus status) {
		this.status = status;
	}

	public RequestColumnValues.BankExecuteWay getExecuteWay() {
		return executeWay;
	}

	public void setExecuteWay(RequestColumnValues.BankExecuteWay executeWay) {
		this.executeWay = executeWay;
	}

	public String getExecuteBankCode() {
		return executeBankCode;
	}

	public void setExecuteBankCode(String executeBankCode) {
		this.executeBankCode = executeBankCode;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getSevenSendDate() {
		return sevenSendDate;
	}

	public void setSevenSendDate(String sevenSendDate) {
		this.sevenSendDate = sevenSendDate;
	}

	public String getSevenSendTime() {
		return sevenSendTime;
	}

	public void setSevenSendTime(String sevenSendTime) {
		this.sevenSendTime = sevenSendTime;
	}

	public Date getSevenRtnDatetime() {
		return sevenRtnDatetime;
	}

	public void setSevenRtnDatetime(Date sevenRtnDatetime) {
		this.sevenRtnDatetime = sevenRtnDatetime;
	}

	public String getOuterSysId() {
		return outerSysId;
	}

	public void setOuterSysId(String outerSysId) {
		this.outerSysId = outerSysId;
	}

	public String getOurerSerialId() {
		return ourerSerialId;
	}

	public void setOurerSerialId(String ourerSerialId) {
		this.ourerSerialId = ourerSerialId;
	}

	public String getOuterRtnDate() {
		return outerRtnDate;
	}

	public void setOuterRtnDate(String outerRtnDate) {
		this.outerRtnDate = outerRtnDate;
	}

	public String getOuterRtnTime() {
		return outerRtnTime;
	}

	public void setOuterRtnTime(String outerRtnTime) {
		this.outerRtnTime = outerRtnTime;
	}

	public String getOuterRtnCode() {
		return outerRtnCode;
	}

	public void setOuterRtnCode(String outerRtnCode) {
		this.outerRtnCode = outerRtnCode;
	}

	public String getOuterRtnInfo() {
		return outerRtnInfo;
	}

	public void setOuterRtnInfo(String outerRtnInfo) {
		this.outerRtnInfo = outerRtnInfo;
	}

	public String getBalStatus() {
		return balStatus;
	}

	public RequestColumnValues.CustType getRcvCustType() {
		return rcvCustType;
	}

	public void setRcvCustType(RequestColumnValues.CustType rcvCustType) {
		this.rcvCustType = rcvCustType;
	}

	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}
}
