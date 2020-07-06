package com.qifenqian.bms.basemanager.router;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.router.bean.Servicechannel;
import com.qifenqian.bms.basemanager.router.service.RouterService;
import com.qifenqian.bms.basemanager.router.service.serviceimpl.RouterServiceImpl;
import com.qifenqian.bms.common.bean.AjaxJson;

@Controller
@RequestMapping(RouterPath.BASE)
public class RouterController {

	private static Logger logger = LoggerFactory.getLogger(RouterController.class);

	@Autowired
	private RouterService routerService;

	@Autowired
	private RouterServiceImpl routerServiceImpl;
	/**
	 * 显示路由列表
	 */
	@RequestMapping(RouterPath.LIST)
	public ModelAndView list(Servicechannel servicechannel) {
		ModelAndView model = new ModelAndView(RouterPath.BASE + RouterPath.LIST);
		List<Servicechannel> Servicechannels = routerService.selectRouters(servicechannel);
		model.addObject("queryBean", servicechannel);
		model.addObject("routerList", JSONObject.toJSON(Servicechannels));
		return model;
	}
	
	
	//新增路由
	@RequestMapping(RouterPath.ADD)
	@ResponseBody
	public String addRouters(Servicechannel queryBean){
		JSONObject json = new JSONObject();
		try {
			if (null == queryBean) {
				throw new IllegalArgumentException("新增对象为空");
			}
			queryBean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			
			routerService.addRouters(queryBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增路由申请异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}
	
	
	//更新路由
	@RequestMapping(RouterPath.UPDATE)
	@ResponseBody
	public String update(Servicechannel servicechannel){
		JSONObject jsonObject = new JSONObject();
		logger.info("请求转发servicechannel：[{}]", JSONObject.toJSONString(servicechannel, true));
		try{
			routerService.updateRouter(servicechannel);
			jsonObject.put("result", "SUCCESS");
		}catch(Exception e){
			logger.error("修改servicechannel异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	
	@RequestMapping(RouterPath.DELETE)
	@ResponseBody
	public String deleteRouterById(String id) {

		AjaxJson aj = new AjaxJson();

		try {
			logger.info("请求删除路由[{}]信息", id);
			routerServiceImpl.deleteRouterById(id);
			aj.setMessage("删除成功");
			aj.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除异常", e);
			aj.setMessage(e.getMessage());
			aj.setSuccess(false);
		}

		return JSONObject.toJSONString(aj);

	}
	}
