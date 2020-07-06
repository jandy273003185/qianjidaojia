package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户证件扫描信息
 */
public class TdCustScanCopy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6303680316451520419L;
	
	/**
	 * pk主键
	 */
	private int scanId;

	/** 
	 * 客户编号.
	 */
	private String custId;

	/**
	 * 证件审核表 pk主键
	 */
	private int authId;

	/** 
	 * 扫描件类型：
	           00 身份证
	           01 税务登记证
	           02 营业执照
	           .
	 */
	private String certifyType;

	/**
	 * 证件号码
	 */
	private String certifyNo;
	
	/** 
	 * 客户名称，冗余.
	 */
	private String custName;

	/** 
	 * 扫描件文件路径.
	 */
	private String scanCopyPath;

	/**
	 * 上传（申请）时间
	 */
	private Date uploadTime;
	
	/** 
	 * 创建人.
	 */
	private String createId;

	/** 
	 * 创建时间.
	 */
	private Date createTime;

	/** 
	 * 修改人.
	 */
	private String modifyId;

	/** 
	 * 修改时间.
	 */
	private Date modifyTime;

	public int getScanId() {
		return scanId;
	}

	public void setScanId(int scanId) {
		this.scanId = scanId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
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
	
}
