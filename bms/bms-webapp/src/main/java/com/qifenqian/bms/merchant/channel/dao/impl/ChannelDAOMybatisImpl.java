package com.qifenqian.bms.merchant.channel.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.common.util.RedisUtil;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.dao.ChannelDAO;
import com.qifenqian.bms.merchant.channel.mapper.ChannelMapperReader;
import com.qifenqian.bms.merchant.channel.mapper.ChannelMapperWriter;
import com.qifenqian.bms.platform.web.page.Page;

import redis.clients.jedis.Jedis;

@Repository
public class ChannelDAOMybatisImpl implements ChannelDAO {

	private final static int[] REDIS_UNITS = {5,6};
	
	@Autowired
	private ChannelMapperReader channelReader;
	@Autowired
	private ChannelMapperWriter channelWriter;

	@Override
	@Page
	public List<ChannelBean> selectChannels(ChannelBean queryBean) {
		List<ChannelBean> list = channelReader.selectChannels(queryBean);
		return list;
	}

	@Override
	@Page
	public List<ChannelDetailBean> selectChannelDetails(ChannelDetailBean queryBean) {
		return channelReader.selectChannelDetails(queryBean);
	}

	@Override
	public ChannelBean selectChannel(String custId, String channelCode, String merNo) {
		return channelReader.selectChannel(custId, channelCode, merNo);
	}

	@Override
	public List<ChannelDetailBean> selectChannelDetail(String custId, String merNo) {
		return channelReader.selectChannelDetail(custId, merNo);
	}

	@Override
	public boolean insertChannel(ChannelBean channel) {
		int ret1 = channelWriter.insertChannel(channel);
		return ret1 > 0;
	}

	@Override
	public boolean deleteChannel(ChannelBean channel) {

		return false;
	}

	@Override
	public boolean updateChannel(ChannelBean channel, ChannelBean oldBean) {
		int ret1 = channelWriter.updateChannel(channel, oldBean);
		return ret1 > 0;
	}

	@Override
	public boolean insertChannelDetail(ChannelBean channel) {

		int ret = channelWriter.insertChannelDetail(channel);
		return ret > 0;
	}

	@Override
	public boolean deleteChannelDetail(ChannelBean channel) {
		int ret = channelWriter.deleteChannelDetail(channel.getMerchantChannelId(), channel.getCustId());
		return ret >= 0;
	}

	@Override
	public boolean activeChannel(ChannelBean bean) {
		int ret = channelWriter.activeChannel(bean);
		return ret > 0;
	}

	@Override
	public boolean deactiveChannel(ChannelBean bean) {
		int res1 = channelWriter.deactiveChannel(bean);
		return res1 > 0;
	}

	@Override
	public boolean activeChannelDetail(ChannelBean bean) {
		int res = channelWriter.activeChannelDetail(bean);
		return res > 0;
	}

	@Override
	public boolean deactiveChannelDetail(ChannelBean bean) {
		int res = channelWriter.deactiveChannelDetail(bean);
		return res > 0;
	}
	
	/**
	 * 删除缓存, 不管有没有成功都返回成功
	 */
	@Override
	public boolean deleteChannelFoRedis(ChannelBean bean) {
		
		try {
			Jedis jedis = RedisUtil.getJedis();
			for(int i : REDIS_UNITS) {
				jedis.select(i);
				jedis.flushDB();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}
