package com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean;

import java.math.BigDecimal;
import java.util.Date;

public class KingdeePayEntry {
	/** 清算编号/单据编号 */
	private String clearId;
	/** 序号 */
	private String entryId;
	/** 付款用途名称 */
	private String fpurposeidName;
	/** 付款用途编码 */
	private String fpurposeidNumber;
	/** 应付金额 */
	private BigDecimal fpaytotalAmountFor;
	/** 付款金额 */
	private BigDecimal fpayAmountForE;
	/** 折后金额 */
	private BigDecimal fsettlepayAmountFor;
	/** 实付金额 */
	private BigDecimal frealpayAmountForD;
	/** 我方银行账号名称 */
	private String faccountidName;
	/** 我方银行账号编码 */
	private String faccountidNumber;
	/** 收方账户类型 */
	private String frecType;
	/** 收款银行名称 */
	private String fbanktypeRecName;
	/** 收款银行编码 */
	private String fbanktypeRecNumber;
	/** 对方银行账号 */
	private String foppositeBankAccount;
	/** 对方账户名称 */
	private String foppositeCcountName;
	/** 对方开户行 */
	private String foppositeBankName;
	/** 开户行地址 */
	private String fopenAddressRec;
	/** 联行号 */
	private String fCnaps;
	/** 省（收方） */
	private String fProvince;
	/** 市（收方） */
	private String fCity;
	/** 地区（收方） */
	private String fDistrict;
	/** 费用项目编码 */
	private String fCostid;
	/** 付款金额本位币 */
	private BigDecimal fpayAmountE;
	/** 写入人 */
	private String instUser;
	/** 写入时间 */
	private Date instDatetime;
	/**手续费**/
	private BigDecimal fhandlingChargeFor;
	/**手续费本位币**/
	private BigDecimal fhandlingCharge;

	public String getClearId() {
		return clearId;
	}

	public void setClearId(String clearId) {
		this.clearId = clearId;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getFpurposeidName() {
		return fpurposeidName;
	}

	public void setFpurposeidName(String fpurposeidName) {
		this.fpurposeidName = fpurposeidName;
	}

	public String getFpurposeidNumber() {
		return fpurposeidNumber;
	}

	public void setFpurposeidNumber(String fpurposeidNumber) {
		this.fpurposeidNumber = fpurposeidNumber;
	}

	public BigDecimal getFpaytotalAmountFor() {
		return fpaytotalAmountFor;
	}

	public void setFpaytotalAmountFor(BigDecimal fpaytotalAmountFor) {
		this.fpaytotalAmountFor = fpaytotalAmountFor;
	}

	public BigDecimal getFpayAmountForE() {
		return fpayAmountForE;
	}

	public void setFpayAmountForE(BigDecimal fpayAmountForE) {
		this.fpayAmountForE = fpayAmountForE;
	}

	public BigDecimal getFsettlepayAmountFor() {
		return fsettlepayAmountFor;
	}

	public void setFsettlepayAmountFor(BigDecimal fsettlepayAmountFor) {
		this.fsettlepayAmountFor = fsettlepayAmountFor;
	}

	public BigDecimal getFrealpayAmountForD() {
		return frealpayAmountForD;
	}

	public void setFrealpayAmountForD(BigDecimal frealpayAmountForD) {
		this.frealpayAmountForD = frealpayAmountForD;
	}

	public String getFaccountidName() {
		return faccountidName;
	}

	public void setFaccountidName(String faccountidName) {
		this.faccountidName = faccountidName;
	}

	public String getFaccountidNumber() {
		return faccountidNumber;
	}

	public void setFaccountidNumber(String faccountidNumber) {
		this.faccountidNumber = faccountidNumber;
	}

	public String getFrecType() {
		return frecType;
	}

	public void setFrecType(String frecType) {
		this.frecType = frecType;
	}

	public String getFbanktypeRecName() {
		return fbanktypeRecName;
	}

	public void setFbanktypeRecName(String fbanktypeRecName) {
		this.fbanktypeRecName = fbanktypeRecName;
	}

	public String getFbanktypeRecNumber() {
		return fbanktypeRecNumber;
	}

	public void setFbanktypeRecNumber(String fbanktypeRecNumber) {
		this.fbanktypeRecNumber = fbanktypeRecNumber;
	}

	public String getFoppositeBankAccount() {
		return foppositeBankAccount;
	}

	public void setFoppositeBankAccount(String foppositeBankAccount) {
		this.foppositeBankAccount = foppositeBankAccount;
	}

	public String getFoppositeCcountName() {
		return foppositeCcountName;
	}

	public void setFoppositeCcountName(String foppositeCcountName) {
		this.foppositeCcountName = foppositeCcountName;
	}

	public String getFoppositeBankName() {
		return foppositeBankName;
	}

	public void setFoppositeBankName(String foppositeBankName) {
		this.foppositeBankName = foppositeBankName;
	}

	public String getFopenAddressRec() {
		return fopenAddressRec;
	}

	public void setFopenAddressRec(String fopenAddressRec) {
		this.fopenAddressRec = fopenAddressRec;
	}

	public String getfCnaps() {
		return fCnaps;
	}

	public void setfCnaps(String fCnaps) {
		this.fCnaps = fCnaps;
	}

	public String getfProvince() {
		return fProvince;
	}

	public void setfProvince(String fProvince) {
		this.fProvince = fProvince;
	}

	public String getfCity() {
		return fCity;
	}

	public void setfCity(String fCity) {
		this.fCity = fCity;
	}

	public String getfDistrict() {
		return fDistrict;
	}

	public void setfDistrict(String fDistrict) {
		this.fDistrict = fDistrict;
	}

	public String getfCostid() {
		return fCostid;
	}

	public void setfCostid(String fCostid) {
		this.fCostid = fCostid;
	}

	public BigDecimal getFpayAmountE() {
		return fpayAmountE;
	}

	public void setFpayAmountE(BigDecimal fpayAmountE) {
		this.fpayAmountE = fpayAmountE;
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

	public BigDecimal getFhandlingChargeFor() {
		return fhandlingChargeFor;
	}

	public void setFhandlingChargeFor(BigDecimal fhandlingChargeFor) {
		this.fhandlingChargeFor = fhandlingChargeFor;
	}

	public BigDecimal getFhandlingCharge() {
		return fhandlingCharge;
	}

	public void setFhandlingCharge(BigDecimal fhandlingCharge) {
		this.fhandlingCharge = fhandlingCharge;
	}

}
