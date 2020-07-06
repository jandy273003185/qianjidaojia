package com.qifenqian.bms.merchant.reported.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.jellyfish.api.realnameauthentication.RealNameAuthenticationService;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationAuthorizeReq;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationAuthorizeRes;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationCancelReq;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationCancelRes;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationQueryReq;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationQueryRes;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationReq;
import com.qifenqian.jellyfish.bean.realnameauthentication.RealNameAuthenticationRes;
import com.seven.micropay.commons.util.DateUtil;

@Service
public class VerifiedMerchantService {
	
	private Logger logger = LoggerFactory.getLogger(VerifiedMerchantService.class);
	
	@Autowired
	private FmIncomeMapper fmIncomeMapper;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private CustScanMapper custScanMapper;
	@Autowired
	private MerchantEnterService merchantEnterService;
	
	@Reference(version="${dubbo.provider.jellyfish.version}")
	private RealNameAuthenticationService realNameAuthenticationService;
	
	@Value("${images.relativePaths}")
    private String relativePaths;
    //存储路径
    @Value("${images.absolutePaths}")
    private String absolutePaths;
//	private static final String PRE_PATH = "D:\\data\\nfsshare\\upload\\picture";
	/**
	 * 微信商户实名认证提交
	 * @param tdMerchantDetailInfo
	 * @return
	 */
	public Map<String, Object> verifiedMerchant(TdMerchantDetailInfo tdMerchantDetailInfo){
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		RealNameAuthenticationReq req = new RealNameAuthenticationReq();
		TdMerchantDetailInfo detailInfo = new TdMerchantDetailInfo();
		//调用实名认证接口
		try {
			req.setMno(tdMerchantDetailInfo.getOutMerchantCode());
			req.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			
			logger.info("-----------------实名认证请求报文：" + JSONObject.toJSONString(req));
			RealNameAuthenticationRes res = realNameAuthenticationService.apply(req);
			logger.info("-----------------实名认证响应报文：" + JSONObject.toJSONString(res));
			
			if(res.isSuccess() && "0000".equals(res.getBizCode())) {
				//根据外部商户号写入微信申请单号
				detailInfo.setWxApplyNo(res.getWxApplyNo());
				detailInfo.setOutMerchantCode(tdMerchantDetailInfo.getOutMerchantCode());
				detailInfo.setReportStatus("20");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(detailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(detailInfo);
				detailInfo.setMerchantCode(tdMerchantDetailInfo.getMerchantCode());
				detailInfo.setPatchNo(tdMerchantDetailInfo.getPatchNo());
				detailInfo.setReportStatus("O");
				detailInfo.setDetailStatus("20");
				fmIncomeMapper.updateTdMerchantReport(detailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				
				verifiedResult.put("message", "认证请求成功");
				verifiedResult.put("result", "SUCCESS");
			}else if("0001".equals(res.getBizCode())){
				verifiedResult.put("result", "FAIL");
				verifiedResult.put("message", res.getBizMsg());
			}
		} catch (Exception e) {
			logger.info("1111," + e);
			verifiedResult.put("message", e);
			verifiedResult.put("result", "FAIL");
		}
		return verifiedResult;
		
	}

	/**
	 * 微信商户认证申请结果查询
	 * @param tdMerchantDetailInfo
	 * @return
	 */
	public Map<String, Object> verifiedQuery(TdMerchantDetailInfo tdMerchantDetailInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		RealNameAuthenticationQueryReq req = new RealNameAuthenticationQueryReq();
		try {	
			//调用实名查询接口
			req.setMno(tdMerchantDetailInfo.getOutMerchantCode());
			req.setWxApplyNo(tdMerchantDetailInfo.getWxApplyNo());
			req.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			logger.info("-----------------认证申请结果查询请求报文：" + JSONObject.toJSONString(req));
			RealNameAuthenticationQueryRes res = realNameAuthenticationService.query(req);
			logger.info("-----------------认证申请结果查询返回报文：" + JSONObject.toJSONString(res));
			if("0000".equals(res.getBizCode())) {
				
				
				result.put("message", res.getBizMsg());
				result.put("idenStatus", res.getIdenStatus());
				result.put("recode", res.getInfoQrcode());
				//将返回二维码存入服务器
				String uploadFileName = BASE64CodeToBeImage(res.getInfoQrcode());
				//返回二维码图片直接存入td_cust_scan_copy
				CustScan custScan = new CustScan();
				MerchantVo merchantVo = merchantMapper.newFindMerchantInfo(tdMerchantDetailInfo.getCustId());
				PicturePath picturePathOld = merchantEnterService.getPicPath(merchantVo);
				
				custScan.setCustId(tdMerchantDetailInfo.getCustId());
				custScan.setAuthId(merchantVo.getAuthId());
				custScan.setCreateId(merchantVo.getCreateId());
				//微信联系人信息确认二维码
				custScan.setCertifyType("36");
				if(!StringUtils.isEmpty(picturePathOld.getInfoQrcodePath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				custScan.setStatus("00");
				custScan.setScanCopyPath(uploadFileName);
				custScanMapper.insertCustScan(custScan);
				logger.info("-----------------------" + uploadFileName);
				tdMerchantDetailInfo.setReportStatus("21");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("21");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				
				result.put("message", "认证请求成功");
				result.put("result", "SUCCESS");
			}else {
				tdMerchantDetailInfo.setReportStatus("22");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("22");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				result.put("result", "FAIL");
				result.put("message", res.getRejectInfo());
			}
		} catch (Exception e) {
			logger.info("-----------------------" + e);
			result.put("message", e);
			result.put("result", "FAIL");
		}
		return result;
	}
    

	/**
	 * 微信商户认证申请撤销接口
	 * @param tdMerchantDetailInfo
	 * @return
	 */
	public Map<String, Object> verifiedCancel(TdMerchantDetailInfo tdMerchantDetailInfo) {
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		RealNameAuthenticationCancelReq req = new RealNameAuthenticationCancelReq();
		//调用实名请撤销接口
		try {
			req.setMno(tdMerchantDetailInfo.getOutMerchantCode());
			req.setWxApplyNo(tdMerchantDetailInfo.getWxApplyNo());
			req.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			logger.info("-----------------实名认证申请撤销请求报文：" + JSONObject.toJSONString(req));
			RealNameAuthenticationCancelRes res = realNameAuthenticationService.cancel(req);
			logger.info("-----------------实名认证申请撤销响应报文：" + JSONObject.toJSONString(res));
			
			if("0000".equals(res.getBizCode())) {
				tdMerchantDetailInfo.setReportStatus("24");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("24");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				verifiedResult.put("message", "实名认证申请撤销请求成功");
				verifiedResult.put("result", "SUCCESS");
			}else if("0001".equals(res.getBizCode())){
				tdMerchantDetailInfo.setReportStatus("25");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("25");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				verifiedResult.put("result", "FAIL");
				verifiedResult.put("message", res.getBizMsg());
			}
		} catch (Exception e) {
			verifiedResult.put("message", e);
			verifiedResult.put("result", "FAIL");
		}
		return verifiedResult;
	}
	
	/**
	 * 微信子商户授权状态查询接口
	 * @param tdMerchantDetailInfo
	 * @return
	 */
	public Map<String, Object> verifiedAuthorize(TdMerchantDetailInfo tdMerchantDetailInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		RealNameAuthenticationAuthorizeReq req = new RealNameAuthenticationAuthorizeReq();
		try {
			req.setSubMchId(tdMerchantDetailInfo.getWxChildNo());
			req.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
			logger.info("-----------------微信子商户授权状态查询请求报文：" + JSONObject.toJSONString(req));
			RealNameAuthenticationAuthorizeRes res = realNameAuthenticationService.authorize(req);
			logger.info("-----------------微信子商户授权状态查询响应报文：" + JSONObject.toJSONString(req));
			
			if("0000".equals(res.getBizCode())) {
				tdMerchantDetailInfo.setReportStatus("27");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("27");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				result.put("status", res.getAuthStatus());
				result.put("message", "授权状态查询请求成功");
				result.put("result", "SUCCESS");
			}else if("0001".equals(res.getBizCode())){
				tdMerchantDetailInfo.setReportStatus("28");
				logger.info("更新td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfo));
				fmIncomeMapper.updateTdMerchantDetailInfo(tdMerchantDetailInfo);
				tdMerchantDetailInfo.setReportStatus("O");
				tdMerchantDetailInfo.setDetailStatus("28");
				fmIncomeMapper.updateTdMerchantReport(tdMerchantDetailInfo);
				logger.info("更新td_merchant_detail_info表数据：{} end...");
				result.put("result", "FAIL");
				result.put("message", res.getBizMsg());
			}
		} catch (Exception e) {
			result.put("message", e);
			result.put("result", "FAIL");
		}
		return result;
	}
	
	
	/**
     * 对给定的字符串进行base64解码操作
     */
	/**
	 * base64转图片
	 * @param str bas64字符串
	 * @return 存储地址
	 */
	public String BASE64CodeToBeImage(String str){
//	    String BASE64str = str.substring(str.lastIndexOf(",")+1);
	    //统一图片后缀
//	    String ext = str.substring(str.indexOf("/")+1,str.indexOf(";"));
	    //文件名称
	    String uploadFileName = DateUtils.getDateStr8()+"_"+UUID.randomUUID().toString().replaceAll("-","") + ".jpg";
	    //存储地址
	    StringBuilder path = new StringBuilder(absolutePaths).append("/").append(uploadFileName);//路径读取不到
	    File saveFile = new File(String.valueOf(path));
	    Decoder decoder = Base64.getDecoder();
	    try(OutputStream out = new FileOutputStream(saveFile)){
	        byte[] b = decoder.decode(str);
	        for (int i = 0; i <b.length ; i++) {
	            if (b[i] <0) {
	                b[i]+=256;
	            }
	        }
	        out.write(b);
	        out.flush();
	        String[] result ={path+uploadFileName,uploadFileName};
	        logger.info("********************图片上传成功********************");
	        return  uploadFileName;
	    }catch (Exception e){
	        e.printStackTrace();
	        logger.info("********************图片上传失败********************");
	        return "图片上传失败，请重新上传";
	    }
	}

	
}
