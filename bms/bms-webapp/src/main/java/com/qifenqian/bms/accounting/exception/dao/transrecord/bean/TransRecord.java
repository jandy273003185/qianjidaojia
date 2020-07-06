package com.qifenqian.bms.accounting.exception.dao.transrecord.bean;

import java.io.Serializable;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * 
 * @project sevenpay-bms-web
 * @fileName TransRecord.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class TransRecord implements Serializable{

	private static final long serialVersionUID = 7415646590903544502L;

	/**
	 * 本系统报文编号
	 */
    private String msgId;

	/**
	 * 报文类型：
            CRT_ACT_BA--创建账户；
            BND_CAD_UN--绑定/解绑银行卡；
            QRY_ACT_IF--账户信息查询；（组合）
            QRY_TRA_RS--交易结果查询；（批量）
            QRY_TRA_DT--交易明细查询；
            SEV_CHARGE--七分钱-充值； 
            SEV_CASH_O--七分钱-提现；
            SEV_TRANSF--七分钱-转账或信用卡还款；
            SEV_PAYMEN--七分钱-交易支付（组合流程）；
            SEV_RETURN--七分钱-退款（组合流程）；
            QFB_TRA_IN--七分宝-转入（组合流程）；
            QFB_TRA_OT--七分宝-转出；
	 */
    private RequestColumnValues.MsgType msgType;

	/**
	 * 发起方系统编号
	 */
    private RequestColumnValues.ReqSysId reqSysId;

	/**
	 * 发起方请求流水号
	 */
    private String reqSerialId;

	/**
	 * 报文明细条数
	 */
    private Integer reqMsgNum;

	/**
	 * 发起方服务器IP
	 */
    private String reqServerIp;

	/**
	 * 发起方请求时间
	 */
    private Date reqDatetime;

	/**
	 * 报文状态
	 */
    private RequestColumnValues.MsgStatus status;

	/**
	 * 报文记录时间
	 */
    private Date instDatetime;

	/**
	 * 返回码
	 */
    private RequestColumnValues.RtnCode rtnCode;

	/**
	 * 返回信息
	 */
    private String rtnInfo;

	/**
	 * 报文响应时间
	 */
    private Date rtnDatetime;
    
    /** 核心服务器ip*/
    private String recordIp;

	/**
	 * 请求报文存储路径
	 */
    private String reqFilepath;

	/**
	 * 响应报文存储路径
	 */
    private String rtnFilepath;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReqSerialId() {
        return reqSerialId;
    }

    public void setReqSerialId(String reqSerialId) {
        this.reqSerialId = reqSerialId;
    }

    public Integer getReqMsgNum() {
        return reqMsgNum;
    }

    public void setReqMsgNum(Integer reqMsgNum) {
        this.reqMsgNum = reqMsgNum;
    }

    public String getReqServerIp() {
        return reqServerIp;
    }

    public void setReqServerIp(String reqServerIp) {
        this.reqServerIp = reqServerIp;
    }

    public String getRtnInfo() {
        return rtnInfo;
    }

    public void setRtnInfo(String rtnInfo) {
        this.rtnInfo = rtnInfo;
    }

    public String getReqFilepath() {
        return reqFilepath;
    }

    public void setReqFilepath(String reqFilepath) {
        this.reqFilepath = reqFilepath;
    }

    public String getRtnFilepath() {
        return rtnFilepath;
    }

    public void setRtnFilepath(String rtnFilepath) {
        this.rtnFilepath = rtnFilepath;
    }

	public Date getReqDatetime() {
		return reqDatetime;
	}

	public void setReqDatetime(Date reqDatetime) {
		this.reqDatetime = reqDatetime;
	}

	public String getRecordIp() {
		return recordIp;
	}

	public void setRecordIp(String recordIp) {
		this.recordIp = recordIp;
	}

	public Date getRtnDatetime() {
		return rtnDatetime;
	}

	public void setRtnDatetime(Date rtnDatetime) {
		this.rtnDatetime = rtnDatetime;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public RequestColumnValues.MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(RequestColumnValues.MsgType msgType) {
		this.msgType = msgType;
	}

	public RequestColumnValues.ReqSysId getReqSysId() {
		return reqSysId;
	}

	public void setReqSysId(RequestColumnValues.ReqSysId reqSysId) {
		this.reqSysId = reqSysId;
	}

	public RequestColumnValues.MsgStatus getStatus() {
		return status;
	}

	public void setStatus(RequestColumnValues.MsgStatus status) {
		this.status = status;
	}

	public RequestColumnValues.RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RequestColumnValues.RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}
}


