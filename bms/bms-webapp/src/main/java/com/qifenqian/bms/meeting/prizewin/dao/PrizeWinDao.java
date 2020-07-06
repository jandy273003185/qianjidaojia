package com.qifenqian.bms.meeting.prizewin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.meeting.prizewin.bean.PrizeWinBean;
import com.qifenqian.bms.meeting.prizewin.mapper.PrizeWinMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class PrizeWinDao {
	
	@Autowired
	private PrizeWinMapper prizeWinMapper;

	@Page
	public List<PrizeWinBean> selectLotteryUserList(PrizeWinBean queryBean) {
		return prizeWinMapper.selectPrizeWinList(queryBean);
	}

}
