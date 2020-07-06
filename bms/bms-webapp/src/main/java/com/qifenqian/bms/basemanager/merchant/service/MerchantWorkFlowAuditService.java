package com.qifenqian.bms.basemanager.merchant.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.feerule.service.FeeRuleService;
import com.qifenqian.bms.basemanager.merchant.bean.ActWorkflowMerchantAudit;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditHistoryMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdCertificateAuthMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import cn.jpush.api.utils.StringUtils;

@Service
public class MerchantWorkFlowAuditService {
	
	public static Logger logger = LoggerFactory.getLogger(MerchantWorkFlowAuditService.class);
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private ActWorkflowMerchantAuditMapper actWorkflowMerchantAuditMapper;
	
	@Autowired
	private ActWorkflowMerchantAuditHistoryMapper actWorkflowMerchantAuditHistoryMapper;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;
	
	@Autowired
	private TdCertificateAuthMapper tdCertificateAuthMapper;
	
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;
	
	@Autowired
	private FeeRuleService feeRuleService;
	
	@Autowired
	private RuleMapper ruleMapper;
	/**
	 * 校验该操作员有无审核权限
	 */
	public void verifyPermission(ModelAndView mv){
		
		User user = WebUtils.getUserInfo();
		/**
		 * 一审
		 */
		String firstAudit = "false";
		List<org.activiti.engine.identity.User> userEntity = identityService.createUserQuery().memberOfGroup("firstGroupAudit").list();
		if(userEntity != null  && userEntity.size()>0){
			for(org.activiti.engine.identity.User use: userEntity){
				if(use.getId().equals(user.getUserCode())){
					firstAudit = "YES";
					break;
				}
			
			}
		}
		
		/**
		 * 二审
		 */
		String secondAudit = "false";
		userEntity = identityService.createUserQuery().memberOfGroup("secondGroupAudit").list();
		if(userEntity != null  && userEntity.size()>0){
			for(org.activiti.engine.identity.User use: userEntity){
				if(use.getId().equals(user.getUserCode())){
					secondAudit = "YES";
					break;
				}
			
			}
		}
		
		mv.addObject("firstAudit", firstAudit);
		mv.addObject("secondAudit", secondAudit);
	}
	
	/**
	 * 装载工作流审核状态
	 * @param list
	 */
	public void loadAuditStatus(List<MerchantVo> list){
		
		if(list!= null && list.size()>0){
			for(MerchantVo mer: list){
				ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(mer.getCustId());
				if(audit == null){
					mer.setWorkFlowStatus("04");//04 待审核
				}else {
					mer.setWorkFlowStatus(audit.getStatus());
				}
			}
		}
	}
	
	/**
	 * 启动工作流并完成任务
	 * @param custId
	 */
	public void startProcessAndCompleteTask(String custId,boolean isPass,String message,String isClear){
		
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		
		try {
			/**
			 * 启动流程
			 */
			identityService.setAuthenticatedUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("proMerchantAudit");
			
			/**
			 * 获取当前任务
			 */
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
			
			/**
			 * 签收任务
			 */
			taskService.claim(task.getId(), WebUtils.getUserInfo().getUserCode());
			
			Map<String, Object> auditVar = new LinkedHashMap<String, Object>();
			
			if(isPass){
				auditVar.put("firstPass", "true");
				
			}else{
				auditVar.put("firstPass", "false");
			}
			
			/**
			 * 完成任务
			 */
			taskService.complete(task.getId(),auditVar);
			
			/**
			 * 绑定工作流与业务
			 */
			
			ActWorkflowMerchantAudit auditBean = new ActWorkflowMerchantAudit();
			auditBean.setProcInstId(processInstance.getId());
			auditBean.setMerchantid(custId);
			auditBean.setMessage(message);
			auditBean.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
			if(isPass){
				auditBean.setStatus("01");//01 一级审核通过
				this.updateCustNumber(custId, null,null,isClear);
			}else{
				auditBean.setStatus("03");//03审核不通过
			}
			ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
			if(audit!= null){
				actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
			}else{
				actWorkflowMerchantAuditMapper.insert(auditBean);
			}
				
		} catch (Exception e) {
			logger.error("完成任务异常",e);
			throw e;
		}
		
	}

	/**
	 * 启动工作流并完成任务
	 * @param custId
	 */
	public void startProcessAndCompleteTaskEnter(String custId,String isPass,String message){

		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}

		try {
			/**
			 * 启动流程
			 */
			identityService.setAuthenticatedUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("proMerchantAudit");

			/**
			 * 获取当前任务
			 */
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

			/**
			 * 签收任务
			 */
			taskService.claim(task.getId(), WebUtils.getUserInfo().getUserCode());

			Map<String, Object> auditVar = new LinkedHashMap<String, Object>();

			if("1".equals(isPass)){
				auditVar.put("firstPass", "true");

			}else{
				auditVar.put("firstPass", "false");
			}

			/**
			 * 完成任务
			 */
			taskService.complete(task.getId(),auditVar);

			/**
			 * 绑定工作流与业务
			 */

			ActWorkflowMerchantAudit auditBean = new ActWorkflowMerchantAudit();
			auditBean.setProcInstId(processInstance.getId());
			auditBean.setMerchantid(custId);
			auditBean.setMessage(message);
			auditBean.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
			if("1".equals(isPass)){
				auditBean.setStatus("01");//01 一级审核通过
				this.updateCustNumberEnter(custId, "00");
			}else{
				auditBean.setStatus("03");//03审核不通过
				this.updateCustNumberEnter(custId, "04");
			}
			ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
			if(audit!= null){
				actWorkflowMerchantAuditHistoryMapper.insert(audit);
				actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
			}else{
				actWorkflowMerchantAuditMapper.insert(auditBean);
			}

		} catch (Exception e) {
			logger.error("完成任务异常",e);
			throw e;
		}

	}
	
	/**
	 * 二级审核
	 * @param custId
	 * @param isPass
	 */
	public void secondTask(String custId,boolean isPass,String message){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		try {
			ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
			String procInstId = audit.getProcInstId(); 
			Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
			/**
			 * 签收任务
			 */
			taskService.claim(task.getId(), WebUtils.getUserInfo().getUserCode());
			Map<String, Object> auditVar = new LinkedHashMap<String, Object>();
			if(isPass){
				auditVar.put("secondPass", "true");
				audit.setStatus("02");
			}else{
				auditVar.put("secondPass", "false");
				audit.setStatus("03");
			}
			
			taskService.complete(task.getId(),auditVar);
			/**
			 * 写入状态
			 */
			audit.setMessage(message);
			audit.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
			actWorkflowMerchantAuditMapper.updateByPrimaryKey(audit);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 保存来往编号
	 * @param custId
	 * @param number
	 */
	public void updateCustNumber(String custId,String number,String status,String isClear){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setFcontactunitNumber(number);
		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState(status);
		if(null!=isClear && !isClear.equals("")){
			custInfo.setIsClear(isClear);
		}
		tdCustInfoMapper.updateInfo(custInfo);
	}

	/**
	 * 保存来往编号
	 * @param custId
	 */
	public void updateCustNumberEnter(String custId,String status){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		TdCustInfo custInfo = new TdCustInfo();

		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState(status);
		//if(null!=isClear && !isClear.equals("")){
		//	custInfo.setIsClear(isClear);
		//}
		tdCustInfoMapper.updateInfo(custInfo);
	}

	/**
	 * 保存 费率
	 * @param number
	 */
	public void saveFcontactunitNumber(String custId,String number){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		
		try {
//			
//			FeeRule feeRule = new FeeRule();
//			feeRule.setCustId(custId);
//			feeRule.setFeeCode(feeCode);
//			FeeRule rule = feeRuleService.selectFeeRule(feeRule);
//			if(rule != null){
//				feeRuleService.updateFeeRule(feeRule);
//			}else{
//				
//				Rule ruleZ=new Rule();
//				User user = WebUtils.getUserInfo();
//				ruleZ.setMappingId(GenSN.getSN().substring(0,30));
//				ruleZ.setInstUser(String.valueOf(user.getUserId()));
//				ruleZ.setOperType(Constant.FEE_RATE_WITHDRAW);// 提现
//				ruleZ.setStatus(Constant.FEE_RATE_STATUS_VALID); // 有效
//				ruleZ.setFeeCode(feeCode);
//				ruleZ.setCustId(custId);
//				ruleMapper.saveFee(ruleZ);
//			}
		} catch (Exception e) {
			logger.error("保存费率失败",e);
			throw e;
		}
		
	}
	
	/**
	 * 录入商户认证状态
	 * @param custId
	 * @param status
	 */
	public void updateCertifiyStatus(String custId,String status,String trustCertifyLvl,String empty){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		try {
			TdCustInfo custInfo = new TdCustInfo();
			custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			custInfo.setCustId(custId);
			custInfo.setTrustCertifyAuditState(status);
			custInfo.setTrustCertifyLvl(trustCertifyLvl);
			custInfo.setMerchantCode(empty);
			custInfo.setState("04");
			tdCustInfoMapper.updateInfo(custInfo);
		} catch (Exception e) {
			logger.error("录入商户认证状态失败",e);
			throw e;
		}
	}
	
	/**
	 * 录入商户审核状态
	 */
	public void updateAuditStatus(String authId,String message,String status){
		
		if(StringUtils.isEmpty(authId)){
			throw new IllegalArgumentException("商户证件审核ID为空");
		}
		TdCertificateAuth tdCertificateAuth = new TdCertificateAuth();
		tdCertificateAuth.setAuthId(Integer.valueOf(authId));
		tdCertificateAuth.setAuthResultDesc(message);
		tdCertificateAuth.setCertifyUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		tdCertificateAuth.setCertificateState(status);
		
		try {
			tdCertificateAuthMapper.updateByPrimaryKeySelective(tdCertificateAuth);
		} catch (Exception e) {
			logger.error("录入商户审核状态失败",e);
			throw e;
		}
		
	}
	
	/**
	 * 修改用户登录状态
	 * @param custId
	 */
	public void updateLoginInfo(String custId,String state){
		TdLoginUserInfo info = new TdLoginUserInfo();
		info.setCustId(custId);
		info.setState(state);
		tdLoginUserInfoMapper.updateLoginUserInfo(info);
	}
	/**
	 * 一级审核不通过
	 * @param custId
	 * @param message
	 * @param isPass
	 * @param certifiyStatus
	 * @param authId
	 * @param auditStatus
	 */
	
	public void firstNotPass(String custId,
							 String message,
							 boolean isPass,
							 String certifiyStatus,
							 String authId,
							 String auditStatus){
		/**
		 * 启动工作流并完成任务
		 * @param custId
		 */
		
		this.startProcessAndCompleteTask(custId, isPass, message,null);
		/**
		 * 录入商户认证状态
		 * @param custId
		 * @param status
		 */
		this.updateCertifiyStatus(custId, certifiyStatus,null,"");//空
		
		/**
		 * 录入商户审核状态
		 */
		this.updateAuditStatus(authId, message, "2");
	}
	
	/**
	 * 二级审核
	 * @param custId
	 * @param isPass
	 * @param authId
	 * @param message
	 * @param certifiyStatus
	 * @param auditStatus
	 */
	
	public void secondAudit(String custId,String number,boolean isPass,String authId,String message,String certifiyStatus,String auditStatus,String trustCertifyLvl,String empty, String isClear){
		/**
		 * 完成流程任务
		 */
		this.secondTask(custId, isPass,message);
		/**
		 * 录入商户认证状态
		 * @param custId
		 * @param status
		 */
		this.updateCertifiyStatus(custId, certifiyStatus,trustCertifyLvl,empty);
		/**
		 * 录入商户审核状态
		 */
		this.updateAuditStatus(authId, message, auditStatus);
		
		if(isPass){
			this.updateLoginInfo(custId, Constant.LOGIN_STATE_AGREEMENTING);
			this.updateCustNumber(custId, number,"00",isClear);
		}else{
			this.updateCustNumber(custId, number,"04",isClear);
		}
		
	}

	/**
	 * 二级审核
	 * @param custId
	 * @param isPass
	 * @param authId
	 * @param message
	 * @param certifiyStatus
	 * @param auditStatus
	 */
	public void secondAuditEnter(String custId,
								 boolean isPass,
								 String authId,
								 String message,
								 String certifiyStatus,
								 String auditStatus,
								 String trustCertifyLvl,
								 String empty){
		/**
		 * 完成流程任务
		 */
		this.secondTask(custId, isPass,message);
		/**
		 * 录入商户认证状态
		 * @param custId
		 * @param status
		 */
		this.updateCertifiyStatus(custId, certifiyStatus,trustCertifyLvl,empty);
		/**
		 * 录入商户审核状态
		 */
		this.updateAuditStatus(authId, message, auditStatus);

		if(isPass){
			this.updateLoginInfo(custId, Constant.LOGIN_STATE_AGREEMENTING);
			this.updateCustNumberEnter(custId, "00");
		}else{
			this.updateCustNumberEnter(custId, "04");
		}

	}

}
