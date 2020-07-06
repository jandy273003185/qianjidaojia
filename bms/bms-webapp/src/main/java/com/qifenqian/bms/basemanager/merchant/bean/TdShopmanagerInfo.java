package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;
import java.util.Date;

public class TdShopmanagerInfo implements Serializable {

	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7022995337064431655L;

	private String shopmanagerId;

	    /**
	     * 店长所属商户编号
	     */
	    private String custId;

	    /**
	     * 店长手机号
	     */
	    private String shopmanagerMobile;

	    /**
	     * 店长姓名
	     */
	    private String shopmanagerName;

	    /**
	     * 是否有退款操作权限，0：无，1：有
	     */
	    private String refundAuth;

	    /**
	     * 查询权限 1查询本店
	     */
	    private String queryAuth;

	    /**
	     * 创建人
	     */
	    private String createId;

	    /**
	     * 创建时间
	     */
	    private Date createTime;

	    /**
	     * 修改人
	     */
	    private String modifyId;

	    /**
	     * 修改时间
	     */
	    private Date modifyTime;

	    /**
	     * 0：失效，1：生效
	     */
	    private String status;

	    /**
	     * 数字密码+密码盐值md5加密
	     */
	    private String loginPw;

	    /**
	     * 数字密码+密码盐值md5加密
	     */
	    private String refundPw;

	    /**
	     * 登录盐值
	     */
	    private String loginSalt;

	    /**
	     * 退款盐值
	     */
	    private String refundSalt;

	    public String getShopmanagerId() {
	        return shopmanagerId;
	    }

	    public void setShopmanagerId(String shopmanagerId) {
	        this.shopmanagerId = shopmanagerId;
	    }

	    public String getCustId() {
	        return custId;
	    }

	    public void setCustId(String custId) {
	        this.custId = custId;
	    }

	    public String getShopmanagerMobile() {
	        return shopmanagerMobile;
	    }

	    public void setShopmanagerMobile(String shopmanagerMobile) {
	        this.shopmanagerMobile = shopmanagerMobile;
	    }

	    public String getShopmanagerName() {
	        return shopmanagerName;
	    }

	    public void setShopmanagerName(String shopmanagerName) {
	        this.shopmanagerName = shopmanagerName;
	    }

	    public String getRefundAuth() {
	        return refundAuth;
	    }

	    public void setRefundAuth(String refundAuth) {
	        this.refundAuth = refundAuth;
	    }

	    public String getQueryAuth() {
	        return queryAuth;
	    }

	    public void setQueryAuth(String queryAuth) {
	        this.queryAuth = queryAuth;
	    }

	    public String getCreateId() {
	        return createId;
	    }

	    public void setCreateId(String createId) {
	        this.createId = createId;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public String getModifyId() {
	        return modifyId;
	    }

	    public void setModifyId(String modifyId) {
	        this.modifyId = modifyId;
	    }

	    public Date getModifyTime() {
	        return modifyTime;
	    }

	    public void setModifyTime(Date modifyTime) {
	        this.modifyTime = modifyTime;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getLoginPw() {
	        return loginPw;
	    }

	    public void setLoginPw(String loginPw) {
	        this.loginPw = loginPw;
	    }

	    public String getRefundPw() {
	        return refundPw;
	    }

	    public void setRefundPw(String refundPw) {
	        this.refundPw = refundPw;
	    }

	    public String getLoginSalt() {
	        return loginSalt;
	    }

	    public void setLoginSalt(String loginSalt) {
	        this.loginSalt = loginSalt;
	    }

	    public String getRefundSalt() {
	        return refundSalt;
	    }

	    public void setRefundSalt(String refundSalt) {
	        this.refundSalt = refundSalt;
	    }

}
