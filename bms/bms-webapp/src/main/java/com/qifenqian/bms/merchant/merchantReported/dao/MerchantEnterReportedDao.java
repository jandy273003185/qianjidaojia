package com.qifenqian.bms.merchant.merchantReported.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.merchant.merchantReported.mapper.MerchantEnterReportedMapper;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.AllinPayMapperDao;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class MerchantEnterReportedDao {

	@Autowired
	private MerchantEnterReportedMapper merchantEnterReportedMapper;
	@Autowired
	private AllinPayMapperDao allinPayMapperDao;
	
	@Page
	public List<TdMerchantDetailInfo> selMerchantDetailInfoList(TdMerchantDetailInfo detail) {
		List<TdMerchantDetailInfo> detailList = merchantEnterReportedMapper.selMerchantDetailInfoList(detail);
		List<TdMerchantDetailInfo> allinPayDetailList = allinPayMapperDao.selectTdMerchantProductInfoAllinPay(detail);
		detailList.addAll(allinPayDetailList); 
		List<TdMerchantDetailInfo> newList = detailList.stream().sorted(Comparator.comparing(TdMerchantDetailInfo::getReportTime).reversed()).collect(Collectors.toList());
		return newList;
	}
	
	
}
