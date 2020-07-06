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
import com.qifenqian.bms.basemanager.channel.bean.ChannelInfoBean;
import com.qifenqian.bms.basemanager.channel.service.ChannelInfoService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@Controller
@RequestMapping(ChannelInfoPath.BASE)
public class ChannelInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(ChannelInfoController.class);
	
	@Autowired
	private ChannelInfoService channelInfoService;
	
	/***
	 * 渠道信息列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(ChannelInfoPath.LIST)
	public ModelAndView list(ChannelInfoBean queryBean) {
		logger.info("查询渠道信息列表");
		ModelAndView mv = new ModelAndView(ChannelInfoPath.BASE + ChannelInfoPath.LIST);
		List<ChannelInfoBean> channelInfoList = channelInfoService.getChannelInfoList(queryBean);
		mv.addObject("channelInfoList", channelInfoList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(ChannelInfoPath.ADD)
	@ResponseBody
	public String add(HttpServletRequest httpRequest,ChannelInfoBean channelInfoBean) {
		logger.info("添加渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			channelInfoBean.setChannelId(GenSN.getSN());
			channelInfoBean.setCreateId(WebUtils.getUserInfo().getUserId()+"");
			try {
				channelInfoService.addChannelInfo(channelInfoBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "添加成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
			
		}
		
		return jo.toJSONString();
	}
	
	@RequestMapping(ChannelInfoPath.UPDATE)
	@ResponseBody
	public String update(HttpServletRequest httpRequest,ChannelInfoBean channelInfoBean) {
		logger.info("修改渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				channelInfoService.updateChannelInfo(channelInfoBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "修改成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
				
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(ChannelInfoPath.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest httpRequest,String channelId) {
		logger.info("删除渠道信息");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
				channelInfoService.deleteChannelInfo(channelId);
				jo.put("result", "SUCCESS");
				jo.put("message", "删除成功");
		}
		return jo.toJSONString();
	}
}
