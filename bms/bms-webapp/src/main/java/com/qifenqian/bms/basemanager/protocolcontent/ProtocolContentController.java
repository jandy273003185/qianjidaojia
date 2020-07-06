package com.qifenqian.bms.basemanager.protocolcontent;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.mapper.AgentMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContentExportBean;
import com.qifenqian.bms.basemanager.protocolcontent.service.ProtocolContentService;
import com.qifenqian.bms.basemanager.protocoltemplate.bean.ProtocolTemplate;
import com.qifenqian.bms.basemanager.protocoltemplate.service.ProtocolTemplateService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.plugin.IPlugin;


@Controller
@RequestMapping(ProtocolContentPath.BASE)
public class ProtocolContentController {

	private static Logger logger = LoggerFactory.getLogger(ProtocolContentController.class);

	@Autowired
	private ProtocolContentService protocolContentService;
	@Autowired
	private ProtocolTemplateService protocolTemplateService;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private IPlugin plugin;
	@Autowired
	private AgentMapper agentMapper;

	/**
	 * 商户协议列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.LIST)
	public ModelAndView merchantlist(ProtocolContent queryBean) {
		ModelAndView mv = new ModelAndView(ProtocolContentPath.BASE + ProtocolContentPath.LIST);
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		List<ProtocolContent> contentList = null;
		if(isAllList){
			contentList = protocolContentService.select(queryBean);
		}else{
			queryBean.setUserId(userId);
			queryBean.setUserName(WebUtils.getUserInfo().getUserName());
			contentList = protocolContentService.selectMyProto(queryBean);
		}
		mv.addObject("queryBean", queryBean);
		List<Merchant> merchantList = merchantMapper.selectMerchant();
		mv.addObject("merchantList", merchantList);
		mv.addObject("protocolContents", JSONObject.toJSON(contentList));
		return mv;
	}
	
	/**
	 * 代理商协议列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.AGENTLIST)
	public ModelAndView agentlist(ProtocolContent queryBean) {
		ModelAndView mv = new ModelAndView(ProtocolContentPath.BASE + ProtocolContentPath.AGENTLIST);
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		List<ProtocolContent> contentList = null;
		if(isAllList){
			contentList = protocolContentService.selectAgent(queryBean);
		}else{
			queryBean.setUserId(userId);
			queryBean.setUserName(WebUtils.getUserInfo().getUserName());
			contentList = protocolContentService.selectMyAgentProto(queryBean);
		}
		mv.addObject("queryBean", queryBean);
		List<Merchant> merchantList = merchantMapper.selectAgent();
		mv.addObject("merchantList", merchantList);
		mv.addObject("protocolContents", JSONObject.toJSON(contentList));
		return mv;
	}
	/***
	 * 新增协议
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.ADD)
	@ResponseBody
	public String add(ProtocolContent insertBean,String merchantCode) {
		JSONObject object = new JSONObject();
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setMerchantCode(merchantCode);
		custInfo = tdCustInfoService.selectByBean(custInfo);
		if(null == custInfo){
			object.put("result", "fail");
			object.put("message", "商户不存在，请核对商户编号是否正确");
			return object.toJSONString();
		}
		TdLoginUserInfo loginInfo = merchantService.selectLoginUserInfo(custInfo.getCustId());
		if(loginInfo!= null){
			if(Constant.LOGIN_STATE_AUDITORING.equals(loginInfo.getState())){
				object.put("result", "fail");
				object.put("message", "商户正在审核中,不能添加协议");
				return object.toJSONString();
			}
		}
		//检查该商户是否存在协议 无协议 或无生效协议的 即可添加协议
		List<ProtocolContent> contentInfos = protocolContentService.selectNewProtocolInfo(custInfo.getCustId());
		if(contentInfos == null || contentInfos.size() == 0){
			try {
				insertBean.setCustId(custInfo.getCustId());
				insertBean.setDisableDate("9999-01-01");//无限期
				protocolContentService.insert(insertBean);
				object.put("result", "success");
			} catch (Exception e) {
				logger.error("新增协议失败", e);
				object.put("result", "fail");
				object.put("message", e.getMessage());
			}
		}else{
			object.put("result", "fail");
			object.put("message", "商户存在可用或待审核协议,不能添加协议");
		}
		
		
		
		return object.toJSONString();
	}
	
	/***
	 * 修改协议（之前的协议保留 但作废）
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.EDIT)
	@ResponseBody
	public String edit(ProtocolContent editBean,String merchantCode) {
		JSONObject object = new JSONObject();
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setMerchantCode(merchantCode);
		custInfo = tdCustInfoService.selectByBean(custInfo);
		if(null == custInfo){
			object.put("result", "fail");
			object.put("message", "商户不存在，请核对商户编号是否正确");
			return object.toJSONString();
		}
		try {
			editBean.setCustId(custInfo.getCustId());
			protocolContentService.eidtProtocol(editBean);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("修改协议模板失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	/**
	 * 审核协议
	 * @param deleteBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.AUDIT)
	@ResponseBody
	public String audit(ProtocolContent auditBean) {
		JSONObject object = new JSONObject();

		try {
			if(null == auditBean){
				object.put("result", "fail");
				object.put("message", "审核对象不存在");
				return object.toJSONString();
			}
			protocolContentService.auditProl(auditBean);
			
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("审核失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/***
	 * 查询协议
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.QUERYTEMPLATE)
	@ResponseBody
	public String queryTemplate(String templateId) {
		JSONObject object = new JSONObject();
		try {
			ProtocolTemplate protocolTemplate = protocolTemplateService.selectTemplateById(templateId);
			List<PayProdBean> prodList = agentMapper.getPayProdList(null);
			if (null == protocolTemplate) {
				object.put("result", "fail");
			}
			String content = plugin.findDictByPath(Constant.FIX_CONTENT_PATH).trim();
			if(StringUtils.isEmpty(protocolTemplate.getProtocolTemplate().trim())){
				protocolTemplate.setProtocolTemplate(content);
			}else{
				protocolTemplate.setProtocolTemplate(content+","+protocolTemplate.getProtocolTemplate());
			}
			StringBuffer temp = new StringBuffer();
			temp.append(protocolTemplate.getProtocolTemplate());
			if(Constant.NORMAL_MERCHANT.equals(protocolTemplate.getMerchantType())){
				for (PayProdBean payProdBean : prodList) {
					temp.append(","+payProdBean.getProdCode()+"_rate:"+payProdBean.getProdName()+"_费率:"+payProdBean.getStandardRate());
				}
			}else{
				for (PayProdBean payProdBean : prodList) {
					temp.append(","+payProdBean.getProdCode()+"_rate:"+payProdBean.getProdName()+"_底价费率:"+payProdBean.getAgentBaseRate());
				}
			}
			
			object.put("message", temp.toString());

		} catch (Exception e) {
			logger.error("新增协议模板失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	

	/***
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(ProtocolContentPath.DELETE)
	@ResponseBody
	public String delete(ProtocolContent deleteBean) {
		JSONObject object = new JSONObject();

		try {
			protocolContentService.delete(deleteBean);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("删除协议模板失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	@RequestMapping(ProtocolContentPath.PROTOCOLCONTENTEXPORT)
	public void exportRechargeExcel(ProtocolContent queryBean,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交易信息");

		try {
			String[] headers = { "协议编号","商户编号", "商户名称", "协议模板","结算周期","是否跳过节假日","结算卡号","结算户名","结算卡开户行及支行信息","开户行地址","联行号","服务商","客户经理","H5支付_费率","APP支付_费率","扫码支付_费率","网关支付_费率","原生H5支付_费率","创建人","失效日期", "状态", "更新日期", "创建日期"};
			List<ProtocolContentExportBean> list = protocolContentService.getProtocolContentExport(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_协议信息.xls";
			Map<String, String> fileInfo = protocolContentService.exportExcel(list, headers, fileName, "协议信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel协议信息成功");
		} catch (Exception e) {
			logger.error("导出excel协议信息异常", e);
		}

	}
}
