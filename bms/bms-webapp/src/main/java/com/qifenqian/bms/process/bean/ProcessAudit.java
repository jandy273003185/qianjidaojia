package com.qifenqian.bms.process.bean;

import java.io.Serializable;

public class ProcessAudit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884770277052200207L;
	
	/**
     * 流程审核id
     */
    private String processAuditId;

    /**
     * 申请流程id
     */
    private String processId;

    /**
     * 财务审核人
     */
    private String firstAuditId;

    /**
     * 财务审核时间
     */
    private String firstAuditTime;

    /**
     * 财务审核记录
     */
    private String firstAuditRecord;

    /**
     * 总办审核人
     */
    private String secondAuditId;

    /**
     * 总办审核时间
     */
    private String secondAuditTime;

    /**
     * 总办审核记录
     */
    private String secondAuditRecord;

    /**
     * 当前节点
     */
    private String currentNode;

    /**
     * 标题
     */
    private String titleName;

    /**
     * 申请部门
     */
    private String deptId;

    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 申请时间
     */
    private String processTime;

    /**
     * 紧急程度
     */
    private String emergencyDegree;

    /**
     * 服务商客户号
     */
    private String partnerCustId;
    
    /**
     * 商户客户号
     */
    private String custId;

    /**
     * 门店号
     */
    private String shopId;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 需求数量
     */
    private String demandNum;

    /**
     * 最迟领取时间
     */
    private String receiveTime;

    /**
     * 是否打款
     */
    private String remittance;

    /**
     * 说明备注
     */
    private String remark;

    /**
     * 是否邮寄
     */
    private String mail;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货人邮寄地址
     */
    private String deliveryAddress;

    /**
     * 收货人联系方式
     */
    private String contact;
    
    /**
     * 附件
     */
    private String attachment;
    
    /**
     * 申请人
     */
    private String userId;

	/**
	 * 部门名称
	 */
    private String  deptName;

	/**
	 * 第一审核人名称
	 */
	private String firstAuditName;

	/**
	 * 第二审核人名称
	 */
	private String secondAuditName;

	public String getSecondAuditName() {
		return secondAuditName;
	}

	public void setSecondAuditName(String secondAuditName) {
		this.secondAuditName = secondAuditName;
	}

	public String getFirstAuditName() {
		return firstAuditName;
	}

	public void setFirstAuditName(String firstAuditName) {
		this.firstAuditName = firstAuditName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getProcessAuditId() {
        return processAuditId;
    }

    public void setProcessAuditId(String processAuditId) {
        this.processAuditId = processAuditId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getFirstAuditId() {
        return firstAuditId;
    }

    public void setFirstAuditId(String firstAuditId) {
        this.firstAuditId = firstAuditId;
    }

    public String getFirstAuditTime() {
        return firstAuditTime;
    }

    public void setFirstAuditTime(String firstAuditTime) {
        this.firstAuditTime = firstAuditTime;
    }

    public String getFirstAuditRecord() {
        return firstAuditRecord;
    }

    public void setFirstAuditRecord(String firstAuditRecord) {
        this.firstAuditRecord = firstAuditRecord;
    }

    public String getSecondAuditId() {
        return secondAuditId;
    }

    public void setSecondAuditId(String secondAuditId) {
        this.secondAuditId = secondAuditId;
    }

    public String getSecondAuditTime() {
        return secondAuditTime;
    }

    public void setSecondAuditTime(String secondAuditTime) {
        this.secondAuditTime = secondAuditTime;
    }

    public String getSecondAuditRecord() {
        return secondAuditRecord;
    }

    public void setSecondAuditRecord(String secondAuditRecord) {
        this.secondAuditRecord = secondAuditRecord;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public String getEmergencyDegree() {
		return emergencyDegree;
	}

	public void setEmergencyDegree(String emergencyDegree) {
		this.emergencyDegree = emergencyDegree;
	}

	
	public String getPartnerCustId() {
		return partnerCustId;
	}

	public void setPartnerCustId(String partnerCustId) {
		this.partnerCustId = partnerCustId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(String demandNum) {
		this.demandNum = demandNum;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRemittance() {
		return remittance;
	}

	public void setRemittance(String remittance) {
		this.remittance = remittance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
    
    

}
