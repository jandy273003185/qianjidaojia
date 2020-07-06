package com.qifenqian.bms.v2.biz.financial.settlement.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.merchant.settle.bean.MerchantChildSettle;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.merchant.settle.dao.MerchantSettleDao;
import com.qifenqian.bms.merchant.settle.mapper.MerchantSettleMapper;
import com.qifenqian.bms.merchant.settle.type.MerchantSettleStatus;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.v2.biz.financial.settlement.bean.domain.BatchSettleAO;
import com.qifenqian.bms.v2.biz.financial.settlement.bean.domain.MerchantSettleAO;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.settleapplybuss.SettleApplyBussRequest;
import com.sevenpay.invoke.transaction.settleapplybuss.SettleApplyBussResponse;
import com.sevenpay.invoke.transaction.trade.TradeRequest;
import com.sevenpay.invoke.transaction.trade.TradeResponse;
import com.sevenpay.invoke.transaction.trade.bean.TransBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description:
 * @date 2020/5/8 09:51
 */
@Service
public class BizMerchantSettleService extends BaseService {
    @Autowired
    private ProtocolContentMapper protocolContentMapper;
    @Autowired
    private MerchantSettleDao merchantSettleDao;
    @Autowired
    private TdCustInfoMapper custInfoMapper;
    @Autowired
    private MerchantSettleMapper merchantSettleMapper;

    public PageInfo<MerchantSettle> findMerchantSettleList(MerchantSettle merchantSettle) {
        List<MerchantSettle> merchantSettles = merchantSettleMapper.selectList(merchantSettle);
        for (MerchantSettle settle : merchantSettles) {
            settle.setDetailList(merchantSettleMapper.selectDetailListByTogetherId(settle.getId()));
        }
        return new PageInfo<>(merchantSettles);
    }

    public ResultData together(CurrentAccount currentAccount, MerchantSettleAO merchantSettleAO) {
        try {
            Date now = new Date();
            String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");
            String nowDatetime = DateFormatUtils.format(now, "yyyyMMddHHmmss");
            MerchantSettle insertBean = new MerchantSettle();
            // 按结算开始时间排序查询
            List<String> togetherIds = merchantSettleAO.getTogetherIds();
            String[] ids = new String[togetherIds.size()];
            togetherIds.toArray(ids);
            List<MerchantSettle> togetherList = merchantSettleMapper.selectListByIds(ids);
            // 生成联合编号
            String togetherId = togetherList.get(0).getCustId() + "-" + nowDatetime;
            // 写入明细表
            MerchantSettle insertTemp = null;
            for (MerchantSettle settleBean : togetherList) {
                // 写入明细表
                insertTemp = new MerchantSettle();
                insertTemp.setId(settleBean.getId());
                insertTemp.setTogetherId(togetherId);
                merchantSettleMapper.insertSettleDetailById(insertTemp);
                // 删除结算表
                merchantSettleMapper.deleteSettleById(settleBean.getId());
                // 统计
                insertBean.setReceiveCount(settleBean.getReceiveCount() + insertBean.getReceiveCount());
                insertBean.setReceiveTotalAmt(settleBean.getReceiveTotalAmt().add(
                        null == insertBean.getReceiveTotalAmt() ? BigDecimal.ZERO : insertBean.getReceiveTotalAmt()));
                insertBean.setReceiveTotalFee(settleBean.getReceiveTotalFee().add(
                        null == insertBean.getReceiveTotalFee() ? BigDecimal.ZERO : insertBean.getReceiveTotalFee()));
                insertBean.setRefundCount(settleBean.getRefundCount() + insertBean.getRefundCount());
                insertBean.setRefundTotalAmt(settleBean.getRefundTotalAmt().add(
                        null == insertBean.getRefundTotalAmt() ? BigDecimal.ZERO : insertBean.getRefundTotalAmt()));
                insertBean.setRefundTotalFee(settleBean.getRefundTotalFee().add(
                        null == insertBean.getRefundTotalFee() ? BigDecimal.ZERO : insertBean.getRefundTotalFee()));
                insertBean.setRevokeCount(settleBean.getRevokeCount() + insertBean.getRevokeCount());
                insertBean.setRevokeTotalAmt(settleBean.getRevokeTotalAmt().add(
                        null == insertBean.getRevokeTotalAmt() ? BigDecimal.ZERO : insertBean.getRevokeTotalAmt()));
                insertBean.setRevokeTotalFee(settleBean.getRevokeTotalFee().add(
                        null == insertBean.getRevokeTotalFee() ? BigDecimal.ZERO : insertBean.getRevokeTotalFee()));
                insertBean.setWithdrawCount(settleBean.getWithdrawCount() + insertBean.getWithdrawCount());
                insertBean.setWithdrawTotalAmt(settleBean.getWithdrawTotalAmt().add(
                        null == insertBean.getWithdrawTotalAmt() ? BigDecimal.ZERO : insertBean.getWithdrawTotalAmt()));
                insertBean.setWithdrawTotalFee(settleBean.getWithdrawTotalFee().add(
                        null == insertBean.getWithdrawTotalFee() ? BigDecimal.ZERO : insertBean.getWithdrawTotalFee()));
                insertBean.setTransferInCount(settleBean.getTransferInCount() + insertBean.getTransferInCount());
                insertBean.setTransferInTotalAmt(settleBean.getTransferInTotalAmt().add(
                        null == insertBean.getTransferInTotalAmt() ? BigDecimal.ZERO : insertBean.getTransferInTotalAmt()));
                insertBean.setTransferInTotalFee(settleBean.getTransferInTotalFee().add(
                        null == insertBean.getTransferInTotalFee() ? BigDecimal.ZERO : insertBean.getTransferInTotalFee()));
                insertBean.setTransferOutCount(settleBean.getTransferOutCount() + insertBean.getTransferOutCount());
                insertBean.setTransferOutTotalAmt(settleBean.getTransferOutTotalAmt().add(
                        null == insertBean.getTransferOutTotalAmt() ? BigDecimal.ZERO : insertBean.getTransferOutTotalAmt()));
                insertBean.setTransferOutTotalFee(settleBean.getTransferOutTotalFee().add(
                        null == insertBean.getTransferOutTotalFee() ? BigDecimal.ZERO : insertBean.getTransferOutTotalFee()));
                insertBean.setMerchantReceivable(settleBean.getMerchantReceivable().add(
                        null == insertBean.getMerchantReceivable() ? BigDecimal.ZERO : insertBean.getMerchantReceivable()));
                insertBean.setMerchantPayable(settleBean.getMerchantPayable().add(
                        null == insertBean.getMerchantPayable() ? BigDecimal.ZERO : insertBean.getMerchantPayable()));
                insertBean.setSettleAmt(settleBean.getSettleAmt().add(
                        null == insertBean.getSettleAmt() ? BigDecimal.ZERO : insertBean.getSettleAmt()));
            }

            // 结算归集一条记录写入结算表
            insertBean.setId(togetherId);
            insertBean.setCustId(togetherList.get(0).getCustId());
            insertBean.setWorkDate(togetherList.get(togetherList.size() - 1).getWorkDate());
            insertBean.setSettleBeginDate(togetherList.get(0).getSettleBeginDate());
            insertBean.setSettleEndDate(togetherList.get(togetherList.size() - 1).getSettleEndDate());
            insertBean.setProtocolId(togetherList.get(0).getProtocolId());
            insertBean.setStatus(MerchantSettleStatus.INIT);
            insertBean.setMemo("联合结算");
            insertBean.setInstUser(currentAccount.getLoginId().toString());
            insertBean.setInstDate(nowDate);
            merchantSettleMapper.insertSettle(insertBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("联合结算联合异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData separate(CurrentAccount currentAccount, MerchantSettleAO merchantSettleAO) {
        try {
            String togetherId = merchantSettleAO.getTogetherId();
            Date now = new Date();
            String nowDate = DateFormatUtils.format(now, "yyyy-MM-dd");

            // 更新结算表为无效
            MerchantSettle settleUpdate = new MerchantSettle();
            settleUpdate.setStatus(MerchantSettleStatus.INVALID);
            settleUpdate.setId(togetherId);
            merchantSettleMapper.updateById(settleUpdate);
            // 从结算明细表中复制写入结算表
            MerchantSettle detailUpdate = new MerchantSettle();
            detailUpdate.setInstUser(currentAccount.getLoginId().toString());
            detailUpdate.setInstDate(nowDate);
            detailUpdate.setTogetherId(togetherId);
            merchantSettleMapper.insertSettleByTogetherBean(detailUpdate);
            // 更新结算明细表为无效
            merchantSettleMapper.updateSettleDetailByTogetherId(togetherId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("联合结算分离异常! " + e.getMessage());
        }
        return ResultData.success();
    }


    public ResultData batchReview(CurrentAccount currentAccount, BatchSettleAO batchSettleAO) {
        List<MerchantSettleAO> merchantSettleAOS = batchSettleAO.getMerchantSettleAOS();
        String settleSuccessIds = "";
        String settleFailIds = "";
        for (MerchantSettleAO merchantSettleAO : merchantSettleAOS) {
            try {
                MerchantSettle bean = new MerchantSettle();
                bean.setId(merchantSettleAO.getSettleId());
                this.audit(currentAccount, bean);
                //成功商户编号 字符串 （返回页面展示）
                settleSuccessIds += merchantSettleAO.getMerchantId() + '@';
            } catch (Exception e) {
                settleFailIds += merchantSettleAO.getMerchantId() + '@';
                throw new BizException("" + e.getMessage());
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("settleFailIds", settleFailIds.length() > 0 ? settleFailIds.substring(0, settleFailIds.length() - 1) : settleFailIds);
        jsonObject.put("settleSuccessIds", settleSuccessIds.length() > 0 ? settleSuccessIds.substring(0, settleSuccessIds.length() - 1) : settleSuccessIds);
        return ResultData.success(jsonObject);
    }

    private void audit(CurrentAccount currentAccount, MerchantSettle updateBean) throws Exception {
        Date now = new Date();
        // 查询
        MerchantSettle selectBean = merchantSettleMapper.selectSingle(updateBean.getId());
        String createId = currentAccount.getLoginId().toString();
        // 若是结算金额小于0则抛异常
        if (selectBean == null || selectBean.getSettleAmt().compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException("结算记录不存在或结算金额小于0");
        }

        updateBean.setCustId(selectBean.getCustId());
        updateBean.setSettleAmt(selectBean.getSettleAmt());
        updateBean.setSettleBeginDate(selectBean.getSettleBeginDate());
        updateBean.setSettleEndDate(selectBean.getSettleEndDate());
        updateBean.setAuditUser(createId);
        updateBean.setAuditDatetime(now);

        // 结算申请 只要应收或应付大于0
        if (selectBean.getMerchantReceivable().compareTo(BigDecimal.ZERO) > 0
                || selectBean.getMerchantPayable().compareTo(BigDecimal.ZERO) > 0) {
            // 与核心交互前， 先更新为异常
            MerchantSettle updateBeforeBean = new MerchantSettle();
            updateBeforeBean.setId(selectBean.getId());
            updateBeforeBean.setAuditUser(createId);
            updateBeforeBean.setAuditDatetime(now);
            updateBeforeBean.setStatus(MerchantSettleStatus.AUDIT_EXCEPTION);
            merchantSettleMapper.updateById(updateBeforeBean);

            /** 记录交易流水 **/
            MerchantChildSettle insertChildSettle = new MerchantChildSettle();
            String orderId = BusinessUtils.getMsgId();
            insertChildSettle.setOrderId(orderId);
            insertChildSettle.setSettleId(selectBean.getId());
            insertChildSettle.setCustId(selectBean.getCustId());
            insertChildSettle.setCreateId(createId);
            insertChildSettle.setStatus(Constant.SETTLE_EXCEPTION);
            insertChildSettle.setOperType(Constant.SETTLE_APPLY);

            merchantSettleMapper.insertChildSettle(insertChildSettle);
            String memo = "";
            ResponseMessage<SettleApplyBussResponse> responseMessage = null;
            try {
                responseMessage = this.settleApplyBuss(selectBean, orderId);
                if (null == responseMessage) {
                    memo = "确认异常：" + "调用核心出现异常：无返回结果";
                }
                if (RequestColumnValues.RtnResult.SUCCESS == responseMessage.getRtnResult()) {
                    updateBean.setApplyCoreId(insertChildSettle.getOrderId());
                    String[] arketCodeList = SpringUtils.getBean(DictionaryUtils.class)
                            .getDataValueByPath(Constant.QFQ_MARKET_CODE).split(",");
                    updateBean.setStatus(MerchantSettleStatus.AUDIT_PASS);

                    for (String temp : arketCodeList) {
                        if (temp.equals(updateBean.getCustId())) {
                            updateBean.setStatus(MerchantSettleStatus.PAY_SUCC);
                            break;
                        }
                    }
                    memo = "确认成功";

                    insertChildSettle.setStatus(Constant.SETTLE_SUCCESS);
                    insertChildSettle.setCoreReturnCode(responseMessage.getRtnCode().name());
                    insertChildSettle.setCoreReturnMsg(responseMessage.getRtnInfo());
                    insertChildSettle.setCoreSn(responseMessage.getMsgId());

                } else if (RequestColumnValues.RtnResult.FAILURE == responseMessage.getRtnResult()) {
                    updateBean.setApplyCoreId(insertChildSettle.getOrderId());
                    updateBean.setStatus(MerchantSettleStatus.INIT);
                    memo = "确认失败：" + "调用核心失败：" + responseMessage.getRtnCode() + "|" + responseMessage.getRtnInfo();
                    insertChildSettle.setStatus(Constant.SETTLE_FAILURE);
                    insertChildSettle.setCoreReturnCode(responseMessage.getRtnCode().name());
                    insertChildSettle.setCoreReturnMsg(responseMessage.getRtnInfo());
                    insertChildSettle.setCoreSn(responseMessage.getMsgId());

                } else {
                    memo = "确认异常：" + "调用核心出现异常：" + responseMessage.getRtnCode() + "|" + responseMessage.getRtnInfo();
                }
            } catch (Exception e) {
                logger.error("执行异常", e);
                memo = "确认异常：" + e.getMessage();
            } finally {
                if (!StringUtils.isBlank(memo) && memo.length() > 500) {
                    memo = memo.substring(0, 500);
                }
                updateBean.setMemo(memo);
                merchantSettleMapper.updateChildSettle(insertChildSettle);
                merchantSettleDao.dealAfterAudit(updateBean);
            }
            if (null == responseMessage) {
                throw new BizException("调用核心出现异常：无返回结果");
            }
            if (RequestColumnValues.RtnResult.SUCCESS != responseMessage.getRtnResult()) {
                throw new BizException("调用核心出现异常：" + responseMessage.getRtnCode() + "|"
                        + responseMessage.getRtnInfo());
            }
        } else {
            // 无须核心调用的
            updateBean.setStatus(MerchantSettleStatus.AUDIT_PASS);
            updateBean.setMemo("确认成功");
            merchantSettleDao.dealAfterAudit(updateBean);
        }
    }

    /**
     * 商户结算申请
     *
     * @param requestMessage
     * @return
     */
    public ResponseMessage<SettleApplyBussResponse> settleApplyBuss(MerchantSettle settle, String reqSerialId) {
        SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
                .getBean("coreHttpInvokerProxy");

        logger.info("商户结算申请");

        RequestMessage<SettleApplyBussRequest> requestMessage = new RequestMessage<SettleApplyBussRequest>();
        ResponseMessage<SettleApplyBussResponse> responseMessage = null;

        // 查找七分钱账号
        TdCustInfo custInfo = custInfoMapper.selectById(settle.getCustId());

        try {
            requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_SETTLE_APPLY);
            requestMessage.setReqDatetime(new Date());
            requestMessage.setReqMsgNum(1);
            requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
            requestMessage.setReqSerialId(reqSerialId);

            SettleApplyBussRequest request = new SettleApplyBussRequest();
            {
                request.setAcctId(custInfo.getQfqAccId());
                request.setBrief("商户结算申请");
                request.setCurrCode(RequestColumnValues.CurrCode.CNY);
                request.setCustId(settle.getCustId());
                request.setOperateType(RequestColumnValues.SettleApplyOperate.APPLY);
                request.setReceivableAmt(settle.getMerchantReceivable());
                request.setPayableAmt(settle.getMerchantPayable());
            }

            requestMessage.setRequest(request);

            logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));

            responseMessage = sevenpayCoreServiceInterface.settleApplyBuss(requestMessage);

            logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

        } catch (Exception e) {
            logger.error("核心结算申请异常", e);
            throw e;
        }
        return responseMessage;
    }

    public ResultData batchVerified(CurrentAccount currentAccount, BatchSettleAO batchSettleAO) {
        List<MerchantSettleAO> merchantSettleAOS = batchSettleAO.getMerchantSettleAOS();
        String settleSuccessIds = "";
        String settleFailIds = "";
        for (MerchantSettleAO merchantSettleAO : merchantSettleAOS) {
            try {
                MerchantSettle bean = new MerchantSettle();
                bean.setId(merchantSettleAO.getSettleId());
                this.verified(currentAccount, bean);
                //成功商户编号 字符串 （返回页面展示）
                settleSuccessIds += merchantSettleAO.getMerchantId() + '@';
            } catch (Exception e) {
                settleFailIds += merchantSettleAO.getMerchantId() + '@';
                throw new BizException("" + e.getMessage());
            }
        }
        return ResultData.success();
    }

    private Map<String, Object> verified(CurrentAccount currentAccount, MerchantSettle updateBean) throws Exception {
        String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        String currentUser = currentAccount.getLoginId().toString();
        // 查询
        MerchantSettle selectBean = merchantSettleMapper.selectSingle(updateBean.getId());
        //查询号码
        String mouble = merchantSettleMapper.selectTdloginUserInfoByMouble(selectBean.getCustId());
        updateBean.setVerifiedUser(currentUser);
        updateBean.setVerifiedDatetime(new Date());
        updateBean.setCustId(selectBean.getCustId());
        updateBean.setSettleBeginDate(selectBean.getSettleBeginDate());
        updateBean.setSettleEndDate(selectBean.getSettleEndDate());
        // 只要结算金额大于0
        if (selectBean.getSettleAmt().compareTo(BigDecimal.ZERO) > 0) {
            // 与核心交互前， 先更新为异常
            MerchantSettle updateBeforeBean = new MerchantSettle();
            updateBeforeBean.setId(selectBean.getId());
            updateBeforeBean.setVerifiedUser(currentUser);
            updateBeforeBean.setVerifiedDatetime(new Date());
            updateBeforeBean.setStatus(MerchantSettleStatus.VERIFIED_EXCEPTION);
            merchantSettleMapper.updateById(updateBeforeBean);

            MerchantChildSettle insertChildSettle = new MerchantChildSettle();
            String orderId = BusinessUtils.getMsgId();
            insertChildSettle.setOrderId(orderId);
            insertChildSettle.setSettleId(selectBean.getId());
            insertChildSettle.setCustId(selectBean.getCustId());
            insertChildSettle.setCreateId(currentUser);
            insertChildSettle.setStatus(Constant.SETTLE_EXCEPTION);
            insertChildSettle.setOperType(Constant.SETTLE_VERIFIED);
            merchantSettleMapper.insertChildSettle(insertChildSettle);
            String memo = "";
            ResponseMessage<TradeResponse> responseMessage = null;
            //Map<String, Object> resuMap=nr
            try {
                Map<String, Object> resuMap = this.settleBuss(selectBean, orderId);
                responseMessage = (ResponseMessage<TradeResponse>) resuMap.get("responseMessage");

                if (null == responseMessage) {
                    memo = "核销异常：" + "调用核心出现异常：无返回结果";
                }
                if (RequestColumnValues.RtnResult.SUCCESS == responseMessage.getRtnResult()) {
                    updateBean.setSettleCoreId(orderId);
                    updateBean.setFinishDate(nowDate);
                    updateBean.setStatus(MerchantSettleStatus.VERIFIED);
                    memo = "核销成功";
                    insertChildSettle.setStatus(Constant.SETTLE_SUCCESS);
                    insertChildSettle.setCoreReturnCode(responseMessage.getRtnCode().name());
                    insertChildSettle.setCoreReturnMsg(responseMessage.getRtnInfo());
                    insertChildSettle.setCoreSn(responseMessage.getMsgId());
                    map.put("transAmt", resuMap.get("transAmt"));
                    map.put("bankInfo", resuMap.get("bankInfo"));
                    map.put("mouble", mouble);
                } else if (RequestColumnValues.RtnResult.FAILURE == responseMessage.getRtnResult()) {
                    updateBean.setSettleCoreId(orderId);
                    updateBean.setStatus(MerchantSettleStatus.VERIFIED_FAIL);
                    memo = "核销失败：" + "调用核心失败：" + responseMessage.getRtnCode() + "|" + responseMessage.getRtnInfo();

                    insertChildSettle.setStatus(Constant.SETTLE_FAILURE);
                    insertChildSettle.setCoreReturnCode(responseMessage.getRtnCode().name());
                    insertChildSettle.setCoreReturnMsg(responseMessage.getRtnInfo());
                    insertChildSettle.setCoreSn(responseMessage.getMsgId());
                } else {
                    memo = "核销异常：" + "调用核心出现异常：" + responseMessage.getRtnCode() + "|" + responseMessage.getRtnInfo();
                }

            } catch (Exception e) {
                logger.error("执行异常", e);
                updateBean.setMemo("核销异常：" + e.getMessage());
            } finally {
                if (!StringUtils.isBlank(memo) && memo.length() > 500) {
                    memo = memo.substring(0, 500);
                }
                updateBean.setMemo(memo);
                merchantSettleMapper.updateChildSettle(insertChildSettle);
                merchantSettleDao.dealAfterVerified(updateBean);
            }
            if (null == responseMessage) {
                throw new RuntimeException("调用核心出现异常：无返回结果");
            }
            if (RequestColumnValues.RtnResult.SUCCESS != responseMessage.getRtnResult()) {
                throw new RuntimeException("调用核心出现异常：" + responseMessage.getRtnCode() + "|"
                        + responseMessage.getRtnInfo());
            }
        } else {
            updateBean.setFinishDate(nowDate);
            updateBean.setStatus(MerchantSettleStatus.VERIFIED);
            updateBean.setMemo("核销成功");
            merchantSettleDao.dealAfterVerified(updateBean);
        }
        return map;
    }

    /**
     * 商户结算交易
     *
     * @param requestMessage
     * @return
     */
    public Map<String, Object> settleBuss(MerchantSettle settle, String reqSerialId) {
        SevenpayCoreServiceInterface sevenpayCoreServiceInterface = (SevenpayCoreServiceInterface) SpringUtils
                .getBean("coreHttpInvokerProxy");

        logger.info("商户结算交易");
        Map<String, Object>  resuMap=new HashMap<>();
        RequestMessage<TradeRequest> requestMessage = new RequestMessage<TradeRequest>();
        ResponseMessage<TradeResponse> responseMessage = null;

        // 根据客户号查找协议内容，找出结算的银行卡号等信息
        // String bankAccId = this.selectBankCard(settle.getCustId(), settle.getBankCardNo()); //TODO 1、改为查协议
        ProtocolContent content = protocolContentMapper.selectProto(settle.getCustId());
        if(content == null){
            throw new IllegalArgumentException("商户协议不存在！");
        }
        // 查找七分钱账号
        TdCustInfo custInfo = custInfoMapper.selectById(settle.getCustId());
        String originMsgId = merchantSettleMapper.selectCronSnByApplyCordId(settle.getApplyCoreId());

        try {
            requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_SETTLE);
            requestMessage.setReqDatetime(new Date());
            requestMessage.setReqMsgNum(1);
            requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
            requestMessage.setReqSerialId(reqSerialId);

            TradeRequest request = new TradeRequest();
            List<TransBean> list = new ArrayList<TransBean>();
            TransBean transBean = new TransBean();
            transBean.setTransType(RequestColumnValues.TransType.SEVEN_TO_BANK_CARD);
            transBean.setPayCustId(settle.getCustId());
            transBean.setPayAcctType(RequestColumnValues.AcctType.SEVEN_BUSS);
            transBean.setPayAcctId(custInfo.getQfqAccId());
            transBean.setRcvCustId(settle.getCustId());
            transBean.setRcvAcctType(RequestColumnValues.AcctType.BANK_DEBIT);
            transBean.setRcvAcctId(custInfo.getQfqAccId());
            transBean.setCurrCode(RequestColumnValues.CurrCode.CNY);
            transBean.setTransAmt(settle.getSettleAmt());
            transBean.setFeePay(new BigDecimal(0));
            transBean.setFeeRcv(new BigDecimal(0));
            transBean.setBrief("商户结算");
            transBean.setOriginMsgId(originMsgId);
            list.add(transBean);
            request.setTransList(list);

            //卡信息
            request.setExtDataMap(protocolSplit(content.getProtocolContent()));
            requestMessage.setRequest(request);
            logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));
            responseMessage = sevenpayCoreServiceInterface.trade(requestMessage);

            logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));
            resuMap.put("transAmt", settle.getSettleAmt());
            resuMap.put("bankInfo", protocolSplit(content.getProtocolContent()));
            resuMap.put("responseMessage", responseMessage);

        } catch (Exception e) {
            logger.error("核心结算申请异常", e);
            throw e;
        }

        return resuMap;
    }

    public HashMap<String,String> protocolSplit(String str) {
        logger.info("******从协议中解析所需的参数*****start");
        String[] Content = str.split("@_@");
        HashMap<String,String> map = new HashMap<String,String>();
        for (int i = 0; i < Content.length; i++) {
            String[] s = Content[i].split(":");
            for (int j = 0; j < s.length; j++) {
                if ("bankCardNo".equals(s[j])) {
                    map.put("bankCardNo", s[j + 1]);
                } else if ("bankCardName".equals(s[j])) {
                    map.put("bankCardName", s[j + 1]);
                } else if ("CNAPS".equals(s[j])) {
                    map.put("CNAPS", s[j + 1]);
                }
            }
        }
        logger.info("******从协议中解析所需的参数*****end");
        return map;
    }
}
