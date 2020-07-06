package com.qifenqian.bms.basemanager.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;

/**
 * 
 * @author dayet
 * @date 2015.5.18
 */

@MapperScan
public interface CustScanMapper {
	
	/**
	 * 保存证件信息
	 * @param photo
	 */
	public int insertCustScan(CustScan custScan);
	
	public String findPath(CustScan custScan);

	public List<String> findByidScanPath(String custId);
	
	public String findPathByIdAndType(CustScan custScan);
	
	public int updateCustScan(CustScan custScan);
	
	public int updateCustScanById(CustScan custScan);
	
	public int insertCertificateAuth(CertificateAuth insertBean);
	
	public int selectAuthId(@Param("custId") String custId);
	
	/** 根据扫描件类型和客户名称查询扫描件是否存在 **/
	public int findCountCustScanInfo(CustScan custScan);
	
	/** 保存微商户证件审核信息 */
	public int saveTinyCertificateAuth(CertificateAuth certificateAuth);
	
	List<CustScan> ListTdCustScanCopy(@Param("custId")String custId);
	
}
