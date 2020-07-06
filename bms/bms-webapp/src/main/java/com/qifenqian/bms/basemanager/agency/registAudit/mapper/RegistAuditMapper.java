package com.qifenqian.bms.basemanager.agency.registAudit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;

@MapperScan
public interface RegistAuditMapper {
	
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
	public List<MerchantExport> exportlist(MerchantVo queryBean);
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
	 * 账户信息-查询银行信息 
	 * @param bankCode
	 * @return
	 */
	public TbBankCode selectBankByBankCode(@Param("bankCode")String bankCode);
	
	/**
	 * 商户信息-查询授权产品
	 * @param merchantCode
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantProduct(@Param("merchantCode")String merchantCode);
	
	/**
	 * 根据custId获取用户登录信息
	 * @param custId
	 * @return
	 */
	public TdLoginUserInfo getUserInfoByCustId(String custId);
	
	/**
	 * 根据custId获取用户信息
	 * @param custId
	 * @return
	 */
	public TdCustInfo getCustInfoByCustId(String custId);
}
