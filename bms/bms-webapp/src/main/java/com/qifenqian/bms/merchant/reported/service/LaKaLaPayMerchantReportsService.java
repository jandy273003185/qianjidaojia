package com.qifenqian.bms.merchant.reported.service;

import com.qifenqian.bms.merchant.reported.bean.TdLakalaBankAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.TdLakalaMccInfo;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoLaKaLaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LvFeng
 * @Description: 拉卡拉报备服务层
 * @date 2020/5/21 15:04
 */
@Service
public class LaKaLaPayMerchantReportsService {

    @Autowired
    private TdMerchantDetailInfoLaKaLaMapper merchantDetailInfoLaKaLaMapper;

    public List<TdLakalaBankAreaInfo> selectLakalaBankAreaInfo(TdLakalaBankAreaInfo queryBean) {
        return merchantDetailInfoLaKaLaMapper.selectLakalaBankAreaInfo(queryBean);
    }

    public List<TdLakalaMccInfo> selectLakalaMccInfo(TdLakalaMccInfo queryBean) {
        return merchantDetailInfoLaKaLaMapper.selectLakalaMccInfo(queryBean);
    }

}
