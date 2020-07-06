package com.qifenqian.bms.v2.biz.merchant.serviceparentermerchant.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.v2.biz.merchant.serviceparentermerchant.service.BizServiceParenterService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
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
 * @author LiBin
 * @Description: 服务商
 * @date 2020/4/15 16:11
 */
@RestController
@Api(tags = "服务商管理")
public class BizServiceParenterController extends BaseController {

    @Autowired
    private BizServiceParenterService bizServiceParenterService;

    @PostMapping(value = "/serviceParenter/list")
    @ApiOperation(value = "服务商列表")
    public PageInfo<MerchantVo> list(CurrentAccount currentAccount, PageQuery pageQuery, @RequestBody MerchantVo merchantVo) {
        merchantVo.setUserId(currentAccount.getLoginId().toString());
        PageInfo<MerchantVo> merchantVos = bizServiceParenterService.findServiceParenterList(merchantVo);
        return merchantVos;
    }

    @PostMapping(value = "/serviceParenter/detail")
    @ApiOperation(value = "服务商详情")
    public MerchantVo detail(Merchant merchant) {
        MerchantVo merchantVo = bizServiceParenterService.newFindMerchantInfo(merchant.getCustId());
        return merchantVo;
    }

    @PostMapping(value = "/serviceParenter/add")
    @ApiOperation(value = "服务商新增")
    public ResultData add(CurrentAccount currentAccount, @RequestBody Merchant merchant) {
        bizServiceParenterService.add(currentAccount, merchant);
        return ResultData.success();
    }

    @PostMapping(value = "/serviceParenter/update")
    @ApiOperation(value = "服务商更新")
    public ResultData update(@RequestBody MerchantVo merchantVo) {
        bizServiceParenterService.update(merchantVo);
        return ResultData.success();
    }

    @PostMapping(value = "/serviceParenter/audit")
    @ApiOperation(value = "服务商审核")
    public ResultData audit(@RequestBody Merchant merchant) {
        if (StringUtils.isBlank(merchant.getCustId())) {
            throw new BizException("custId不能为空");
        }
        if (StringUtils.isBlank(merchant.getFals())) {
            throw new BizException("fals不能为空");
        }
        return bizServiceParenterService.audit(merchant);
    }
}
