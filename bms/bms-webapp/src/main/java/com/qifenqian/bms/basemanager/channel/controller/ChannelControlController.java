package com.qifenqian.bms.basemanager.channel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.channel.bean.ChannelControlBean;
import com.qifenqian.bms.basemanager.channel.service.ChannelControlService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@Controller
@RequestMapping(ChannelControlPath.BASE)
public class ChannelControlController {
	
	private static Logger logger = LoggerFactory.getLogger(ChannelControlController.class);
	
	@Autowired
	private ChannelControlService channelControlService;
	
	/***
	 * 渠道控制信息列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(ChannelControlPath.LIST)
	public ModelAndView list(ChannelControlBean queryBean) {
		logger.info("查询渠道控制信息列表");
		ModelAndView mv = new ModelAndView(ChannelControlPath.BASE + ChannelControlPath.LIST);
		
		List<ChannelControlBean> list = channelControlService.getChannelControlList(queryBean);
		mv.addObject("channelControlList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(ChannelControlPath.ADD)
	@ResponseBody
	public String add(HttpServletRequest httpRequest,ChannelControlBean channelControlBean) {
		logger.info("添加渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			
			channelControlBean.setCreateId(WebUtils.getUserInfo().getUserId()+"");
			
				channelControlService.addChannelControl(channelControlBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "添加成功");
			
			
		}
		
		return jo.toJSONString();
	}
	
	@RequestMapping(ChannelControlPath.UPDATE)
	@ResponseBody
	public String update(HttpServletRequest httpRequest,ChannelControlBean channelControlBean) {
		logger.info("修改渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			jo = channelControlService.updateChannelControl(channelControlBean);
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(ChannelControlPath.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest httpRequest,String channel,String mchId) {
		logger.info("删除渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			
			jo=channelControlService.deleteChannelControl(channel,mchId);
			
		}
		return jo.toJSONString();
	}
	
}
