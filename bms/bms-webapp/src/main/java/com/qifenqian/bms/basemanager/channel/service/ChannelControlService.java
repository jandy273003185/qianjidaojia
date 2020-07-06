package com.qifenqian.bms.basemanager.channel.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.channel.bean.ChannelControlBean;
import com.qifenqian.bms.basemanager.channel.mapper.ChannelMapper;

@Service
public class ChannelControlService {
	@Autowired
	private ChannelMapper channelMapper;
	public List<ChannelControlBean> getChannelControlList(
			ChannelControlBean queryBean) {
		
		return channelMapper.getChannelControlList(queryBean);
	}
	public void addChannelControl(ChannelControlBean channelControlBean) {
		// TODO Auto-generated method stub
		channelMapper.addChannelControl(channelControlBean);
	}
	public JSONObject updateChannelControl(ChannelControlBean channelControlBean) {
		// TODO Auto-generated method stub
		JSONObject jo = new JSONObject();
		try {
			channelMapper.updateChannelControl(channelControlBean);
			jo.put("result", "SUCCESS");
			jo.put("message", "修改成功");
		} catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("message", e.getMessage());
		}
		return jo;
	}
	public JSONObject deleteChannelControl(@Param("channelId")String channelId,@Param("mchId")String mchId) {
		// TODO Auto-generated method stub
		JSONObject jo= new JSONObject();
		try {
			channelMapper.deleteChannelControl(channelId,mchId);
			jo.put("result", "SUCCESS");
			jo.put("message", "删除成功");
		} catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("message", e.getMessage());
		}
		return jo;
	}
	
}