package com.qifenqian.bms.merchant.reported.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.merchant.reported.bean.Area;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.BankBranch;
import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.FmWxCategory;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.SumPayArea;
import com.qifenqian.bms.merchant.reported.bean.SumpayMcc;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.YQBArea;
import com.qifenqian.bms.merchant.reported.bean.YQBIndustry;

@MapperScan
public interface FmIncomeMapper {

	public List<TdMerchantDetailInfo> selMerchantDetailInfoList(TdMerchantDetailInfo detail);

	public List<Province> selProvinceList();

	public List<Bank> selBankList();

	public List<TbFmTradeInfo> selPowerIdList();

	public List<City> selCityList(@Param("province")String province);

	public List<Area> selAreaList(@Param("city")String city);

	public List<BankBranch> selInterBankList(@Param("interBank")String interBank);

	public List<FmWxCategory> selAlipayList();

	public List<FmWxCategory> selWxList();
	
	public List<FmWxCategory> selWxSecondTypeList(FmWxCategory category);
	
	public List<FmWxCategory> selWxThirdTypeList(FmWxCategory category);
	
	public List<FmWxCategory> selJdList();
	
	public List<FmWxCategory> selJdThirdTypeList(FmWxCategory category);

	public TdMerchantDetailInfo selTdMerchantReport(CrInComeBean cr);

	public void insertTdMerchantReport(TdMerchantDetailInfo info);

	public void inserTdMerchantDetailInfo(TdMerchantDetailInfo info);

	public TdCustInfo selInComeInfo(String merchantCode);

	public void updateTdMerchantReport(TdMerchantDetailInfo tdInfo);

	public void updateTdMerchantDetailInfo(TdMerchantDetailInfo tdInfo);
	
	public TdMerchantDetailInfo selTdMerchantDetailInfo(TdMerchantDetailInfo tdInfo);
	
	public TdMerchantDetailInfo selMerchantDetailInfo(TdMerchantDetailInfo detail);

	public List<Industry> selIndustryList();

	public List<Industry> selSuiXingIndustryList();

	public List<Province> selSuiXingProvinceList();

	public List<City> selSuiXingCityList(String province);

	public List<MerchantCity> selSuiXingAreaList(String superiorAreaCode);

	public List<MerchantCity> selSuiXingMerchantCityList(String areaType);

	public List<Bank> selSumPayBankList();
	
	public List<SumPayArea> selSumPayProvinceList(String superiorAreaCode);

	public List<SumpayMcc> selSumpayMccList();

	public List<SumpayMcc> selSumPayMccTwoList(String one);

	public List<SumpayMcc> selSumPayMccThreeList(SumpayMcc sumpayMcc);
	
	public List<SumpayMcc> selSumpayMccWXList();

	public List<SumpayMcc> selSumPayMccTwoWXList(String one);

	public List<SumpayMcc> selSumPayMccThreeWXList(SumpayMcc sumpayMcc);

	public List<SumpayMcc> selSumpayMccZFBList();

	public List<YQBArea> selYQBProvinceList();

	public List<YQBArea> selYQBCityList(YQBArea yabArea);

	public List<YQBArea> selYQBAreaList(YQBArea yabArea);

	public List<YQBIndustry> selYQBIndustryList();

	public List<YQBIndustry> selYQBIndustryCodeList(YQBIndustry yqbIndustry);

	public List<Bank> selYQBBankList(Bank bank);

	public List<Industry> selSuiXingIndustryListByCategoryId(Industry industry);
}
