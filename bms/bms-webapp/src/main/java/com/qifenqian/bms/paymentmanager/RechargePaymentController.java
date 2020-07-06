package com.qifenqian.bms.paymentmanager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
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

import com.sevenpay.plugin.message.bean.MessageBean;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditInfo;
import com.qifenqian.bms.paymentmanager.service.PaymentService;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageColumnValues;


@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class RechargePaymentController {
	private static Logger logger = LoggerFactory.getLogger(AuditPaymentController.class);
	
	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private FmIncomeMapperDao fmIncomeMapperDao;
	
	/**
	 * 代付充值审核主页面
	 */
	@RequestMapping(PaymentManagerPath.AUDITRECHARGEPAYMENT)
	public ModelAndView rechargePaymentList(TdPayCreditInfo queryBean) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.AUDITRECHARGEPAYMENT);
		/*去掉多余的空格*/
		if (!StringUtils.isBlank(queryBean.getId())){
			queryBean.setId(queryBean.getId().trim());
		}
		if (!StringUtils.isBlank(queryBean.getRecAccountNo())){
			queryBean.setRecAccountNo(queryBean.getRecAccountNo().trim());
		}
		
		if (!StringUtils.isBlank(queryBean.getCustName())){
			queryBean.setCustName(queryBean.getCustName().trim());
		}
		
		List<TdPayCreditInfo> payCreditList = paymentService.rechargePaymentList(queryBean);
		for(int i=0;i < payCreditList.size();i++){
			//根据商户号查询出客户名称
			TdCustInfo custInfo = new TdCustInfo();
			if(payCreditList.get(i).getRecMerchantNo() != null){
				custInfo =fmIncomeMapperDao.getInComeInfo(payCreditList.get(i).getRecMerchantNo());
				if(null == payCreditList.get(i).getCustName()){
					if(null != custInfo){
						if(null != custInfo.getCustName()){
							payCreditList.get(i).setCustName(custInfo.getCustName());
						}
					}
				}
			}else if(payCreditList.get(i).getMerchantCode() != null){
				custInfo =fmIncomeMapperDao.getInComeInfo(payCreditList.get(i).getMerchantCode());
				if(null == payCreditList.get(i).getCustName()){
					if(null != custInfo){
						if(null != custInfo.getCustName()){
							payCreditList.get(i).setCustName(custInfo.getCustName());
						}
					}
				}
			}else{
				if(payCreditList.get(i).getRecAccountNo() != null){
					//根据email查询出custName
					String email =payCreditList.get(i).getRecAccountNo(); 
					custInfo = paymentService.selCustInfoByEmail(email);
					if(null == payCreditList.get(i).getCustName() && null != custInfo){
						if(null != custInfo){
							if(null != custInfo.getCustName()){
								payCreditList.get(i).setCustName(custInfo.getCustName());
							}
						}
					}
				}else if(payCreditList.get(i).getEmail() != null){
					String email = payCreditList.get(i).getEmail();
					custInfo = paymentService.selCustInfoByEmail(email);
					if(null == payCreditList.get(i).getCustName() && null != custInfo){
						if(null != custInfo){
							if(null != custInfo.getCustName()){
								payCreditList.get(i).setCustName(custInfo.getCustName());
							}
						}
					}
					
				}
				
			}
			
			
		}
		mv.addObject("queryBean", queryBean);
		mv.addObject("payCreditList", JSONObject.toJSON(payCreditList));
		return mv;
	}
	
	
	/**
	 * 查询单笔代付记录明细
	 */
	@RequestMapping(PaymentManagerPath.SELRECHARGEINFO)
	@ResponseBody
	public String selRechargeInfo(String id){
		JSONObject ob = new JSONObject();
		logger.info("开始 查询单笔代付记录明细:{}",id);
		try {
			List<TdPayCreditInfo>  list = paymentService.selRechargeInfo(id);
			if(null!=list){
				TdPayCreditInfo info = list.get(0);
				//根据id查询custName
				if( null != info.getMerchantCode()){
					TdCustInfo custInfo =fmIncomeMapperDao.getInComeInfo(list.get(0).getMerchantCode());
					info.setCustName(custInfo.getCustName());
					if(null == info.getRecAccountNo()){
						info.setEmail(custInfo.getEmail());
					}else{
						info.setEmail(info.getRecAccountNo());
					}
				}else if(null != info.getRecMerchantNo()){
					TdCustInfo custInfo =fmIncomeMapperDao.getInComeInfo(list.get(0).getRecMerchantNo());
					info.setCustName(custInfo.getCustName());
					if(null == info.getRecAccountNo()){
						info.setEmail(custInfo.getEmail());
					}else{
						info.setEmail(info.getRecAccountNo());
					}
					
				}else{
					if(info.getRecAccountNo() != null){
						//根据email查询出custName
						String email =info.getRecAccountNo(); 
						TdCustInfo custInfo = paymentService.selCustInfoByEmail(email);
						if(null == info.getCustName() && null != custInfo){
							if(null != custInfo.getCustName()){
								info.setCustName(custInfo.getCustName());
							}
						}
					}else if(info.getEmail() != null){
						String email = info.getEmail();
						TdCustInfo custInfo = paymentService.selCustInfoByEmail(email);
						if(null == info.getCustName() && null != custInfo){
							if(null != custInfo.getCustName()){
								info.setCustName(custInfo.getCustName());
							}
						}
						
					}
					
				}
				
				
				ob.put("Info", info);
				ob.put("result", "SUCCESS");
				ob.put("message", "商户单笔代付信息查询成功");
			}else{
				logger.error("商户单笔代付信息查询异常");
				ob.put("result", "FAILE");
				ob.put("message","商户单笔代付信息查询异常");
			}
			
		} catch (Exception e) {
			logger.error("商户单笔代付信息查询异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }  
	
	
	
	
	
  	/**
  	 * 充值审核不通过
  	 */
	@RequestMapping(PaymentManagerPath.AUDITRECHARGENOTPASS)
	@ResponseBody
	public String rechargeNotPass(String id,String message,HttpServletRequest request,String email,String custName,String auditFlag){
		JSONObject ob = new JSONObject();
		logger.info("开始代付充值{}审核不通过流程",id);
		
		try {
			paymentService.AuditRecharge(id,message,auditFlag);
		    if(!StringUtils.isBlank(email)){
		    	   final IPlugin plugin = commonService.getIPlugin();
					final MessageBean messageBean = new MessageBean();
					String[] tos = new String[] {email};//EMAIL
					messageBean.setSubject("【七分钱支付】代付充值审核未通过");//标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
				   + "<div style=\"margin-bottom:10px;\">您的代付"+custName+"未能通过审核<br/>  ,原因是："+message+"。<br/>如有任何问题,请拨打400-633-0707</div></body></html>");//内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);//邮件EMAIL
					smsExecutors.execute(new Runnable() {
						@Override
						public void run() {
							logger.debug("发送邮件");
						    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); //邮件EMAIL
							
						}
					});	
		        }
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   } 
	
	
	 /**
	 * 代付充值清洁算审核通过
	 */
	@RequestMapping(PaymentManagerPath.AUDITRECHARGEPASS)
	@ResponseBody
	public String rechargeAuditPass(HttpServletRequest request,String id,String sumMoney){
		JSONObject ob = new JSONObject();
		
		try {
			//审核通过
			paymentService.RechargeFristPass(id,sumMoney);
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("代付充值清洁算审核",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	   }
	/**
	 * 财务审核通过
	 */
	@RequestMapping (PaymentManagerPath.AUDITRECHARGESECOUNDPASS)
	@ResponseBody
	public String rechargeAuditSecodePass(HttpServletRequest request,TdPayCreditInfo creditInfo) {
		logger.info("=================充值财务审核 开始 =================");
		JSONObject ob = new JSONObject();
		  TdPayCreditInfo tdPayCreditInfo=null;
		 try {
			 String id = creditInfo.getId();
		   List<TdPayCreditInfo>  list = paymentService.selRechargeInfo(id);
		   if(null!=list && list.size()>0){
			    tdPayCreditInfo = list.get(0);
			paymentService.rechargeAuditSecodePass(request,tdPayCreditInfo);
		  
			ob.put("result", "SUCCESS");
			ob.put("message", "充值成功");
		   }
		} catch (Exception e) {
			logger.error("代付充值异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}
	
	 /**
	 * 代付充值审核通过
	 */
	@RequestMapping(PaymentManagerPath.PAYMENTRECHARGEAUDITPASS)
	@ResponseBody
	public String paymengtRechargeAuditPass(HttpServletRequest request,TdPayCreditInfo creditInfo){
		logger.info("=================代付充值审核 开始 =================");

		JSONObject ob = new JSONObject();
		TdPayCreditInfo tdPayCreditInfo=null;
		
		try {
		   String id = creditInfo.getId();
		   String sumMoney = creditInfo.getAuditAmt();
		   paymentService.RechargeFristPass(id,sumMoney);
		   
		   List<TdPayCreditInfo>  list = paymentService.selRechargeInfo(id);
		   if(null!=list && list.size()>0){
			    tdPayCreditInfo = list.get(0);
			paymentService.rechargeAuditSecodePass(request,tdPayCreditInfo);
		  
			ob.put("result", "SUCCESS");
			ob.put("message", "充值成功");
		   }
		} catch (Exception e) {
			logger.error("代付充值异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		
		return ob.toJSONString();
		
	}
	
	
	/**
	 * 撤销
	 */
	@RequestMapping (PaymentManagerPath.PAYMENTRECHARGEREVOKE)
	@ResponseBody
	public String rechargeRevoke(HttpServletRequest request,TdPayCreditInfo creditInfo) {
		logger.info("=================代付充值撤销 开始 =================");
		JSONObject ob = new JSONObject();
		TdPayCreditInfo tdPayCreditInfo=null;
		 try {
			 String id = creditInfo.getId();
			 List<TdPayCreditInfo>  list = paymentService.selRechargeInfo(id);
			 if(null!=list && list.size()>0){
				  tdPayCreditInfo = list.get(0);
			 
			paymentService.rechargeRevoke(request,tdPayCreditInfo);
			ob.put("result", "SUCCESS");
			ob.put("message", "撤销成功");
			 }
		} catch (Exception e) {
			logger.error("代付充值撤销异常",e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}
	

	
	// 读取服务器图片
		@RequestMapping(PaymentManagerPath.GETPICTURE)
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("custId");
			String certifyType = request.getParameter("certifyType");
			String front = request.getParameter("front");
			String scanPath = "";
			scanPath =paymentService.selBankScanPathById(id);
			
			if (scanPath != null) {
				String path[] = null;
				if (certifyType.equals(Constant.CERTIFY_TYPE_PERSON_IDCARD)) {
					scanPath =scanPath.split(";")[0] ;
				}
				
				if (certifyType.equals(Constant.CERTIFY_TYPE_OPEN)) {
					scanPath =scanPath.split(";")[0] ;
				}
				
				if(certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)){
					scanPath = scanPath.split(";")[0];
				}
				
				if(certifyType.equals(Constant.CERTIFY_TYPE_BANK_PAPERS)){
					scanPath = scanPath.split(";")[0];
				}
				if (!StringUtils.isEmpty(front)) {
					path = scanPath.split(";");
					if (front.equals("0")) {
						scanPath = path[0];
					} else {
						scanPath = path[1];
					}
				}

				OutputStream os = response.getOutputStream();
				File file = new File(scanPath);

				if (file.exists()) {
					FileInputStream fips = new FileInputStream(file);
					byte[] btImg = readStream(fips);
					os.write(btImg);
					os.flush();
					if (null != fips) {
						fips.close();
					}
					if (null != os) {
						os.close();

					}
				}

			}

		}

		/**
		 * 读取管道中的流数据
		 */
		public byte[] readStream(InputStream inStream) {
			ByteArrayOutputStream bops = new ByteArrayOutputStream();
			int data = -1;
			try {
				while ((data = inStream.read()) != -1) {
					bops.write(data);
				}
				return bops.toByteArray();
			} catch (Exception e) {
				return null;
			} finally {
				if (null != bops) {
					try {
						bops.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}	
	
		
		
		/**
		 * 代付充值审核主页面
		 */
		@RequestMapping(PaymentManagerPath.NEWAUDITRECHARGEPAYMENT)
		public ModelAndView newRechargePaymentList(TdPayCreditInfo queryBean) {
			ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.AUDITRECHARGEPAYMENT);
			/*去掉多余的空格*/
			if (!StringUtils.isBlank(queryBean.getId())){
				queryBean.setId(queryBean.getId().trim());
			}
			if (!StringUtils.isBlank(queryBean.getRecAccountNo())){
				queryBean.setRecAccountNo(queryBean.getRecAccountNo().trim());
			}
			
			if (!StringUtils.isBlank(queryBean.getCustName())){
				queryBean.setCustName(queryBean.getCustName().trim());
			}
			
			List<TdPayCreditInfo> payCreditList = paymentService.rechargePaymentList(queryBean);
			for(int i=0;i < payCreditList.size();i++){
				//根据商户号查询出客户名称
				TdCustInfo custInfo = new TdCustInfo();
				if(payCreditList.get(i).getRecMerchantNo() != null){
					custInfo =fmIncomeMapperDao.getInComeInfo(payCreditList.get(i).getRecMerchantNo());
					if(null == payCreditList.get(i).getCustName()){
						if(null != custInfo.getCustName()){
							payCreditList.get(i).setCustName(custInfo.getCustName());
						}
					}
				}else if(payCreditList.get(i).getMerchantCode() != null){
					custInfo =fmIncomeMapperDao.getInComeInfo(payCreditList.get(i).getMerchantCode());
					if(null == payCreditList.get(i).getCustName()){
						if(null != custInfo.getCustName()){
							payCreditList.get(i).setCustName(custInfo.getCustName());
						}
					}
				}
				
				
			}
			mv.addObject("queryBean", queryBean);
			mv.addObject("payCreditList", JSONObject.toJSON(payCreditList));
			return mv;
		}
		
		
		/**
		 * 获取代付审核历史记录
		 */
		@RequestMapping (PaymentManagerPath.GETRECHRAGEHISTORY)
		@ResponseBody
		public String rechargeGetHistory(HttpServletRequest request,String id) {
			logger.info("=================充值财务审核 开始 =================");
			JSONObject ob = new JSONObject();
			 try {
				List<TdPayCreditAuditInfo> checkHistory = paymentService.getRechargeCheckHistory(id);
				if(null!=checkHistory && checkHistory.size()>0){
					ob.put("checkHistory", checkHistory);
					ob.put("result", "SUCCESS");
					
				}else{
					ob.put("result", "FAIL");
					ob.put("result", "获取代付审核记录异常");
				}
				
				
			} catch (Exception e) {
				logger.error("代付充值审核记录异常",e);
				ob.put("result", "FAILE");
				ob.put("message", e.getMessage());
			}
			return ob.toJSONString();
		}
}
