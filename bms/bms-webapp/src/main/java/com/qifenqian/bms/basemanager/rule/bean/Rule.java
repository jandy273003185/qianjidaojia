package com.qifenqian.bms.basemanager.rule.bean;

import java.io.Serializable;

/***
 * 费率
 * 
 * @author pc
 *
 */
public class Rule implements Serializable {
	
	private static final long serialVersionUID = 7236156472640481007L;

	private String feeCode;/**费率编号**/
	private String feeType;/**费率类型（套餐费率/固定费率）**/
	private String feeName;/**费率名称**/
	private String ruleDesc;/**费率内容**/
	private String memo;/**费率描述**/
	private String ruleValues;/**费率值**/
	private String status;/**费率状态**/
	private String instUser;
	private String lupdUser;
	private String instTime;
	private String lupdTime;
	private String custId; /**客户ID**/
	private String operType;/**费率类型**/
	private String mappingId;/**客户费率编号**/

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRuleValues() {
		return ruleValues;
	}

	public void setRuleValues(String ruleValues) {
		this.ruleValues = ruleValues;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getLupdUser() {
		return lupdUser;
	}

	public void setLupdUser(String lupdUser) {
		this.lupdUser = lupdUser;
	}

	public String getInstTime() {
		return instTime;
	}

	public void setInstTime(String instTime) {
		this.instTime = instTime;
	}

	public String getLupdTime() {
		return lupdTime;
	}

	public void setLupdTime(String lupdTime) {
		this.lupdTime = lupdTime;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getMappingId() {
		return mappingId;
	}

	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	
}
