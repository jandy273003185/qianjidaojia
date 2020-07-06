package com.qifenqian.bms.basemanager.channelroute.countroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.basemanager.channelroute.service.ChannelRouteService;
import com.qifenqian.bms.basemanager.channelroute.service.serviceimpl.ChannelRouteServiceImpl;
import com.qifenqian.bms.basemanager.router.RouterController;
import com.qifenqian.bms.common.bean.AjaxJson;

@Controller
@RequestMapping(ChannelRouteControlPath.BASE)
public class ChannelRouteController {
	private static Logger logger = LoggerFactory.getLogger(RouterController.class);

	@Autowired
	private ChannelRouteService channelRouteService;

	@Autowired
	private ChannelRouteServiceImpl channelRouteServiceImpl;
	
	
	//显示路由列表
	@RequestMapping(ChannelRouteControlPath.LIST)
	public ModelAndView list(ChannelRouteBean channelrouteBean) {
		ModelAndView model = new ModelAndView(ChannelRouteControlPath.BASE + ChannelRouteControlPath.LIST);
		List<ChannelRouteBean> channelRouteBeans = channelRouteService.selectRoute(channelrouteBean);
		model.addObject("queryBean", channelrouteBean);
		model.addObject("routerList", JSONObject.toJSON(channelRouteBeans));
		return model;
	}
	
	
	//删除路由
	@RequestMapping(ChannelRouteControlPath.DELETE)
	@ResponseBody
	public String deleteChannelRoute(String id) {

		AjaxJson aj = new AjaxJson();

		try {
			logger.info("请求删除路由[{}]信息", id);
			channelRouteServiceImpl.deleteChannelRoute(id);
			aj.setMessage("删除成功");
			aj.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除异常", e);
			aj.setMessage(e.getMessage());
			aj.setSuccess(false);
		}

		return JSONObject.toJSONString(aj);
	}
	
	//新增路由
	@RequestMapping(ChannelRouteControlPath.ADD)
	@ResponseBody
	public String addRouters(ChannelRouteBean queryBean){
		JSONObject json = new JSONObject();
		try {
			if (null == queryBean) {
				throw new IllegalArgumentException("新增对象为空");
			}

			channelRouteService.addChannelRoute(queryBean);		
			
			//取checkbox中数据
			String codeString = queryBean.getChannels();	
			if(codeString !=""){
				String[] codes = codeString.split(",");
				
				List<ChannelRouteBean> routeBeans = new ArrayList<ChannelRouteBean>();
					
				for (int i = 0; i < codes.length; i++) {
					
					//取对应channelID
					routeBeans = channelRouteService.selectId(codes[i]);
	
					queryBean.setChannelId(routeBeans.get(0).getId());
					
					//取对应channel_route_id
					List<ChannelRouteBean> channelRouteBeans = channelRouteService.selectRoute(queryBean);
					
					queryBean.setId(channelRouteBeans.get(0).getId());
	
					channelRouteService.addRouteRf(queryBean);
				}
			}
			json.put("result", "SUCCESS");
		} catch (Exception e) {

			logger.error("新增路由申请异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}
	
	
	//更新路由
	@RequestMapping(ChannelRouteControlPath.UPDATE)
	@ResponseBody
	public String update(ChannelRouteBean queryBean){
		JSONObject jsonObject = new JSONObject();
		logger.info("请求转发channelRouteBean：[{}]", JSONObject.toJSONString(queryBean, true));
		try{
			
			channelRouteService.updateChannelRoute(queryBean);
			
			//根据路由ID先删去原渠道ID，再添加新的渠道ID
			channelRouteServiceImpl.deleteRouteId(queryBean);
			
			String codeString = queryBean.getChannels();
			if(codeString !=""){
				
				String[] codes = codeString.split(",");
				
				List<ChannelRouteBean> routeBeans = new ArrayList<ChannelRouteBean>();
					
				for (int i = 0; i < codes.length; i++) {
					
					//取对应channelID
					routeBeans = channelRouteService.selectId(codes[i]);
	
					queryBean.setChannelId(routeBeans.get(0).getId());
					
					//取对应channel_route_id
					queryBean.getId();
	
					channelRouteService.addRouteRf(queryBean);
	
				}
			}	  
			jsonObject.put("result", "SUCCESS");
		}catch(Exception e){
			logger.error("修改路由异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
}
