package com.qifenqian.bms.v2.biz.version.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.app.bean.TdAppEditionControl;
import com.qifenqian.bms.v2.biz.version.service.BizAppEditionManagerService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvFeng
 * @Description: APP版本管理
 * @date 2020/4/29 18:40
 */
@RestController
@Api(tags = "APP版本管理")
public class BizAppEditionManagerController extends BaseController {
    @Autowired
    private BizAppEditionManagerService bizAppEditionManagerService;

    @PostMapping("/version/app/list")
    @ApiOperation(value = "APP版本列表")
    public PageInfo<TdAppEditionControl> list(PageQuery query, @RequestBody TdAppEditionControl queryBean) {
        return bizAppEditionManagerService.list(queryBean);
    }

    @PostMapping("/version/app/add")
    @ApiOperation(value = "APP版本新增")
    public ResultData add(@RequestBody TdAppEditionControl tdAppEditionControl) {
        if (StringUtils.isBlank(tdAppEditionControl.getFileUrl()))
            throw new BizException("文件路径不能为空！");
        if (StringUtils.isBlank(tdAppEditionControl.getMachineType()))
            throw new BizException("设备类别不能为空！");
        if (StringUtils.isBlank(tdAppEditionControl.getEditionId()))
            throw new BizException("版本号不能为空！");
        return bizAppEditionManagerService.add(tdAppEditionControl);
    }

    @PostMapping("/version/app/delete")
    @ApiOperation(value = "APP版本删除")
    public ResultData delete(@RequestBody TdAppEditionControl tdAppEditionControl) {
        if (StringUtils.isBlank(tdAppEditionControl.getId()))
            throw new BizException("ID不能为空！");
        return bizAppEditionManagerService.delete(tdAppEditionControl);
    }
}

