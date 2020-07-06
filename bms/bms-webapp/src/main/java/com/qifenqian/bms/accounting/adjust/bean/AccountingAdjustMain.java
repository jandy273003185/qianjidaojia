package com.qifenqian.bms.accounting.adjust.bean;

import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues.IsAdjustJGKJ;

/**
 * 调账业务主表
 */
public class AccountingAdjustMain {

	private String opId;

	/**
	 * 工作流实例编号
	 */
	private String processInstanceId;

	/**
	 * 经办日期
	 */
	private String handleDatetime;

	/**
	 * 经办人ID
	 */
	private String handlerUid;

	/**
	 * 复核日期
	 */
	private Date checkDatetime;

	/**
	 * 复核人ID
	 */
	private String checkerUid;

	/**
	 * 总经理审批日期
	 */
	private Date approvalDatetime;

	/**
	 * 总经理ID
	 */
	private String managerUid;

	/**
	 * 业务归属机构
	 */
	private String orgId;

	/**
	 * 完结标识 0-未完结; 1-已完结; 2-被删除
	 */
	private String finishedFlag;

	/**
	 * 删除日期
	 */
	private Date deleteDatetime;

	/**
	 * 关联交易业务编号
	 */
	private String relationOpId;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 是否是单边账务
	 */
	private String singleFlag;

	/**
	 * 初次进入页面
	 */
	private String firstFlag;

	/**
	 * 起始日期
	 */
	private String handleDateStart;

	/**
	 * 截止日期
	 */
	private String handleDateEnd;

	/**
	 * 查询条件账号
	 */
	private String acctNo;

	/**
	 * 账务分录ID
	 */
	private String entryId;

	/**
	 * 借方客户号
	 */
	private String debitCustId;

	/**
	 * 借方科目
	 */
	private String debitSubjectId;

	/**
	 * 贷方客户号
	 */
	private String creditCustId;

	/**
	 * 贷方科目
	 */
	private String creditSubjectId;

	/**
	 * 是否联通交广科技调账
	 */
	private IsAdjustJGKJ isAdjustJGKJ;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getHandleDatetime() {
		return handleDatetime;
	}

	public void setHandleDatetime(String handleDatetime) {
		this.handleDatetime = handleDatetime;
	}

	public String getHandlerUid() {
		return handlerUid;
	}

	public void setHandlerUid(String handlerUid) {
		this.handlerUid = handlerUid;
	}

	public Date getCheckDatetime() {
		return checkDatetime;
	}

	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

	public String getCheckerUid() {
		return checkerUid;
	}

	public void setCheckerUid(String checkerUid) {
		this.checkerUid = checkerUid;
	}

	public Date getApprovalDatetime() {
		return approvalDatetime;
	}

	public void setApprovalDatetime(Date approvalDatetime) {
		this.approvalDatetime = approvalDatetime;
	}

	public String getManagerUid() {
		return managerUid;
	}

	public void setManagerUid(String managerUid) {
		this.managerUid = managerUid;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getFinishedFlag() {
		return finishedFlag;
	}

	public void setFinishedFlag(String finishedFlag) {
		this.finishedFlag = finishedFlag;
	}

	public Date getDeleteDatetime() {
		return deleteDatetime;
	}

	public void setDeleteDatetime(Date deleteDatetime) {
		this.deleteDatetime = deleteDatetime;
	}

	public String getRelationOpId() {
		return relationOpId;
	}

	public void setRelationOpId(String relationOpId) {
		this.relationOpId = relationOpId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSingleFlag() {
		return singleFlag;
	}

	public void setSingleFlag(String singleFlag) {
		this.singleFlag = singleFlag;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}

	public String getHandleDateStart() {
		return handleDateStart;
	}

	public void setHandleDateStart(String handleDateStart) {
		this.handleDateStart = handleDateStart;
	}

	public String getHandleDateEnd() {
		return handleDateEnd;
	}

	public void setHandleDateEnd(String handleDateEnd) {
		this.handleDateEnd = handleDateEnd;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getDebitCustId() {
		return debitCustId;
	}

	public void setDebitCustId(String debitCustId) {
		this.debitCustId = debitCustId;
	}

	public String getDebitSubjectId() {
		return debitSubjectId;
	}

	public void setDebitSubjectId(String debitSubjectId) {
		this.debitSubjectId = debitSubjectId;
	}

	public String getCreditCustId() {
		return creditCustId;
	}

	public void setCreditCustId(String creditCustId) {
		this.creditCustId = creditCustId;
	}

	public String getCreditSubjectId() {
		return creditSubjectId;
	}

	public void setCreditSubjectId(String creditSubjectId) {
		this.creditSubjectId = creditSubjectId;
	}

	public IsAdjustJGKJ getIsAdjustJGKJ() {
		return isAdjustJGKJ;
	}

	public void setIsAdjustJGKJ(IsAdjustJGKJ isAdjustJGKJ) {
		this.isAdjustJGKJ = isAdjustJGKJ;
	}

}
