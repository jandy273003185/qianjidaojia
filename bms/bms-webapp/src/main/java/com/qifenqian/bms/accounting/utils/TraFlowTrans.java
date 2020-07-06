package com.qifenqian.bms.accounting.utils;

import java.math.BigDecimal;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;

/**
* 交易--交易流水表
*/
public class TraFlowTrans {

	/**
	 * 本系统交易流水号
	 */
    private String transId;
    
    /**
     * 交易报文编号
     */
    private String msgSubId;

	/**
	 * 报文类型：
            SEV_CHARGE--七分钱-充值； 
            SEV_CASH_O--七分钱-提现；
            SEV_TRANSF--七分钱-转账或信用卡还款；
            SEV_PAYMEN--七分钱-交易支付（组合流程）；
            SEV_RETURN--七分钱-退款（组合流程）；
            QFB_TRA_IN--七分宝-转入（组合流程）；
            QFB_TRA_OT--七分宝-转出（组合流程）；
	 */
    private MsgType msgType;

	/**
	 * 交易类型：
            ONL_TO_SEV--网银入账七分钱；
            CAD_TO_SEV--快捷支付至七分钱；
            SEV_TO_CAD--七分钱至银行卡；
            SEV_TO_SEV--七分钱至七分钱；
            SEV_TO_QFB--七分钱至七分宝；
            QFB_TO_SEV--七分宝至七分钱；
	 */
    private TransferTransType transType;

	/**
	 * 是否发报：1--发报；0--不发报
	 */
    private String sendNeed;

	/**
	 * 是否实时：1--实时；0--非实时
	 */
    private String inRealTime;

	/**
	 * 是否加急：1--加急；0--不加急
	 */
    private String isRapid;

	/**
	 * 付方客户ID
	 */
    private String payCustId;

	/**
	 * 付方账户类型：
            BANK_CUS_0--银行-客户借记账户；
            BANK_CUS_1--银行-客户贷记账户；
            BANK_SEV_0--银行-七分钱账户；
            BANK_QFB_0--银行-七分宝账户；
            SEVN_CUS_0--七分钱-客户账户；
            SEVN_SEV_0--七分钱-七分钱账户；
            SEVN_SEV_1--七分钱-七分钱担保账户；
            SEVN_SEV_2--七分钱-七分钱手续费账户；
            QFB0_CUS_0--七分宝-客户账户；
            QFB0_QFB_0--七分宝-七分宝账户；
	 */
    private AcctType payAcctType;

	/**
	 * 付方银行账号
	 */
    private String payAcctNo;

	/**
	 * 付方账户名
	 */
    private String payAcctName;

	/**
	 * 收方客户ID
	 */
    private String rcvCustId;

	/**
	 * 收方账户类型：
            BANK_CUS_0--银行-客户借记账户；
            BANK_CUS_1--银行-客户贷记账户；
            BANK_SEV_0--银行-七分钱账户；
            BANK_QFB_0--银行-七分宝账户；
            SEVN_CUS_0--七分钱-客户账户；
            SEVN_SEV_0--七分钱-七分钱账户；
            SEVN_SEV_1--七分钱-七分钱担保账户；
            SEVN_SEV_2--七分钱-七分钱手续费账户；
            QFB0_CUS_0--七分宝-客户账户；
            QFB0_QFB_0--七分宝-七分宝账户；
	 */
    private AcctType rcvAcctType;

	/**
	 * 收方银行账号
	 */
    private String rcvAcctNo;

	/**
	 * 收方账户名
	 */
    private String rcvAcctName;

	/**
	 * 币别
	 */
    private String currCode;

	/**
	 * 交易金额
	 */
    private BigDecimal transAmt;

	/**
	 * 交易记录时间
	 */
    private Date transTime;

	/**
	 * 交易状态
	 */
    private String statusFlag;

	/**
	 * 对账状态
	 */
    private String checkStatus;

	/**
	 * 返回码
	 */
    private String rtnCode;

	/**
	 * 返回信息
	 */
    private String rtnInfo;

	/**
	 * 返回时间
	 */
    private Date rtnTime;

	/**
	 * 外系统编号
	 */
    private String outerSysId;

	/**
	 * 外系统流水号
	 */
    private String ourerSerialId;

	/**
	 * 外系统结算日期（yyyyMMdd）
	 */
    private String outerSettleDate;

	/**
	 * 预留1
	 */
    private String remark1;

	/**
	 * 预留2
	 */
    private String remark2;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public TransferTransType getTransType() {
        return transType;
    }

    public void setTransType(TransferTransType transType) {
        this.transType = transType;
    }

    public String getSendNeed() {
        return sendNeed;
    }

    public void setSendNeed(String sendNeed) {
        this.sendNeed = sendNeed;
    }

    public String getInRealTime() {
        return inRealTime;
    }

    public void setInRealTime(String inRealTime) {
        this.inRealTime = inRealTime;
    }

    public String getIsRapid() {
        return isRapid;
    }

    public void setIsRapid(String isRapid) {
        this.isRapid = isRapid;
    }

    public String getPayCustId() {
        return payCustId;
    }

    public void setPayCustId(String payCustId) {
        this.payCustId = payCustId;
    }

    public AcctType getPayAcctType() {
        return payAcctType;
    }

    public void setPayAcctType(AcctType payAcctType) {
        this.payAcctType = payAcctType;
    }

    public String getPayAcctNo() {
        return payAcctNo;
    }

    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }

    public String getPayAcctName() {
        return payAcctName;
    }

    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName;
    }

    public String getRcvCustId() {
        return rcvCustId;
    }

    public void setRcvCustId(String rcvCustId) {
        this.rcvCustId = rcvCustId;
    }

    public AcctType getRcvAcctType() {
        return rcvAcctType;
    }

    public void setRcvAcctType(AcctType rcvAcctType) {
        this.rcvAcctType = rcvAcctType;
    }

    public String getRcvAcctNo() {
        return rcvAcctNo;
    }

    public void setRcvAcctNo(String rcvAcctNo) {
        this.rcvAcctNo = rcvAcctNo;
    }

    public String getRcvAcctName() {
        return rcvAcctName;
    }

    public void setRcvAcctName(String rcvAcctName) {
        this.rcvAcctName = rcvAcctName;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnInfo() {
        return rtnInfo;
    }

    public void setRtnInfo(String rtnInfo) {
        this.rtnInfo = rtnInfo;
    }

    public Date getRtnTime() {
        return rtnTime;
    }

    public void setRtnTime(Date rtnTime) {
        this.rtnTime = rtnTime;
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

    public String getOuterSettleDate() {
        return outerSettleDate;
    }

    public void setOuterSettleDate(String outerSettleDate) {
        this.outerSettleDate = outerSettleDate;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

	public String getMsgSubId() {
		return msgSubId;
	}

	public void setMsgSubId(String msgSubId) {
		this.msgSubId = msgSubId;
	}
    
}
