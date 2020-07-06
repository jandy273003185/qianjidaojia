package com.qifenqian.bms.v2.biz.basedata.bank.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/26 14:24
 */
@Service
public class BaseBankService extends BaseService {
    @Autowired
    private BankMapper bankMapper;

    public PageInfo<Bank> findBankList(Bank bank) {
        List<Bank> banks = bankMapper.selectBanks(bank);
        return new PageInfo<>(banks);
    }

    public ResultData delete(Bank bank) {
        try {
            bankMapper.deleteBankByBankCode(bank.getBankCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData add(Bank bank) {
        Bank existBank = this.bankMapper.selectBankByBankCode(bank.getBankCode());
        if (existBank != null) {
            throw new BizException("该支付系统行号已经占用!");
        }
        int result = this.bankMapper.insertBank(bank);
        if (result < 1) {
            return ResultData.error("添加保存银行信息异常!");
        }
        return ResultData.success();
    }

    public ResultData update(Bank bank) {
        try {
            this.bankMapper.updateBank(bank);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改银行信息异常!" + e.getMessage());
        }

        return ResultData.success();
    }
}
