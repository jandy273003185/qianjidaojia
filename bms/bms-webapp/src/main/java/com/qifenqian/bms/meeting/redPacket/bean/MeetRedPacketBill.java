package com.qifenqian.bms.meeting.redPacket.bean;

import java.math.BigDecimal;

public class MeetRedPacketBill {
    private String redPacketSn;

    private String receiveCustId;
    
    private String custName;
    
    private String mobile;
    
    private String giveCustId;

    private String redPacketType;

    private String redPacketName;

    private BigDecimal redPacketAmt;

    private String status;

    private String memo;

    private String relationId;
    
    private String createTime;

    private String modifyTime;
    
    

    
    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRedPacketSn() {
        return redPacketSn;
    }

    public void setRedPacketSn(String redPacketSn) {
        this.redPacketSn = redPacketSn;
    }

    public String getReceiveCustId() {
        return receiveCustId;
    }

    public void setReceiveCustId(String receiveCustId) {
        this.receiveCustId = receiveCustId;
    }

    public String getGiveCustId() {
        return giveCustId;
    }

    public void setGiveCustId(String giveCustId) {
        this.giveCustId = giveCustId;
    }

    public String getRedPacketType() {
        return redPacketType;
    }

    public void setRedPacketType(String redPacketType) {
        this.redPacketType = redPacketType;
    }

    public String getRedPacketName() {
        return redPacketName;
    }

    public void setRedPacketName(String redPacketName) {
        this.redPacketName = redPacketName;
    }

    public BigDecimal getRedPacketAmt() {
        return redPacketAmt;
    }

    public void setRedPacketAmt(BigDecimal redPacketAmt) {
        this.redPacketAmt = redPacketAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

   

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	
}