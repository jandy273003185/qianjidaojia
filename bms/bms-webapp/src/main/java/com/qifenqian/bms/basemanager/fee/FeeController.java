package com.qifenqian.bms.basemanager.fee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.fee.bean.Fee;
import com.qifenqian.bms.basemanager.fee.service.FeeService;

@Controller
@RequestMapping(FeePath.BASE)
public class FeeController {

	private static Logger logger = LoggerFactory.getLogger(FeeController.class);
	@Autowired
	private FeeService feeService;
	
	/**
	 * 手续费展示
	 * @param fee
	 * @return
	 */
	@RequestMapping(FeePath.LIST)
	public ModelAndView list(Fee fee){
		ModelAndView mv =new ModelAndView(FeePath.BASE+FeePath.LIST);
		List<Fee> list= feeService.selectFees(fee);
		String feeCode=fee.getFeeCode();
		String feeName=fee.getFeeName();
		mv.addObject("feeCode", feeCode);
		mv.addObject("feeName", feeName);
		mv.addObject("fees", list);
		mv.addObject("feeList", JSONObject.toJSONString(list));
		return mv;
	}
	
	/**
	 * 增加费用
	 * @param city
	 * @return
	 */
	@RequestMapping(FeePath.ADD)
	@ResponseBody
	public String add(Fee fee){
		
		
		JSONObject js = new JSONObject();
		
		try {
			logger.info("增加费用信息");
			feeService.addFee(fee);
			js .put("result", "success");
		} catch (Exception e) {
			logger.error("请求增加费用异常",e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}
	
		return js.toJSONString();
		
	}
	
	/**
	 * 删除费用信息
	 * @param city
	 * @return
	 */
	@RequestMapping(FeePath.DELETE)
	@ResponseBody
	public String delete(String feeCode){
		
		
		JSONObject js = new JSONObject();
		
		try {
			logger.info("删除费用信息");
			feeService.deleteFee(feeCode);
			js .put("result", "success");
		} catch (Exception e) {
			logger.error("请求删除费用异常",e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}
	
		return js.toJSONString();
		
	}
	
	/**
	 * 更新费用信息
	 * @param city
	 * @return
	 */
	@RequestMapping(FeePath.UPDATE)
	@ResponseBody
	public String update(Fee fee){
		
		
		JSONObject js = new JSONObject();
		
		try {
			logger.info("更新费用信息");
			feeService.updateCity(fee);
			js .put("result", "success");
		} catch (Exception e) {
			logger.error("请求更新费用异常",e);
			js.put("result", "fail");
			js.put("message", e.getMessage());
		}
	
		return js.toJSONString();
		
	}
	
	@RequestMapping(FeePath.VALIDATE)
	@ResponseBody
	public String validateFeeCode(String  feeCode){
		
		logger.info("校验费率code");
		
		JSONObject object = new JSONObject();
		
		try {
			Fee fee = feeService.selectFeeCode(feeCode);
			
			if(null == fee){
				object.put("result", "SUCCESS");
			}else{
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("费率code出现问题"+e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		
		return object.toJSONString();
		
	}
}
