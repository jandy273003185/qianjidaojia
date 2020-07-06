package com.qifenqian.bms.basemanager.admanage.dao;


import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.basemanager.admanage.mapper.AdManagementMapper;
import com.qifenqian.bms.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdManagementDao {

    @Autowired
    private AdManagementMapper adManagementMapper;

    @Page
    public List<AdManagement> selectAdManagementListBy(AdManagement queryBean) {
        return this.adManagementMapper.selectAdManagementListBy(queryBean);
    }

    public int saveAdManagement(AdManagement adManagement) {
        return this.adManagementMapper.saveAdManagement(adManagement);
    }

    public int updateAdManagement(AdManagement adManagement) {
        return this.adManagementMapper.updateAdManagement(adManagement);
    }
    /**
     * @param zhanggc 查下每个商户设备 的广告列表
     */
    @Page
    public List<TdMachineAdvert> selecTdMachineAdvertListBy(TdMachineAdvert queryBean) {
        return this.adManagementMapper.selecTdMachineAdvertListBy(queryBean);
    }
}
