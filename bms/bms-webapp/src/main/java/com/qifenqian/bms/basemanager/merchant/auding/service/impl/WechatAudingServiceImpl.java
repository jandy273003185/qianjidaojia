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
import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantCashier;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbAreaInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbCity;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbProvince;
import com.qifenqian.bms.basemanager.merchant.auding.bean.WechatExport;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.auding.dao.WechatAudingDao;
import com.qifenqian.bms.basemanager.merchant.auding.dao.audingDao;
import com.qifenqian.bms.basemanager.merchant.auding.mapper.WechatAudingMapper;
import com.qifenqian.bms.basemanager.merchant.auding.service.WechatAudingService;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.merchant.bean.ActWorkflowMerchantAudit;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditHistoryMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdCertificateAuthMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolContent;
import com.qifenqian.bms.basemanager.protocolcontent.dao.ProtocolContentDao;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import cn.jpush.api.utils.StringUtils;

@Service
public class WechatAudingServiceImpl implements WechatAudingService{
	
	public static Logger logger = LoggerFactory.getLogger(WechatAudingServiceImpl.class);
	
	@Autowired
    private WechatAudingDao wechatAudingDao;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
    private audingDao audingDao;
	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;
	@Autowired
	private ActWorkflowMerchantAuditMapper actWorkflowMerchantAuditMapper;
	@Autowired
	private TdCertificateAuthMapper tdCertificateAuthMapper;
	@Autowired
	private audingServiceImpl audingServiceImpl;
	@Autowired
	private ActWorkflowMerchantAuditHistoryMapper actWorkflowMerchantAuditHistoryMapper;
	@Autowired
	private audingService audingService;
	@Autowired
	private WechatAudingMapper wechatAudingMapper;
	
	@Autowired
	private ProtocolContentDao protocolContentDao;

	@Override
	public List<AgencyAuding> getWeChatAudingList(MerchantVo merchantVo) {
		
		return wechatAudingDao.getAgencyAudingList(merchantVo);
	}

	@Override
	public MerchantVo getSingleWeChatAuding(MerchantVo merchantVo) {
		return wechatAudingDao.getSingleWechatAuding(merchantVo);
	}

	@Override
	public List<MerchantCashier> getCashierList(String custId) {
		
		return wechatAudingDao.findCashierList(custId);
	}

	@Override
	public List<bmsProtocolColumn> getbmsColumnList(String custId) {
		
		return wechatAudingDao.findColumnList(custId);
	}
	
	public List<bmsProtocolColumn> getbmsProtocolColumnList(String custId) {
		return audingDao.getbmsProtocolColumnList(custId);
	}
	            
	public void updatebmsprotocolContent(String custId ,String message,String status) {
		bmsProtocolContent bmsProtocolContent=new bmsProtocolContent();
		bmsProtocolContent.setCustId(custId);
		bmsProtocolContent.setStatus(status);
		bmsProtocolContent.setMemo(message);
		bmsProtocolContent.setUpdateDatetime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		bmsProtocolContent.setUpdateUser(WebUtils.getUserInfo().getUserName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    bmsProtocolContent.setValidDate(sdf.format(cal.getTime()));
	    cal.add(Calendar.YEAR, 1);
	    bmsProtocolContent.setDisableDate("9999-01-01");//无限期
	 	wechatAudingDao.updatebmsprotocolContent(bmsProtocolContent);
	}
	            
	public void updatebmsprotocolColumn(String id) {
		bmsProtocolColumn bmsprotocolColumn = new bmsProtocolColumn();
		bmsprotocolColumn.setProtocolId(id);
		bmsprotocolColumn.setStatus("DISABLE");//失效
		wechatAudingDao.updatebmsprotocolColumn(bmsprotocolColumn);
	}
	/**
	 * 
	 * 代理商审核不通过
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
		 * 录入商户审核状态实名认证状态: 0 审核通过  1 待审核  2 审核不通过\r\n9 用户取消认证',
		 */
		audingServiceImpl.updatetdCertificateAuth(custId, message, "2",authId);;//auditStatus=2
		
		/**
		 * 更新栏位表的状态
		 */
		List<bmsProtocolColumn> protocolColumnList = this.getbmsProtocolColumnList(custId);
		if(null!=protocolColumnList && protocolColumnList.size()>0){
			//更新栏位表状态
			this.updatebmsprotocolColumn(protocolColumnList.get(0).getProtocolId());
		 }
		//更新协议表
		this.updatebmsprotocolContent(custId, message, "AUDIT_NO");
		//更新登录状态表
		this.updateLoginInfo(custId,"01");//AUDIT_RESULT_NOPAS=00 不通过
		//更新客户信息表
		this.updateTdCustInfo(custId, "ontEmpty",certifiyStatus);
  	
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
	 * 修改商户表
	 * @param custId
	 * @param number
	 */
	public void updateTdCustInfo(String custId,String number,String certifiyStatus){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		  }
		if(StringUtils.isEmpty(number)){
		  	throw new IllegalArgumentException("来往单位编号为空！");
		  }
		
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState("04");//审核不通过
		custInfo.setTrustCertifyAuditState(certifiyStatus);
		tdCustInfoMapper.updateTdCustInfo(custInfo);
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
			
			ActWorkflowMerchantAudit auditBean = new ActWorkflowMerchantAudit();
			//查询注册时间
			TdLoginUserInfo login = tdLoginUserInfoMapper.selectLoginUserInfo(custId);
			auditBean.setProcInstId(processInstance.getId());
			auditBean.setMerchantid(custId);
			auditBean.setMessage(message);
			auditBean.setAuditer(String.valueOf(WebUtils.getUserInfo().getUserId()));
			auditBean.setAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
			auditBean.setSubmitTime(DatetimeUtils.dateFormat(login.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
			
			if(isPass){
				auditBean.setStatus("01");//01 一级审核通过
			 }else{
				auditBean.setStatus("03");//03审核不通过
			 }
			ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
			if(audit!= null){//有就更新  没有插入
				
				actWorkflowMerchantAuditHistoryMapper.insert(auditBean);
				actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
			}else{
				actWorkflowMerchantAuditHistoryMapper.insert(auditBean);
				actWorkflowMerchantAuditMapper.insert(auditBean);
			 }
		} catch (Exception e) {
			logger.error("完成任务异常",e);
			throw e;
		}
		
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
			custInfo.setTrustCertifyAuditState(status);//实名认证审核状态
			custInfo.setTrustCertifyLvl(trustCertifyLvl);//实名认证等级
			custInfo.setMerchantCode(empty);
			
			tdCustInfoMapper.updateInfo(custInfo);
		} catch (Exception e) {
			logger.error("录入商户认证状态失败",e);
			throw e;
		}
	}
	
	/**
	 * 录入商户审核状态status=2 审核不通过
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
	
	public void startPass(String custId, boolean isPass, String message,String number,HttpServletRequest request,Map<String,Object> parameterMap,String authId,String isClear) {
		
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
				auditBean.setStatus("02");//02 直接为二级审核通过
			}else{
				auditBean.setStatus("03");//03审核不通过
			}
			ActWorkflowMerchantAudit audit =  actWorkflowMerchantAuditMapper.selectListByMerchantId(custId);
			if(audit!= null){//有就更新  没有插入
				actWorkflowMerchantAuditHistoryMapper.insert(audit);
				actWorkflowMerchantAuditMapper.updateByPrimaryKey(auditBean);
			}else{
				actWorkflowMerchantAuditMapper.insert(auditBean);
			}
			
			//修改协议表为生效
			
			this.updatebmsprotocolContent(custId,message,"VALID");
			
			//修改登录信息
		
			this.updateLoginInfo(custId, Constant.AUDIT_RESULT_PASS);//00 审核通过
			
			this.updateCustNumber(custId,isClear);//修改商户表  00有效
			//实名认证状态: 0 审核通过  1 待审核  2 审核不通过\r\n9 用户取消认证',
			audingServiceImpl.updatetdCertificateAuth(custId, message, "0",authId);;//0审核通过
			
		  } catch (Exception e) {
			logger.error("完成任务异常",e);
			throw e;
		}
		
	   }

	public void updateColumn(HttpServletRequest request, String custId,
			Map<String, Object> parameterMap) {

		List<bmsProtocolColumn> protocolColumnList = wechatAudingMapper
				.getbmsProtocolColumnList(custId);
		String key = null;
		String[] onlyId = null;
		String[] refundAuth = null;
		String onlyIdStr = request.getParameter("onlyId");
		String refundAuthStr = request.getParameter("refundAuth");
		onlyId = onlyIdStr.split("@");
		refundAuth = refundAuthStr.split("@");
		for (int i = 0; i < onlyId.length; i++) {
			MerchantCashier merchantCashier = new MerchantCashier();
			merchantCashier.setOnlyId(onlyId[i]);
			merchantCashier.setRefundAuth(refundAuth[i]);
			merchantCashier.setModifyTime(DatetimeUtils.dateFormat(
					new Date(), "yyyy-MM-dd HH:mm:ss"));
			merchantCashier.setModifyId(String.valueOf(WebUtils
					.getUserInfo().getUserId()));
			
			  this.updateMerchantCashierInfo(merchantCashier);//收银员表
			 }

	//修改栏位表
	for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
		key = entry.getKey();
		
		 String  value=request.getParameter(entry.getKey());
		if (null != protocolColumnList && protocolColumnList.size() > 0) {
			//查询协议内容
			String protocolId = protocolColumnList.get(0).getProtocolId();//协议编号
			ProtocolContent content = new ProtocolContent();
			content.setId(protocolId);
			content= protocolContentDao.selectByBean(content);
			String protocolContent = content.getProtocolContent();
			String[] contents = protocolContent.split("@_@");
			for (int i = 0; i < protocolColumnList.size(); i++) {
				bmsProtocolColumn protocol = protocolColumnList.get(i);
				String id = protocol.getId();
			
				if (id.equals(key)) {
					bmsProtocolColumn protocolColumn = new bmsProtocolColumn();
					protocolColumn.setId(id);
					protocolColumn.setStatus("VALID");// 直接修改
					protocolColumn.setColumnValue(value);
					audingService.updatebmsProtocolColumn(protocolColumn);
					
					  //协议编号为
					  if("onecode.coll.pay_rate".equals(protocol.getColumnCode())){
						  
						  for(int j = 0; j<contents.length;j++){
							  if(contents[j].contains("onecode.coll.pay_rate")){
								  contents[j] =  contents[j].split(":")[0]+":"+contents[j].split(":")[1]+":"+value;
							  }
						  }
					  }
					  if("mobile.plugin.pay_rate".equals(protocol.getColumnCode())){
						  for(int j = 0; j<contents.length;j++){
							  if(contents[j].contains("mobile.plugin.pay_rate")){
								  contents[j] =  contents[j].split(":")[0]+":"+contents[j].split(":")[1]+":"+value;
							  }
						  }
					  }
					  if("h5.gateway.pay_rate".equals(protocol.getColumnCode())){
						  for(int j = 0; j<contents.length;j++){
							  if(contents[j].contains("h5.gateway.pay_rate")){
								  contents[j] =  contents[j].split(":")[0]+":"+contents[j].split(":")[1]+":"+value;
							  }
						  }
					  }
					  if("h5_t.gateway.pay_rate".equals(protocol.getColumnCode())){
						  for(int j = 0; j<contents.length;j++){
							  if(contents[j].contains("h5_t.gateway.pay_rate")){
								  contents[j] =  contents[j].split(":")[0]+":"+contents[j].split(":")[1]+":"+value;
							  }
						  }
					  }
					  if("pc.gateway.pay_rate".equals(protocol.getColumnCode())){
						  for(int j = 0; j<contents.length;j++){
							  if(contents[j].contains("pc.gateway.pay_rate")){
								  contents[j] =  contents[j].split(":")[0]+":"+contents[j].split(":")[1]+":"+value;
							  }
						  }
					  }
				} else {
					// 除了费率以外的栏位为生效
					 bmsProtocolColumn updateprotocolColumn = new bmsProtocolColumn();
					 updateprotocolColumn.setId(id);
					 updateprotocolColumn.setStatus("VALID");
					 audingService.updatebmsProtocolColumn(updateprotocolColumn);
					 
				}
			}
			
			 StringBuffer newContents = new StringBuffer();
			 for(String x : contents){
				  newContents.append(x).append("@_@");
			 }
			 //更改 协议内容
			 content.setProtocolContent(newContents.toString());
			 protocolContentDao.updateProt(content);
		  }
		}

	}

	/**
	 * 保存来往编号
	 * @param custId
	 * @param number
	 */
	public void updateCustNumber(String custId,String isClear){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空！");
		}
		TdCustInfo custInfo = new TdCustInfo();
		custInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custInfo.setCustId(custId);
		custInfo.setState("00");
		custInfo.setIsClear(isClear);
		custInfo.setTrustCertifyAuditState("30");
		tdCustInfoMapper.updateInfo(custInfo);
	}

	@Override
	public List<WechatExport> tinyMerchantExport(MerchantVo merchantVo) {
		
		return wechatAudingDao.tinyMerchantExport(merchantVo);
	}

	@Override
	public void updateMerchantCashierInfo(MerchantCashier merchantCashier) {
		
		wechatAudingDao.updateMerchantCashierInfo(merchantCashier);
	}

	@Override
	public String getCustScanCopy(String custId,String certifyType) {
		CustScan custScan=new CustScan();
		custScan.setCustId(custId);
		custScan.setCertifyType(certifyType);	
		return wechatAudingDao.getCustScanCopy(custScan);
	}

	@Override
	public List<bmsProtocolColumn> getCheckbmsProtocolColumnList(String custId) {
		return wechatAudingDao.getCheckbmsProtocolColumnList(custId);
	}

	@Override
	public List<CategoryCodeInfo> getCategoryCodeInfoByType(
			CategoryCodeInfo categoryCodeInfo) {
		return wechatAudingDao.getCategoryCodeInfoByType(categoryCodeInfo);
	}

	@Override
	public TbCity getCityInfoById(Integer cityId) {
		return wechatAudingDao.getCityInfoById(cityId);
	}

	@Override
	public TbAreaInfo getAreaInfoById(Integer areaId) {
		
		return wechatAudingDao.getAreaInfoById(areaId);
	}

	@Override
	public List<TbProvince> selectProvinceList() {
		return wechatAudingDao.selectProvinceList();
	}

}

