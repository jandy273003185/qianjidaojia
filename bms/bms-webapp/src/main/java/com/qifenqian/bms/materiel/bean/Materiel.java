package com.qifenqian.bms.materiel.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料管理bean
 * 
 * @project qifenqian-bms
 * @fileName Materiel.java
 * @author wuzz
 * @date 2019年11月4日
 * @memo
 */
public class Materiel implements Serializable {

	private static final long serialVersionUID = -9010748539674202829L;

	/** 物料编号 */
	private Integer id;
	/** 设备编号 */
	private String machineId;
	/** 设备类型 */
	private String machineType;
	/** 录入时间 */
	private Date inputTime;
	/** 供应商 */
	private String supplier;
	/** 机器状态 */
	private String machineState;
	/** 所用商户 */
	private String usedMerchant;
	/** 所用门店 */
	private String usedStores;
	/** 领取人 */
	private String receiver;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private Date createTime;
	/** 最后修改人 */
	private String lastupdateUser;
	/** 最后更改时间 */
	private Date lupdDatetime;
	/** 备注 */
	private String memo;
	/**所属服务商名称*/
	private String serviceParenterName;
	
	
	
	public String getServiceParenterName() {
		return serviceParenterName;
	}
	public void setServiceParenterName(String serviceParenterName) {
		this.serviceParenterName = serviceParenterName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getMachineState() {
		return machineState;
	}
	public void setMachineState(String machineState) {
		this.machineState = machineState;
	}
	public String getUsedMerchant() {
		return usedMerchant;
	}
	public void setUsedMerchant(String usedMerchant) {
		this.usedMerchant = usedMerchant;
	}
	public String getUsedStores() {
		return usedStores;
	}
	public void setUsedStores(String usedStores) {
		this.usedStores = usedStores;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLastupdateUser() {
		return lastupdateUser;
	}
	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}
	public Date getLupdDatetime() {
		return lupdDatetime;
	}
	public void setLupdDatetime(Date lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	

}
