package com.qifenqian.bms.meeting.prize.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.meeting.prize.bean.PrizeBean;

@MapperScan
public interface PrizeMapper {

	List<PrizeBean> selectPrizeBeanList(PrizeBean queryBean);

	int insertPrizeSingle(PrizeBean insertBean);
	
	int updatePrizeById(PrizeBean updateBean);
	
	PrizeBean selectPrizeSingleById(int prizeId);

}
