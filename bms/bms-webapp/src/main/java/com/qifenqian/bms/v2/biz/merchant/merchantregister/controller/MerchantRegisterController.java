package com.qifenqian.bms.v2.biz.merchant.merchantregister.controller;


import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.v2.biz.merchant.merchantregister.service.MerchantRegisterService;
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

@RestController
@Api(tags = "商户管理")
public class MerchantRegisterController extends BaseController {

    @Autowired
    private MerchantRegisterService merchantRegisterService;

    @PostMapping(value = "/merchantRegister/list")
    @ApiOperation(value = "商户列表")
    public PageInfo<MerchantVo> list(CurrentAccount currentAccount, PageQuery pageQuery, @RequestBody MerchantVo merchantVo) {
        merchantVo.setUserId(currentAccount.getLoginId().toString());
        PageInfo<MerchantVo> merchantVos = merchantRegisterService.findMerchantList(merchantVo);
        return merchantVos;
    }

    @PostMapping(value = "/merchantRegister/detail")
    @ApiOperation(value = "商户详细")
    public MerchantVo detail(@RequestBody Merchant merchant) {
        MerchantVo merchantVo = merchantRegisterService.findMerchantInfo(merchant.getCustId());
        return merchantVo;
    }

    @PostMapping(value = "/merchantRegister/add")
    @ApiOperation(value = "商户新增")
    public ResultData add(CurrentAccount currentAccount, @RequestBody Merchant merchant) {
        merchantRegisterService.add(currentAccount, merchant);
        return ResultData.success();
    }

    @PostMapping(value = "/merchantRegister/update")
    @ApiOperation(value = "商户更新")
    public ResultData update(@RequestBody MerchantVo merchantVo) {
        merchantRegisterService.update(merchantVo);
        return ResultData.success();
    }

    @PostMapping(value = "/merchantRegister/audit")
    @ApiOperation(value = "商户审核")
    public ResultData audit(@RequestBody TdAuditRecodeInfo tdAuditRecodeInfo) {
        if (StringUtils.isBlank(tdAuditRecodeInfo.getMerchantCode())) {
            throw new BizException("MerchantCode不能为空");
        }
        if (StringUtils.isBlank(tdAuditRecodeInfo.getStatus())) {
            throw new BizException("status不能为空");
        }
        return merchantRegisterService.audit(tdAuditRecodeInfo);
    }
}
