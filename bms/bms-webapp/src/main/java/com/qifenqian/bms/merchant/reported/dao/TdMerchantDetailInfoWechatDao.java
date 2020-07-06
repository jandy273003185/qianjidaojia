package com.qifenqian.bms.merchant.reported.dao;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoWeChat;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoWechatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TdMerchantDetailInfoWechatDao {

    @Autowired
    private TdMerchantDetailInfoWechatMapper tdMerchantDetailInfoWechatMapper;

    public int insert(TdMerchantDetailInfoWeChat tdMerchantDetailInfoWechat) {
        return tdMerchantDetailInfoWechatMapper.insert(tdMerchantDetailInfoWechat);
    }

    public int updateDynamic(TdMerchantDetailInfoWeChat tdMerchantDetailInfoWechat) {
        return tdMerchantDetailInfoWechatMapper.updateDynamic(tdMerchantDetailInfoWechat);
    }

    public TdMerchantDetailInfoWeChat selectByPatchNo(String patchNo) {
        return tdMerchantDetailInfoWechatMapper.selectByPatchNo(patchNo);
    }
}
