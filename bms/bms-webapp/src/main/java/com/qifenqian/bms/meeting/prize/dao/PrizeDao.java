package com.qifenqian.bms.meeting.prize.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.mapper.PrizeMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class PrizeDao {
	@Autowired
	private PrizeMapper prizeMapper;
	@Page
	public List<PrizeBean> selectPrizeBeanList(PrizeBean queryBean) {
		return prizeMapper.selectPrizeBeanList(queryBean);
	}
	
}
