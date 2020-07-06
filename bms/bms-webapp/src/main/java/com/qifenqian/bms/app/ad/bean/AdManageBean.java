package com.qifenqian.bms.app.ad.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * APP登录页广告管理实体类
 * @author hongjiagui
 *
 */
public class AdManageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5482510994497392211L;

	/**
	 * Id
	 */
	private String adId;
	/**
	 * 广告名称
	 */
	private String adName;
	/**
	 * 图片路径
	 */
	private String imgPath;
	/**
	 * 显示时间
	 */
	private String showTime;
	/**
	 * 状态  1-正常，0-无效
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createUser;
	
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
	
}
