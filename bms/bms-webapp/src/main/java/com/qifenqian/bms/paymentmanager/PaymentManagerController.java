package com.qifenqian.bms.paymentmanager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.service.PaymentService;

@Controller
@RequestMapping (PaymentManagerPath.BASE)
public class PaymentManagerController {
	private static Logger logger = LoggerFactory.getLogger(PaymentManagerController.class);

	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 代付
	 * @param request
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.PAYMENT)
	public String payment(HttpServletRequest request) {
		logger.info("================= 开始 =================");
		
		return null;
	}
	
	/**
	 * 批量代付-文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.FILEUPLOAD)
	public ModelAndView fileUpload(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("================= 开始 =================");
	
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTLIST);

		JSONObject object = new JSONObject();
		String batchNo = new SimpleDateFormat("yyyyMMdd").format(new Date())+RandomStringUtils.randomNumeric(8);
	    Map<String, Object> map = null;
	    try{
	    map = paymentService.fileUpload(file,request, batchNo);
	    List<TdPaymentBatDetail> detail = null;
	    List<TdPaymentBatDetail> detail2 = null;
		if("SUCCESS".equals(map.get("result").toString())){
			detail = paymentService.ReadExcel(map.get("filePath").toString(), batchNo, Boolean.valueOf(map.get("isExcel2003").toString()));
//			paymentService.addPaymentList(detail);
			detail2= paymentService.addPaymentList(detail);
			object.put("result", "SUCCESS");
			object.put("message", "解析上传文件成功");
//			object.put("batNo", batchNo);
//			object.put("detailList", JSONObject.toJSON(detail2));
			//mv.addObject("detailList", detail2);
			request.getSession().setAttribute("detailList", detail2);
		} else {
//			mv.addObject("result", "FAIL");
			object.put("result", "FAIL");
			object.put("message", "解析上传文件失败");
		}
	    }catch(Exception e){
	    	object.put("result", "FAIL");
			object.put("message", "解析上传文件异常");
	    }
//	    String url=PaymentManagerPath.BASE+PaymentManagerPath.PAYMENTLIST+"?"+object.toJSONString();
//	    return new ModelAndView("redirect:" + url);
	    mv.addObject("object", object);
		return mv;
	}
	
	/**
	 * 查询批次数据
	 * @param batNo
	 * @return
	 */
	@RequestMapping(PaymentManagerPath.SELECTLIST)
	public ModelAndView selectList(String batNo) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTLIST);
		mv.addObject("detailList",JSONObject.toJSON(paymentService.selecPaymentList(batNo)));
		return mv;
	}
	
	/**
	 * 批量代付-提交银行处理
	 * @param request
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.BATPAYMENTDATA)
	@ResponseBody
	public String batPayment(HttpServletRequest request) {
		logger.info("================= 开始 =================");
		JSONObject object = new JSONObject();
		List<TdPaymentBatDetail> list = (List<TdPaymentBatDetail>) request.getSession().getAttribute("detailList");
		logger.info("页面传送数据:"+JSONObject.toJSONString(list));
		Map<String, String> map = paymentService.paymentManage(list, null);
		if("SUCESS".equals(map.get("result").toString())){
			object.put("result", "SUCCESS");
			object.put("message", "提交银行处理成功");
			request.getSession().removeAttribute("detailList");
		} else {
			object.put("result", "FAIL");
			object.put("message", "提交银行处理失败");
		}
		return object.toJSONString();
	}
	
	/**
	 * 修改代付明细数据
	 * @param bean
	 * @return
	 */
	@RequestMapping (PaymentManagerPath.UPDATE)
	@ResponseBody
	public String batPayment(TdPaymentBatDetail bean) {
		logger.info("================= 开始 =================");
		JSONObject object = new JSONObject();
		
		String resMsg=paymentService.updateDetail(bean);
		if("SUCESS".equals(resMsg)){
			object.put("result", "SUCCESS");
			object.put("message", "修改成功");
		} else {
			object.put("result", "FAIL");
			object.put("message", "修改失败");
		}
		return object.toJSONString();
	}
	
	@RequestMapping (PaymentManagerPath.STATUS)
	@ResponseBody
	public String selectBatInfo(TdPaymentBatDetail bean) {
		Map<String, String> result = new HashMap<String, String>();
		if(StringUtils.isBlank(bean.getBatNo())){
			result.put("result", "FAIL");
			result.put("message", "查询失败，批次号为空");
			return JSONObject.toJSONString(result);
		}
		result = paymentService.selectPaymentTrans(bean);
		return JSONObject.toJSONString(result);
	}
}
