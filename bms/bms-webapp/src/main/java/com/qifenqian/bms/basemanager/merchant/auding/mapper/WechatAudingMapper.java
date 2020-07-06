package com.qifenqian.bms.basemanager.merchant.auding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

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
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
@MapperScan
public interface WechatAudingMapper {
	 //获取商户审核列表
	public List<AgencyAuding> getWechatAudingList(MerchantVo merchantVo);
	
	//获取单个商户对象
	public MerchantVo getSingleWechatAuding(MerchantVo merchantVo);
	
	//获取收银员对象
	public List<MerchantCashier> findCashierList(@Param("custId") String custId);
	
	//获取协议
	public List<bmsProtocolColumn> findColumnList(@Param("custId") String custId);	
	
	//更新栏位表
	public void updatebmsprotocolColumn(bmsProtocolColumn bc);
	
	public void updatebmsprotocolColumn2(bmsProtocolColumn bc);
	
	//更新协议表
	public  void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent);
	
	public List<WechatExport> tinyMerchantExport(MerchantVo merchantVo);
	
	public void  updateMerchantCashierInfo(MerchantCashier merchantCashier);
	
	public String getCustScanCopy(CustScan custScan);
	//获取代理商的栏位信息
	public List<bmsProtocolColumn> getbmsProtocolColumnList(@Param("custId")String custId);
	
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(@Param("custId")String custId);
	
	public List<CategoryCodeInfo> getCategoryCodeInfoByType(CategoryCodeInfo categoryCodeInfo);
	
	public TbCity getCityInfoById(@Param("cityId")Integer cityId);
	
	public TbAreaInfo getAreaInfoById(@Param("areaId") Integer areaId);
	
	/**
	 * 账户信息-查询省份列表
	 * @return
	 */
	public List<TbProvince> selectProvinceList();
	

}
