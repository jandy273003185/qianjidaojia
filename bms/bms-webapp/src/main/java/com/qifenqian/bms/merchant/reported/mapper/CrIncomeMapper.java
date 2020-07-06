package com.qifenqian.bms.merchant.reported.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.MerchantFilingInfo;
import com.qifenqian.bms.merchant.reported.bean.MerchantProdInfo;

@MapperScan
public interface CrIncomeMapper {

	public   List<CrInComeBean> getInComeInfo(@Param("merchantCode") String merchantCode);
	
	public void updateInComeInfo(List<CrInComeBean> beans);
	
	public void insertFilingInfo(List<CrInComeBean> list);
	
	public MerchantProdInfo getMerchantProdInfo(@Param("merchantCode") String merchantCode);

	public List<ChannlInfo> getChannlInfoList();
	
	public List<MerchantFilingInfo> getInComeInfoList(MerchantFilingInfo merchantCode);
	
	public List<MerchantFilingInfo> getfilingInfoList(MerchantFilingInfo merchantCode);
	
	public List<MerchantFilingInfo> verifyFiling(MerchantFilingInfo merchantCode);
	
	/**
	 * 获取合利宝的省份ID
	 * @param provinceId
	 * @return
	 */
	public String getProvinceId(@Param("provinceId")String provinceId);
	
	/**
	 * 获取合利宝的城市ID
	 * @param cityId
	 * @return
	 */
	public String getCityId(@Param("cityId")String cityId);
	
	/**
	 * 获取合利宝的地区ID
	 * @param cityId
	 * @return
	 */
	public String getAreaId(@Param("areaId")String areaId);
	
	public CategoryCodeInfo getCategoryTypeInfo(@Param("merchantId")String merchantId);
	
	public CategoryCodeInfo getCategoryInfoHelipay(@Param("categoryId")String categoryId);
	
	
}
