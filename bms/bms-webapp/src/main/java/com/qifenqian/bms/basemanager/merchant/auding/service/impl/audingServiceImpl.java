package com.qifenqian.bms.basemanager.merchant.auding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.feerule.service.FeeRuleService;
import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TdCustScanCopy;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.auding.dao.audingDao;
import com.qifenqian.bms.basemanager.merchant.auding.mapper.audingMapper;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.merchant.bean.ActWorkflowMerchantAudit;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditHistoryMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdCertificateAuthMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.protocolcontent.dao.ProtocolContentDao;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import cn.jpush.api.utils.StringUtils;
@Service
public class audingServiceImpl implements audingService{
	@Autowired
    private audingDao audingDao;
	public static Logger logger = LoggerFactory.getLogger(audingServiceImpl.class);
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
	
	@Autowired
	private ProtocolContentDao protocolContentDao;
	
	@Autowired
	private audingMapper audingMapper;
	
	@Override
	public List<AgencyAuding> getAgencyAudingList(MerchantVo merchantVo) {
		
		return audingDao.getAgencyAudingList(merchantVo);
	}
	@Override
	public MerchantVo getSingleAgencyAuding(MerchantVo merchantVo) {
		
		return audingDao.getSingleAgencyAuding(merchantVo);
	}
	/**
	 * custId,null, false, custInfo.getAuthId(), message, "35", "2",null,""
	 * custId,null, false, custInfo.getAuthId(), message, "35", "2",null,""
	 */
	@Override
	public void secondAudit(String custId, String number, boolean isPass,
			String authId, String message, String certifiyStatus,
			String auditStatus, String trustCertifyLvl, String empty) {
		/**
		 * 开启工作流
		 */
		this.startProcessAndCompleteTask(custId, isPass, message);
		
		
		
		/**
		 * 录入商户审核状态修改td_certificate_auth
		 */
		this.updatetdCertificateAuth(custId, message, "2",authId);//auditStatus=2
		
		/**
		 * 更新栏位表
		 * 
		 * 
		 */
		List<bmsProtocolColumn> updatebmsprotocolContent = this.getbmsProtocolColumnList(custId);
		
			this.updatebmsprotocolContent(custId, "AUDIT_NO");//失效
		 
		/**
		 * 更新协议表
		 */
		List<bmsProtocolColumn> updateprotocolColumnList = this.getbmsProtocolColumnList(custId);
		if(null!=updatebmsprotocolContent && updatebmsprotocolContent.size()>0){
			for (int i = 0; i < updateprotocolColumnList.size(); i++) {
				  bmsProtocolColumn protocolColumn = updateprotocolColumnList.get(i);
				  String id = protocolColumn.getId();
				  protocolColumn.setId(id);
				  protocolColumn.setStatus("AUDIT_NO");//失效
				  this.updatebmsProtocolColumn(protocolColumn);
			 }
	 
		    }
	     //登录的
		this.updateLoginInfo(custId,"01");//AUDIT_RESULT_NOPAS=01不通过
		/**
		 * /**
		 * 录入商户认证状态（代理商的）
		 * @param custId
		 * @param status
		 */
		 
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setFcontactunitNumber(number);
		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState("04");//04审核不通过
		custInfo.setTrustCertifyAuditState(certifiyStatus);
		tdCustInfoMapper.updateTdCustInfo(custInfo);
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
public void startProcessAndCompleteTask(String custId,boolean isPass,String message){
	
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
		//查询注册时间
		TdLoginUserInfo login = tdLoginUserInfoMapper.selectLoginUserInfo(custId);
		ActWorkflowMerchantAudit auditBean = new ActWorkflowMerchantAudit();
		auditBean.setProcInstId(processInstance.getId());
		auditBean.setMerchantid(custId);
		auditBean.setMessage(message);
		auditBean.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
		auditBean.setAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		auditBean.setStatus("03");//03审核不通过
		auditBean.setSubmitTime(DatetimeUtils.dateFormat(login.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
		ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);//工作流审核表
	    if(audit!= null){//有就更新  没有插入
			actWorkflowMerchantAuditHistoryMapper.insert(auditBean);//工作流商户审核表历史表
			actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
			
		}else{
			actWorkflowMerchantAuditHistoryMapper.insert(auditBean);
			actWorkflowMerchantAuditMapper.insert(auditBean);//工作流商户审核表
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
 * 修改代理商表
 * @param custId
 * @param number
 */
public void updateCustNumber(String custId,String number){
	if(StringUtils.isEmpty(custId)){
		throw new IllegalArgumentException("商户ID为空！");
	}
	if(StringUtils.isEmpty(number)){
		throw new IllegalArgumentException("来往单位编号为空！");
	 }
	TdCustInfo custInfo = new TdCustInfo();
	custInfo.setFcontactunitNumber(number);
	custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
	custInfo.setCustId(custId);
	custInfo.setState("00");//正常审核通过
	custInfo.setTrustCertifyAuditState("30");
	tdCustInfoMapper.updateTdCustInfo(custInfo);
}
/**
 * 修改代理商表
 * @param custId
 * @param number
 */
public void updateTdCustInfo(String custId,String number){
	if(StringUtils.isEmpty(custId)){
		throw new IllegalArgumentException("商户ID为空！");
	  }
	if(StringUtils.isEmpty(number)){
	  	throw new IllegalArgumentException("来往单位编号为空！");
	}
	TdCustInfo custInfo = new TdCustInfo();
	custInfo.setFcontactunitNumber(number);
	custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
	custInfo.setCustId(custId);
	custInfo.setState("04");//审核不通过
	tdCustInfoMapper.updateInfo(custInfo);
}

/**
 * 录入商户认证状态
 * @param custId
 * @param status
 * custId, certifiyStatus,null,""
 */
public void updateCertifiyStatus(String custId,String status,String trustCertifyLvl,String empty){
	if(StringUtils.isEmpty(custId)){
		throw new IllegalArgumentException("商户ID为空！");
	}
	try {
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState("04");//04 审核不通过
		custInfo.setTrustCertifyAuditState(status);
		custInfo.setTrustCertifyLvl(trustCertifyLvl);
		custInfo.setMerchantCode(empty);
		
		tdCustInfoMapper.updateInfo(custInfo);
	} catch (Exception e) {
		logger.error("录入商户认证状态失败",e);
		throw e;
	}
}

public void updatetdCertificateAuth(String custId,String message,String status,String authId){
	if(StringUtils.isEmpty(custId)){
		throw new IllegalArgumentException("商户custid空");
	 }
	try {
	
	TdCertificateAuth tdCertificateAuth = new TdCertificateAuth();
	tdCertificateAuth.setCustId(custId);
	tdCertificateAuth.setAuthResultDesc(message);
	tdCertificateAuth.setCertifyUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
	tdCertificateAuth.setCertificateState(status);//2为审核不通过
	tdCertificateAuth.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
	tdCertificateAuth.setModifyTime(new Date());
	if(null!=authId && !"".equals(authId)){
		tdCertificateAuth.setAuthId(new Integer(authId));
	}
	this.updatetdCertificateAuth(tdCertificateAuth);
	} catch (Exception e) {
		logger.error("录入代理商户审核状态失败",e);
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
 * 更新协议表
 * @param bmsProtocolContent2 
 */
@Override
public void updatebmsprotocolContent(bmsProtocolContent bmsProtocolContent) {
	
	audingDao.updatebmsprotocolContent(bmsProtocolContent);
}

public void startPass(String custId, boolean isPass, String message,String number,String authId) {
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
		TdLoginUserInfo login = tdLoginUserInfoMapper.selectLoginUserInfo(custId);
		SimpleDateFormat  df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ActWorkflowMerchantAudit auditBean = new ActWorkflowMerchantAudit();
		auditBean.setProcInstId(processInstance.getId());
		auditBean.setMerchantid(custId);
		auditBean.setMessage(message);
		auditBean.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
		auditBean.setSubmitTime(df.format(login.getCreateTime()));
		auditBean.setAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		 if(isPass){
			auditBean.setStatus("02");//02 直接为二级审核通过
		 }else{
			auditBean.setStatus("03");//03审核不通过
		 }
		ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
		if(audit!= null){//有就更新  没有插入
			actWorkflowMerchantAuditHistoryMapper.insert(audit);
			actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
		}else{
			actWorkflowMerchantAuditHistoryMapper.insert(auditBean);
			actWorkflowMerchantAuditMapper.insert(auditBean);
		}
		
		
		//修改登录信息
	
		this.updateLoginInfo(custId, Constant.AUDIT_RESULT_PASS);//00 审核通过
		
		this.updateCustNumber(custId, number);//修改商户表  00有效
		//修改td_certificate_auth
		this.updatetdCertificateAuth(custId, "审核通过", "0",authId);//auditStatus=2
		
		//修改协议
		this.updatebmsprotocolContent(custId, "VALID");
		
		 
	  } catch (Exception e) {
		logger.error("完成任务异常",e);
		throw e;
	}
	
   }
@Override
public List<MerchantExport> agentExportMerchantInfo(MerchantVo merchantVo) {
	  
     return audingDao.agentExportMerchantInfo(merchantVo);
	
}
public void updatebmsprotocolContent(String custId ,String status){
	
	 bmsProtocolContent bmsProtocolContent=new bmsProtocolContent();
     bmsProtocolContent.setCustId(custId);
	 bmsProtocolContent.setStatus(status);
	 bmsProtocolContent.setUpdateDatetime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
	 bmsProtocolContent.setUpdateUser(WebUtils.getUserInfo().getUserName());
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(new Date());
	 bmsProtocolContent.setValidDate(sdf.format(cal.getTime()));
	 cal.add(Calendar.YEAR, 1);
	 bmsProtocolContent.setDisableDate("9999-01-01");
	 this.updatebmsprotocolContent(bmsProtocolContent);
	
}
@Override
public List<bmsProtocolColumn> getbmsProtocolColumnList(String custId) {
	
	return audingDao.getbmsProtocolColumnList(custId);
}
@Override
public void updatebmsProtocolColumn(bmsProtocolColumn protocolColumn) {
	
	audingDao.updatebmsProtocolColumn(protocolColumn);
	//添加数据

}
@Override
public void addbmsProtocolColumn(bmsProtocolColumn protocolColumn) {

	audingDao.addbmsProtocolColumn(protocolColumn);
}

public  void updateColumn(HttpServletRequest request,String custId,Map<String,Object> parameterMap){
List<bmsProtocolColumn> protocolColumnList = this.getbmsProtocolColumnList(custId);
	
	String key=null;
	
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) { 
			       key=entry.getKey();
			
		  if(key.equals("custId")||key.equals("number")) continue;
		  String  value=request.getParameter(entry.getKey());
		  //key即为参数名,value即为值,从这里该是,只要是custId和number 以外的参数,都根据条件去修改就好了
		  
		for (int i = 0; i < protocolColumnList.size(); i++) {
			  bmsProtocolColumn updateprotocolColumn = protocolColumnList.get(i);
			  String id = updateprotocolColumn.getId();
			  if(id.equals(key)){
				  bmsProtocolColumn protocolColumn=new bmsProtocolColumn();
				  protocolColumn.setId(id);
				  protocolColumn.setStatus("VALID");//直接修改
				  protocolColumn.setColumnValue(value);
				  this.updatebmsProtocolColumn(protocolColumn);
				
			     }else{
			    	 //除了费率以外的栏位为生效
			    	  bmsProtocolColumn updateprotocolColumn2=new bmsProtocolColumn();
			    	  updateprotocolColumn2.setId(id);
			    	  updateprotocolColumn2.setStatus("VALID");
					  this.updatebmsProtocolColumn(updateprotocolColumn2); 
			      }
	 
		      }
          }
		  
	       
	
       }
@Override
public List<TdCustScanCopy> getAgencyAudingPictures(String custId) {
	
	return audingDao.getAgencyAudingPictures(custId);
			}
@Override
public List<bmsProtocolContent> getbmsProtocolContentList(String custId) {
	
	return audingDao.getbmsProtocolContentList(custId);
}
@Override
public void updatetdCertificateAuth(TdCertificateAuth tdCertificateAuth) {
	audingDao.updatetdCertificateAuth(tdCertificateAuth);
	
}

@Override
public String findScanPath(String custId, String certifyType) {
	CustScan custScan=new CustScan();
	custScan.setCustId(custId);
	custScan.setCertifyType(certifyType);	
	
	return audingMapper.findPath(custScan);
	
  }
@Override
public List<TdCertificateAuth> getAgencyCheckHistory(String custId) {
	
	return audingMapper.getAgencyCheckHistory(custId);
  }
@Override
public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(String custId) {
	
	return audingMapper.getCheckbmsProtocolColumnList(custId);
  }

@Override
public bmsProtocolContent getbmsPbmsProtocolContent(String custId) {
	return audingMapper.getbmsPbmsProtocolContent(custId);
	
}
     
}
