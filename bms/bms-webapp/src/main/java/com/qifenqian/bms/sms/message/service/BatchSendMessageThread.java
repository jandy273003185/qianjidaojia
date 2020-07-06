package com.qifenqian.bms.sms.message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.sms.message.bean.BaseMessage;

@Service
@Scope("prototype")
public class BatchSendMessageThread extends Thread {

	private static Logger logger = LoggerFactory.getLogger(BatchSendMessageThread.class);

	@Autowired
	private BaseMessageService baseMessageService;

	private static final String SUCCESS = "SUCCESS";

	private static final String FAILURE = "FAILURE";

	private BaseMessage baseMessageBean = null;

	public void setBaseMessage(BaseMessage baseMessageBean) {

		this.baseMessageBean = baseMessageBean;
	}

	public void run() {
		if (null == baseMessageBean) {
			throw new RuntimeException("发送对象为空");
		}
		String result = baseMessageService.sendBatchMessage(baseMessageBean);

		switch (result) {
		case SUCCESS:
			logger.info("======用户编号{}短信发送成功！", baseMessageBean.getId());
			break;
		case FAILURE:
			logger.info("======用户编号{}短信发送失败！", baseMessageBean.getId());
			break;
		default:
			logger.info("======用户编号{}短信发送异常！", baseMessageBean.getId());
			break;
		}
	}
}
