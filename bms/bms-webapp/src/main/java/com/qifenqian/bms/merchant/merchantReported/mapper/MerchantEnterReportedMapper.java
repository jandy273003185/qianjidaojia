package com.qifenqian.bms.merchant.merchantReported.mapper;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;

@MapperScan
public interface MerchantEnterReportedMapper {
	
	public List<TdMerchantDetailInfo> selMerchantDetailInfoList(TdMerchantDetailInfo detail);
		

}
