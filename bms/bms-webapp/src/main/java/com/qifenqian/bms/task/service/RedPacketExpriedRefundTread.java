package com.qifenqian.bms.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;

@Service
@Scope("prototype")
public class RedPacketExpriedRefundTread extends Thread {


	private RedEnvelopeInfo redEnvelopeInfo = null;

	@Autowired
	private RedPacketExpriedRefundService redPacketExpriedRefundService;


	public void setRedEnvelopeInfo(RedEnvelopeInfo redEnvelopeInfo) {
		this.redEnvelopeInfo = redEnvelopeInfo;
	}


	public void run() {
		Thread.currentThread().setName("expiredRedPacketProc-Thread");
		redPacketExpriedRefundService.refund(redEnvelopeInfo);
	}

}
