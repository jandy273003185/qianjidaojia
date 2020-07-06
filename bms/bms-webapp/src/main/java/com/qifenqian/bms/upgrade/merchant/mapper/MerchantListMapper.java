package com.qifenqian.bms.upgrade.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantAuditExport;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;

@MapperScan
public interface MerchantListMapper {
	
	/**
	 * 查询商户列表
	 */
	public List<MerchantVo> listMerchant(MerchantVo queryBean);
	/**
	 * 查询商户状态
	 */
	public String selectStateOfMerchant(String merchantCode);
	/**
	 * 查询商户信息
	 */
	public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode);
	/**
	 * 导出商户列表表格
	 * @param queryBean
	 * @return
	 */
	public List<MerchantAuditExport> exportlist(MerchantVo queryBean);
	/**
	 * 插入审核信息
	 */
	public void insertAuditInfo(TdAuditRecodeInfo auditRecodeInfo);
	/**
	 * 删除审核信息
	 */
	public void deleteAuditInfo(TdAuditRecodeInfo auditRecodeInfo);
	/**
	 * 将审核结果更新到各个表中
	 */
	public void updateStatusForAuditResult(
			@Param("applyStatus")String applyStatus,
			@Param("custStatus")String custStatus,
			@Param("userStatus")String userStatus,
			@Param("merchantId")String merchantId);
	
	/**
	 * 将审核结果更新到各个表中
	 */
	public void updateStatusForAuditResultOld(			
			@Param("custStatus")String custStatus,
			@Param("userStatus")String userStatus,
			@Param("merchantId")String merchantId);
	/**
	 * 账户信息-查询银行信息 
	 * @param bankCode
	 * @return
	 */
	public TbBankCode selectBankByBankCode(@Param("bankCode")String bankCode);

	/**
	 * 根据custId获取用户登录信息
	 */
	public TdLoginUserInfo getUserInfoByCustId(@Param("custId")String custId);
	
	/**
	 * 根据custId获取用户信息
	 */
	public TdCustInfo getCustInfoByCustId(@Param("custId")String custId);
	
	/**
	 * 审核通过时，将密码加密后的字段以及attachStr写入TdLoginUserInfo表中
	 * @param loginUserInfo
	 */
	public void updateLoginUserInfo(TdLoginUserInfo loginUserInfo);
	
	/**
	 * 根据merchantId从TD_MERCHANT_APPLY_INFO中查询是否有信息，有的话则为服务商下的商户，无的话则为个体商户
	 */
	public String getApplyIdByMerchantId(String merchantId);
}
