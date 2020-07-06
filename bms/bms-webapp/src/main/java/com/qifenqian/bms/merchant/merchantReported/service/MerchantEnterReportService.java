package com.qifenqian.bms.merchant.merchantReported.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.merchant.merchantReported.dao.MerchantEnterReportedDao;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;

@Service
public class MerchantEnterReportService {
	
	private Logger logger = LoggerFactory.getLogger(MerchantEnterReportService.class);
	
	@Autowired
	private MerchantEnterReportedDao merchantEnterReportedDao;
	
	public List<TdMerchantDetailInfo> getMerchantDetailInfoList(TdMerchantDetailInfo detail) {
		List<TdMerchantDetailInfo> detailList =merchantEnterReportedDao.selMerchantDetailInfoList(detail);
	    logger.info("查询报备列表----------------------------------------" + detailList);
		return detailList;
	}
}
