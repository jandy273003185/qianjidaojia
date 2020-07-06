package com.qifenqian.bms.basemanager.payIn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.payIn.bean.PayIn;
import com.qifenqian.bms.basemanager.payIn.service.PayInService;
import com.qifenqian.bms.common.bean.AjaxJson;
@Controller
@RequestMapping(PayInPath.BASE)
public class PayInController {

	private static Logger logger = LoggerFactory.getLogger(PayInController.class);

	@Autowired
	private PayInService payInService;

	/**
	 * 显示代付垫资费率信息列表
	 * 
	 * @param payIn
	 * @return
	 */
	@RequestMapping(PayInPath.LIST)
	public ModelAndView list(PayIn payIn) {
		ModelAndView model = new ModelAndView(PayInPath.BASE + PayInPath.LIST);
		
		List<PayIn> payIns = payInService.selectPayIn(payIn);
		
		model.addObject("queryBean", payIn);
		model.addObject("payInList", JSONObject.toJSON(payIns));
		
		return model;
	}


	/**
	 * 增加代付垫资费率
	 * 
	 * @param payIn
	 */
	@RequestMapping(PayInPath.ADD)
	@ResponseBody
	public String addBank(PayIn payIn) {

		logger.info("添加代付垫资费率");

		JSONObject jsonObject = new JSONObject();

		try {
			
			PayIn payInResult  = 	payInService.selectPayInByFeeCode(payIn.getFeeCode());
			if(null != payInResult ){
				jsonObject.put("result", "fail");
				jsonObject.put("message", "该费用代码已经占用");
			}else{
				payInService.insertPayIn(payIn);
				jsonObject.put("result", "success");
			}
			
			
		} catch (Exception e) {
			logger.error("增加银行出错：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();
	}

	/**
	 * 更新代付垫资费率
	 * 
	 * @param payIn
	 * @return
	 */
	@RequestMapping(PayInPath.UPDATE)
	@ResponseBody
	public String updatePayIn(PayIn payIn) {
		logger.info("更新费率信息");
		JSONObject jsonObject = new JSONObject();
		try {
			payInService.updatePayIn(payIn);
			jsonObject.put("result", "success");
			logger.info("代付垫资费率更新完成！");
		} catch (Exception e) {
			logger.error("更新代付垫资费率未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();

	}
	
	/**
	 * 停用代付垫资费率
	 * 
	 * @param payIn
	 * @return
	 */
	@RequestMapping(PayInPath.STOP)
	@ResponseBody
	public String stop(String feeCode) {

		AjaxJson aj = new AjaxJson();

		try {
			logger.info("请求删除银行[{}]信息", feeCode);
			payInService.stopPayInByCode(feeCode);
			aj.setMessage("停用成功");
			aj.setSuccess(true);
		} catch (Exception e) {
			logger.error("停用异常", e);
			aj.setMessage(e.getMessage());
			aj.setSuccess(false);
		}

		return JSONObject.toJSONString(aj);

	}
	
	
}
