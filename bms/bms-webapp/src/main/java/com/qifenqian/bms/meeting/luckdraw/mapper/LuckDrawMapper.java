package com.qifenqian.bms.meeting.luckdraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.meeting.luckdraw.bean.Activity;
import com.qifenqian.bms.meeting.luckdraw.bean.Prize;
import com.qifenqian.bms.meeting.luckdraw.bean.PrizeWin;
import com.qifenqian.bms.meeting.luckdraw.bean.RedPacket;

/**
 * @project sevenpay-bms-web
 * @fileName LuckDrawMapper.java
 * @author huiquan.ma
 * @date 2015年12月15日
 * @memo 
 */
@MapperScan
public interface LuckDrawMapper {

	/**
	 * 查询列表
	 * @param selectBean
	 * @return
	 */
	List<Prize> selectPrizeList(Prize selectBean);
	
	
	/**
	 * 查询单个
	 * @param prizeId
	 * @return
	 */
	Prize selectPrizeSingleById(@Param("prizeId") int prizeId);
	
	/**
	 * 查询可抽奖客户列表
	 * @param selectBean
	 * @return
	 */
	PrizeWin selectCustDrawByPrizeRandom(Prize selectBean);
	
	
	/**
	 * 
	 * @param insertBean
	 * @return
	 */
	int insertPrizeWinSingle(PrizeWin insertBean);
	
	/**
	 * 
	 * @param winId
	 * @return
	 */
	PrizeWin selectPrizeWinSingleById(@Param("winId") int winId);
	
	/**
	 * 
	 * @return
	 */
	List<PrizeWin> selectPrizeWinList(PrizeWin selectBean);
	
	/**
	 * 
	 * @param insertBean
	 * @return
	 */
	int insertRedPacketSingle(RedPacket insertBean);
	
	/**
	 * 查询客户信息
	 * @param custId
	 * @return
	 */
	TdCustInfo selectCustSingleById(String custId);
	
	/**
	 * 查询下一个抽奖奖项
	 * @param activityId
	 * @return
	 */
	Prize selectNextDrawPrize(int activityId);
	
	Activity selectActivitySingleById(int activityId);
}


