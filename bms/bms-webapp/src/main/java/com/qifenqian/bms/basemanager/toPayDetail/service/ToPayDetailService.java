package com.qifenqian.bms.basemanager.toPayDetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.toPayDetail.bean.ToPaySingleDetail;
import com.qifenqian.bms.basemanager.toPayDetail.dao.ToPayDetailDao;

@Service
public class ToPayDetailService {

	@Autowired
	private ToPayDetailDao dao;
	
	public List<ToPaySingleDetail> listDetail(ToPaySingleDetail bean) {
		return dao.listDetail(bean);
	}

	public List<ToPaySingleDetail> exportDetailList(ToPaySingleDetail toPaySingleDetail) {
		return dao.exportDetailList(toPaySingleDetail);
	}

}
