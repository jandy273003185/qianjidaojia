package com.qifenqian.bms.merchant.reported.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class FmIncomeMapperDao {
  @Autowired
  private FmIncomeMapper fmIncomeMapper;

  @Page
  public List<TdMerchantDetailInfo> selMerchantDetailInfoList(TdMerchantDetailInfo detail) {

    return fmIncomeMapper.selMerchantDetailInfoList(detail);
  }

  public List<Province> selProvinceList() {

    return fmIncomeMapper.selProvinceList();
  }

  public List<Bank> selBankList() {

    return fmIncomeMapper.selBankList();
  }

  public List<TbFmTradeInfo> selPowerIdList() {
    return fmIncomeMapper.selPowerIdList();
  }

  public List<City> selCityList(String province) {
    return fmIncomeMapper.selCityList(province);
  }

  public List<Area> selAreaList(String city) {
    return fmIncomeMapper.selAreaList(city);
  }

  public List<BankBranch> selInterBankList(String interBank) {
    return fmIncomeMapper.selInterBankList(interBank);
  }

  public List<FmWxCategory> selAlipayList() {
    return fmIncomeMapper.selAlipayList();
  }

  public List<FmWxCategory> selWxList() {
    return fmIncomeMapper.selWxList();
  }

  public List<FmWxCategory> selWxSecondTypeList(FmWxCategory category) {
    return fmIncomeMapper.selWxSecondTypeList(category);
  }

  public List<FmWxCategory> selWxThirdTypeList(FmWxCategory category) {
    return fmIncomeMapper.selWxThirdTypeList(category);
  }

  public List<FmWxCategory> selJdList() {
    return fmIncomeMapper.selJdList();
  }

  public List<FmWxCategory> selJdThirdTypeList(FmWxCategory category) {

    return fmIncomeMapper.selJdThirdTypeList(category);
  }

  public TdMerchantDetailInfo selTdMerchantReport(CrInComeBean cr) {
    return fmIncomeMapper.selTdMerchantReport(cr);
  }

  public void insertTdMerchantReport(TdMerchantDetailInfo info) {
    fmIncomeMapper.insertTdMerchantReport(info);
  }

  public void inserTdMerchantDetailInfo(TdMerchantDetailInfo info) {
    fmIncomeMapper.inserTdMerchantDetailInfo(info);
  }

  public TdCustInfo getInComeInfo(String merchantCode) {
    return fmIncomeMapper.selInComeInfo(merchantCode);
  }

  public TdMerchantDetailInfo selMerchantDetailInfo(TdMerchantDetailInfo detail) {
    return fmIncomeMapper.selMerchantDetailInfo(detail);
  }

  public List<Industry> selIndustryList() {
    return fmIncomeMapper.selIndustryList();
  }

  public List<Industry> selSuiXingIndustryList() {
    return fmIncomeMapper.selSuiXingIndustryList();
  }
  
  public List<Industry> selSuiXingIndustryListByCategoryId(Industry industry) {
    return fmIncomeMapper.selSuiXingIndustryListByCategoryId(industry);
  }

  public List<Province> selSuiXingProvinceList() {
    return fmIncomeMapper.selSuiXingProvinceList();
  }

  public List<City> selSuiXingCityList(String province) {
    return fmIncomeMapper.selSuiXingCityList(province);
  }

  public List<MerchantCity> selSuiXingAreaList(String superiorAreaCode) {
    return fmIncomeMapper.selSuiXingAreaList(superiorAreaCode);
  }

  public List<MerchantCity> selSuiXingMerchantCityList(String areaType) {
    return fmIncomeMapper.selSuiXingMerchantCityList(areaType);
  }

public List<Bank> selSumPayBankList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayBankList();
}

public List<SumPayArea> selSumPayProvinceList(String superiorAreaCode) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayProvinceList(superiorAreaCode);
}

public List<SumpayMcc> selSumpayMccList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumpayMccList();
}

public List<SumpayMcc> selSumPayMccTwoList(String one) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayMccTwoList(one);
}

public List<SumpayMcc> selSumPayMccThreeList(SumpayMcc sumpayMcc) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayMccThreeList(sumpayMcc);
}

public List<SumpayMcc> selSumpayMccWXList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumpayMccWXList();
}
public List<SumpayMcc> selSumPayMccTwoWXList(String one) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayMccTwoWXList(one);
}

public List<SumpayMcc> selSumPayMccThreeWXList(SumpayMcc sumpayMcc) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumPayMccThreeWXList(sumpayMcc);
}

public List<SumpayMcc> selSumpayMccZFBList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selSumpayMccZFBList();
}

public List<YQBArea> selYQBProvinceList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBProvinceList();
}

public List<YQBArea> selYQBCityList(YQBArea yabArea) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBCityList(yabArea);
}

public List<YQBArea> selYQBAreaList(YQBArea yabArea) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBAreaList(yabArea);
}

public List<YQBIndustry> selYQBIndustryList() {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBIndustryList();
}

public List<YQBIndustry> selYQBIndustryCodeList(YQBIndustry yqbIndustry) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBIndustryCodeList(yqbIndustry);
}

public List<Bank> selYQBBankList(Bank bank) {
	// TODO Auto-generated method stub
	return fmIncomeMapper.selYQBBankList(bank);
}

}
