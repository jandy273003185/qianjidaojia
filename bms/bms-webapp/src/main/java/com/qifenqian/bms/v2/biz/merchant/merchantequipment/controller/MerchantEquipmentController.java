package com.qifenqian.bms.v2.biz.merchant.merchantequipment.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.v2.biz.merchant.merchantequipment.service.MerchantEquipmentService;
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

@RestController
@Api(tags = "商户设备管理")
public class MerchantEquipmentController extends BaseController {

    @Autowired
    private MerchantEquipmentService merchantEquipmentService;

    @PostMapping("/merchant/equipment/list")
    @ApiOperation(value = "商户设备列表")
    public PageInfo<MerchantSign> list(PageQuery query, @RequestBody MerchantSign queryBean) {
        return merchantEquipmentService.list(queryBean);
    }

    @PostMapping("/merchant/equipment/newlist")
    @ApiOperation(value = "商户设备信息列表")
    public PageInfo<DeviceLogin> newList(PageQuery query, @RequestBody DeviceLogin queryBean) {
        return merchantEquipmentService.newList(queryBean);
    }


    @PostMapping("/merchant/equipment/add")
    @ApiOperation(value = "商户设备新增")
    public ResultData add(@RequestBody MerchantSign merchantSign) {
        paramValidation(merchantSign);
        return merchantEquipmentService.add(merchantSign);
    }

    @PostMapping("/merchant/equipment/update")
    @ApiOperation(value = "商户设备修改")
    public ResultData update(@RequestBody MerchantSign merchantSign) {
        paramValidation(merchantSign);
        if (null == merchantSign.getId())
            throw new BizException("id不能为空！");
        return merchantEquipmentService.update(merchantSign);
    }


    @PostMapping("/merchant/equipment/delete")
    @ApiOperation(value = "商户设备删除")
    public ResultData delete(@RequestBody MerchantSign merchantSign) {
        if (null == merchantSign.getId())
            throw new BizException("id不能为空！");
        return merchantEquipmentService.delete(merchantSign);
    }

    private void paramValidation(MerchantSign merchantSign) {
        if (null == merchantSign)
            throw new BizException("商户设备不能为空");
        if (StringUtils.isBlank(merchantSign.getMerchantId()))
            throw new BizException("商户编号不能为空！");
        if (StringUtils.isBlank(merchantSign.getTerminalNo()))
            throw new BizException("机器编号不能为空！");
    }

}
