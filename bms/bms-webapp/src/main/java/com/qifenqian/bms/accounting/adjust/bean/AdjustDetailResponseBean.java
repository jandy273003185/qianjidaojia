package com.qifenqian.bms.accounting.adjust.bean;

import java.util.List;

import org.activiti.engine.task.Comment;

public class AdjustDetailResponseBean extends CommonResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 调账main信息
	private AccountingAdjustMain mainInfo = null;
	
	// 调账分录
	private List<AccountingAdjustDetail> entryList = null;
	
	// 备注列表
	private List<Comment> commentList = null;

	public AccountingAdjustMain getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(AccountingAdjustMain mainInfo) {
		this.mainInfo = mainInfo;
	}

	public List<AccountingAdjustDetail> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<AccountingAdjustDetail> entryList) {
		this.entryList = entryList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
