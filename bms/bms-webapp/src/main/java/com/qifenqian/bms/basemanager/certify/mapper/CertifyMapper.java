package com.qifenqian.bms.basemanager.certify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.certify.bean.Certify;
import com.qifenqian.bms.basemanager.certify.bean.FileBean;
import com.qifenqian.bms.basemanager.certify.bean.IdentityDetailBean;

/**
 * 证件信息持久层
 * 
 * @project sevenpay-bms-web
 * @fileName CertifyMapper.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo
 */

@MapperScan
public interface CertifyMapper {

	/**
	 * 增加银行信息
	 * 
	 * @return
	 */
	public int insertCertify(Certify certify);

	/**
	 * 根据证件编号删除证件信息
	 * 
	 * @param roleId
	 */
	public void deleteBankByBankCode(@Param("certifyType") String certifyType);

	/**
	 * 查询所有证件信息
	 * 
	 * @return
	 */
	public List<Certify> selectCertifys(Certify certify);

	/**
	 * 更新证件信息
	 * 
	 * @param certify
	 */
	public void updateCertify(Certify certify);

	/**
	 * 查询实名认证文件信息
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<FileBean> getFileList(FileBean queryBean);

	/**
	 * 查询实名认证验证详情
	 * 
	 * @param queryBean
	 * @return
	 */
	public List<IdentityDetailBean> getIdentityDetail(IdentityDetailBean queryBean);

	/**
	 * 处理验证明细
	 * 
	 * @param validateId
	 * @param fileId
	 * @param memo
	 */
	public void dealValidateDetail(@Param("validateId") String validateId, @Param("fileId") String fileId, @Param("memo") String memo,
			@Param("auditUserId") String auditUserId);

	/**
	 * 查询认证文件
	 * 
	 * @param fileName
	 * @return
	 */
	public FileBean selectByFileName(String fileName);

	/**
	 * 修改认证文件
	 * 
	 * @param updateBean
	 * @return
	 */
	public int updateFile(FileBean updateBean);

	/***
	 * 插入认证文件
	 * 
	 * @param insertBean
	 * @return
	 */
	public int insertFile(FileBean insertBean);

	/***
	 * 删除认证详细信息
	 * 
	 * @param update
	 * @return
	 */
	public int deleteIdentityDetailBean(IdentityDetailBean update);

	/***
	 * 插入认证详细信息
	 * 
	 * @param identityDetailList
	 */
	public void insertIdentityDetail(List<IdentityDetailBean> identityDetailList);

	/***
	 * 修改认证详细信息
	 * 
	 * @param updateBean
	 * @return
	 */
	public int updateIdentityDetial(IdentityDetailBean updateBean);

	/**
	 * 修改认证详细信息（写入用户数据）
	 * 
	 * @param update
	 */
	public void updateIdentityDetialList(IdentityDetailBean update);

}
