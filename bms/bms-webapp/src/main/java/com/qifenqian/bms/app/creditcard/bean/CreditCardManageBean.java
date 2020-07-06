package com.qifenqian.bms.app.creditcard.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 信用卡申请链接管理实体类
 * @author hongjiagui
 *
 */
public class CreditCardManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2796199829155002966L;

	/**
	 * ID
	 */
	private String cardId;
	
	/**
	 * 信用卡名称
	 */
	private String cardName;
	
	/**
	 * 上线日期
	 */
	private Date createTime;

	/**
	 * 链接地址
	 */
	private String linkUrl;
	
	/**
	 * 状态
	 * 0-隐藏 ， 1-正常
	 */
	private String status;
	
	/**
	 * 排序
	 */
	private Integer sort;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public CreditCardManageBean() {
		
	}
	
	public CreditCardManageBean(String cardId, String cardName, Date createTime, String linkUrl, String status,
			Integer sort) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.createTime = createTime;
		this.linkUrl = linkUrl;
		this.status = status;
		this.sort = sort;
	}
	

	
}
