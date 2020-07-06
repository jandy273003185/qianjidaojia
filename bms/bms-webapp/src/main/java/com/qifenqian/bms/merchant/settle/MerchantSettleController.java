package com.qifenqian.bms.merchant.settle;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.ValidateTool;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettleExport;
import com.qifenqian.bms.merchant.settle.service.MerchantSettleService;
import com.qifenqian.bms.platform.utils.FormTokenUtil;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleController.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo 
 */
@Controller
@RequestMapping(MerchantSettlePath.BASE)
public class MerchantSettleController {
	
	private Logger logger = LoggerFactory.getLogger(MerchantSettleController.class);

	@Autowired
	private MerchantSettleService merchantSettleService;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@Autowired
	private IPlugin iPlugin;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(MerchantSettlePath.LIST)
	public ModelAndView list(MerchantSettle requestBean,String isFirst) {
		// 返回视图
		ModelAndView mv = new ModelAndView(MerchantSettlePath.BASE + MerchantSettlePath.LIST);
		
		if (StringUtils.isEmpty(isFirst)) {
			requestBean.setWorkDateMax(DateUtils.formatDate(new Date(), "yyyyMMdd"));
			requestBean.setWorkDateMin(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		}
		// 列表
		mv.addObject("settleList", merchantSettleService.selectListByPage(requestBean));
		mv.addObject("isFirst", "No");
		mv.addObject("selectBean", requestBean);
		// 返回
		return mv;
	}
	
	/**
	 * 代理商结算列表
	 * @param requestBean
	 * @param isFirst
	 * @return
	 */
	@RequestMapping(MerchantSettlePath.AGENCYLIST)
	public ModelAndView ageclist(MerchantSettle requestBean,String isFirst) {
		// 返回视图
		ModelAndView mv = new ModelAndView(MerchantSettlePath.BASE + MerchantSettlePath.AGENCYLIST);
		
		if (StringUtils.isEmpty(isFirst)) {
			requestBean.setWorkDateMax(DateUtils.formatDate(new Date(), "yyyyMMdd"));
			requestBean.setWorkDateMin(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		}
		// 列表
		mv.addObject("settleList", merchantSettleService.selectAgencyList(requestBean));
		mv.addObject("isFirst", "No");
		mv.addObject("selectBean", requestBean);
		// 返回
		return mv;
	}
	
	
	/**
	 * 确认
	 * @param requestBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.AUDIT)
	public String audit(MerchantSettle requestBean,HttpServletRequest request) {
		// 请求bean 打印
		logger.info("商户结算复核bean：[{}]", JSONObject.toJSONString(requestBean, true));

		JSONObject jsonObject = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", "已经确认,请不要重复提交");
			return jsonObject.toJSONString();
		}
		try {
			merchantSettleService.audit(requestBean);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("复核异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 联合
	 * @param togetherIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.TOGETHER)
	public String together(String togetherIds) {
		// 请求bean 打印
		logger.info("商户结算联合：[{}]", JSONObject.toJSONString(togetherIds, true));

		JSONObject jsonObject = new JSONObject();
		try {
			merchantSettleService.together(togetherIds);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("联合异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 分离
	 * @param togetherIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.SEPARATE)
	public String separate(String togetherId) {
		// 请求bean 打印
		logger.info("商户结算联合：[{}]", JSONObject.toJSONString(togetherId, true));

		JSONObject jsonObject = new JSONObject();
		try {
			merchantSettleService.separate(togetherId);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("分离异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 批量复核
	 * @param requestBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.BATCHSETTLE)
	public String batchSettle(String settleIds,HttpServletRequest request) {
		logger.info("商户批量复核[{}]",settleIds);

		JSONObject jsonObject = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", "已经复核,请不要重复提交");
			return jsonObject.toJSONString();
		}
		try {
			String[]  idArray =  settleIds.split("\\*");
			String settleFailIds = "";
			String settleSuccessIds = "";
			for(int i=0; i<idArray.length;i++){
				MerchantSettle bean = new MerchantSettle();
				String id = idArray[i].split("#")[1];
				String merchantId = idArray[i].split("#")[0];
				bean.setId(id);
				try {
					merchantSettleService.audit(bean);
					//成功商户编号 字符串 （返回页面展示）
					settleSuccessIds += merchantId +'@';
				} catch (Exception e) {
					logger.error("复核异常[{}]",idArray[i]);
					//失败商户编号 字符串 （返回页面展示）
					settleFailIds += merchantId+'@' ;
				}
			
			}
			
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("settleFailIds", settleFailIds.length() > 0 ? settleFailIds.substring(0, settleFailIds.length()-1): settleFailIds );
			jsonObject.put("settleSuccessIds", settleSuccessIds.length() > 0 ? settleSuccessIds.substring(0, settleSuccessIds.length()-1): settleSuccessIds);
		} catch (Exception e) {
			logger.error("复核异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	
	
	/**
	 * 复核撤销
	 * @param requestBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.CANCEL)
	public String cancel(MerchantSettle requestBean) {
		// 请求bean 打印
		logger.info("商户结算复核撤销bean：[{}]", JSONObject.toJSONString(requestBean, true));

		JSONObject jsonObject = new JSONObject();
		try {
			merchantSettleService.cancel(requestBean);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("复核撤销异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 核销
	 * @param requestBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.VERIFIED)
	public String verified(MerchantSettle requestBean ,HttpServletRequest request) {
		// 请求bean 打印
		logger.info("商户结算核销bean：[{}]", JSONObject.toJSONString(requestBean, true));
		JSONObject jsonObject = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", "已经核销,请不要重复提交");
			return jsonObject.toJSONString();
		}
		
		try {
			Map<String, Object> map = merchantSettleService.verified(requestBean);
			BigDecimal transAmt = (BigDecimal) map.get("transAmt");
			Map<String, String> bankInfo = (Map<String, String>) map.get("bankInfo");
			String bankCardNo = bankInfo.get("bankCardNo");
			String bankCardName = bankInfo.get("bankCardName");
			String mouble = (String) map.get("mouble");
			String date = DatetimeUtils.shortDate();
			String cardNo = ValidateTool.sbuStr(bankCardNo);
			String content="【七分钱支付】您于"+date+"，收款总额为"+transAmt.toString()+"元。现已将该款项结算至您的"+bankCardName+"（尾号"+cardNo+"），预计今晚24：00前到账，如有疑问请联系客服：400-633-0707";
			sendInfo(mouble, content);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("核销异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 批量核销
	 * @param requestBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantSettlePath.BATCHVERIFIED)
	public String batchVerified(String settleIds,HttpServletRequest request ) {
		logger.info("商户结算核销");
		
		JSONObject jsonObject = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", "已经核销,请不要重复提交");
			return jsonObject.toJSONString();
		}
		try {
			
			String[]  idArray =  settleIds.split("\\*");
			String settleFailIds = "";
			String settleSuccessIds = "";
			for(int i=0; i<idArray.length;i++){
				MerchantSettle bean = new MerchantSettle();
				String id = idArray[i].split("#")[1];
				String merchantId = idArray[i].split("#")[0];
				bean.setId(id);
				try {
					merchantSettleService.verified(bean);
					//成功商户编号 字符串 （返回页面展示）
					settleSuccessIds += merchantId +'@';
				} catch (Exception e) {
					logger.error("复核异常[{}]",idArray[i]);
					//失败商户编号 字符串 （返回页面展示）
					settleFailIds += merchantId+'@' ;
				}
			
			}
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("settleFailIds", settleFailIds.length() > 0 ? settleFailIds.substring(0, settleFailIds.length()-1): settleFailIds );
			jsonObject.put("settleSuccessIds", settleSuccessIds.length() > 0 ? settleSuccessIds.substring(0, settleSuccessIds.length()-1): settleSuccessIds);

		} catch (Exception e) {
			logger.error("核销异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 导出商户结算
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantSettlePath.EXPORT)
	public void exportExcel(MerchantSettle requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出excel商户结算表");

		try {
			List<MerchantSettleExport> excels = merchantSettleService.exportSettle(requestBean);
			
			String[] headers = {"编号","结算申请编号","批次号","金蝶清算编号","商户编号","商户名称","七分钱执行","结算开始日","结算结束日","收款笔数","收款总额",
					"收款总费用","撤销笔数","撤销总额","撤销总费用","退款笔数","退款总额","退款总费用","提现笔数","提现总额","提现总费用","转入笔数","转入总额","转入总费用","转出笔数","转出总额","转出总费用",
					"商户应收金","商户应付金","结算金额","完成日期","状态","协议编号","银行卡号","银行卡名","银行信息","备注","生成人","记账日期",
					"生成时间","复核人","复核时间","核销人","核销时间"};
			String fileName = DatetimeUtils.dateSecond() + "_商户结算.xls";
			fileName = URLEncoder.encode(fileName,"UTF8");  
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "商户结算表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel商户结算表成功");
		} catch (Exception e) {
			logger.error("导出excel商户结算表异常", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 导出代理商户结算
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantSettlePath.AGENCYEXPORT)
	public void exportAgencyExcel(MerchantSettle requestBean, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出excel商户结算表");

		try {
			List<MerchantSettleExport> excels = merchantSettleService.exportAgencySettle(requestBean);
			
			String[] headers = {"编号","结算申请编号","金蝶清算编号","代理商编号","代理商名称","七分钱执行","结算开始日","结算结束日","收款笔数","收款总额",
					"收款总费用","撤销笔数","撤销总额","撤销总费用","退款笔数","退款总额","退款总费用","提现笔数","提现总额","提现总费用","转入笔数","转入总额","转入总费用","转出笔数","转出总额","转出总费用",
					"商户应收金","商户应付金","结算金额","完成日期","状态","协议编号","银行卡号","银行卡名","银行信息","备注","生成人","记账日期",
					"生成时间","复核人","复核时间","核销人","核销时间"};
			String fileName = DatetimeUtils.dateSecond() + "_代理商结算.xls";
			fileName = URLEncoder.encode(fileName,"UTF8");  
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代理商结算表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel代理商结算表成功");
		} catch (Exception e) {
			logger.error("导出excel代理商结算表异常", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 生成Token
	 * @param request
	 * @return
	 */
	@RequestMapping(MerchantSettlePath.SAVE_TOKEN)
	@ResponseBody
	public String saveToken(HttpServletRequest request){
		logger.debug("生成Token");
		String token = FormTokenUtil.creatToken(request);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", token);
		return jsonObject.toJSONString();
	}
	/**
	 * 发送信息
	 * @param custId
	 * @param content
	 */
	public String sendInfo(String mobile, String content) {
		logger.info("发送信息 {}",JSONObject.toJSONString(mobile,true));
		JSONObject json = new JSONObject();
		IPlugin plugin = commonService.getIPlugin();
		MessageBean messageBean = new MessageBean();
		messageBean.setContent(content);
		messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
		messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
		String[] tos = new String[]{mobile};
		messageBean.setTos(tos);

		boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
		if (falg) {
			json.put("result", "SUCCESS");
			logger.info("发送信息成功！");
		} else {
			logger.error("发送信息失败");
			json.put("result", "FAIL");
			json.put("message", "发送信息失败");
		}
		return json.toJSONString();
	 
		
	}
}


