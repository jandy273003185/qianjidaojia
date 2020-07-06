package com.qifenqian.bms.merchant.reported.mapper;


import com.qifenqian.bms.merchant.reported.bean.TdLakalaBankAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.TdLakalaMccInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoLaKaLa;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface TdMerchantDetailInfoLaKaLaMapper {
    int insert(TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa);

    int updateDynamic(TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa);

    TdMerchantDetailInfoLaKaLa selectByPatchNo(String patchNo);

    List<TdLakalaBankAreaInfo> selectLakalaBankAreaInfo(TdLakalaBankAreaInfo queryBean);

    List<TdLakalaMccInfo> selectLakalaMccInfo(TdLakalaMccInfo queryBean);
}
