package com.qifenqian.bms.merchant.reported.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.MerchantFilingInfo;
import com.qifenqian.bms.merchant.reported.bean.MerchantProdInfo;
import com.qifenqian.bms.merchant.reported.dao.CrIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.CrIncomeMapper;
import com.qifenqian.bms.platform.web.page.Page;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.TdCustInfo;
import com.seven.micropay.channel.domain.merchant.CrMerCategoryList;
import com.seven.micropay.channel.domain.merchant.CrMerChantDataReq;
import com.seven.micropay.channel.domain.merchant.MerRegistParamReq;
import com.seven.micropay.channel.domain.merchant.TdMerFilePatch;
import com.seven.micropay.channel.domain.merchant.TdMerFilePatchList;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;

@Service
public class CrIncomeService {
	private Logger logger = LoggerFactory.getLogger(CrIncomeService.class);
	
	@Autowired 
	private  IMerChantIntoService iMerChantIntoService;
	
	@Autowired
	private CrIncomeMapperDao crIncomeMapperDao;
	
    @Autowired
	private CrIncomeMapper crIncomeMapper;

	
	
	public static final String EXECUTE_SUCCESS = "SUCCESS";
	public static final String EXECUTE_FAILURE = "FAILURE";
	public static final String EXECUTE_FLAG = "EXECUTEFLAG";
	public static final String EXECUTE_DESC = "EXECUTEDESC";
	
	/**
	 * 调用进件接口 和 资料上传接口
	 */
	public void excuteCr(Map<String,Object> inMap,Map<String,Object> fileMap,List<CrInComeBean> beans){
		
		try {
			//调用商户进件接口
			iMerChantIntoService.merchatAdd(inMap);
			
			//调用商户资料上传接口
			iMerChantIntoService.fileUpload(fileMap);
			
			//修改商户报备状态
			crIncomeMapperDao.updateInComeInfo(beans);
		} catch (Exception e) {
			logger.error("执行进件接口、资料上传接口异常"+e);
			throw e;
		}
		
	}
	
	@Page
	public List<MerchantFilingInfo> selectReportList(MerchantFilingInfo selectBean) {
		List<MerchantFilingInfo> reportList = crIncomeMapper.getInComeInfoList(selectBean);
		return reportList;
	}
	
	/**
	 * 校验商户是线上线下
	 * @return 
	 */
	public String validOnOrOff(String merchantCode){
		MerchantProdInfo info  = this.getMerchantProdInfo(merchantCode);
		int n = 0;
		if(info != null){
			if(!StringUtils.isEmpty(info.getH5())|| !StringUtils.isEmpty(info.getPc())||!StringUtils.isEmpty(info.getOnecode())){
				n= n+1;
			}
			if(!StringUtils.isEmpty(info.getH5t())||!StringUtils.isEmpty(info.getMobile())){
				n= n+2;
			}
			if(n == 0){
				return null;
			}
			if(n == 1){
				return "1";
			}
			if(n == 2){
				return "0";
			}
			if(n == 3){
				return "2";
			}
		 }
		return null;
	 }
	
		
	@SuppressWarnings("unchecked")
	public String doReported(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String merchantCode = request.getParameter("merchantCode");
			
			//查询需要报备的商户
			List<CrInComeBean> inComeInfos = crIncomeMapperDao.getInComeInfo(merchantCode);
			
			List<CrMerChantDataReq> uplistReq = new ArrayList<CrMerChantDataReq>();//线上
			List<CrMerChantDataReq> downlistReq = new ArrayList<CrMerChantDataReq>();//线下
			List<CrMerCategoryList> listReq =new ArrayList<CrMerCategoryList>();//返回的数据
			CrMerCategoryList upcrMerCategoryList = new CrMerCategoryList();//线上实体类
			CrMerCategoryList downcrMerCategoryList = new CrMerCategoryList();//线下实体类
			
			List<TdMerFilePatch> uplistFile = new ArrayList<TdMerFilePatch>();
			List<TdMerFilePatch> downlistFile = new ArrayList<TdMerFilePatch>();
			List<TdMerFilePatchList> listFile = new ArrayList<TdMerFilePatchList>();
			TdMerFilePatchList uptdMerFilePatchList = new TdMerFilePatchList();
			TdMerFilePatchList downtdMerFilePatchList = new TdMerFilePatchList();
			
			List<CrInComeBean> addComeBean = new ArrayList<CrInComeBean>();
			String patchNo = GenSN.getSysTime();
			String upPatchNo =  "ON"+ patchNo; // 线上批次
			String downPatchNo = "OFF" + patchNo; // 线下批次
			
			if(inComeInfos != null && inComeInfos.size() >0){
				logger.debug("crIncomingServlet------组装商户进件数据-------");
				for(CrInComeBean bean :   inComeInfos){
					//进件数据
					CrMerChantDataReq crReq = new CrMerChantDataReq();
					crReq.setMerCustId(bean.getCustId());
//					crReq.setOuterMchNo(bean.getMerchantCode());		//外部商户编号
					crReq.setMchName(bean.getMchName());				//商户名称
					crReq.setMchShortName(bean.getMchShortName());		//商户简称
					crReq.setCategoryType(bean.getCategoryType());		//类目
					crReq.setProvince(bean.getProvince());				//省份代码
					crReq.setCity(bean.getCity());						//城市代码
					crReq.setCountry(bean.getCountry());				//区县码
					crReq.setAddress(bean.getAddress());				//详细地址
					crReq.setCustomerPhone(bean.getCustomerPhone());  	//客户电话
					crReq.setLinkman(bean.getLinkman());				//联系人
					crReq.setPhone("13632849522"); 						//联系电话
					crReq.setEmail("bank@szgyzb.com");  				//联系邮箱
					crReq.setOperator(bean.getOperator()); 				//负责人姓名
					crReq.setMchRole(bean.getMchRole()); 				//商户角色
					crReq.setOperatorIdno(bean.getOperatorIdno()); 		//负责人身份证号码
					crReq.setLinenceNo(bean.getLinenceNo()); 			//商户证件编号
					crReq.setCertifcateType(bean.getCertifcateType());	//商户证件类型
					crReq.setContractsType(bean.getContractsType()); 	//联系人类型
					
					//判断该商户是否线上 或者线下 
					String onOrOff =this.validOnOrOff(bean.getMerchantCode());
					if(onOrOff != null){
						//报备角色
						String merchantRole = request.getParameter("role");
						if("00".equals(merchantRole)){//线上线下
							crReq.setPatchNo(upPatchNo);		//线上批次	
							crReq.setOuterMchNo(bean.getCustId());
							uplistReq.add(crReq);
							
							CrMerChantDataReq downDataReq = new CrMerChantDataReq();
							BeanUtils.copyProperties(downDataReq, crReq);
							downDataReq.setPatchNo(downPatchNo);
							downDataReq.setOuterMchNo(bean.getMerchantCode());
							downlistReq.add(downDataReq);
							
							bean.setPatchNo(upPatchNo);			//报备表记录线上批次信息
							bean.setMchRole(Constant.ON_LINE);
							bean.setOutMerchantCode(bean.getCustId());
							//增加一条线下记录
							CrInComeBean newBean = new CrInComeBean();
							BeanUtils.copyProperties(newBean, bean);
							newBean.setPatchNo(downPatchNo);
							newBean.setChannelNo(Constant.HUARUN);
							newBean.setFilingStatus(Constant.YES_FILING);//设置报备状态
							newBean.setMchRole(Constant.OFF_LINE);
							newBean.setId(GenSN.getSN());
							newBean.setOutMerchantCode(bean.getMerchantCode());
							addComeBean.add(newBean);			//报备增加线下批次信息
						}else if ("01".equals(merchantRole)){//线上
							crReq.setPatchNo(upPatchNo);
							crReq.setOuterMchNo(bean.getCustId());
							uplistReq.add(crReq);
							bean.setPatchNo(upPatchNo);
							bean.setMchRole(Constant.ON_LINE);
							bean.setOutMerchantCode(bean.getCustId());
						}else if ("02".equals(merchantRole)){//线下
							crReq.setPatchNo(downPatchNo);
							crReq.setOuterMchNo(bean.getMerchantCode());
							downlistReq.add(crReq);
							bean.setPatchNo(downPatchNo);
							bean.setMchRole(Constant.OFF_LINE);
							bean.setOutMerchantCode(bean.getMerchantCode());
						}
						bean.setChannelNo(Constant.HUARUN);//设置渠道编号
						bean.setFilingStatus(Constant.YES_FILING);//设置报备状态
						bean.setId(GenSN.getSN());
						
					  }
				   }
				upcrMerCategoryList.setMchRole(Constant.ON_LINE);
				upcrMerCategoryList.setDetail(uplistReq);
				downcrMerCategoryList.setMchRole(Constant.OFF_LINE);
				downcrMerCategoryList.setDetail(downlistReq);
				if(downlistReq.size()>0){
					listReq.add(downcrMerCategoryList);
				}
				if(uplistReq.size()>0){
					listReq.add(upcrMerCategoryList);
				 }
				//更改已经报备商户的报备状态   0 已报备 1未报备
//				crIncomeMapper.updateInComeInfo(inComeInfos);
				inComeInfos.addAll(addComeBean);
				//进件成功 写数据
				logger.debug("报备商户数据[{}]", JSONObject.toJSONString(inComeInfos, true));
				crIncomeMapperDao.insertFilingInfo(inComeInfos);
			}
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("merList", listReq);
			map.put("channelType", ChannelMerRegist.CR_ULOPAY);
			//调用商户进件接口
			logger.debug("crIncomingServlet-------调用商户进件接口-------");
			ChannelResult channelResult = iMerChantIntoService.merchatAdd(map);
			
			Map<String,Object> resultmap = (Map<String,Object>) channelResult.getData();
			List<TdCustInfo>  successCustList = (List<TdCustInfo>)resultmap.get("custInfoStatus");
			logger.debug("报备成功文件返回数据[{}]", JSONObject.toJSONString(successCustList, true));
			if (inComeInfos != null && inComeInfos.size() >0){
				//存在数据的商户     提交资料
				if( successCustList != null && successCustList.size() >0){
					//当前批次进件成功商户 
					for(CrInComeBean bean:  inComeInfos){
						for(TdCustInfo data: successCustList){
							
							logger.debug("crIncomingServlet-------组装商户文件进件接口数据-------");
							//线下报备文件信息
							if(bean.getMerchantCode().equals(data.getMerchantCode())){
								//上传资料数据
								TdMerFilePatch filepatch = new TdMerFilePatch();
								filepatch.setOuterMchNo(bean.getMerchantCode());
								filepatch.setLinenceImg(bean.getBusinessPath().split(";")[0]);
								filepatch.setIndentityImg(bean.getIdCardPath().split(";")[0]);
								filepatch.setIndentityBackImg(bean.getIdCardPath().split(";")[1]);
								filepatch.setPatchNo(downPatchNo);
								downlistFile.add(filepatch);
								continue;
							//线上报备文件信息
							}else if(bean.getCustId().equals(data.getMerchantCode())){
								TdMerFilePatch filepatch = new TdMerFilePatch();
								filepatch.setOuterMchNo(bean.getCustId());
								filepatch.setLinenceImg(bean.getBusinessPath().split(";")[0]);
								filepatch.setIndentityImg(bean.getIdCardPath().split(";")[0]);
								filepatch.setIndentityBackImg(bean.getIdCardPath().split(";")[1]);
								filepatch.setPatchNo(upPatchNo);
								uplistFile.add(filepatch);
								continue;
							}
							
						}
					}
					
					uptdMerFilePatchList.setMchRole(Constant.ON_LINE);
					uptdMerFilePatchList.setDetail(uplistFile);
					downtdMerFilePatchList.setMchRole(Constant.OFF_LINE);
					downtdMerFilePatchList.setDetail(downlistFile);
					if(uplistFile.size()>0){
						listFile.add(uptdMerFilePatchList);
					}
					if(downlistFile.size()>0){
						listFile.add(downtdMerFilePatchList);
					}
					
					//进件成功商户 调用上传资料接口
					HashMap<String,Object> filemap = new HashMap<String,Object>();
					filemap.put("filePatch", listFile);
					//调用商户资料上传接口
					logger.debug("crIncomingServlet-------调用商户文件进件接口数据-------");
					iMerChantIntoService.fileUpload(filemap);
				}
			}
		} catch (Exception e) {
			logger.error("CrIncomingServlet execute exception",e);
			return EXECUTE_FAILURE;
		}
		
		return EXECUTE_SUCCESS;
	
	}

		/**
		 * 合利宝报备
		 * @param request
		 * @return
		 */
		public String helipayReport(HttpServletRequest request){
			
			//查询商户信息
			String merchantCode = request.getParameter("merchantCode");
			try {
				List<CrInComeBean> inComeInfos = crIncomeMapperDao.getInComeInfo(merchantCode);
				HashMap<String , Object> map = new HashMap<String , Object>();
				//拼装接口信息
				if(inComeInfos != null && inComeInfos.size() >0){
					//查询行业类型信息
					String industryTypeCode = request.getParameter("categoryId");
					CategoryCodeInfo codeInfo = crIncomeMapperDao.getCategoryInfoHelipay(industryTypeCode);
					MerRegistParamReq paramReq = new MerRegistParamReq();
					paramReq.setLegalPerson(inComeInfos.get(0).getOperator());//法人名字
					paramReq.setLegalPersonID(inComeInfos.get(0).getOperatorIdno());//法人身份证号码
					paramReq.setBusinessLicense(inComeInfos.get(0).getLinenceNo());//营业执照
					paramReq.setOrgNum(inComeInfos.get(0).getLinenceNo());//组织机构代码
					paramReq.setAddress(inComeInfos.get(0).getAddress());//通讯地址
					paramReq.setLinkman(inComeInfos.get(0).getLinkman());//联系人
					paramReq.setLinkPhone(inComeInfos.get(0).getPhone());//联系电话
					paramReq.setEmail(inComeInfos.get(0).getEmail());//联系邮箱
					paramReq.setBindMobile(inComeInfos.get(0).getPhone()); //绑定手机
					paramReq.setBankCode(inComeInfos.get(0).getCNAPS());  //联行号
					paramReq.setAccountName(inComeInfos.get(0).getAccountName()); //结算户名
					paramReq.setAccountNo(inComeInfos.get(0).getAccountNo()); //结算卡号
					paramReq.setSettleBankType("TOPUBLIC");//结算卡类型
					paramReq.setSettlementMode("SELF");//结算方式
					paramReq.setSettlementPeriod("T1");//结算类型
					paramReq.setIndustryTypeCode(industryTypeCode);//行业类型编号
					paramReq.setMerchantCategory(codeInfo.getMerchantCategory());//经营类别
					paramReq.setMerchantType(codeInfo.getMerchantType());
					paramReq.setAuthorizationFlag(true);
					paramReq.setRegionCode(crIncomeMapperDao.getAreaId(inComeInfos.get(0).getCountry()));//区县编码
					String sn = GenSN.getSN();
					paramReq.setOrderNo(sn);
					
					//报备接口参数数据
					map.put("merList", paramReq);
					map.put("channelType", ChannelMerRegist.HELIPAY);
					
					//进件成功 写数据
					String patchNo = GenSN.getSysTime();
					inComeInfos.get(0).setPatchNo(patchNo);
					inComeInfos.get(0).setChannelNo(Constant.HELIPAY);
					inComeInfos.get(0).setOutMerchantCode(sn);
					inComeInfos.get(0).setId(GenSN.getSN());
					inComeInfos.get(0).setFilingStatus(Constant.YES_FILING);
					logger.debug("报备商户数据[{}]", JSONObject.toJSONString(inComeInfos, true));
					crIncomeMapperDao.insertFilingInfo(inComeInfos);
					
					//调用合利宝报备接口
					ChannelResult channelResult = iMerChantIntoService.merchatAdd(map);
					if(channelResult != null ){
						if("04".equals(channelResult.getReCode())){
							return "FAIL";
						}
					}
				}
				
			} catch (Exception e) {
				logger.error("合利宝报备异常 ",e);
				return "FAIL";
			}
			
			return "SUCCESS";
		}
		
		public List<CrInComeBean> getInComeInfo(@Param("merchantCode") String merchantCode){
			
			   return crIncomeMapperDao.getInComeInfo(merchantCode);
		    }
			public void updateInComeInfo(List<CrInComeBean> beans){
				crIncomeMapperDao.updateInComeInfo(beans);
			}
			public void insertFilingInfo(List<CrInComeBean> beans){
				crIncomeMapperDao.insertFilingInfo(beans);
			}
			public MerchantProdInfo getMerchantProdInfo(@Param("merchantCode") String merchantCode){
				return crIncomeMapperDao.getMerchantProdInfo(merchantCode);
			}
			
			public List<ChannlInfo> getChannlInfoList(){
				return crIncomeMapperDao.getChannlInfoList();
			}
			public List<MerchantFilingInfo> getfilingInfoList(MerchantFilingInfo merchantCode){
				
				return crIncomeMapperDao.getfilingInfoList(merchantCode);
			}
			
			/**
			 * 查询行业信息
			 * @param merchantId
			 * @return
			 */
			public CategoryCodeInfo getCategoryTypeInfo(String merchantId){
				return crIncomeMapperDao.getCategoryTypeInfo(merchantId);
			}
			
			/**
			 * 校验商户是否报备
			 * @param merchantCode
			 * @return
			 */
			public List<MerchantFilingInfo> verifyFiling(MerchantFilingInfo merchantCode){
				return crIncomeMapperDao.verifyFiling(merchantCode);
			}
			
	}


