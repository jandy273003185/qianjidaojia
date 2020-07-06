package com.qifenqian.bms.basemanager.merchant.auding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TdCustScanCopy;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
@MapperScan
public interface audingMapper {
	 //获取代理商审核列表
	public List<AgencyAuding> getAgencyAudingList(MerchantVo merchantVo);
	//获取单个审核代理商对象
	public MerchantVo getSingleAgencyAuding(MerchantVo merchantVo);

	//更新协议表
	public  void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent);
	
	public List<MerchantExport> agentExportMerchantInfo(MerchantVo merchantVo);
	
	//获取代理商的栏位信息
	public List<bmsProtocolColumn> getbmsProtocolColumnList(@Param("custId")String custId);
	
	public bmsProtocolContent getbmsPbmsProtocolContent(@Param("custId")String custId);
	
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(@Param("custId")String custId);
	//更改协议表的状态
	public void updatebmsProtocolColumn(bmsProtocolColumn protocolColumn);
	//添加协议表
	public void addbmsProtocolColumn(bmsProtocolColumn protocolColumn);
	//获取代理商图片路径
	public List<TdCustScanCopy> getAgencyAudingPictures(@Param("custId")String custId);
	
	//获取代理商协议表
	public List<bmsProtocolContent> getbmsProtocolContentList(@Param("custId")String custId);
	
	public void updatetdCertificateAuth(TdCertificateAuth tdCertificateAuth);
	
	public String findPath(CustScan custScan);
	//获取审核历史记录
	public List<TdCertificateAuth> getAgencyCheckHistory(@Param("custId")String custId);
	
}
