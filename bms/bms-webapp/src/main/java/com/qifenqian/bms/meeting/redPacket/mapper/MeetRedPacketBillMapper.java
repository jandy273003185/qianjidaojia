package com.qifenqian.bms.meeting.redPacket.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBill;
import com.qifenqian.bms.meeting.redPacket.bean.MeetRedPacketBillConditionBean;

@MapperScan
public interface MeetRedPacketBillMapper {
  
    List<MeetRedPacketBill> selectRedPacket(MeetRedPacketBillConditionBean record);
}