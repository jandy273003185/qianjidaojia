package com.qifenqian.bms.merchant.reported.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.MerchantFilingInfo;
import com.qifenqian.bms.merchant.reported.bean.MerchantProdInfo;
import com.qifenqian.bms.merchant.reported.mapper.CrIncomeMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class CrIncomeMapperDao {
	@Autowired
	private CrIncomeMapper crIncomeMapper;
	
  public List<CrInComeBean> getInComeInfo(@Param("merchantCode") String merchantCode){
	
	   return crIncomeMapper.getInComeInfo(merchantCode);
  }
	
	public void updateInComeInfo(List<CrInComeBean> beans){
        crIncomeMapper.updateInComeInfo(beans);
	}
	
	public void insertFilingInfo(List<CrInComeBean> list){
		crIncomeMapper.insertFilingInfo(list);
	}
	
	public MerchantProdInfo getMerchantProdInfo(@Param("merchantCode") String merchantCode){
		return crIncomeMapper.getMerchantProdInfo(merchantCode);
	}
	
	public List<ChannlInfo> getChannlInfoList(){
		return crIncomeMapper.getChannlInfoList();
	}
	
	@Page
	public List<MerchantFilingInfo> getfilingInfoList(MerchantFilingInfo merchantCode){
		return crIncomeMapper.getfilingInfoList(merchantCode);
	}
	
	/**
	 * 校验商户是否报备
	 * @param merchantCode
	 * @return
	 */
	public List<MerchantFilingInfo> verifyFiling(MerchantFilingInfo merchantCode){
		return crIncomeMapper.verifyFiling(merchantCode);
	}
	
	/**
	 * 获取合利宝的省份ID
	 * @param provinceId
	 * @return
	 */
	public String getProvinceId(String provinceId){
		return crIncomeMapper.getProvinceId(provinceId);
	}
	
	/**
	 * 获取合利宝的城市ID
	 * @param cityId
	 * @return
	 */
	public String getCityId(String cityId){
		return crIncomeMapper.getCityId(cityId);
	}
	
	/**
	 * 获取合利宝的地区ID
	 * @param cityId
	 * @return
	 */
	public String getAreaId(String areaId){
		return crIncomeMapper.getAreaId(areaId);
	}
	
	public CategoryCodeInfo getCategoryTypeInfo(String merchantId){
		return crIncomeMapper.getCategoryTypeInfo(merchantId);
	}
	
	/**
	 * 获取合利宝行业信息
	 * @param categoryId
	 * @return
	 */
	public CategoryCodeInfo getCategoryInfoHelipay(String categoryId){
		return crIncomeMapper.getCategoryInfoHelipay(categoryId);
	}
}
