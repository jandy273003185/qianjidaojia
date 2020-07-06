package com.qifenqian.bms.basemanager.toPayDetail.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.toPayDetail.bean.ToPaySingleDetail;

@MapperScan
public interface ToPayDetailMapper {

	public List<ToPaySingleDetail> listDetail(ToPaySingleDetail bean);

	public List<ToPaySingleDetail> exportDetailList(ToPaySingleDetail toPaySingleDetail);

}
