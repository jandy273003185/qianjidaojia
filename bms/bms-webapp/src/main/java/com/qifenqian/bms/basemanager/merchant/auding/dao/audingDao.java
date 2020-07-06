package com.qifenqian.bms.basemanager.merchant.auding.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TdCustScanCopy;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.auding.mapper.audingMapper;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class audingDao {
	@Autowired
	private audingMapper audingMapper;
     //获取代理商审核列表
	@Page
	public List<AgencyAuding> getAgencyAudingList(MerchantVo merchantVo){
		return audingMapper.getAgencyAudingList(merchantVo);
	}
	//获取单个审核代理商对象
	public MerchantVo getSingleAgencyAuding(MerchantVo merchantVo){
		return audingMapper.getSingleAgencyAuding(merchantVo);
	}
	public void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent){
		audingMapper.updatebmsprotocolContent(bmsProtocolContent);
	}
	@Page
	public List<MerchantExport> agentExportMerchantInfo(MerchantVo merchantVo) {
		return  audingMapper.agentExportMerchantInfo(merchantVo);
	}
	public List<bmsProtocolColumn> getbmsProtocolColumnList(String custId){
		return  audingMapper.getbmsProtocolColumnList(custId);
	}
	//更改协议表的费率
	public void updatebmsProtocolColumn(bmsProtocolColumn protocolColumn){
		audingMapper.updatebmsProtocolColumn(protocolColumn);
	}
	public void addbmsProtocolColumn(bmsProtocolColumn protocolColumn) {
		audingMapper.addbmsProtocolColumn(protocolColumn);
	}//获取代理商图片路径
	public List<TdCustScanCopy> getAgencyAudingPictures(String custId){
		return audingMapper.getAgencyAudingPictures(custId);
	}
	
	public List<bmsProtocolContent> getbmsProtocolContentList(String custId){
		return audingMapper.getbmsProtocolContentList(custId);
	}
	
	public void updatetdCertificateAuth(TdCertificateAuth tdCertificateAuth){
		audingMapper.updatetdCertificateAuth(tdCertificateAuth);
	}
	public List<TdCertificateAuth> getAgencyCheckHistory(String custId){
		return audingMapper.getAgencyCheckHistory(custId);
	}
	public bmsProtocolContent getbmsPbmsProtocolContent(String custId){
		
		return audingMapper.getbmsPbmsProtocolContent(custId);
	}
}
