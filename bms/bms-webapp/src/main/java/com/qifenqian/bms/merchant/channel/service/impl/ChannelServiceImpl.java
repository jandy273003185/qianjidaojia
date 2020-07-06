package com.qifenqian.bms.merchant.channel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.dao.ChannelDAO;
import com.qifenqian.bms.merchant.channel.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDAO dao;
	@Autowired
	private TdCustInfoService ts;

	@Override
	public List<ChannelBean> getChannels(ChannelBean queryBean) {

		List<ChannelBean> list = dao.selectChannels(queryBean);
		for (ChannelBean item : list) {

			TdCustInfo tc = ts.selectById(item.getCustId());
			String custName = tc == null ? "无此商户,请管理处理数据" : tc.getCustName();
			String merchantCode = tc == null ? "" : tc.getMerchantCode();
			item.setCustName(custName);
			item.setMerchantCode(merchantCode);

			// 填充detail
			List<ChannelDetailBean> details = dao.selectChannelDetail(item.getCustId(), item.getMerchantChannelId());
			if (details != null) {
				String merchantChannelKey = details.size() > 0 ? details.get(0).getMerchantChannelKey() : "";
				item.setDetails(details);
				item.setMerchantChannelKey(merchantChannelKey);
			}

		}

		return list;
	}

	@Override
	public List<ChannelDetailBean> getChannelDetails(ChannelDetailBean queryBean) {
		List<ChannelDetailBean> list = dao.selectChannelDetails(queryBean);
		for (ChannelDetailBean item : list) {

			TdCustInfo tc = ts.selectById(item.getCustId());
			String custName = tc == null ? "无此商户,请管理处理数据" : tc.getCustName();
			item.setCustName(custName);

		}
		return list;
	}

	@Override
	public ChannelBean getChannel(String custId, String channelCode, String merChannerId) {
		ChannelBean channel = dao.selectChannel(custId, channelCode, merChannerId);

		TdCustInfo tc = ts.selectById(channel.getCustId());
		channel.setMerchantCode(tc.getMerchantCode());

		if (channel != null) {
			List<ChannelDetailBean> details = dao.selectChannelDetail(custId, merChannerId);
			if (!details.isEmpty()) {
				channel.setDetails(details);
				channel.setMerchantChannelKey(details.get(0).getMerchantChannelKey());
			}
		}

		return channel;
	}

	@Override
	public boolean saveOrupdateChannel(ChannelBean bean, ChannelBean oldBean) {

		boolean retValue = true;

		if (oldBean == null) {
			boolean ret1 = dao.insertChannel(bean);
			boolean ret2 = dao.insertChannelDetail(bean);
			retValue = ret1 && ret2;
		} else {
			// 先更新channel
			boolean ret1 = dao.updateChannel(bean, oldBean);
			// 先删除channelDetail
			boolean ret2 = dao.deleteChannelDetail(oldBean);
			// 再插入新的值
			boolean ret3 = dao.insertChannelDetail(bean);
			retValue = ret1 && ret2 && ret3;
		}

		return retValue;
	}

	/**
	 * 激活一个通道, 即使将某个通道的状态 设置为 00
	 */
	@Override
	public boolean activeChannel(ChannelBean bean) {
		// 激活通道
		boolean ret1 = dao.activeChannel(bean);
		// 激活通道详细信息
		boolean ret2 = dao.activeChannelDetail(bean);
		dao.deleteChannelFoRedis(bean);
		return ret1 && ret2;
	}

	/**
	 * 下线某个通道 ,即使将某个通道的状态设置为 09 并将此通道在 edis 里面的数据清除
	 */
	@Override
	public boolean deactiveChannel(ChannelBean bean) {
		// 反激活通道
		boolean ret = dao.deactiveChannel(bean);
		// 反激活通道详细信息
		boolean ret3 = dao.deactiveChannelDetail(bean);
		// 清除redis
		boolean ret2 = dao.deleteChannelFoRedis(bean);
		return ret && ret2 && ret3;

	}
}
