package com.qifenqian.bms.basemanager.protocoltemplate;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.PayProdService;
import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.service.ProtocolTemplateService;
import com.sevenpay.plugin.IPlugin;

@Controller
@RequestMapping(ProtocolTemplatePath.BASE)
public class ProtocolTemplateController {
	@Autowired
	private ProtocolTemplateService protocolTemplateService;
	
	@Autowired
	private PayProdService payProdService;
	@Autowired
	private IPlugin plugin;
	private static Logger logger = LoggerFactory.getLogger(ProtocolTemplateController.class);

	/**
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(ProtocolTemplatePath.LIST)
	public ModelAndView list(ProtocolTemplate queryBean) {
		ModelAndView mv = new ModelAndView(ProtocolTemplatePath.BASE + ProtocolTemplatePath.LIST);
		List<ProtocolTemplate> list = protocolTemplateService.select(queryBean);
		String content = plugin.findDictByPath(Constant.FIX_CONTENT_PATH).trim();//协议模板固定内容
		
		for(ProtocolTemplate template : list){
			template.setChoiceContext(template.getProtocolTemplate());
			if(StringUtils.isEmpty(template.getProtocolTemplate().trim())){
				template.setProtocolTemplate(content);
			}else{
				template.setProtocolTemplate(content+","+template.getProtocolTemplate());
			}
		}
		mv.addObject("queryBean", queryBean);
		mv.addObject("fixContent", content);
		mv.addObject("protocolTemplates", JSONObject.toJSON(list));
		return mv;
	}

	/***
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ProtocolTemplatePath.ADD)
	@ResponseBody
	public String add(ProtocolTemplate insertBean) {
		JSONObject object = new JSONObject();
		
		try {
			protocolTemplateService.insert(insertBean);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("新增协议模板失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/***
	 * 
	 * @param updateBean
	 * @return
	 */
	@RequestMapping(ProtocolTemplatePath.EDIT)
	@ResponseBody
	public String edit(ProtocolTemplate updateBean) {
		JSONObject object = new JSONObject();
		try {
			protocolTemplateService.update(updateBean);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("修改协议模板失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

}
