package com.qifenqian.bms.v2.biz.ad.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAdDO;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;
import com.qifenqian.bms.v2.biz.ad.bean.CustomAO;
import com.qifenqian.bms.v2.biz.ad.service.BizAdManagementService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LvFeng
 * @Description: 广告管理
 * @date 2020/5/18 10:33
 */
@RestController
@Api(tags = "广告管理")
public class BizAdManagementController extends BaseController {

    @Autowired
    private BizAdManagementService bizAdManagementService;


    @PostMapping(value = "/admanagement/list")
    @ApiOperation(value = "广告列表")
    public PageInfo<AdManagement> list(PageQuery pageQuery, @RequestBody AdManagement queryBean) {
        return bizAdManagementService.list(queryBean);
    }

    @PostMapping(value = "/admanagement/add")
    @ApiOperation(value = "广告新增")
    public ResultData add(CurrentAccount currentAccount, @RequestBody AdManagement adManagement) {
        paramValidation(adManagement);
        //将当前登录用户的ID设置放入创建者的字段中
        adManagement.setCreateId(String.valueOf(currentAccount.getLoginId()));
        return bizAdManagementService.add(adManagement);
    }

    @PostMapping(value = "/admanagement/update")
    @ApiOperation(value = "广告修改")
    public ResultData update(CurrentAccount currentAccount, @RequestBody AdManagement adManagement) {
        paramValidation(adManagement);
        adManagement.setModifyId(String.valueOf(currentAccount.getLoginId()));
        return bizAdManagementService.update(adManagement);
    }

    @PostMapping(value = "/admanagement/distribution")
    @ApiOperation(value = "分配广告给商户门店")
    public ResultData distribution(@RequestBody ShopAdDO shopAdDO) {
        if (null == shopAdDO) {
            throw new BizException("参数不能为空!");
        }
        if (CollectionUtils.isEmpty(shopAdDO.getAdDOList())) {
            throw new BizException("广告信息不能为空!");
        }
        return bizAdManagementService.distribution(shopAdDO);
    }

    @PostMapping(value = "/admanagement/adcustomlist")
    @ApiOperation(value = "广告名查询商户")
    public List<AdCustInfoVO> adCustomList(@RequestBody CustomAO customAO) {
        return bizAdManagementService.adCustomList(customAO.getCustomName());
    }

    @PostMapping(value = "/admanagement/adallcustomlist")
    @ApiOperation(value = "广告名和门店查询商户门店信息")
    public List<AdAllCustInfoVO> adAllCustomList(@RequestBody CustomAO customAO) {
        String customName = customAO.getCustomName(), shopName = customAO.getShopName();
        if (null != customName) {
            customName = customName.trim();
        }
        if (null != shopName) {
            shopName = shopName.trim();
        }
        return bizAdManagementService.adAllCustomList(customName, shopName);
    }

    @PostMapping(value = "/admanagement/machineadvert/list")
    @ApiOperation(value = "商户设备广告列表")
    public List<TdMachineAdvert> machineAdvertList(@RequestBody TdMachineAdvert queryBean) {
        return bizAdManagementService.machineAdvertList(queryBean);
    }

    private void paramValidation(AdManagement adManagement) {
        if (StringUtils.isBlank(adManagement.getAdName())) {
            throw new BizException("广告名称不能为空!");
        }
        if (StringUtils.isBlank(adManagement.getType())) {
            throw new BizException("广告类型不能为空!");
        }
        if (StringUtils.isBlank(adManagement.getShowTime())) {
            throw new BizException("广告播放时长不能为空!");
        }
        if (StringUtils.isBlank(adManagement.getImagePath())) {
            throw new BizException("上传路径不能为空!");
        }
        if (StringUtils.isBlank(adManagement.getUrl())) {
            throw new BizException("广告链接不能为空!");
        }
    }
}
