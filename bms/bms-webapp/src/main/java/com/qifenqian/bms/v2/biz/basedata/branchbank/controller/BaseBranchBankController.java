package com.qifenqian.bms.v2.biz.basedata.branchbank.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.v2.biz.basedata.branchbank.service.BaseBranchBankService;
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
 * @Description: 支行信息管理
 * @date 2020/4/27 16:03
 */
@RestController
@Api(tags = "支行信息管理")
public class BaseBranchBankController extends BaseController {

    @Autowired
    private BaseBranchBankService baseBranchBankService;

    @PostMapping(value = "/branch/bank/list")
    @ApiOperation("支行信息列表")
    public PageInfo<BranchBank> list(PageQuery pageQuery, @RequestBody BranchBank queryBean) {
        return this.baseBranchBankService.findBranchBankList(queryBean);
    }

    @PostMapping(value = "/branch/bank/add")
    @ApiOperation("支行信息添加")
    public ResultData add(@RequestBody BranchBank branchBank) {
        if (StringUtils.isBlank(branchBank.getBankCnaps())) {
            throw new BizException("支行信息联行编号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankCode())) {
            throw new BizException("支行信息银行支付系统行号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankName())) {
            throw new BizException("支行信息银行名称为空");
        }
        if (branchBank.getAreaId() < 1) {
            throw new BizException("支行信息区域编号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankAddress())) {
            throw new BizException("支行信息 地址为空");
        }
        return this.baseBranchBankService.add(branchBank);
    }

    @PostMapping(value = "/branch/bank/update")
    @ApiOperation("支行信息更新")
    public ResultData update(@RequestBody BranchBank branchBank) {
        if (StringUtils.isBlank(branchBank.getBankCnaps())) {
            throw new BizException("支行信息联行编号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankCode())) {
            throw new BizException("支行信息银行支付系统行号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankName())) {
            throw new BizException("支行信息银行名称为空");
        }
        if (branchBank.getAreaId() < 1) {
            throw new BizException("支行信息区域编号为空");
        }
        if (StringUtils.isBlank(branchBank.getBankAddress())) {
            throw new BizException("支行信息 地址为空");
        }
        return this.baseBranchBankService.update(branchBank);
    }
    @PostMapping(value = "/branch/bank/delete")
    @ApiOperation("支行信息删除")
    public ResultData delete(@RequestBody BranchBank branchBank) {
        if (StringUtils.isBlank(branchBank.getBankCnaps())) {
            throw new BizException("支行信息联行编号为空");
        }
        return this.baseBranchBankService.delete(branchBank);
    }

}
