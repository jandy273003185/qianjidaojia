package com.qifenqian.bms.basemanager.toPay.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.city.bean.CityNew;
import com.qifenqian.bms.basemanager.city.bean.ProvinceBean;
import com.qifenqian.bms.basemanager.toPay.bean.Creater;
import com.qifenqian.bms.basemanager.toPay.bean.DateBean;
import com.qifenqian.bms.basemanager.toPay.bean.ProfitBean;
import com.qifenqian.bms.basemanager.toPay.bean.TdPaymentBatDetailBean;
import com.qifenqian.bms.basemanager.toPay.bean.TopayBatDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TopaySingleDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TyyBankInfo;
import com.qifenqian.bms.basemanager.toPay.service.TopayService;
import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.AcctType;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.ReqSysId;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayApplyRequest;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayApplyResponse;
import com.sevenpay.invoke.transaction.queryacctseven.QueryAcctSevenRequest;
import com.sevenpay.invoke.transaction.queryacctseven.QueryAcctSevenResponse;
import com.sevenpay.plugin.IPlugin;

/**
 * 代付Controller
 * @author hongjiagui
 *
 */
@Controller
@RequestMapping(TopayPath.BASE)
public class TopayController {

	private static Logger logger = LoggerFactory.getLogger(TopayController.class);
	
	@Autowired
	private SevenpayCoreServiceInterface coreServiceInterface;
	@Autowired
	private TopayService topayService;
	
	@Value("${ACCOUNT_NUMBER}")
	private String ACCOUNT_NUMBER;
	
	@Value("${CHANNEL_NAME}")
	private String CHANNEL_NAME;
	

	@Autowired
	@Qualifier("pluginInvokerProxy")
	IPlugin sevenPayPlugin;
	/**
	 * 代付审核（新）
	 * @param bean
	 * @return
	 */
	@RequestMapping(TopayPath.LIST)
	public ModelAndView initTopay(TopayBatDetail bean) {	
		ModelAndView mv = new ModelAndView(TopayPath.BASE + TopayPath.LIST);
		List<TopayBatDetail> list = topayService.listBatRecord(bean);
		List<Creater> createrList = topayService.listCreater();
		mv.addObject("queryBean",bean);
		mv.addObject("createrList",createrList);
		mv.addObject("audingList", list);
		return mv;
	}
	
	/**
	 * 代付审核详情
	 * @param batNo
	 * @return
	 */
	@RequestMapping(TopayPath.AUDITPAYMENTINFOS)
	public ModelAndView topayDetailInfo(String batNo) {
		
		ModelAndView mv = new ModelAndView(TopayPath.BASE + TopayPath.AUDITPAYMENTINFOS);
		//获取汇总信息
		TopayBatDetail bean = new TopayBatDetail();
		bean.setBatNo(batNo);
		TopayBatDetail result = topayService.listBatRecord(bean).get(0);
		//获取该次交易下的所有单笔交易
		List<TopaySingleDetail> singleList = topayService.listSingleRecordByOrderNo(batNo);
		//计算总金额
		String totalAmt = this.addMoney(result.getSumAmt().toString(), result.getFeeAmt());
		
		mv.addObject("totalAmt", totalAmt);
		mv.addObject("auditPayment",result);
		mv.addObject("paymentList", singleList);
		return mv;
	}
	
	/**
	 * 代付审核
	 */
	@RequestMapping(TopayPath.AUDIT)
	@ResponseBody
	public String auditTopay(String batNo,String auditResult,String reason) {
		logger.info("<<<<<交易号为" + batNo + "的代付审核开始，审核结果为 " + auditResult);
		//审核通过 
		JSONObject json = new JSONObject();
		if("pass".equals(auditResult)) {
			json = topayService.toPay(batNo);
			
		}else if("noPass".equals(auditResult)) {
			//审核不通过 
			json = topayService.auditNopass(batNo,reason);
			
		}
		return json.toJSONString();
	}
	/**
	 * 新增代付
	 * @return
	 */
	@RequestMapping(TopayPath.ADD)
	public ModelAndView addTopay() {		
		ModelAndView mv = new ModelAndView(TopayPath.BASE + TopayPath.ADD);
		List<TyyBankInfo> bankList = topayService.getTyyBankCode();
		List<ProvinceBean> provinceList = topayService.listProvince();
		mv.addObject("bankList", bankList);
		mv.addObject("provinceList",provinceList);
		return mv;
	}
	
	/**
	 * 代付
	 */
	@RequestMapping(TopayPath.SAVE)
	@ResponseBody
	public String saveTopay(String paerMerchantCode,String singleFeeAmt,String[] recAccountName,
				String[] recCardName,String[] recBankCode,String[] recAccountNo,String[] recBankProvince,String[] recBankCity,String[] recTransAmt,String dataTotal) {
		List<TopaySingleDetail> list = new ArrayList<TopaySingleDetail>();
		TopayBatDetail batDetail = new TopayBatDetail();
		JSONObject object = new JSONObject();
		BigDecimal totalAmt =new BigDecimal("0");
		BigDecimal totalFeeAmt =new BigDecimal("0");
		BigDecimal fee =new BigDecimal(singleFeeAmt);
//		int fee = Integer.parseInt(singleFeeAmt);
		
		//此次批量代付的批次号
		String orderNo = new SimpleDateFormat("yyyyMMdd").format(new Date()) + RandomStringUtils.randomNumeric(5);
		
		for(int i=0; i<Integer.valueOf(dataTotal); i++) {
			
			TopaySingleDetail singleDetail = new TopaySingleDetail();
			
			//生成单笔代付的批号
			String batNo = new SimpleDateFormat("yyyyMMdd").format(new Date()) + RandomStringUtils.randomNumeric(5);
			//单笔批次号
			singleDetail.setBatNo(batNo);
			
			//序号
			singleDetail.setRowNo("111");
			
			//此次操作的批次号(多笔单笔代付共用同一个orderNo批次号,用于存放到批量表中)
			singleDetail.setOrderNo(orderNo);
			
			//发起此次代付的商户merchantCode
			String merchantCode = topayService.selInfoByMerchantCode(paerMerchantCode);
			if( merchantCode == null ){
				object.put("result", "FAIL");
				object.put("message", "商户号错误,请重新填写商户号");
				return object.toString();
			}else{
				singleDetail.setPaerMerchantCode(paerMerchantCode);
			}
			
			
			//单笔代付的手续费
			singleDetail.setSingleFeeAmt(fee);
			
			//收款人名称
			singleDetail.setRecAccountName(recAccountName[i]);
			
			//收款人银行名称
			singleDetail.setRecCardName(recCardName[i]);
			
			//收款人银行编号
			TyyBankInfo bankInfo = topayService.selBankCodeByName(recCardName[i]);
			if( bankInfo == null){
				object.put("result", "FAIL");
				object.put("message", "银行号为空,请重新填写银行信息");
				return object.toString();
			}else{
				singleDetail.setRecBankCode(bankInfo.getBankCode());
			}
			
			//收款人银行账号
			singleDetail.setRecAccountNo(recAccountNo[i]);
			
			//收款人银行卡开户省份
			ProvinceBean provinceBean = topayService.selProvinceByName(recBankProvince[i]);
			if(provinceBean ==null ){
				object.put("result", "FAIL");
				object.put("message", "开户省份号为空,请重新填写开户省份信息");
				return object.toString();
			}else{
				singleDetail.setProvinceCode(provinceBean.getProvinceCode());
			}
			
			
			//收款人银行卡开户城市
			CityNew cityBean = topayService.selCityByName(recBankCity[i]);
			if(cityBean ==null ){
				object.put("result", "FAIL");
				object.put("message", "开户城市号为空,请重新填写开户城市信息");
				return object.toString();
			}else{
			singleDetail.setCityCode(cityBean.getCityId());
			}
			
			//单笔代付金额
			BigDecimal sumTransAmt = topayService.sumTransAmtInToday(recAccountNo[i]);
			if(sumTransAmt !=null ){
				int a = (BigDecimal.valueOf(Double.valueOf(recTransAmt[i]))).add(sumTransAmt).compareTo( new BigDecimal(50000));
				if(a == 1){
					object.put("result", "FAIL");
					object.put("message", "该银行卡今日代付金额超过额度");
					return object.toString();
				}
			}
			singleDetail.setTransAmt(BigDecimal.valueOf(Double.valueOf(recTransAmt[i])));
			
			
			//交易类型  ："00"表示银行卡  "01"表示支付到账号
			singleDetail.setTopayType("00");
			
			//交易状态 
			singleDetail.setTradeStatus("01");
			
			list.add(singleDetail);
			
			totalAmt =totalAmt.add(singleDetail.getTransAmt());
			totalFeeAmt = totalFeeAmt.add(fee);
			
			topayService.addSingleTopay(singleDetail);
			
		}

		//批量批次号
		batDetail.setBatNo(orderNo);
		//批量总金额
		batDetail.setSumAmt(totalAmt);
		//批量总笔数
		batDetail.setSumCount(Integer.valueOf(dataTotal));
		//交易状态
		batDetail.setTradeStatus("01");
		//交易类型
		batDetail.setTopayType("00");
		//总手续费
		batDetail.setFeeAmt(totalFeeAmt);
		//获取当前用户ID
		batDetail.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		//商户号
		batDetail.setPaerMerchantCode(paerMerchantCode);
		//成功金额
		batDetail.setSuccAmt(new BigDecimal(0.00));
		//成功笔数
		batDetail.setSuccCount(0);
		//失败金额
		batDetail.setFailAmt(new BigDecimal(0.00));
		//失败笔数
		batDetail.setFailCount(0);
		topayService.addBatTopay(batDetail);
		
		object.put("result", "SUCCESS");
		object.put("message", "代付成功");
		
		return object.toString();
	}
	
	
	/**
	 * 根据省份code获取城市列表
	 */
//	@RequestMapping(TopayPath.SELCITY)
	@ResponseBody
	public String listCityByProvinceCode(String provinceCode){
		JSONObject js = new JSONObject();
		try {
			List<CityNew> list = topayService.listCityByProvinceCode(provinceCode);
			js.put("cityList", list);
			js.put("result", "success");
			
		}catch(Exception e) {
			e.printStackTrace();
			js.put("result","fail");
			js.put("message", "查询城市列表出错");
		}
		return js.toJSONString();
	}
	
	/**
	 * 计算总金额
	 * @param sumAmt
	 * @param feeAmt
	 * @return
	 */
	public static String addMoney(String sumAmt,BigDecimal feeAmt){   
		BigDecimal b1 = new BigDecimal(sumAmt);   		
		return b1.add(feeAmt).toString();   
	}
	
	// 读取服务器图片
	@RequestMapping(TopayPath.GETPICTURE)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scanPath = request.getParameter("scanPath");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");

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
	 * 统计代付利润
	 */
	@RequestMapping(TopayPath.PROFITLIST)
	public ModelAndView profitList() {	
		ModelAndView mv = new ModelAndView(TopayPath.BASE + TopayPath.PROFITLIST);
		//初始化日期
		DateBean dateBean = new DateBean();		
		this.initDate(dateBean);
		//查询今日充值手续费利润
		ProfitBean todayRechargeBean = topayService.getProfitOfRecharge(dateBean);
		//查询今日代付手续费利润
		ProfitBean todayPoundageBean = topayService.getProfitOfPoundage(dateBean);
		//查询内部户可用余额
		BigDecimal usableAmt = this.getUsableAmt();
		
//		System.out.println("充值手续费收入:"+todayRechargeBean.getIncomeAmt());
//		System.out.println("充值手续费支出:"+todayRechargeBean.getExpenseAmt());
//		System.out.println("充值手续费利润:"+todayRechargeBean.getProfitAmt());
//		System.out.println("代付手续费收入:"+todayPoundageBean.getIncomeAmt());
//		System.out.println("代付手续费支出:"+todayPoundageBean.getExpenseAmt());
//		System.out.println("代付手续费利润:"+todayPoundageBean.getProfitAmt());
		String accountNumber = ACCOUNT_NUMBER;
		String accountName = null;
		mv.addObject("todayRechargeProfit", todayRechargeBean.getProfitAmt());
		mv.addObject("todayPoundageProfit", todayPoundageBean.getProfitAmt());
		mv.addObject("todayAllProfit",todayRechargeBean.getProfitAmt().add(todayPoundageBean.getProfitAmt()));
		mv.addObject("usableAmt",usableAmt);
		mv.addObject("accountNumber",accountNumber);
		mv.addObject("accountName",accountName);
		return mv;
	}
	/**
	 * 根据日期与类型查询利润
	 */
	@RequestMapping(TopayPath.QUERYPROFIT)
	@ResponseBody
	public String quertProfit(DateBean queryDate,String flag) {		
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		//查询充值手续费利润
		try {	
			if("recharge".equals(flag)) {
				ProfitBean rechargeBean = topayService.getProfitOfRecharge(queryDate);
				json.put("rechargeProfit", rechargeBean.getProfitAmt());
				
				//查询代付手续费利润
			}else if("poundage".equals(flag)) {
				ProfitBean poundageBean = topayService.getProfitOfPoundage(queryDate);
				json.put("poundageProfit", poundageBean.getProfitAmt());
				
			}
			json.put("result", "SUCCESS");
		}catch(Exception e) {
			json.put("result","FALSE");
			json.put("message", e.toString());
			logger.error("查询"+flag+"类型利润出错{}"+queryDate.toString(),e.toString());
		}
		return json.toJSONString();
	}
	
	/**提现**/
	@RequestMapping(TopayPath.CASHWITHDRAWAL)
	@ResponseBody
	public String cashWithdrawal(BigDecimal money,String accountNumber,String accountName) {
		JSONObject json = new JSONObject();
		if(money == null || (money.compareTo(new BigDecimal(0))<0)) {			
			logger.error(">>>>>>>提现金额格式填写错误,提现失败{}",money);
			json.put("result", "FALSE");
			json.put("message","提现金额填写格式有误");
			return json.toJSONString();
		}
		BigDecimal usableAmt = this.getUsableAmt();
		if( money.compareTo(usableAmt) > 0 ) {			
			logger.error(">>>>>>>提现金额超过总收余额,提现失败{},提现金额为"+money+",总收益余额为:"+usableAmt);
			json.put("result", "FALSE");
			json.put("message","提现金额超过总收益余额");
			return json.toJSONString();
		}
		try {
			String custId = sevenPayPlugin.findDictByPath("core.DF_SEVEN_INCOME_INNER_ID");
			TdPaymentBatDetailBean detail = topayService.cashWithdrawal(money,custId,accountNumber,accountName);
			if(detail != null) {
				//调用通道方法,发起提现
				this.cashApply(detail);			
			}
			
		}catch(Exception e) {
			logger.error(">>>>>>>提现处理发生异常，提现失败{}",e);
			json.put("result", "FALSE");
			json.put("message","提现处理失败,详细原因请联系技术人员查看相关日志");
			return json.toJSONString();
			
		}
		BigDecimal newUsableAmt = this.getUsableAmt();
		json.put("result", "SUCCESS");
		json.put("newUsableAmt", newUsableAmt);
		return json.toJSONString();
	}
	/**获取内部户可用余额**/
	public BigDecimal getUsableAmt(){
		RequestMessage<QueryAcctSevenRequest> requestMessage = new RequestMessage<QueryAcctSevenRequest>();
		{
			requestMessage.setMsgType(MsgType.ACCT_SEVEN_QUERY);
			requestMessage.setReqSysId(ReqSysId.BMS);
			requestMessage.setReqDatetime(new Date());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqSerialId(DatetimeUtils.datetime());
			
			QueryAcctSevenRequest request = new QueryAcctSevenRequest();
			{
				//String accId = sevenPayPlugin.findDictByPath("core.DF_SEVEN_INCOME_INNER_ID");
				String channelName = CHANNEL_NAME;  // 渠道名称
				String channelCode = CHANNEL_NAME; //(String)topayService.searchProperty("/topayProfit.properties", "CHANNEL_NAME");
				if(null == channelCode && null !=channelName){
					request.setChannelCode(channelName.toUpperCase());
				}else{
					request.setChannelCode(channelCode.toUpperCase());
				}
				request.setAcctId(this.queryInnerAcc(channelCode));
				request.setAcctType(AcctType.SEVEN_INNER);
			}
			requestMessage.setRequest(request);
		}
		ResponseMessage<QueryAcctSevenResponse> responseMessage = coreServiceInterface.queryAcctSeven(requestMessage);
		logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
		if(responseMessage.getResponse() != null) {
			BigDecimal usableAmt  = responseMessage.getResponse().getAcctSeven().getUsableAmt();
			return usableAmt;
		}else {
			logger.error("查询内部户余额出错");
			return new BigDecimal(0);
			
		}
	}
	/**向渠道发起提现**/
	public void cashApply(TdPaymentBatDetailBean detail) throws Exception{
		try {
			RequestMessage<BussAgentpayApplyRequest> requestMessage = new RequestMessage<BussAgentpayApplyRequest>();
			{
								
				requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_APPLY); //代付申请
				requestMessage.setReqDatetime(new Date());
				requestMessage.setReqMsgNum(2);
				requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); //来源系统的代码
				requestMessage.setReqSerialId(DatetimeUtils.datetime());
				
				BussAgentpayApplyRequest request_ = new BussAgentpayApplyRequest();
				{
					//String accId = sevenPayPlugin.findDictByPath("core.DF_SEVEN_INCOME_INNER_ID");
					String channelCode = CHANNEL_NAME; //(String)topayService.searchProperty("/topayProfit.properties", "CHANNEL_NAME");
					request_.setAcctId(this.queryInnerAcc(channelCode));
					request_.setBrief("代付收益账户代付申请");
					request_.setCurrCode(RequestColumnValues.CurrCode.CNY);
					request_.setOperateType(RequestColumnValues.BussAgentPayOperate.APPLY);
					request_.setPayType("0"); //付款方式 pay_type ，0：付到银行卡（默认）；1：付到其代付账户
					request_.setAgentpayAmt(detail.getTransAmtCount()); //代付金额
					request_.setAgentpayFeeAmt(new BigDecimal(detail.getSingleFeeAmt()));//代付手续费
					request_.setOriginMsgId("");
					request_.setProductCode("");
					request_.setChannelCode(channelCode.toUpperCase());
					request_.setBatchNo(detail.getBatNo());
					request_.setBizOrderNo(detail.getChannelOrderId());
				}

				requestMessage.setRequest(request_);
			}
			
			ResponseMessage<BussAgentpayApplyResponse> responseMessage = coreServiceInterface.innerBussAgentpDFApply(requestMessage);
			if(StringUtils.isNotEmpty(responseMessage.getMsgId())){
				detail.setCoreSn(responseMessage.getMsgId());
				//插入核心流水号
				topayService.updateCoreSn(detail);
			}else {
				logger.error(">>>>>>获取核心流水号失败");
				throw new Exception("获取核心流水号失败");
			}
			logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("<<<<<<<<<<<向渠道发起提现发生异常{}",e);
			throw e;
		}
	}
	public String queryInnerAcc(String channelName){
		//sevenPayPlugin = (IPlugin)SpringUtils.getBean("sevenpayPluginHttpInvoker");
		//String accId = sevenPayPlugin.findDictByPath("core.DF_SEVEN_INCOME_INNER_ID");
		RequestMessage<QueryAcctSevenRequest> requestMessage = new RequestMessage<QueryAcctSevenRequest>();
		{
			requestMessage.setMsgType(MsgType.ACCT_SEVEN_QUERY);
			requestMessage.setReqSysId(ReqSysId.BMS);
			requestMessage.setReqDatetime(new Date());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqSerialId(DatetimeUtils.datetime());
			QueryAcctSevenRequest request = new QueryAcctSevenRequest();
			{
				//request.setAcctId(accId);
				//request.setAcctId("SEVENBUSSDF1802019010717105866500002");
				request.setChannelCode(channelName.toUpperCase());
				request.setAcctType(AcctType.SEVEN_INNER);
			}
			requestMessage.setRequest(request);
		}
		ResponseMessage<QueryAcctSevenResponse> responseMessage = coreServiceInterface.queryAcctSeven(requestMessage);
		logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage,true));
		if(responseMessage.getResponse() != null) {
			return responseMessage.getResponse().getAcctSeven().getAcctId();
		}else {
			logger.error("查询内部户AcctId出错");
		}
		
		return null;
	}
	
	
	/**初始化日期**/
	public void initDate(DateBean dateBean) {
		SimpleDateFormat  format = new SimpleDateFormat ("yyyy-MM-dd");	
		dateBean.setRechargeStart(format.format(new Date()));	
		dateBean.setRechargeEnd(format.format(new Date()));	
		dateBean.setPoundageStart(format.format(new Date()));	
		dateBean.setPoundageEnd(format.format(new Date()));
		
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

	
	
}
