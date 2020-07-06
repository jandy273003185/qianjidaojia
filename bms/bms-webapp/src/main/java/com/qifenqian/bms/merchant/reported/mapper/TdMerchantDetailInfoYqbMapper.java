package com.qifenqian.bms.merchant.reported.mapper;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoYqb;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author LvFeng
 * @Description: 平安付Mapper
 * @date 2020/5/7 18:23
 */
@MapperScan
public interface TdMerchantDetailInfoYqbMapper {
    int insert(TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb);

    int update(TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb);

    TdMerchantDetailInfoYqb selectByPatchNo(String patchNo);
}
