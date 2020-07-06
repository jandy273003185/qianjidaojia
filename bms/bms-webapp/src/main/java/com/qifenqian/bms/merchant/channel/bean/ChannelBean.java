package com.qifenqian.bms.merchant.channel.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author sunxun
 *
 */
public class ChannelBean implements Serializable {

	private static final long serialVersionUID = -1974101805090576902L;

	// 渠道
	private ChannelMerRegist channelName;
	private String custId;
	//商户名称
	private String custName;
	// 商户在渠道的ID
	private String merchantChannelId;
	//商户在渠道的KEY
	private String merchantChannelKey;
	private Date createTime;
	private Date modifyTime;
	private ChannelStatus status;
	private String comment;
	//渠道微信子商户号
	private String wxChildNo;
	//渠道支付宝子商户号
	private String zfbChildNo;

	// 具体在渠道的产品
	private List<ChannelDetailBean> details;

	// 商户编号, 仅用于查询
	private String merchantCode;
	
	

	public String getWxChildNo() {
		return wxChildNo;
	}

	public void setWxChildNo(String wxChildNo) {
		this.wxChildNo = wxChildNo;
	}

	public String getZfbChildNo() {
		return zfbChildNo;
	}

	public void setZfbChildNo(String zfbChildNo) {
		this.zfbChildNo = zfbChildNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public List<ChannelDetailBean> getDetails() {
		return details;
	}

	public void setDetails(List<ChannelDetailBean> details) {
		this.details = details;
	}

	public ChannelMerRegist getChannelName() {
		return channelName;
	}

	public void setChannelName(ChannelMerRegist channelName) {
		this.channelName = channelName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public ChannelStatus getStatus() {
		return status;
	}

	public void setStatus(ChannelStatus status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMerchantChannelKey() {
		return merchantChannelKey;
	}

	public void setMerchantChannelKey(String merchantChannelKey) {
		this.merchantChannelKey = merchantChannelKey;
	}

	public String getMerchantChannelId() {
		return merchantChannelId;
	}

	public void setMerchantChannelId(String merchantChannelId) {
		this.merchantChannelId = merchantChannelId;
	}

	@Override
	public String toString() {		
		return JSON.toJSONString(this);
	}

	

}
