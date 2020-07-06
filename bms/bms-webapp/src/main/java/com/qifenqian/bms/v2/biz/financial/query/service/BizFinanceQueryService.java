package com.qifenqian.bms.v2.biz.financial.query.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.accounting.financequery.bean.ChangeBalance;
import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;
import com.qifenqian.bms.accounting.financequery.bean.FinanceSum;
import com.qifenqian.bms.accounting.financequery.bean.UserBalance;
import com.qifenqian.bms.accounting.financequery.mapper.ChangeBalanceMapper;
import com.qifenqian.bms.accounting.financequery.mapper.CommerciaBalanceMapper;
import com.qifenqian.bms.accounting.financequery.mapper.FinanceSumMapper;
import com.qifenqian.bms.accounting.financequery.mapper.UserBalanceMapper;
import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;
import com.qifenqian.bms.accounting.withdraw.mapper.WithdrawMapper;
import com.qifenqian.bms.accounting.withdrawrevoke.mapper.WithdrawRevokeMapper;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.basemanager.merchantwithdraw.mapper.MerchantWithdrawMapper;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/5/7 10:05
 */
@Service
public class BizFinanceQueryService extends BaseService {

    @Autowired
    private CommerciaBalanceMapper commerciaBalanceMapper;
    @Autowired
    private FinanceSumMapper financeSumMapper;
    @Autowired
    private ChangeBalanceMapper changeBalanceMapper;
    @Autowired
    private UserBalanceMapper userBalanceMapper;
    @Autowired
    private WithdrawMapper withdrawMapper;
    @Autowired
    private MerchantWithdrawMapper merchantWithdrawMapper;
    @Autowired
    private WithdrawRevokeMapper withdrawRevokeMapper;

    public PageInfo<FinanceSum> findFinanceSumList(FinanceSum financeSum) {
        List<FinanceSum> financeSums = financeSumMapper.selectFinanceList(financeSum.getSubjectName());
        return new PageInfo<>(financeSums);
    }

    public PageInfo<ChangeBalance> findChangeBalanceList(ChangeBalance changeBalance) {
        List<ChangeBalance> changeBalances = changeBalanceMapper.changeBalanceList(changeBalance);
        List<ChangeBalance> sumList = this.changeBalanceMapper.changeBalanceSum(changeBalance);
        for (int i = 0; i < sumList.size(); i++) {
            if (null != sumList.get(i)) {
                sumList.get(i).setSubjectName("合计");
            }

            changeBalances.add(sumList.get(i));
        }
        return new PageInfo<>(changeBalances);
    }

    public PageInfo<CommerciaBalance> findCommerciaBalanceList(CommerciaBalance commerciaBalance) {
        List<CommerciaBalance> commerciaBalances = commerciaBalanceMapper.selectCommerciaBalanceList(commerciaBalance);
        return new PageInfo<>(commerciaBalances);
    }

    public PageInfo<UserBalance> findUserBalanceList(UserBalance userBalance) {
        List<UserBalance> userBalances = this.userBalanceMapper.selectUserBalanceList(userBalance);
        return new PageInfo<>(userBalances);
    }

    public PageInfo<Withdraw> findWithdrawList(WithdrawRequestBean withdrawRequestBean) {
        List<Withdraw> withdraws = this.withdrawMapper.selectCustomerWithdrawList(withdrawRequestBean);
        return new PageInfo<>(withdraws);
    }

    public PageInfo<MerchantWithdraw> findMerchantWithdrawList(MerchantWithdraw merchantWithdraw) {
        List<MerchantWithdraw> merchantWithdraws = this.merchantWithdrawMapper.selectMerchantWithdrawList(merchantWithdraw);
        return new PageInfo<>(merchantWithdraws);
    }


    public PageInfo<Withdraw> findRevokeWithdrawList(WithdrawRequestBean withdrawRequestBean) {
        List<Withdraw> withdraws = withdrawRevokeMapper.selectWithdrawRevokeList(withdrawRequestBean);
        return new PageInfo<>(withdraws);
    }
}
