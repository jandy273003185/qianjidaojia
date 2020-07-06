package com.qifenqian.bms.basemanager.certify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.certify.bean.IdentityDetailBean;
@Service
@Scope("prototype")
public class CertifyTread extends Thread {


	private IdentityDetailBean IdentityDetailBean = null;

	@Autowired
	private CertifyService certifyService;



	public void setIdentityDetailBean(IdentityDetailBean identityDetailBean) {
		IdentityDetailBean = identityDetailBean;
	}

	public void run() {
		Thread.currentThread().setName("CertifyTread-Thread");
		certifyService.updateIdentityDetial(IdentityDetailBean);
	}


}
