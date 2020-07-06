package com.qifenqian.bms.basemanager.messagelog.bean;

import java.io.Serializable;

public class BmsMessageLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4043642493139802170L;

	private Integer msgId;

    private String orderId;

    private String executeDate;

    private String msgTo;

    private String addresser;

    private String ccTo;

    private String executeDatetime;

    private String content;

    private String msgType;

    private String subject;

    private String attachmentpaths;

    private String attachmentnames;
    
    private String state;
    
    private String logId;
    
    private String businessType;
    
    private String clientIp;
    
    private String beginTime;
    
    private String endTime;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }

    public String getCcTo() {
        return ccTo;
    }

    public void setCcTo(String ccTo) {
        this.ccTo = ccTo;
    }

    public String getExecuteDatetime() {
        return executeDatetime;
    }

    public void setExecuteDatetime(String executeDatetime) {
        this.executeDatetime = executeDatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachmentpaths() {
        return attachmentpaths;
    }

    public void setAttachmentpaths(String attachmentpaths) {
        this.attachmentpaths = attachmentpaths;
    }

    public String getAttachmentnames() {
        return attachmentnames;
    }

    public void setAttachmentnames(String attachmentnames) {
        this.attachmentnames = attachmentnames;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}