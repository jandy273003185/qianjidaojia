package com.qifenqian.bms.accounting.kingdee.bean;

import java.math.BigDecimal;

/**
 * 后台清算-金蝶付款 返回信息
 */
@SuppressWarnings("serial")
public class BmsClearKingdeePayInfo extends BaseBean {

	/**
	 * 清算编号/单据编号
	 */
	private String clearId;

	/**
	 * 业务类型:个人提现PERSONAL_WITHDRAW，商户结算MERCHANT_SETTLE
	 */
	private String businessType;

	/**
	 * 业务编号
	 */
	private String operId;

	/**
	 * 业务日期
	 */
	private String date;

	/**
	 * 往来单位名称
	 */
	private String fcontactunitName;

	/**
	 * 往来单位编码
	 */
	private String fcontactunitNumber;

	/**
	 * 往来单位类型
	 */
	private String fcontactunitType;

	/**
	 * 收款单位名称
	 */
	private String frectunitName;

	/**
	 * 收款单位编码
	 */
	private String frectunitNumber;

	/**
	 * 收款单位类型
	 */
	private String frectunitType;

	/**
	 * 是否期初单据
	 */
	private String fisinit;

	/**
	 * 七分钱会计日期
	 */
	private String workDate;

	/**
	 * 七分钱交易发送日期YYYYMMDD
	 */
	private String sendDate;

	/**
	 * 七分钱交易发送时间HHmmss
	 */
	private String sendTime;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 写入人
	 */
	private String instUser;

	/**
	 * 写入日期
	 */
	private String instDate;

	/**
	 * 写入时间
	 */
	private String instDatetime;

	/**
	 * 返回日期
	 */
	private String rtnDate;

	/**
	 * 返回时间
	 */
	private String rtnTime;

	/**
	 * 返回ID
	 */
	private String rtnId;

	/**
	 * 返回number
	 */
	private String rtnNumber;

	/**
	 * 交易是否成功
	 */
	private String rtnIsSuccess;

	/**
	 * 返回错误码
	 */
	private String rtnErrorCode;

	/**
	 * 返回信息
	 */
	private String rtnMessage;

	/**
	 * 返回堆栈跟踪
	 */
	private String rtnStackTrace;

	/**
	 * 返回错误信息列表：
	 */
	private String rtnErrors;

	/**
	 * 返回写入时间
	 */
	private String updateDatetime;

	/**
	 * 对账状态
	 */
	private String balStatus;

	/**
	 * 银行处理状态
	 */
	private String bankStatus;

	/**
	 * 付款用途名称
	 */
	private String fpurposeidName;

	/**
	 * 付款用途编码
	 */
	private String fpurposeidNumber;

	/**
	 * 应付金额
	 */
	private BigDecimal fpaytotalAmountFor;

	/**
	 * 付款金额
	 */
	private BigDecimal fpayAmountForE;

	/**
	 * 折后金额
	 */
	private BigDecimal fsettlepayAmountFor;

	/**
	 * 实付金额
	 */
	private BigDecimal frealpayAmountForD;

	/**
	 * 我方银行账号名称
	 */
	private String faccountidName;

	/**
	 * 我方银行账号编码
	 */
	private String faccountidNumber;

	/**
	 * 收款银行名称
	 */
	private String fbanktypeRecName;

	/**
	 * 收款银行编码
	 */
	private String fbanktypeRecNumber;

	/**
	 * 对方银行账号
	 */
	private String foppositeBankAccount;

	/**
	 * 对方账户名称
	 */
	private String foppositeCcountName;

	/**
	 * 对方开户行
	 */
	private String foppositeBankName;

	/**
	 * 开户行地址
	 */
	private String fopenAddressRec;

	/**
	 * 联行号
	 */
	private String cnaps;

	/**
	 * 费用项目编码
	 */
	private String costid;

	/**
	 * 付款金额本位币
	 */
	private BigDecimal fpayAmountE;
	
	/**
	 * 银行描述信息
	 */
	private String fBankMsg;
	
	/**
	 * 银行描述信息
	 */
	private String fEbMsg;
	

	public String getFBankMsg() {
		return fBankMsg;
	}

	public String getFEbMsg() {
		return fEbMsg;
	}

	public void setFBankMsg(String fBankMsg) {
		this.fBankMsg = fBankMsg;
	}

	public void setFEbMsg(String fEbMsg) {
		this.fEbMsg = fEbMsg;
	}

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFcontactunitName() {
		return fcontactunitName;
	}

	public void setFcontactunitName(String fcontactunitName) {
		this.fcontactunitName = fcontactunitName;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public String getFcontactunitType() {
		return fcontactunitType;
	}

	public void setFcontactunitType(String fcontactunitType) {
		this.fcontactunitType = fcontactunitType;
	}

	public String getFrectunitName() {
		return frectunitName;
	}

	public void setFrectunitName(String frectunitName) {
		this.frectunitName = frectunitName;
	}

	public String getFrectunitNumber() {
		return frectunitNumber;
	}

	public void setFrectunitNumber(String frectunitNumber) {
		this.frectunitNumber = frectunitNumber;
	}

	public String getFrectunitType() {
		return frectunitType;
	}

	public void setFrectunitType(String frectunitType) {
		this.frectunitType = frectunitType;
	}

	public String getFisinit() {
		return fisinit;
	}

	public void setFisinit(String fisinit) {
		this.fisinit = fisinit;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getRtnDate() {
		return rtnDate;
	}

	public void setRtnDate(String rtnDate) {
		this.rtnDate = rtnDate;
	}

	public String getRtnTime() {
		return rtnTime;
	}

	public void setRtnTime(String rtnTime) {
		this.rtnTime = rtnTime;
	}

	public String getRtnId() {
		return rtnId;
	}

	public void setRtnId(String rtnId) {
		this.rtnId = rtnId;
	}

	public String getRtnNumber() {
		return rtnNumber;
	}

	public void setRtnNumber(String rtnNumber) {
		this.rtnNumber = rtnNumber;
	}

	public String getRtnIsSuccess() {
		return rtnIsSuccess;
	}

	public void setRtnIsSuccess(String rtnIsSuccess) {
		this.rtnIsSuccess = rtnIsSuccess;
	}

	public String getRtnErrorCode() {
		return rtnErrorCode;
	}

	public void setRtnErrorCode(String rtnErrorCode) {
		this.rtnErrorCode = rtnErrorCode;
	}

	public String getRtnMessage() {
		return rtnMessage;
	}

	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}

	public String getRtnStackTrace() {
		return rtnStackTrace;
	}

	public void setRtnStackTrace(String rtnStackTrace) {
		this.rtnStackTrace = rtnStackTrace;
	}

	public String getRtnErrors() {
		return rtnErrors;
	}

	public void setRtnErrors(String rtnErrors) {
		this.rtnErrors = rtnErrors;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getBalStatus() {
		return balStatus;
	}

	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getFpurposeidName() {
		return fpurposeidName;
	}

	public void setFpurposeidName(String fpurposeidName) {
		this.fpurposeidName = fpurposeidName;
	}

	public String getFpurposeidNumber() {
		return fpurposeidNumber;
	}

	public void setFpurposeidNumber(String fpurposeidNumber) {
		this.fpurposeidNumber = fpurposeidNumber;
	}

	public BigDecimal getFpaytotalAmountFor() {
		return fpaytotalAmountFor;
	}

	public void setFpaytotalAmountFor(BigDecimal fpaytotalAmountFor) {
		this.fpaytotalAmountFor = fpaytotalAmountFor;
	}

	public BigDecimal getFpayAmountForE() {
		return fpayAmountForE;
	}

	public void setFpayAmountForE(BigDecimal fpayAmountForE) {
		this.fpayAmountForE = fpayAmountForE;
	}

	public BigDecimal getFsettlepayAmountFor() {
		return fsettlepayAmountFor;
	}

	public void setFsettlepayAmountFor(BigDecimal fsettlepayAmountFor) {
		this.fsettlepayAmountFor = fsettlepayAmountFor;
	}

	public BigDecimal getFrealpayAmountForD() {
		return frealpayAmountForD;
	}

	public void setFrealpayAmountForD(BigDecimal frealpayAmountForD) {
		this.frealpayAmountForD = frealpayAmountForD;
	}

	public String getFaccountidName() {
		return faccountidName;
	}

	public void setFaccountidName(String faccountidName) {
		this.faccountidName = faccountidName;
	}

	public String getFaccountidNumber() {
		return faccountidNumber;
	}

	public void setFaccountidNumber(String faccountidNumber) {
		this.faccountidNumber = faccountidNumber;
	}

	public String getFbanktypeRecName() {
		return fbanktypeRecName;
	}

	public void setFbanktypeRecName(String fbanktypeRecName) {
		this.fbanktypeRecName = fbanktypeRecName;
	}

	public String getFbanktypeRecNumber() {
		return fbanktypeRecNumber;
	}

	public void setFbanktypeRecNumber(String fbanktypeRecNumber) {
		this.fbanktypeRecNumber = fbanktypeRecNumber;
	}

	public String getFoppositeBankAccount() {
		return foppositeBankAccount;
	}

	public void setFoppositeBankAccount(String foppositeBankAccount) {
		this.foppositeBankAccount = foppositeBankAccount;
	}

	public String getFoppositeCcountName() {
		return foppositeCcountName;
	}

	public void setFoppositeCcountName(String foppositeCcountName) {
		this.foppositeCcountName = foppositeCcountName;
	}

	public String getFoppositeBankName() {
		return foppositeBankName;
	}

	public void setFoppositeBankName(String foppositeBankName) {
		this.foppositeBankName = foppositeBankName;
	}

	public String getFopenAddressRec() {
		return fopenAddressRec;
	}

	public void setFopenAddressRec(String fopenAddressRec) {
		this.fopenAddressRec = fopenAddressRec;
	}

	public String getCnaps() {
		return cnaps;
	}

	public void setCnaps(String cnaps) {
		this.cnaps = cnaps;
	}

	public String getCostid() {
		return costid;
	}

	public void setCostid(String costid) {
		this.costid = costid;
	}

	public BigDecimal getFpayAmountE() {
		return fpayAmountE;
	}

	public void setFpayAmountE(BigDecimal fpayAmountE) {
		this.fpayAmountE = fpayAmountE;
	}

}
