package com.qifenqian.bms.basemanager.agency.registAudit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.agency.registAudit.mapper.RegistAuditMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RegistAuditDao {

	@Autowired
	private RegistAuditMapper registAuditMapper;
	/**
	 * 查询商户列表
	 * @param queryBean  查询条件
	 * @return
	 */
	@Page
	public List<MerchantVo> listMerchant(MerchantVo queryBean){
		return registAuditMapper.listMerchant(queryBean);
	}
	/**
	 * 导出列表 查询出所有的信息
	 * @param queryBean
	 * @return
	 */
	public List<MerchantExport> exportlist(MerchantVo queryBean){
		return registAuditMapper.exportlist(queryBean);
	}
	/**
	 * 查询商户状态
	 * @param merchantCode 商户编号
	 * @return
	 */
	public String selectStateOfMerchant(String merchantCode) {
		return registAuditMapper.selectStateOfMerchant(merchantCode);
	}
	/**
	 * 查询商户信息
	 * @param merchantCode 商户编号
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode){
		return registAuditMapper.queryMerchantInfo(merchantCode);
	}
	
	/**
	 * 插入审核信息
	 */
	public void insertAuditInfo(TdAuditRecodeInfo auditRecodeInfo) {
		registAuditMapper.insertAuditInfo(auditRecodeInfo);
	}
	/**
	 * 删除审核信息
	 */
	public void deleteAuditInfo(TdAuditRecodeInfo auditRecodeInfo) {
		registAuditMapper.deleteAuditInfo(auditRecodeInfo);
	}
	/**
	 * 将审核结果更新到各个表中
	 */
	public void updateStatusForAuditResult(String applyStatus,String custStatus,
			String userStatus,String merchantId) {
		registAuditMapper.updateStatusForAuditResult(applyStatus, custStatus, userStatus, merchantId);
	}
	/**
	 * 账户信息-查询银行信息 
	 * @param bankCode
	 * @return
	 */
	public TbBankCode selectBankByBankCode(String bankCode) {
		return registAuditMapper.selectBankByBankCode(bankCode);
	}
	
	/**
	 * 商户信息-查询授权产品
	 * @param merchantCode
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantProduct(String merchantCode) {
		return registAuditMapper.queryMerchantProduct(merchantCode);
	}
	
	/**
	 * 根据custId获取服务商登录表信息
	 * @param custId
	 * @return
	 */
	public TdLoginUserInfo getUserInfoByCustId(String custId) {
		return registAuditMapper.getUserInfoByCustId(custId);
	}
	
	/**
	 *  根据custId获取商户信息
	 * @param custId
	 * @return
	 */
	public TdCustInfo getCustInfoByCustId(String custId) {
		return registAuditMapper.getCustInfoByCustId(custId);
	}
}
