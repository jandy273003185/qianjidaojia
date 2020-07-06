package com.qifenqian.bms.merchant.merchantReported.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTArea;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTMccBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;

@MapperScan
public interface KftIncomeMapper {

	public List<KFTArea> selKftProvinceList();

	public List<KFTMccBean> selKftIndustryList();

	public List<KFTMccBean> selKftIndustryTwoList(KFTMccBean kFTMccBean);

	public List<KFTMccBean> selKftIndustryThreeList(KFTMccBean kFTMccBean);

	public List<KFTArea> selKftCityList(KFTArea kftArea);
	
	public List<KFTArea> selKftAreaList(KFTArea kftArea);

	public TdMerchantDetailInfo selInfo(TdMerchantDetailInfo detailInfo);
	
	public TdCustInfo selInComeInfo(String merchantCode);

	
}
