package com.qifenqian.bms.v2.biz.basedata.branchbank.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.mapper.BranchBankMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 16:05
 */
@Service
public class BaseBranchBankService extends BaseService {

    @Autowired
    private BranchBankMapper branchBankMapper;

    public PageInfo<BranchBank> findBranchBankList(BranchBank queryBean) {
        List<BranchBank> branchBanks = branchBankMapper.branchBankList(queryBean);
        return new PageInfo<>(branchBanks);
    }

    public ResultData add(BranchBank insertBean) {
        BranchBank branchBank = this.branchBankMapper.selectBankCnaps(insertBean.getBankCnaps());
        if (branchBank != null) {
            throw new BizException("支行信息联行编号已存在!");
        }
        int result = branchBankMapper.insertBranchBank(insertBean);
        if (result < 1) {
            throw new BizException("添加保存支行信息异常!");
        }
        return ResultData.success();
    }

    public ResultData update(BranchBank branchBank) {
        int result = branchBankMapper.updateBranchBank(branchBank);
        if (result < 1) {
            throw new BizException("修改支行信息异常!");
        }
        return ResultData.success();
    }

    public ResultData delete(BranchBank branchBank) {
        int result = branchBankMapper.deleteBranchBank(branchBank);
        if (result < 1) {
            throw new BizException("删除支行信息异常!");
        }
        return ResultData.success();
    }
}
