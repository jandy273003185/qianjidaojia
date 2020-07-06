package com.qifenqian.bms.basemanager.merchant.auding.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantCashier;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbAreaInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbCity;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbProvince;
import com.qifenqian.bms.basemanager.merchant.auding.bean.WechatExport;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;


public interface WechatAudingService {
	 //获取商户审核列表
	public List<AgencyAuding> getWeChatAudingList(MerchantVo merchantVo);
	//获取单个商户对象
	public MerchantVo getSingleWeChatAuding(MerchantVo merchantVo);
	//获取收银员信息
	public List<MerchantCashier> getCashierList(String custId);
	//获取产品信息
	public List<bmsProtocolColumn> getbmsColumnList(String custId);
	//商户审核不通过
	public void secondAudit(String custId,String number,boolean isPass,String authId,String message,String certifiyStatus,String auditStatus,String trustCertifyLvl,String empty);
	//更新栏位表状态
	public  void updateColumn(HttpServletRequest request,String custId,Map<String,Object> parameterMap);
	//代理商审核通过
	public  void startPass(String custId,boolean isPass,String message,String number,HttpServletRequest request,Map<String,Object> parameterMap,String authId,String isClear);
	
	//报表
	public List<WechatExport> tinyMerchantExport(MerchantVo merchantVo);
	
	//修改收银员
	public void  updateMerchantCashierInfo(MerchantCashier merchantCashier);
	
	public String getCustScanCopy(String  custId,String certifyType);
	
	//获取已经审核完毕的微商户信息
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(String custId);
	
	//获取行业类目信息
	public List<CategoryCodeInfo> getCategoryCodeInfoByType(CategoryCodeInfo categoryCodeInfo);
	
	public TbCity getCityInfoById(@Param("cityId")Integer cityId);
	
	public TbAreaInfo getAreaInfoById(@Param("areaId") Integer areaId);
	
	public List<TbProvince> selectProvinceList();
	
}
