package com.qifenqian.bms.basemanager.merchant.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdCustBelongInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TinyMerchantExport;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;

@MapperScan
public interface MerchantMapper {

	/**
	 * 保存商户信息
	 */
	public void saveMerchant(Merchant merchant);

	/**
	 * 保存商户信息v2
	 */
	public void saveMerchant2(Merchant merchant);

	
	/**
	 * 验证营业执照注册号
	 * @param businessLicense
	 * @return
	 */
	public Merchant validateLicense(@Param("businessLicense") String businessLicense,
			@Param("custId") String custId);
	
	/**
	 * 验证组织机构代码
	 * @param orgInstCode
	 * @return
	 */
	public Merchant validateOrgInstCode(@Param("orgInstCode") String orgInstCode,
			@Param("custId") String custId);

	
	/**
	 * 商户信息展现
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantVo> list(MerchantVo merchantVo);
	
	/**
	 * 后台商户注册信息展现
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantVo> backList(MerchantVo merchantVo);
	
	/**
	 * 商户信息展现
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantVo> auditList(MerchantVo merchantVo);
	
	/**
	 * 商户信息展现(登录客户经理所属)
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantVo> myAuditList(MerchantVo merchantVo);
	
	// 修改email
	
	public void updateEmail(MerchantVo merchantVo);
	
	//  修改商户状态
	public void updateUserState(String custId);
 
	/**
	 * 商户列表导出
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantExport> exportlist(MerchantVo merchantVo);
	
	public List<MerchantExport> proExportlist(MerchantVo merchantVo);
	
	//  查找商户
	public MerchantVo findMerchantInfo(String custId);
	
	// 根据ID查找微商户信息
	public MerchantVo findTinyMerchantInfo(String custId);

	//  修改商户
	public void updateMerchant(MerchantVo merchantVo);
	
	//修改商户登陆状态
	public void updateMerchantLoginInfo(MerchantVo merchantVo);

	// 审核商户列表
	public List<MerchantVo> listAuditor(MerchantVo merchantVo);
	
	public int updateAcctNameByCustName(MerchantVo merchantVo);

	public List<MerchantExport> backExportlist(MerchantVo merchantVo);

	/**
	 * 
	 * @return
	 */
	public List<Merchant> selectMerchant();

	/** 微商户列表查询  **/
	public List<MerchantVo> tinyMerchantsList(MerchantVo merchantVo); 
	
	/** 自己权限微商户列表查询  **/
	public List<MerchantVo> tinyMyMerchantsList(MerchantVo merchantVo); 
	
	/** 导出微商户列表Excel */
	public List<TinyMerchantExport> exportTinyMerchantsList(MerchantVo merchantVo);
	
	/** 图片 */
	public String findPath(CustScan custScan);
	
	/** 修改客户扫描件 **/
	public void updateCustScanInfo(String custId,MerchantVo merchantVo,Map<String,String> filePath);
	
	/** 验证微商户二维码编号是否被占用 **/
	public int validateTinyMerchantCode(String merchantCode);
	
	/** 验证营业执照注册号是否被占用 **/
	public int validateBusinessLicense(@Param("businessLicense") String businessLicense,@Param("roleId") String roleId);
	
	/** 保存微商户审核结果 */
	public void saveTinyCertificateAuth(Merchant merchant);
	
	/**
	 * 商户列表
	 * @return
	 */
	public List<Merchant> getAllMerchant();
	
	/**
	 * 代理商列表
	 * @return
	 */
	public List<Merchant> selectAgent();
	
	public int validateMobile(@Param("mobile") String mobile);


	public void updateLoginInfo(MerchantVo merchantVo);

	void updateByPrimaryKeySelective(MerchantVo merchantVo);
	
	/**
	 * 新版商户信息展现
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantVo> newMerchantList(MerchantVo merchantVo);
	
	
	/**
	 * 新版查找商户
	 * @param custId
	 * @return
	 */
	public MerchantVo newFindMerchantInfo(String custId);
	
	/**
	 * 商户列表导出
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantExport> newExportlist(MerchantVo merchantVo);

	/**
	 * 商户信息展现v2
	 * @param merchantVo
	 * @return
	 */
	List<MerchantVo> auditList2(MerchantVo merchantVo);


	void updateMerchantEnter(MerchantVo merchantVo);

	String findAreaNameByAreaId(String areaId);

	public MerchantVo getMerchantInfo(TdMerchantDetailInfo detailInfo);

	void saveCustBelongInfo(TdCustBelongInfo tdCustBelongInfo);

	public TdMerchantDetailInfo findMerchantDetailInfo(TdMerchantDetailInfo detailInfo);
}
