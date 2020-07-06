package com.qifenqian.bms.sns.redpacket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.sns.redpacket.bean.RedEnvelopeInfo;

@MapperScan
public interface RedEnvelopeInfoMapper {

	/**
	 * 查询待退款红包
	 * @param queryBean
	 * @return
	 */
	List<RedEnvelopeInfo> selectExpriedRedPacket();
	
	/**
	 * 
	 * @param redEnvelopeInfo
	 * @return
	 */
	int updateRedEnvelopeInfo(RedEnvelopeInfo redEnvelopeInfo);
	
	/**
	 * 
	 * @param redEnvelopeInfo
	 * @return
	 */
	void updateRedEnvelopeInfoState(List<RedEnvelopeInfo> redEnvelopeInfo);
	
	/**
	 * 
	 * @param coreSn
	 * @return
	 */
	RedEnvelopeInfo selectRedEnvelopeInfoRefund(@Param("orderId") String orderId);

	/**
	 * 查询列表
	 * @param queryBean
	 * @return
	 */
	List<RedEnvelopeInfo> selectList(RedEnvelopeInfo queryBean);
	
	
	/**
	 * 
	 * @param redEnvelopeInfo
	 * @return
	 */
	int updateRedEnvelopeInfoByRefund(RedEnvelopeInfo redEnvelopeInfo);
	
	/**
	 * 
	 * @param operId
	 * @return
	 */
	RedEnvelopeInfo selectRedEnvelopeInfo(String operId);
}
