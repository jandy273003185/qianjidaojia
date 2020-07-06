package com.qifenqian.bms.meeting.prizewin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.meeting.prizewin.bean.PrizeWinBean;
import com.qifenqian.bms.meeting.prizewin.dao.PrizeWinDao;
import com.qifenqian.bms.meeting.prizewin.mapper.PrizeWinMapper;
import com.qifenqian.bms.meeting.prizewin.type.PrizeWinStatus;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class PrizeWinService {
	@Autowired
	private PrizeWinDao prizeWinDao;

	@Autowired
	private PrizeWinMapper prizeWinMapper;

	public List<PrizeWinBean> selectLotteryUserList(PrizeWinBean queryBean) {

		return prizeWinDao.selectLotteryUserList(queryBean);
	}

	public int deleteLottery(PrizeWinBean deleteBean) {
		deleteBean.setLupdUser(WebUtils.getUserInfo().getUserId());
		deleteBean.setStatus(PrizeWinStatus.DISABLE);
		return prizeWinMapper.updatePrizeWin(deleteBean);
	}

}
