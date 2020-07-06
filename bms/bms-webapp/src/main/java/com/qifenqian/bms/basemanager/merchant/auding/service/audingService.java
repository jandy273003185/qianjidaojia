package com.qifenqian.bms.basemanager.merchant.auding.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TdCustScanCopy;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;

public interface audingService {
	 //获取代理商审核列表
	public List<AgencyAuding> getAgencyAudingList(MerchantVo merchantVo);
	//获取单个审核代理商对象
	public MerchantVo getSingleAgencyAuding(MerchantVo merchantVo);
	
	//代理商审核不通过
	public void secondAudit(String custId,String number,boolean isPass,String authId,String message,String certifiyStatus,String auditStatus,String trustCertifyLvl,String empty);
	//更新协议表
	public void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent);
	
	//代理商审核通过
	public  void startPass(String custId,boolean isPass,String message,String number,String authId);
	//报表
	public List<MerchantExport> agentExportMerchantInfo(MerchantVo merchantVo);
	
	public List<bmsProtocolColumn> getbmsProtocolColumnList(String custId);
	
	public bmsProtocolContent getbmsPbmsProtocolContent(String custId);
	
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(String custId);
	//更改协议表的费率
	public void updatebmsProtocolColumn(bmsProtocolColumn protocolColumn);
	
	//添加协议
	public void addbmsProtocolColumn(bmsProtocolColumn protocolColumn);
	//更新栏位表状态
	public  void updateColumn(HttpServletRequest request,String custId,Map<String,Object> parameterMap);
	//
	public List<TdCustScanCopy> getAgencyAudingPictures(@Param("custId")String custId);
	
	public List<bmsProtocolContent> getbmsProtocolContentList(String custId);
	public void updatetdCertificateAuth(TdCertificateAuth tdCertificateAuth);
	
	public String findScanPath(String custId,String certifyType);
	
	public List<TdCertificateAuth> getAgencyCheckHistory(String custId);
}
