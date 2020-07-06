package com.qifenqian.bms.merchant.reported.mapper;


import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoWeChat;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TdMerchantDetailInfoWechatMapper {

    int insert(TdMerchantDetailInfoWeChat tdMerchantDetailInfoWechat);

    int updateDynamic(TdMerchantDetailInfoWeChat tdMerchantDetailInfoWechat);

    TdMerchantDetailInfoWeChat selectByPatchNo(String patchNo);
}
