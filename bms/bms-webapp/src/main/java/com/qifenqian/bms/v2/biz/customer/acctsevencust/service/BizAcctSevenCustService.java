package com.qifenqian.bms.v2.biz.customer.acctsevencust.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.mapper.AcctSevenCustMapper;
import com.qifenqian.bms.basemanager.acctsevencust.service.AcctSevenCustFreezeService;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LvFeng
 * @Description: 客户账户管理服务层
 * @date 2020/4/28 14:53
 */
@Service
public class BizAcctSevenCustService extends BaseService {
    @Autowired
    private AcctSevenCustMapper acctSevenCustMapper;

    @Autowired
    private AcctSevenCustFreezeService acctSevenCustFreezeService;

    public PageInfo<AcctSevenCust> list(AcctSevenCust queryBean) {
        List<AcctSevenCust> acctSevenCust = acctSevenCustMapper.getAcctSevenCust(queryBean);
        return new PageInfo<>(acctSevenCust);
    }

    public ResultData edit(AcctSevenCust acctSevenCust) {
        JSONObject jsonObject = null;
        try {
            // 冻结
            if (acctSevenCust.getStatus().equals(Constant.ACCOUNT_FREEZE)) {
                logger.info("修改账号状态-冻结" + JSONObject.toJSONString(acctSevenCust, true));
                jsonObject = acctSevenCustFreezeService.fullFreeze(acctSevenCust);
            } else if (acctSevenCust.getStatus().equals(Constant.ACCOUNT_NORMAL)) {
                logger.info("修改账号状态-解冻" + JSONObject.toJSONString(acctSevenCust, true));
                jsonObject = acctSevenCustFreezeService.fullUnfreeze(acctSevenCust);
            } else {
                throw new BizException("不支持的状态类型：" + acctSevenCust.getStatus());
            }
            if ("FAIL".equals(jsonObject.get("result")))
                return ResultData.error(jsonObject.get("message").toString());
        } catch (Exception e) {
            logger.error("修改账户未成功", e);
            throw new BizException(e.getMessage());
        }

        return ResultData.success();
    }
}
