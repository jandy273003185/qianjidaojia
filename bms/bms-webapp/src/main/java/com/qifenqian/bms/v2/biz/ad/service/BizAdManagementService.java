package com.qifenqian.bms.v2.biz.ad.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAdDO;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.basemanager.admanage.mapper.AdManagementMapper;
import com.qifenqian.bms.basemanager.admanage.service.ShopAdService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LvFeng
 * @Description: 广告管理服务层
 * @date 2020/5/18 10:36
 */
@Service
public class BizAdManagementService extends BaseService {
    @Autowired
    private AdManagementMapper adManagementMapper;
    @Autowired
    private ShopAdService shopAdService;

    public PageInfo<AdManagement> list(AdManagement queryBean) {
        List<AdManagement> adManagements = adManagementMapper.selectAdManagementListBy(queryBean);
        return new PageInfo<>(adManagements);
    }

    public ResultData add(AdManagement adManagement) {
        //设置ID为UUID生成的32位随机数
        adManagement.setAdId(GenSN.getSN());
        //设置当前时间为创建时间
        adManagement.setCreateTime(new Date());
        //首次创建的广告信息状态均默认为1 （1-正常，0-无效）
        adManagement.setStatus("1");
        adManagement.setIsValid("1");
        try {
            adManagementMapper.saveAdManagement(adManagement);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(AdManagement adManagement) {
        try {
            adManagementMapper.updateAdManagement(adManagement);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData distribution(ShopAdDO shopAdDO) {
        try {
            JSONObject jsonObject = shopAdService.distributionShopAd(shopAdDO);
            if ("SUCCESS".equals(jsonObject.get("result"))) {
                return ResultData.success();
            } else {
                return ResultData.error(jsonObject.get("message").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public List<AdCustInfoVO> adCustomList(String customName) {
        try {
            return shopAdService.findAdCustomList(customName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public List<AdAllCustInfoVO> adAllCustomList(String customName, String shopName) {
        try {
            return shopAdService.findAdAllCustomList(customName, shopName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public List<TdMachineAdvert> machineAdvertList(TdMachineAdvert queryBean) {
        String startTime = null;//起止最大时间
        String endTime = null;//结束时间
        if (!StringUtils.isBlank(queryBean.getStartTime())) {
            startTime = queryBean.getStartTime().trim();
            queryBean.setStartTime(startTime + " 00:00:00");
        }
        if (!StringUtils.isBlank(queryBean.getEndTime())) {
            endTime = queryBean.getEndTime().trim();
            queryBean.setEndTime(endTime + " 23:59:59");
        }
        if (!StringUtils.isBlank(queryBean.getCustName())) {
            queryBean.setCustName(queryBean.getCustName().trim());
        }
        if (!StringUtils.isBlank(queryBean.getCustId())) {
            queryBean.setCustId(queryBean.getCustId().trim());
        }
        try {
            return adManagementMapper.selecTdMachineAdvertListBy(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }

    }
}
