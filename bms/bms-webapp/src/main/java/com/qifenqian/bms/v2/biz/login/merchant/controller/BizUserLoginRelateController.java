package com.qifenqian.bms.v2.biz.login.merchant.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import com.qifenqian.bms.v2.biz.login.merchant.service.BizUserLoginRelateService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiBin
 * @Description: 商户登录管理
 * @date 2020/4/29 10:19
 */
@RestController
@Api(tags = "商户登录管理")
public class BizUserLoginRelateController extends BaseController {

    @Autowired
    private BizUserLoginRelateService bizUserLoginRelateService;

    @PostMapping(value = "/user/login/relate/list")
    @ApiOperation("商户登录管理列表")
    public PageInfo<UserLoginRelate> list(PageQuery pageQuery, @RequestBody UserLoginRelate userLoginRelate) {
        userLoginRelate.setIfUnbind("1");
        String startTime = null;//起止最大时间
        String endTime = null;//结束时间
        if (!StringUtils.isBlank(userLoginRelate.getStartTime())) {
            startTime = userLoginRelate.getStartTime().trim();
            userLoginRelate.setStartTime(startTime + " 00:00:00");
        }
        if (!StringUtils.isBlank(userLoginRelate.getEndTime())) {
            endTime = userLoginRelate.getEndTime().trim();
            userLoginRelate.setEndTime(endTime + " 23:59:59");
        }
        return this.bizUserLoginRelateService.findUserLoginRelateList(userLoginRelate);
    }

    @PostMapping(value = "/user/login/relate/cust/list")
    @ApiOperation("商户客户列表")
    public List<TdCustInfo> list() {
        return this.bizUserLoginRelateService.findTdCustInfoList();
    }
    @PostMapping(value = "/user/login/relate/add")
    @ApiOperation("商户登录列表添加")
    public ResultData add(@RequestBody UserLoginRelate userLoginRelate) {
        return this.bizUserLoginRelateService.add(userLoginRelate);
    }
    @PostMapping(value = "/user/login/relate/delete")
    @ApiOperation("商户登录列表删除")
    public ResultData delete(@RequestBody UserLoginRelate userLoginRelate) {
        userLoginRelate.setIfUnbind("0");
        return this.bizUserLoginRelateService.delete(userLoginRelate);
    }
}
