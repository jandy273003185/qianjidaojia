package com.qifenqian.bms.meeting.redPacket.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBill;
import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBillConditionBean;
import com.qifenqian.bms.meeting.redPacket.mapper.MeetRedPacketBillMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RedPacketDAO {
	
	@Autowired
	private MeetRedPacketBillMapper meetRedPacketBillMapper;
	
	@Page
	public  List<MeetRedPacketBill> selectRedPacket(MeetRedPacketBillConditionBean record){
		  return meetRedPacketBillMapper.selectRedPacket(record);
	}
}
