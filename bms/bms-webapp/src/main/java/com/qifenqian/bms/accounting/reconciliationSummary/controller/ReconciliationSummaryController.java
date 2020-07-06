package com.qifenqian.bms.accounting.reconciliationSummary.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qifenqian.bms.platform.common.utils.DatetimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.ExportDetail;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDetail;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.ReconDiff;
import com.qifenqian.bms.accounting.reconciliationSummary.bean.Summary;
import com.qifenqian.bms.accounting.reconciliationSummary.mapper.ReconciliationSummaryMapper;
import com.qifenqian.bms.accounting.reconciliationSummary.service.ReconciliationsummaryService;
import com.qifenqian.bms.platform.utils.DownLoadUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.weixin.CheckReq;
import com.seven.micropay.channel.enums.CheckType;
import com.seven.micropay.channel.service.ICheckService;
import com.seven.micropay.commons.Base64;
import com.seven.micropay.commons.util.DateUtil;
import com.seven.micropay.commons.util.GzipUtil;

@Controller
@RequestMapping(ReconciliationSummaryPath.BASE)
public class ReconciliationSummaryController {
	
	private static Logger logger = LoggerFactory.getLogger(ReconciliationSummaryController.class);
	
	@Autowired
	private ReconciliationsummaryService reconciliationsummaryService;
	
	@Autowired
	private ReconciliationSummaryMapper reconciliationSummaryMapper;

	@Autowired
	private UserService userService;
		
	@Resource
	private ICheckService checkService;

	

	
	
	//对账列表
	@RequestMapping(ReconciliationSummaryPath.LIST)
	public ModelAndView list(ReconDetail reconDetail) {
		
		ModelAndView mv = new ModelAndView(ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.LIST);
		
		List<Summary> ReconciliationSummaryList = reconciliationsummaryService.selectSummary(reconDetail);
		List<User> userList = userService.getUserList(new User());
		
		mv.addObject("queryBean", reconDetail);
		mv.addObject("appointedAuditorList", JSONObject.toJSON(userList));
		mv.addObject("ReconciliationSummaryList", JSONObject.toJSON(ReconciliationSummaryList));
		return mv;
	}
	
	/*交易明细*/
	@RequestMapping(value = ReconciliationSummaryPath.DETAILS)
	public ModelAndView details(ReconDetail reconDetail,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.DETAILS);
		
		List<ReconDetail> ReconciliationDetailsList = reconciliationsummaryService.selectByReconResult(reconDetail);
		List<User> userList = userService.getUserList(new User());
		
		mv.addObject("queryBean", reconDetail);
		mv.addObject("appointedAuditorList", JSONObject.toJSON(userList));
		mv.addObject("ReconciliationDetailsList", ReconciliationDetailsList.size() == 0 ? null : ReconciliationDetailsList);
		return mv;
	}
	
	/*交易明细报表*/
	@RequestMapping(value = ReconciliationSummaryPath.DETAILSEXPORT)
	public void detailsExport(ReconDetail reconDetail,HttpServletRequest request, HttpServletResponse response) {
		logger.info("交易明细报表导出：{}", JSONObject.toJSONString(reconDetail, true));
		
		try {
			List<ReconDetail> excels = reconciliationSummaryMapper.selectByReconResult(reconDetail);
			List<ExportDetail> list = new ArrayList<ExportDetail>();
			
			if(null != excels) {
				for(ReconDetail detail : excels) {
					ExportDetail ed = new ExportDetail();
					BeanUtils.copyProperties(detail, ed);
					
					ed.setPaychannelTypeStr(detail.getPaychannelType().getText());
					String rs = "DIFF_SUCCESS".equals(detail.getReconResult()) ? "差错成功" 
							: "SUCCESS".equals(detail.getReconResult()) ? "成功" 
							: "LACK".equals(detail.getReconResult()) ? "掉单"
							: "丢单"; 
					ed.setReconResult(rs);
					ed.setInOut("OUT".equals(detail.getInOut()) ? "收入" : "支出");
					list.add(ed);
				}
			}
		
			String[] headers = { "编号","订单编号","外部订单号","支付渠道", "交易金额", "交易类型", "IN 收入 OUT 支出","交易时间","创建时间"};
			String fileName = DatetimeUtils.datetime()+"_交易明细统计报表_.csv";
			
			
			Map<String, String> fileInfo = ExportExcelUtils.exportExcel(list,headers, fileName, "交易明细统计报表", request);
			DownLoadUtils.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "csv");
			logger.info("导出交易明细统计报表成功");

		} catch (Exception e) {
			logger.error("导出交易明细统计报表异常", e);
			e.printStackTrace();
		}	
	}
	
	/*交易原数据*/
	@RequestMapping(value = ReconciliationSummaryPath.DETAILSCHECKEXPORT)
	public void detailsCheckExport(ReconDetail reconDetail,HttpServletRequest request, HttpServletResponse response) {
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		
		try {
			List<ReconDetail> excels = reconciliationSummaryMapper.selectByReconResult(reconDetail);
			String[] orderIds = new String[excels.size()];
			for(int i=0;i<excels.size();i++) {
				orderIds[i] = excels.get(i).getOrderId();
			}
			
 			String content = this.checkService.download(reconDetail.getPaychannelType(), DateUtil.parse(reconDetail.getCreateTime(), "yyyy-MM-dd"), orderIds);
			
			byte[] data = GzipUtil.unzip(Base64.decode(content));
			
			output = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[4096];// 缓冲区
			
			// 文件名
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("download.txt".getBytes(), "ISO8859-1") + "\"");
			response.setContentLength(data.length);
			
			input = new BufferedInputStream(new ByteArrayInputStream(data));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			
			output.flush();
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出交易原数据异常", e);
			e.printStackTrace();
		} finally {
			try {
				// 关闭流
				if (input != null)
					input.close();
				if (output != null)
					output.close();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
	//对账
	@RequestMapping(value = ReconciliationSummaryPath.RECONCILIATION)
	@ResponseBody
	public String Reconciliation(ReconDetail reconDetail,HttpServletRequest request, HttpServletResponse response) {
		try {
			CheckReq checkReq = new CheckReq();
			checkReq.setCheckType(CheckType.HAND);
			checkReq.setDate(DateUtil.parse(reconDetail.getCreateTime(), DateUtil.YYYY_MM_DD));
			checkReq.setPaychannelType(reconDetail.getPaychannelType());
			
			ReStatus status = this.checkService.check(checkReq);
			if(ReStatus.SUCCESS == status) {
				return "success";
			}
		} catch(Exception e) {
			logger.error("对账失败：", e);
		}
		
		return "error";
		
	}

	
	/*差错处理*/
	@RequestMapping(value = ReconciliationSummaryPath.ERRORDISPOSAL)
	public ModelAndView errordisposal(ReconDiff reconDiff,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(ReconciliationSummaryPath.BASE + ReconciliationSummaryPath.ERRORDISPOSAL);
		
		List<ReconDiff> ReconciliationDetailsList = reconciliationsummaryService.selectByErrorDisposal(reconDiff);
		List<User> userList = userService.getUserList(new User());
		mv.addObject("queryBean", reconDiff);
		mv.addObject("appointedAuditorList", JSONObject.toJSON(userList));
		mv.addObject("ReconciliationDetailsList", ReconciliationDetailsList.size() == 0 ? null : ReconciliationDetailsList);
		return mv;
	}
	
	
	/*处理*/
	@RequestMapping(value = ReconciliationSummaryPath.DISPOSAL)
	@ResponseBody
	public String disposal(ReconDiff reconDiff,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			reconciliationSummaryMapper.updateByErrorDisposal(reconDiff);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("处理失败：", e);
		}
		
		return "error";
	}
	
}
