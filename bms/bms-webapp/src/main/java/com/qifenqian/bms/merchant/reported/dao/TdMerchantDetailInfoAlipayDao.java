package com.qifenqian.bms.merchant.reported.dao;


import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAlipay;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoAlipayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TdMerchantDetailInfoAlipayDao {

    @Autowired
    private TdMerchantDetailInfoAlipayMapper tdMerchantDetailInfoAlipayMapper;

    public int delete(String patchNo) {
        return this.tdMerchantDetailInfoAlipayMapper.delete(patchNo);
    }

    public int insert(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay) {
        return this.tdMerchantDetailInfoAlipayMapper.insert(tdMerchantDetailInfoAlipay);
    }

    public int insertDynamic(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay) {
        return this.tdMerchantDetailInfoAlipayMapper.insertDynamic(tdMerchantDetailInfoAlipay);
    }

    public int updateDynamic(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay) {
        return this.tdMerchantDetailInfoAlipayMapper.updateDynamic(tdMerchantDetailInfoAlipay);
    }

    public TdMerchantDetailInfoAlipay selectByPatchNo(String patchNo) {
        return this.tdMerchantDetailInfoAlipayMapper.selectByPatchNo(patchNo);
    }
}
