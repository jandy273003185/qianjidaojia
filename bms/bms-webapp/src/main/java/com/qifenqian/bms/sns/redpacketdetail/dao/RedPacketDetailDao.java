package com.qifenqian.bms.sns.redpacketdetail.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.sns.redpacketdetail.bean.RedPacketDetail;
import com.qifenqian.bms.sns.redpacketdetail.mapper.RedPacketDetailMapper;

@Repository
public class RedPacketDetailDao {
	
	@Autowired
	private RedPacketDetailMapper redPacketDetailMapper;
	@Page
	public List<RedPacketDetail> selectRedPacketDetailList(RedPacketDetail queryBean) {
		return redPacketDetailMapper.selectRedPacketDetailList(queryBean);
	}
	
}
