package com.qifenqian.bms.merchant.reported.dao;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoYqb;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoYqbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author LvFeng
 * @Description: 平安付Dao
 * @date 2020/5/7 18:22
 */
@Repository
public class TdMerchantDetailInfoYqbDao {
    @Autowired
    private TdMerchantDetailInfoYqbMapper tdMerchantDetailInfoYqbMapper;


    public int insert(TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb) {
        return tdMerchantDetailInfoYqbMapper.insert(tdMerchantDetailInfoYqb);
    }

    public int update(TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb) {
        return tdMerchantDetailInfoYqbMapper.update(tdMerchantDetailInfoYqb);
    }

    public TdMerchantDetailInfoYqb selectByPatchNo(String patchNo) {
        return tdMerchantDetailInfoYqbMapper.selectByPatchNo(patchNo);
    }
}
