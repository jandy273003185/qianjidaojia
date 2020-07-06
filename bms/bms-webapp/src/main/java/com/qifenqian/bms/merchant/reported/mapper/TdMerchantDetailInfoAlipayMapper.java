package com.qifenqian.bms.merchant.reported.mapper;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAlipay;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TdMerchantDetailInfoAlipayMapper {
    int delete(String patchNo);

    int insert(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay);

    int insertDynamic(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay);

    int updateDynamic(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay);

    TdMerchantDetailInfoAlipay selectByPatchNo(String patchNo);

}
