package com.qifenqian.bms.basemanager.merchant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.feerule.service.FeeRuleService;
import com.qifenqian.bms.basemanager.merchant.bean.BmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

@Controller
@RequestMapping(MerchantPath.BASE)
public class MerchantController {

	private Logger logger = LoggerFactory.getLogger(MerchantController.class);

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private CityService cityService;
	@Autowired
	private FeeRuleService feeRuleService;
	@Autowired
	private AuditorService auditorService;
	@Autowired
	private UserService userService;
	@Autowired
	private TdLoginUserInfoMapper loginUserInfoMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TdCustInfoService tdCustInfoService;

	@Autowired
	private TradeBillService tradeBillService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private ActWorkflowMerchantAuditMapper actWorkflowMerchantAuditMapper;
	
	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;
	/**
	 * 商户注册页面
	 * 
	 * @param requestRole
	 * @return
	 */
	@RequestMapping(MerchantPath.REGISTPAGE)
	public ModelAndView accountInfoPage(HttpServletRequest request) {
		Bank bank = new Bank();
		Rule rule = new Rule();
		User user = new User();
		rule.setStatus("VALID");
		ModelAndView mv = new ModelAndView(MerchantPath.BASE + MerchantPath.REGISTPAGE);
		mv.addObject("taskId", request.getParameter("taskId"));
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("rulelist", ruleMapper.selectRules(rule));
		mv.addObject("userlist", userService.getUserList(user));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("provincelist_", cityService.selAllProvince());
		return mv;
	}

	/**
	 * 商户注册
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.REGIST)
	@ResponseBody
	public String regist(HttpServletRequest request, Merchant merchant) {

		logger.info("商户注册");
		JSONObject object = new JSONObject();
		try {
			// 邮箱
			String email = request.getParameter("email");
//			String feeCode = request.getParameter("feeCode");

			// 设置商户custId
			String merchantCode = BusinessUtils.getMerchantId(merchant.getBusinessLicense());
			String custId = GenSN.getSN();
			
			merchant.setMerchantCode(merchantCode);
			merchant.setCustId(custId);
			// 任务ID
			String taskId = request.getParameter("taskId");
			this.saveRegist(email, custId, merchant, null);
			// 流程到下一步
			String auditName = request.getParameter("auditName");
			merchantService.startProcess(custId, taskId, auditName);
			object.put("custId", custId);
			object.put("result", "SUCCESS");
			object.put("message", "注册成功");
		} catch (Exception e) {
			logger.error("注册失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}

	/**
	 * 校验邮箱是否已经存在
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATE)
	@ResponseBody
	public String validateEmail(String email, String custId) {

		logger.info("校验邮箱");

		JSONObject object = new JSONObject();

		try {
			TdLoginUserInfo loginInfo = merchantService.selectLoginUserInfoByEmail(email, custId,"ent");

			if (null == loginInfo) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("邮箱匹配出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();

	}
	
	/**
	 * 校验手机号是否已经存在
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATEMOBILE)
	@ResponseBody
	public String validateMobile(String mobile, String custId) {

		logger.info("校验手机号");

		JSONObject object = new JSONObject();

		try {
			TdLoginUserInfo loginInfo = merchantService.selectLoginUserInfoByMobile(mobile, custId,"ent");

			if (null == loginInfo) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("手机号匹配出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();

	}

	/**
	 * 校验商户名字是否存在
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATEMERCHANTNAME)
	@ResponseBody
	public String validateMerchantName(String name) {
		logger.info("校验商户名称");

		JSONObject object = new JSONObject();

		try {
			TdCustInfo tdCustInfo = tdCustInfoService.validateMerchantName(name);

			if (null == tdCustInfo) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验商户名称异常" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}

	/**
	 * 校验营业执照注册号是否已经存在
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATELICENSE)
	@ResponseBody
	public String validateLicense(String businessLicense,String custId) {

		logger.info("校验营业执照注册号是否已经存在");

		JSONObject object = new JSONObject();

		try {
			Merchant merchant = merchantService.validateLicense(businessLicense, custId);

			if (null == merchant) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验营业执照注册号出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();

	}

	/**
	 * 校验组织机构代码是否已经存在
	 * 
	 * @return
	 */
	@RequestMapping(MerchantPath.VALIDATEORGINSTCODE)
	@ResponseBody
	public String validateOrgInstCode(String orgInstCode, String custId) {

		logger.info("校验组织机构代码是否已经存在");

		JSONObject object = new JSONObject();

		try {
			Merchant merchant = merchantService.validateOrgInstCode(orgInstCode, custId);

			if (null == merchant) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验组织机构代码出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();

	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @param  
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(MerchantPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response,String custId) throws IOException {

		JSONObject object = new JSONObject();
		Map<String,String> result = merchantService.compressUpload(request,custId);
		object.put("result", result.get("result"));
		object.put("message", result.get("message"));
		if("SUCCESS".equals(result.get("result"))){//写入文件数据
			merchantService.insertCustScan(custId, result);
		}
		return object.toJSONString();
	}
	
	/**
	 * 事务处理
	 * 
	 * @param email
	 * @param custId
	 * @param merchant
	 */
	public void saveRegist(String email, String custId, Merchant merchant, String paths) {
		merchantService.saveRegist(email, custId, merchant, paths);
	}

	/**
	 * 显示前台商户待审核列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(MerchantPath.PROLIST)
	public ModelAndView list(MerchantVo merchantVo) {
		Bank bank = new Bank();
//		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantPath.BASE + MerchantPath.PROLIST);
		
		/**
		 * 校验权限
		 */
		merchantWorkFlowAuditService.verifyPermission(mv);
		
		List<MerchantVo> list = merchantService.selectMerchants(merchantVo);
		
		/**
		 * 装载工作流审核状态
		 */
		merchantWorkFlowAuditService.loadAuditStatus(list);
		
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
//		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("queryBean", merchantVo);
		
		return mv;
	}
	
	/**
	 * 显示后台商户待审核列表
	 * @param merchantVo
	 * @return
	 */
	@RequestMapping(MerchantPath.BACKLIST)
	public ModelAndView backList(MerchantVo merchantVo){
		Bank bank = new Bank();
		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantPath.BASE + MerchantPath.BACKLIST);
		List<MerchantVo> list = merchantService.selectBackMerchants(merchantVo);

		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("queryBean", merchantVo);
		return mv;
	}
	
	/**
	 * 显示商户列表
	 * @param merchantVo
	 * @return
	 */
	@RequestMapping(MerchantPath.LIST)
	public ModelAndView auditList(MerchantVo merchantVo){
		Bank bank = new Bank();
		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantPath.BASE + MerchantPath.LIST);
		List<MerchantVo> list = null;
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		if(isAllList){
			list = merchantService.selectAuditMerchants(merchantVo);
		}else{
			merchantVo.setUserId(userId);
			merchantVo.setUserName(WebUtils.getUserInfo().getUserName());
			list = merchantService.selectMyAuditMerchants(merchantVo);
		}
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("queryBean", merchantVo);
		return mv;
	}

	
	/**
	 * 显示商户列表（新）
	 * @param merchantVo
	 * @return
	 */
	@RequestMapping(MerchantPath.NEWLIST)
	public ModelAndView newList(MerchantVo merchantVo){
		Bank bank = new Bank();
		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantPath.BASE + MerchantPath.NEWLIST);
		List<MerchantVo> list = null;
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		if(isAllList){
			list = merchantService.selectNewMyAuditMerchants(merchantVo);
		}else{
			merchantVo.setUserId(userId);
			merchantVo.setUserName(WebUtils.getUserInfo().getUserName());
			list = merchantService.selectMyAuditMerchants(merchantVo);
		}
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("queryBean", merchantVo);
		return mv;
	}
	/**
	 * 导出商户列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantPath.EXPORTMERCHANTINFO)
	public void exportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出商户列表信息");

		try {
			List<MerchantExport> excels = merchantService.exportMerchantInfo(merchantVo);

			String[] headers = { "商户编号", "商户名称", "交易密码", "附加串", "错误密码次数",
			/*
			 * "证件类型，00身份证", "证件号",
			 */
			"客户类型：0 个人1 企业", "商户状态", "账号", "商户标志：0 商户，1 非商户", "客户积分", "客户等级", "实名认证等级", "实名认证审核状态", "客户信息完整度，分几级：0、1",
					"地址", "邮编", "营业执照编号（企业专用）", "税务登记证号（企业专用）", "法人证件类型（企业专用）", "法人证件号码（企业专用）", "法人名称（企业专用）",
					"注册资本（企业专用）", "企业类型", "所属行业", "年营业额", "商户网站地址", "客户状态", "是否证书认证", "是否短信认证", "七分钱账户ID", "创建人",
					"创建时间", "修改人", "修改时间", "营业期限", "营业执照注册所在地", "企业联系电话", "来往单位编号", "组织机构代码", "法人代表归属地", "法人手机",
					"代理人真实姓名", "代理人身份证类型", "代理人身份证号码", "代理人手机号码", "公司对公账号", "公司对公账号所属行", "支付密码冻结时间", "公司汇款校验金额",
					"支行信息", "银行开户名", "备注" };
			String fileName = DatetimeUtils.dateSecond() + "_商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费者列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel商户列表成功");
		} catch (Exception e) {
			logger.error("导出excel商户列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 导出前台商户审核列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantPath.PROEXPORTMERCHANTINFO)
	public void proExportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出前台商户审核列表信息");

		try {
			List<MerchantExport> excels = merchantService.proExportMerchantInfo(merchantVo);
			/*
			 * "证件类型，00身份证", "证件号",
			 */
			String[] headers = { "商户编号", "商户名称", "交易密码", "附加串", "错误密码次数",
			"客户类型：0 个人1 企业", "商户状态", "账号", "商户标志：0 商户，1 非商户", "客户积分", "客户等级", "实名认证等级", "实名认证审核状态", "客户信息完整度，分几级：0、1",
					"地址", "邮编", "营业执照编号（企业专用）", "税务登记证号（企业专用）", "法人证件类型（企业专用）", "法人证件号码（企业专用）", "法人名称（企业专用）",
					"注册资本（企业专用）", "企业类型", "所属行业", "年营业额", "商户网站地址", "客户状态", "是否证书认证", "是否短信认证", "七分钱账户ID", "创建人",
					"创建时间", "修改人", "修改时间", "营业期限", "营业执照注册所在地", "企业联系电话", "来往单位编号", "组织机构代码", "法人代表归属地", "法人手机",
					"代理人真实姓名", "代理人身份证类型", "代理人身份证号码", "代理人手机号码", "公司对公账号", "公司对公账号所属行", "支付密码冻结时间", "公司汇款校验金额",
					"支行信息", "银行开户名", "备注" };
			String fileName = DatetimeUtils.dateSecond() + "_商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "前台商户审核列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel前台商户审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel前台商户审核列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 导出后台商户审核列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantPath.BACKEXPORTMERCHANTINFO)
	public void backExportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出后台商户审核列表信息");

		try {
			List<MerchantExport> excels = merchantService.backExportMerchantInfo(merchantVo);

			String[] headers = { "商户编号", "商户名称", "交易密码", "附加串", "错误密码次数",
			/*
			 * "证件类型，00身份证", "证件号",
			 */
			"客户类型：0 个人1 企业", "商户状态", "账号", "商户标志：0 商户，1 非商户", "客户积分", "客户等级", "实名认证等级", "实名认证审核状态", "客户信息完整度，分几级：0、1",
					"地址", "邮编", "营业执照编号（企业专用）", "税务登记证号（企业专用）", "法人证件类型（企业专用）", "法人证件号码（企业专用）", "法人名称（企业专用）",
					"注册资本（企业专用）", "企业类型", "所属行业", "年营业额", "商户网站地址", "客户状态", "是否证书认证", "是否短信认证", "七分钱账户ID", "创建人",
					"创建时间", "修改人", "修改时间", "营业期限", "营业执照注册所在地", "企业联系电话", "来往单位编号", "组织机构代码", "法人代表归属地", "法人手机",
					"代理人真实姓名", "代理人身份证类型", "代理人身份证号码", "代理人手机号码", "公司对公账号", "公司对公账号所属行", "支付密码冻结时间", "公司汇款校验金额",
					"支行信息", "银行开户名", "备注" };
			String fileName = DatetimeUtils.dateSecond() + "_商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "前台商户审核列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel后台商户审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel后台商户审核列表异常", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(MerchantPath.UPDATEEMAIL)
	@ResponseBody
	public String updateEmail(MerchantVo merchantVo) {
		logger.info("修改商户信息");
		JSONObject jsonObject = new JSONObject();
		try {
			merchantService.updateEmail(merchantVo);
			jsonObject.put("result", "success");
			logger.info("商户修改完成！");
		} catch (Exception e) {
			logger.error("修改商户未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();

	}

	// 发送邮件
	@RequestMapping(MerchantPath.SENDEMAIL)
	@ResponseBody
	public String sendEmail(MerchantVo merchantVo) {

		logger.info("发送邮件信息");
		JSONObject jsonObject = new JSONObject();
		String pwd = GenSN.getRandomNum(6);
		String custName = merchantVo.getCustName();
		String email = merchantVo.getEmail();
		String custId = merchantVo.getCustId();
		String attachStr = GenSN.getRandomNum(5);
		String loginPwd_02 = MD5Security.getMD5String(custId + pwd + attachStr);
		String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
				+ "<div style=\"margin-bottom:10px;\">"
				+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
				+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
				+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custName + "：您好！</h3>"
				+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + email + "</b>"
				+ "<b style=\"font-size:18px;color:#ff9900\">密码为" + pwd + "</b>" + "，为了方便使用七分钱请点击"
				+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "尽快修改你的初始密码。" + "</p>"
				+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
				+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
				+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
				+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
				+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
				+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";

		IPlugin plugin = commonService.getIPlugin();
		MessageBean messageBean = new MessageBean();
		String[] tos = new String[] { email };
		messageBean.setSubject("七分钱--亲爱的" + custName + "你的七分钱商户账号已注册成功,请尽快重置您的密码！");
		messageBean.setContent(content);
		messageBean.setTos(tos);
		messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
		messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);

		boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
		if (falg) {
			try {
				TdLoginUserInfo userInfo = new TdLoginUserInfo();
				userInfo.setCustId(custId);
				userInfo.setLoginPwd(loginPwd_02);
				userInfo.setAttachStr(attachStr);
				loginUserInfoMapper.updatePwd(userInfo);
				jsonObject.put("result", "success");
				logger.info("发送邮件成功！");
			} catch (Exception e) {

				logger.error("发送邮件失败" + e.getMessage());
				jsonObject.put("result", "fail");
			}
		} else {
			logger.error("发送邮件失败");
			jsonObject.put("result", "fail");
		}

		return jsonObject.toJSONString();
	}

	// 根据id查找商户信息
	@RequestMapping(MerchantPath.FINDMERCHANTINFO)
	@ResponseBody
	public String findByIdMerchantInfo(String custId) {
		logger.info("查找商户信息");
		JSONObject jsonObject = new JSONObject();
		MerchantVo merchantVo = merchantService.findMerchantInfo(custId);
		List<BmsProtocolContent> contents = merchantService.selectContentByCustId(custId);

		if (null != contents && contents.size() > 0) {

			jsonObject.put("bmsProtocolContent", contents.get(0));
		}
		
		//查询商户门头照信息
		String path = auditorService.findScanPath(custId, "08",merchantVo.getAuthId());

		jsonObject.put("merchantVo", merchantVo);
		jsonObject.put("path", path);
		

		return jsonObject.toJSONString();
	}

	@RequestMapping(MerchantPath.UPDATEMERCHANTINFO)
	@ResponseBody
	public String updateMerchantInfo(MerchantVo merchantVo,HttpServletRequest request) {
		logger.info("修改商户信息");
		JSONObject object = new JSONObject();
		String businessType = request.getParameter("businessType");
		String doorPhoto = request.getParameter("doorPhoto");
		String doorFlag = request.getParameter("doorFlag");
		String certAttributeType1 = request.getParameter("certAttributeType1");
		String idCardType_1 = request.getParameter("idCardType_1");
		String idCardType_2 = request.getParameter("idCardType_2");
		
		Map<String,String> filePath = new HashMap<String, String>();
		filePath.put("businessType", businessType);
		filePath.put("doorPhoto", doorPhoto);
		filePath.put("doorFlag", doorFlag);
		filePath.put("certAttributeType1", certAttributeType1);
		filePath.put("idCardType_1", idCardType_1);
		filePath.put("idCardType_2", idCardType_2);
		try {
			merchantService.updateMerchantAndFeeRule(merchantVo,filePath);
			object.put("result", "SUCCESS");
			object.put("message", "修改商户信息成功");
		} catch (Exception e) {
			logger.error("修改商户信息未成功", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	@RequestMapping(MerchantPath.UPDATEFILEUPLOAD)
	@ResponseBody
	public String updateFileUpload(HttpServletRequest request, HttpServletResponse response)throws IOException {

		JSONObject object = new JSONObject();
		String custId = request.getParameter("custId");
		Map<String,String> result =merchantService.compressUpload(request,custId);
		object.putAll(result);
		return object.toJSONString();
	}

	// 根据id查找商户信息 businessRegAddr
	@RequestMapping(MerchantPath.PREMERCHANTINFO)
	@ResponseBody
	public String preMerchantInfo(String custId) {
		logger.info("查找商户信息");
		JSONObject jsonObject = new JSONObject();
		MerchantVo merchantVo = merchantService.findMerchantInfo(custId);
		if (merchantVo.getCompAcctBank() != null) {
			Bank bank = bankMapper.selectBankByBankCode(merchantVo.getCompAcctBank());
			merchantVo.setBankName(bank.getBankName());
		}
		String businessRegAddr = merchantVo.getBusinessRegAddr();
		Integer province = 0;
		Integer city1 = 0;

		if (businessRegAddr != null) {
			province = Integer.valueOf(businessRegAddr.split(",")[0]);
			city1 = Integer.valueOf(businessRegAddr.split(",")[1]);
			if (province <= 4) {
				businessRegAddr = cityService.findProvineNameById(province);
				merchantVo.setBusinessRegAddr(businessRegAddr);
			} else {
				City city = new City();
				city.setCityId(city1);
				city.setProvinceId(province);
				businessRegAddr = cityService.findProvineNameById(province) + cityService.findCityName(city);
				merchantVo.setBusinessRegAddr(businessRegAddr);
			}
		}

		jsonObject.put("merchantVo", merchantVo);
		return jsonObject.toJSONString();
	}
	
	/**
	 * 一级审核通过
	 */
	@RequestMapping(MerchantPath.FIRSTPASS)
	@ResponseBody
	public String firstPass(String custId,String number,String message,String isClear){
		logger.info("开始商户{}一级审核通过流程",custId);
		
		JSONObject ob = new JSONObject();
		try {
			/**
			 * 启动流程完成任务
			 */
			logger.info("启动流程完成任务");
			merchantWorkFlowAuditService.startProcessAndCompleteTask(custId,true,message,isClear);
			
//			/**
//			 * 保存来往单位编号
//			 */
//			logger.info("保存来往单位编号");
//			merchantWorkFlowAuditService.saveFcontactunitNumber(custId, number);
			
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}
	
	/**
	 * 一级审核不通过
	 * @param custId
	 * @return
	 */
	@RequestMapping(MerchantPath.FIRSTNOTPASS)
	@ResponseBody
	public String firstNotPass(String custId,String message){
		logger.info("开始商户{}一级审核不通过流程",custId);
		JSONObject ob = new JSONObject();
		try {
			
			TdCustInfo custInfo = tdCustInfoService.selectById(custId);
			merchantWorkFlowAuditService.firstNotPass(custId, message, false, "35", custInfo.getAuthId(), "2");//35  实名认证不通过        2：审核不通过
			ob.put("result", "SUCCESS");
			
			
			/**
			 * 发送邮件
			 */
			
			MerchantVo merchant = merchantMapper.findMerchantInfo(custId);
			
			String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
					+ "<div style=\"margin-bottom:10px;\">"
					+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
					+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
					+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
					+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + merchant.getEmail() + "</b>"
					+ "审核不通过，可以通过 "
					+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
					+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
					+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
					+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
			String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号没有审核通过，请重新提交！";
			logger.info("{}发送邮件(一级审核不通过)!",custId);
			boolean flag = merchantService.sendInfo(merchant.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
			if(flag){
				logger.info("{}发送邮件成功(一级审核不通过)!",custId);
			}
			
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		
		return ob.toJSONString();
	}

	
	
	/**
	 * 二级审核通过
	 */
	@RequestMapping(MerchantPath.SECONDPASS)
	@ResponseBody
	public String secondPass(String custId,String message,String number,String isClear){
		logger.info("开始商户{}二级审核通过流程",custId);
		
		JSONObject ob = new JSONObject();
		try {
			
			
			TdCustInfo custInfo = tdCustInfoService.selectById(custId);
			ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> response = merchantService.requestBindBank(custInfo);
			if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
				
			}else{
				ob.put("result", "FAILE");
				ob.put("message", "七分钱账户开户失败");
				return ob.toJSONString();
			}
			merchantWorkFlowAuditService.secondAudit(custId,number, true, custInfo.getAuthId(), message, "30", "0","3","notEmpty",isClear);
			
			ob.put("result", "SUCCESS");
			
			/**
			 * 发送邮件
			 */
			
			MerchantVo merchant = merchantMapper.findMerchantInfo(custId);
			
			String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
					+ "<div style=\"margin-bottom:10px;\">"
					+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
					+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
					+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
					+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + merchant.getEmail() + "</b>"
					+ "已经审核通过，可以通过 "
					+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
					+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
					+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
					+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
			String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号已经审核通过，欢迎登录！";
			logger.info("{}发送邮件(审核通过)!",custId);
			boolean flag = merchantService.sendInfo(merchant.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
			if(flag){
				logger.info("{}审核通过发送邮件成功(审核通过)!",custId);
			}
			
			
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}
	
	/**
	 * 二级审核不通过
	 */
	@RequestMapping(MerchantPath.SECONDNOTPASS)
	@ResponseBody
	public String secondNotPass(String custId,String message){
		logger.info("开始商户{}二级审核不通过流程",custId);
		
		JSONObject ob = new JSONObject();
		try {
			TdCustInfo custInfo = tdCustInfoService.selectById(custId);
			merchantWorkFlowAuditService.secondAudit(custId,null, false, custInfo.getAuthId(), message, "35", "2",null,"","");
			
			ob.put("result", "SUCCESS");
			
			/**
			 * 发送邮件
			 */
			
			MerchantVo merchant = merchantMapper.findMerchantInfo(custId);
			
			String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
					+ "<div style=\"margin-bottom:10px;\">"
					+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
					+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
					+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
					+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + merchant.getEmail() + "</b>"
					+ "审核不通过，可以通过 "
					+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
					+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
					+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
					+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
					+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
			String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号没有审核通过，请重新提交！";
			logger.info("{}发送邮件(二级审核不通过)!",custId);
			boolean flag = merchantService.sendInfo(merchant.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
			if(flag){
				logger.info("{}发送邮件成功(二级审核不通过)!",custId);
			}
			
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}
	
	
	/**
	 * 商户地址省市查询
	 */
	@RequestMapping(MerchantPath.GRTCITYLIST)
	@ResponseBody
	public String getCityList(HttpServletRequest request,
			HttpServletResponse response){
			logger.info("开始省市查询");
		
			JSONObject jo = new JSONObject();
		try {
			if (request.getMethod().equalsIgnoreCase("POST")) {
				String choiceType = request.getParameter("choiceType");

				if ("city".equals(choiceType)) {
					getCityInfo(request, jo);
				} else if ("area".equals(choiceType)) {
					getAreaInfo(request, jo);
				} 
			} 
		}catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("returnMsg", "网络繁忙，请稍候重试！");
		} 
		
		return jo.toJSONString();
	}
	
	/**
	 * 获取城市信息
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	private void getCityInfo(HttpServletRequest request, Map<String, Object> map) {
			String provinceId = request.getParameter("province");
			try {
				List<City> cityList = cityService.selCityByProvinceId(provinceId);
				map.put("cityList", cityList);
				map.put("result", "SUCCESS");
			} catch (Exception e) {
				logger.error("查询城市信息异常{}",e);
				throw new IllegalArgumentException("查询城市信息异常！");
			}
		
		}

	/**
	 * 获取区域信息
	 * @param request
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	private void getAreaInfo(HttpServletRequest request, Map<String, Object> map) {
		String cityId = request.getParameter("city");
		try {
			List<City> areaList = cityService.selAreaByCityId(cityId);
			map.put("areaList", areaList);
			map.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("查询区域信息异常{}",e);
			throw new IllegalArgumentException("查询区域信息异常！");
		}
		
	}
	
	
	/**
	 * 新版导出商户列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantPath.NEWEXPORTMERCHANTINFO)
	public void newExportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出商户列表信息");

		try {
			List<MerchantExport> excels = merchantService.newExportMerchantInfo(merchantVo);

			String[] headers = { "商户编号", "商户名称", "交易密码", "附加串", "错误密码次数",
			/*
			 * "证件类型，00身份证", "证件号",
			 */
			"客户类型：0 个人1 企业", "商户状态", "账号", "商户标志：0 商户，1 非商户", "客户积分", "客户等级", "实名认证等级", "实名认证审核状态", "客户信息完整度，分几级：0、1",
					"地址", "邮编", "营业执照编号（企业专用）", "税务登记证号（企业专用）", "法人证件类型（企业专用）", "法人证件号码（企业专用）", "法人名称（企业专用）",
					"注册资本（企业专用）", "企业类型", "所属行业", "年营业额", "商户网站地址", "客户状态", "是否证书认证", "是否短信认证", "七分钱账户ID", "创建人",
					"创建时间", "修改人", "修改时间", "营业期限", "营业执照注册所在地", "企业联系电话", "来往单位编号", "组织机构代码", "法人代表归属地", "法人手机",
					"代理人真实姓名", "代理人身份证类型", "代理人身份证号码", "代理人手机号码", "公司对公账号", "公司对公账号所属行", "支付密码冻结时间", "公司汇款校验金额",
					"支行信息", "银行开户名", "备注" };
			String fileName = DatetimeUtils.dateSecond() + "_商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费者列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel商户列表成功");
		} catch (Exception e) {
			logger.error("导出excel商户列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
}
