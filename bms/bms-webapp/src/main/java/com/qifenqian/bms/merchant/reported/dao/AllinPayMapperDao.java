package com.qifenqian.bms.merchant.reported.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.reported.bean.AllinPayBean;
import com.qifenqian.bms.merchant.reported.bean.AllinPayProductInfo;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.mapper.AllinPayMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class AllinPayMapperDao {

	@Autowired
	private AllinPayMapper allinPayMapper;
	
	public List<Industry> getAllinPayIndustryList() {
		return allinPayMapper.getAllinPayIndustryList();
	}

	public List<Province> getProvinceName() {
		return allinPayMapper.getProvinceName();
	}

	public List<Bank> getBankInfo() {
		return allinPayMapper.getBankInfo();
	}

	public TdMerchantReportInfo getTdMerchantReport(TdMerchantReportInfo reportBean) {
		return allinPayMapper.getTdMerchantReport(reportBean);
	}
	
	public TdMerchantDetailInfoAllinPay getAllinPayTdMerchantDetail(String patchNo) {
		return allinPayMapper.getAllinPayTdMerchantDetail(patchNo);
	}

	public void insertTdMerchantReport(TdMerchantReportInfo info) {
		allinPayMapper.insertTdMerchantReport(info);
		
	}

	public void insertTdMerchantDetailInfoAllinPay(AllinPayBean cr) {
		allinPayMapper.insertTdMerchantDetailInfoAllinPay(cr);
		
	}

	public void insertTdMerchantProductInfoAllinPay(AllinPayProductInfo prod) {
		allinPayMapper.insertTdMerchantProductInfoAllinPay(prod);
		
	}

	public void updateTdMerchantDetailInfoAllinPay(AllinPayBean cr) {
		allinPayMapper.updateTdMerchantDetailInfoAllinPay(cr);
		
	}

	public void updateTdMerchantProductInfoAllinPay(AllinPayProductInfo prod) {
		allinPayMapper.updateTdMerchantProductInfoAllinPay(prod);
		
	}
	
	@Page
	public List<TdMerchantDetailInfo> selectTdMerchantProductInfoAllinPay(TdMerchantDetailInfo detail){
		return allinPayMapper.selectTdMerchantProductInfoAllinPay(detail);
	}
}
