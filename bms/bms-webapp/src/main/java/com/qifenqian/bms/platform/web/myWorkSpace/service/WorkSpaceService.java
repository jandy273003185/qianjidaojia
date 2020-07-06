package com.qifenqian.bms.platform.web.myWorkSpace.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.myworkspace.WorkFlowHelper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.platform.web.myWorkSpace.bean.WaitTaskBean;
import com.qifenqian.bms.platform.web.myWorkSpace.dao.WorkSpaceDAO;
import com.qifenqian.bms.platform.web.myWorkSpace.mapper.MyWorkSpaceMapper;

@Service
public class WorkSpaceService {
	
	private Logger logger = LoggerFactory.getLogger(WorkSpaceService.class);
	@Autowired
	private WorkSpaceDAO workSpaceDao ;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private WorkFlowHelper workFlowHelper;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private CustScanMapper custScanMapper;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private RuleMapper ruleMapper;
	
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;
	
	@Autowired
	private MyWorkSpaceMapper myWorkSpaceMapper;
	
	@Value("${CF_FILE_SAVE_PATH}")
	private String CF_FILE_SAVE_PATH;
	
	
	/**
	 * 查询我的代办任务
	 */
	public List<WaitTaskBean> getTasks(WaitTaskBean bean){

		return workSpaceDao.getMyTasks(bean);
	}
	
	/**
	 * 查询当前任务的URL
	 * @return
	 */
	public String getUrl(String taskId){
		return myWorkSpaceMapper.getUrl(taskId);
	}
	
	
	/**
	 * 根据任务ID查询业务信息 和 业务图片路径
	 * @param taskId
	 * @return
	 */
	public Map<String,Object>  findPathAndInfo(String taskId){
		Task task = workFlowHelper.getTask(taskId);
		ProcessInstance pi=	workFlowHelper.getProcessInstance(task.getProcessInstanceId());
		String bussessKey = pi.getBusinessKey();
		String id= "";
		if(bussessKey!=null && bussessKey.length()>0){
			String key[]= bussessKey.split("\\.");
			id = key[1];
		}
		
		MerchantVo info = merchantService.findMerchantInfo(id);
		
		List<String> paths = auditorService.findByidScanPath(id);
		List<String> certiPath = new ArrayList<String>();
		for(String path: paths){
			String[] scanpath = path.split(",");
			if(scanpath.length>1){
				certiPath.add(scanpath[0]);
				certiPath.add(scanpath[1]);
			}
			else{
				certiPath.add(path);
			}
		}
		
		Map<String ,Object> valus= new HashMap<String, Object>();
		valus.put("MerchantVo", info);
		valus.put("path", certiPath);
		return valus;
	}
	
	/**
	 * 修改证件信息
	 * @param
	 * @param custId
	 */
	public void updateCustScanInfo(String custId ,MerchantVo merchant,Map<String,String> fileNames){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空");
		}
		logger.info("修改商户证件信息[{}]",JSONObject.toJSONString(merchant));
		String businessType = fileNames.get("businessType");
		String doorPhoto = fileNames.get("doorPhoto");
		String doorFlag = fileNames.get("doorFlag");
		String certAttributeType1 = fileNames.get("certAttributeType1");
		String idCardType_1 = fileNames.get("idCardType_1");
		String idCardType_2 = fileNames.get("idCardType_2");
		String bankCard = fileNames.get("bankCard");
		String cf_path = CF_FILE_SAVE_PATH; //p.getProperty("CF_FILE_SAVE_PATH");
		try {
			/**
			 * 更新营业执照
			 */
			if(!StringUtils.isEmpty(businessType)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_BUSINESS,cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS+File.separator+custId+File.separator+businessType,merchant.getCustName(),merchant.getBusinessLicense());
			}
			
			/**
			 * 更新门头照
			 */
			if("true".equals(doorFlag)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_MERCHANT_DOORID,doorPhoto,merchant.getCustName(),null);
			}

			/**
			 * 更新开户证件
			 */
			if(!StringUtils.isEmpty(certAttributeType1)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_OPEN,cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN+File.separator+custId+File.separator+certAttributeType1,merchant.getCustName(),merchant.getCompMainAcct());
			}
			if(!StringUtils.isEmpty(bankCard)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_BANK_CARD,cf_path + File.separator + Constant.CERTIFY_TYPE_BANK_CARD+File.separator+custId+File.separator+bankCard,merchant.getCustName(),merchant.getCompMainAcct());
			}
			CustScan custScan = new CustScan();
			custScan.setCustId(custId);
			custScan.setCertifyType( Constant.CERTIFY_TYPE_MERCHANT_IDCARD);
			String idcardPath = custScanMapper.findPathByIdAndType(custScan);
			if((null == idcardPath && ("").equals(idcardPath))){
				String path[] = idcardPath.split(";");
				String idCard_1= path[0];
				if(!StringUtils.isEmpty(idCardType_1)){
					idCard_1=cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD+File.separator+custId+File.separator+idCardType_1;
				}
				String idCard_2 =path[1];
				if(!StringUtils.isEmpty(idCardType_2)){
					idCard_2=cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD+File.separator+custId+File.separator+idCardType_2;
				}
				idcardPath = idCard_1+";"+idCard_2;
			}

			/**
			 * 更新身份证件
			 */
			this.updateScanPath(custId,Constant.CERTIFY_TYPE_MERCHANT_IDCARD,idcardPath,merchant.getCustName(),merchant.getRepresentativeCertNo());	
			
		} catch (Exception e) {
			logger.error("更新证件信息异常",e);
			throw e;
		}
		
	}

	/**
	 * 修改证件信息
	 * @param
	 * @param custId
	 */
	public void updateEnterCustScanInfo(String custId ,MerchantVo merchant,Map<String,String> fileNames){
		if(StringUtils.isEmpty(custId)){
			throw new IllegalArgumentException("商户ID为空");
		}
		logger.info("修改商户证件信息[{}]",JSONObject.toJSONString(merchant));
		String businessType = fileNames.get("businessType");
		String doorPhoto = fileNames.get("doorPhoto");
		String doorFlag = fileNames.get("doorFlag");
		String certAttributeType1 = fileNames.get("certAttributeType1");
		String idCardType_1 = fileNames.get("idCardType_1");
		String idCardType_2 = fileNames.get("idCardType_2");

		//Properties p = PropertiesUtil.getProperties();
		String cf_path = CF_FILE_SAVE_PATH; //p.getProperty("CF_FILE_SAVE_PATH");
		try {
			/**
			 * 更新营业执照
			 */
			if(!StringUtils.isEmpty(businessType)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_BUSINESS,cf_path + File.separator + Constant.CERTIFY_TYPE_BUSINESS+File.separator+custId+File.separator+businessType,merchant.getCustName(),merchant.getBusinessLicense());
			}

			/**
			 * 更新门头照
			 */
			if("true".equals(doorFlag)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_MERCHANT_DOORID,doorPhoto,merchant.getCustName(),null);
			}

			/**
			 * 更新开户证件
			 */
			if(!StringUtils.isEmpty(certAttributeType1)){
				this.updateScanPath(custId,Constant.CERTIFY_TYPE_OPEN,cf_path + File.separator + Constant.CERTIFY_TYPE_OPEN+File.separator+custId+File.separator+certAttributeType1,merchant.getCustName(),merchant.getCompMainAcct());
			}
			CustScan custScan = new CustScan();
			custScan.setCustId(custId);
			custScan.setCertifyType( Constant.CERTIFY_TYPE_MERCHANT_IDCARD);
			String idcardPath = custScanMapper.findPathByIdAndType(custScan);
			String path[] = idcardPath.split(";");
			String idCard_1= path[0];
			if(!StringUtils.isEmpty(idCardType_1)){
				idCard_1=cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD+File.separator+custId+File.separator+idCardType_1;
			}
			String idCard_2 =path[1];
			if(!StringUtils.isEmpty(idCardType_2)){
				idCard_2=cf_path + File.separator + Constant.CERTIFY_TYPE_MERCHANT_IDCARD+File.separator+custId+File.separator+idCardType_2;
			}
			idcardPath = idCard_1+";"+idCard_2;
			/**
			 * 更新身份证件
			 */
			this.updateScanPath(custId,Constant.CERTIFY_TYPE_MERCHANT_IDCARD,idcardPath,merchant.getCustName(),merchant.getRepresentativeCertNo());

		} catch (Exception e) {
			logger.error("更新证件信息异常",e);
			throw e;
		}

	}
	/**
	 * 修改商户信息事物
	 * @param paths
	 * @param custId
	 * @param isChange
	 * @param taskId
	 */
	public void updateRegist(String custId,MerchantVo merchant,Map<String,String> fileNames){
		
		this.updateCustScanInfo( custId,merchant,fileNames);
		this.updateMerchant( merchant);
		this.updateMerchantFeeRule(custId, merchant.getFeeCode());
		this.updateLoginInfoEmail(custId, merchant.getEmail());
		
	}
	
	
	/**
	 * 修改商户主体信息
	 * @param custId
	 * @param merchant
	 */
	public void updateMerchant(MerchantVo merchant){
		logger.info("修改商户主体信息[{}]",JSONObject.toJSONString(merchant));
		if(null == merchant){
			throw new IllegalArgumentException("商户信息为空");
		}
		try {
			merchant.setMerchantCode("M"+merchant.getBusinessLicense());
			merchantMapper.updateMerchant(merchant);
			logger.info("修改商户主体信息[{}]成功",merchant.getCustId());
		} catch (Exception e) {
			logger.error("修改商户主体信息[{}]失败",merchant.getCustId(), e);
		}
		
	}
	
	/**
	 * 修改商户费率
	 * @param custId
	 * @param feeCode
	 */
	public void updateMerchantFeeRule(String custId,String feeCode){
		ruleMapper.updateCustFeeRule(custId,feeCode);
		
	}
	
	/**
	 *  修改商户邮箱
	 */
	public void updateLoginInfoEmail(String custId,String email){
		tdLoginUserInfoMapper.updateEmail(custId, email);
	}
	/**
	 * 根据ID 和证件类型查找 证件路径
	 * @param custId
	 * @param certifyType
	 * @return
	 */
	public String findScanPath(String custId, String certifyType){
		CustScan custScan = new CustScan();
		custScan.setCertifyType(certifyType);
		custScan.setCustId(custId);
		return custScanMapper.findPath(custScan);
	}
	
	/**
	 * 更改证件路径
	 * @param custId
	 * @param certifyType
	 * @param path
	 */
	public void updateScanPath(String custId,String certifyType,String path,String custName,String certifyNo){
		CustScan custScan = new CustScan();
		custScan.setCustId(custId);
		custScan.setCertifyType(certifyType);
		custScan.setScanCopyPath(path);
		custScan.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		custScan.setCertifyNo(certifyNo);
		custScan.setCustName(custName);
		custScanMapper.updateCustScan(custScan);
	}
}
