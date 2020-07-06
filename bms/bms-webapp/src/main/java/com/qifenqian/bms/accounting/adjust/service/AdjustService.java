package com.qifenqian.bms.accounting.adjust.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustMain;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustSingleHistoryListBean;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.dao.AccountingAdjustDetailMapper;
import com.qifenqian.bms.accounting.adjust.dao.AccountingAdjustMainMapper;
import com.qifenqian.bms.accounting.adjust.dao.AccountingSingleAdjustDetailMapper;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.accounting.exception.dao.transrecordflow.mapper.TransRecordFlowMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.ConstantUtils;
import com.qifenqian.bms.platform.utils.SequenceUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.IsAdjustJGKJ;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.ReqSysId;
import com.sevenpay.invoke.common.type.RequestColumnValues.RtnResult;
import com.sevenpay.invoke.transaction.adjust.AdjustRequest;
import com.sevenpay.invoke.transaction.adjust.AdjustResponse;
import com.sevenpay.invoke.transaction.adjust.bean.AdjustBuss;
import com.sevenpay.invoke.transaction.adjust.bean.AdjustCust;
import com.sevenpay.invoke.transaction.adjust.bean.AdjustInner;
import com.sevenpay.invoke.transaction.adjust.bean.AdjustJgkj;
import com.sevenpay.invoke.transaction.queryacctseven.QueryAcctSevenRequest;
import com.sevenpay.invoke.transaction.queryacctseven.QueryAcctSevenResponse;
import com.sevenpay.invoke.transaction.trade.TradeRequest;
import com.sevenpay.invoke.transaction.trade.TradeResponse;
import com.sevenpay.invoke.transaction.trade.bean.TransBean;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.exception.bean.BmsException;
import com.sevenpay.plugin.exception.bean.ExceptionColumnValues;

/**
 * 调账Service
 * 
 * @project sevenpay-bms-web
 * @fileName AdjustService.java
 * @author kunwang.li
 * @date 2015年7月25日
 * @memo
 * 
 */
@Service
public class AdjustService {

  private static Logger logger = LoggerFactory.getLogger(AdjustService.class);

  @Autowired
  private AdjustServiceSupport adjustServiceSupport;

  @Autowired
  private TaskService taskService;

  @Autowired
  private AccountingAdjustMainMapper accountingAdjustMainMapper;

  @Autowired
  private AccountingAdjustDetailMapper accountingAdjustDetailMapper;

  @Autowired
  private AccountingSingleAdjustDetailMapper accountingSingleAdjustDetailMapper;

  @Autowired
  private TransRecordFlowMapper transRecordFlowMapper;

  @Autowired
  @Qualifier("coreHttpInvokerProxy")
  private SevenpayCoreServiceInterface coreHttpInvokerProxy;

  @Autowired
  @Qualifier("pluginInvokerProxy")
  IPlugin interfaceService;

  /**
   * 调账经办服务
   * 
   * @param mBean
   * @param dBean
   * @return 业务编号
   */
  @AfterThrowing()
  public String handle(AccountingAdjustMain mBean, List<?> dlist) {

    try {
      String opId = SequenceUtils.getSequence("AA");
      logger.info("开始调账经办处理, opId[{}]", opId);

      mBean.setOpId(opId);
      mBean.setFinishedFlag("0");

      if ("0".equals(mBean.getSingleFlag())) {
        List<AccountingAdjustDetail> detailList = new ArrayList<AccountingAdjustDetail>();
        for (int i = 0; i < dlist.size(); i++) {
          AccountingAdjustDetail dBean = (AccountingAdjustDetail) dlist.get(i);
          dBean.setOpId(mBean.getOpId());
          dBean.setEntryId(String.valueOf(i + 1));
          detailList.add(dBean);
        }

        adjustServiceSupport.handle(mBean, detailList);
      } else {
        List<AccountingSingleAdjustDetail> detailList = new ArrayList<AccountingSingleAdjustDetail>();
        for (int i = 0; i < dlist.size(); i++) {
          AccountingSingleAdjustDetail dBean = (AccountingSingleAdjustDetail) dlist.get(i);
          dBean.setOpId(mBean.getOpId());
          dBean.setEntryId(String.valueOf(i + 1));
          detailList.add(dBean);
        }

        adjustServiceSupport.handleSingle(mBean, detailList);
      }
      return mBean.getOpId();
    } catch (Exception e) {
      logger.error("调账记录保存异常:", e);
      throw e;
    }
  }

  /**
   * 通过taskId查询AccountingAdjustMain
   * 
   * @param taskId
   * @return
   */
  public AccountingAdjustMain queryAccountingAdjustMain(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    return adjustServiceSupport.queryAccountingAdjustMain(task.getProcessInstanceId());
  }

  public AccountingAdjustMain queryAccountingAdjustMainByPid(String processInstanceId) {
    return adjustServiceSupport.queryAccountingAdjustMain(processInstanceId);
  }

  /**
   * 查询分录列表
   * 
   * @param opId
   * @return 返回调账分录列表
   */
  public List<AccountingAdjustDetail> queryEntryList(String opId) {

    return adjustServiceSupport.queryEntryList(opId);
  }

  /**
   * 查询单边调账分录列表
   * 
   * @param opId
   * @return 返回调账分录列表
   */
  public List<AccountingSingleAdjustDetail> querySingleEntryList(String opId) {

    return adjustServiceSupport.querySingleEntryList(opId);
  }

  /**
   * 查询审批明细信息
   * 
   * @param opId
   * @return 返回各级审批信息列表
   */

  public List<Comment> queryCommentList(String opId) {

    return adjustServiceSupport.queryApproveCommentList(opId);
  }

  /**
   * 更新调账记录后再次提交复核
   * 
   * @param mBean
   * @param dlist
   */
  public void update4Recheck(AccountingAdjustMain adjustMain, List<AccountingAdjustDetail> dlist) {

    for (int i = 0; i < dlist.size(); i++) {
      AccountingAdjustDetail dBean = dlist.get(i);
      dBean.setOpId(adjustMain.getOpId());
      dBean.setEntryId(String.valueOf(i + 1));
    }

    adjustServiceSupport.update4Recheck(adjustMain, dlist);
  }

  /**
   * 更新单边调账记录后再次提交复核
   * 
   * @param adjustMain
   * @param dlist
   */
  public void update4RecheckSingle(AccountingAdjustMain adjustMain, List<AccountingSingleAdjustDetail> dlist) {

    for (int i = 0; i < dlist.size(); i++) {
      AccountingSingleAdjustDetail dBean = dlist.get(i);
      dBean.setOpId(adjustMain.getOpId());
      dBean.setEntryId(String.valueOf(i + 1));
    }

    adjustServiceSupport.update4RecheckSingle(adjustMain, dlist);
  }

  /**
   * 删除调账记录(并不是物理删除, 只是改变状态)
   * 
   * @param opId
   */
  public void delete(String opId) {

    logger.info("删除调账业务数据 opId[{}]", opId);

    adjustServiceSupport.delete(opId);
  }

  /**
   * 审批
   * 
   * @param opId
   * @param memo
   * @param checkResult
   */
  public void check(String opId, boolean checkResult, String memo, String checkerUid) {
    try {
      // 锁定本地数据库记录
      adjustServiceSupport.claim4Check(opId, checkerUid);

      // 是否交由总经理审批, 现在规则没有定义， 暂定为均无需总经理审批
      boolean approve = false;

      // 审核人员审批通过后， 不用总经理审批的， 直接调用核心过账接口过账
      if (checkResult == true && approve == false) {
        AccountingAdjustMain adjustMain = accountingAdjustMainMapper.selectByPrimaryKey(opId);
        if ("0".equals(adjustMain.getSingleFlag())) {
          ResponseMessage<TradeResponse> response = invokeCore(adjustMain);
          if (RtnResult.SUCCESS != response.getRtnResult()) {
            logger.error("核心调账失败, 原因:{} {}", response.getRtnCode(), response.getRtnInfo());

            // 增加异常监控
            BmsException bmsException = new BmsException();
            {
              bmsException.setExceCode("00000");
              bmsException.setExceType(ExceptionColumnValues.exceType.BUSINESS);
              bmsException.setLevel(ExceptionColumnValues.level.HIGH);
              bmsException.setModel(ExceptionColumnValues.model.BMS);
              bmsException.setBusType(ExceptionColumnValues.busType.ADJUST);
              bmsException.setOrderId(opId);
              bmsException.setExceDesc("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setDescription("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setCurrentState(ExceptionColumnValues.currentState.CREATE);
            }
            interfaceService.addException(bmsException);
            throw new RuntimeException("核心调账失败, 原因: " + response.getRtnCode() + response.getRtnInfo());
          }
        } else {
          ResponseMessage<AdjustResponse> response = invokeCoreSingle(adjustMain);
          if (response != null && RtnResult.SUCCESS != response.getRtnResult()) {
            logger.error("核心调账失败, 原因:{}", response.getRtnInfo());

            // 增加异常监控
            BmsException bmsException = new BmsException();
            {
              bmsException.setExceCode("00000");
              bmsException.setExceType(ExceptionColumnValues.exceType.BUSINESS);
              bmsException.setLevel(ExceptionColumnValues.level.HIGH);
              bmsException.setModel(ExceptionColumnValues.model.BMS);
              bmsException.setBusType(ExceptionColumnValues.busType.ADJUST);
              bmsException.setOrderId(opId);
              bmsException.setExceDesc("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setDescription("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setCurrentState(ExceptionColumnValues.currentState.CREATE);
            }
            interfaceService.addException(bmsException);
            throw new RuntimeException("核心调账失败, 原因: " + response.getRtnCode() + response.getRtnInfo());
          }
        }
      }

      // 更新本地数据库状态
      adjustServiceSupport.check(opId, checkResult, approve, memo, checkerUid);

    } catch (Exception e) {
      logger.error("审批调账业务异常:", e);
      throw e;
    }
  }

  /**
   * 调用核心业务逻辑
   * 
   * @param adjustMain
   */
  private ResponseMessage<TradeResponse> invokeCore(AccountingAdjustMain adjustMain) {

    // 双边调账
    AccountingAdjustDetailExample example = new AccountingAdjustDetailExample();
    example.createCriteria().andOpIdEqualTo(adjustMain.getOpId());
    List<AccountingAdjustDetail> detailList = accountingAdjustDetailMapper.selectByExample(example);

    RequestMessage<TradeRequest> requestMessage = new RequestMessage<TradeRequest>();
    {
      requestMessage.setMsgType(MsgType.INNER_TRANSFER);
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
      requestMessage.setReqSerialId(adjustMain.getOpId());
      requestMessage.setReqMsgNum(detailList.size());
      requestMessage.setReqDatetime(new Date());

      TradeRequest request = new TradeRequest();
      {
        // 入账信息
        List<TransBean> transList = new ArrayList<TransBean>();
        {
          TransBean sevenToSeven = new TransBean();
          {
            for (AccountingAdjustDetail detail : detailList) {
              sevenToSeven.setTransType(RequestColumnValues.TransType.SEVEN_TO_SEVEN);
              // 付方账户
              sevenToSeven.setPayCustId(detail.getDebitSubjectId()); // 内部账户此处传入科目
              sevenToSeven.setPayAcctType(detail.getDebitAcctType());
              sevenToSeven.setPayAcctId(detail.getDebitAcctNo());
              // 收方账户
              sevenToSeven.setRcvCustId(detail.getCreditSubjectId()); // 内部账户此处传入科目
              sevenToSeven.setRcvAcctType(detail.getCreditAcctType());
              sevenToSeven.setRcvAcctId(detail.getCreditAcctNo());

              sevenToSeven.setCurrCode(detail.getCurcde());
              sevenToSeven.setTransAmt(detail.getAmt());
              sevenToSeven.setFeePay(new BigDecimal("0.00"));
              sevenToSeven.setFeeRcv(new BigDecimal("0.00"));
              sevenToSeven.setBrief(StringUtils.isBlank(adjustMain.getMemo()) ? adjustMain.getOpId() : adjustMain.getMemo());
            }
          }
          transList.add(sevenToSeven);
        }
        request.setTransList(transList);
      }
      requestMessage.setRequest(request);
    }

    ResponseMessage<TradeResponse> responseMessage = coreHttpInvokerProxy.trade(requestMessage);

    logger.info(JSONObject.toJSONString(responseMessage));

    return responseMessage;
  }

  /**
   * 单边调账调用核心接口
   * 
   * @param adjustMain
   * @return
   */
  private ResponseMessage<AdjustResponse> invokeCoreSingle(AccountingAdjustMain adjustMain) {

    ResponseMessage<AdjustResponse> responseMessage = null;

    // 单边调账
    AccountingSingleAdjustDetailExample example = new AccountingSingleAdjustDetailExample();
    example.createCriteria().andOpIdEqualTo(adjustMain.getOpId());
    List<AccountingSingleAdjustDetail> detailList = accountingSingleAdjustDetailMapper.selectByExample(example);

    AccountingSingleAdjustDetail detail = detailList.get(0);

    if (detail.getAcctType().equals(AcctType.SEVEN_BUSS)) {

      RequestMessage<AdjustRequest<AdjustBuss>> requestMessage = new RequestMessage<AdjustRequest<AdjustBuss>>();
      {
        requestMessage.setMsgType(MsgType.BUSS_ADJUST);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(adjustMain.getOpId());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        AdjustRequest<AdjustBuss> request = new AdjustRequest<AdjustBuss>();
        {
          AdjustBuss adjustBean = new AdjustBuss();

          adjustBean.setAcctId(detail.getAcctNo());
          adjustBean.setCurrCode(detail.getCurcde());
          adjustBean.setAdjustAmt(detail.getAmt());
          adjustBean.setOnwayAmt(detail.getOnwayAmt());
          adjustBean.setUsableAmt(detail.getUsableAmt());
          adjustBean.setBrief(StringUtils.isBlank(adjustMain.getMemo()) ? adjustMain.getOpId() : adjustMain.getMemo());

          request.setAdjustBean(adjustBean);

        }
        requestMessage.setRequest(request);
      }

      responseMessage = coreHttpInvokerProxy.bussAdjust(requestMessage);

    } else if (detail.getAcctType().equals(AcctType.SEVEN_CUST)) {
      TransRecordFlow transRecordFlow = null;
      if (IsAdjustJGKJ.Y.equals(detail.getIsAdjustJGKJ()) && detail.getAmt().compareTo(ConstantUtils.ZERO) > 0) {

        if (StringUtils.isBlank(adjustMain.getRelationOpId())) {
          throw new IllegalArgumentException("关联交易业务编号为空");
        }
        transRecordFlow = transRecordFlowMapper.selectRecordFlowByReqId(adjustMain.getRelationOpId());

        if (transRecordFlow == null) {
          throw new IllegalArgumentException("关联操作对象为空");
        }
        if (StringUtils.isBlank(transRecordFlow.getMsgId())) {
          throw new IllegalArgumentException("关联交易原始核心编号为空");
        }
        if (transRecordFlow.getTransAmt().compareTo(detail.getAmt()) != 0) {
          throw new IllegalArgumentException("操作金额与关联原始金额不一致");
        }
      }
      RequestMessage<AdjustRequest<AdjustCust>> requestMessage = new RequestMessage<AdjustRequest<AdjustCust>>();
      {
        requestMessage.setMsgType(MsgType.CUST_ADJUST);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(adjustMain.getOpId());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        AdjustRequest<AdjustCust> request = new AdjustRequest<AdjustCust>();
        {
          AdjustCust adjustBean = new AdjustCust();
          adjustBean.setAcctId(detail.getAcctNo());
          adjustBean.setCurrCode(detail.getCurcde());
          adjustBean.setAdjustAmt(detail.getAmt());
          adjustBean.setOnwayAmt(detail.getOnwayAmt());
          adjustBean.setUsableAmt(detail.getUsableAmt());
          adjustBean.setBrief(StringUtils.isBlank(adjustMain.getMemo()) ? adjustMain.getOpId() : adjustMain.getMemo());
          adjustBean.setIsAdjustJGKJ(detail.getIsAdjustJGKJ());
          if (null != transRecordFlow) {
            adjustBean.setOriginMsgId(transRecordFlow.getMsgId());
            adjustBean.setRebackAmt(transRecordFlow.getTransAmt());
            adjustBean.setOriginId(transRecordFlow.getId());
          }
          request.setAdjustBean(adjustBean);
        }
        requestMessage.setRequest(request);
      }
      logger.info("客户账户-调用核心调账服务请求[{}]", JSONObject.toJSONString(requestMessage, true));
      responseMessage = coreHttpInvokerProxy.custAdjust(requestMessage);
      logger.info("客户账户-调用核心调账服务返回[{}]", JSONObject.toJSONString(responseMessage, true));
    } else if (detail.getAcctType().equals(AcctType.SEVEN_INNER)) {
      RequestMessage<AdjustRequest<AdjustInner>> requestMessage = new RequestMessage<AdjustRequest<AdjustInner>>();
      {
        requestMessage.setMsgType(MsgType.INNER_ADJUST);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(adjustMain.getOpId());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        AdjustRequest<AdjustInner> request = new AdjustRequest<AdjustInner>();
        {
          AdjustInner adjustBean = new AdjustInner();

          adjustBean.setAcctId(detail.getAcctNo());
          adjustBean.setCurrCode(detail.getCurcde());
          adjustBean.setAdjustAmt(detail.getAmt());
          adjustBean.setBrief(StringUtils.isBlank(adjustMain.getMemo()) ? adjustMain.getOpId() : adjustMain.getMemo());
          request.setAdjustBean(adjustBean);
        }
        requestMessage.setRequest(request);
      }

      responseMessage = coreHttpInvokerProxy.innerAdjust(requestMessage);
    } else if (detail.getAcctType().equals(AcctType.JGKJ_CARD)) {
      RequestMessage<AdjustRequest<AdjustJgkj>> requestMessage = new RequestMessage<AdjustRequest<AdjustJgkj>>();
      {
        requestMessage.setMsgType(MsgType.JGKJ_ADJUST);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
        requestMessage.setReqSerialId(adjustMain.getOpId());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        AdjustRequest<AdjustJgkj> request = new AdjustRequest<AdjustJgkj>();
        {
          AdjustJgkj adjustBean = new AdjustJgkj();

          adjustBean.setAcctId(detail.getAcctNo());
          adjustBean.setCurrCode(detail.getCurcde());
          adjustBean.setAdjustAmt(detail.getAmt());
          adjustBean.setBrief(StringUtils.isBlank(adjustMain.getMemo()) ? adjustMain.getOpId() : adjustMain.getMemo());

          request.setAdjustBean(adjustBean);

        }
        requestMessage.setRequest(request);
      }

      responseMessage = coreHttpInvokerProxy.jgkjAdjust(requestMessage);
    }

    logger.info(JSONObject.toJSONString(responseMessage, true));

    return responseMessage;
  }

  /**
   * 总经理审批
   * 
   * @param opId
   * @param memo
   * @param checkResult
   */
  public void approve(String opId, boolean checkResult, String memo, String checkerUid) {
    try {
      // 锁定待审批件
      adjustServiceSupport.claim4Approve(opId, checkerUid);

      // 调用核心系统调账
      if (checkResult == true) {
        AccountingAdjustMain adjustMain = accountingAdjustMainMapper.selectByPrimaryKey(opId);
        if ("0".equals(adjustMain.getSingleFlag())) {
          ResponseMessage<TradeResponse> response = invokeCore(adjustMain);
          if (response != null && RtnResult.SUCCESS != response.getRtnResult()) {
            logger.error("核心调账失败, 原因:{} {}", response.getRtnCode(), response.getRtnInfo());

            // 增加异常监控
            BmsException bmsException = new BmsException();
            {
              bmsException.setExceCode("00000");
              bmsException.setExceType(ExceptionColumnValues.exceType.BUSINESS);
              bmsException.setLevel(ExceptionColumnValues.level.HIGH);
              bmsException.setModel(ExceptionColumnValues.model.BMS);
              bmsException.setBusType(ExceptionColumnValues.busType.ADJUST);
              bmsException.setOrderId(opId);
              bmsException.setExceDesc("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setDescription("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setCurrentState(ExceptionColumnValues.currentState.CREATE);
            }
            interfaceService.addException(bmsException);
            throw new RuntimeException("核心调账失败, 原因: " + response.getRtnCode() + response.getRtnInfo());
          }
        } else {
          ResponseMessage<AdjustResponse> response = invokeCoreSingle(adjustMain);
          if (response != null && RtnResult.SUCCESS != response.getRtnResult()) {
            logger.error("核心调账失败, 原因:{}", response.getRtnInfo());

            // 增加异常监控
            BmsException bmsException = new BmsException();
            {
              bmsException.setExceCode("00000");
              bmsException.setExceType(ExceptionColumnValues.exceType.BUSINESS);
              bmsException.setLevel(ExceptionColumnValues.level.HIGH);
              bmsException.setModel(ExceptionColumnValues.model.BMS);
              bmsException.setBusType(ExceptionColumnValues.busType.ADJUST);
              bmsException.setOrderId(opId);
              bmsException.setExceDesc("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setDescription("核心调账失败: [" + response.getRtnCode() + "]" + response.getRtnInfo());
              bmsException.setCurrentState(ExceptionColumnValues.currentState.CREATE);
            }
            interfaceService.addException(bmsException);
            throw new RuntimeException("核心调账失败, 原因: " + response.getRtnCode() + response.getRtnInfo());
          }
        }
      }

      // 更新本地数据库状态
      adjustServiceSupport.approve(opId, checkResult, memo, checkerUid);
    } catch (Exception e) {
      logger.error("审批调账业务异常:", e);
      throw e;
    }
  }

  public ResponseMessage<QueryAcctSevenResponse> queryActInfo(String acctId, AcctType acctType) {
    RequestMessage<QueryAcctSevenRequest> requestMessage = new RequestMessage<QueryAcctSevenRequest>();
    {
      requestMessage.setMsgType(MsgType.ACCT_SEVEN_QUERY);
      requestMessage.setReqSysId(ReqSysId.BMS);
      requestMessage.setReqDatetime(new Date());
      requestMessage.setReqMsgNum(1);
      requestMessage.setReqSerialId(GenSN.getSN());
      QueryAcctSevenRequest request = new QueryAcctSevenRequest();
      {
        request.setAcctId(acctId);
        request.setAcctType(acctType);
      }
      requestMessage.setRequest(request);
    }
    ResponseMessage<QueryAcctSevenResponse> responseMessage = coreHttpInvokerProxy.queryAcctSeven(requestMessage);
    return responseMessage;
  }

  /**
   * 调账历史查询
   * 
   * @param listBean
   * @return
   */
  public List<AccountingAdjustHistoryListBean> historyList(AccountingAdjustHistoryListBean listBean) {
    return adjustServiceSupport.historyList(listBean);
  }

  public List<AccountingAdjustSingleHistoryListBean> singleHistoryList(AccountingAdjustSingleHistoryListBean listBean) {
    return adjustServiceSupport.singleHistoryList(listBean);
  }

  public List<AccountingAdjustHistoryListBean> historyListReport(AccountingAdjustHistoryListBean listBean) {
    return accountingAdjustMainMapper.selectHistoryList(listBean);
  }

  public List<AccountingAdjustSingleHistoryListBean> singleHistoryListReport(AccountingAdjustSingleHistoryListBean listBean) {
    return accountingAdjustMainMapper.selectSingleHistoryList(listBean);
  }

}
