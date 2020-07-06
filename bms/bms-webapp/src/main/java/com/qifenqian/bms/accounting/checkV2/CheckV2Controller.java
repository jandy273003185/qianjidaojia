package com.qifenqian.bms.accounting.checkV2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.qifenqian.bms.accounting.checkV2.bean.CheckDetail;
import com.qifenqian.bms.accounting.checkV2.bean.CheckStats;
import com.qifenqian.bms.accounting.checkV2.bean.ExplorerCheckDetail;
import com.qifenqian.bms.accounting.checkV2.service.CheckDetailServic;
import com.qifenqian.bms.accounting.checkV2.service.CheckStatsService;
import com.qifenqian.bms.platform.utils.DownLoadUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;
import com.qifenqian.bms.platform.web.page.Page;
import com.seven.micropay.channel.enums.PaychannelType;
import com.seven.micropay.commons.util.DateUtil;


@Controller
@RequestMapping(CheckV2Path.BASE)
public class CheckV2Controller {
	private static Logger logger = LoggerFactory.getLogger(CheckV2Controller.class);
	@Autowired
	private CheckDetailServic checkDetailServic;
	@Autowired
	private CheckStatsService checkStatsService;
	
	@RequestMapping(CheckV2Path.LIST)
	@Page
	public ModelAndView list(CheckStats requestBean) {
		logger.info("对账汇总查询，参数={}",JSON.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(CheckV2Path.BASE+CheckV2Path.LIST);
		mv.addObject("checkStatsRequestBean", requestBean);
		mv.addObject("checkStatsList", checkStatsService.findCheckStats(requestBean));
		return mv;
	}
	@RequestMapping(CheckV2Path.EXPLORER_LIST)
	public void explorerList(CheckStats requestBean) {
		logger.info("对账汇总导出，参数={}",JSON.toJSONString(requestBean, true));
	}
	@RequestMapping(CheckV2Path.DETAIL)
	@Page
	public ModelAndView detail(CheckDetail requestBean) {
		logger.info("对账详情列表，参数={}",JSON.toJSONString(requestBean, true));
		ModelAndView mv = new ModelAndView(CheckV2Path.BASE+CheckV2Path.DETAIL);
		mv.addObject("checkDetailRequestBean", requestBean);
		mv.addObject("checkDetailList", checkDetailServic.findCheckDetail(requestBean));
		return mv;
	}
	@RequestMapping(CheckV2Path.EXPLORER_DETAIL)
	public void explorerDetail(CheckDetail requestBean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("对账详情导出，参数={}",JSON.toJSONString(requestBean, true));
		List<CheckDetail> checkDetails = checkDetailServic.findCheckDetail(requestBean);
		List<ExplorerCheckDetail> list = new ArrayList<ExplorerCheckDetail>();
		
		for(CheckDetail c:checkDetails){
			ExplorerCheckDetail ec = new ExplorerCheckDetail();
			BeanUtils.copyProperties(c, ec);
			
			String tradeTypeStr="";
			if("pay".equals(c.getTradeType())){
				tradeTypeStr = "支付";
			}
			if("refund".equals(c.getTradeType())){
				tradeTypeStr = "退款";
			}
			
			String channelTypeStr = "";
			for(PaychannelType p:PaychannelType.values()){
				if(p.name().equals(c.getChannelType())){
					channelTypeStr = p.getText();
					break;
				}
			}
			
			String checkResultStr = "";
			if("SUCCESS".equals(c.getCheckResult())){
				checkResultStr = "正常";
			}
			if("PLACK".equals(c.getCheckResult())){
				checkResultStr = "丢单";
			}
			if("CLACK".equals(c.getCheckResult())){
				checkResultStr = "掉单";
			}
			if("OTHERS".equals(c.getCheckResult())){
				checkResultStr = "其他差错账";
			}
			
			ec.setTradeTypeStr(tradeTypeStr);
			ec.setChannelTypeStr(channelTypeStr);
			ec.setCheckResultStr(checkResultStr);
			ec.setCheckDateStr(DateUtil.format(c.getCheckDate(), DateUtil.YYYY_MM_DD));
			ec.setOrderCreateTimeStr(DateUtil.format(c.getOrderCreateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
			ec.setOrderFinishTimeStr(DateUtil.format(c.getOrderFinishTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
			list.add(ec);
		}
		
		
		String[] headers = { "平台订单号","交易类型","服务商号","商户号", "商户名称", "渠道", "渠道订单号","交易金额","渠道手续费","交易手续费","划款金额","退款原订单号","订单创建时间","订单完成时间","对账结果","对账日期"};
		String fileName = DateUtil.format(new Date(), DateUtil.YYYY_MM_DD)+"_对账明细报表.xls";
		
		
		Map<String, String> fileInfo = ExportExcelUtils.exportExcel(list,headers, fileName, "对账明细报表", request);
		try {
			DownLoadUtils.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出对账明细报表成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("导出对账明细报表异常", e);
			e.printStackTrace();
		}
		
	}
}
