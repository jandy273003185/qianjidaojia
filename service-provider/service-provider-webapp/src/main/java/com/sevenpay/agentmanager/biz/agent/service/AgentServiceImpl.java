package com.sevenpay.agentmanager.biz.agent.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.bean.TdCustInfo;
import com.qifenqian.app.bean.dto.TdOrderRateDetailProfitDTO;
import com.qifenqian.app.bean.dto.trade.PayAndRefundBean;
import com.qifenqian.app.bean.dto.trade.TdOrderRateDetailCondition;
import com.qifenqian.app.bean.profitsharing.AgentProfitStatisticRequest;
import com.qifenqian.app.customer.MerchantInfoService;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.qifenqian.app.merchant.CommercialService;
import com.qifenqian.app.profitsharing.AgentProfitSharingService;
import com.qifenqian.app.trade.PayOrderService;
import com.sevenpay.agentmanager.biz.agent.bean.condition.MerchantCondition;
import com.sevenpay.agentmanager.common.utils.DateUtils;
import com.sevenpay.agentmanager.core.bean.ResultData;
import com.sevenpay.agentmanager.core.exception.BizException;
import com.sevenpay.agentmanager.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName：AgentServiceImpl
 * Description：TODO
 * Author: LiBin
 * Date：2020/1/2 10:30 上午
 */
@Service
public class AgentServiceImpl extends BaseService {
    @Reference(version = "${app.service.version}")
    private MerchantInfoService merchantInfoService;//查询商户接口
    @Reference(version = "${app.service.version}")
    private SalesmanManagerService salesmanManagerService;
    @Reference(version = "${app.service.version}")
    private CommercialService commerService;
    @Reference(version = "${app.service.version}")
    private PayOrderService payOrderService;
    @Reference
    private AgentProfitSharingService agentProfitSharingService;

    /**
     * @description: 查询商户支付信息
     * @author: LiBin
     * @params [merchantDO]
     * @date: 2020-01-02 10:56:07
     */

    public ResultData getSPMerchantOrderList(MerchantCondition merchantCondition) {
        PayAndRefundBean payAndRefundBean = new PayAndRefundBean();
        BeanUtils.copyProperties(merchantCondition, payAndRefundBean);
        List<PayAndRefundBean> payAndRefundBeans = payOrderService.getPayOrderAndRefundByBean(payAndRefundBean);
        for (int i = 0; i < payAndRefundBeans.size(); i++) {
            /**
             * 不用订单表中退款状态
             */
            PayAndRefundBean p = payAndRefundBeans.get(i);
            /**
             * 做实时分页
             */
            PayAndRefundBean resp = payAndRefundBeans.get(payAndRefundBeans.size() - 1);
            p.setLastTradeTime(resp.getFinishTime());//最后一条做为分页条件 }
        }
        return ResultData.success(payAndRefundBeans);
    }

    /**
     * @description: 针对各个门店的交易记录, 发送邮件
     * @author: LiBin
     * @params [userId, custName, queryStartDate, queryEndDate, roleId, rankingCode]
     * @date: 2020-01-03 17:32:22
     */


    public ResultData sentEmailByDealRanking(String userId, String custName, String queryStartDate, String queryEndDate, String roleId, String rankingCode) {
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;
        List<Map<String, Object>> list = commerService.getDealRanking(userId, custName, queryStartDate, queryEndDate, roleId, pageSize, pageNum, rankingCode);
        String custId = null;
        for (Map<String, Object> stringObjectMap : list) {
            stringObjectMap.put("queryStartDate", queryStartDate);
            stringObjectMap.put("queryEndDate", queryEndDate);
            custId = (String) stringObjectMap.get("CUST_ID");
            TdCustInfo custInfo = merchantInfoService.getMerchantById(custId);
            if (custInfo == null) {
                throw new BizException("服务商信息异常!");
            }
            /**
             * TODO 调用分润接口
             * 根据custId 取出具体的值丢在map
             */
            Date startTime = DateUtils.parseDate(queryStartDate);
            Date endTime = DateUtils.parseDate(queryEndDate);
            AgentProfitStatisticRequest agentProfitStatisticRequest = new AgentProfitStatisticRequest();
            agentProfitStatisticRequest.setAgentId(custInfo.getMerchantCode());
            agentProfitStatisticRequest.setEndDate(endTime);
            agentProfitStatisticRequest.setStartDate(startTime);
            BigDecimal profitSharing = agentProfitSharingService.agentProfitStatistics(agentProfitStatisticRequest);
            if (profitSharing == null) {
                profitSharing = new BigDecimal("0");
            }
            System.out.println("==========profitSharing=============" + profitSharing);
            stringObjectMap.put("profitSharing", profitSharing);
        }
        if (StringUtils.isBlank(custId)) {
            throw new BizException("服务商信息异常!");
        }
        return ResultData.success();
    }

    public ResultData getShareProfit(TdOrderRateDetailCondition tdOrderRateDetailCondition) {
        TdCustInfo custInfo = merchantInfoService.getMerchantById(tdOrderRateDetailCondition.getCustId());
        if (custInfo == null) {
            throw new BizException("服务商信息异常!");
        }
        /**
         * 替换custID为merchantCode
         */
        tdOrderRateDetailCondition.setCustId(custInfo.getMerchantCode());
        TdOrderRateDetailProfitDTO tdOrderRateDetailProfitDTO = payOrderService.getShareProfit(tdOrderRateDetailCondition);
        if (null == tdOrderRateDetailProfitDTO) {
            tdOrderRateDetailProfitDTO = new TdOrderRateDetailProfitDTO();
        }
        return ResultData.success(tdOrderRateDetailProfitDTO);
    }
}
