package com.qifenqian.bms.basemanager.protocoltemplate.bean;

import java.io.Serializable;
import java.util.Date;

public class ProtocolTemplate implements Serializable {
	/**
	 * 协议模板
	 */
	private static final long serialVersionUID = -5283335562779322750L;

	/**编号**/
	private String id;
	/**模板名称**/
	private String protocolTemplateName;
	/**协议类型**/
	private String protocolType;
	/**版本号**/
	private String protocolVersion;
	/**模板内容**/
	private String protocolTemplate;
	/**状态**/
	private String status;
	/**生效日期**/
	private String validDate;
	/**失效日期**/
	private String disableDate;
	/**备注**/
	private String memo;
	/**创建人**/
	private String instUser;
	/**创建时间**/
	private Date instDatetime;
	/**修改人**/
	private String updateUser;
	/**修改时间**/
	private Date updateDatetime;
	
	private String choiceContext;
	
	/**商户类型**/
	private String merchantType;
	
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getChoiceContext() {
		return choiceContext;
	}

	public void setChoiceContext(String choiceContext) {
		this.choiceContext = choiceContext;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocolTemplateName() {
		return protocolTemplateName;
	}

	public void setProtocolTemplateName(String protocolTemplateName) {
		this.protocolTemplateName = protocolTemplateName;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getProtocolTemplate() {
		return protocolTemplate;
	}

	public void setProtocolTemplate(String protocolTemplate) {
		this.protocolTemplate = protocolTemplate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
