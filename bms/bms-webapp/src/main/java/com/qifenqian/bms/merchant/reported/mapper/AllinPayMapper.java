package com.qifenqian.bms.merchant.reported.mapper;


import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.reported.bean.AllinPayBean;
import com.qifenqian.bms.merchant.reported.bean.AllinPayProductInfo;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;

@MapperScan
public interface AllinPayMapper {

	List<Industry> getAllinPayIndustryList();

	List<Province> getProvinceName();

	List<Bank> getBankInfo();

	TdMerchantReportInfo getTdMerchantReport(TdMerchantReportInfo reportBean);
	
	TdMerchantDetailInfoAllinPay getAllinPayTdMerchantDetail(String patchNo);

	void updateTdMerchantReport(TdMerchantReportInfo tdInfo);

	void insertTdMerchantReport(TdMerchantReportInfo info);

	void insertTdMerchantDetailInfoAllinPay(AllinPayBean cr);

	void insertTdMerchantProductInfoAllinPay(AllinPayProductInfo prod);

	void updateTdMerchantDetailInfoAllinPay(AllinPayBean cr);

	void updateTdMerchantProductInfoAllinPay(AllinPayProductInfo prod);

	List<TdMerchantDetailInfo> selectTdMerchantProductInfoAllinPay(TdMerchantDetailInfo detail);

}
