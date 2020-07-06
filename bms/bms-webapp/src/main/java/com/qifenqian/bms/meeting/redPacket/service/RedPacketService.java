package com.qifenqian.bms.meeting.redPacket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBill;
import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBillConditionBean;
import com.qifenqian.bms.meeting.redPacket.dao.RedPacketDAO;
import com.qifenqian.bms.meeting.redPacket.mapper.MeetRedPacketBillMapper;

@Service
public class RedPacketService {

	@Autowired
	private RedPacketDAO redPacketDAO;
	
	@Autowired
	private MeetRedPacketBillMapper MeetRedPacketBillMapper;
	
	public  List<MeetRedPacketBill> selectRedPacket(MeetRedPacketBillConditionBean record){
		  return redPacketDAO.selectRedPacket(record);
	}
	
	public  List<MeetRedPacketBill> exportRedPacket(MeetRedPacketBillConditionBean record){
		  return MeetRedPacketBillMapper.selectRedPacket(record);
	}
}
