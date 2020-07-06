package com.qifenqian.bms.merchant.reported.dao;


import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoLaKaLa;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoLaKaLaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TdMerchantDetailInfoLaKaLaDao {
    @Autowired
    private TdMerchantDetailInfoLaKaLaMapper tdMerchantDetailInfoLaKaLaMapper;


    public int insert(TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa) {
        return tdMerchantDetailInfoLaKaLaMapper.insert(tdMerchantDetailInfoLaKaLa);
    }

    public int updateDynamic(TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa) {
        return tdMerchantDetailInfoLaKaLaMapper.updateDynamic(tdMerchantDetailInfoLaKaLa);
    }

    public TdMerchantDetailInfoLaKaLa selectByPatchNo(String patchNo) {
        return tdMerchantDetailInfoLaKaLaMapper.selectByPatchNo(patchNo);
    }
}
