package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;
import java.util.Date;

public class CustScan implements Serializable {

	/** pk主键 */
	private String scanId;
	/** 客户编号 */
	private String custId;
	/** 证件审核id */
	private String authId;
	/**
	 * 扫描件类型： 00 身份证 01 税务登记证 02 营业执照
	 */
	private String certifyType;
	/** */
	private String certifyNo;
	/** 客户名称，冗余 */
	private String custName;
	/** 扫描件文件路径，多个证件请用分号分隔开。 */
	private String scanCopyPath;
	/** 上传（申请）时间 */
	private Date uploadTime;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private Date modifyTime;
	/** 证件生效时间*/
	private String certifyBeginTime; 
	/** 证件失效时间 */
	private String certifyEndTime;
	/**资料归属类型*/
	private String scanBelong;
	/**状态*/
	private String status;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3253649451039992193L;
	
	

	public String getCertifyBeginTime() {
		return certifyBeginTime;
	}

	public void setCertifyBeginTime(String certifyBeginTime) {
		this.certifyBeginTime = certifyBeginTime;
	}

	public String getCertifyEndTime() {
		return certifyEndTime;
	}

	public void setCertifyEndTime(String certifyEndTime) {
		this.certifyEndTime = certifyEndTime;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getCertifyType() {
		return certifyType;
	}

	public void setCertifyType(String certifyType) {
		this.certifyType = certifyType;
	}

	public String getCertifyNo() {
		return certifyNo;
	}

	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getScanCopyPath() {
		return scanCopyPath;
	}

	public void setScanCopyPath(String scanCopyPath) {
		this.scanCopyPath = scanCopyPath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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

	public String getScanBelong() {
		return scanBelong;
	}

	public void setScanBelong(String scanBelong) {
		this.scanBelong = scanBelong;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
