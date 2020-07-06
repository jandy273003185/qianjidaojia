package com.qifenqian.bms.basemanager.question.bean;

import java.io.Serializable;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2799978660346633426L;
	
	/*******************************************************/
	/**问题编号**/private String questNo;
	
	/**问题内容**/private String questContent;
				 private String status; 
	
	/*******************************************************/
	
	
	public String getQuestNo() {
		return questNo;
	}
	public void setQuestNo(String questNo) {
		this.questNo = questNo;
	}
	public String getQuestContent() {
		return questContent;
	}
	public void setQuestContent(String questContent) {
		this.questContent = questContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
