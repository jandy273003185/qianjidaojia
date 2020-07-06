package com.qifenqian.bms.paymentmanager.topay;
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
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.topay.service.PaymentQueryService;


@Controller
@RequestMapping(toPayCollectDailyPath.BASE)
public class toPayQueryController {
	private Logger logger = LoggerFactory.getLogger(toPayQueryController.class);
	
	@Autowired
	private PaymentQueryService paymentQueryService;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@RequestMapping(toPayCollectDailyPath.TOPAYQUERYLIST)
	public ModelAndView toPayQueryList(TdPaymentBatInfo paymentBatInfo ){
		/*去掉多余的空格*/
		if (!StringUtils.isBlank(paymentBatInfo.getBatNo())) {
			paymentBatInfo.setBatNo(paymentBatInfo.getBatNo().trim());
		}
		if (!StringUtils.isBlank(paymentBatInfo.getPaerAcctNo())) {
			paymentBatInfo.setPaerAcctNo(paymentBatInfo.getPaerAcctNo().trim());
		}
		//获取列表
		ModelAndView mv=new ModelAndView(toPayCollectDailyPath.BASE + toPayCollectDailyPath.TOPAYQUERYLIST);
		List<TdPaymentBatInfo> paymentBatInfos = paymentQueryService.getPaymetList(paymentBatInfo);
		mv.addObject("paymentBatInfos", paymentBatInfos);
		mv.addObject("paymentBatInfos", JSONObject.toJSON(paymentBatInfos));
		mv.addObject("queryBean", paymentBatInfo);
		return  mv;
	}
	
    @RequestMapping(value="/showPayments")
   public ModelAndView paymentBatListInfos(String batNo,TdPaymentBatDetail paymentBatDetail){
    	 
			paymentBatDetail.setBatNo(batNo);
			logger.info("开始查询代付批次{}信息",batNo);
			ModelAndView mv=new ModelAndView(toPayCollectDailyPath.BASE + toPayCollectDailyPath.PAYMENTINFO);
			List<TdPaymentBatDetail> paymentBatListInfos = paymentQueryService.getPaymetbatNoList(batNo); 
			System.out.println(paymentBatListInfos.size());
			mv.addObject("paymentBatInfos", paymentBatListInfos);
			mv.addObject("paymentBatInfos", JSONObject.toJSON(paymentBatListInfos));
			mv.addObject("queryBean", paymentBatDetail);
			return  mv;	
			
		}
    
    /**
     * 导出	
     * 
     */
    	
    @RequestMapping(toPayCollectDailyPath.EXPORTPAYMENT)
    public void backExportExcel(TdPaymentBatInfo paymentBatInfo, HttpServletRequest request, HttpServletResponse response) {
    		logger.info("导出后台代付列表信息");
    	try {
    			List<TdPaymentBatInfo> excels = paymentQueryService.exportPaymentList(paymentBatInfo);
    			System.out.println(excels.size());
    			String[] headers = { "批次号", "付款账号","总金额", "总笔数", "成功总金额", "成功笔数","失败总金额", "失败笔数", "状态","创建时间"};
    			String fileName = DatetimeUtils.dateSecond() + "_代付信息.xls";
    			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代付查询列表", request);
    			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
    			logger.info("导出excel代付列表成功");
    		  } catch (Exception e) {
    			logger.error("导出excel代付列表异常", e);
    			throw new RuntimeException(e);
    		 }
    	   }
    
    /**
     * 
     * 
     */
    @RequestMapping(toPayCollectDailyPath.EXPORTPAYMENTINFOS)
    public void ExportExcel(TdPaymentBatDetail paymentBatDetail, HttpServletRequest request, HttpServletResponse response) {
    		logger.info("导出后台代付列表信息");
    	try {
    			List<TdPaymentBatDetail> excels = paymentQueryService.exportPaymentInfoList(paymentBatDetail);
    			System.out.println(excels.size());
    			String[] headers = { "批次号", "序号","收款人名称", "收款人账号", "收款人银行号","收款人联行号", "收款金额","状态","处理情况摘要" ,"创建人","创建时间"};
    			String fileName = DatetimeUtils.dateSecond() + "_代付信息.xls";
    			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代付查询列表", request);
    			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
    			logger.info("导出excel代付列表成功");
    		  } catch (Exception e) {
    			logger.error("导出excel代付列表异常", e);
    			throw new RuntimeException(e);
    		 }
    	   }
    
    @RequestMapping(toPayCollectDailyPath.SELECTPAYMENTS)
    public ModelAndView selpaymentBatListInfos(TdPaymentBatDetail paymentBatDetail){
    	/*去掉多余的空格*/
		if (!StringUtils.isBlank(paymentBatDetail.getBatNo())) {
			paymentBatDetail.setBatNo(paymentBatDetail.getBatNo().trim());
		}
		if(!StringUtils.isBlank(paymentBatDetail.getRecAccountName())){
			paymentBatDetail.setRecAccountName(paymentBatDetail.getRecAccountName().trim());
		}
 		logger.info("开始查询代付批次{}信息",paymentBatDetail);
 		ModelAndView mv=new ModelAndView(toPayCollectDailyPath.BASE + toPayCollectDailyPath.PAYMENTINFO);
 		List<TdPaymentBatDetail> paymentBatListInfos = paymentQueryService.selectPaymetbatNoList(paymentBatDetail); 
 		mv.addObject("paymentBatInfos", paymentBatListInfos);
 		mv.addObject("paymentBatInfos", JSONObject.toJSON(paymentBatListInfos));
 		mv.addObject("queryBean", paymentBatDetail);
 		return  mv;	
 			
 		}
}
