package com.qifenqian.bms.basemanager.toPayDetail.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.toPayDetail.bean.ToPaySingleDetail;
import com.qifenqian.bms.basemanager.toPayDetail.mapper.ToPayDetailMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ToPayDetailDao {

	@Autowired
	private ToPayDetailMapper mapper;

	@Page
	public List<ToPaySingleDetail> listDetail(ToPaySingleDetail bean) {
		return mapper.listDetail(bean);
	}

	public List<ToPaySingleDetail> exportDetailList(ToPaySingleDetail toPaySingleDetail) {
		
		return mapper.exportDetailList(toPaySingleDetail);
	}
	
}
