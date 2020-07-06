package com.qifenqian.bms.v2.biz.version.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.app.bean.TdAppEditionControl;
import com.qifenqian.bms.basemanager.app.mapper.TdAppEditionControlMapper;
import com.qifenqian.bms.basemanager.app.service.AppEditionManagerService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LvFeng
 * @Description: APP版本管理服务层
 * @date 2020/4/29 18:41
 */
@Service
public class BizAppEditionManagerService extends BaseService {
    @Autowired
    private TdAppEditionControlMapper appEditionControlMapper;

    @Value("${APP_EDITION_SERVICE_IP}")
    private String appPaths;

    @Autowired
    private AppEditionManagerService appEditionManagerService;


    public PageInfo<TdAppEditionControl> list(TdAppEditionControl queryBean) {
        List<TdAppEditionControl> tdAppEditionControls = appEditionControlMapper.listTdAppEditionControl(queryBean);
        return new PageInfo<>(tdAppEditionControls);
    }

    public ResultData add(TdAppEditionControl tdAppEditionControl) {
        tdAppEditionControl.setState("00");
        tdAppEditionControl.setCreator(WebUtils.getUserInfo().getUserName());
        tdAppEditionControl.setFileUrl(appPaths + tdAppEditionControl.getFileUrl());
        try {
            int flag = appEditionManagerService.insertTdAppEditionControl(tdAppEditionControl);
            return flag > 0 ? ResultData.success() : ResultData.error();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public ResultData delete(TdAppEditionControl tdAppEditionControl) {
        tdAppEditionControl.setState("99");
        try {
            int flag = appEditionManagerService.updateTdAppEditionControl(tdAppEditionControl);
            return flag > 0 ? ResultData.success() : ResultData.error();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }
}
