package com.sevenpay.agentmanager.biz.old.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.UserLoginRelate;
import com.qifenqian.app.bean.customer.TdSalesmanInfo;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.login.UserLoginManagerService;
import com.qifenqian.app.merchant.CommercialService;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.exception.BizException;
import com.sevenpay.agentmanager.common.pojo.Pager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 业务员接口
 */
@RestController
@RequestMapping("salesman")
public class SalesmanManagerController {

    @Reference(version="${app.service.version}")
    private SalesmanManagerService salesmanManagerService;

    @Reference(version="${app.service.version}")
    private CommercialService commerService;

    @Reference(version="${app.service.version}")
    private UserLoginManagerService loginManagerService;

    /**
     * 管理员下业务员商户审核数据
     *
     * @param userId
     * @param custName
     * @param stateCode
     * @param filingAuditStatus
     * @param queryStartDate
     * @param queryEndDate
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PostMapping("selectSalesmanCommercialInfo")
    public ResultData selectSalesmanCommercialInfo(String userId,
                                                   String custName,
                                                   String stateCode,
                                                   String filingAuditStatus,
                                                   String queryStartDate,
                                                   String queryEndDate,
                                                   int pageSize,
                                                   int pageNum) {
        int page = (pageNum - 1) * pageSize;

        Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
        List<Map<String, Object>> maps = salesmanManagerService.selectSalesmanMerchentByServiceProviderId(userId, custName, stateCode, filingAuditStatus, queryStartDate, queryEndDate, pageSize, page);

        pager.setData(maps);
        pager.setTotal(salesmanManagerService.selectServiceProviderCommercialInfoCount(userId, custName, stateCode, filingAuditStatus, queryStartDate, queryEndDate, pageSize, page));

        return ResultData.success(pager);


    }

    /**
     * 交易排名
     *
     * @param userId         管理员
     * @param custName       商户名称
     * @param queryStartDate 查询起始时间
     * @param queryEndDate   查询终止时间
     * @param pageSize       页面条数
     * @param pageNum        当前页数
     * @param rankingCode    transactionNum desc      transactionNum asc
     * @return
     */
    @PostMapping("getServiceProviderDealRanking")
    public ResultData getDealRanking(String userId,
                                     String custName,
                                     String queryStartDate,
                                     String queryEndDate,
                                     int pageSize,
                                     int pageNum,
                                     String rankingCode) {
        int page = (pageNum - 1) * pageSize;

        Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
        List<Map<String, Object>> list = salesmanManagerService.getServiceProviderDealRanking(userId, custName, queryStartDate, queryEndDate, pageSize, page, rankingCode);
        pager.setData(list);
        pager.setTotal(salesmanManagerService.getServiceProviderDealRankingCount(userId, custName, queryStartDate, queryEndDate, pageSize, page, rankingCode));
        return ResultData.success(pager);
    }


    /**
     * 重置业务员密码
     *
     * @param custId 服务商号
     * @param id     业务员号
     * @return
     */
    @PostMapping("regPassword")
    public ResultData regSalesman(String custId, String id) {
        Integer result = salesmanManagerService.resetTdSalesmanInfoPassword(custId, id);
        if (result > 0) {
            return ResultData.success();
        }
        return ResultData.error();
    }

    /**
     * 业务员修改密码
     *
     * @param id          业务员号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @PostMapping("updatePassword")
    public ResultData updateSalesman(String id, String oldPassword, String newPassword) {
        Integer result = salesmanManagerService.updateTdSalesmanInfoPassword(id, oldPassword, newPassword);
        if (result > 0) {
            return ResultData.success();
        }
        return ResultData.error();
    }

    /**
     * 查询业务员信息
     *
     * @param salesmanInfo
     * @return
     */
    @PostMapping("query")
    public ResultData queryTdSalesmanInfos(TdSalesmanInfo salesmanInfo) {
        List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(salesmanInfo);
        return ResultData.success(tdSalesmanInfos);
    }

    /**
     * 根据主键id获取业务员业绩详情
     *
     * @param id             业务员编号
     * @param queryStartDate 查询起始时间
     * @param queryEndDate   查询终止时间
     * @param roleId         2管理员（服务商）  3业务员
     * @return
     */
    @PostMapping("get")
    public ResultData getTdSalesmanInfoById(String id,
                                            String queryStartDate,
                                            String queryEndDate,
                                            String roleId) {
        //查询商户数据
        Map<String, Object> statCommercial = commerService.getStatCommercial(id, queryStartDate, queryEndDate, roleId);
        //获取商户统计数，交易笔均金额
        Map<String, Object> merchantStatistics = salesmanManagerService.getMerchantStatistics(id, queryStartDate, queryEndDate);
        statCommercial.putAll(merchantStatistics);
        return ResultData.success(statCommercial);
    }

    /**
     * 新增业务员
     *
     * @param tdSalesmanInfo
     * @return
     */
    @PostMapping("insert")
    public ResultData insertTdSalesmanInfoById(TdSalesmanInfo tdSalesmanInfo) {
        TdSalesmanInfo tdSalesmanInfo1 = new TdSalesmanInfo();
        tdSalesmanInfo1.setUserPhone(tdSalesmanInfo.getUserPhone());
        List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo1);
        if (tdSalesmanInfos.size() > 0) {
            for (TdSalesmanInfo salesmanInfo : tdSalesmanInfos) {//假如多个服务商下都有该业务员
                if ("1".equals(salesmanInfo.getStatus()) && !tdSalesmanInfo.getCustId().equals(salesmanInfo.getCustId())) {
                    throw new BizException("添加失败,如有疑问请联系客服！");
                }
            }
        }
        //该业务员没有在别的服务商下启用或者没有改业务员
        Integer result = salesmanManagerService.insertTdSalesmanInfo(tdSalesmanInfo);
        if (result > 0) {
            return ResultData.success();
        }
        return ResultData.error();
    }

    /**
     * 更新业务员信息
     *
     * @param tdSalesmanInfo
     * @return
     */
    @PostMapping("update")
    public ResultData updateTdSalesmanInfoById(TdSalesmanInfo tdSalesmanInfo) {
        TdSalesmanInfo tdSalesmanInfo1 = new TdSalesmanInfo();
        tdSalesmanInfo1.setUserPhone(tdSalesmanInfo.getUserPhone());
        List<TdSalesmanInfo> tdSalesmanInfos = salesmanManagerService.listTdSalesmanInfos(tdSalesmanInfo1);
        if (tdSalesmanInfos.size() > 0) {
            for (TdSalesmanInfo salesmanInfo : tdSalesmanInfos) {//假如多个服务商下都有该业务员
                if ("1".equals(salesmanInfo.getStatus()) && !tdSalesmanInfo.getCustId().equals(salesmanInfo.getCustId())) {
                    throw new BizException("添加失败,如有疑问请联系客服！");
                }
            }
        }
        //该业务员没有在别的服务商下启用或者没有改业务员
        if ("0".equals(tdSalesmanInfo.getStatus())) {
            UserLoginRelate userLoginRelate = new UserLoginRelate();
            userLoginRelate.setIfUnbind("0");
            userLoginRelate.setUserId(tdSalesmanInfo.getSalesmanId());
            userLoginRelate.setUserType("salesman");
            userLoginRelate.setCustId(tdSalesmanInfo.getCustId());
            loginManagerService.updateBindingInfo(userLoginRelate);
        }
        Integer result = salesmanManagerService.updateTdSalesmanInfo(tdSalesmanInfo);
        if (result > 0) {
            return ResultData.success();
        }
        return ResultData.error();
    }

    /**
     * 查询业务员业绩排名
     *
     * @param sortType       0 按进件数  1 按交易额
     * @param userId         业务员所属服务商 userId
     * @param queryStartDate
     * @param queryEndDate
     * @return
     */
    @PostMapping("performance")
    public ResultData selectSalesmanPerformance(String sortType, String userId, String queryStartDate, String queryEndDate) {
        List<Map<String, Object>> maps = salesmanManagerService.selectSalesmanPerformance(sortType, userId, queryStartDate, queryEndDate);
        return ResultData.success(maps);
    }

    /**
     * 校验该业务员手机账号是否绑定过
     *
     * @param phone
     * @return
     */
    @PostMapping("checkPhone")
    public ResultData checkPhone(String phone, String custId) {
        boolean b = salesmanManagerService.checkPhone(phone, custId);
        if (!b) {
            return ResultData.error("该业务员手机号已绑定");
        }
        return ResultData.success();
    }

}
