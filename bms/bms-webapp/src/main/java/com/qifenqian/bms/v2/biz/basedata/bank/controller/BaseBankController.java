package com.qifenqian.bms.v2.biz.basedata.bank.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.v2.biz.basedata.bank.service.BaseBankService;
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
 * @Description: 银行管理
 * @date 2020/4/26 14:21
 */
@RestController
@Api(tags = "银行管理")
public class BaseBankController extends BaseController {

    @Autowired
    private BaseBankService baseBankService;

    @PostMapping(value = "/bank/list")
    @ApiOperation("银行列表")
    public PageInfo<Bank> list(PageQuery pageQuery, @RequestBody Bank bank) {
        return this.baseBankService.findBankList(bank);
    }

    @PostMapping(value = "/bank/add")
    @ApiOperation("银行添加")
    public ResultData add(@RequestBody Bank bank) {
        if (StringUtils.isBlank(bank.getBankCode())) {
            throw new BizException("银行编号为空");
        }
        if (StringUtils.isBlank(bank.getBankName())) {
            throw new BizException("银行名称为空");
        }
        if (StringUtils.isBlank(bank.getBankType())) {
            throw new BizException("银行别名为空");
        }
        if (StringUtils.isBlank(bank.getCityCode())) {
            throw new BizException("城市编号为空");
        }
        if (StringUtils.isBlank(bank.getCcpc())) {
            throw new BizException("ccpc为空");
        }
        if (StringUtils.isBlank(String.valueOf(bank.getOrderBy()))) {
            throw new BizException("排序号为空");
        }
        return this.baseBankService.add(bank);
    }
    @PostMapping(value = "/bank/update")
    @ApiOperation("银行添加")
    public ResultData update(@RequestBody Bank bank) {
        if (StringUtils.isBlank(bank.getBankCode())) {
            throw new BizException("银行编号为空");
        }
        if (StringUtils.isBlank(bank.getBankName())) {
            throw new BizException("银行名称为空");
        }
        if (StringUtils.isBlank(bank.getBankType())) {
            throw new BizException("银行别名为空");
        }
        if (StringUtils.isBlank(bank.getCityCode())) {
            throw new BizException("城市编号为空");
        }
        if (StringUtils.isBlank(bank.getCcpc())) {
            throw new BizException("ccpc为空");
        }
        if (StringUtils.isBlank(String.valueOf(bank.getOrderBy()))) {
            throw new BizException("排序号为空");
        }
        return this.baseBankService.update(bank);
    }

    @PostMapping(value = "/bank/delete")
    @ApiOperation("银行删除")
    public ResultData delete(@RequestBody Bank bank) {
        if (StringUtils.isBlank(bank.getBankCode())) {
            throw new BizException("银行编号为空");
        }
        return this.baseBankService.delete(bank);
    }
}
