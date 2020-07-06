package com.qifenqian.bms.sns.redpacket.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;
import com.qifenqian.bms.sns.redpacket.mapper.RedEnvelopeInfoMapper;

@Repository
public class RedPacketInfoDao {

	@Autowired
	private RedEnvelopeInfoMapper redEnvelopeInfoMapper;
	
	@Page
	public List<RedEnvelopeInfo> selectList(RedEnvelopeInfo queryBean) {
		return redEnvelopeInfoMapper.selectList(queryBean);
	}
}
