package com.qifenqian.bms.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.qifenqian.bms.unionPay.unionPayImpl.RefundImpl;
import com.qifenqian.bms.unionPay.unionPayImpl.RevocationImpl;
import com.stc.gateway.unionpay.AsynResponseListener;

@Configuration
public class HttpServiceBean {

	/**
	 * <!-- 交易撤销 -->
	 * 
	 * @param revocationImpl
	 * @return
	 */
	@Bean("/unionpay/revocation")
	public HttpInvokerServiceExporter revocationImplServiceExporter(RevocationImpl revocationImpl) {
		HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
		httpInvokerServiceExporter.setService(revocationImpl);
		httpInvokerServiceExporter.setServiceInterface(AsynResponseListener.class);
		return httpInvokerServiceExporter;
	}

	/**
	 * <!-- 退款 -->
	 * 
	 * @param refundImpl
	 * @return
	 */
	@Bean("/unionpay/refund")
	public HttpInvokerServiceExporter refundImplServiceExporter(RefundImpl refundImpl) {
		HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
		httpInvokerServiceExporter.setService(refundImpl);
		httpInvokerServiceExporter.setServiceInterface(com.stc.gateway.unionpay.AsynResponseListener.class);
		return httpInvokerServiceExporter;
	}

}
