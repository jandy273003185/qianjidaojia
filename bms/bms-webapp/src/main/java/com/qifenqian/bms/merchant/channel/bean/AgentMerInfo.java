package com.qifenqian.bms.merchant.channel.bean;
/**
 * 通道详情表
 * @author admin
 *
 */
public class AgentMerInfo {

	
/**********************************************************/
	private String channelCode;//通道号
	private String merCustId;//商户的custid
	private String merNo;//商户号
	private String appId;//APPID
	private String appIdKey;//APPKEY
	private String createTime;//创建时间
	private String modifyTime;//修改时间
	private String status;//审核状态 00--可用 01--不可用
	private String publicStatus;//公用开启
	private String reserved;//预留字段
/**********************************************************/

	public String getMerCustId() {
		return merCustId;
	}
	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPublicStatus() {
		return publicStatus;
	}
	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getAppIdKey() {
		return appIdKey;
	}
	public void setAppIdKey(String appIdKey) {
		this.appIdKey = appIdKey;
	}
	
}
