package com.qifenqian.bms.basemanager.merchant.auding.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantCashier;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbAreaInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbCity;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbProvince;
import com.qifenqian.bms.basemanager.merchant.auding.bean.WechatExport;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.auding.mapper.WechatAudingMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class WechatAudingDao {
	@Autowired
	private WechatAudingMapper wechatMapper;
	
     //获取代理商审核列表
	@Page
	public List<AgencyAuding> getAgencyAudingList(MerchantVo merchantVo){
		return wechatMapper.getWechatAudingList(merchantVo);
	}
	
	//获取单个审核代理商对象
	public MerchantVo getSingleWechatAuding(MerchantVo merchantVo){
		return wechatMapper.getSingleWechatAuding(merchantVo);
	}
	
	//获取收银员对象
	public List<MerchantCashier> findCashierList(String custId){
		return wechatMapper.findCashierList(custId);
	}
	
	//获取产品信息
	
	public List<bmsProtocolColumn> findColumnList(String custId){
		return wechatMapper.findColumnList(custId);
	}
	
	//更新栏位表
	public void updatebmsprotocolColumn(bmsProtocolColumn bmsProtocolColumn){
		wechatMapper.updatebmsprotocolColumn(bmsProtocolColumn);
	}
	
	//更新栏位表
		public void updatebmsprotocolColumn2(bmsProtocolColumn bmsProtocolColumn){
			wechatMapper.updatebmsprotocolColumn2(bmsProtocolColumn);
		}
	
	//更新协议表
	public void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent){
		wechatMapper.updatebmsprotocolContent(bmsProtocolContent);
	}
	
	
	@Page
	public List<WechatExport> tinyMerchantExport(MerchantVo merchantVo) {
		return wechatMapper.tinyMerchantExport(merchantVo);
	 } 
	
	public void  updateMerchantCashierInfo(MerchantCashier merchantCashier){
		wechatMapper.updateMerchantCashierInfo(merchantCashier);
	}
	public String getCustScanCopy(CustScan custScan){
		return wechatMapper.getCustScanCopy(custScan);
	}
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(String custId) {
		  return  wechatMapper.getCheckbmsProtocolColumnList(custId);
	}
	public List<CategoryCodeInfo> getCategoryCodeInfoByType(CategoryCodeInfo categoryCodeInfo){
		return wechatMapper.getCategoryCodeInfoByType(categoryCodeInfo);
	}
	public TbCity getCityInfoById(Integer cityId) {
		return wechatMapper.getCityInfoById(cityId);
	}
	
	public TbAreaInfo getAreaInfoById(Integer areaId){
		
		return wechatMapper.getAreaInfoById(areaId);
	}
	public List<TbProvince> selectProvinceList(){
		return wechatMapper.selectProvinceList();
	}
	
}
