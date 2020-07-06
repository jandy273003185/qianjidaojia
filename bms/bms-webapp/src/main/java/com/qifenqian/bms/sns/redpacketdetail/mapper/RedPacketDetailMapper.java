package com.qifenqian.bms.sns.redpacketdetail.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.sns.redpacketdetail.bean.RedPacketDetail;

@MapperScan
public interface RedPacketDetailMapper {
	/***
	 * 修改
	 * @param updateBean
	 * @return
	 */
	int updateRedPacketDetail(RedPacketDetail updateBean);
	
	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<RedPacketDetail> selectRedPacketDetailByRedEnvId(RedPacketDetail queryBean);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	List<RedPacketDetail> selectRedPacketDetailList(RedPacketDetail queryBean);
}
