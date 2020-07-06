package com.qifenqian.bms.v2.biz.message.sms.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.sms.message.mapper.BaseMessageMapper;
import com.qifenqian.bms.sms.message.service.BatchSendMessageThread;
import com.qifenqian.bms.sms.thread.BaseMessageThreadPool;
import com.qifenqian.bms.v2.biz.message.sms.bean.domain.BaseMessageAO;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 17:16
 */
@Service
public class BizSmsService extends BaseService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseMessageMapper baseMessageMapper;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String EXCEPTION = "EXCEPTION";
    private static final String INIT = "INIT";

    public PageInfo<BaseMessage> findSmsList(BaseMessage baseMessage) {
        List<BaseMessage> baseMessages = baseMessageMapper.selectBaseMessageList(baseMessage);
        return new PageInfo<>(baseMessages);
    }

    public ResultData delete(BaseMessage baseMessage) {
        int result = baseMessageMapper.deleteBaseMessage(baseMessage);
        if (result < 1) {
            throw new BizException("删除短信信息异常!");
        }
        return ResultData.success();
    }

    public ResultData batchDelete(BaseMessageAO baseMessageAO) {
        int result = baseMessageMapper.batchDelete(baseMessageAO);
        if (result < 1) {
            throw new BizException("删除短信信息异常!");
        }
        return ResultData.success();
    }

    public ResultData singleSend(BaseMessage baseMessageBean) {
        String msg = "发送信息异常!";
        try {
            MessageBean messageBean = new MessageBean();
            messageBean.setContent(baseMessageBean.getContent());
            messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
            messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
            String[] tos = new String[]{baseMessageBean.getMobile()};
            messageBean.setTos(tos);
            IPlugin plugin = commonService.getIPlugin();
            boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
            if (falg) {
                logger.info("发送信息成功===== {}", baseMessageBean.getId());
                baseMessageBean.setStatus(SUCCESS);
                msg = "发送信息成功";
            } else {
                logger.error("发送信息失败");
                baseMessageBean.setStatus(FAILURE);
                msg = "发送信息失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseMessageBean.setStatus(EXCEPTION);
        }
        baseMessageMapper.updateSingleMessage(baseMessageBean);
        return ResultData.success(msg, null);
    }

    public ResultData batchSend(BaseMessageAO baseMessageAO) {
        String sendId = baseMessageAO.getSendId();
        try {
            if (Constant.JGKJ_SUCC.equals(sendId)) {
                BaseMessage queryBean = new BaseMessage();
                queryBean.setStatus(INIT);
                /** 待发送数据 **/
                List<BaseMessage> baseMessageList = baseMessageMapper.selectBaseMessageList(queryBean);
                logger.info("=========待发送数据数量========{}", baseMessageList.size());

                if (baseMessageList.size() > 0) {
                    for (BaseMessage waitSendBean : baseMessageList) {
                        BatchSendMessageThread batchSendMessageThread = (BatchSendMessageThread) SpringUtils
                                .getBean("batchSendMessageThread");
                        batchSendMessageThread.setBaseMessage(waitSendBean);
                        BaseMessageThreadPool.getInstance().put(batchSendMessageThread);
                    }
                }
            } else {
                String[] idArray = sendId.split("\\*");
                for (int i = 0; i < idArray.length; i++) {
                    BaseMessage queryBean = new BaseMessage();
                    String id = idArray[i];
                    queryBean.setId(id);
                    BaseMessage sendBean = baseMessageMapper.selectBaseMessageById(queryBean);
                    if (sendBean != null) {
                        BatchSendMessageThread batchSendMessageThread = (BatchSendMessageThread) SpringUtils
                                .getBean("batchSendMessageThread");
                        batchSendMessageThread.setBaseMessage(sendBean);
                        BaseMessageThreadPool.getInstance().put(batchSendMessageThread);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("发送信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }
}
