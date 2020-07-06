package com.qifenqian.bms.basemanager.photo.bean;

import java.io.Serializable;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
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
	private String uploadTime;
	/** 创建人 */
	private String createId;
	/** 创建时间 */
	private String createTime;
	/** 修改人 */
	private String modifyId;
	/** 修改时间 */
	private String modifyTime;

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

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
