package com.qifenqian.bms.basemanager.admanage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.basemanager.admanage.dao.AdManagementDao;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class AdManagementService {

    @Autowired
    private AdManagementDao adManagementDao;

    /**
     * 获取广告列表
     *
     * @param queryBean
     * @return
     */
    public List<AdManagement> selectAdManagementListBy(AdManagement queryBean) {
        return adManagementDao.selectAdManagementListBy(queryBean);
    }

    public String saveAdManagement(AdManagement adManagement) {
        //设置ID为UUID生成的32位随机数
        adManagement.setAdId(GenSN.getSN());
        //设置当前时间为创建时间
        adManagement.setCreateTime(new Date());
        //首次创建的广告信息状态均默认为1 （1-正常，0-无效）
        adManagement.setStatus("1");
        adManagement.setIsValid("1");
        //将当前登录用户的ID设置放入创建者的字段中
        adManagement.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        try {
            adManagementDao.saveAdManagement(adManagement);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FALSE";
        }
    }

    public String updateAdManagement(AdManagement adManagement) {
        try {
            adManagement.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
            adManagementDao.updateAdManagement(adManagement);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FALSE";
        }
    }
    
    /**
     * @param zhanggc 查下每个商户设备 的广告列表
     */
    public List<TdMachineAdvert> selecTdMachineAdvertListBy(TdMachineAdvert queryBean) {
        return adManagementDao.selecTdMachineAdvertListBy(queryBean);
    }
}
