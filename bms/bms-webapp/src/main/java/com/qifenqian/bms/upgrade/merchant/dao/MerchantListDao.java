package com.qifenqian.bms.upgrade.merchant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantAuditExport;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.merchant.mapper.MerchantListMapper;

@Repository
public class MerchantListDao {

	@Autowired
	private MerchantListMapper merchantListMapper;
	
	@Autowired 
	private TdCustInfoMapper custInfoMapper;
	/**
	 * 查询商户列表
	 * @param queryBean  查询条件
	 * @return
	 */
	@Page
	public List<MerchantVo> listMerchant(MerchantVo queryBean){
		return merchantListMapper.listMerchant(queryBean);
	}
	/**
	 * 导出列表 查询出所有的信息
	 * @param queryBean
	 * @return
	 */
	public List<MerchantAuditExport> exportlist(MerchantVo queryBean){
		return merchantListMapper.exportlist(queryBean);
	}
	/**
	 * 查询商户状态
	 * @param merchantCode 商户编号
	 * @return
	 */
	public String selectStateOfMerchant(String merchantCode) {
		return merchantListMapper.selectStateOfMerchant(merchantCode);
	}
	/**
	 * 查询商户信息
	 * @param merchantCode 商户编号
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode){
		return merchantListMapper.queryMerchantInfo(merchantCode);
	}
	
	/**
	 * 插入审核信息
	 */
	public void insertAuditInfo(TdAuditRecodeInfo auditRecodeInfo) {
		merchantListMapper.insertAuditInfo(auditRecodeInfo);
	}
	/**
	 * 删除审核信息
	 */
	public void deleteAuditInfo(TdAuditRecodeInfo auditRecodeInfo) {
		merchantListMapper.deleteAuditInfo(auditRecodeInfo);
	}
	/**
	 * 将审核结果更新到各个表中
	 */
	public void updateStatusForAuditResult(String applyStatus,String custStatus,
			String userStatus,String merchantId) {
		merchantListMapper.updateStatusForAuditResult(applyStatus, custStatus, userStatus, merchantId);
	}
	/**
	 * 将审核结果更新到各个表中
	 */
	public void updateStatusForAuditResultOld(String custStatus,String userStatus,String merchantId) {
		merchantListMapper.updateStatusForAuditResultOld(custStatus, userStatus, merchantId);
	}
	/**
	 * 账户信息-查询银行信息 
	 * @param bankCode
	 * @return
	 */
	public TbBankCode selectBankByBankCode(String bankCode) {
		return merchantListMapper.selectBankByBankCode(bankCode);
	}
	
	/**
	 * 根据custId获取商户登录表信息
	 */
	public TdLoginUserInfo getUserInfoByCustId(String custId) {
		return merchantListMapper.getUserInfoByCustId(custId);
	}
	
	/**
	 * 根据custId获取商户信息
	 */
	public TdCustInfo getCustInfoByCustId(String custId) {
		return merchantListMapper.getCustInfoByCustId(custId);
	}
	/**
	 * 审核通过时，将密码加密后的字段以及attachStr写入TdLoginUserInfo表中
	 * @param loginUserInfo
	 */
	public void updateLoginUserInfo(TdLoginUserInfo loginUserInfo) {
		merchantListMapper.updateLoginUserInfo(loginUserInfo);
	}
	
	/**
	 * 根据merchantId从TD_MERCHANT_APPLY_INFO中查询是否有信息，有的话则为服务商下的商户，无的话则为个体商户
	 */
	public String getApplyIdByMerchantId(String merchantId) {
		return merchantListMapper.getApplyIdByMerchantId(merchantId);
	}
}
