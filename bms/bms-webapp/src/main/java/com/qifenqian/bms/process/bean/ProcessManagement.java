package com.qifenqian.bms.process.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ProcessManagement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6503503956292500968L;
	
	
	/**
     * 申请流程id
     */
    private String processId;

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
     * 规格型号LIST
     */
  	private List<Map> modelInfoList;
  	
  	

    public List<Map> getModelInfoList() {
		return modelInfoList;
	}

    
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setModelInfoList(List<Map> modelInfoList) {
		this.modelInfoList = modelInfoList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
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
