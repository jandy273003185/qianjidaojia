package com.qifenqian.bms.meeting.winShow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.meeting.winShow.bean.BasePrizeWin;
import com.qifenqian.bms.meeting.winShow.mapper.BasePrizeWinMapper;

@Service
public class WinShowService {

	@Autowired
	private BasePrizeWinMapper basePrizeWinMapper;
	
	/**
	 * 当前中奖人列表
	 * @param prizeId
	 * @return
	 */
	public  List<BasePrizeWin>  showPrizeWinNow(String prizeId){
		return basePrizeWinMapper.showPrizeWinNow(prizeId);
	}
}
